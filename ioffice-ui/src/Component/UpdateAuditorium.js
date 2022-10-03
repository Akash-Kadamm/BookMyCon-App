import { yupResolver } from '@hookform/resolvers/yup';
import AccountCircle from '@mui/icons-material/AccountCircle';
import AssignmentIndIcon from '@mui/icons-material/AssignmentInd';
import EmailRounded from '@mui/icons-material/EmailRounded';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import VpnKey from '@mui/icons-material/VpnKey';
import { InputAdornment, Radio } from '@mui/material';
import Avatar from '@mui/material/Avatar';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Link from '@mui/material/Link';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useForm } from "react-hook-form";
import { useNavigate } from 'react-router-dom';
import * as Yup from 'yup';
import './Registration.css'
import { toast } from 'react-toastify';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import RadioGroup from '@mui/material/RadioGroup';

const theme = createTheme();

export default function UpdateAuditorium() {

  
   
    const { register, handleSubmit, errors } = useForm()
   
    // const navigate = useNavigate();

    const[auditorium,setAuditorium]=useState({});
    const [message, setMessage] = useState("");
    
    useEffect(()=>{
        getAuditoriumDetails();
    },[]);

    const getAuditoriumDetails=()=> {
        axios
        .get("")
        .then(response => setAuditorium(response.data)).catch((error=>setMessage("error occered ")));
    }
    const onSubmit = data => {

        axios.put("", data, { headers: { "Content-Type": "application/json", }, })
            .then((response) => {

               
                // navigate('');

            })
            .catch((err) => {
                console.log(err.response);


            });


    };


    return (<>

        <div id='Registration-div'>
            <ThemeProvider theme={theme}>
                <Container component="main" maxWidth="xs">
                    <CssBaseline />

                    <Box id="Registration-card"
                        sx={{
                            marginTop: 8,
                            display: 'flex',
                            flexDirection: 'column',
                            alignItems: 'center',
                        }}
                    >
                        <Avatar sx={{ m: 1, ml: 3, bgcolor: 'secondary.main' }}>
                            <LockOutlinedIcon />
                        </Avatar>
                        <Typography component="h1" variant="h5" sx={{ color: 'secondary.main' }}>
                            Update  Auditorium Details
                        </Typography>
                        <Box component="form" noValidate onSubmit={handleSubmit(onSubmit)} sx={{ mt: 1, width: '80%' }}>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    
                                    autoComplete="given-name"
                                    name="Auditorium ID"
                                    readOnly
                                    fullWidth
                                    value={auditorium.auditoriumId}
                                    id="auditoriumId"
                                    label="Auditorium ID"
                                    autoFocus
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    {...register("auditoriumId")}

                                />

                            </Grid><br />
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="given-name"
                                    name="auditoriumName"
                                    required
                                    fullWidth
                                    defaultValue={auditorium.auditoriumName}
                                    id="auditoriumName"
                                    label="Auditorium Name"
                                    autoFocus
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    {...register("auditoriumName")}

                                />

                            </Grid>
                            <br />
                            <Grid item xm={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    id="auditoriumLocation"
                                    label="Auditorium Location"
                                    name="auditoriumLocation"
                                    defaultValue={auditorium.auditoriumLocation}
                                    autoComplete="family-name"
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    {...register("auditoriumLocation")}

                                />

                            </Grid>
                            <br />
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    type="number"
                                    id="auditoriumCapacity"
                                    label="Auditorium Capacity"
                                    defaultValue={auditorium.auditoriumCapacity}
                                    name="auditoriumCapacity"
                                    autoComplete="family-name"
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    {...register("auditoriumCapacity")}

                                /> </Grid>
                            <br />
                            

                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color='primary'
                                sx={{ mt: 3, mb: 2, color: 'black' }}
                                startIcon={< AssignmentIndIcon />}
                            >
                                Update Auditorium
                            </Button>
                           
                        </Box>
                    </Box>

                </Container>
            </ThemeProvider></div></>
    );
}