import React, { useEffect, useState } from 'react'
import { Button } from '@mui/material';
import { Link } from 'react-router-dom'
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardMedia from '@material-ui/core/CardMedia';

const styles = {
    card: {
      maxWidth: 200,
    },
    media: {
      height: 150,
    },
  };

const AdminDashboard = (props) => {
    const { classes } = props;
    return (
        <div>
             <h1>Welcome Admin</h1>
            <hr/>

<div class="row">
  <div class="col-sm-3 m-4">
    <Card className={classes.card}>
      <CardActionArea>
        <CardMedia
          className={classes.media}
          image="https://cdn.vectorstock.com/i/1000x1000/10/38/businesspeople-character-avatar-icon-vector-13871038.webp"
          title="Contemplative Reptile"
        />
      </CardActionArea>
      <CardActions>
      <Link to={"/user_details"}><Button variant="contained" color="secondary">User</Button></Link>
      </CardActions>
    </Card>
    </div>

    <div class="col-sm-3 m-4">
    <Card className={classes.card}>
      <CardActionArea>
        <CardMedia
          className={classes.media}
          image="https://st2.depositphotos.com/3591429/6010/i/450/depositphotos_60107387-stock-photo-man-booking-hotel-reservation-on.jpg"
          title="Contemplative Reptile"
        />
      </CardActionArea>
      <CardActions>
      <Link to={"/all_booking"}><Button variant="contained" color="secondary">All Booking</Button></Link>
      </CardActions>
    </Card>
    </div>

    <div class="col-sm-3 m-4">
    <Card className={classes.card}>
      <CardActionArea>
        <CardMedia
          className={classes.media}
          image="https://images.pexels.com/photos/260922/pexels-photo-260922.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          title="Contemplative Reptile"
        />
      </CardActionArea>
      <CardActions>
      <Link to={"/all_vendors"}><Button variant="contained" color="secondary">Vendors</Button></Link>
      </CardActions>
    </Card>
    </div>

</div>
    
        </div>
    )
}

AdminDashboard.propTypes = {
    classes: PropTypes.object.isRequired,
  };

export default  withStyles(styles)(AdminDashboard)
