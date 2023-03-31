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





export const UserList = () => {
  let [user, setUser] = useState([]);
  let [setErrorMsg] = useState('');

  useEffect(() => {
    getAllUsers()
  }, []);


  const getAllUsers = () => {
    axios
      .get("http://localhost:8080/user/allUser")
      .then(response => setUser(response.data))
      .catch((error => setErrorMsg("error occered ")));
  }


  return (
    <>
      <div>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 700 }} aria-label="customized table">
            <TableHead>
              <TableRow>
                <StyledTableCell align='center' >User ID</StyledTableCell>
                <StyledTableCell align='center'> Name</StyledTableCell>
                <StyledTableCell align='center'>Email</StyledTableCell>
                <StyledTableCell align='center'>Role</StyledTableCell>
                <StyledTableCell align='center'>Contact</StyledTableCell>

              </TableRow>
            </TableHead>
            <TableBody>
              {user.map((user) => (
                <StyledTableRow key={user.userIdId}>
                  <StyledTableCell align='center'>{user.userId}</StyledTableCell>
                  <StyledTableCell align='center'>{user.userName}</StyledTableCell>
                  <StyledTableCell align='center'>{user.userEmail}</StyledTableCell>
                  <StyledTableCell align='center'>{user.userRole}</StyledTableCell>
                  <StyledTableCell align='center'>{user.userContact}</StyledTableCell>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    </>
  )
}
