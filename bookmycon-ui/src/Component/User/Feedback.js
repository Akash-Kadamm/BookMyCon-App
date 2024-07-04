import * as React from "react";
import { useNavigate } from "react-router";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Rating from "@mui/material/Rating";
import { FormLabel, TextareaAutosize } from "@mui/material";
import axios from "axios";
import FastfoodIcon from '@mui/icons-material/Fastfood';
import BookmarkAddedIcon from '@mui/icons-material/BookmarkAdded';
import RoomPreferencesIcon from '@mui/icons-material/RoomPreferences';
import { toast } from "react-toastify";
 
 
export default function Feedback() {
  const navigate = useNavigate();
  const userSignIn = JSON.parse(sessionStorage.getItem("userLogin"));
 
  const [value1, setValue1] = React.useState(0);
  const [value2, setValue2] = React.useState(0);
  const [value3, setValue3] = React.useState(0);
  const [remarks, setRemarks] = React.useState("");
 
  const handleSubmit = () => {
    const data = {
      bookingRating: value1,
      snacksRating: value2,
      housekeepingRating: value3,
      remarks: remarks,
      userId: userSignIn.userId,
    };
 
    axios
      .post("http://localhost:8080/ratings/addRating", data, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response.data);
        toast.success("Feedback Sent Successfully");
      })
      .catch((err) => console.log(err + "Incorrect Data"));
 
    toast.error('Feedback Not Sent Successfully');
 
    setValue1(0);
    setValue2(0);
    setValue3(0);
    setRemarks("");
    navigate("/");
  };
 
  return (
    <Container maxWidth="sm">
         <Typography
          variant="h5"
          style={{
            color: "black",
            fontWeight: "bold",
            fontSize:'35px'
          }}
          mt={4}
          className="Feedbackform-text-title"
          gutterBottom
        >
          Feedback Form
        </Typography>
      <Box
        sx={{
          backgroundColor: "#fff",
          padding: 3,
          border:'2px solid',
          borderRadius: 2,
          borderColor:'black',
          boxShadow: 12,
          mt: 3,
        }}
      >
     
        <form
          style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
           
          }}
          onSubmit={handleSubmit}
        >
         
          <FormLabel
              style={{
                color: "black",
                fontWeight: "bold",
                marginTop: 10,
                display: 'flex',
                alignItems: 'center',
              }}
              className="feedback-rating-text-title"
            >
              <FastfoodIcon fontSize="small" style={{ marginRight: 8 }} />
            Snacks Quality
          </FormLabel>
          <Rating
            className="feedback-rating-bar"
            size="large"
           
            value={value1}
            name="snacks"
            style={{marginTop:6,color:'purple'}}
            onChange={(event, newValue) => {
              setValue1(newValue);
            }}
          />
 
          <FormLabel
            style={{
              color: "black",
              fontWeight: "bold",
              marginTop: 14,
              display: 'flex',
              alignItems: 'center',
            }}
            className="feedback-rating-text-title"
          >
             <RoomPreferencesIcon fontSize="small" style={{ marginRight: 8 }} />
            Housekeeping Service
          </FormLabel>
          <Rating
            className="feedback-rating-bar"
            size="large"
            style={{marginTop:6,color:'purple'}}
            name="housekeeping"
            value={value2}
            onChange={(event, newValue) => {
              setValue2(newValue);
            }}
          />
 
          <FormLabel
            style={{
              color: "black",
              fontWeight: "bold",
              marginTop: 14,
              display: 'flex',
              alignItems: 'center',
            }}
            className="feedback-rating-text-title"
          ><BookmarkAddedIcon fontSize="small" style={{ marginRight: 8 }} />
            Ease Of Booking
          </FormLabel>
          <Rating
            className="feedback-rating-bar"
            size="large"
            style={{marginTop:6,color:'purple'}}
            name="booking"
            value={value3}
            onChange={(event, newValue) => {
              setValue3(newValue);
            }}
          />
 
            <TextareaAutosize
             
              placeholder="Enter Your Remark Here..."
              value={remarks}
              onChange={(e) => {
                setRemarks(e.target.value);
              }}
              minRows={4}
              style={{
                width: "60%",
               
                // padding: 4,
                borderRadius: 4,
                marginTop: 18,
 
                fontSize:'14px'
              }}
            />
         
          <Box style={{    width: "60%"}} mt={3}>
            <Button
              onClick={handleSubmit}
              variant="contained"
              color="success"
              type="submit"
              style={{
                width: "100%",
       
               
                fontSize:'15px'
              }}
            >
              Submit
            </Button>
          </Box>
        </form>
      </Box>
    </Container>
  );
}