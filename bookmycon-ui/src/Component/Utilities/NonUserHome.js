import React from 'react'
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import DashboardIcon from '@mui/icons-material/Dashboard';
import LoginIcon from '@mui/icons-material/Login';
import HowToRegIcon from '@mui/icons-material/HowToReg';
import { useNavigate } from "react-router";


export const NonUserHome = () => {
    const navigate = useNavigate();
  return (
    <div style={{marginLeft:"20px",marginTop:"80px", textAlign:"left"}} ><Grid container spacing={2}>
    {/* <Grid item xs={6}>
   <div style={{color: "#6610f2"}} onClick={()=>{navigate("/nonUserHome")}} >
   <DashboardIcon  color="secondary"  />
   DashBoard
   </div>
    </Grid> */}
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/signin")}} >
    <LoginIcon sx={{ fontSize: 40 }} color="secondary"  />
     Login  
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/signup")}}  >
    <HowToRegIcon sx={{ fontSize: 40 }}  color="secondary" />
     Registration   
     </div>    
    </Grid>
   
  </Grid></div>
  )
}
