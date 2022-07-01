package br.com.cerubank.scalemanager.service;

import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.EventHour;
import br.com.cerubank.scalemanager.model.Skill;
import br.com.cerubank.scalemanager.repository.EventHourRepository;
import br.com.cerubank.scalemanager.request.NewEventHourRequest;
import br.com.cerubank.scalemanager.request.NewSkillRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventHourService {

    @Autowired
    private EventHourRepository eventHourRepository;

    ModelMapper mapper = new ModelMapper();

    public List<EventHour> findAllEvents() {
        return eventHourRepository.findAll();
    }

    public void addEventHour(NewEventHourRequest request) {
        EventHour newEventHour = mapper.map(request, EventHour.class);
        newEventHour.setEventHourCode(UUID.randomUUID().toString());
        eventHourRepository.save(newEventHour);
    }

    public void updateEventHour(NewEventHourRequest request, String eventHourCode) {
        try {
            EventHour eventHour = eventHourRepository.findByEventHourCode(eventHourCode).orElse(null);
            if (eventHour != null) {
                eventHour.setCredit(request.getCredit());
                eventHour.setDescription(request.getDescription());
                eventHourRepository.save(eventHour);
            }
        } catch (Exception e) {
            throw new ModelNotFoundException("Event not found " + e);
        }
    }

    @Transactional
    public void deleteEventHour(Long id) {
        Optional<EventHour> eventHour = eventHourRepository.findById(id);
        if (eventHour.isEmpty()) {
            throw new ModelNotFoundException("Event not found with id: " + id);
        } eventHourRepository.deleteEventHourById(id);
    }
}
