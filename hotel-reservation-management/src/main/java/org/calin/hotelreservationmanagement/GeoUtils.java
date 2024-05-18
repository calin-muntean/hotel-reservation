package org.calin.hotelreservationmanagement;

public class GeoUtils {
    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final double DEG_TO_RAD = Math.PI / 180.0;

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double lat1Rad = lat1 * DEG_TO_RAD;
        double lon1Rad = lon1 * DEG_TO_RAD;
        double lat2Rad = lat2 * DEG_TO_RAD;
        double lon2Rad = lon2 * DEG_TO_RAD;


        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;


        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_KM * c;

        return distance;
    }

    public static double convertToMeters(double value) {

        return value * 111_000;
    }
}