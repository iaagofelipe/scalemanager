package br.com.cerubank.scalemanager.repository;

import br.com.cerubank.scalemanager.model.EventHour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventHourRepository extends JpaRepository<EventHour, Long> {

    Optional<EventHour> findByEventHourCode(String eventHourCode);

    void deleteEventHourById(Long id);
}
