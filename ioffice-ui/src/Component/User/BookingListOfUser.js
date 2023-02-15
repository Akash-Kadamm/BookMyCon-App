import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useEffect, useState } from 'react'
import axios from 'axios'
import Button from '@mui/material/Button';
import DeleteIcon from "@mui/icons-material/Delete";
import CancelIcon from '@mui/icons-material/Cancel';
import UpdateIcon from '@mui/icons-material/Update';
import { toast } from 'react-toastify'
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';
import ConfirmDialog from './ConfirmDialog';


const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
      
    },
    [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
    },
  }));
  
  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
      border: 0,
    },
  }));
  
  

function BookingListOfUser() {

    let [booking,setBooking]=useState([]);
    const [user,setUser]=useState();
    let[errorMsg,setErrorMsg]=useState('');
    

    // setUser(sessionStorage.getItem("userLogin"));

    useEffect(()=>
    {
        getAllBookings()
    }, []);

   const id=2;

 const getAllBookings=()=> {
    console.log(id);
        axios
        .get(`http://localhost:8080/admins/getAllBookings/${id}`)
        .then(response =>{ setBooking(response.data);
                  console.log(response.date);
                  console.log(booking);
        })
        .catch((error=>setErrorMsg("error occered ")));
    }
   
    const cancelBooking = (bookId) => {
        
     
        console.log(bookId);
    
        const url = `http://localhost:8080/admins/${bookId}`;
        axios
          .delete(url)
          .then((response) => {
              toast.success(response.data);
            getAllBookings()
           

          })
          .catch((error) => console.log("error:"));
        
      };

  return (
    <>
       <div>
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            {/* <StyledTableCell>Offer Id</StyledTableCell> */}
            <StyledTableCell align='center' >Booking ID</StyledTableCell>
            <StyledTableCell align='center'> Auditorium Name</StyledTableCell>
            <StyledTableCell align='center'>Auditorium Location</StyledTableCell>
            <StyledTableCell align='center'>Booking From</StyledTableCell>
            <StyledTableCell align='center'>Booking To</StyledTableCell>
            <StyledTableCell align='center'>Booking From</StyledTableCell>
            <StyledTableCell align='center'>Booking To</StyledTableCell>
            <StyledTableCell align='center'>Booking Agenda</StyledTableCell>
            <StyledTableCell align='center'>Action</StyledTableCell>
      
          </TableRow>
        </TableHead>
        <TableBody>
          {booking.map((b) => (
            <StyledTableRow >
            <StyledTableCell align='center'>{b.bookingId}</StyledTableCell>
              <StyledTableCell align='center'>{b.aduitoriamId.auditoriumName}</StyledTableCell>
              <StyledTableCell align='center'>{b.aduitoriamId.auditoriumLocation}</StyledTableCell>
              <StyledTableCell align='center'>{b.bookingDateFrom}</StyledTableCell>
              <StyledTableCell align='center'>{b.bookingDateTo}</StyledTableCell>
              <StyledTableCell align='center'>{b.bookingTimeFrom}</StyledTableCell>
              <StyledTableCell align='center'>{b.bookingTimeTO}</StyledTableCell>
              <StyledTableCell align='center'>{b.bookingAgenda}</StyledTableCell>
              <StyledTableCell align="center">
                    <Button
                      className="m-2"
                    color='secondary'
                      variant="outlined"
                      startIcon={<UpdateIcon/>}
                      onClick={() =>{
                          
                    }}
                    
                    >
                      UPDATE
                    </Button>
                    {/* <ConfirmDialog props={b.bookingId}/> */}
                    <Button
                          color='error'
                      variant="outlined"
                      startIcon={<CancelIcon/>}
                      onClick={() =>{ 
                       cancelBooking(b.bookingId);
                      }}
                      
                    >
                  CANCEL
                    </Button>
                  </StyledTableCell>

            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </div> 
    </>
  )
}

export default BookingListOfUser