import React from 'react';
import { Card, CardContent, CardMedia, Button, Grid, Typography } from '@mui/material';
import { makeStyles } from '@material-ui/core';
import { Link } from 'react-router-dom';
import "../../../src/App.css";

const useStyles = makeStyles({
  root: {
    maxWidth: 345,
  },
  media: {
    height: 140,
  },
  
});

const cardsData = [
  {
    id: 1,
    imageSrc:'https://img.freepik.com/free-vector/office-interior-top-view_1284-6525.jpg?size=626&ext=jpg&ga=GA1.1.632798143.1705363200&semt=ais', 
    buttonText: 'Check',
    title: 'Pune',
    to:'/floormap'
  },
  {
    id: 2,
    imageSrc: 'https://images.edrawmax.com/images/knowledge/office-layout-1-office-layout-example.jpg', 
    buttonText: 'Check',
    title:'Gandhinagar',
    to:'/floormap'
  },
  {
    id: 3,
    imageSrc: 'https://images.edrawmax.com/examples/office-layout-examples/office-layout-example-2.jpg', 
    buttonText: 'Check',
    title:'Hyderabad',
    to:'/floormap'
  },
];

const ResponsiveCard = ({ imageSrc, buttonText,title, to }) => {
  const classes = useStyles();
 

  return (
   
    <Card className={classes.root}>
      <CardMedia
        component="img"
        height="140"
        image={imageSrc}
        className={classes.media}
      />
      <CardContent>
        <Typography variant="h6" gutterBottom>
          {title}
        </Typography>
        <Button variant="contained" color="primary"  component={Link} to={to}>
          {buttonText}
        </Button>
      </CardContent>
    </Card>
  );
};

const UserFloorCards = () => {
  return (
    <Grid container spacing={3} style={{ marginTop: 20 }}>
      {cardsData.map((card) => (
        <Grid key={card.id} item xs={12} sm={6} md={4}>
          <ResponsiveCard imageSrc={card.imageSrc} buttonText={card.buttonText} title={card.title} to={card.to}
 />
        </Grid>
      ))}
    </Grid>
  ); 
};

export default UserFloorCards;
