package br.com.cerubank.scalemanager.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmployeeLevelRequest {
    @NotBlank
    private Integer code;

    @NotBlank
    private String description;
}
