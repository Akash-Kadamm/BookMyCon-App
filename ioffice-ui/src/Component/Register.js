
import { Container, Typography, Box, TextField, Button } from '@mui/material'
import React, { useState } from 'react'
import axios from 'axios';
// import { useNavigate } from 'react-router-dom';

function Register() {

    const [userName, setUserName] = useState('')
    const [userEmail, setUserEmail] = useState('')
    const [userPassword, setUserPassword] = useState('')
    const [userContact, setUserContact] = useState('')
    // const [phoneNo, setMobileNo] = useState('')
    
    // const navigate = useNavigate()

    const styles = {
        // paperContainer: {
        //     backgroundImage: `url(https://th.bing.com/th/id/OIP.3LtxHFr92ZmO_qB5-t4SJgHaEo?pid=ImgDet&rs=1)`,
        //     backgroundRepeat: "no-repeat",
        //     backgroundSize: "cover",
        // },

        Container: {
            backgroundImage: `url(https://images.unsplash.com/photo-1563291074-2bf8677ac0e5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=707&q=80)`,
            backgroundRepeat: "no-repeat",
            backgroundSize: "cover",
        }
    };

    const resgister = () => {
        const body = { userName, userEmail, userPassword,userContact , role: "user" }

        axios.post("http://localhost:8080/user/registration", body)
          .then(response => {
            console.log(response)
          })
          .catch(error => console.log(error));
    }

    return (
        <body style={styles.paperContainer}>
            <Box component='form' pt={10}>
                <Container maxWidth='sm' style={styles.Container} sx={{ pb: 4 }}>
                    <Typography varient="h1" sx={{ pt: 3, fontSize: 35 }} >Registration</Typography>

                    <TextField label='Name' name='userName' fullWidth required margin='normal' onChange={(event) => setUserName(event.target.value)} ></TextField>

                    <TextField label='Email' name='userEmail' fullWidth required margin='normal' onChange={(event) => setUserEmail(event.target.value)}></TextField>
                   
                    <TextField type={"password"} label='Password' name='userPassword' fullWidth required margin='normal' onChange={(event) => setUserPassword(event.target.value)}></TextField>

                    <TextField label='Contact' name='userContact' fullWidth required margin='normal' onChange={(event) => setUserContact(event.target.value)}></TextField>


                    <br /><br></br>
                    <Button type='submit' variant="contained" color="primary" fullWidth onClick={() => resgister()}>Submit</Button>

                </Container>
            </Box>
        </body>
    )
}

export default Register
