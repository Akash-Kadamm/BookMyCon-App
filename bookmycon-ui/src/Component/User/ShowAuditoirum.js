import React, { useState, useEffect } from 'react';
import Grid from '@mui/material/Grid';
import { Link } from 'react-router-dom';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
  CircularProgress,
  Button
} from '@mui/material';

const ShowAuditorium = () => {
  const [auditoriums, setAuditoriums] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('http://localhost:8080/admin/getAll')
      .then(response => response.json())
      .then(data => {
        setAuditoriums(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <CircularProgress />
      </div>
    );
  }

  return (
    <div>
    <div style={{ padding: '20px' }}>
      <TableContainer component={Paper} style={{ marginTop: '20px' }}>
        <Typography variant="h5" component="div" style={{ padding: '16px' }}>
          Auditorium Details
        </Typography>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Location</TableCell>
              <TableCell>Capacity</TableCell>
              <TableCell>Type</TableCell>
              <TableCell>Amenities</TableCell>
              <TableCell>Booked</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {auditoriums.map(auditorium => (
              <TableRow key={auditorium.auditoriumId}>
                <TableCell>{auditorium.auditoriumId}</TableCell>
                <TableCell>{auditorium.auditoriumName}</TableCell>
                <TableCell>{auditorium.auditoriumLocation}</TableCell>
                <TableCell>{auditorium.auditoriumCapacity}</TableCell>
                <TableCell>{auditorium.auditoriumType}</TableCell>
                <TableCell>{auditorium.auditoriumAminity}</TableCell>
                <TableCell>{auditorium.booked ? 'Yes' : 'No'}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
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

export default ShowAuditorium;
