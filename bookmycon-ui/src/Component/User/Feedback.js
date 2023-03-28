import * as React from 'react';
import { useNavigate } from 'react-router';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';



import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Rating from '@mui/material/Rating';
import axios from 'axios';
import { toast } from "react-toastify";




const theme = createTheme();

export default function Feedback() {

    const navigate = useNavigate();
    const userSignIn = JSON.parse(sessionStorage.getItem("userLogin"))



    const [value1, setValue1] = React.useState(0);
    const [value2, setValue2] = React.useState(0);
    const [value3, setValue3] = React.useState(0);
    const [remarks, setRemarks] = React.useState('')


    const handleSubmit = () => {


        const data = {
            bookingRating: value1,
            snacksRating: value2,
            housekeepingRating: value3,
            remarks: remarks,
            userId: userSignIn.userId

        };

        axios.post("http://localhost:8080/ratings/addRating", data, {
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then((response) => {
                console.log(response.data);
            })
            .catch((err) => console.log(err + "Incorrect Data"));
                toast.success('Feedback Sent Successfully')

            
//            .catch((err) => {
//                toast.error('Feedback Not Sent ')
//
//            });
        
        setValue1(0)
        setValue2(0)
        setValue3(0)
        setRemarks("")
        navigate("/")


    }

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >

                    <Typography component="h1" variant="h3">
                        Feedback Form
                    </Typography>
                    <h4>Please rate for Snacks</h4>
                    <Rating
                        name="simple-controlled"
                        value={value1}
                        sx={{
                            "& .MuiRating-iconFilled": {
                              color: "yellow"
                            },
                            "& .MuiRating-iconHover": {
                              color: "yellow"
                            }
                          }}
                       
                        onChange={(event, newValue) => {
                            setValue1(newValue);
                        }}
                    />
                    <h4>Please rate for booking</h4>
                    <Rating
                        name="simple-controlled"
                        value={value2}
                        sx={{
                            "& .MuiRating-iconFilled": {
                              color: "yellow"
                            },
                            "& .MuiRating-iconHover": {
                              color: "yellow"
                            }
                          }}
                        onChange={(event, newValue) => {
                            setValue2(newValue);
                        }}
                    />
                    <h4>Please rate for housekeeping</h4>
                    <Rating
                        name="simple-controlled"
                        value={value3}
                        sx={{
                            "& .MuiRating-iconFilled": {
                              color: "yellow"
                            },
                            "& .MuiRating-iconHover": {
                              color: "yellow"
                            }
                          }}
                        onChange={(event, newValue) => {
                            setValue3(newValue);
                        }}
                    />

                    <TextField
                        margin="normal"
                        value={remarks}
                        fullWidth
                        name="remarks"
                        label="Remarks"
                        type="text"
                        id="remarks"
                        onChange={e => { setRemarks(e.target.value) }}

                    />

                    <Button
                        onClick={handleSubmit}
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                    >
                        Submit
                    </Button>
                    <Grid container>
                        <Grid item xs>

                        </Grid>
                    </Grid>
                </Box>


            </Container>
        </ThemeProvider>
    );
}