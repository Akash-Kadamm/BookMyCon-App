import React from 'react';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CardActions from '@mui/material/CardActions';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { useNavigate } from "react-router";
import { useSelector } from "react-redux";
import EmergencyShareIcon from '@mui/icons-material/EmergencyShare';
import CycloneIcon from '@mui/icons-material/Cyclone';
import MapIcon from '@mui/icons-material/Map';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import Badge from "@mui/material/Badge";
import OfficeLayout from "../../../src/Image/officelayout.jpg"


const cards = [
  { title: "Alexa", icon: <EmergencyShareIcon sx={{ fontSize: 50 }} color="secondary" />, route: "/alexa" },
  { title: "Solution Assistant", icon: <CycloneIcon sx={{ fontSize: 60 }} color="secondary" />, route: "/assistant" },
  { title: "Floor Map", icon: <MapIcon sx={{ fontSize: 60 }} color="secondary" />, route: "/floormain" },
  { title: "Calendar", icon: <CalendarMonthIcon sx={{ fontSize: 60 }} color="secondary" />, route: "/calender-view" },
  { title: "All Bookings", icon: <CollectionsBookmarkIcon sx={{ fontSize: 60 }} color="secondary" />, route: "/auditorium-view" },
  { title: "Feedback", icon: <ThumbUpAltIcon sx={{ fontSize: 60 }} color="secondary" />, route: "/feedback" },
  { title: "Cart", icon: <ShoppingCartIcon sx={{ fontSize: 60 }} color="secondary" />, route: "/cart" },
  { title: "Guest", icon: <CollectionsBookmarkIcon sx={{ fontSize: 60 }} color="secondary" />, route: "/guest" },
];

export const UserHomePage = () => {
  const cart = useSelector((state) => state);
  const navigate = useNavigate();

  return (
    <div style={{ maxWidth: 1300.250, maxHeight: 600.297, margin: 'auto' }}>
      <Grid container spacing={4}>
        <Grid item xs={12}>
            <Card style={{ height: 200, backgroundColor: '#f0f0f0', marginTop: 20 }}>
            <CardMedia
                      component="img"
                      height="440"
                      image={OfficeLayout} >
            </CardMedia>
            </Card>
        </Grid>

        {cards.map((card, index) => (
          <Grid item xs={12} sm={6} md={4} key={index}>
            <Card style={{ display: 'flex', height: 150 }} onClick={() => navigate(card.route)}>
            <CardContent style={{ flex: '1 0 50%' }}>
                <Typography variant="h5" component="div">
                  {card.title}
                </Typography>
              </CardContent>
              <CardMedia style={{ flex: '1 0 50%' }}>
                {card.title === "Cart" ? (
                  <Badge badgeContent={cart.length} color="primary">
                    {card.icon}
                  </Badge>
                ) : (
                  card.icon
                )}
              </CardMedia>
            </Card>
          </Grid>
        ))}
      </Grid>
    </div>
  );
}
