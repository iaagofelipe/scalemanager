package br.com.cerubank.scalemanager.dto;

import lombok.Data;

@Data
public class EmployeesScaleDTO {
    private HoursBankDTO hoursBank;

    private EmployeesDTO employee;
}
