package br.com.cerubank.scalemanager.controller;




import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.EmployeeLevel;
import br.com.cerubank.scalemanager.request.EmployeeLevelRequest;
import br.com.cerubank.scalemanager.service.EmployeeLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-level")
public class EmployeeLevelController {

    @Autowired
    private EmployeeLevelService employeeLevelService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeLevel>> getEmployeeLevelById() {
        List<EmployeeLevel> employeeLevels = employeeLevelService.findAllEmployeesLevel();
        return new ResponseEntity<>(employeeLevels, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addEmployeeLevel(@RequestBody EmployeeLevelRequest request) {
        employeeLevelService.addEmployeeLevel(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/update/{employeeLevelCode}")
    public ResponseEntity<?> updateEmployeeLevel(@RequestBody EmployeeLevelRequest request, @PathVariable("employeeLevelCode") String employeeLevelCode) {
        employeeLevelService.updateEmployeeLevel(request, employeeLevelCode);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployeeLevel(@PathVariable("id") Long id) {
        try {
            employeeLevelService.deleteEmployeeLevel(id);
            return ResponseEntity.ok().build();
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body("Level not found in database " + e);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
