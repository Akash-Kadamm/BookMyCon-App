import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Button,Table, TableContainer, TableHead, TableBody, TableRow, TableCell, Paper } from '@mui/material';
import Grid from '@mui/material/Grid';
import { createBrowserHistory } from 'history';
import ModalPieChart from "./ModalPieChart";

const UserFloorTable = () => {
  const [data, setData] = useState([]);
  
  const floorstyle = {
    marginLeft: 73,
    width: 1200, 
   
};

  const history = createBrowserHistory();

  const checkAvailability = (booking) => {
    const bookingDate = new Date(...booking.bookingDateFrom);
    const currentDate = new Date();
    return bookingDate.getDate() !== currentDate.getDate();
  };
 
  const handleBookClick = (booking) => {
    if (checkAvailability(booking)) {
        history.push('/booking');
    } else {
        alert(' unavailable'); 
    }
};

  useEffect(() => {
    fetch('http://localhost:8080/admins/getAllBookings')
      .then(response => response.json())
      .then(data => setData(data))
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  return (
   <div>
          <h1 className="text-center"> Floor List</h1>
          <TableContainer component={Paper}>
              <Table className='table table-striped' style={floorstyle}>
                  <TableHead>
                      <TableRow>
                          <TableCell>Name</TableCell>
                          <TableCell>Location</TableCell>
                          <TableCell>Type</TableCell>
                          <TableCell>Amenties</TableCell>
                          <TableCell>Capacity</TableCell>
                          <TableCell>Booking Agenda</TableCell>
                          <TableCell>Status</TableCell>
                      </TableRow>
                  </TableHead>
                  <TableBody>
                      {data.map(booking => (
                          <TableRow key={booking.bookingId}>
                              <TableCell>{booking.aduitoriamId.auditoriumName}</TableCell>
                              <TableCell>{booking.aduitoriamId.auditoriumLocation}</TableCell>
                              <TableCell>{booking.aduitoriamId.auditoriumType}</TableCell>
                              <TableCell>{booking.aduitoriamId.auditoriumAminity}</TableCell>
                              <TableCell>{booking.aduitoriamId.auditoriumCapacity}</TableCell>
                              <TableCell>{booking.bookingAgenda}</TableCell>
                              <TableCell>{checkAvailability(booking) ? (
                              <Button  component={Link} to="/auditoriumBooking" variant="contained" color="success" onClick={() => handleBookClick(booking)}> Book </Button> 
                               ) : ("Unavailable" )}</TableCell>

                          </TableRow>
                      ))}
                  </TableBody>
              </Table>
          </TableContainer>  
          <Grid item style={{ marginLeft: '57vh' }}>
         
         <ModalPieChart/>
       
        </Grid>
          <Grid container justifyContent="center" style={{ marginTop: '20px' }}> 
          <Grid item>
          <Button component={Link} to="/floormap" variant="contained" color="primary">
            Back
          </Button>
        </Grid>
        </Grid>
      </div>
      
    
  );
};

export default UserFloorTable;
