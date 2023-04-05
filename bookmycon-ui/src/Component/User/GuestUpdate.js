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
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import React, { useState, useEffect } from 'react';
import { useForm } from "react-hook-form";
import "../../css/GuestUpdate.css";

function GuestUpdate() {

    const theme = createTheme();

    const navigate = useNavigate();
    const [guestName , setGuestName] = useState('')
    const [guestCompany, setGuestCompany] = useState('')
    const [guestEmail , setGuestEmail] = useState('')
    const [guestMobileNo , setGuestMobileNo] = useState('')
    const [thumbnail, setThumbnail] = useState(undefined)
    const [guest, setGuest] = useState('')

    const onFileSelect = (event) => {
        setThumbnail(event.target.files[0])
    }

    // const [guest, setGuest] = useState({
    //     guestName: "",
    //     guestCompany: "",
    //     guestEmail: "",
    //     guestMobileNo: "",
    //     thumbnail: ""
    // })

    const getData = (guestName) => {
         {
            axios.get('http://localhost:8080/guest/get-guest-by-name/' + guestName)
                .then(response => {
                    console.log("Data", response.data)
                    setGuestName(response.data)
                });
        }
    }
    useEffect(() => {
        getData()
    }, [])

   const [message, setMessage] = useState("");
    const { register, handleSubmit, formState: { errors } } = useForm()

    const onSubmit = data => {

        console.log(data);
        axios.post('http://localhost:8080/guest/updateGuestProfile/' + data.guestName)
            .then(response => {
                toast.success(guest.guestName + "Your Guest Profile Updated successfully");
                console.log(response)
                navigate("/guest");
            }).catch((error) =>{
              toast.error("Failed to update your Guest profile")
                console.log(error)
              navigate("/guest");
            });

    };


    return (<>

        <div id='Login-div'>
            <ThemeProvider theme={theme}>
                <Container component="main" maxWidth="xs" 
                sx={{marginTop: -9,height: "40rem", display: "flex",alignItems: "center",}}>
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
                                    name="guestName"
                                    fullWidth
                                    value={guest.guestName}
                                    id="outlined-basic"
                                    variant="outlined"
                                    label="Name"
                                    autoFocus
                                    onChange={(event) => {setGuestName(event.target.value)}}
                                    size="large"
                    sx={{ mt: 1 }}
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment  position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                />{
                                    errors.guestName?.type === "required" && <Box id="error" sx={{ color: 'error.main' }}>Please enter Name </Box>
                                }

                            </Grid><br />

                            <Grid item xm={12} sm={6}>
                                <TextField
                                    autoComplete="given-name"
                                    name="guestEmail"
                                    fullWidth
                                    value={guest.guestEmail}
                                    id="guestEmail"
                                    label="Email"
                                    autoFocus
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    onChange={(event) => {setGuestEmail(event.target.value)}}
                                />
                                {
                                    errors.guestEmail?.type === "required" && <Box id="error" sx={{ color: 'error.main' }}>Please enter Email </Box>
                                }
                            </Grid>
                            <br />

                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="given-name"
                                    name="guestCompany"
                                    fullWidth
                                    value={guest.guestCompany}
                                    id="guestCompany"
                                    label="Comapny"
                                    autoFocus
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    onChange={(event) => {setGuestCompany(event.target.value)}}
                                />{
                                    errors.guestCompany?.type === "required" && <Box id="error" sx={{ color: 'error.main' }}>Please enter Company </Box>
                                }
                                <Box sx={{ color: 'error.main' }}>{(message != null) && <span>{message}</span>} </Box>


                            </Grid>
                            <br />
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    id="guestContact"
                                    label="Contact Number"
                                    value={guest.guestMobileNo}
                                    name="guestMobileNo"
                                    autoComplete="MobileNo"
                                    InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <AccountCircle />
                                            </InputAdornment>
                                        ),
                                    }}
                                    onChange={(event) => {setGuestMobileNo(event.target.value)}}

                                />
                                {
                                    errors.userContact?.type === "required" && <Box id="error" sx={{ color: 'error.main' }}>Please enter Valid Contact Number </Box>
                                }

                            </Grid>
                            <br />
                            <Grid item xs={12}>
                            <TextField  
                                name='Thumbnail' accept="image/*" 
                                type={"file"} fullWidth required margin='normal' 
                                onChange={onFileSelect}>
                            </TextField>
                            </Grid>

                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color='primary'
                                sx={{ mt: 3, mb: 2, color: 'black' }}
                                startIcon={< AssignmentIndIcon />}
                                onChange = {(e)=> getData({ ...guest, guestName: e.target.value })}
                            >
                                Update Profile
                            </Button>

                        </Box>
                    </Box>

                </Container>
            </ThemeProvider></div></>
    );
}

export default GuestUpdate