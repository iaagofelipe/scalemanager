package br.com.cerubank.scalemanager.service;

import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.Skill;
import br.com.cerubank.scalemanager.repository.SkillRepository;
import br.com.cerubank.scalemanager.request.NewSkillRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    ModelMapper mapper = new ModelMapper();

    public List<Skill> findAllSkills() {
        return skillRepository.findAll();
    }

    public void addSkill(NewSkillRequest request) {
        Skill newSkill = mapper.map(request, Skill.class);
        newSkill.setSkillCode(UUID.randomUUID().toString());
        skillRepository.save(newSkill);
    }

    public void updateSkill(NewSkillRequest request, String skillCode) {
        try {
            Skill skill = skillRepository.findBySkillCode(skillCode).orElse(null);

            if (skill != null) {
                skill.setCode(request.getCode());
                skill.setDescription(request.getDescription());
                skillRepository.save(skill);
            }

        } catch (Exception e) {
            throw new ModelNotFoundException("Skill not found " + e);
        }
    }

    @Transactional
    public void deleteSkill(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        if (skill.isEmpty()) {
            throw new ModelNotFoundException("Skill not found with id: " + id);
        } skillRepository.deleteSkillById(id);
    }


}
