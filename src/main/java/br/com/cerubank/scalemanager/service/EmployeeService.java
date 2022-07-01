package br.com.cerubank.scalemanager.service;


import br.com.cerubank.scalemanager.dto.EmployeesDTO;
import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.Employee;
import br.com.cerubank.scalemanager.model.EmployeeLevel;
import br.com.cerubank.scalemanager.model.Skill;
import br.com.cerubank.scalemanager.repository.EmployeeLevelRepository;
import br.com.cerubank.scalemanager.repository.EmployeeRepository;
import br.com.cerubank.scalemanager.repository.SkillRepository;
import br.com.cerubank.scalemanager.request.NewEmployeeRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeLevelRepository employeeLevelRepository;

    @Autowired
    private SkillRepository skillRepository;

    ModelMapper mapper = new ModelMapper();

    public void addEmployee(NewEmployeeRequest request){
        Employee newEmployee = mapper.map(request, Employee.class);
        newEmployee.setEmployeeIdentifier(UUID.randomUUID().toString());
        Optional<EmployeeLevel> levelEmployee = employeeLevelRepository.findByEmployeeLevelCode(request.getLevel());
        newEmployee.setEmployeeLevel(levelEmployee.get());
        for (String identifier : request.getSkillCode()) {
            Optional<Skill> skill = skillRepository.findBySkillCode(identifier);
            skill.ifPresent(newEmployee::addSkill);
        }
        employeeRepository.save(newEmployee);
    }

    public List<EmployeesDTO> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeesDTO> dtos = employees
                .stream()
                .map(user -> mapper.map(user, EmployeesDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }

    public void updateEmployee(NewEmployeeRequest request, String employeeIdentifier) {
        try {
            Employee employee = employeeRepository.findByEmployeeIdentifier(employeeIdentifier).orElse(null);

            if (employee != null) {
                Optional<EmployeeLevel> levelEmployee = employeeLevelRepository.findByEmployeeLevelCode(request.getLevel());
                employee.setEmployeeLevel(levelEmployee.get());
                employee.setName(request.getName());
                employee.setEmail(request.getEmail());
                employee.setPhone(request.getPhone());
                employeeRepository.save(employee);
            }

        } catch (Exception e) {
            throw new NullPointerException("Employee not found " + e);
        }
    }

    @Transactional
    public void deleteEmployee(String employeeIdentifier) {
        Optional<Employee> employee = employeeRepository.findByEmployeeIdentifier(employeeIdentifier);
        if (employee.isEmpty()) {
           throw new ModelNotFoundException("Employee not found with id: " + employeeIdentifier);
        } employeeRepository.deleteByEmployeeIdentifier(employeeIdentifier);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id).orElseThrow(
                () -> new ModelNotFoundException("Employee with id" + id + "was not found"));
    }
}
