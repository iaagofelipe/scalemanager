package br.com.cerubank.scalemanager.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "scale")
public class Scale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_scale")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "scale_date")
    private Date scaleDate;

    @Column(nullable = false, updatable = false)
    private String scaleCode;


    @OneToMany(mappedBy = "scale", cascade = CascadeType.ALL)
    private List<EmployeeScale> employeeScales;

    @ManyToOne
    @JoinColumn(name = "type_scale_id")
    private TypeScale typeScale;

}
