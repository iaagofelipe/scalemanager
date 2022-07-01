package br.com.cerubank.scalemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @JoinTable(name = "employee_skill",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn (name = "skill_id"))
    @ManyToMany
    private List<Skill> skills = new ArrayList<>();

    @ManyToOne
    private EmployeeLevel employeeLevel;

    @ManyToOne
    private User user;

    @Column(nullable = false, updatable = false)
    private String employeeIdentifier;

    public void addSkill (Skill skill) {
        skills.add(skill);
    }

}
