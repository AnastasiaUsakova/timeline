
-- создаем игровую сессию и наполняем колоду картами

delimiter //
create
    definer = root@`%` procedure createSession(out game_session_id int)
begin
    declare template_id, cnt, first_card, num int default 0;


    -- создается новя зписсь в таблице game_session

    insert into game_session (end_time, start_time) VALUES (null, current_time);
    set game_session_id = LAST_INSERT_ID();
    set cnt = (select count(*) from template_card);

    -- привязка шаблонов карт к колоде
    start transaction;

    myloop: loop
        if cnt = 0 then leave myloop; end if;
        set template_id = (select id from template_card limit num, 1);

        insert into original_card (at_deck, template_card_id, session_id, id_table_card) VALUES (true, template_id, game_session_id, null);
        set cnt = cnt - 1;
        set num = num + 1;
    end loop myloop;
    commit;

    -- кладем первую карту на стол
    set first_card = (select id_table_card from original_card limit 1);
    insert into table_card (id_table_card, `index`) VALUES (first_card, 0);
    update original_card set at_deck = false where at_deck = true limit 1;
end; //

delimiter ;

-- регестрируем пользователя в игровую сессию и берем из колоды первые 5 карт в руку
delimiter //
create definer = root@`%` procedure joinGame(IN username varchar(30), IN id_game_session int)
begin
    declare cnt, tmp, id_user int default 0;
    declare mv boolean default false;
    set id_user = (select id from profile where name = username);
    if ((select count(*) from player where session_id = id_game_session) = 0) then set mv = true;
    END IF;
    insert into player (move_queue, session_id, user_id) VALUES (mv, id_game_session, id_user);

    start transaction;
    WHILE cnt < 5 DO
            set tmp = (select id from original_card where at_deck = true limit 1);
            insert into player_card (id, original_card_id) VALUES (id_user, tmp);
            update original_card set at_deck = false where at_deck = true limit 1;
            SET cnt = cnt + 1;
        END WHILE;
    commit;

end //

delimiter ;

-- взять карту из колоды
delimiter //
create
    definer = root@`%` procedure getCard(IN id_player int)
begin
    -- проверяем что у игрока <5 карт, берем первую из колоды и вставляем в таблицу карт игрока
    declare card_id int default 0;
    if((select count(*) from player_card where player_card.id = id_player) < 5) then
        begin
            set card_id = (select id from original_card where at_deck = true limit 1);
            insert into player_card (id, original_card_id) values (id_player, card_id);
            update original_card set at_deck = false where at_deck = true limit 1;
        end;
    end if;
end //

delimiter ;

-- положить карту на стол:
    -- находим id игрока, удаляем запись с этой картой из таблицы карт игрока
    -- и добавляем в таблицу карт на столе. сдвигаем индекс карт на столе
    -- возвращаем id игрока на случай, если он походил неправильно

delimiter //
create
    definer = root@`%` procedure cardToTable(IN id_original_card int, IN position int, OUT tmp int)
begin
    create temporary table put_to_table select id, original_card_id from player_card where original_card_id = id_original_card;
    delete from player_card where original_card_id = id_original_card;
    update table_card set `index` = `index` + 1 where `index` >= position;
    insert into table_card (`index`) VALUES (position);
    select id from put_to_table as tmp;
    drop temporary table if exists put_to_table;
    select tmp;
end //

delimiter ;

-- проверить, правильно ли была положена карта игроком на стол
-- если нет, то перед передачей хода игрок берет себе карту
delimiter //
create procedure checkIfRightPlace(in player_card int, in position int, out next_move int)
begin
    declare leftCard_id, rightCard_id, leftDate, rightDate, thisDate int default 0;

    set leftCard_id = (select id_table_card from table_card where `index` = position - 1);
    set rightCard_id = (select id_table_card from table_card where `index` = position + 1);

    set leftDate = (select invention_date from template_card JOIN original_card ON template_card.id = original_card.template_card_id where id_table_card = leftCard_id);
    set rightDate = (select invention_date from template_card JOIN original_card ON template_card.id = original_card.template_card_id where id_table_card = rightCard_id);
    set thisDate = (select invention_date from template_card JOIN original_card ON template_card.id = original_card.template_card_id where original_card.id = player_card);

    call cardToTable(player_card, position, @next_move);

    if thisDate > rightDate and thisDate < leftDate then call getCard(next_move); end if;
    set next_move = next_move + 1;
    select next_move;

end //

delimiter ;

/* the main game:
   создаем сессию
   игрок присоединяется к игре
   когда их стало два{
    пока у одного не закончатся карты {
        получааем карту,
            проверяем, на место ли кладем
            }
    }
 */
