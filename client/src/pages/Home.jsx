import { useEffect, useState } from "react";
import useHotelsNearby from "../hooks/useHotelsNearby";
import useHotels from "../hooks/useHotels";

export const Home = () => {
    const [radius, setRadius] = useState();
    const {hotels} = useHotelsNearby(radius);
    const { allHotels } = useHotels();
    const [hotelsData, setHotelsData] = useState([]);

    useEffect(()=>{
        setHotelsData(hotels);
    },[hotels])

    const handleClick = () => {
        setHotelsData(allHotels);
    }

    return (
    <div className="flex flex-col">
        <div className="flex justify-evenly">
        <a className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full disabled:bg-blue-100" href="/booking">My bookings</a>
        <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full disabled:bg-blue-100"  onClick={handleClick}>See all hotels</button>
        </div>
        <div className="flex justify-center">
          <input type="text" id="distance" value={radius} onChange={(e)=>setRadius(e.target.value)} className="w-52 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Distance" />
        </div>
        <div className="flex flex-col items-center pt-4">

      {hotelsData?hotelsData.map(hotel=>{
        return(
          <div key={hotel.id} className="flex justify-between w-72 m-4">
            <a href={`/hotel/${hotel.hotelId}`} >
          {hotel.name}
          </a>
          <a className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full disabled:bg-blue-100" href={`/hotel/${hotel.hotelId}/reviews`}>Reviews</a>
          </div>
        )
      }):<></>}
        </div>

    </div>
  )
}