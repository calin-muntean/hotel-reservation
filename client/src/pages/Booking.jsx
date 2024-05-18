import { Reservation } from "../component/Reservation";
import useBooking from "../hooks/useBooking"

export const Booking = () => {
    const {bookings} = useBooking();
    

    return(
        <div className="flex gap-2 pt-4">
        {bookings.map((book, index) => {
            return <div key={index}><Reservation book={book} /></div>
        })}
        </div>
    )
}