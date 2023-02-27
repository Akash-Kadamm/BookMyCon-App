import { useState } from 'react';
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
import { useForm } from "react-hook-form";
import { InputAdornment } from '@mui/material';
import  MailIcon  from '@mui/icons-material/Mail';
import PhoneAndroidIcon from '@mui/icons-material/PhoneAndroid';
import  PasswordTwoToneIcon  from '@mui/icons-material/PasswordTwoTone';
import { useNavigate } from 'react-router';
import { toast } from "react-toastify";
import "../../css/Registration.css";

const Registration = () => {

    const navigate = useNavigate();

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm()

    const [message, setMessage] = useState("");
    const [user, setUser] = useState({});

    const theme = createTheme();

    const onSubmit = (data) => {
       
        axios.post("http://localhost:8080/user/registration", data)
            .then((result) => {
                console.log(result.data)
                if(result.data.user.userRole === 'user'){
                    toast.success("Register sucessfully. please Login in")
                    console.log(result.data.message)
                    navigate("/signin")
                }else{
                toast.error(result.data.message)
                navigate("/signup")
                }
            }).catch((err) => {
                navigate("/signup")
            });
    };
    return (
        <>
        <div id="Registration-div" >
        <div>
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs"
            sx={{
                marginTop: -9,
                height: "40rem",
                display: "flex",
                alignItems: "center",
              }}
            >
                <CssBaseline />
                <Box
                    id="Registration-card"
                    onSubmit={handleSubmit(onSubmit)}
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign up
                    </Typography>
                    <Box component="form" noValidate sx={{ mt: 1, width: "80%" }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    autoComplete="given-name"
                                    name="userName"
                                    required
                                    fullWidth
                                    id="userName"
                                    placeholder="Enter Full Name Here"
                                    autoFocus
                                    {...register("userName", {
                                        required: true
                                    })}
                                    InputProps={{
                                        startAdornment: (
                                          <InputAdornment position="start">
                                             <Avatar variant="soft" />
                                          </InputAdornment>
                                        ),
                                      }}
                                />
                                {
                                    errors.userName?.type === "required" && <Box id="error" sx={{ color: 'error.main' }}>Please Enter Name</Box>
                                }
                            </Grid>

                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="userEmail"
                                    name="userEmail"
                                    autoComplete="Email"
                                    placeholder="Enter Email Here"
                                    {...register("userEmail", {
                                        required: true,
                                        pattern: /^[^@ ]+@[^@ ]+\.[^@ .]{2,}$/,
                                    })}
                                    InputProps={{
                                        startAdornment: (
                                          <InputAdornment position="start">
                                            <MailIcon />
                                          </InputAdornment>
                                        ),
                                      }}
                                />
                                {errors.userEmail?.type === "required" && (
                                    <Box id="error" sx={{ color: "error.main" }}>
                                        Please Enter Your Email
                                    </Box>
                                )}
                                {errors.userEmail && errors.userEmail.type === "pattern" && (
                                    <p className="text-danger errorMsg">Email is Not Valid.</p>
                                )}
                            </Grid>

                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    name="userPassword"
                                    type="password"
                                    id="userPassword"
                                    autoComplete="new-password"
                                    placeholder="Enter Password Here"
                                    {...register("userPassword", { required: true, minLength: 6 })}
                                    InputProps={{
                                        startAdornment: (
                                          <InputAdornment position="start">
                                            <PasswordTwoToneIcon />
                                          </InputAdornment>
                                        ),
                                      }}
                                />
                                {errors.userPassword?.type === "required" && (
                                    <Box id="error" sx={{ color: "error.main" }}>
                                        Please Enter Your Password{" "}
                                    </Box>
                                )}
                                {errors.userPassword && errors.userPassword.type === "minLength" && (
                                    <p className="text-danger errorMsg">
                                        Password Should Be at-least 6 Characters.
                                    </p>
                                )}
                                <Box sx={{ color: "error.main" }}>
                                    {message != null && <span>{message}</span>}{" "}
                                </Box>

                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="userContact"
                                    name="Contact"
                                    autoComplete="family-name"
                                    placeholder="Contact Number"
                                    {...register("userContact", { required: true, maxLength: 10 })}
                                    InputProps={{
                                        startAdornment: (
                                          <InputAdornment position="start">
                                           <PhoneAndroidIcon />
                                          </InputAdornment>
                                        ),
                                      }}
                                />
                                {
                                    errors.userContact?.type === "required" && <Box id="error" sx={{ color: 'error.main' }}>Please Enter Valid Contact Number </Box>
                                }
                            </Grid>
                        </Grid>

                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Sign Up
                        </Button>
                        <Grid container justifyContent="center">
                            <Grid item >
                                <Link href="/signin" variant="body2">
                                    Already have an account? Sign in
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
        </div>
        </div>
        </>
    );
}

export default Registration;