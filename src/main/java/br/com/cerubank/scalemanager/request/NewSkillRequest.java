package br.com.cerubank.scalemanager.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewSkillRequest {

    @NotBlank
    private String description;
}
