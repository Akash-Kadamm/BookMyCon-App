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

  const[book,setBook]=useState();

  const[flag1,setFlag1]=React.useState(false);
  const[flag2,setFlag2]=React.useState(false);
  const[flag3,setFlag3]=React.useState(false);
  const[flag4,setFlag4]=React.useState(true);
  const[flag5,setFlag5]=React.useState(true);
  const[flag6,setFlag6]=React.useState(false);
  const[flag7,setFlag7]=React.useState(false);
  const[flag8,setFlag8]=React.useState(false);
  const[flag9,setFlag9]=React.useState(false);


  const hangleSplit=(t)=>{
    const time = t.split(':');
    return time;
  }

  const handleCompare=(t1,t2)=>
  {
    console.log("t1[0]:"+ parseInt(t1[0]))
    console.log("t2[0]:"+ parseInt(t2[0]))
    if(parseInt(t1[0])>parseInt(t2[0]))
    {
      return 1
    }
    else if(parseInt(t1[0])<parseInt(t2[0]))
    {
      return -1
    }
    else
    {
      if(parseInt(t1[1])>parseInt(t2[1]))
      {
        return 1
      }
      else if(parseInt(t1[1])<parseInt(t2[1]))
      {
        return -1
      }
      else
      {
        if(parseInt(t1[2])>parseInt(t2[2]))
      {
        return 1
      }
      else if(parseInt(t1[2])<parseInt(t2[2]))
      {
        return -1
      }
      else
      {
        return 0
      }

      }
    }
  }


  const hangleFlag1=(t1,t2)=>{
 
t1="10:10:10";
t2="10:10:10";
const time1 = hangleSplit(t1)
const time2 = hangleSplit(t2)

handleCompare(time1,time2 )
console.log("time1:"+ handleCompare(time1,time2 ));



  
    if(("09:00:00">t1<="10:00:00")||(t1>="09:00:00"&&t2>"10:00:00"))
    {
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
       
        <StyledTableCell  style={{ background:hangleFlag1(row.bookingTimeFrom,row.bookingTimeTO) ? 'gray': 'white'}}>{row.bookingId}</StyledTableCell>
        <StyledTableCell  style={{ background:flag2 ? 'gray': 'white'}}>{row.bookingId}</StyledTableCell>
        <StyledTableCell style={{ background:flag3 ? 'gray': 'white'}}>{row.bookingId}</StyledTableCell>
        <StyledTableCell style={{ background:flag4 ? 'gray': 'white'}}>{row.bookingId}</StyledTableCell>
        <StyledTableCell style={{ background:flag5 ? 'gray': 'white'}}>{row.bookingId}</StyledTableCell>
        <StyledTableCell style={{ background:flag6 ? 'gray': 'white'}}>{row.bookingId}</StyledTableCell>
        <StyledTableCell style={{ background:flag7 ? 'gray': 'white'}}></StyledTableCell>
        <StyledTableCell style={{ background:flag8 ? 'gray': 'white'}}></StyledTableCell>
        <StyledTableCell style={{ background:flag9 ? 'gray': 'white'}}>{row.bookingId}</StyledTableCell>
        {/* <StyledTableCell style={{ background:flag ? 'gray': 'white'}}>{row.bookingId}</StyledTableCell> */}
      
        {/* 
        <TableCell component="th" scope="row">
          {row.bookingDateFrom}
        </TableCell>
        <TableCell  >{row.bookingDateFrom}</TableCell>
        <TableCell >{row.bookingDateFrom}</TableCell>
        <TableCell >{row.bookingDateFrom}</TableCell>
        <TableCell >{row.bookingDateFrom}</TableCell>
        <TableCell >{row.bookingDateFrom}</TableCell>
        <TableCell >{row.bookingDateFrom}</TableCell>
        <TableCell >{row.bookingDateFrom}</TableCell>
        <TableCell >{row.bookingDateFrom}</TableCell>
        <TableCell >{row.bookingDateFrom}</TableCell>
        <TableCell >{row.bookingDateFrom}</TableCell>
         */}
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
                    <TableCell>User Details</TableCell>

                    <TableCell>
                      Coffee and Snacks
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
                      {/* <TableCell align="bookingId">
                        {Math.round(historyRow.amount * row.price * 100) / 100}
                      </TableCell> */}
                      <TableCell>{row.bookingTimeTO}</TableCell>
                      <TableCell>{row.bookingAgenda}</TableCell>
                      <TableCell>{row.userId.userName}</TableCell>

                      <TableCell>
                        <TableRow>
                          <TableCell
                            style={{ paddingBottom: 0, paddingTop: 0 }}
                            colSpan={12}
                          >
                            <Collapse
                              in={openFlag1}
                              timeout="auto"
                              unmountOnExit
                            >
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
                                          Menu
                                        </Button>
                                      </TableCell>
                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {/* {row.map((historyRow) => ( */}
                                      <TableRow key={row.bookingId}>
                                        <TableCell component="th" scope="row">
                                          {row.bookingId}
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

// Row.propTypes = {
//   row: PropTypes.shape({
//     calories: PropTypes.number.isRequired,
//     carbs: PropTypes.number.isRequired,
//     fat: PropTypes.number.isRequired,
//     history: PropTypes.arrayOf(
//       PropTypes.shape({
//         amount: PropTypes.number.isRequired,
//         customerId: PropTypes.string.isRequired,
//         date: PropTypes.string.isRequired,
//       })
//     ).isRequired,
//     name: PropTypes.string.isRequired,
//     price: PropTypes.number.isRequired,
//     protein: PropTypes.number.isRequired,
//   }).isRequired,
// };

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

      .get("http://localhost:8080/admins/getAllBookings")
      .then((response) => setBooking(response.data))

      .catch((error) => setErrorMsg("error "));
  };
  rows=booking;
  console.log("Booking Data:=- " + JSON.stringify(booking));

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
