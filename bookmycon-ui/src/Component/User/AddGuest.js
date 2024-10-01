import React, { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import { Typography, TextField, Button, Container, Divider } from '@mui/material';
import { toast } from "react-toastify";

const AddGuest = () => {
    const users = {
        userId: 1,
        userName: "",
        userEmail: "",
        userPassword: "",
        userRole: "",
        userContact: "",
        thumbnail: ""
    };

    const [guestName, setGuestName] = useState('');
    const [guestCompany, setguestCompany] = useState('');
    const [guestMobileNo, setguestMobileNo] = useState('');
    const [guestEmail, setGuestEmail] = useState('');
    const [thumbnail, setThumbnail] = useState(undefined);
    const [error, setError] = useState('');
    const [errorResponse, setErrorResponse] = useState('');
    const [emailError, setEmailError] = useState('');
    const [mobileError, setMobileError] = useState('');
    const [nameError, setNameError] = useState('');
    const navigate = useNavigate();

    const onFileSelect = (event) => {
        setThumbnail(event.target.files[0]);
    };

    const toGuest = () => {
        navigate("/guest");
    };

    const validateEmail = (email) => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    };

    const validateMobileNo = (mobileNo) => {
        const mobileRegex = /^[0-9]{10}$/;
        return mobileRegex.test(mobileNo);
    };

    const validateGuestName = (name) => {
        const nameRegex = /^[A-Za-z\s]+$/;
        return nameRegex.test(name);
    };

    const addGuest = () => {
        if (!validateGuestName(guestName)) {
            setNameError('Guest name must contain alphabets only');
            return;
        }

        if (!validateEmail(guestEmail)) {
            setEmailError('Please enter a valid email address');
            return;
        }

        if (!validateMobileNo(guestMobileNo)) {
            setMobileError('Please enter a valid 10-digit mobile number');
            return;
        }

        if (thumbnail && guestName && guestCompany && guestMobileNo && guestEmail && users) {
            const data = new FormData();
            data.append('thumbnail', thumbnail);
            data.append('guestName', guestName);
            data.append('guestCompany', guestCompany);
            data.append('guestMobileNo', guestMobileNo);
            data.append('guestEmail', guestEmail);
            data.append('users', JSON.stringify(users));

            axios.post('http://localhost:8080/guest/addGuest', data)
                .then((response) => {
                    toast.success("Guest Added Successfully");
                    setErrorResponse("");
                    navigate("/guest");
                })
                .catch((error) => {
                    console.error("Error adding guest:", error.response?.data || error.message);
                    setErrorResponse("Failed to add guest: " + (error.response?.data || "Unknown error"));
                    toast.error("Failed to add guest: " + (error.response?.data || "Unknown error"));
                });
        } else {
            setError('Some required fields are missing');
        }
    };

    return (
        <Container className="text-center" maxWidth='md' sx={{ pb: 4 }}>
            <span className="h3 text-danger">{error}</span>
            <Typography variant="h1" sx={{ pt: 3, fontSize: 35 }} color={"rebeccapurple"} > Add Guest</Typography>
            <Divider />
            <TextField label='Guest Name' name='name' fullWidth required margin='normal' error={!!nameError} helperText={nameError} onChange={(event) => {
                setGuestName(event.target.value);
                setNameError(''); // Reset name error when user starts typing
            }}></TextField>

            <TextField label='Company Name' name='company name' fullWidth required margin='normal' onChange={(event) => {
                setguestCompany(event.target.value);
            }}></TextField>

            <TextField label='Email' name='Email' fullWidth required margin='normal' error={!!emailError} helperText={emailError} onChange={(event) => {
                setGuestEmail(event.target.value);
                setEmailError(''); // Reset email error when user starts typing
            }}></TextField>

            <TextField label='Mobile No' name='Mobile No' type='text' fullWidth required margin='normal' error={!!mobileError} helperText={mobileError} value={guestMobileNo} 
                onChange={(event) => {
                    const value = event.target.value;
                    if (/^\d*$/.test(value) && value.length <= 10) {
                        setguestMobileNo(value);
                    }
                    setMobileError(''); // Reset mobile error when user starts typing
                }}>
            </TextField>

            <TextField name='Thumbnail' accept="image/*" type={"file"} fullWidth required margin='normal' onChange={onFileSelect}></TextField>

            <Button id="succesBtn" variant="contained" onClick={() => addGuest()} color="secondary"> Add</Button>

            <Button variant="contained" color="warning" sx={{ m: 2 }} onClick={() => toGuest()}>Back</Button>
        </Container>
    );
}

export default AddGuest;
