import React from 'react'
import { useState } from 'react';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Select  from 'react-select';
import './PageByUser.css'
import { toast } from "react-toastify";
import axios from "axios";

export default function PageByUser() {
  
  const [value,setValue]=useState(null)
  
    const options=[
      {value:"User 1" ,label:"27731"},
      {value:"User 2" ,label:"27732"},
      {value:"User 3" ,label:" 27733"},
      {value:"User 4" ,label:"27734"}  
    ]

    const submit=(data)=>{
      console.log(data)
      axios
      .post("http://localhost:8080/users/migrateUsers", data, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response)=>{
        console.log(response.data);
        
        
      })
    }

    
  return (
    
    <div className='PagesByUser'>
      
    
      <div className='text-container'>
          
        <Grid item xs={4}>
          
          <Card sx={{ minWidth:10 +'%' ,height:250 ,backgroundColor:" rgb(190, 233, 219);" }}>
      <CardContent>
        <Typography sx={{ fontSize: 22 , textAlign:'center' }} color="text.secondary" gutterBottom>
        Data migration using ID involves moving data from one
        system to another while preserving unique identifiers,
        using them as keys to match data between systems..A data migration service can supplement your in-house capabilities or manage the entire migration process from strategy through completion, testing, and documentation. The latter type of service—often referred to as “white glove data migration service”—is more expensive.

        </Typography>
      </CardContent>
      <CardActions>
      
      </CardActions>
    </Card>
        </Grid>
      

      <div className='corosell'  >
              <div classsname="dropBtn" style={{margin:20 , width:400}}>
              <Select options={options} defaultValue={value} placeholder="Search UserID" 
              onChange={setValue}
              isSearchable></Select>
      

      <Button>
      <input type="submit" id="submit-bt2" value="Search" onClick={submit()}/></Button>
</div>
        

        </div>
        </div>
       
       





    </div>
  )
}
