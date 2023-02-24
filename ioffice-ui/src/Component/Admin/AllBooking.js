import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Button } from '@mui/material';
import fileDownload from 'js-file-download'

const AllBooking = () => {
  const [booking,setBooking] = useState([])

  useEffect(() => {
    getAllBookings()
  }, [])

  const getAllBookings = () => {
    axios.get('http://localhost:8080/admins/getAllBookings').then((response) => {
        setBooking(response.data)
    }).catch((error) => {
      console.log(error)
    })
  }

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

  // const getReportOfBooking = () => {
  //   axios({url:"http://localhost:8080/admins/export-to-pdf-book",method:"GET",responseType:"blob"}).then((response) => {
  //       fileDownload(response.data,'downlodedBooking.pdf')
  //       console.log(response)
  //   }).catch((error) => {
  //     console.log(error)
  //   })
  // }


  return (
  
    <div className="user">
    <h1> Booking Details </h1>
    {/* <Button
        className="m-2"
          onClick={() => {
            getReportOfBooking()
            }}
        variant="contained"
       color="success"
        >
        Report 
        </Button>  */}
    <hr />
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell align='center' >Booking ID</StyledTableCell>
            <StyledTableCell align='center'> Auditorium Name</StyledTableCell>
            <StyledTableCell align='center'>Auditorium Location</StyledTableCell>
            <StyledTableCell align='center'>Booking From</StyledTableCell>
            <StyledTableCell align='center'>Booking To</StyledTableCell>
            <StyledTableCell align='center'>Booking From</StyledTableCell>
            <StyledTableCell align='center'>Booking To</StyledTableCell>
            <StyledTableCell align='center'>Booking Agenda</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          { booking.map((book, index) =>(
            <StyledTableRow key={book.bookingId}>
              <StyledTableCell component="th" scope="row">
              {index + 1}
              </StyledTableCell>
              {/* <StyledTableCell align='center'>{b.bookingId}</StyledTableCell> */}
              <StyledTableCell align='center'>{book.aduitoriamId.auditoriumName}</StyledTableCell>
              <StyledTableCell align='center'>{book.aduitoriamId.auditoriumLocation}</StyledTableCell>
              <StyledTableCell align='center'>{book.bookingDateFrom}</StyledTableCell>
              <StyledTableCell align='center'>{book.bookingDateTo}</StyledTableCell>
              <StyledTableCell align='center'>{book.bookingTimeFrom}</StyledTableCell>
              <StyledTableCell align='center'>{book.bookingTimeTO}</StyledTableCell>
              <StyledTableCell align='center'>{book.bookingAgenda}</StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </div>
  )
}

export default AllBooking

