package br.com.cerubank.scalemanager.service;

import br.com.cerubank.scalemanager.dto.SkillDTO;
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
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    ModelMapper mapper = new ModelMapper();

    public List<SkillDTO> findAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        List<SkillDTO> dto = skills
                .stream()
                .map(user -> mapper.map(user, SkillDTO.class))
                .collect(Collectors.toList());
        return dto;
    }

    public SkillDTO findBySkillCode(String skillCode) {
        Skill skill = skillRepository.findBySkillCode(skillCode).get();
        return mapper.map(skill, SkillDTO.class);
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
                skill.setDescription(request.getDescription());
                skillRepository.save(skill);
            }

        } catch (Exception e) {
            throw new ModelNotFoundException("Skill not found " + e);
        }
    }

    @Transactional
    public void deleteSkill(String skillCode) {
        Optional<Skill> skill = skillRepository.findBySkillCode(skillCode);
        if (skill.isEmpty()) {
            throw new ModelNotFoundException("Skill not found with that identifier: " + skillCode);
        } skillRepository.deleteBySkillCode(skillCode);
    }


}
