package br.com.cerubank.scalemanager.controller;


import br.com.cerubank.scalemanager.dto.EmployeesDTO;
import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.Employee;
import br.com.cerubank.scalemanager.request.NewEmployeeRequest;
import br.com.cerubank.scalemanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeesDTO>> getAllEmployees() {
        List<EmployeesDTO> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeesById(@PathVariable("id") Long id) {
        Employee employeeById = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody NewEmployeeRequest request) {
        employeeService.addEmployee(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/update/{employeeIdentifier}")
    public ResponseEntity<?> updateEmployee(@RequestBody NewEmployeeRequest request, @PathVariable("employeeIdentifier") String employeeIdentifier) {
       employeeService.updateEmployee(request, employeeIdentifier);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete/{employeeIdentifier}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeIdentifier") String employeeIdentifier) {
        try {
            employeeService.deleteEmployee(employeeIdentifier);
            return ResponseEntity.ok().build();
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body("Employee not found in database" + e);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
