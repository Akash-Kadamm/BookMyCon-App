import * as React from "react";
import PropTypes from "prop-types";
import Box from "@mui/material/Box";
import Collapse from "@mui/material/Collapse";
import IconButton from "@mui/material/IconButton";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Typography from "@mui/material/Typography";
import Paper from "@mui/material/Paper";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import { styled } from "@mui/material/styles";
import { tableCellClasses } from "@mui/material/TableCell";
import Button from "@mui/material/Button";
import { useState, useEffect } from "react";
import axios from "axios";
import { ReactSession } from "react-client-session";
import { useNavigate } from "react-router";
import fileDownload from 'js-file-download'

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.black,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

function createData(name, calories, fat, carbs, protein, price) {
  return {
    name,
    calories,
    fat,
    carbs,
    protein,
    price,
    history: [
      {
        date: "2020-01-05",
        customerId: "11091700",
        amount: 3,
      },
      {
        date: "2020-01-02",
        customerId: "Anonymous",
        amount: 1,
      },
    ],
    history2: [
      {
        date: "2020-01-05",
      },
      {
        date: "2020-01-05",
      },
      {
        date: "2020-01-05",
      },
      {
        date: "2020-01-05",
      },
    ],
  };
}

function Row(props) {
  const { row } = props;
  const [openFlag1, setOpenFlag1] = React.useState(false);
  const [open, setOpen] = React.useState(false);

  const [book, setBook] = useState();

  const [flag1, setFlag1] = React.useState(false);
  const [flag2, setFlag2] = React.useState(false);
  const [flag3, setFlag3] = React.useState(false);
  const [flag4, setFlag4] = React.useState(false);
  const [flag5, setFlag5] = React.useState(false);
  const [flag6, setFlag6] = React.useState(false);
  const [flag7, setFlag7] = React.useState(false);
  const [flag8, setFlag8] = React.useState(false);
  const [flag9, setFlag9] = React.useState(false);

  const handleSplit = (t) => {
    const time = t.split(":");
    return time;
  };

  const handleCompare = (t1, t2) => {
    if (parseInt(t1[0]) > parseInt(t2[0])) {
      return 1;
    } else if (parseInt(t1[0]) < parseInt(t2[0])) {
      return -1;
    } else {
      if (parseInt(t1[1]) > parseInt(t2[1])) {
        return 1;
      } else if (parseInt(t1[1]) < parseInt(t2[1])) {
        return -1;
      } else {
        if (parseInt(t1[2]) > parseInt(t2[2])) {
          return 1;
        } else if (parseInt(t1[2]) < parseInt(t2[2])) {
          return -1;
        } else {
          return 0;
        }
      }
    }
  };

  const handleFlag1 = (t1, t2) => {
    // t1="10:10:10";
    // t2="10:10:10";
    // const time1 = handleSplit(t1)
    // const time2 = handleSplit(t2)

    // handleCompare(time1,time2 )
    // console.log("time1:"+ handleCompare(time1,time2 ));

    if (
      ((handleCompare(handleSplit(t1), handleSplit("09:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("09:00:00")) == 1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("10:00:00")) == 0 ||
        handleCompare(handleSplit(t2), handleSplit("10:00:00")) == -1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("09:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("09:00:00")) == 1
        ? true
        : false) &&
        (handleCompare(handleSplit(t1), handleSplit("10:00:00")) == -1
          ? true
          : false) &&
        (handleCompare(handleSplit(t2), handleSplit("10:00:00")) == 1
          ? true
          : false))
    ) {

      return true;
    }
    return false;
  };

  const handleFlag2 = (t1, t2) => {
    // t1="10:10:10";
    // t2="10:10:10";
    // const time1 = handleSplit(t1)
    // const time2 = handleSplit(t2)

    // handleCompare(time1,time2 )
    // console.log("time1:"+ handleCompare(time1,time2 ));

    if (
      ((handleCompare(handleSplit(t1), handleSplit("10:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("10:00:00")) == 1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("11:00:00")) == 0 ||
        handleCompare(handleSplit(t2), handleSplit("11:00:00")) == -1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("10:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("11:00:00")) == 0
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("10:00:00")) == 0
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("11:00:00")) == 1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("10:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("11:00:00")) == 1
          ? true
          : false))
    ) {
      return true;
    }
    return false;
  };

  const handleFlag3 = (t1, t2) => {
    // t1="10:10:10";
    // t2="10:10:10";
    // const time1 = handleSplit(t1)
    // const time2 = handleSplit(t2)

    // handleCompare(time1,time2 )
    // console.log("time1:"+ handleCompare(time1,time2 ));

    if (
      ((handleCompare(handleSplit(t1), handleSplit("11:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("11:00:00")) == 1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("12:00:00")) == 0 ||
        handleCompare(handleSplit(t2), handleSplit("12:00:00")) == -1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("11:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("12:00:00")) == 0
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("11:00:00")) == 0
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("12:00:00")) == 1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("11:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("12:00:00")) == 1
          ? true
          : false))
    ) {
      return true;
    }
    return false;
  };

  const handleFlag4 = (t1, t2) => {
    // t1="10:10:10";
    // t2="10:10:10";
    // const time1 = handleSplit(t1)
    // const time2 = handleSplit(t2)

    // handleCompare(time1,time2 )
    // console.log("time1:"+ handleCompare(time1,time2 ));

    if (
      ((handleCompare(handleSplit(t1), handleSplit("12:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("12:00:00")) == 1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("13:00:00")) == 0 ||
        handleCompare(handleSplit(t2), handleSplit("13:00:00")) == -1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("12:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("13:00:00")) == 0
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("12:00:00")) == 0
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("13:00:00")) == 1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("12:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("13:00:00")) == 1
          ? true
          : false))
    ) {
      return true;
    }
    return false;
  };

  const handleFlag5 = (t1, t2) => {
    // t1="10:10:10";
    // t2="10:10:10";
    // const time1 = handleSplit(t1)
    // const time2 = handleSplit(t2)

    // handleCompare(time1,time2 )
    // console.log("time1:"+ handleCompare(time1,time2 ));

    if (
      ((handleCompare(handleSplit(t1), handleSplit("13:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("13:00:00")) == 1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("14:00:00")) == 0 ||
        handleCompare(handleSplit(t2), handleSplit("14:00:00")) == -1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("13:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("14:00:00")) == 0
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("13:00:00")) == 0
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("14:00:00")) == 1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("13:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("14:00:00")) == 1
          ? true
          : false))
    ) {
      return true;
    }
    return false;
  };

  const handleFlag6 = (t1, t2) => {
    // t1="10:10:10";
    // t2="10:10:10";
    // const time1 = handleSplit(t1)
    // const time2 = handleSplit(t2)

    // handleCompare(time1,time2 )
    // console.log("time1:"+ handleCompare(time1,time2 ));

    if (
      ((handleCompare(handleSplit(t1), handleSplit("14:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("14:00:00")) == 1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("15:00:00")) == 0 ||
        handleCompare(handleSplit(t2), handleSplit("15:00:00")) == -1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("14:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("15:00:00")) == 0
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("14:00:00")) == 0
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("15:00:00")) == 1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("14:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("15:00:00")) == 1
          ? true
          : false))
    ) {
      return true;
    }
    return false;
  };

  const handleFlag7 = (t1, t2) => {
    // t1="10:10:10";
    // t2="10:10:10";
    // const time1 = handleSplit(t1)
    // const time2 = handleSplit(t2)

    // handleCompare(time1,time2 )
    // console.log("time1:"+ handleCompare(time1,time2 ));

    if (
      ((handleCompare(handleSplit(t1), handleSplit("15:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("15:00:00")) == 1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("16:00:00")) == 0 ||
        handleCompare(handleSplit(t2), handleSplit("16:00:00")) == -1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("15:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("16:00:00")) == 0
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("15:00:00")) == 0
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("16:00:00")) == 1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("15:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("16:00:00")) == 1
          ? true
          : false))
    ) {
      return true;
    }
    return false;
  };

  const handleFlag8 = (t1, t2) => {
    // t1="10:10:10";
    // t2="10:10:10";
    // const time1 = handleSplit(t1)
    // const time2 = handleSplit(t2)

    // handleCompare(time1,time2 )
    // console.log("time1:"+ handleCompare(time1,time2 ));

    if (
      ((handleCompare(handleSplit(t1), handleSplit("16:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("16:00:00")) == 1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("17:00:00")) == 0 ||
        handleCompare(handleSplit(t2), handleSplit("17:00:00")) == -1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("16:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("17:00:00")) == 0
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("16:00:00")) == 0
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("17:00:00")) == 1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t1), handleSplit("16:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("17:00:00")) == 1
          ? true
          : false))
    ) {
      return true;
    }
    return false;
  };

const navigate=useNavigate();

  const handleFood=(e)=>{
    ReactSession.set("BookingIdForFood",e)
    navigate('/product-List')
  }
  const handleHousekeeping=(e)=>{
    ReactSession.set("Housekeeping",e)
    navigate('/addHousekeeping')
  }

  const handleComplaint=()=>{
      navigate('/make-complaint')
  }

  const handleGuestPass=()=>{
    axios({url:"http://localhost:8080/user/export-to-user-pass/"+JSON.parse(sessionStorage.getItem("userLogin")).userEmail,method:"GET",responseType:"blob"}).then((response) => {
      fileDownload(response.data,'UserID.pdf')
      console.log(response)
  }).catch((error) => {
    console.log(error)
  })
  }

  const handleFlag9 = (t1, t2) => {
    // t1="10:10:10";
    // t2="10:10:10";
    // const time1 = handleSplit(t1)
    // const time2 = handleSplit(t2)

    // handleCompare(time1,time2 )
    // console.log("time1:"+ handleCompare(time1,time2 ));

    if (
      ((handleCompare(handleSplit(t1), handleSplit("17:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("17:00:00")) == 1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("18:00:00")) == 0 ||
        handleCompare(handleSplit(t2), handleSplit("18:00:00")) == -1
          ? true
          : false)) ||
      ((handleCompare(handleSplit(t2), handleSplit("18:00:00")) == 0 ||
      handleCompare(handleSplit(t1), handleSplit("18:00:00")) == -1
        ? true
        : false) &&
        (handleCompare(handleSplit(t2), handleSplit("17:00:00")) == 1
          ? true
          : false) &&
        (handleCompare(handleSplit(t1), handleSplit("17:00:00")) == -1
          ? true
          : false))
    ) {
      return true;
    }
    return false;
  };

  return (
    <React.Fragment>
      <TableRow sx={{ "& > *": { borderBottom: "unset" } }}>
        <TableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={() => setOpen(!open)}
          >
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>

        <TableCell component="th" scope="row">
          {row.bookingDateFrom}
        </TableCell>
        <TableCell component="th" scope="row">
          {row.aduitoriamId.auditoriumName}
        </TableCell>
        <StyledTableCell 
          style={{
            background: handleFlag1(row.bookingTimeFrom, row.bookingTimeTO)
              ? "gray"
              : "white",
          }}
        >
        {handleFlag1(row.bookingTimeFrom, row.bookingTimeTO)?"Booked":""}
        </StyledTableCell>
        <StyledTableCell
          style={{
            background: handleFlag2(row.bookingTimeFrom, row.bookingTimeTO)
              ? "gray"
              : "white",
          }}
        >
          {handleFlag2(row.bookingTimeFrom, row.bookingTimeTO)?"Booked":""}
        </StyledTableCell>
        <StyledTableCell
          style={{
            background: handleFlag3(row.bookingTimeFrom, row.bookingTimeTO)
              ? "gray"
              : "white",
          }}
        >
            {handleFlag3(row.bookingTimeFrom, row.bookingTimeTO)?"Booked":""}
        </StyledTableCell>
        <StyledTableCell
          style={{
            background: handleFlag4(row.bookingTimeFrom, row.bookingTimeTO)
              ? "gray"
              : "white",
          }}
        >
           { handleFlag4(row.bookingTimeFrom, row.bookingTimeTO)?"Booked":""}
        </StyledTableCell>
        <StyledTableCell
          style={{
            background: handleFlag5(row.bookingTimeFrom, row.bookingTimeTO)
              ? "gray"
              : "white",
          }}
        >
             { handleFlag5(row.bookingTimeFrom, row.bookingTimeTO)?"Booked":""}
        </StyledTableCell>
        <StyledTableCell
          style={{
            background: handleFlag6(row.bookingTimeFrom, row.bookingTimeTO)
              ? "gray"
              : "white",
          }}
        >
            { handleFlag6(row.bookingTimeFrom, row.bookingTimeTO)?"Booked":""}
        </StyledTableCell>
        <StyledTableCell
          style={{
            background: handleFlag7(row.bookingTimeFrom, row.bookingTimeTO)
              ? "gray"
              : "white",
          }}
        >   { handleFlag7(row.bookingTimeFrom, row.bookingTimeTO)?"Booked":""}</StyledTableCell>
        <StyledTableCell
          style={{
            background: handleFlag8(row.bookingTimeFrom, row.bookingTimeTO)
              ? "gray"
              : "white",
          }}
        >   { handleFlag8(row.bookingTimeFrom, row.bookingTimeTO)?"Booked":""}</StyledTableCell>
        <StyledTableCell
          style={{
            background: handleFlag9(row.bookingTimeFrom, row.bookingTimeTO)
              ? "gray"
              : "white",
          }}
        >
             {handleFlag9(row.bookingTimeFrom, row.bookingTimeTO)?"Booked":""}
        </StyledTableCell>
        
      </TableRow>

      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={12}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1 }}>
              <Typography variant="h6" gutterBottom component="div">
                Meeting Details
              </Typography>
              <Table size="small" aria-label="purchases">
                <TableHead>
                  <TableRow>
                
                    <TableCell>Date From</TableCell>
                    <TableCell>Date To</TableCell>
                    <TableCell>Time From</TableCell>
                    <TableCell>Time To</TableCell>
                    <TableCell>Agenda</TableCell>

                    <TableCell>
                      User Details
                      <IconButton
                        aria-label="expand row"
                        size="small"
                        onClick={() => setOpenFlag1(!openFlag1)}
                      >
                        {openFlag1 ? (
                          <KeyboardArrowUpIcon />
                        ) : (
                          <KeyboardArrowDownIcon />
                        )}
                      </IconButton>
                    </TableCell>
                    <TableCell>Action</TableCell>
                
                    {/* <TableCell align="right">Total price ($)</TableCell> */}
                  </TableRow>
                </TableHead>
                <TableBody>
                  {/* {row.map((historyRow) => ( */}
                  <TableRow key={row.bookingId}>
                 
                    <TableCell component="th" scope="row">
                      {row.bookingDateFrom}
                    </TableCell>
                    <TableCell>{row.bookingDateTo}</TableCell>
                    <TableCell>{row.bookingTimeFrom}</TableCell>
              
                    <TableCell>{row.bookingTimeTO}</TableCell>
                    <TableCell>{row.bookingAgenda}</TableCell>
                  
                                    
                    <TableCell>
                      <TableRow>
                        <TableCell
                          style={{ paddingBottom: 0, paddingTop: 0 }}
                          colSpan={12}
                        >
                          <Collapse in={openFlag1} timeout="auto" unmountOnExit>
                            <Box sx={{ margin: 1 }}>
                              <Table size="small" aria-label="purchases">
                                <TableHead>
                                  <TableRow>
                                    <TableCell>
                                      <Button
                                        color="warning"
                                        size="small"
                                        variant="contained"
                                      >
                                        Details
                                      </Button>
                                    </TableCell>
                                   
                                  </TableRow>
                                </TableHead>
                                <TableBody>
                                 
                                  <TableRow key={row.bookingId}>
                               <TableCell>{row.userId.userName}</TableCell>
                                  </TableRow>
                                  <TableRow key={row.bookingId}>
                                    <TableCell>
                                      {row.userId.userEmail}
                                    </TableCell>
                                  </TableRow>
                                  <TableRow key={row.bookingId}>
                                    <TableCell>
                                      {row.userId.userContact}
                                    </TableCell>
                                  </TableRow>

                                  {/* ))} */}
                                </TableBody>
                              </Table>
                            </Box>
                          </Collapse>
                        </TableCell>
                      </TableRow>
                    </TableCell>
                    <TableCell>
                  
                       <Button
                    onClick={()=>handleFood(row.bookingId)}
                                        color="warning"
                                        size="small"
                                        variant="contained"
                                      >
                                       Food
                                      </Button>
                    <br></br>
                    <br></br>
                    <Button
                    onClick={()=>handleComplaint()}
                                        color="error"
                                        size="small"
                                        variant="contained"
                                      >
                                         Complaint
                                      </Button> <br></br>
                                      <br></br>
                                         <Button
                                         onClick={()=>handleGuestPass()}
                                        color="success"
                                        size="small"
                                        variant="contained"
                                      >
                                         User Pass
                                      </Button>
                                      <br></br>
                                      <br></br>
                                      <Button
                    onClick={()=>handleHousekeeping(row.auditoriumName)}
                                        color="warning"
                                        size="small"
                                        variant="contained"
                                      >
                                       Housekeeping
                                      </Button>
                                      </TableCell>
                  </TableRow>
                  {/* ))} */}
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
}

let rows = [
  createData("10/02/2022", "Book", 6.0, 24, 4.0, 3.99),
  createData("10/02/2022", 237, 9.0, 37, 4.3, 4.99),
  createData("10/02/2022", 262, 16.0, 24, 6.0, 3.79),
  createData("10/02/2022", 305, 3.7, 67, 4.3, 2.5),
  createData("10/02/2022", 356, 16.0, 49, 3.9, 1.5),
];

export const ViewForUser = () => {
  let [errorMsg, setErrorMsg] = useState("");
  let [booking, setBooking] = useState([]);
  useEffect(() => {
    getAllBooking();
    //  handleAllAreas();
  }, []);

  const getAllBooking = () => {
    axios

      .get("http://localhost:8080/admins/getAllBookings/"+JSON.parse(sessionStorage.getItem("userLogin")).userId)
      .then((response) => setBooking(response.data))

      .catch((error) => setErrorMsg("error "));
  };
  rows = booking;

  return (
    <div
      style={
        {
          //    width: "90%", height: 490,  justifyContent:'center', alignItems:'center'
        }
      }
    >
      <TableContainer component={Paper}>
        <Table aria-label="collapsible table">
          <TableHead>
            <TableRow>
              <TableCell />
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  Date
                </Button>
              </TableCell>
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  Meeting Hall
                </Button>
              </TableCell>
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  9AM-10AM
                </Button>
              </TableCell>
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  10AM-11AM
                </Button>
              </TableCell>
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  11AM-12PM
                </Button>
              </TableCell>
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  12PM-1PM
                </Button>
              </TableCell>
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  1PM-2PM
                </Button>
              </TableCell>
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  2PM-3PM
                </Button>
              </TableCell>
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  3PM-4PM
                </Button>
              </TableCell>
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  4PM-5PM
                </Button>
              </TableCell>
              <TableCell>
                <Button color="secondary" size="small" variant="contained">
                  5PM-6PM
                </Button>
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <Row key={row.bookingId} row={row} />
              //   bookingId
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

