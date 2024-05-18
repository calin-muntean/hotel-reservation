import React, { useEffect, useState } from "react";
import hotelsService from "../services/hotelService";

const useAvailableRooms = (hotelId) => {
    const [rooms, setRooms] = useState([]);
  
    useEffect(() => {
      const fetchData = async () => {
        const data = await hotelsService().getAvailableRooms(hotelId);
        if (!data.error) setRooms(data);
      };
  
      fetchData();
    }, [hotelId]);
  
    return { rooms };
  };
  
  export default useAvailableRooms;