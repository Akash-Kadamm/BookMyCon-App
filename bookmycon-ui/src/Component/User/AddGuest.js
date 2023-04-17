import React, { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import { Typography, TextField, Button, Container, Divider } from '@mui/material';
import { toast } from "react-toastify";

const AddGuest = () => {

    const [guestName, setGuestName] = useState('')
    const [guestCompany, setguestCompany] = useState('')
    const [guestMobileNo, setguestMobileNo] = useState('')
    const [guestEmail, setGuestEmail] = useState('')
    const [thumbnail, setThumbnail] = useState(undefined)
    const [error, setError] = useState('');
    const [errorResponse, setErrorResponse] = useState('');
    const navigate = useNavigate()
    const users = useState(JSON.parse(sessionStorage.getItem("userLogin")))
    const onFileSelect = (event) => {
        setThumbnail(event.target.files[0])
    }

    const toGuest = () => {
        navigate("/guest")
    }

    const addGuest = () => {

        if (thumbnail && guestName && guestCompany && guestMobileNo && guestEmail && users) {
            const data = new FormData()
            data.append('thumbnail', thumbnail)
            data.append('guestName', guestName)
            data.append('guestCompany', guestCompany)
            data.append('guestMobileNo', guestMobileNo)
            data.append('guestEmail', guestEmail)
            data.append('users' , users)
console.log(users)
            axios.post('http://localhost:8080/guest/addGuest', data)
                .then((response) => {
                    toast.success("Guest Added Successfully");
                    setErrorResponse(" ")
                    navigate("/guest")
                })
                .catch((error) => {
                    setErrorResponse(error.data)
                    alert(errorResponse)
                })
        } else {
            setError('something is missing')
        }
    }

    return (
        <Container className-="text-center" maxWidth='md' sx={{ pb: 4 }}>
            <span className="h3 text-danger">{error}</span>
            <Typography varient="h1" sx={{ pt: 3, fontSize: 35 }} color={"rebeccapurple"} > Add Guest</Typography>
            <Divider />
            <TextField label='Guest Name' name='name' fullWidth required margin='normal' onChange={(event) => {
                setGuestName(event.target.value)
            }}></TextField>

            <TextField label='Company Name' name='company name' fullWidth required margin='normal' onChange={(event) => {
                setguestCompany(event.target.value)
            }}></TextField>

            <TextField label='Email' name='Email' fullWidth required margin='normal' onChange={(event) => {
                setGuestEmail(event.target.value)
            }}></TextField>

            <TextField label='Number' name='Mobile No' type='number' fullWidth required margin='normal' onChange={(event) => {
                setguestMobileNo(event.target.value)
            }}></TextField>

            <TextField  name='Thumbnail' accept="image/*" type={"file"} fullWidth required margin='normal' onChange={onFileSelect}></TextField>

            <Button id="succesBtn" variant="contained" onClick={() => addGuest()} color="secondary"> Add</Button>

            <Button variant="contained" color="warning" sx={{ m: 2 }} onClick={() => toGuest()}>Back  </Button>
        </Container>
    )
}

export default AddGuest
