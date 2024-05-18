package org.calin.hotelreservationmanagement.Repository;

import org.calin.hotelreservationmanagement.*;
import org.bson.types.ObjectId;
import org.calin.hotelreservationmanagement.Entity.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<Feedback, ObjectId> {
}
