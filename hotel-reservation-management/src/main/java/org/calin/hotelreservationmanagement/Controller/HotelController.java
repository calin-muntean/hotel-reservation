package org.calin.hotelreservationmanagement.Controller;


import org.bson.types.ObjectId;
import org.calin.hotelreservationmanagement.Entity.Hotel;
import org.calin.hotelreservationmanagement.Entity.Room;
import org.calin.hotelreservationmanagement.GeoUtils;
import org.calin.hotelreservationmanagement.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return new ResponseEntity<List<Hotel>>(hotelService.allHotels(), HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Optional<Hotel>> getSingleHotel(@PathVariable int hotelId){
        return new ResponseEntity<Optional<Hotel>>(hotelService.singleHotel(hotelId),HttpStatus.OK);
    }

    @GetMapping("/{hotelId}/available-rooms")
    public List<Room> getAvailableRoomsByHotelId(@PathVariable int hotelId) {
        return hotelService.getAvailableRoomsByHotelId(hotelId);
    }

    @GetMapping("/nearby")
    public List<Hotel> findNearbyHotels(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("radius") double radiusInKm) {

        double userLatMeters = GeoUtils.convertToMeters(latitude);
        double userLonMeters = GeoUtils.convertToMeters(longitude);


        return hotelService.findNearbyHotels(userLatMeters, userLonMeters, radiusInKm);
    }


}
