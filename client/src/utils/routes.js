import { createBrowserRouter } from "react-router-dom";
import { Home } from "../pages/Home";
import { Hotels } from "../pages/Hotels";
import { Reviews } from "../pages/Reviews";
import { Booking } from "../pages/Booking";

export const router = createBrowserRouter([
    {
        path:"/",
        element: <Home />
},
{
    path: "/hotel/:hotelId",
    element: <Hotels />
},
{
    path: "/hotel/:hotelId/reviews",
    element: <Reviews />
},
{
    path: "/booking",
    element: <Booking />
},
{
    path: "/hotel/:hotelId/update/:oldRoomId",
    element: <Hotels />
},
])