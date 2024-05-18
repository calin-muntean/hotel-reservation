package org.calin.hotelreservationmanagement.Service;


import org.calin.hotelreservationmanagement.Entity.Feedback;
import org.calin.hotelreservationmanagement.Entity.Hotel;

import org.calin.hotelreservationmanagement.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Feedback createFeedback(String feedbackBody, int hotelId) {
        Feedback feedback = feedbackRepository.insert(new Feedback(feedbackBody));


        mongoTemplate.update(Hotel.class)
                .matching(Criteria.where("hotelId").is(hotelId))
                .apply(new Update().push("feedbackIds").value(feedback))
                .first();
        return feedback;
    }
}
