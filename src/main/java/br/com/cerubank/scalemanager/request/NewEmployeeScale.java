package br.com.cerubank.scalemanager.request;

import br.com.cerubank.scalemanager.model.Employee;
import br.com.cerubank.scalemanager.model.Scale;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewEmployeeScale {

    @NotBlank
    private Employee employee;

    @NotBlank
    private Scale scale;
}
