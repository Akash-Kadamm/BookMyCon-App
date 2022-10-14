import React from "react";
import axios from "axios";
import { useState } from "react";
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";

import "react-big-calendar/lib/css/react-big-calendar.css";
import { useEffect } from "react";
import { textAlign } from "@mui/system";

const localizer = momentLocalizer(moment);

const HomePage = () => {
  const userSignIn = JSON.parse(sessionStorage.getItem("userLogin"))

  useEffect(() => {
    data();
  }, []);

  const [selectedDateData, setSelectedDateData] = useState([]);
  // const [allBookingDateData, setallBookingDateData] = useState([]);
  // const [selectData, setSelectData] = useState([])
  
  const data = () => {
    if (userSignIn && userSignIn.userRole === "user") {
      axios.get("http://localhost:8080/admins/get-all-bookings/" + userSignIn.userId).then((response) => {
        setSelectedDateData(response.data);
      });
    } else if (userSignIn && userSignIn.userRole === "admin") {
      axios.get('http://localhost:8080/admins/get-all-bookings')
        .then(response =>
          setSelectedDateData(response.data)
        );
    } else {

    }
  };

  console.log(selectedDateData)
  return (
      <div>
        {/* {userSignIn && userSignIn.userRole === "user" && ( */}
        <Calendar
          localizer={localizer}
          events={selectedDateData.map((element) => {
            return {
              ...element,
              bookingDateTimeFrom: new Date(element.bookingDateTimeFrom),
              bookingDateTimeTo: new Date(element.bookingDateTimeTo),
            };
          })}
          startAccessor="bookingDateTimeFrom"
          endAccessor="bookingDateTimeTo"
          // style={{ height: 1000, width: 1000 }}
          titleAccessor="bookingAgenda"
          style={{ height: "100vh", textAlign: "left" }}
        />
      </div>
  );
};

export default HomePage;
