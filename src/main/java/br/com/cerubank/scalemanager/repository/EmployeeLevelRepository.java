package br.com.cerubank.scalemanager.repository;

import br.com.cerubank.scalemanager.model.EmployeeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface EmployeeLevelRepository extends JpaRepository<EmployeeLevel, Long> {
    Optional<EmployeeLevel> findByEmployeeLevelCode(String employeeLevelCode);

    @Query("select max(el.code) from EmployeeLevel el")
    Integer findMaxEmployeeLevelCodePosition();

    void deleteByEmployeeLevelCode(String employeeLevelCode);
}
