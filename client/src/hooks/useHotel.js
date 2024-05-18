import { useEffect, useState } from "react";
import hotelsService from "../services/hotelService";

const useHotel = (hotelId) => {
    const [hotel, setHotel] = useState();
  
    useEffect(() => {
      const fetchData = async () => {
        const data = await hotelsService().getHotel(hotelId);
        if (!data.error) setHotel(data);
      };
  
      fetchData();
    }, [hotelId]);
  
    return { hotel };
  };
  
  export default useHotel;