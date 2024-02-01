import React, { useState, useEffect } from "react";
import "../../../src/App.css";
import { Link } from 'react-router-dom';
import ImageMapper from "react-image-mapper";
import a1 from "../../Image/OfficePlan.jpg"
import Box from '@mui/material/Box';
import Popper from '@mui/material/Popper';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import Fade from '@mui/material/Fade';
import Paper from '@mui/material/Paper';
import { getOverlayAlpha } from "@mui/material";
import { styled } from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import Popover from '@mui/material/Popover';
import { ReactSession } from 'react-client-session';
import { useNavigate } from 'react-router-dom';
import axios from 'axios'
import BookMeeting from "../User/BookMeeting";
import { borderRadius } from "@mui/system";
ReactSession.setStoreType("localStorage");
export const UserFloorMap = () => {
  const navigate = useNavigate();
  const [msg, setMsg] = useState(null);
    const [hoveredArea, setHoveredArea] = useState(null);
    const [moveMsg, setMoveMsg] = useState(null);
     let[errorMsg,setErrorMsg]=useState('');
   
    const [reset, setReset] = useState(false);
  
    const [textData, setTextData] = useState("none");
    const [userLocation, setUserLocation] = useState("none");
    const [userType, setUserType] = useState("none");
    const [userCapacity, setUserCapacity] = useState(0);
    const [userAmenities, setUserAmenities] = useState("none");
    const [dots, setDots] = useState([]);
  
    const [anchorEl, setAnchorEl] = React.useState(null);
   
   
   
 
    const [checkCoords, setCoords] = useState();
  
    const [checkNameId, setNameId] = useState('');
   
   
   
   
     const [areaslist, setAreaslist] = useState([]);
  

    const addAreas=()=>{
   
     let data={
   
      name: "11",
      shape: "poly",
      coords: checkCoords,
      preFillColor: "green",
      fillColor: "black",
      nameId:"UserLayout"
     }
 
  
  
      axios.post("http://localhost:8080/userLayout/addareas", data,
      {
          headers: {
              "Content-Type": "application/json",
          },
      })
      .then((response) => {
          console.log(response.data);
          // Navigate("/adminDashboard/viewallflight")
  
      }).catch((err) => console.log(err + "Incorrect Data"));
  
    }
  
  
  
  
  
    const handleClick = (event) => {
  
      
      setAnchorEl(true);
      
    };
  
    const handleClose = () => {
      setAnchorEl(null);
    };
  
    const open = Boolean(anchorEl);
    const id = open ? 'simple-popover' : undefined;
  

    let adminLayout = {
      name: "dots",
      areas: dots
    };
  
    const [userLayoutCoords, setUserLayoutCoords] = useState([]);
   
   
    const [userLayout, setUserLayout] = useState({
      name: "userLayout",
      areas: [
      
        {
          name: "1",
          shape: "poly",
          coords: userLayoutCoords,
          // preFillColor: "green",
          fillColor: "blue"
        },
     
      ]
    });
  
    var URL = "";
  
    const load = () => {
      setMsg("Interact with image !");
    };
  
    const clicked = (area) => {
      setMsg(
        `You clicked on ${area.shape} ${area.name} at coords ${JSON.stringify(
          area.coords
        )} !`
      );
 
 // alert(`I am alert, nice to meet you+${area.name}`+  <button onClick={() => resetHandler()}>Reset</button>);
  //     

  console.log(`Click on Area:`+` ${area.name}`)

  ReactSession.set("auditoriumName",area.name);
  navigate('/auditorium-Booking');

    };
  
    const clickedOutside = (evt) => {
     
    };
  
    const moveOnImage = (evt) => {
      const coords = { x: evt.nativeEvent.layerX, y: evt.nativeEvent.layerY };
      setMoveMsg(`You moved on the image at coords ${JSON.stringify(coords)} !`);

    };
  
    const enterArea = (area) => {
      setHoveredArea(area);
      setMsg(
        `You entered ${area.shape} ${area.name} at coords ${JSON.stringify(
          area.coords
        )} !`
      );



      
      axios

      .get(`http://localhost:8080/admin/getAuditoriunByName/${area.name}`)
      .then((response )=>
      {
        console.log(response.data.auditoriumName+"---Audi name");
      
        setUserLocation(response.data.auditoriumLocation);
        setUserType(response.data.auditoriumType);
        setUserCapacity(response.data.auditoriumCapacity);
        setUserAmenities(response.data.auditoriumAminity);
      })


      console.log("nter in area"+area.name)
      setTextData(area.name)
   
      console.log("text Data: ---"+area.name)
   
   
   
    };
  
    const leaveArea = (area) => {
      setHoveredArea(null);
      setMsg(
        `You leaved ${area.shape} ${area.name} at coords ${JSON.stringify(
          area.coords
        )} !`
      );
      console.log("Leave area")
   
    };
  
    const moveOnArea = (area, evt) => {
      const coords = { x: evt.nativeEvent.layerX, y: evt.nativeEvent.layerY };
      setMoveMsg(
        "You moved on " +
          area.shape +
          " " +
          area.name +
          ' at coords {"x":' +
          coords.x +
          ',"y":' +
          coords.y +
          "} !"
      );
    };
  
    const getTipPosition = (area) => {
      return { top: `${area.center[1]}px`, left: `${area.center[0]}px` };
    };
  
    const makeDot = (evt) => {
      const coords = { x: evt.nativeEvent.layerX, y: evt.nativeEvent.layerY };
      setDots((oldArray) => [
        ...oldArray,
        {
          name: "1",
          shape: "circle",
          coords: [coords.x, coords.y, 3],
          preFillColor: "black",
          lineWidth: 11
        }
      ]);
      drawPolygon(coords.x, coords.y);
    };
  
    const drawPolygon = (x, y) => {
      const areasCopy = [...userLayout.areas];
      areasCopy[0].coords.push(x);
      areasCopy[0].coords.push(y);
    console.log("coords 1")
      const userLayoutCopy = { ...userLayout, areas: areasCopy };
      setUserLayoutCoords(userLayoutCopy);
  console.log(userLayout)
  
    };
  
  
  
    const resetHandler = () => {
      const areasCopy = [...userLayout.areas];
      areasCopy[0].coords = [];
  
      const userLayoutCopy = { ...userLayout, areas: areasCopy };
      setUserLayoutCoords(userLayoutCopy);
  
      setDots([]);
    };
  
  
    const addPolygon=(evt)=>{
    const coords = { x: evt.nativeEvent.layerX, y: evt.nativeEvent.layerY };

    console.log(userLayout)
    const areasCopy={...userLayout.areas}
   
     let obj={ name: "11",
      shape: "poly",
      coords:areasCopy[0].coords,
       // preFillColor: "transperant",
       preFillColor: "green",
       fillColor: "black",
      //  userLayout:checkUserLay,
      }
      // setName("UserLayout2")
  
      console.log(obj)
     
        console.log("out :")
   
  
   
        console.log("in")
        
        setCoords(areasCopy[0].coords);
        userLayout.areas.push(obj);
        addAreas();
    
       
    
      console.log("UserLayout :"+userLayout)
  
    resetHandlerForAddPolygon();
  
    }
    const resetHandlerForAddPolygon = () => {
      const areasCopy = [...userLayout.areas];
      areasCopy[0].coords = [];

      const userLayoutCopy = { ...userLayout, areas: areasCopy };
      setUserLayoutCoords(userLayoutCopy);
    setUserLayout(userLayoutCopy)
      setDots([]);
    };
 
    useEffect(()=>
    {
      getAllAreas();
      //  handleAllAreas();
  
    }, [reset]);
  
  
    const getAllAreas=()=>{
      axios

        .get("http://localhost:8080/userLayout/areasList")
        .then(response =>setUserLayout((prevState)=>({
          ...prevState,
          areas:[...userLayout.areas,...response.data]            
        })))
  
        .catch((error=>setErrorMsg("error ")));
      
    }
  
  
    const handleAllAreas=()=>{
      const areasCopy = [...userLayout.areas];
  
     debugger;
     const userLayoutCopy = { ...userLayout, areas: areasCopy };
     setUserLayout((prevState)=>({
       ...prevState,
       areas:[...areaslist]
      }));
   
        console.log("hi")
        
      }
      // console.log(areaslist);
      // console.log("UserLayout :"+ JSON.stringify(userLayout))
      
      
    // console.log("areaList :"+ JSON.stringify(areaslist))
    const text="Nam,e: "+textData+"\n"+
    "Location: "+userLocation+"\n"+
    "Type: "+userType+"\n"+
    "Amenities: "+userAmenities+"\n"+
    "Capacity: "+userCapacity;
    console.log(text)
  return (
 
<div>
  
<Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={5}>
        <Grid item xs={8}>
        <h2>Floor Map</h2>
     

        
      <div  style={{marginTop:"20px"}}>
       <ImageMapper
       
       src={a1}
       map={userLayout}
       
       width={500}
      onImageClick={(evt) => clickedOutside(evt)}
      onImageMouseMove={(evt) => moveOnImage(evt)}
   // onClick={(evt) => handleClick(evt)}
  //  onClick={(evt) => clicked(evt)}
      onClick={(area) => clicked(area)}
     
  //    onClick={(area) => openModal(area)}
   //  onClick={(area) => handleClick(area.name)}
        onMouseEnter={(area) => enterArea(area)}
        onMouseLeave={(area) => leaveArea(area)}
        />
        </div>
        </Grid>
        <Grid item xs={4}>
       {/* <BookMeeting/> */}
       <div style={{marginTop:"75px",
       backgroundColor:"#85d1f4",
      textDecorationColor:"red",
      marginLeft:"0px",
      borderRadius: "25px",
      fontSize:"15px",
      borderWidth:"2px", 
      border:"solid",
      padding: "20px",
      width: "200px",
      height: "250px"}} >
       
       <Typography variant="subtitle1">
                Name: {textData}
              </Typography>
              <Typography variant="subtitle1">
                Location: {userLocation}
              </Typography>
              <Typography variant="subtitle1">
                Type: {userType}
              </Typography>
              <Typography variant="subtitle1">
                Amenities: {userAmenities}
              </Typography>
              <Typography variant="subtitle1">
                Capacity: {userCapacity}
              </Typography>
       </div>

        </Grid>
      
      </Grid>

    
     
     <Grid container spacing={2} direction="column" alignItems="center" justifyContent="center" style={{ minHeight: '0vh' }}>
          <Grid item style={{ marginLeft: '30vh' }}>
            <Button component={Link} to="/floor" variant="contained" color="primary">
              Watch Table
            </Button>
          </Grid>
        </Grid>
        <Grid item style={{ marginLeft: '112vh' }}>
          <Button component={Link} to="/floormain" variant="contained" color="secondary" >
            Back
          </Button>
        </Grid>
    </Box>

    
</div>
);
}
 export default UserFloorMap;