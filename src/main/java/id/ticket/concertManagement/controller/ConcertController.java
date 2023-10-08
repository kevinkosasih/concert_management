package id.ticket.concertManagement.controller;

import id.ticket.concertManagement.entity.Concert;
import id.ticket.concertManagement.models.ConcertModel;
import id.ticket.concertManagement.repository.ConcertRepository;
import id.ticket.concertManagement.responseException.NotFoundException;
import id.ticket.concertManagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ConcertController {

    @Autowired
    private ConcertRepository concertRepository;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<Concert> getConcertById(@PathVariable Long id) {
        Optional<Concert> concert = concertRepository.findById(id);
        if (concert.isPresent()) {
            return ResponseEntity.ok(concert.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Concert> createConcert(@RequestBody ConcertModel request) {
        // Validate request and create a concert
        Concert concert = createConcertLogic(request);
        return ResponseEntity.ok(concert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Concert> updateConcert(@PathVariable Long id, @RequestBody ConcertModel request) {
        // Validate request and update the concert with the given ID
        Concert updatedConcert = updateConcertLogic(id, request);
        return ResponseEntity.ok(updatedConcert);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConcert(@PathVariable Long id) {
        // Delete the concert with the given ID
        concertRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Add additional endpoints for retrieving all concerts, searching by artist, etc.

    private Concert createConcertLogic(ConcertModel request) {
        // Implement logic to create a concert and save it in the database
        Concert concert = new Concert();
        concert.setName(request.getName());
        concert.setDate(request.getDate());
        concert.setVenue(request.getVenue());
        concert.setArtist(request.getArtist());
        concert = concertRepository.save(concert);
        ticketService.generateTicket(request,concert);
        return concert;
    }

    private Concert updateConcertLogic(Long id, ConcertModel request) {
        // Implement logic to update an existing concert with the given ID
        // Retrieve the concert by ID, update its properties, and save it
        Optional<Concert> optionalConcert = concertRepository.findById(id);
        if (optionalConcert.isPresent()) {
            Concert concert = optionalConcert.get();
            concert.setName(request.getName());
            concert.setDate(request.getDate());
            concert.setVenue(request.getVenue());
            concert.setArtist(request.getArtist());
            return concertRepository.save(concert);
        } else {
            throw new NotFoundException("Concert not found with ID: " + id);
        }
    }
}
