import { useState, useEffect } from "react";
import hotelsService from "../services/hotelService";

const useHotelsNearby = (radius) =>{
    const [hotels, setHotels] = useState([]);
    const [latitude, setLatitude] = useState(0);
    const [longitude, setLongitude] = useState(0);

    useEffect(() => {
        const getLocation = () => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
             setLatitude(position.coords.latitude);
             setLongitude(position.coords.longitude);
         });
        }
      };
     getLocation();
     }, []);
     useEffect(() => {
        const fetchData = async () => {
          const queryParams = new URLSearchParams({
            latitude: latitude,
            longitude: longitude,
            radius: radius == "" ? "0" : radius,
          });
          
          const data = await hotelsService().getHotelsNearby(queryParams);
          if (!data.error) setHotels(data);
        };
    
        fetchData();
      }, [radius, latitude, longitude]);
    
      return { hotels };

}

export default useHotelsNearby;