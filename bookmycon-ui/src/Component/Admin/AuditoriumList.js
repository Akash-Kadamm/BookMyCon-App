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
import { useNavigate } from "react-router";
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
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

export const AuditoriumList = () => {
  const navigate = useNavigate();
  let [auditorium, setAuditorium] = useState([]);
  let [setErrorMsg] = useState("");

  useEffect(() => {
    getAllAuditorium();
  }, []);

  const addAuditorium = () => {
    navigate('/add-auditorium')
  }

  const getAllAuditorium = () => {
    axios
      .get("http://localhost:8080/admin/getAll")
      .then((response) => setAuditorium(response.data))
      .catch((error) => setErrorMsg("error occered "));
  };

  const editAuditorium = (id) => {
    navigate(`/auditorium-update/${id}`);
  };

  const deleteAudi = (id) => {
    const url = `http://localhost:8080/admin/${id}`;
    axios
      .delete(url)
      .then((response) => {
        getAllAuditorium();

      })
      .catch((error) => console.log("error:"));
  };

  const getReportOfAudi = () => {
    axios({ url: "http://localhost:8080/admin/export-to-pdf-audi", method: "GET", responseType: "blob" }).then((response) => {
      fileDownload(response.data, 'downlodedAudi.pdf')
      console.log(response)
    }).catch((error) => {
      console.log(error)
    })
  }

  return (
    <>
      <div>
        <h1> Auditorium Details </h1>
        <Button
          className="m-2"
          onClick={() => {
            addAuditorium();
          }}
          variant="contained"
          color="success"
        >
          Add
        </Button>

        <Button
          className="m-2"
          onClick={() => {
            getReportOfAudi();
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
                {/* <StyledTableCell>Offer Id</StyledTableCell> */}
                <StyledTableCell align="center">Auditorium ID</StyledTableCell>
                <StyledTableCell align="center">
                  Auditorium Name
                </StyledTableCell>
                <StyledTableCell align="center">
                  Auditorium Location
                </StyledTableCell>
                <StyledTableCell align="center">
                  Auditorium Capacity
                </StyledTableCell>
                <StyledTableCell align="center">
                  Auditorium Type
                </StyledTableCell>
                <StyledTableCell align="center">
                  Auditorium Amenities
                </StyledTableCell>
                <StyledTableCell align="center">Action</StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {auditorium.map((auditorium) => (

                <StyledTableRow key={auditorium.auditoriumId}>
                  <StyledTableCell align="center"> {auditorium.auditoriumId}
                  </StyledTableCell>
                  <StyledTableCell align="center">
                    {auditorium.auditoriumName}
                  </StyledTableCell>
                  <StyledTableCell align="center">
                    {auditorium.auditoriumLocation}
                  </StyledTableCell>
                  <StyledTableCell align="center">
                    {auditorium.auditoriumCapacity}
                  </StyledTableCell>
                  <StyledTableCell align="center">
                    {auditorium.auditoriumType}
                  </StyledTableCell>
                  <StyledTableCell align="center">
                    {auditorium.auditoriumAminity}
                  </StyledTableCell>
                  <StyledTableCell align="center">
                    <Button
                      className="m-2"
                      onClick={() => {
                        editAuditorium(auditorium.auditoriumId);
                      }}
                      variant="outlined"
                    >
                      UPDATE
                    </Button>
                    <Button
                      onClick={() => {
                        deleteAudi(auditorium.auditoriumId);
                      }}
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
    </>
  );
};
