import * as React from "react";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useState } from "react";
import FormControlLabel from "@mui/material/FormControlLabel";
import SendIcon from "@mui/icons-material/Send";
import axios from "axios";
import { FormGroup, Icon } from "@mui/material";
import { useNavigate } from 'react-router-dom';
import { toast } from "react-toastify";

const theme = createTheme();

export default function AddAudi() {
  const [auditoriumName, SetAuditoriumName] = useState("");
  const [auditoriumLocation, SetAuditoriumLocation] = useState("");
  const [auditoriumCapacity, SetAuditoriumCapacity] = useState("");
  const [auditoriumType, SetAuditoriumType] = useState("");
  const [auditoriumAminity, SetAuditoriumAminity] = useState("");
  const [auditoryAm, setAuditoryAm]=useState([])

  const getAminity = (e) => {
    let data = auditoryAm;
    data.push(e.target.value);
    setAuditoryAm(data);
    SetAuditoriumAminity(auditoryAm.toString())
  };

  const aminityList = ["AC", "Network", "Housekeeping","screen"];

  const onSubmitAll = (e) => {
  

    const data = {
      auditoriumName,
      auditoriumLocation,
      auditoriumCapacity,
      auditoriumType,
      auditoriumAminity,
    };
    const url = `http://localhost:8080/admin/addAudi`;
    console.log(data);
    axios
      .post(url, data)
      .then((response) => {
        toast.success(response.data);
        console.log(response);
       
      })

      .catch((error) => console.log("error:"));
  };
  return (
    <div id="div_reg">
      <ThemeProvider theme={theme}>
        <Container component="main" maxWidth="md">
          <CssBaseline />
          <Box
            id="card"
            sx={{
              marginTop: 8,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Icon
              baseClassName="fas"
              className="fa-plus-circle"
              sx={{ fontSize: 30 }}
            />

            <Typography component="h1" variant="h5">
              Add Auditorium
            </Typography>
            <Box component="form" sx={{ mt: 3 }}>
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <TextField
                    onChange={(e) => {
                      SetAuditoriumName(e.target.value);
                    }}
                    autoComplete="given-name"
                    name="auditoriumName"
                    required
                    fullWidth
                    id="name"
                    label="Auditorium Name"
                    // value={audi.auditoriumName}
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    onChange={(e) => {
                      SetAuditoriumLocation(e.target.value);
                    }}
                    required
                    fullWidth
                    name="location"
                    label="location"
                    id="location"
                    autoFocus
                    // value={audi.location}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    onChange={(e) => {
                      SetAuditoriumCapacity(e.target.value);
                    }}
                    required
                    fullWidth
                    id="capacity"
                    type="number"
                    label="Auditorium Capacity"
                    name="capacity"
                    // value={audi.capacity}
                  />
                </Grid>

                <Grid item xs={12}>
                  <TextField
                    onChange={(e) => {
                      SetAuditoriumType(e.target.value);
                    }}
                    required
                    fullWidth
                    name="type"
                    label="Auditorium Type"
                    type="text"
                    id="type"
                    // value={audi.type}
                  />
                </Grid>

                <Grid>
                    <br></br>
                </Grid>
                <Grid>
                  {/* <TextField
                                    onChange={handleChange('type')}
                                        required
                                        fullWidth
                                        name="type"
                                        label="Auditorium Type"
                                        type="text"
                                        id="type"
                                        value={audi.type}
                                    /> */}
                  <div>
                    {aminityList.map((am) => {
                      return (
                        <div key={am}>
                          <FormGroup row>
                            <FormControlLabel
                            sx={{marginLeft:3, marginTop:2}}
                              onChange={(e) => {
                                getAminity(e);
                              }}
                              // value={audi.amenitites}
                              control={<input type="checkbox" value={am} />}
                              label={am}
                            />
                          </FormGroup>
                        </div>
                      );
                    })}

                    {/* <FormGroup>
                      <FormControlLabel
                        onChange={(e) => {
                          SetAuditoriumAminity(e.target.name);
                        }}
                        // value={audi.amenitites}
                        control={<Checkbox />}
                        label="AC"
                        type="text"
                        name="AC"
                      />
                      <FormControlLabel
                        onChange={(e) => {
                          SetAuditoriumAminity(e.target.name);
                        }}
                        // value={audi.amenitites}
                        control={<Checkbox />}
                        label="Network"
                        type="text"
                        name="Network"
                      />
                      <FormControlLabel
                        onChange={(e) => {
                          SetAuditoriumAminity(e.target.name);
                        }}
                        // value={audi.amenitites}
                        control={<Checkbox />}
                        label="Housekeeping"
                        type="text"
                        name="Housekeeping"
                      />
                    </FormGroup> */}
                  </div>
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                onClick={onSubmitAll}
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                endIcon={<SendIcon />}
              >
                Submit
              </Button>
              <Grid container justifyContent="flex-start">
                <Grid item>
                  {/* <Link href="http://localhost:3000/sign-in" variant="body2">
                                        Already have an account? Sign in
                                    </Link> */}
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    </div>
  );
}
