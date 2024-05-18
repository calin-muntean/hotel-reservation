import { useParams } from "react-router-dom"
import useHotel from "../hooks/useHotel";
import { useState } from "react";
import feedbackService from "../services/feedbackService";

export const Reviews = () => {
    const {hotelId} = useParams();
    const {hotel} = useHotel(hotelId);
    const [message, setMessage] = useState("");

    if(hotel === undefined) return <></>

    const handleClick = () => {
        feedbackService().createFeedback(hotelId, message);
        window.location.reload()
    }

    return(<>
    {hotel.feedbackIds.map((feedback, index)=>{
        return(
            <div key={index}>
                {feedback.body}
            </div>
        )
    })}
    <textarea value={message} onChange={(e)=>setMessage(e.target.value)} />
    <button onClick={handleClick}>Submit</button>
    </>)
}