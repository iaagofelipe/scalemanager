package br.com.cerubank.scalemanager.service;

import br.com.cerubank.scalemanager.dto.ScaleDTO;
import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.Employee;
import br.com.cerubank.scalemanager.model.EmployeeScale;
import br.com.cerubank.scalemanager.model.Scale;
import br.com.cerubank.scalemanager.model.TypeScale;
import br.com.cerubank.scalemanager.repository.EmployeeRepository;
import br.com.cerubank.scalemanager.repository.EmployeeScaleRepository;
import br.com.cerubank.scalemanager.repository.ScaleRepository;
import br.com.cerubank.scalemanager.repository.TypeScaleRepository;
import br.com.cerubank.scalemanager.request.NewScaleRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ScaleService {

    @Autowired
    private ScaleRepository scaleRepository;
    @Autowired
    private TypeScaleRepository typeScaleRepository;
    @Autowired
    private EmployeeScaleRepository employeeScaleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    ModelMapper mapper = new ModelMapper();


    public List<ScaleDTO> findAllScales() {
        List<Scale> scaleList = scaleRepository.findAll();
        List<ScaleDTO> dto = scaleList
                .stream()
                .map(scale -> mapper.map(scale, ScaleDTO.class))
                .collect(Collectors.toList());
        return dto;
    }

    public void addScale(NewScaleRequest request) {
        Scale newScale = mapper.map(request, Scale.class);
        newScale.setScaleCode(UUID.randomUUID().toString());
        Optional<TypeScale> type = typeScaleRepository.findByCode(request.getType());
        newScale.setTypeScale(type.get());
        Scale savedScale = scaleRepository.save(newScale);

        for (String employeeIdentifier : request.getEmployees()) {
            Optional<Employee> employee = employeeRepository.findByEmployeeIdentifier(employeeIdentifier);
            EmployeeScale saveEmployeeScale = new EmployeeScale(savedScale, employee.get());
            employeeScaleRepository.save(saveEmployeeScale);
        }
    }

    public void updateScale(NewScaleRequest request, String scaleCode) {
        try {
            Scale scale = scaleRepository.findByScaleCode(scaleCode).orElse(null);
            if (scale != null) {
                Optional<TypeScale> type = typeScaleRepository.findByCode(request.getType());
                scale.setScaleDate(request.getDate());
                type.ifPresent(scale::setTypeScale);
                scale.setDescription(request.getDescription());
                Scale savedScale = scaleRepository.save(scale);

                for (String employeeCode : request.getEmployees()) {
                    Optional<Employee> employee = employeeRepository.findByEmployeeIdentifier(employeeCode);
                    EmployeeScale updateEmployeeScale = new EmployeeScale(savedScale, employee.get());
                    employeeScaleRepository.save(updateEmployeeScale);
                }
            }

        } catch (Exception e) {
            throw new ModelNotFoundException("Scale not found " + e);
        }
    }

    @Transactional
    public void deleteScale(Long id) {
        Optional<Scale> scale = scaleRepository.findById(id);
        if (scale.isEmpty()) {
            throw new ModelNotFoundException("Scale not found with id: " + id);
        } scaleRepository.deleteScaleById(id);
    }

    public Optional<Scale> findScaleByid(Long id) {
        return scaleRepository.findById(id);
    }


}
