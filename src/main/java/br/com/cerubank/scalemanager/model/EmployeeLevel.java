package br.com.cerubank.scalemanager.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Data
@Entity
@Table(name = "employee_level")
public class EmployeeLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee_level")
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "description")
    private String description;

    @Column(nullable = false, updatable = false)
    private String employeeLevelCode;
}
