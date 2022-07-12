package br.com.cerubank.scalemanager.repository;

import br.com.cerubank.scalemanager.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findBySkillCode(String skillCode);

    void deleteBySkillCode(String skillCode);
}
