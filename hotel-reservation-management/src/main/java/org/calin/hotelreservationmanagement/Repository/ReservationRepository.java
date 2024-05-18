package org.calin.hotelreservationmanagement.Repository;

import org.bson.types.ObjectId;
import org.calin.hotelreservationmanagement.Entity.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String>
{
        List<Reservation> findByHotelIdAndUserIdAndCancelledIsFalse(String hotelId, String userId);

}
