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
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeesDTO>> getAllEmployees() {
        List<EmployeesDTO> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{employeeCode}")
    public ResponseEntity<Employee> findByEmployeeCode(@PathVariable("employeeCode") String employeeCode) {
        Employee employeeById = employeeService.findByEmployeeCode(employeeCode);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody NewEmployeeRequest request) {
        employeeService.addEmployee(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/update/{employeeCode}")
    public ResponseEntity<?> updateEmployee(@RequestBody NewEmployeeRequest request, @PathVariable("employeeCode") String employeeCode) {
       employeeService.updateEmployee(request, employeeCode);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete/{employeeCode}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeCode") String employeeCode) {
        try {
            employeeService.deleteEmployee(employeeCode);
            return ResponseEntity.ok().build();
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body("Employee not found in database" + e);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
