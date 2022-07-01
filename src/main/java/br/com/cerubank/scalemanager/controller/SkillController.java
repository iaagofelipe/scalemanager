package br.com.cerubank.scalemanager.controller;


import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.Skill;
import br.com.cerubank.scalemanager.request.NewSkillRequest;
import br.com.cerubank.scalemanager.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/all")
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.findAllSkills();
        return ResponseEntity.ok(skills);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable("id") Long id) {
        try {
            skillService.deleteSkill(id);
            return ResponseEntity.ok().build();
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body("Skill not found in database" + e);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
