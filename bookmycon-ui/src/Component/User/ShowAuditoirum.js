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
  Button,
  TextField
} from '@mui/material';
import Pagination from '@mui/material/Pagination';

const ShowAuditorium = () => {
  const [auditoriums, setAuditoriums] = useState([]);
  const [filteredAuditoriums, setFilteredAuditoriums] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage] = useState(5); 

  useEffect(() => {
    fetch('http://localhost:8080/admin/getAll')
      .then(response => response.json())
      .then(data => {
        setAuditoriums(data);
        setFilteredAuditoriums(data); 
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
        setLoading(false);
      });
  }, []);

 
  const handleSearchChange = (e) => {
    const query = e.target.value.toLowerCase();
    setSearchQuery(query);

    const filtered = auditoriums.filter(auditorium => 
      auditorium.auditoriumName.toLowerCase().includes(query) ||
      auditorium.auditoriumLocation.toLowerCase().includes(query) ||
      auditorium.auditoriumType.toLowerCase().includes(query)
    );

    setFilteredAuditoriums(filtered);
    setCurrentPage(1); 
  };

 
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentAuditoriums = filteredAuditoriums.slice(indexOfFirstItem, indexOfLastItem);

  
  const handlePageChange = (event, value) => {
    setCurrentPage(value);
  };

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
        {/* Search Box */}
        <TextField
          label="Search Auditorium"
          variant="outlined"
          fullWidth
          value={searchQuery}
          onChange={handleSearchChange}
          style={{ marginBottom: '20px' }}
        />
        
        <TableContainer
          component={Paper}
          style={{ marginTop: '20px', maxHeight: '400px', overflowY: 'auto' }} // Scrollbar
        >
          <Typography variant="h5" component="div" style={{ padding: '16px' }}>
            Auditorium Details
          </Typography>
          <Table stickyHeader>
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Name</TableCell>
                <TableCell>Location</TableCell>
                <TableCell>Capacity</TableCell>
                <TableCell>Type</TableCell>
                <TableCell>Amenities</TableCell>
                <TableCell>Status</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {currentAuditoriums.map(auditorium => (
                <TableRow
                  key={auditorium.auditoriumId}
                  hover
                  component={Link}
                  to={`/book-auditorium/${auditorium.auditoriumId}`} 
                  style={{ textDecoration: 'none' }} 
                >
                  <TableCell>{auditorium.auditoriumId}</TableCell>
                  <TableCell>{auditorium.auditoriumName}</TableCell>
                  <TableCell>{auditorium.auditoriumLocation}</TableCell>
                  <TableCell>{auditorium.auditoriumCapacity}</TableCell>
                  <TableCell>{auditorium.auditoriumType}</TableCell>
                  <TableCell>{auditorium.auditoriumAminity}</TableCell>
                  <TableCell>
                    {auditorium.booked ? (
                      'Unavailable'
                    ) : (
                      <Button
                        variant="contained"
                        color="success"
                        component={Link}
                        to={`/book-auditorium/${auditorium.auditoriumId}`}
                      >
                        Book
                      </Button>
                    )}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

       
        <div style={{ marginTop: '20px', display: 'flex', justifyContent: 'center' }}>
          <Pagination
            count={Math.ceil(filteredAuditoriums.length / itemsPerPage)} 
            page={currentPage}
            onChange={handlePageChange}
            color="primary"
          />
        </div>
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
