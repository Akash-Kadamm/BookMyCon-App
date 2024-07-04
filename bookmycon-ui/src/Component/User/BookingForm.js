import React, { useState } from 'react';
import axios from 'axios';

const BookingForm = ({ auditoriumId, userId }) => {
    const [bookingDateFrom, setBookingDateFrom] = useState('');
    const [bookingDateTo, setBookingDateTo] = useState('');
    const [bookingTimeFrom, setBookingTimeFrom] = useState('');
    const [bookingTimeTo, setBookingTimeTo] = useState('');
    const [bookingAgenda, setBookingAgenda] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const booking = {
            aduitoriamId: { id: auditoriumId },
            userId: { id: userId },
            bookingDateFrom,
            bookingDateTo,
            bookingTimeFrom,
            bookingTimeTO: bookingTimeTo,
            bookingAgenda
        };
        
        try {
            await axios.post('http://localhost:8080/admins/addBooking', booking);
            alert('Meeting booked successfully!');
        } catch (error) {
            console.error('There was an error booking the meeting!', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Booking Date From:
                <input type="date" value={bookingDateFrom} onChange={(e) => setBookingDateFrom(e.target.value)} required />
            </label>
            <br />
            <label>
                Booking Date To:
                <input type="date" value={bookingDateTo} onChange={(e) => setBookingDateTo(e.target.value)} required />
            </label>
            <br />
            <label>
                Booking Time From:
                <input type="time" value={bookingTimeFrom} onChange={(e) => setBookingTimeFrom(e.target.value)} required />
            </label>
            <br />
            <label>
                Booking Time To:
                <input type="time" value={bookingTimeTo} onChange={(e) => setBookingTimeTo(e.target.value)} required />
            </label>
            <br />
            <label>
                Booking Agenda:
                <textarea value={bookingAgenda} onChange={(e) => setBookingAgenda(e.target.value)} required />
            </label>
            <br />
            <button type="submit">Book Meeting</button>
        </form>
    );
};

export default BookingForm;
