import * as React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { DesktopDatePicker } from "@mui/x-date-pickers";
import { useState } from "react";
import dayjs from "dayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import axios from "axios";

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

export default function BookMeeting() {
  const styles = {
    test: {
      backgroundColor: "#f1f1f1",
      width: "100%",
    },
  };

  const [checkAuditorium, setAuditorium] = useState("");
  const [checkUserName, setUserName] = useState("");
  const [checkBookingAgenda, setBookingAgenda] = useState("");
  const [checkTimeFrom, setTimeFrom] = useState();
  const [checkTimeTo, setTimeTo] = useState();

  const handleAuditorium = (e) => {
    setAuditorium(e.target.value);
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

  const [dateFrom, setdateFrom] = React.useState(dayjs(Date.now()));

  const handleDateFrom = (newValue) => {
    setdateFrom(newValue);
  };
  const [dateTo, setdateTo] = React.useState(dayjs(Date.now()));

  const handleDateTo = (newValue) => {
    setdateTo(newValue);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    let data = {
      bookingDateFrom: dateFrom,
      bookingDateTo: dateTo,
        bookingTimeFrom: checkTimeFrom,
        bookingTimeTO: checkTimeTo,
      bookingAgenda: checkBookingAgenda,
    };
    console.log(data);

    axios
      .post("http://localhost:8080/admins/addBooking", data, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response.data);
      })
      .catch((err) => console.log(err + "Incorrect Data"));
  };

  return (
    <div className="test" style={styles.test}>
      <ThemeProvider theme={theme}>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
              <LockOutlinedIcon />
            </Avatar>
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
                  <TextField
                    onChange={handleAuditorium}
                    autoComplete="given-name"
                    name="Auditorium"
                    required
                    fullWidth
                    id="Auditorium"
                    label="Auditorium Name"
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12} sm={12}>
                  <TextField
                    onChange={handleUserName}
                    autoComplete="given-name"
                    name="UserName"
                    required
                    fullWidth
                    id="UserName"
                    label="User Name"
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DesktopDatePicker
                      label="Date From"
                      inputFormat="MM/DD/YYYY"
                      value={dateFrom}
                      onChange={handleDateFrom}
                      renderInput={(params) => <TextField {...params} />}
                    />
                  </LocalizationProvider>
                </Grid>
                <Grid item xs={12} sm={6}>
                  <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DesktopDatePicker
                      label="Date to"
                      inputFormat="MM/DD/YYYY"
                      value={dateTo}
                      onChange={handleDateTo}
                      renderInput={(params) => <TextField {...params} />}
                    />
                  </LocalizationProvider>
                </Grid>

                <Grid item xs={6}>
                  <TextField
                    onChange={handleTimeFrom}
                    id="timefrom"
                    label="Time From"
                    type="time"
                    defaultValue={checkTimeFrom}
                    InputLabelProps={{
                      shrink: true,
                    }}
                    inputProps={{
                      step: 300, // 5 min
                    }}
                    sx={{ width: 150 }}
                  />
                </Grid>
                <Grid item xs={6}>
                  <TextField
                  onChange={handleTimeTo}
                    id="timeto"
                    label="Time to"
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
                  >
                  {/* <input
                    onChange={handleTimeTo}
                    id="timeto"
                    label="Time to"
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
                  ></input> */}
                  </TextField>
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    name="BookingAgenda"
                    label="Booking Agenda"
                    onChange={handleBookingAgenda}
                    id="BookingAgenda"
                  />
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
          <Copyright sx={{ mt: 5 }} />
          
        </Container>
      </ThemeProvider>
    </div>
  );
}
