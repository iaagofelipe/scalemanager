package br.com.cerubank.scalemanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@NoArgsConstructor
@Entity
public class EmployeeScale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee_scale")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hours_bank_id")
    private HoursBank hoursBank;

    @ManyToOne
    @JoinColumn(name = "scale_id")
    private Scale scale;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public EmployeeScale(Scale scale, Employee employee) {
        this.scale = scale;
        this.employee = employee;
    }
}
