package org.calin.hotelreservationmanagement.Service;

import org.calin.hotelreservationmanagement.Entity.Hotel;
import org.calin.hotelreservationmanagement.Entity.Reservation;
import org.calin.hotelreservationmanagement.Entity.Room;
import org.calin.hotelreservationmanagement.Repository.HotelRepository;
import org.calin.hotelreservationmanagement.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Reservation> allReservations() {
        return reservationRepository.findAll();
    }

    public String bookRoom(int hotelId, int roomNumber, int userId, LocalDateTime checkInTime,LocalDateTime checkOutTime) {

        Query hotelQuery = new Query(Criteria.where("hotelId").is(hotelId));
        Hotel hotel = mongoTemplate.findOne(hotelQuery, Hotel.class);

        if (hotel != null) {

            for (Room room : hotel.getRooms()) {
                if (room.getRoomNumber() == roomNumber && room.isAvailable()) {

                    room.setAvailable(false);
                    Update update = new Update().set("rooms", hotel.getRooms());
                    mongoTemplate.updateFirst(hotelQuery, update, Hotel.class);


                    Reservation reservation = new Reservation();
                    reservation.setHotelId(hotelId);
                    reservation.setRoomNumber(roomNumber);
                    reservation.setUserId(String.valueOf(userId));
                    reservation.setCheckInTime(checkInTime);
                    reservation.setCheckOutTime(checkOutTime);

                    mongoTemplate.save(reservation);

                    return "Room booked successfully";
                }
            }
            return "Room is not available";
        }
        return "Hotel not found";
    }

    public String cancelReservation(String reservationId) {
        Optional<Reservation> optionalReservation = Optional.ofNullable(mongoTemplate.findById(reservationId, Reservation.class));
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            if (!reservation.isCancelled()) {

                reservation.setCancelled(true);
                mongoTemplate.save(reservation);
                Query query = new Query(Criteria.where("hotelId").is(reservation.getHotelId())
                        .and("rooms.roomNumber").is(reservation.getRoomNumber()));
                Update update = new Update().set("rooms.$.isAvailable", true); // Update existing field
                mongoTemplate.updateFirst(query, update, Hotel.class);
                return "Reservation canceled successfully";
            } else {
                return "Reservation is already canceled";
            }
        } else {
            return "Reservation not found";
        }
    }

    public String changeRoom(String reservationId, int newRoomNumber) {
        Optional<Reservation> optionalReservation = Optional.ofNullable(mongoTemplate.findById(reservationId, Reservation.class));
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            LocalDateTime checkInTime = reservation.getCheckInTime();
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("UTC"));


            if (currentDateTime.plusHours(2).isBefore(checkInTime)) {

                Query oldRoomQuery = new Query(Criteria.where("hotelId").is(reservation.getHotelId())
                        .and("rooms.roomNumber").is(reservation.getRoomNumber()));
                Update update = new Update().set("rooms.$.isAvailable", true);
                mongoTemplate.updateFirst(oldRoomQuery, update, Hotel.class);


                Query newRoomQuery = new Query(Criteria.where("hotelId").is(reservation.getHotelId())
                        .and("rooms.roomNumber").is(newRoomNumber));
                update = new Update().set("rooms.$.isAvailable", false);
                mongoTemplate.updateFirst(newRoomQuery, update, Hotel.class);


                reservation.setRoomNumber(newRoomNumber);
                mongoTemplate.save(reservation);
                return "Room changed successfully";
            } else {
                return "Cannot change room. It's less than two hours before check-in";
            }
        } else {
            return "Reservation not found";
        }
    }
}



