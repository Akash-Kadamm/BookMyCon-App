import AccountCircle from '@mui/icons-material/AccountCircle';
import AssignmentIndIcon from '@mui/icons-material/AssignmentInd';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { InputAdornment } from '@mui/material';
import Avatar from '@mui/material/Avatar';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { useForm } from "react-hook-form";
import './Login.css'
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';


const theme = createTheme();

export default function UpdateUser() {
    const navigate = useNavigate()

    const [user, setUser] = useState({
        userName: "",
        userPassword: "",
        userEmail: "",
        userContact: ""
    })

    const getData = () => {
        if (sessionStorage.getItem("userLogin")) {
            axios.get('http://localhost:8080/user/get-user-by-email/' + JSON.parse(sessionStorage.getItem("userLogin")).userEmail)
                .then(response => {
                    setUser(response.data)
                });
        }
    }
    useEffect(() => {
        getData()
    }, [])

    const [message, setMessage] = useState("");
    const { register, handleSubmit, formState: { errors } } = useForm()

    const onSubmit = data => {
       
        axios.put('http://localhost:8080/user/updateProfile', user)
            .then(response => {
                toast.success(response.data.message);
                navigate("/")
            })
    };


    return (
        <div id='Login-div'>
            <ThemeProvider theme={theme}>
                <Container component="main" maxWidth="xs" >
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
                                    onChange={(e) => {
                                        setUser({ ...user, userName: e.target.value })
                                    }}
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                />{
                                    errors.userName?.type === "required" && <Box id="error" sx={{ color: 'error.main' }}>Please enter Name </Box>
                                }

                            </Grid><br />

                            <Grid item xm={12} sm={6}>
                                <TextField
                                    autoComplete="given-name"
                                    name="userEmail"
                                    fullWidth
                                    value={user.userEmail}
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
                                    onChange={(e) => {
                                        setUser({ ...user, userEmail: e.target.value })
                                    }}
                                />
                                {
                                    errors.userEmail?.type === "required" && <Box id="error" sx={{ color: 'error.main' }}>Please enter Email </Box>
                                }
                            </Grid>
                            <br />

                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="given-name"
                                    name="userPassword"
                                    fullWidth
                                    value={user.userPassword}
                                    id="userName"
                                    label="Password"
                                    autoFocus
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    onChange={(e) => {
                                        setUser({ ...user, userPassword: e.target.value })
                                    }}
                                />{errors.userPassword && errors.userPassword.type === "minLength" && (
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
                                    onChange={(e) => {
                                        setUser({ ...user, userContact: e.target.value })
                                    }}

                                />
                                {
                                    errors.userContact?.type === "required" && <Box id="error" sx={{ color: 'error.main' }}>Please enter Valid Contact Number </Box>
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
            </ThemeProvider></div>
    );
}