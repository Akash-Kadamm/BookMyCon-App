import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Link from '@mui/material/Link';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useForm } from "react-hook-form";
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { json, useParams } from 'react-router';
import "./UpdateAuditorium.css";
const theme = createTheme();

export default function UpdateAuditorium() {

   
    const params = useParams();
    const { register, handleSubmit, errors } = useForm()
   
     const navigate = useNavigate();

    const[auditorium,setAuditorium]=useState();
    const [message, setMessage] = useState("");
    
    useEffect(()=>{
        getAuditoriumDetails();
    },[]);

    const getAuditoriumDetails=()=> {
        axios
        .get(`http://localhost:8080/admin/getAudi/${params.id}`)
        .then(response =>{ 
            setAuditorium(response.data.Auditorium);
        console.log(response.data.Auditorium);
   
       //  console.log(auditorium);
        
    }).catch((error=>setMessage("error occered ")));
    }
    const onSubmit = data => {
        console.log(data);

        axios.put(`http://localhost:8080/admin/${params.id}`, data, { headers: { "Content-Type": "application/json", }, })
            .then((response) => {
                toast.success(response.data);
               
                navigate('/auditorium-list');

            })
            .catch((err) => {
                console.log(err.response);


            });


    };


    return (<>
{auditorium!=null ?
        <div id='update-div'>
            <ThemeProvider theme={theme}>
                <Container component="main" maxWidth="xs">
                    <CssBaseline />

                    <Box id="update-card"
                        sx={{
                            
                            marginTop: 8,
                            display: 'flex',
                            flexDirection: 'column',
                            alignItems: 'center',
                        }}
                    >
                       
                        <Typography component="h1" variant="h5" sx={{ color: 'secondary.main' }}>
                            Update  Auditorium Details
                        </Typography>
                        <Box component="form" noValidate onSubmit={handleSubmit(onSubmit)} sx={{ mt: 1, width: '80%' }}>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    
                                    autoComplete="given-name"
                                    name="Auditorium ID"
                                    readOnly
                                    fullWidth
                                    value={auditorium.auditoriumId}
                                    id="auditoriumId"
                                    label="Auditorium ID"
                                    autoFocus
                                    
                                    {...register("auditoriumId",{required:true})}

                                />

                            </Grid><br />
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="given-name"
                                    name="auditoriumName"
                                    required
                                    fullWidth
                                    defaultValue={auditorium.auditoriumName}
                                    id="auditoriumName"
                                    label="Auditorium Name"
                                    autoFocus
                                    
                                    {...register("auditoriumName",{required:true})}

                                />

                            </Grid>
                            <br />
                            <Grid item xm={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    id="auditoriumLocation"
                                    label="Auditorium Location"
                                    name="auditoriumLocation"
                                defaultValue={auditorium.auditoriumLocation}
                                    autoComplete="family-name"
                                    
                                    {...register("auditoriumLocation",{required:true})}

                                />

                            </Grid>
                            <br />
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    type="number"
                                    id="auditoriumCapacity"
                                    label="Auditorium Capacity"
                                    defaultValue={auditorium.auditoriumCapacity}
                                    name="auditoriumCapacity"
                                    autoComplete="family-name"
                                    
                                    {...register("auditoriumCapacity",{required:true})}

                                /> </Grid>
                            <br />
                            

                            <Button
                            id='btn-update'
                                type="submit"
                                fullWidth
                                variant="contained"
                                color='success'
                                sx={{ mt: 3, mb: 2, color: 'black' }}
                               
                            >
                                Update Auditorium
                            </Button>
                           
                        </Box>
                    </Box>

                </Container>
            </ThemeProvider></div>:null}</>
    );
}