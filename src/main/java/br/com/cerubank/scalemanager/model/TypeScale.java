package br.com.cerubank.scalemanager.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "type_scale")
public class TypeScale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_type_scale")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private Integer code;

    @Column(nullable = false, updatable = false)
    private String typeScaleCode;

}
