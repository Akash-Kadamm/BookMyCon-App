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

export default function AddHousekeepingRequests() {
    const [auditoriumName, SetAuditoriumName] = useState("");
  const [housekeepingTasks, SetHousekeepingTasks] = useState("");
  const [hsTasks, sethsTasks]=useState([])

  const getHousekeepingTasks = (e) => {
    let data = hsTasks;
    data.push(e.target.value);
    sethsTasks(data);
    SetHousekeepingTasks(hsTasks.toString())
  };

  const navigate = useNavigate()

  const HousekeepingTasksList = ["mopping floors","vacuuming", "sweeping", "emptying trash cans","Sanitize floors","dusting shelves"];

  const onSubmitAll = (e) => {
  

    const data = {
        auditoriumName,
      housekeepingTasks,
    };
    const url = `http://localhost:8081/api/housekeeping`;
    console.log(data);
    axios
      .post(url, data)
      .then((response) => {
        toast.success(response.data);
        console.log(response);
       
      })

      .catch((error) => console.log("error:"));
      toast.success("Request Raised Successfully")
      navigate("/auditorium-view")
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
              Add Housekeeping Request
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
                    placeholder={sessionStorage.getItem("audName")}
                    autoFocus
                  />
                </Grid>

                <Grid>
                    <br></br>
                </Grid>
                <Grid>
                  
                  <div>
                    {HousekeepingTasksList.map((am) => {
                      return (
                        <div key={am}>
                          <FormGroup row>
                            <FormControlLabel
                            sx={{marginLeft:3, marginTop:2}}
                              onChange={(e) => {
                                getHousekeepingTasks(e);
                              }}
                              // value={audi.amenitites}
                              control={<input type="checkbox" value={am} />}
                              label={am}
                            />
                          </FormGroup>
                        </div>
                      );
                    })}

                   
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
