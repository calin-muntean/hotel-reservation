package org.calin.hotelreservationmanagement.Controller;


import org.calin.hotelreservationmanagement.Entity.Feedback;
import org.calin.hotelreservationmanagement.Entity.Reservation;
import org.calin.hotelreservationmanagement.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;


    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Map<String,String> payload){
        return new ResponseEntity<Feedback>(feedbackService.createFeedback(payload.get("feedbackBody"), Integer.parseInt(payload.get("hotelId"))), HttpStatus.OK);
    }
}
