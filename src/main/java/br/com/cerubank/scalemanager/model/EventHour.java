package br.com.cerubank.scalemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "event_hour")
public class EventHour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_event_hour")
    private Long id;

    @Column(name = "credit")
    private Boolean credit;

    @Column(name = "description")
    private String description;

    @Column(nullable = false, updatable = false)
    private String eventHourCode;
}
