package br.com.cerubank.scalemanager.controller;

import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.TypeScale;
import br.com.cerubank.scalemanager.request.NewTypeScaleRequest;
import br.com.cerubank.scalemanager.service.TypeScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type-scale")
public class TypeScaleController {
    @Autowired
    private TypeScaleService typeScaleService;

    @GetMapping("/all")
    public ResponseEntity<List<TypeScale>> getAllTypeScale() {
        List<TypeScale> typeScales = typeScaleService.findAllTypeScale();
        return ResponseEntity.ok(typeScales);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTypeScale(@RequestBody NewTypeScaleRequest request) {
        typeScaleService.addTypeScale(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/update/{typeScaleCode}")
    public ResponseEntity<?> updateTypeScale(@RequestBody NewTypeScaleRequest request, @PathVariable("typeScaleCode") String typeScaleCode) {
        typeScaleService.updateTypeScale(request, typeScaleCode);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTypeScale(@PathVariable("id") Long id) {
        try {
            typeScaleService.deleteTypeScale(id);
            return ResponseEntity.ok().build();
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body("Skill not found in database" + e);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
