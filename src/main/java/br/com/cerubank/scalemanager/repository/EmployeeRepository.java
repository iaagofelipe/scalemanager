package br.com.cerubank.scalemanager.repository;

import br.com.cerubank.scalemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    void deleteEmployeeById(Long id);

    Optional<Employee> findEmployeeById(Long id);

    Optional<Employee> findByEmployeeIdentifier(String employeeIdentifier);

    void deleteByEmployeeIdentifier(String employeeIdentifier);
}
