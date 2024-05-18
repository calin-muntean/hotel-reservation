package org.calin.hotelreservationmanagement.Service;

import org.calin.hotelreservationmanagement.Entity.Hotel;
import org.calin.hotelreservationmanagement.Entity.Room;
import org.calin.hotelreservationmanagement.GeoUtils;
import org.calin.hotelreservationmanagement.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> allHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> singleHotel(int hotelId) {
        return Optional.ofNullable(hotelRepository.findHotelByHotelId(hotelId));
    }

    public List<Room> getAvailableRoomsByHotelId(int hotelId) {
        Hotel hotel = hotelRepository.findHotelByHotelId(hotelId);
        if (hotel != null) {
            return hotel.getRooms().stream()
                    .filter(Room::isAvailable)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<Hotel> findNearbyHotels(double userLatitude, double userLongitude, double radiusInKm) {
        List<Hotel> nearbyHotels = new ArrayList<>();
        List<Hotel> allHotels = hotelRepository.findAll();

        for (Hotel hotel : allHotels) {
            double hotelLatMeters = GeoUtils.convertToMeters(hotel.getLatitude());
            double hotelLonMeters = GeoUtils.convertToMeters(hotel.getLongitude());
            double userLatMeters = GeoUtils.convertToMeters(userLatitude);
            double userLonMeters = GeoUtils.convertToMeters(userLongitude);

            double distance = GeoUtils.calculateDistance(userLatMeters, userLonMeters, hotelLatMeters, hotelLonMeters);
            if (distance <= radiusInKm * 1000) {
                nearbyHotels.add(hotel);
            }
        }

        return nearbyHotels;
    }
//    public Optional<Room> getRoom(String id, int roomNumber) {
//        Optional<Hotel> optionalHotel = hotelRepository.findById(Integer.valueOf(id));
//        if (optionalHotel.isPresent()) {
//            Hotel hotel = optionalHotel.get();
//            return hotel.getRooms().stream()
//                    .filter(room -> room.getRoomNumber() == roomNumber)
//                    .findFirst();
//        }
//        return Optional.empty();
//    }

}


