package br.com.cerubank.scalemanager.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
public class NewScaleRequest {

    @NotBlank
    private String description;

    @NotBlank
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @NotBlank
    private List<String> employees;

    @NotBlank
    private Integer type;



}
