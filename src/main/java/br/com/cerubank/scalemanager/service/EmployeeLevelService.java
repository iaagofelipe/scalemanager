package br.com.cerubank.scalemanager.service;


import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.Employee;
import br.com.cerubank.scalemanager.model.EmployeeLevel;
import br.com.cerubank.scalemanager.repository.EmployeeLevelRepository;
import br.com.cerubank.scalemanager.request.EmployeeLevelRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeLevelService {

    @Autowired
    private EmployeeLevelRepository employeeLevelRepository;

    ModelMapper mapper = new ModelMapper();

    public void addEmployeeLevel(EmployeeLevelRequest request) {
        EmployeeLevel employeeLevel = mapper.map(request, EmployeeLevel.class);
        employeeLevel.setEmployeeLevelCode(UUID.randomUUID().toString());
        employeeLevelRepository.save(employeeLevel);
    }

    public List<EmployeeLevel> findAllEmployeesLevel() {
        return employeeLevelRepository.findAll();
    }

    public void updateEmployeeLevel(EmployeeLevelRequest request, String employeeLevelCode) {
        EmployeeLevel employeeLevel = employeeLevelRepository.findByEmployeeLevelCode(employeeLevelCode).orElse(null);
        if (employeeLevel != null) {
            employeeLevel.setCode(request.getCode());
            employeeLevel.setDescription(request.getDescription());
            employeeLevelRepository.save(employeeLevel);
        } else {
            throw new ModelNotFoundException("Level not listed or don't exist");
        }
    }

    @Transactional
    public void deleteEmployeeLevel(Long id) {
        Optional<EmployeeLevel> employeeLevel = employeeLevelRepository.findById(id);
        if (employeeLevel.isEmpty()) {
            throw new ModelNotFoundException("Level not listed or don't exist with id: " + id);
        } employeeLevelRepository.deleteEmployeeLevelById(id);
    }

    public EmployeeLevel findEmployeeLevelById(Long id) {
        return employeeLevelRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException("Level not found with id: " + id));
    }


}
