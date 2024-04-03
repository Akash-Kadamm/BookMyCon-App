import React, { useState } from 'react';
import { TextField, Button, Container, Typography } from '@mui/material';
import axios from 'axios';

const BookingForm = () => {
    const [bookingData, setBookingData] = useState({
        auditoriumId: '',
        userId: '',
        bookingDateFrom: '',
        bookingDateTo: '',
        bookingTimeFrom: '',
        bookingTimeTo: '',
        bookingAgenda: ''
    });

    const handleInputChange = (e) => {
        setBookingData({ ...bookingData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/booking', bookingData);
            console.log(response.data);
            // Optionally, you can display a success message to the user
        } catch (error) {
            console.error('Error:', error);
            // Display an error message to the user
        }
    };

    return (
        <Container maxWidth="sm">
            <Typography variant="h4" gutterBottom>
                Book Auditorium
            </Typography>
            <form onSubmit={handleSubmit}>
                <TextField
                    label="Auditorium ID"
                    name="auditoriumId"
                    value={bookingData.auditoriumId}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin="normal"
                />
                <TextField
                    label="User ID"
                    name="userId"
                    value={bookingData.userId}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin="normal"
                />
                <TextField
                    label="Booking Date From"
                    type="date"
                    name="bookingDateFrom"
                    value={bookingData.bookingDateFrom}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin="normal"
                />
                {/* Add more fields for booking date to, booking time from, booking time to, booking agenda */}
                <Button type="submit" variant="contained" color="primary">
                    Book Auditorium
                </Button>
            </form>
        </Container>
    );
};

export default BookingForm;
