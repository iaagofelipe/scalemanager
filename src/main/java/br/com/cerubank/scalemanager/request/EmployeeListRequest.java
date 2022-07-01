package br.com.cerubank.scalemanager.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmployeeListRequest {

    @NotBlank
    private String employeeCode;
}
