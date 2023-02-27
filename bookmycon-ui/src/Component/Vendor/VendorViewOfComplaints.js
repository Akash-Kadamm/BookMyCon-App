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
import Button from '@mui/material/Button';
import { toast } from 'react-toastify'
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

const VendorViewOfComplaints = () => {
  const [complaints,setComplaints] = useState([])
  const [compalintType,setComplaintType]=useState("")

  useEffect(() => {
    getAllComplaints()
  }, [])

  const getAllComplaints = () => {
    axios.get('http://localhost:8080/complaint/getAllComplaints').then((response) => {
        setComplaints(response.data)
    }).catch((error) => {
      toast.error(error.response.data)
    })
  }

  const deleteComplaint =(Id) =>{
      const url=`http://localhost:8080/complaint/resolveComplaint/${Id}`;
      axios.delete(url)
      .then((response)=>{
        toast.success(response.data);
        getAllComplaints();
      }).catch((error)=>{
        toast.error(error.response.data)
      })
  }

  const deleteComplaintType=(Id)=>{
    const url=`http://localhost:8080/complaint/resolveComplaint/${Id}`;
    axios.delete(url)
    .then((response)=>{
      toast.success(response.data);
      getAllComplaintsType();
    }).catch((error)=>{
      toast.error(error.response.data)
    })
  }

  const getAllComplaintsType=(type)=>{
 
    let url2;
    if(type === "drinksAndSnacks"){
            url2="http://localhost:8080/complaint/getAllDrinksAndSnacksComplaints";
    }
    if(type ==="houseKeeping"){
            url2="http://localhost:8080/complaint/getAllHouseKeepingComplaints";
    }
    axios.get(url2)
    .then((response)=>{
        setComplaints(response.data)
    }).catch((error)=>{
      toast.error(error.response.data)
    })
  }
  const handleChange = (event) => {
    setComplaintType(event.target.value);
  };

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


  return (
    <div className="user">
    <h1 align="middle"> Complaint Details </h1>
    <TableContainer component={Paper}>
        <Table>
                <TableRow>
                <StyledTableCell>
                <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Select</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={compalintType}
          label="Age"
          onChange={handleChange}
        >
          <MenuItem value="drinksAndSnacks" >Drinks And Snacks</MenuItem>
          <MenuItem value="houseKeeping">House Keeping</MenuItem>
        </Select>
      </FormControl>
    </Box>
                     </StyledTableCell>
                     <StyledTableCell><Button
                      className="m-2"
                      color='success'
                      variant="outlined"
                      onClick={() =>{
                          getAllComplaintsType(compalintType)   
                    }}
                    >
                      Search
                    </Button></StyledTableCell>
                </TableRow>
            
        </Table>
    </TableContainer>

    <hr />
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell align='center' > Complaint ID</StyledTableCell>
            <StyledTableCell align='center' > Order ID</StyledTableCell>
            <StyledTableCell align='center'> Complaint Description</StyledTableCell>
            <StyledTableCell align='center'>Action</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          { complaints.map((complaint, index) =>(
            <StyledTableRow key={complaint.complaintId}>
              <StyledTableCell component="th" scope="row">
              {index + 1}
              </StyledTableCell>
              <StyledTableCell align='center'>{complaint.orderId}</StyledTableCell>
              <StyledTableCell align='center'>{complaint.description}</StyledTableCell>
              <StyledTableCell align='center'>
              <Button
                      className="m-2"
                      color='secondary'
                      variant="outlined"
                      onClick={() =>{
                          deleteComplaint(complaint.complaintId);
                    }}
                    >
                      Resolved
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

export default VendorViewOfComplaints

