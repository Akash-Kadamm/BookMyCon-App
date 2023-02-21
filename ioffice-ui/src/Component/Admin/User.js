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
import DeleteIcon from "@mui/icons-material/Delete";
import Button from "@mui/material/Button";
import fileDownload from 'js-file-download'

const User = () => {
  const [users, setUsers] = useState([])

  useEffect(() => {
    getAllUser()
  }, [])

  const getAllUser = () => {
    axios.get('http://localhost:8080/user/allUsers').then((response) => {
        setUsers(response.data)
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


  const getReportOfUser = () => {
    axios.get('http://localhost:8080/user/export-to-pdf').then((response) => {
        fileDownload(response.data,'user.pdf')
        // console.log(response.headers.Content-Disposition)
    }).catch((error) => {
      console.log(error)
    })
  }


  return (
  
    <div className="user">
    <h1> Users Details </h1>
    <Button
        className="m-2"
          onClick={() => {
            getReportOfUser()
            }}
        variant="contained"
       color="success"
        >
        Report
        </Button>
    <hr />
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>User Id</StyledTableCell>
            <StyledTableCell align="right">User Name</StyledTableCell>
            <StyledTableCell align="right">User Email Id</StyledTableCell>
            <StyledTableCell align="right">User Password</StyledTableCell>
            <StyledTableCell align="right">User Mobile No.</StyledTableCell>
            <StyledTableCell align="center">Action</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          { users.map((user, index) =>(
            <StyledTableRow key={user.userId}>
              <StyledTableCell component="th" scope="row">
              {index + 1}
              </StyledTableCell>
              <StyledTableCell align="right">{user.userName}</StyledTableCell>
              <StyledTableCell align="right">{user.userEmail}</StyledTableCell>
              <StyledTableCell align="right">{user.userPassword}</StyledTableCell>
              <StyledTableCell align="right">{user.userContact}</StyledTableCell>
              <StyledTableCell align="center">
                    <Button
                      variant="outlined"
                      color="error"
                      startIcon={<DeleteIcon />}
                    >
                      Delete
                    </Button>
                  </StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </div>
  )
}

export default User

