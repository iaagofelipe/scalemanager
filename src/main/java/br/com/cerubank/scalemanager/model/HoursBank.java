package br.com.cerubank.scalemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "hours_bank")
public class HoursBank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_hours_bank")
    private Long id;

    @Column(name = "amount_hours")
    private Integer amountHours;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "event_hour_id")
    private EventHour eventHour;
}
