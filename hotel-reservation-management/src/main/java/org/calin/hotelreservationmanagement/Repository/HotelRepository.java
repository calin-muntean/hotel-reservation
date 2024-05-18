package org.calin.hotelreservationmanagement.Repository;

import org.calin.hotelreservationmanagement.*;
import org.calin.hotelreservationmanagement.Entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, Integer> {
    Hotel findHotelByHotelId(int hotelId);


}
