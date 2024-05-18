const bookingService = () => {
    const allBooking = async () => {
        return await fetch("http://localhost:8080/reservations").then(data=>data.json())
    }

    const bookRoom = async (queryParams) => {
        return await fetch(
            `http://localhost:8080/reservations/bookRoom?${queryParams}`,
            {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              }
            }
          ).then((data) => data.json());
    }

    const cancelBooking = async (id) => {
        return await fetch(`http://localhost:8080/reservations/cancelReservation/${id}`,{
                method: "DELETE",
                headers: {
                  "Content-Type": "application/json",
                }
        }).then(data=>data.json());
    }

    const updateBooking = async (id, queryParams) => {
        return await fetch(`http://localhost:8080/reservations/changeRoom/${id}?${queryParams}`,
    {
            method: "PUT",
                headers: {
                  "Content-Type": "application/json",
                }
    }).then(data=>data.json());
    }

    return {allBooking, bookRoom, cancelBooking, updateBooking}
}

export default bookingService;