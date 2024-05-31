import * as React from "react";
import { Link } from 'react-router-dom';
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { DesktopDatePicker } from "@mui/x-date-pickers";
import { useState } from "react";
import dayjs from "dayjs";
import Stack from "@mui/material/Stack";
import { useNavigate } from "react-router-dom";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { TimePicker } from "@mui/x-date-pickers/TimePicker";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
import axios from "axios";
import { MobileDatePicker } from "@mui/x-date-pickers/MobileDatePicker";
import { style } from "@mui/system";
import { Navigate } from "react-router";
import { useEffect } from "react";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import { ReactSession } from "react-client-session";

function Copyright(props) {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      {...props}      
    >
      {"Copyright © "}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

const theme = createTheme();

export default function BookMeeting2() {
  // const RoomName = ReactSession.get("roomname");
  // console.log(RoomName)
  const navigate = useNavigate();
  const [auditoriumList, setAuditoriumList] = useState([]);
  const [auditorium, setAuditorium] = useState([]);
  const [auditoriumObj, setAuditoriumObj] = useState();
  const [auditoriumName, setAuditoriumName] = useState("");
  let [errorMsg, setErrorMsg] = useState("");
  const [message, setMessage] = useState("");
  let [booking, setBooking] = useState([]);
  useEffect(() => {
    // handleObj();
    // getAllAuditorium()
  }, []);
  const styles = {
    test: {
      width: "100%",
    },
  };

  const [checkUserName, setUserName] = useState("");
  const [checkBookingAgenda, setBookingAgenda] = useState("");
  const [checkTimeFrom, setTimeFrom] = useState();
  const [checkTimeTo, setTimeTo] = useState();

  const [timefromHH, setTimefromHH] = React.useState(0);
  const handletimefromHHChange = (event) => {
    setTimefromHH(event.target.value);
  };




  
  const [timetoHH, setTimetoHH] = React.useState(0);
  const handletimetoHHChange = (event) => {
    setTimetoHH(event.target.value);
  };

 

  const handleAuditorium = () => {
    setAuditoriumName(ReactSession.get("auditoriumName"));
  };
  const handleUserName = (e) => {
    setUserName(e.target.value);
  };
  const handleTimeFrom = (e) => {
    setTimeFrom(e.target.value);
  };
  const handleTimeTo = (e) => {
    setTimeTo(e.target.value);
  };
  const handleBookingAgenda = (e) => {
    setBookingAgenda(e.target.value);
  };

  const [dateFrom, setdateFrom] = React.useState(dayjs("2024-04-16T21:11:54"));

  const handleDateFrom = (newValue) => {

    axios
    .get(
      `http://localhost:0/admin/getAuditoriunByName/${ReactSession.get(
        "auditoriumName"
      )}`,
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    )
    .then((response) => {
      setAuditorium(response.data);
    })
    .catch((err) => console.log(err + "Incorrect Data"));

    setdateFrom(newValue);
  };
  const [dateTo, setdateTo] = React.useState(dayjs("2024-04-16T21:11:54"));

  const handleDateTo = (newValue) => {
    setdateTo(newValue);
  };

  useEffect(() => {
    getAudi();
    getBooking();
  }, []);



  const getAudi = () => {
    axios
      .get(
        `http://localhost:8080/admin/getAuditoriunByName/${ReactSession.get(
          "auditoriumName"
        )}`,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
      .then((response) => {
        setAuditorium(response.data);
      })
      .catch((err) => console.log(err + "Incorrect Data"));
      

  };


  const getBooking = () => {
  axios

      .get("http://localhost:8080/admins/getAllBookings")
      .then((response) => setBooking(response.data))

      .catch((error) => setErrorMsg("error "));

      console.log(booking)
  }
  // const handleSubmit = (event) => {
  //   event.preventDefault();
    
  //   let data = {
  //     aduitoriamId: auditorium,
  //     bookingDateFrom: dateFrom,
  //     bookingDateTo: dateTo,
  //     bookingTimeFrom: checkTimeFrom,
  //     bookingTimeTO: checkTimeTo,
  //     bookingAgenda: checkBookingAgenda,
  //     userId: JSON.parse(sessionStorage.getItem("userLogin")),
  //   };
  //   console.log(data.userId, "userId");
  //   console.log(data, "data");

  //   axios
  //     .post("http://localhost:8080/admins/addBooking", data, {
  //       headers: {
  //         "Content-Type": "application/json",
  //       },
  //     })
  //     .then((response) => {
  //       console.log(response.data);
  //       ReactSession.set("Booking", response.data);
  //       navigate("/auditorium-view");
  //     })
  //     .catch((err) => console.log(err + "Incorrect Data"));
  // };


  const handleSubmit = (event) => {
    event.preventDefault();
    
    let data = {
      aduitoriamId: auditorium,
      bookingDateFrom: dateFrom,
      bookingDateTo: dateTo,
      bookingTimeFrom: timefromHH,
      bookingTimeTO: timetoHH,
      bookingAgenda: checkBookingAgenda,
      userId: JSON.parse(sessionStorage.getItem("userLogin")),
    };
    console.log(data.userId, "userId");
    console.log(data, "data");






    // axios
    //   .post("http://localhost:8080/admins/addBookings", data, {
    //     headers: {
    //       "Content-Type": "application/json",
    //     },
    //   })
    //   .then((response) => {
    //     console.log(response.data);
    //     ReactSession.set("Booking", response.data);
    //     navigate("/auditorium-view");
    //   })
    //   .catch((err) => console.log(err + "Incorrect Data"));
  };

  return (
    <><div className="test" style={styles.test}>
      <ThemeProvider theme={theme}>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 10,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            {/* <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
      <LockOutlinedIcon />
    </Avatar> */}
            <Typography component="h1" variant="h5">
              Booking
            </Typography>
            <Box
              component="form"
              noValidate
              onSubmit={handleSubmit}
              sx={{ mt: 3 }}
            >
              <Grid container spacing={2}>
                <Grid item xs={12} sm={12}>
                  Auditorium Name
                  <TextField
                    onChange={handleAuditorium}
                    autoComplete="given-name"
                    name="Auditorium"
                    defaultValue={ReactSession.get("auditoriumName")}
                    required
                    fullWidth
                    id="Auditorium"
                    // label={ReactSession.get("auditoriumName")}
                    autoFocus
                    InputProps={{
                      readOnly: true,
                    }} />
                </Grid>{" "}
                <Grid item xs={12} sm={6}>
                  <LocalizationProvider dateAdapter={AdapterDayjs}>
                    Date From
                    <DesktopDatePicker
                      // label="Date From"
                      inputFormat="MM/DD/YYYY"
                      value={dateFrom}
                      onChange={handleDateFrom}
                      renderInput={(params) => <TextField {...params} />} />
                  </LocalizationProvider>
                </Grid>
                <Grid item xs={12} sm={6}>
                  <LocalizationProvider dateAdapter={AdapterDayjs}>
                    Date To
                    <DesktopDatePicker
                      // label="Date to"
                      inputFormat="MM/DD/YYYY"
                      value={dateTo}
                      onChange={handleDateTo}
                      renderInput={(params) => <TextField {...params} />} />
                  </LocalizationProvider>
                </Grid>
                <Grid item xs={6}>
                  <div> From</div>
                  <FormControl variant="standard" sx={{ m: 1, minWidth: 10 }}>
                    <InputLabel id="demo-simple-selefct-standard-label"></InputLabel>
                    <Select
                      labelId="demo-simple-select-standard-label"
                      id="demo-simple-select-standard"
                      value={timefromHH}
                      onChange={handletimefromHHChange}

                    >
                      <MenuItem value="">
                        <em>None</em>
                      </MenuItem>
                      <MenuItem value={9}> 09 AM</MenuItem>
                      <MenuItem value={10}>10 AM</MenuItem>
                      <MenuItem value={11}>11 AM</MenuItem>
                      <MenuItem value={12}>12 PM</MenuItem>
                      <MenuItem value={13}>01 PM</MenuItem>
                      <MenuItem value={14}>02 PM</MenuItem>
                      <MenuItem value={15}>03 PM</MenuItem>
                      <MenuItem value={16}>04 PM</MenuItem>
                      <MenuItem value={17}>05 PM</MenuItem>
                      <MenuItem value={18}>06 PM</MenuItem>
                    </Select>
                  </FormControl>

                  {/* <TextField
      onChange={handleTimeFrom}
      id="timefrom"
      // label="From"
      type="time"
      defaultValue={checkTimeFrom}
      InputLabelProps={{
        shrink: true,
      }}
      inputProps={{
        step: 300, // 5 min
        // min: '9:00',
        // max: '16:00',
      }}
      sx={{ width: 150 }}
    /> */}
                </Grid>
                <Grid item xs={6}>
                  <div> To</div>
                  {/* <TextField
      onChange={handleTimeTo}
      id="timeto"
      // label="To"
      type="time"
      step="1"
      defaultValue={checkTimeTo}
      InputLabelProps={{
        shrink: true,
      }}
      inputProps={{
        step: 300, // 5 min
      }}
      sx={{ width: 150 }}
    ></TextField> */}

                  <FormControl variant="standard" sx={{ m: 1, minWidth: 10 }}>
                    <InputLabel id="demo-simple-select-standard-label"></InputLabel>
                    <Select
                      labelId="demo-simple-select-standard-label"
                      id="demo-simple-select-standard"
                      value={timetoHH}
                      onChange={handletimetoHHChange}

                    >
                      <MenuItem value="">
                        <em>None</em>
                      </MenuItem>
                      <MenuItem value={9}> 09 AM</MenuItem>
                      <MenuItem value={10}>10 AM</MenuItem>
                      <MenuItem value={11}>11 AM</MenuItem>
                      <MenuItem value={12}>12 PM</MenuItem>
                      <MenuItem value={13}>01 PM</MenuItem>
                      <MenuItem value={14}>02 PM</MenuItem>
                      <MenuItem value={15}>03 PM</MenuItem>
                      <MenuItem value={16}>04 PM</MenuItem>
                      <MenuItem value={17}>05 PM</MenuItem>
                      <MenuItem value={18}>06 PM</MenuItem>
                    </Select>
                  </FormControl>

                </Grid>
                <Grid item xs={12}>
                  Agenda
                  <TextField
                    fullWidth
                    name="BookingAgenda"
                    // label="Booking Agenda"
                    onChange={handleBookingAgenda}
                    id="BookingAgenda" />
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Book Meeting
              </Button>
              <Grid container justifyContent="flex-end"></Grid>
            </Box>
          </Box>
          {/* <Copyright sx={{ mt: 5 }} /> */}
        </Container>
      </ThemeProvider>

    </div>
    <Button component={Link} to="/floortable" variant="contained" color="primary">
        Back
      </Button></>
    
  );
}
