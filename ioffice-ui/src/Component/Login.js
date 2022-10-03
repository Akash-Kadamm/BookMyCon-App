import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Avatar from "@mui/material/Avatar";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Container from "@mui/material/Container";
import CssBaseline from "@mui/material/CssBaseline";
import InputAdornment from "@mui/material/InputAdornment";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login.css";
import MailIcon from "@mui/icons-material/Mail";
import PasswordTwoToneIcon from "@mui/icons-material/PasswordTwoTone";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";

const theme = createTheme();

function Login() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const [message, setMessage] = useState("");
  const [user, setUser] = useState({});

  const navigate = useNavigate();

  const onSubmit = (data) => {
    // alert(`${data.email}, ${data.password}`);
    // console.log(data)

    axios
      .post("http://localhost:8080/login/loginCheck", data, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((res) => {
      
        toast.error(res.data.message);
        sessionStorage.setItem("userLogin", JSON.stringify(res.data.user));
     
        if (res.data.user.userRole === "user") {
          toast.success(res.data.user.userName+" Login successfully");
          navigate("/");
          window.location.reload();
        } else {
          toast.success(res.data.user.userName+" Login successfully");
          navigate("/admin-home");
          window.location.reload();
        }
        
      })
      .catch((err) => {
        console.log(err.response);
        toast.error(err.response.data);
      });
  };

  return (
    <>
      <div id="Login-div">
        <div>
          <ThemeProvider theme={theme}>
            <Container
              component="main"
              maxWidth="xs"
              sx={{
                marginTop: -8,
                height: "40rem",
                display: "flex",
                alignItems: "center",
              }}
            >
              <CssBaseline />

              <Box
                id="Login-card"
                onSubmit={handleSubmit(onSubmit)}
                sx={{
                  marginTop: 8,
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "center",
                }}
              >
                <Avatar sx={{ mt: 4, mb: 2, bgcolor: "primary.main" }}>
                  <LockOutlinedIcon />
                </Avatar>
                <Typography
                  component="h1"
                  variant="h6"
                  sx={{ color: "secondary.main" }}
                >
                  Sign In
                </Typography>
                <Box component="form" noValidate sx={{ mt: 1, width: "80%" }}>
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="Email"
                    label="Email"
                    name="Email"
                    autoComplete="Email"
                    variant="outlined"
                    {...register("email", {
                      required: true,
                      pattern: /^[^@ ]+@[^@ ]+\.[^@ .]{2,}$/,
                    })}
                    size="large"
                    sx={{ mt: 1 }}
                    InputProps={{
                      startAdornment: (
                        <InputAdornment position="start">
                          <MailIcon />
                        </InputAdornment>
                      ),
                    }}
                  />
                  {errors.email?.type === "required" && (
                    <Box id="error" sx={{ color: "error.main" }}>
                      Please enter your Email
                    </Box>
                  )}
                  {errors.email && errors.email.type === "pattern" && (
                    <p className="text-danger errorMsg">Email is not valid.</p>
                  )}
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    name="Password"
                    label="Password"
                    type="password"
                    id="password"
                    autoComplete="Password"
                    variant="outlined"
                    size="large"
                    sx={{ mt: 1, my: 1 }}
                    {...register("password", { required: true, minLength: 6 })}
                    InputProps={{
                      startAdornment: (
                        <InputAdornment position="start">
                          <PasswordTwoToneIcon />
                        </InputAdornment>
                      ),
                    }}
                  />
                  {errors.password?.type === "required" && (
                    <Box id="error" sx={{ color: "error.main" }}>
                      Please enter your Password{" "}
                    </Box>
                  )}
                  {errors.password && errors.password.type === "minLength" && (
                    <p className="text-danger errorMsg">
                      Password should be at-least 6 characters.
                    </p>
                  )}
                  <Box sx={{ color: "error.main" }}>
                    {message != null && <span>{message}</span>}{" "}
                  </Box>

                  <Button
                    type="submit"
                    variant="contained"
                    sx={{ mt: 3, mb: 2, ml: 11, color: "black" }}
                    color="primary"
                  >
                    Login
                  </Button>
                </Box>
              </Box>
            </Container>
          </ThemeProvider>
        </div>
      </div>
    </>
  );
}

export default Login;
