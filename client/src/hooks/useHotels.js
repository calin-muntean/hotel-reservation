import { useEffect, useState } from "react";
import hotelsService from "../services/hotelService";

const useHotels = () => {
    const [allHotels, setAllHotels] = useState();
  
    useEffect(() => {
      const fetchData = async () => {
        const data = await hotelsService().getHotels();
        if (!data.error) setAllHotels(data);
      };
  
      fetchData();
    }, []);
  
    return { allHotels };
  };
  
  export default useHotels;