import { useState } from 'react';
import axios from 'axios'
import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { InputAdornment, ListItem } from '@mui/material';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import MarkEmailUnreadIcon from '@mui/icons-material/MarkEmailUnread';
import ListIcon from '@mui/icons-material/List';
import "../../css/Complaint.css";
import { ReactSession } from "react-client-session";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";

const theme = createTheme();
function Complaint() {
    const navigate = useNavigate();
    
    let [complaint, setComplaint] = useState({
        userId:JSON.parse(sessionStorage.getItem("userLogin")).userId,
        complaintType:"",
        orderId:3,
        bookingId: ReactSession.get("BookingIdForFood"),
        description:""
    });

    const handleChange = name => e => {
        setComplaint({ ...complaint, [name]: e.target.value });
    };

    const handleSubmit=(event)=>{
    
        event.preventDefault();

     
        axios.post("http://localhost:8080/complaint/makeComplaint",complaint,{               
            headers: {
           "Content-Type": "application/json",
       },
       })
       .then(res=>{console.log(res.data)
        toast.success(res.data);
        navigate("/auditorium-view");
    })
       
    }

  return (
      <>
       <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box id="complaint-card"
                    onSubmit={handleSubmit}
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'warning.main' }}>
                        <MarkEmailUnreadIcon/>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                       Complaint Box
                    </Typography>
                    <Box component="form" noValidate sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                            <Box sx={{ minWidth: 120 }}>
                        <FormControl fullWidth>
                            <InputLabel id="demo-simple-select-label" color='secondary'>Complaint for</InputLabel>
                            <Select
                            labelId="demo-simple-select-label"
                            color="secondary" 
                            id="complaintType"
                            label="Complaint For"
                            value={complaint.complaintType}
                            onChange={handleChange("complaintType")}
                            InputProps={{
                                startAdornment: (
                                  <InputAdornment position="start">
                                    <ListIcon/>
                                  </InputAdornment>
                                ),
                              }}
                            >
                            <MenuItem value={"both"}>Both</MenuItem>
                            <MenuItem value={"drinksAndSnacks"}>Drinks And Snacks</MenuItem>
                            <MenuItem value={"houseKeeping"}>House Kepping</MenuItem>
                            </Select>
                            
                        </FormControl>
                        </Box>
                                            
                            </Grid>

                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="Description"
                                    label="Complaint Description"
                                    name="description"
                                    value={complaint.description}
                                    onChange={handleChange("description")}
                                    multiline
                                    rows={10}
                                    autoFocus
                                    color="secondary"
                                />
                               
                            </Grid>
                        <Button
                        id='submit-button'
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                           Submit
                        </Button>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
      </>
  )
}

export default Complaint