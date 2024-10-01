import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Typography, CircularProgress, Paper, Button, TextField, Dialog, DialogActions, DialogContent, DialogTitle, Grid } from '@mui/material';
import { Link } from 'react-router-dom';

// Sample image URLs
const imageUrls = {
  1: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXsS41rFfjH3eLzJC4YwVE2pPWuCODxo13WA&s',
  2: 'https://5.imimg.com/data5/SELLER/Default/2022/11/NQ/GC/DR/116599768/conference-hall-interior-designing-service.jpg',
  3: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtdPu4PvVrLEieGKZR4TQKpktDJ_vC3Xg3Ew&s',
  4: 'https://5.imimg.com/data5/ZE/PC/XT/SELLER-19154162/auditorium-interior-works.jpg',
  5: 'https://www.mse.ac.in/wp-content/uploads/2021/05/Inauguration-of-Mini-Auditorium-03.jpg',
  575: 'https://media.licdn.com/dms/image/D4D12AQERtYaryaVxDw/article-cover_image-shrink_720_1280/0/1674806347697?e=2147483647&v=beta&t=ZtPqEug8GmgO_EgTBE3nMinic8AY3uT4-u6CsV17JzA',
};

const AuditoriumDetails = () => {
  const { auditoriumId } = useParams(); // Get the auditoriumId from the URL
  const navigate = useNavigate();
  const [auditorium, setAuditorium] = useState(null);
  const [loading, setLoading] = useState(true);
  const [isBookingDialogOpen, setBookingDialogOpen] = useState(false);
  const [bookingDate, setBookingDate] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    // Fetch specific auditorium details based on the auditoriumId
    fetch(`http://localhost:8080/admin/getAudi/${auditoriumId}`)
      .then(response => response.json())
      .then(data => {
        setAuditorium(data.Auditorium); // Accessing the "Auditorium" object inside the response
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching auditorium details:', error);
        setLoading(false);
      });
  }, [auditoriumId]);

  const handleBookClick = () => {
    const today = new Date().toISOString().split('T')[0]; // Get today's date in YYYY-MM-DD format
    if (auditorium && !auditorium.booked) {
      if (bookingDate === today) {
        // Handle booking for today
        bookAuditorium(today);
      } else {
        // Open booking dialog for other dates
        setBookingDialogOpen(true);
      }
    }
  };

  const bookAuditorium = (date) => {
    fetch(`http://localhost:8080/admin/bookAud/${auditoriumId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ date }),
    })
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          alert('Booking successful!');
          navigate('/showAudis'); // Navigate back to the list after booking
        } else {
          setError(data.message || 'Booking failed.');
        }
      })
      .catch(error => {
        console.error('Error booking auditorium:', error);
        setError('Error booking auditorium.');
      });
  };

  const handleDialogClose = () => {
    setBookingDialogOpen(false);
  };

  const handleDateChange = (e) => {
    setBookingDate(e.target.value);
  };

  const handleSubmitBooking = () => {
    const today = new Date().toISOString().split('T')[0];
    if (bookingDate && bookingDate !== today) {
      bookAuditorium(bookingDate);
      handleDialogClose();
    } else {
      setError('Please select a valid future date.');
    }
  };

  if (loading) {
    return (
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <CircularProgress />
      </div>
    );
  }

  if (!auditorium) {
    return <Typography variant="h5">No Auditorium Found</Typography>;
  }

  // Select an image URL based on auditoriumId
  const imageUrl = imageUrls[auditoriumId] || 'https://images.unsplash.com/photo-1506748686214e9df14b1b8a6d2e3';

  return (
    <div style={{ padding: '20px' }}>
      <Grid container spacing={2}>
        {/* Image Section */}
        <Grid item xs={12} md={4}>
          <img
            src={imageUrl}
            alt={auditorium.auditoriumName}
            style={{ width: '100%', borderRadius: '8px', objectFit: 'cover', height: '300px' }}
          />
        </Grid>

        {/* Details Section */}
        <Grid item xs={12} md={8}>
          <Paper style={{ padding: '20px' }}>
            <Typography variant="h4" gutterBottom>{auditorium.auditoriumName}</Typography>
            <Typography variant="body1"><strong>Location:</strong> {auditorium.auditoriumLocation}</Typography>
            <Typography variant="body1"><strong>Capacity:</strong> {auditorium.auditoriumCapacity}</Typography>
            <Typography variant="body1"><strong>Type:</strong> {auditorium.auditoriumType}</Typography>
            <Typography variant="body1"><strong>Amenities:</strong> {auditorium.auditoriumAminity}</Typography>
            <Typography variant="body1"><strong>Status:</strong> {auditorium.booked ? 'Unavailable' : 'Available'}</Typography>
            <Button
              variant="contained"
              color="primary"
              onClick={handleBookClick}
              disabled={auditorium.booked}
              style={{ marginTop: '20px' }}
            >
              {auditorium.booked ? 'Booked' : 'Book Auditorium'}
            </Button>
          </Paper>
        </Grid>
      </Grid>

      <div style={{ marginTop: '20px' }}>
        <Button component={Link} to="/showAudis" variant="contained" color="primary">
          Back to Auditoriums
        </Button>
      </div>

      <Dialog open={isBookingDialogOpen} onClose={handleDialogClose}>
        <DialogTitle>Book Auditorium</DialogTitle>
        <DialogContent>
          <TextField
            type="date"
            label="Booking Date"
            fullWidth
            value={bookingDate}
            onChange={handleDateChange}
            InputLabelProps={{ shrink: true }}
          />
          {error && <Typography color="error">{error}</Typography>}
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDialogClose}>Cancel</Button>
          <Button onClick={handleSubmitBooking} color="primary">Book</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default AuditoriumDetails;
