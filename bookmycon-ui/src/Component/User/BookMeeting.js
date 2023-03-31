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

export default function BookMeeting() {
  const navigate = useNavigate();
  const [auditoriumList, setAuditoriumList] = useState([]);
  const [auditorium, setAuditorium] = useState([]);
  const [auditoriumObj, setAuditoriumObj] = useState();
  const [auditoriumName, setAuditoriumName] = useState("");
  let [errorMsg, setErrorMsg] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {

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

  const [dateFrom, setdateFrom] = React.useState(dayjs("2023-02-20T21:11:54"));

  const handleDateFrom = (newValue) => {
    setdateFrom(newValue);
  };
  const [dateTo, setdateTo] = React.useState(dayjs("2023-02-20T21:11:54"));

  const handleDateTo = (newValue) => {
    setdateTo(newValue);
  };

  useEffect(() => {
    getAudi();
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

  const handleSubmit = (event) => {
    event.preventDefault();

    let data = {
      aduitoriamId: auditorium,
      bookingDateFrom: dateFrom,
      bookingDateTo: dateTo,
      bookingTimeFrom: checkTimeFrom,
      bookingTimeTO: checkTimeTo,
      bookingAgenda: checkBookingAgenda,
      userId: JSON.parse(sessionStorage.getItem("userLogin")),
    };


    axios
      .post("http://localhost:8080/admins/addBooking", data, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        ReactSession.set("Booking", response.data);
        navigate("/auditorium-view");
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
                    defaultValue={
                      ReactSession.get("auditoriumName")
                    }
                    required
                    fullWidth
                    id="Auditorium"
                    autoFocus
                    InputProps={{
                      readOnly: true,
                    }}
                  />
                </Grid>{" "}
                <Grid item xs={12} sm={6}>
                  <LocalizationProvider dateAdapter={AdapterDayjs}>
                    Date From
                    <DesktopDatePicker
                      inputFormat="MM/DD/YYYY"
                      value={dateFrom}
                      onChange={handleDateFrom}
                      renderInput={(params) => <TextField {...params} />}
                    />
                  </LocalizationProvider>
                </Grid>
                <Grid item xs={12} sm={6}>
                  <LocalizationProvider dateAdapter={AdapterDayjs}>
                    Date To
                    <DesktopDatePicker
                      inputFormat="MM/DD/YYYY"
                      value={dateTo}
                      onChange={handleDateTo}
                      renderInput={(params) => <TextField {...params} />}
                    />
                  </LocalizationProvider>
                </Grid>
                <Grid item xs={6}>
                  <div> From</div>
                  <TextField
                    onChange={handleTimeFrom}
                    id="timefrom"
                    type="time"
                    defaultValue={checkTimeFrom}
                    InputLabelProps={{
                      shrink: true,
                    }}
                    inputProps={{
                      step: 300
                    }}
                    sx={{ width: 150 }}
                  />
                </Grid>
                <Grid item xs={6}>
                  <div> To</div>
                  <TextField
                    onChange={handleTimeTo}
                    id="timeto"
                    type="time"
                    step="1"
                    defaultValue={checkTimeTo}
                    InputLabelProps={{
                      shrink: true,
                    }}
                    inputProps={{
                      step: 300,
                    }}
                    sx={{ width: 150 }}
                  ></TextField>
                </Grid>
                <Grid item xs={12}>
                  Agenda
                  <TextField
                    fullWidth
                    name="BookingAgenda"
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
        </Container>
      </ThemeProvider>
    </div>
  );
}
