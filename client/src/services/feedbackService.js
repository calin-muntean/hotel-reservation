const feedbackService = () => {
    const createFeedback = async (hotelId, message) => {
        console.log(JSON.stringify({
            hotelId: hotelId,
            message: message
          }));
        return await fetch(
            "http://localhost:8080/feedbacks",
            {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
                hotelId: hotelId,
                feedbackBody: message
              })
            }
          ).then((data) => data.json());
    }

    return {createFeedback};
}

export default feedbackService;