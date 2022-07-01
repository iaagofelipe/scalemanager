package br.com.cerubank.scalemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role")
    private Long id;

    @Column(name = "role")
    private String role;

    @Column(name = "description")
    private String description;
}
