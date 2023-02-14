import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Rating from '@mui/material/Rating';

function Copyright(props) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit" href="https://mui.com/">
                Your Website
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const theme = createTheme();

export default function Feedback() {
    const userSignIn = JSON.parse(sessionStorage.getItem("userLogin"))

    const [value1, setValue1] = React.useState(0);
    const [value2, setValue2] = React.useState(0);
    const [value3, setValue3] = React.useState(0);
    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        console.log({
            email: data.get('email'),
            password: data.get('password'),
        });
    };

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

                    <Typography component="h1" variant="h5">
                        <h1> Feedback Form</h1>
                    </Typography>
                    <h3>Please rate for Snacks</h3>
                    <Rating
                        name="simple-controlled"
                        value={value1}
                        onChange={(event, newValue) => {
                            setValue1(newValue);
                        }}
                    />
                    <h3>Please rate for booking</h3>
                    <Rating
                        name="simple-controlled"
                        value={value2}
                        onChange={(event, newValue) => {
                            setValue2(newValue);
                        }}
                    />
                    <h3>Please rate for housekeeping</h3>
                    <Rating
                        name="simple-controlled"
                        value={value3}
                        onChange={(event, newValue) => {
                            setValue3(newValue);
                        }}
                    />

                    <TextField
                        margin="normal"

                        fullWidth
                        name="remarks"
                        label="Remarks"
                        type="text"
                        id="remarks"

                    />

                    <Button
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