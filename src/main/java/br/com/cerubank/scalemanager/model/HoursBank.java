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
    private Employee employee;

    @ManyToOne
    private EventHour eventHour;
}
