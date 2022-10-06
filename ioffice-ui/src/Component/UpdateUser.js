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
import React, { useState } from 'react';
import { useForm } from "react-hook-form";
import { useNavigate } from 'react-router-dom';
import * as Yup from 'yup';
import './Login.css'

import { toast } from 'react-toastify';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import RadioGroup from '@mui/material/RadioGroup';

const theme = createTheme();

export default function UpdateUser() {

    

    const[user,setUser]=useState({
        userName:"Akash Kadam",
        userEmail:"akashkad@cybage.com",
        userPassword:"ak@123",
        userContact:"1234567891"
    });
    
    const [message, setMessage] = useState("");
    const { register, handleSubmit, formState:{errors} } = useForm()
    
    // const navigate = useNavigate();

    const onSubmit = data => {
   console.log(user.userEmail);
        console.log(data);
        // axios.post("", data, { headers: { "Content-Type": "application/json", }, })
        //     .then((response) => {

        //        
        //         // navigate('');

        //     })
        //     .catch((err) => {
        //         console.log(err.response);


        //     });


    };


    return (<>

        <div id='Login-div'>
            <ThemeProvider theme={theme}>
                <Container component="main" maxWidth="xs">
                    <CssBaseline />

                    <Box id="Login-card"
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
                            Update  Account Details
                        </Typography>
                        <Box component="form" noValidate onSubmit={handleSubmit(onSubmit)} sx={{ mt: 1, width: '80%' }}>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    
                                    autoComplete="given-name"
                                    name="userName"
                                    fullWidth
                                    value={user.userName}
                                    id="userName"
                                    label="Name"
                                    autoFocus
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    {...register("userName",{required:true})}

                                />{
                                    errors.userName?.type === "required" && <Box  id="error" sx={{ color: 'error.main' }}>Please enter Name </Box>
                                }
                                 
                            </Grid><br />
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="given-name"
                                    name="userEmail"
                                    required
                                    fullWidth
                                    Value={user.userEmail}
                                    id="userEmail"
                                    label="Email"
                                    autoFocus
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    {...register("userEmail",{required:true})}

                                />

                            </Grid>
                            <br />
                            <Grid item xm={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    id="userPassword"
                                    label="Password"
                                    name="userPassword"
                                    defaultValue={user.userPassword}
                                    autoComplete="family-name"
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    {...register("userPassword", { required: true ,minLength: 6})}
                                    
                                />
                                {
                                        errors.userPassword?.type === "required" && <Box  id="error" sx={{ color: 'error.main' }}>Please enter your Password </Box>
                                    }
                                     {errors.userPassword && errors.userPassword.type === "minLength" && (
                                            <p className="text-danger errorMsg">
                                            Password should be at-least 6 characters.
                                            </p>
                                        )}
                                    <Box sx={{ color: 'error.main' }}>{(message != null) && <span>{message}</span>} </Box>
                                   

                            </Grid>
                            <br />
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    id="userContact"
                                    label="Contact Number"
                                    value={user.userContact}
                                    name="userContact"
                                    autoComplete="family-name"
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    {...register("userContact", { required: true ,maxLength: 10})}

                                />
                                {
                                        errors.userContact?.type === "required" && <Box  id="error" sx={{ color: 'error.main' }}>Please enter Valid Contact Number </Box>
                                    }
                                    
                                 </Grid> 
                           
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color='primary'
                                sx={{ mt: 3, mb: 2, color: 'black' }}
                                startIcon={< AssignmentIndIcon />}
                            >
                                Update Profile
                            </Button>
                           
                        </Box>
                    </Box>

                </Container>
            </ThemeProvider></div></>
    );
}