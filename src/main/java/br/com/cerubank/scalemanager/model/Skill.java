package br.com.cerubank.scalemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_skill")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private Integer code;

    @ManyToMany(mappedBy = "skills")
    private List<Employee> employees;

    @Column(nullable = false, updatable = false)
    private String skillCode;
}
