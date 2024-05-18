package org.calin.hotelreservationmanagement.Controller;

import org.calin.hotelreservationmanagement.Entity.Hotel;
import org.calin.hotelreservationmanagement.Entity.Reservation;
import org.calin.hotelreservationmanagement.Repository.ReservationRepository;
import org.calin.hotelreservationmanagement.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
        return new ResponseEntity<List<Reservation>>(reservationService.allReservations(),HttpStatus.OK);
    }

    @PostMapping("/bookRoom")
    public String bookRoom(@RequestParam int hotelId,
                           @RequestParam int roomNumber,
                           @RequestParam int userId,
                           @RequestParam LocalDateTime reservationDate,
                           @RequestParam LocalDateTime checkOutDate)
    {return reservationService.bookRoom(hotelId, roomNumber, userId, reservationDate,checkOutDate);
    }

    @DeleteMapping("/cancelReservation/{reservationId}")
    public String cancelReservation(@PathVariable String reservationId ) {
        return reservationService.cancelReservation(reservationId);
    }
    @PutMapping("/changeRoom/{reservationId}")
    public String changeRoom(@PathVariable String reservationId,
                             @RequestParam int newRoomNumber) {
        return reservationService.changeRoom(reservationId, newRoomNumber);
    }

}
