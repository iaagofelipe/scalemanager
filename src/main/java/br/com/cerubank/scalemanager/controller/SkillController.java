package br.com.cerubank.scalemanager.controller;


import br.com.cerubank.scalemanager.dto.SkillDTO;
import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.Skill;
import br.com.cerubank.scalemanager.request.NewSkillRequest;
import br.com.cerubank.scalemanager.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/all")
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        List<SkillDTO> skills = skillService.findAllSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping("/find/{skillCode}")
    public ResponseEntity<SkillDTO> findBySkillCode(@PathVariable("skillCode") String skillCode) {
        SkillDTO skillIdentifier = skillService.findBySkillCode(skillCode);
        return new ResponseEntity<>(skillIdentifier, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSkill(@RequestBody NewSkillRequest request) {
        skillService.addSkill(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/update/{skillCode}")
    public ResponseEntity<?> updateSkill(@RequestBody NewSkillRequest request, @PathVariable("skillCode") String skillCode) {
        skillService.updateSkill(request, skillCode);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete/{skillCode}")
    public ResponseEntity<?> deleteSkill(@PathVariable("skillCode") String skillCode) {
        try {
            skillService.deleteSkill(skillCode);
            return ResponseEntity.ok().build();
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body("Skill not found in database" + e);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
