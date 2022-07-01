package br.com.cerubank.scalemanager.repository;

import br.com.cerubank.scalemanager.model.TypeScale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.OptionalInt;

public interface TypeScaleRepository extends JpaRepository<TypeScale, Long> {
    Optional<TypeScale> findByTypeScaleCode(String typeScaleCode);

    Optional<TypeScale> findByCode(Integer code);

    void deleteTypeScaleById(Long id);
}
