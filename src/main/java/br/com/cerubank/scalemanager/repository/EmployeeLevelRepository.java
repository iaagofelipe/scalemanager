package br.com.cerubank.scalemanager.repository;

import br.com.cerubank.scalemanager.model.EmployeeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface EmployeeLevelRepository extends JpaRepository<EmployeeLevel, Long> {
    void deleteEmployeeLevelById(Long id);

    Optional<EmployeeLevel> findByEmployeeLevelCode(String employeeLevelCode);
}
