package br.com.cerubank.scalemanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ScaleDTO {

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private List<EmployeesScaleDTO> employeeScales;

    private TypeScaleDTO typeScale;


}
