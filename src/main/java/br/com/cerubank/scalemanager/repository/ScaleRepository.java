package br.com.cerubank.scalemanager.repository;

import br.com.cerubank.scalemanager.model.Scale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScaleRepository extends JpaRepository<Scale, Long> {
    Optional<Scale> findByScaleCode(String scaleCode);

    void deleteScaleById(Long id);
}
