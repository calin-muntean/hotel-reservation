const hotelsService = () => {
    const getHotels = async () => {
        return await fetch("http://localhost:8080/hotels").then(data=>data.json());
    };

    const getHotelsNearby = async (queryParams) => {
        return await fetch(`http://localhost:8080/hotels/nearby?${queryParams}`).then(data=>data.json());
    };

    const getHotel = async (hotelId) => {
        return await fetch(`http://localhost:8080/hotels/${hotelId}`).then(data=>data.json());
    }

    const getAvailableRooms = async (hotelId) => {
        return await fetch(`http://localhost:8080/hotels/${hotelId}/available-rooms`).then(data=>data.json());
    };
    
    return { getHotelsNearby, getAvailableRooms, getHotel, getHotels }
}

export default hotelsService;