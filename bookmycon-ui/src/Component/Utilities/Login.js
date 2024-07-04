import React from 'react';
import { useForm } from 'react-hook-form';
import { TextField, Button, Card, CardContent, Typography, Grid, Link, useMediaQuery, useTheme } from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify'; 
import 'react-toastify/dist/ReactToastify.css'; 
import CardMedia from '@mui/material/CardMedia';
import LoginImage from '../../../src/Image/Login.png'

const cardStyle = {
  maxWidth: 1400,
  height: '100%',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  marginTop: 70,
  padding: '20px',
  boxSizing: 'border-box',
};

const Login = () => {
  const { register, handleSubmit, formState: { errors } } = useForm();
  const navigate = useNavigate();
  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down('md'));

  const onSubmit = async (data) => {
    try {
      const response = await axios.post("http://localhost:8080/login/loginCheck", data, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      const { user } = response.data;
      sessionStorage.setItem("userLogin", JSON.stringify(user));
      sessionStorage.setItem("userRole", JSON.stringify(user.userRole));

      switch (user.userRole) {
        case "user":
          toast.success(`${user.userName} Login successfully`);
          navigate("/userHomePage");
          window.location.reload();
          break;
        case "admin":
          toast.success(`${user.userName} Login successfully`);
          navigate("/adminHomePage");
          window.location.reload();
          break;
        case "vendor":
          toast.success(`${user.userName} Login successfully`);
          navigate("/vendor");
          window.location.reload();
          break;
        default:
          toast.error("Unknown user role");
      }
    } catch (error) {
      toast.error(error.response.data);
    }
  };

  return (
    <Grid container style={cardStyle}>
      <Card style={{ display: 'flex', flexDirection: isSmallScreen ? 'column' : 'row', width: '100%', height: '100%', borderRadius: '4%', backgroundColor: '#faf4e6' }}>
        <Grid item xs={12} md={6} style={{ padding: '40px', display: 'flex', alignItems: 'center' }}>
          <CardContent style={{ width: '100%' }}>
            <Typography variant="h4" gutterBottom style={{ fontFamily: 'cursive', fontWeight: 'bold' }}>Welcome Back!</Typography>
            <form onSubmit={handleSubmit(onSubmit)} style={{ marginTop: '15%' }}>
              <TextField
                id="email"
                label="Email"
                type="email"
                variant="outlined"
                fullWidth
                margin="normal"
                {...register("email", { required: true })}
                error={!!errors.email}
                helperText={errors.email ? 'Email is required' : ''}
                InputProps={{
                  style: { backgroundColor: 'white', borderRadius: '5px' },
                }}
              />
              <TextField
                id="password"
                label="Password"
                type="password"
                variant="outlined"
                fullWidth
                margin="normal"
                {...register("password", { required: true })}
                error={!!errors.password}
                helperText={errors.password ? 'Password is required' : ''}
                InputProps={{
                  style: { backgroundColor: 'white', borderRadius: '5px' },
                }}
              />
              <Button
                type="submit"
                variant="contained"
                color="primary"
                fullWidth
                size="large"
                style={{ marginTop: '10px', maxWidth: '250px', backgroundColor: '#fa5e52', color: 'black', fontWeight: 'bolder', fontFamily: 'cursive' }}
              >
                Login
              </Button>
            </form>
            <Typography variant="body2" style={{ marginTop: '10px' }}>
              Don't have an account? <Link href="/signup" style={{ textDecoration: 'none' }}>Register</Link>
            </Typography>
          </CardContent>
        </Grid>
        {!isSmallScreen && (
          <Grid item xs={12} md={6} style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' ,  padding:'2%'}}>
            <Card style={{ margin: '2%', borderRadius: '3%', width: '100%', height: '100%',}}>
              <CardMedia
                component="img"
                image={LoginImage}
                style={{ objectFit: 'cover', borderRadius: '3%' ,height: '100%' }}
              />
            </Card>
          </Grid>
        )}
      </Card>
    </Grid>
  );
};

export default Login;
