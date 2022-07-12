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
    @JoinColumn(name = "employee_level_id")
    private EmployeeLevel employeeLevel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JoinColumn(name = "employee_code", nullable = false, updatable = false)
    private String employeeCode;

    public void addSkill (Skill skill) {
        skills.add(skill);
    }

}
