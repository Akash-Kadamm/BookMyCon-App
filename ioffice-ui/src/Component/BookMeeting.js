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
  // const RoomName = ReactSession.get("roomname");
  // console.log(RoomName)
  const navigate = useNavigate();
  const [auditoriumList, setAuditoriumList] = useState([]);
  const [auditorium, setAuditorium] = useState();
  const [auditoriumObj, setAuditoriumObj] = useState();
  const [auditoriumName, setAuditoriumName] = useState("");
  let [errorMsg, setErrorMsg] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {
    // handleObj();
    // getAllAuditorium()
  }, []);

  // const getAllAuditorium=()=> {
  //       axios
  //       .get("http://localhost:8080/admin/getAll")
  //       .then(response => setAuditoriumList(response.data)).catch((error=>setErrorMsg("error occered ")));
  //   }
  // const audiId=0;
  // let data = []

  // data=auditoriumList;

  // const handleChange = (event) => {
  //   setAuditorium(event.target.value);
  // };

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

  //  let roomName=  ReactSession.get("roomname");
  //  let name=JSON.stringify(roomName)
  //   console.log(name )

  // const handleObj = (e) => {
  //   axios
  //   .get(`http://localhost:8080/admin/getAuditoriunByName/+${auditorium}`)
  //   .then(response => setAuditoriumObj(response.data)).catch((error=>setErrorMsg("error occered ")));
  // //  audiId=auditorium.auditoriumId;

  // console.log(auditoriumObj)
  // };

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

  const [dateFrom, setdateFrom] = React.useState(dayjs("2022-12-10T21:11:54"));

  const handleDateFrom = (newValue) => {
    setdateFrom(newValue);
  };
  const [dateTo, setdateTo] = React.useState(dayjs("2022-12-10T21:11:54"));

  const handleDateTo = (newValue) => {
    setdateTo(newValue);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
    .get(`http://localhost:8080/admin/getAuditoriunByName/${ReactSession.get("auditoriumName")}`, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      setAuditorium(response.data);
    })
    .catch((err) => console.log(err + "Incorrect Data"));

    let data = {
      aduitoriamId: auditorium,
      bookingDateFrom: dateFrom,
      bookingDateTo: dateTo,
      bookingTimeFrom: checkTimeFrom,
      bookingTimeTO: checkTimeTo,
      bookingAgenda: checkBookingAgenda,
      userId: JSON.parse(sessionStorage.getItem("userLogin")),
    };
    console.log(data.userId, "userId");
    console.log(data, "data");

    axios
      .post("http://localhost:8080/admins/addBooking", data, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response.data);
        navigate("/auditorium-view")
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
                    defaultValue={
                      "Auditorium Name:" + ReactSession.get("auditoriumName")
                    }
                    required
                    fullWidth
                    id="Auditorium"
                    // label={ReactSession.get("auditoriumName")}
                    autoFocus
                    InputProps={{
                      readOnly: true,
                    }}
                  />
                  {/* <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Auditorium Name</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
           value={auditoriumName}
          label="Auditorium Name"
          onChange={handleAuditorium}
        >
          {auditoriumList.map((auditoriumList) => (<MenuItem value={auditoriumList.auditoriumName}>{auditoriumList.auditoriumName}</MenuItem>))}
        </Select>
      </FormControl>
    </Box> */}
                </Grid>
                {/* <Grid item xs={12} sm={12}>
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
                </Grid> */}
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
                      // min: '9:00',
                      // max: '16:00',
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
          {/* <Copyright sx={{ mt: 5 }} /> */}
        </Container>
      </ThemeProvider>
    </div>
  );
}

// import * as React from "react";
// import Avatar from "@mui/material/Avatar";
// import Button from "@mui/material/Button";
// import CssBaseline from "@mui/material/CssBaseline";
// import TextField from "@mui/material/TextField";
// import FormControlLabel from "@mui/material/FormControlLabel";
// import Checkbox from "@mui/material/Checkbox";
// import Link from "@mui/material/Link";
// import Grid from "@mui/material/Grid";
// import Box from "@mui/material/Box";
// import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
// import Typography from "@mui/material/Typography";
// import Container from "@mui/material/Container";
// import { createTheme, ThemeProvider } from "@mui/material/styles";
// import { DesktopDatePicker } from "@mui/x-date-pickers";
// import { useState } from "react";
// import dayjs from "dayjs";
// import Stack from "@mui/material/Stack";
// import { useNavigate } from "react-router-dom";
// import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
// import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
// import { TimePicker } from "@mui/x-date-pickers/TimePicker";
// import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
// import axios from "axios";
// import { MobileDatePicker } from "@mui/x-date-pickers/MobileDatePicker";
// import { style } from "@mui/system";
// import { ReactSession }  from 'react-client-session';
// function Copyright(props) {
//   return (
//     <Typography
//       variant="body2"
//       color="text.secondary"
//       align="center"
//       {...props}
//     >
//       {"Copyright © "}
//       <Link color="inherit" href="https://mui.com/">
//         Your Website
//       </Link>{" "}
//       {new Date().getFullYear()}
//       {"."}
//     </Typography>
//   );
// }

// const theme = createTheme();

// export default function BookMeeting() {
//  let roomName=  ReactSession.get("roomname");
//   console.log(roomName )

//   const styles = {
//     test: {
//       backgroundColor: "#f1f1f1",
//       width: "100%",
//     },
//   };
//    const [auditoriumObj,setAuditoriumObj]=useState();
//   let[errorMsg,setErrorMsg]=useState('');

//   const [checkAuditorium, setAuditorium] = useState("");
//   const [checkUserName, setUserName] = useState("");
//   const [checkBookingAgenda, setBookingAgenda] = useState("");
//   const [checkTimeFrom, setTimeFrom] = useState();
//   const [checkTimeTo, setTimeTo] = useState();

//   const handleAuditorium = (e) => {
//     setAuditorium(e.target.value);
//   };
//   const handleUserName = (e) => {
//     setUserName(e.target.value);
//   };
//   const handleTimeFrom = (e) => {
//     setTimeFrom(e.target.value);
//   };
//   const handleTimeTo = (e) => {
//     setTimeTo(e.target.value);
//   };
//   const handleBookingAgenda = (e) => {
//     setBookingAgenda(e.target.value);
//   };

//   const [dateFrom, setdateFrom] = React.useState(dayjs("2022-10-12T21:11:54"));

//   const handleDateFrom = (newValue) => {
//     setdateFrom(newValue);
//   };
//   const [dateTo, setdateTo] = React.useState(dayjs("2022-10-12T21:11:54"));

//   const handleDateTo = (newValue) => {
//     setdateTo(newValue);
//   };
//   axios
//   .get(`http://localhost:8080/admin/getAuditoriunByName/${roomName}`)
//   .then(response => setAuditoriumObj(response.data)).catch((error=>setErrorMsg("error occered ")));
//   console.log(auditoriumObj )
//   const handleSubmit = (event) => {
//     event.preventDefault();
//     let data = {

//       bookingDateFrom: dateFrom,
//       bookingDateTo: dateTo,
//        bookingTimeFrom: checkTimeFrom,
//        bookingTimeTO: checkTimeTo,
//       bookingAgenda: checkBookingAgenda,
//     };
//     console.log(data);

//     axios
//       .post("http://localhost:8080/admins/addBooking", data, {
//         headers: {
//           "Content-Type": "application/json",
//         },
//       })
//       .then((response) => {
//         console.log(response.data);
//       })
//       .catch((err) => console.log(err + "Incorrect Data"));
//   };

//   return (
//     <div className="test" style={styles.test}>
//       <ThemeProvider theme={theme}>
//         <Container component="main" maxWidth="xs">
//           <CssBaseline />
//           <Box
//             sx={{
//               marginTop: 8,
//               display: "flex",
//               flexDirection: "column",
//               alignItems: "center",
//             }}
//           >
//             <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
//               <LockOutlinedIcon />
//             </Avatar>
//             <Typography component="h1" variant="h5">
//               Booking
//             </Typography>
//             <Box
//               component="form"
//               noValidate
//               onSubmit={handleSubmit}
//               sx={{ mt: 3 }}
//             >
//               <Grid container spacing={2}>
//                 <Grid item xs={12} sm={12}>
//                   <TextField
//                     onChange={handleAuditorium}
//                     autoComplete="given-name"
//                     name="Auditorium"
//                     required
//                     fullWidth
//                     id="Auditorium"
//                     label="Auditorium Name"
//                     autoFocus
//                   />
//                 </Grid>
//                 <Grid item xs={12} sm={12}>
//                   <TextField
//                     onChange={handleUserName}
//                     autoComplete="given-name"
//                     name="UserName"
//                     required
//                     fullWidth
//                     id="UserName"
//                     label="User Name"
//                     autoFocus
//                   />
//                 </Grid>
//                 <Grid item xs={12} sm={6}>
//                   <LocalizationProvider dateAdapter={AdapterDayjs}>
//                     <DesktopDatePicker
//                       label="Date From"
//                       inputFormat="MM/DD/YYYY"
//                       value={dateFrom}
//                       onChange={handleDateFrom}
//                       renderInput={(params) => <TextField {...params} />}
//                     />
//                   </LocalizationProvider>
//                 </Grid>
//                 <Grid item xs={12} sm={6}>
//                   <LocalizationProvider dateAdapter={AdapterDayjs}>
//                     <DesktopDatePicker
//                       label="Date to"
//                       inputFormat="MM/DD/YYYY"
//                       value={dateTo}
//                       onChange={handleDateTo}
//                       renderInput={(params) => <TextField {...params} />}
//                     />
//                   </LocalizationProvider>
//                 </Grid>

//                 <Grid item xs={6}>
//                   <TextField
//                     onChange={handleTimeFrom}
//                     id="timefrom"
//                     label="Time From"
//                     type="time"
//                     defaultValue={checkTimeFrom}
//                     InputLabelProps={{
//                       shrink: true,
//                     }}
//                     inputProps={{
//                       step: 300, // 5 min
//                     }}
//                     sx={{ width: 150 }}
//                   />
//                 </Grid>
//                 <Grid item xs={6}>
//                   <TextField
//                   onChange={handleTimeTo}
//                     id="timeto"
//                     label="Time to"
//                     type="time"
//                     step="1"
//                     defaultValue={checkTimeTo}
//                     InputLabelProps={{
//                       shrink: true,
//                     }}
//                     inputProps={{
//                       step: 300, // 5 min
//                     }}
//                     sx={{ width: 150 }}
//                   >
//                   {/* <input
//                     onChange={handleTimeTo}
//                     id="timeto"
//                     label="Time to"
//                     type="time"
//                     step="1"
//                     defaultValue={checkTimeTo}
//                     InputLabelProps={{
//                       shrink: true,
//                     }}
//                     inputProps={{
//                       step: 300, // 5 min
//                     }}
//                     sx={{ width: 150 }}
//                   ></input> */}
//                   </TextField>
//                 </Grid>
//                 <Grid item xs={12}>
//                   <TextField
//                     required
//                     fullWidth
//                     name="BookingAgenda"
//                     label="Booking Agenda"
//                     onChange={handleBookingAgenda}
//                     id="BookingAgenda"
//                   />
//                 </Grid>
//               </Grid>
//               <Button
//                 type="submit"
//                 fullWidth
//                 variant="contained"
//                 sx={{ mt: 3, mb: 2 }}
//               >
//                 Book Meeting
//               </Button>
//               <Grid container justifyContent="flex-end"></Grid>
//             </Box>
//           </Box>
//           <Copyright sx={{ mt: 5 }} />

//         </Container>
//       </ThemeProvider>
//     </div>
//   );
// }
