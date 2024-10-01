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
import fileDownload from 'js-file-download';
import { toast } from "react-toastify";

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
  let [errorMsg, setErrorMsg] = useState("");

  useEffect(() => {
    getAllAuditorium();
  }, []);

  const addAuditorium = () => {
    navigate('/add-auditorium');
  };

  const getAllAuditorium = () => {
    axios
      .get("http://localhost:8080/admin/getAll")
      .then((response) => setAuditorium(response.data))
      .catch((error) => setErrorMsg("An error occurred while fetching auditoriums"));
  };

  const editAuditorium = (id) => {
    navigate(`/auditorium-update/${id}`);
  };

  const deleteAudi = (id) => {
    const url = `http://localhost:8080/admin/${id}`;
    axios
      .delete(url)
      .then(() => {
        toast.success("Auditorium Deleted Successfully");
        // Update the state to remove the deleted auditorium
        setAuditorium(auditorium.filter(audi => audi.auditoriumId !== id));
      })
      .catch((err) => {
        console.error("Error:", err);
        toast.error("Failed to delete Auditorium");
      });
  };

  const getReportOfAudi = () => {
    axios({
      url: "http://localhost:8080/admin/export-to-pdf-audi",
      method: "GET",
      responseType: "blob"
    })
      .then((response) => {
        fileDownload(response.data, 'downloadedAudi.pdf');
      })
      .catch((error) => {
        console.error("Error generating report:", error);
      });
  };

  return (
    <div>
      <h1>Auditorium Details</h1>
      <Button
        className="m-2"
        onClick={addAuditorium}
        variant="contained"
        color="success"
      >
        Add
      </Button>
      <Button
        className="m-2"
        onClick={getReportOfAudi}
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
              <StyledTableCell align="center">Auditorium ID</StyledTableCell>
              <StyledTableCell align="center">Auditorium Name</StyledTableCell>
              <StyledTableCell align="center">Auditorium Location</StyledTableCell>
              <StyledTableCell align="center">Auditorium Capacity</StyledTableCell>
              <StyledTableCell align="center">Auditorium Type</StyledTableCell>
              <StyledTableCell align="center">Auditorium Amenities</StyledTableCell>
              <StyledTableCell align="center">Action</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {auditorium.map((audi) => (
              <StyledTableRow key={audi.auditoriumId}>
                <StyledTableCell align="center">{audi.auditoriumId}</StyledTableCell>
                <StyledTableCell align="center">{audi.auditoriumName}</StyledTableCell>
                <StyledTableCell align="center">{audi.auditoriumLocation}</StyledTableCell>
                <StyledTableCell align="center">{audi.auditoriumCapacity}</StyledTableCell>
                <StyledTableCell align="center">{audi.auditoriumType}</StyledTableCell>
                <StyledTableCell align="center">{audi.auditoriumAminity}</StyledTableCell>
                <StyledTableCell align="center">
                  <Button
                    className="m-2"
                    onClick={() => editAuditorium(audi.auditoriumId)}
                    variant="outlined"
                  >
                    UPDATE
                  </Button>
                  <Button
                    onClick={() => deleteAudi(audi.auditoriumId)}
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
  );
};
