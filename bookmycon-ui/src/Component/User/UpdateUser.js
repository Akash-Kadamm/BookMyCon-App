import { useState, useEffect } from 'react';
import axios from 'axios'
import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { InputAdornment } from '@mui/material';
import MailIcon from '@mui/icons-material/Mail';
import PhoneAndroidIcon from '@mui/icons-material/PhoneAndroid';
import PasswordTwoToneIcon from '@mui/icons-material/PasswordTwoTone';
import { useNavigate } from 'react-router';
import { toast } from "react-toastify";
import { useForm } from "react-hook-form";
import "../../css/Registration.css";


const UpdateUser = () => {
    const theme = createTheme();
    const navigate = useNavigate();
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
                    console.log("Data", response.data)
                    setUser(response.data)
                });
        }
    }
    useEffect(() => {
        getData()
    }, [])

    const [message] = useState("");
    const { handleSubmit } = useForm()

    const onSubmit = data => {
        axios.post('http://localhost:8080/user/updateProfile', user)
            .then(response => {
                toast.success(user.userName + "Your Profile Updated successfully");
                navigate("/");
            }).catch((error) => {
                toast.error("Failed to update your profile")
                navigate("/");
            });

    };


    return (
        <>
            <div id="Registration-div" >
                <div>
                    <ThemeProvider theme={theme}>
                        <Container component="main" maxWidth="xs" sx={{ marginTop: -9, height: "40rem", display: "flex", alignItems: "center", }}>
                            <CssBaseline />
                            <Box id="Registration-card"
                                sx={{ marginTop: 8, display: 'flex', flexDirection: 'column', alignItems: 'center', }}>
                                <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                                    <LockOutlinedIcon />
                                </Avatar>
                                <Typography component="h1" variant="h5">  Update  Account Details </Typography>
                                <Box component="form" noValidate sx={{ mt: 1, width: "80%" }} onSubmit={handleSubmit(onSubmit)}>
                                    <Grid container spacing={2}>
                                        <Grid item xs={12}>
                                            <TextField
                                                autoComplete="given-name"
                                                name="userName"
                                                fullWidth
                                                id="userName"
                                                value={user.userName}
                                                onChange={(e) => {
                                                    setUser({ ...user, userName: e.target.value })
                                                }}
                                                InputProps={{
                                                    startAdornment: (
                                                        <InputAdornment position="start">
                                                            <Avatar variant="soft" />
                                                        </InputAdornment>),
                                                }}
                                            />
                                        </Grid>

                                        <Grid item xs={12}>
                                            <TextField
                                                fullWidth
                                                id="userEmail"
                                                name="userEmail"
                                                autoComplete="Email"
                                                value={user.userEmail}
                                                onChange={(e) => {
                                                    setUser({ ...user, userEmail: e.target.value })
                                                }}
                                                InputProps={{
                                                    startAdornment: (
                                                        <InputAdornment position="start">
                                                            <MailIcon />
                                                        </InputAdornment>
                                                    ),
                                                }}
                                            />
                                        </Grid>

                                        <Grid item xs={12}>
                                            <TextField
                                                required
                                                fullWidth
                                                name="userPassword"
                                                type="password"
                                                id="userPassword"
                                                autoComplete="new-password"
                                                value={user.userPassword}
                                                onChange={(e) => {
                                                    setUser({ ...user, userPassword: e.target.value })
                                                }}
                                                InputProps={{
                                                    startAdornment: (
                                                        <InputAdornment position="start">
                                                            <PasswordTwoToneIcon />
                                                        </InputAdornment>
                                                    ),
                                                }}
                                            />
                                            <Box sx={{ color: "error.main" }}>
                                                {message != null && <span>{message}</span>}{" "}
                                            </Box>

                                        </Grid>
                                        <Grid item xs={12}>
                                            <TextField
                                                required
                                                fullWidth
                                                id="userContact"
                                                name="userContact"
                                                autoComplete="family-name"
                                                value={user.userContact}
                                                onChange={(e) => {
                                                    setUser({ ...user, userContact: e.target.value })
                                                }}
                                                InputProps={{
                                                    startAdornment: (
                                                        <InputAdornment position="start">
                                                            <PhoneAndroidIcon />
                                                        </InputAdornment>
                                                    ),
                                                }}
                                            />
                                        </Grid>
                                    </Grid>

                                    <Button
                                        type="submit"
                                        fullWidth
                                        variant="contained"
                                        sx={{ mt: 3, mb: 2 }}
                                    >
                                        Update Profile
                                    </Button>
                                </Box>
                            </Box>
                        </Container>
                    </ThemeProvider>
                </div>
            </div>
        </>
    )
}

export default UpdateUser
