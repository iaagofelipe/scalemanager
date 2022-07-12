package br.com.cerubank.scalemanager.service;


import br.com.cerubank.scalemanager.dto.TypeScaleDTO;
import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.TypeScale;
import br.com.cerubank.scalemanager.repository.TypeScaleRepository;
import br.com.cerubank.scalemanager.request.NewTypeScaleRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TypeScaleService {

    @Autowired
    private TypeScaleRepository typeScaleRepository;

    ModelMapper mapper = new ModelMapper();

    public List<TypeScale> findAllTypeScale() {
        return typeScaleRepository.findAll();
    }

    public void addTypeScale(NewTypeScaleRequest request) {
        TypeScale newTypeScale = mapper.map(request, TypeScale.class);
        newTypeScale.setTypeScaleCode(UUID.randomUUID().toString());
        typeScaleRepository.save(newTypeScale);
    }

    public void updateTypeScale(NewTypeScaleRequest request, String typeScaleCode) {
        try {
            TypeScale updateTypeScale = typeScaleRepository.findByTypeScaleCode(typeScaleCode).orElse(null);

            if (updateTypeScale != null) {
                updateTypeScale.setCode(request.getCode());
                updateTypeScale.setDescription(request.getDescription());
                typeScaleRepository.save(updateTypeScale);
            }

        } catch (Exception e) {
            throw new ModelNotFoundException("Type not found " + e);
        }
    }

    @Transactional
    public void deleteTypeScale(String typeScaleCode) {
        Optional<TypeScale> typeScale = typeScaleRepository.findByTypeScaleCode(typeScaleCode);
        if (typeScale.isEmpty()) {
            throw new ModelNotFoundException("Type not found with id: " + typeScaleCode);
        } typeScaleRepository.deleteByTypeScaleCode(typeScaleCode);
    }

    public TypeScaleDTO findByTypeScaleCode(String typeScaleCode) {
        TypeScale ts = typeScaleRepository.findByTypeScaleCode(typeScaleCode).get();
        return mapper.map(ts, TypeScaleDTO.class);
    }
}
