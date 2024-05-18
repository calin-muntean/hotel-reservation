import { useParams } from "react-router-dom";
import useAvailableRooms from "../hooks/useAvailableRooms"
import { getRoomType } from "../utils/getRoomType";
import bookingService from "../services/bookingService";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useState } from "react";

export const Hotels = () =>{
    const {hotelId, update} = useParams();
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const {rooms} = useAvailableRooms(hotelId);

    if(rooms.length === 0 ) return <p>There are no rooms available!</p>;

    return(
    <>
    {rooms.map((room, index)=>{
        const handleChange = () => {
            const queryParams = new URLSearchParams({
                hotelId: hotelId,
                roomNumber: room.roomNumber,
                userId: "1",
                reservationDate: `${startDate.toISOString().slice(0, 10)}T10:00:00`,
                checkOutDate:`${endDate.toISOString().slice(0, 10)}T10:00:00`
                });
    
                bookingService().bookRoom(queryParams.toString());
                window.location.reload()
        }

        const handleChangeRoom = (e) => {
            const queryParams = new URLSearchParams({
                newRoomNumber: e.target.value
              }); 
        
              bookingService().updateBooking(update, queryParams);
              window.location.reload();
        }

        return(
            <div key={index}>
                <p>Room Number: {room.roomNumber}</p>
                <p>Type: {getRoomType(room.type)}</p>
                <p>Price: {room.price}$</p>
                {update != null?
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full disabled:bg-blue-100" value={room.roomNumber} onClick={handleChangeRoom}>Change room</button>:
                <>
                <DatePicker selected={startDate} onChange={(date) => setStartDate(date)} />
                <DatePicker selected={endDate} onChange={(date) => setEndDate(date)} />
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full disabled:bg-blue-100" onClick={handleChange}>Book now</button>
                </>
                }
            </div>
        )
    })}
    </>
    )
}