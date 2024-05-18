import { useEffect, useState } from "react";
import bookingService from "../services/bookingService";

const useBooking = (hotelId) => {
    const [bookings, setBookings] = useState([]);
  
    useEffect(() => {
      const fetchData = async () => {
        const data = await bookingService().allBooking();
        if (!data.error) setBookings(data);
      };
  
      fetchData();
    }, []);
  
    return { bookings };
  };
  
  export default useBooking;