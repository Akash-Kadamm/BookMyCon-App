import * as React from "react";
import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { useEffect, useState } from "react";
import axios from "axios";
import Button from "@mui/material/Button";
import DeleteIcon from "@mui/icons-material/Delete";
import {  useNavigate } from "react-router";
import fileDownload from 'js-file-download'

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
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

export const GetHousekeepingRequests = () => {
  const navigate = useNavigate();
  let [housekeeping, setHousekeeping] = useState([]);
  let [errorMsg, setErrorMsg] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {
    getAllHousekeepingRequest();
  }, []);

//   const addAuditorium = () =>{
//     navigate('/add-auditorium')
//   }

  const getAllHousekeepingRequest = () => {
    axios
      .get("http://localhost:8081/api/housekeeping")
      .then((response) => setHousekeeping(response.data))
      .catch((error) => setErrorMsg("error occered "));
  };

//   const editAuditorium = (id) => {
//     navigate(`/auditorium-update/${id}`);
//   };

  const deleteRequest = (id) => {
  
    console.log(id);

    const url = `http://localhost:8081/api/housekeeping/${id}`;
    axios
      .delete(url)
      .then((response) => {
        getAllHousekeepingRequest();
         console.log(response);

      })
      .catch((error) => console.log("error:"));
    // alert("deleted successfully");
  };

//   const getReportOfAudi = () => {
//     axios({url:"http://localhost:8080/admin/export-to-pdf-audi",method:"GET",responseType:"blob"}).then((response) => {
//         fileDownload(response.data,'downlodedAudi.pdf')
//         console.log(response)
//     }).catch((error) => {
//       console.log(error)
//     })
//   }

  return (
    <>
      <div>
      <h1> Housekeeping Request Details </h1>
      {/* <Button
        className="m-2"
          onClick={() => {
            addAuditorium();
            }}
        variant="contained"
       color="success"
        >
        Add
        </Button> */}

        {/* <Button
        className="m-2"
          onClick={() => {
            getReportOfAudi();
            }}
        variant="contained"
       color="success"
        >
        Report
        </Button> */}
        <hr />
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 700 }} aria-label="customized table">
            <TableHead>
              <TableRow>
               
                <StyledTableCell align="center">Housekeeping Req ID</StyledTableCell>
                <StyledTableCell align="center">
                  Auditorium Name
                </StyledTableCell>
                <StyledTableCell align="center">
                 Housekeeping Tasks
                </StyledTableCell>
               
                <StyledTableCell align="center">Action</StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {housekeeping.map((housekeeping) => (
                <StyledTableRow>
                  <StyledTableCell align="center">
                    {housekeeping.id}
                  </StyledTableCell>
                  <StyledTableCell align="center">
                    {housekeeping.auditoriumName}
                  </StyledTableCell>
                  <StyledTableCell align="center">
                    {housekeeping.housekeepingTasks}
                  </StyledTableCell>
                  
                    <Button
                      onClick={() => {
                        deleteRequest(housekeeping.id);
                      }}
                      variant="outlined"
                      color="error"
                      startIcon={<DeleteIcon />}
                    >
                      Delete
                    </Button>
                  

                  {/* */}
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    </>
  );
};
