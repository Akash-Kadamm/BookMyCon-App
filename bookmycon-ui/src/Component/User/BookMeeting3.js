import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {  Dialog, DialogTitle, DialogContent } from '@material-ui/core';

const BookMeeting3 = ({ open, handleClose }) => {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/bookings');
      setBookings(response.data);
    } catch (error) {
      console.error('Error fetching bookings:', error);
    }
  };

  const handleBooking = async (type) => {
    try {
      await axios.post(`http://localhost:8080/api/book/${type}`);
      fetchBookings();
    } catch (error) {
      console.error('Error booking:', error);
    }
  };

  const handleCancel = async (type) => {
    try {
      await axios.post(`http://localhost:8080/api/cancel/${type}`);
      fetchBookings(); 
    } catch (error) {
      console.error('Error canceling booking:', error);
    }
  };

  return (
    <div>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Booking Information</DialogTitle>
        <DialogContent>
          <div>
            <ul>
              {Array.isArray(bookings) && bookings.map((booking, index) => (
                <li key={index}>{booking}</li>
              ))}
            </ul>
            <button onClick={() => handleBooking('conferenceHall')}>Book Conference Hall</button>
            <button onClick={() => handleCancel('conferenceHall')}>Cancel Conference Hall Booking</button>
          
          </div>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default BookMeeting3;
