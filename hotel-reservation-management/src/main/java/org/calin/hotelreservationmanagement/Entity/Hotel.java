package org.calin.hotelreservationmanagement.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "hotels1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    private ObjectId id;
    private int hotelId;
    private String name;
    private double latitude;
    private double longitude;
    private List<Room> rooms;
    @DocumentReference
    private List<Feedback> feedbackIds;
}





