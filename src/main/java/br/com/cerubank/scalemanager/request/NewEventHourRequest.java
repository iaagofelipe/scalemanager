package br.com.cerubank.scalemanager.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewEventHourRequest {
    @NotBlank
    private Boolean credit;

    @NotBlank
    private String description;
}
