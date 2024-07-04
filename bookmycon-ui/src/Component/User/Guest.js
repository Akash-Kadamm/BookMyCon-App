import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Avatar, Button, Typography, Box } from '@mui/material';
import fileDownload from 'js-file-download';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import AddIcon from '@mui/icons-material/Add';
import PassIcon from '@mui/icons-material/AssignmentTurnedIn';

const Guest = () => {
  const [guests, setGuests] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    getAllGuest();
  }, []);

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
    '&:last-child td, &:last-child th': {
      border: 0,
    },
  }));

  const addGuest = () => {
    navigate('/add-guest');
  };

  const getAllGuest = () => {
    axios
      .get('http://localhost:8080/guest/allGuest/' + JSON.parse(sessionStorage.getItem('userLogin')).userId)
      .then((response) => {
        setGuests(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const deleteGuest = (guestId) => {
    axios
      .delete('http://localhost:8080/guest/delete-guest/' + guestId)
      .then((response) => {
        setErrorMessage('');
        getAllGuest();
      })
      .catch((error) => {
        setErrorMessage(error.response.data);
      });
  };

  const updateGuest = () => {
    navigate('/guest-update');
  };

  const handleGuestPass = (guestName) => {
    axios({
      url: 'http://localhost:8080/guest/export-to-pass/' + guestName,
      method: 'GET',
      responseType: 'blob',
    })
      .then((response) => {
        fileDownload(response.data, 'GuestPass.pdf');
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', padding: 4 }}>
      <Typography variant="h3" sx={{ color: 'red', marginBottom: 2 }}>
        {errorMessage}
      </Typography>
      <Typography variant="h4" color="primary" sx={{ fontWeight: 'bold', marginBottom: 2 }}>
        Guest List
      </Typography>

      <Button
        className="m-2"
        onClick={addGuest}
        variant="contained"
        color="success"
        startIcon={<AddIcon />}
        sx={{ marginBottom: 2 }}
      >
        Add Guest
      </Button>

      <TableContainer component={Paper} sx={{ maxWidth: 900 }}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell align="right">#</StyledTableCell>
              <StyledTableCell>Guest Name</StyledTableCell>
              <StyledTableCell>Guest Email ID</StyledTableCell>
              <StyledTableCell align="left">Company Name</StyledTableCell>
              <StyledTableCell align="left">Mobile No.</StyledTableCell>
              <StyledTableCell align="left">Guest Images</StyledTableCell>
              <StyledTableCell align="center">Actions</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {guests.map((guest, index) => (
              <StyledTableRow key={guest.restId}>
                <StyledTableCell component="th" scope="row">
                  {index + 1}
                </StyledTableCell>
                <StyledTableCell>{guest.guestName}</StyledTableCell>
                <StyledTableCell align="left">{guest.guestEmail}</StyledTableCell>
                <StyledTableCell align="left">{guest.guestCompany}</StyledTableCell>
                <StyledTableCell align="left">{guest.guestMobileNo}</StyledTableCell>
                <StyledTableCell align="left">
                  <Avatar
                    alt="Guest"
                    variant="square"
                    sx={{ borderRadius: 3, width: 150, height: 90 }}
                    src={'http://localhost:8080/' + guest.thumbnail}
                  />
                </StyledTableCell>
                <StyledTableCell align="center">
                  <Button
                    color="info"
                    size="small"
                    variant="outlined"
                    sx={{ margin: 1 }}
                    startIcon={<PassIcon />}
                    onClick={() => handleGuestPass(guest.guestName)}
                  >
                    Pass
                  </Button>
                  <Button
                    color="error"
                    size="small"
                    sx={{ margin: 1 }}
                    variant="outlined"
                    startIcon={<DeleteIcon />}
                    onClick={() => deleteGuest(guest.guestId)}
                  >
                    Delete
                  </Button>
                  <Button
                    color="success"
                    size="small"
                    variant="contained"
                    sx={{ margin: 1 }}
                    startIcon={<EditIcon />}
                    onClick={updateGuest}
                  >
                    Update
                  </Button>
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default Guest;
