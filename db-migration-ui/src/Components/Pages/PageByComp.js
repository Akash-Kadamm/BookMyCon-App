import React, { useState } from 'react'
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Select  from 'react-select';
import './PageByComp.css'
import { toast } from "react-toastify";
import axios from "axios";

export default function PageByComp() {
  
  const [value,setValue]=useState(null)
  
    const options=[
      {value:"Cybage" ,label:"Cybage PVT Ltd"},
      {value:"Cybage 1" ,label:"Cybage PVT Ltd 1"},
      {value:"Cybage 2" ,label:"Cybage PVT Ltd 2"},
      {value:"Cybage 3" ,label:"Cybage PVT Ltd 3"}
        
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
    
    <div className='PagesByComp'>
     
      <div className='text-container'>
          
        <Grid item xs={4}>
          
          <Card sx={{ minWidth:10 +'%' ,height:250 ,backgroundColor:" rgb(190, 233, 219);" }}>
      <CardContent>
        <Typography sx={{ fontSize: 22 , textAlign:'center' }} color="text.secondary" gutterBottom>
        Data migration using Company Name involves moving data from one
        system or storage location to another. When using the company name as a data migration method, data is organized and transferred according to the company's structure and hierarchy. 
        This ensures that data is migrated to the appropriate location and accessible by the right people.

        </Typography>
      </CardContent>
      <CardActions>
      
      </CardActions>
    </Card>
        </Grid>
      

      <div className='corosel'  >
              <div classsname="dropButton" style={{margin:20 , width:400}}>
              <Select options={options} defaultValue={value} placeholder="Select Companey Name  " 
              onChange={setValue}
              isSearchable></Select>
      {/* </div> */}

      <Button>
      <input type="submit" id="submit-bt2" value="Search" onClick={submit(options.label)}/></Button>
</div>
        

        </div>
        </div>
       
       





    </div>
  )
}
