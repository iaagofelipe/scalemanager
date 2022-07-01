package br.com.cerubank.scalemanager.controller;

import br.com.cerubank.scalemanager.exception.ModelNotFoundException;
import br.com.cerubank.scalemanager.model.EventHour;
import br.com.cerubank.scalemanager.model.Skill;
import br.com.cerubank.scalemanager.request.NewEventHourRequest;
import br.com.cerubank.scalemanager.request.NewSkillRequest;
import br.com.cerubank.scalemanager.service.EventHourService;
import br.com.cerubank.scalemanager.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-hour")
public class EventHourController {
    @Autowired
    private EventHourService eventHourService;

    @GetMapping("/all")
    public ResponseEntity<List<EventHour>> getAllEventsHours() {
        List<EventHour> eventHours = eventHourService.findAllEvents();
        return ResponseEntity.ok(eventHours);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEventHour(@RequestBody NewEventHourRequest request) {
        eventHourService.addEventHour(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/update/{eventHourCode}")
    public ResponseEntity<?> updateEventHour(@RequestBody NewEventHourRequest request, @PathVariable("eventHourCode") String eventHourCode) {
        eventHourService.updateEventHour(request, eventHourCode);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEventHour(@PathVariable("id") Long id) {
        try {
            eventHourService.deleteEventHour(id);
            return ResponseEntity.ok().build();
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body("Event not found in database" + e);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
