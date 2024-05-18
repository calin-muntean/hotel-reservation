package org.calin.hotelreservationmanagement.Entity;

import org.calin.hotelreservationmanagement.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedbacks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    private ObjectId id;
    private String body;

    public Feedback(String body) {
        this.body = body;
    }
}
