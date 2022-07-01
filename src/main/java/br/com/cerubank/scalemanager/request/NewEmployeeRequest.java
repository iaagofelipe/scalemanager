package br.com.cerubank.scalemanager.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class NewEmployeeRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;

    @NotBlank
    private List<String> skillCode;

    @NotEmpty
    private String level;

}
