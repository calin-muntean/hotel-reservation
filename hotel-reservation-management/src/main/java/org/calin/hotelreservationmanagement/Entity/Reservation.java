package org.calin.hotelreservationmanagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    private String id;
    private int hotelId;
    private int roomNumber;
    private String userId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private boolean cancelled;





    public Reservation(int hotelId, int roomNumber, String userId, LocalDateTime checkInTime,LocalDateTime checkOutTime) {
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.userId = userId;
        this.checkInTime = checkInTime;
        this.checkOutTime=checkOutTime;

    }
}
