package br.com.cerubank.scalemanager.controller;


import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.Scale;
import br.com.cerubank.scalemanager.request.NewScaleRequest;
import br.com.cerubank.scalemanager.dto.ScaleDTO;
import br.com.cerubank.scalemanager.service.ScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scale")
public class ScaleController {

    @Autowired
    private ScaleService scaleService;

    @GetMapping("/all")
    public ResponseEntity<List<ScaleDTO>> getAllScales() {
        List<ScaleDTO> scaledto = scaleService.findAllScales();
        return ResponseEntity.ok(scaledto);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addScale(@RequestBody NewScaleRequest request) {
        scaleService.addScale(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/update/{scaleCode}")
    public ResponseEntity<?> updateScale(@RequestBody NewScaleRequest request, @PathVariable("scaleCode") String scaleCode) {
        scaleService.updateScale(request, scaleCode);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable("id") Long id) {
        try {
            scaleService.deleteScale(id);
            return ResponseEntity.ok().build();
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body("Scale not found in database" + e);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findScaleById(@PathVariable("id") Long id) {
        Optional<Scale> findScale = scaleService.findScaleByid(id);
        return ResponseEntity.ok(findScale);
    }
}
