import React from 'react'
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import DashboardIcon from '@mui/icons-material/Dashboard';
import LoginIcon from '@mui/icons-material/Login';
import HowToRegIcon from '@mui/icons-material/HowToReg';
import { useNavigate } from "react-router";
import MapIcon from '@mui/icons-material/Map';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import BookIcon from '@mui/icons-material/Book';
import BarChartIcon from '@mui/icons-material/BarChart';
import SyncAltIcon from '@mui/icons-material/SyncAlt';

export const AdminHomePage = () => {
    const navigate = useNavigate();
  return (
  
  <div style={{marginLeft:"20px",marginTop:"80px", textAlign:"left"}} ><Grid container spacing={2}>
    
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/admin-floormap")}} >
    <MapIcon  sx={{ fontSize: 40 }}color="secondary"  />
    Floor Updation
   
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/auditorium-list")}}  >
    <AccountBalanceIcon  sx={{ fontSize: 40 }} color="secondary" />
    Auditorium   
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/user_details")}} >
    <AccountCircleIcon  sx={{ fontSize: 40 }} color="secondary"  />
    All User
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/all_booking")}}  >
    <BookIcon  sx={{ fontSize: 40 }} color="secondary" />
    Booking   
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/all_booking")}}  >
    <SyncAltIcon  sx={{ fontSize: 40 }} color="secondary" />
    Log   
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/cart")}}  >
    <BarChartIcon  sx={{ fontSize: 40 }} color="secondary" />
    Report   
     </div>    
    </Grid>
  </Grid></div>
  )
}
