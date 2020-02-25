package com.itmo.usakova.entity.card;

import com.itmo.usakova.entity.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "template_card")
public class TemplateCard implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "invention_date", nullable = false)
    private String inventionDate;

    @Column(name = "def_invention", nullable = false)
    private String defInvention;
}
