import React from 'react'
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import DashboardIcon from '@mui/icons-material/Dashboard';
import LoginIcon from '@mui/icons-material/Login';
import HowToRegIcon from '@mui/icons-material/HowToReg';
import { useNavigate } from "react-router";
import {  Link } from "react-router-dom";
import MapIcon from '@mui/icons-material/Map';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import CycloneIcon from '@mui/icons-material/Cyclone';
import EmergencyShareIcon from '@mui/icons-material/EmergencyShare';
import { Badge } from "@mui/material";
import CartIcon from "@mui/icons-material/ShoppingCart";

import { useSelector } from "react-redux";

export const UserHomePage = () => {

    const cart = useSelector((state) => state);

    const navigate = useNavigate();
  return (
    
    <div style={{marginLeft:"20px",marginTop:"80px", textAlign:"left"}} ><Grid container spacing={2}>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/alexa")}}  >
    <EmergencyShareIcon sx={{ fontSize: 40 }} color="secondary" />
    Alexa  
     </div>    
    </Grid> <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/assistant")}}  >
    <CycloneIcon sx={{ fontSize: 40 }} color="secondary" />
    Solution Assistant   
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/floormain")}} >
    <MapIcon sx={{ fontSize: 40 }} color="secondary"  />
    Floor Map
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/calender-view")}}  >
    <CalendarMonthIcon sx={{ fontSize: 40 }} color="secondary" />
    Calender   
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/auditorium-view")}} >
    <CollectionsBookmarkIcon sx={{ fontSize: 40 }} color="secondary"  />
    All Bookings
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/feedback")}}  >
    <ThumbUpAltIcon sx={{ fontSize: 40 }} color="secondary" />
    Feedback   
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/cart")}}  >
    <Badge  badgeContent={cart.length}  sx={{ mr: 1}} >
    <ShoppingCartIcon sx={{ fontSize: 40 }} color="secondary" />
      </Badge>  
    Cart    
     </div>    
    </Grid>
    <Grid item xs={6}>
    <div style={{color: "#9c27b0", textAlign:"left"}} onClick={()=>{navigate("/guest")}} >
    <CollectionsBookmarkIcon sx={{ fontSize: 40 }} color="secondary"  />
    Guest
     </div>    
    </Grid>
   
  </Grid></div>
  )
}
