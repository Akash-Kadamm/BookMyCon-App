import React, { useState, useEffect } from 'react';
import { Table, TableContainer, TableHead, TableBody, TableRow, TableCell, Paper } from '@mui/material';
import { makeStyles } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  container: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
  },
  table: {
    width: '80%',
    borderCollapse: 'collapse',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
  },
  tableCell: {
    padding: '12px 15px',
    textAlign: 'left',
    backgroundColor: theme.palette.grey[200],
  },
  tableRow: {
    '&:nth-of-type(even)': {
      backgroundColor: theme.palette.grey[300], 
    },
    '&:hover': {
      backgroundColor: theme.palette.grey[400], 
    },
  },
}));

const BackendApiTable = () => {
  const classes = useStyles();
  const [data, setData] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/admins/getAllBookings')
      .then(response => response.json())
      .then(data => setData(data))
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  return (
    <div className={classes.container}>
      <TableContainer component={Paper}>
        <Table className={classes.table}>
          <TableHead>
            <TableRow>
              <TableCell className={classes.tableCell}>Booking ID</TableCell>
              <TableCell className={classes.tableCell}>User Name</TableCell>
              <TableCell className={classes.tableCell}>User Email</TableCell>
              <TableCell className={classes.tableCell}>Auditorium Name</TableCell>
              <TableCell className={classes.tableCell}>Booking Date From</TableCell>
              <TableCell className={classes.tableCell}>Booking Date To</TableCell>
              <TableCell className={classes.tableCell}>Booking Time From</TableCell>
              <TableCell className={classes.tableCell}>Booking Time To</TableCell>
              <TableCell className={classes.tableCell}>Booking Agenda</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {data.map(booking => (
              <TableRow key={booking.bookingId} className={classes.tableRow}>
                <TableCell className={classes.tableCell}>{booking.bookingId}</TableCell>
                <TableCell className={classes.tableCell}>{booking.userId.userName}</TableCell>
                <TableCell className={classes.tableCell}>{booking.userId.userEmail}</TableCell>
                <TableCell className={classes.tableCell}>{booking.aduitoriamId.auditoriumName}</TableCell>
                <TableCell className={classes.tableCell}>{new Date(...booking.bookingDateFrom).toLocaleDateString()}</TableCell>
                <TableCell className={classes.tableCell}>{new Date(...booking.bookingDateTo).toLocaleDateString()}</TableCell>
                <TableCell className={classes.tableCell}>{booking.bookingTimeFrom.join(':')}</TableCell>
                <TableCell className={classes.tableCell}>{booking.bookingTimeTO.join(':')}</TableCell>
                <TableCell className={classes.tableCell}>{booking.bookingAgenda}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default BackendApiTable;
