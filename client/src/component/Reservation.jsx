import useHotel from "../hooks/useHotel";
import bookingService from "../services/bookingService";

export const Reservation = ({book}) => {
    const {hotel} = useHotel(book.hotelId)

    return(
        <div className="flex flex-col w-48 gap-2 p-2 border-2">
        <p>{hotel? hotel.name: ""}</p>
        <p className="flex flex-col">Check in: <span>{book.checkInTime.slice(0, 10)}</span></p>
        <p className="flex flex-col">Check out: <span>{book.checkOutTime.slice(0, 10)}</span></p>
        <p>Room number: {book.roomNumber}</p>
        <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full disabled:bg-blue-100" disabled={book.cancelled} onClick={()=>{bookingService().cancelBooking(book.id);window.location.reload()}}>Cancel booking</button>
        <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full disabled:bg-blue-100" disabled={book.cancelled}><a href={`/hotel/${book.hotelId}/update/${book.id}`}>Update booking</a></button>
        </div>
    )
}