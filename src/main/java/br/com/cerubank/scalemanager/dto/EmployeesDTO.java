package br.com.cerubank.scalemanager.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeesDTO {


    private String name;

    private String email;

    private String phone;

    private List<SkillDTO> skills;

    private EmployeeLevelDTO level;

    private String employeeCode;

}
