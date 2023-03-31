import React, { useState, useEffect } from "react";
import "../../../src/App.css";
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
import BookMeeting from "./BookMeeting";
import { borderRadius } from "@mui/system";

ReactSession.setStoreType("localStorage");
export const UserFloorMap = () => {
  const navigate = useNavigate();
  const [msg, setMsg] = useState(null);
  const [hoveredArea, setHoveredArea] = useState(null);
  const [moveMsg, setMoveMsg] = useState(null);
  let [errorMsg, setErrorMsg] = useState('');

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

  const addAreas = () => {
    let data = {
      name: "11",
      shape: "poly",
      coords: checkCoords,
      preFillColor: "green",
      fillColor: "black",
      nameId: "UserLayout"
    }
    axios.post("http://localhost:8080/userLayout/addareas", data,
      {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response.data);

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


    ReactSession.set("auditoriumName", area.name);
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
      .then((response) => {
        setUserLocation(response.data.auditoriumLocation);
        setUserType(response.data.auditoriumType);
        setUserCapacity(response.data.auditoriumCapacity);
        setUserAmenities(response.data.auditoriumAminity);
      })
    setTextData(area.name)
  };

  const leaveArea = (area) => {
    setHoveredArea(null);
    setMsg(
      `You leaved ${area.shape} ${area.name} at coords ${JSON.stringify(
        area.coords
      )} !`
    );
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
    const userLayoutCopy = { ...userLayout, areas: areasCopy };
    setUserLayoutCoords(userLayoutCopy);
  };



  const resetHandler = () => {
    const areasCopy = [...userLayout.areas];
    areasCopy[0].coords = [];
    const userLayoutCopy = { ...userLayout, areas: areasCopy };
    setUserLayoutCoords(userLayoutCopy);
    setDots([]);
  };


  const addPolygon = (evt) => {
    const coords = { x: evt.nativeEvent.layerX, y: evt.nativeEvent.layerY };
    const areasCopy = { ...userLayout.areas }

    let obj = {
      name: "11",
      shape: "poly",
      coords: areasCopy[0].coords,
      preFillColor: "green",
      fillColor: "black",
    }

    setCoords(areasCopy[0].coords);
    userLayout.areas.push(obj);
    addAreas();
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

  useEffect(() => {
    getAllAreas();

  }, [reset]);


  const getAllAreas = () => {
    axios
      .get("http://localhost:8080/userLayout/areasList")
      .then(response => setUserLayout((prevState) => ({
        ...prevState,
        areas: [...userLayout.areas, ...response.data]
      })))
      .catch((error => setErrorMsg("error ")));

  }


  const handleAllAreas = () => {
    const areasCopy = [...userLayout.areas];
    debugger;
    const userLayoutCopy = { ...userLayout, areas: areasCopy };
    setUserLayout((prevState) => ({
      ...prevState,
      areas: [...areaslist]
    }));
  }
  const text = "Name: " + textData + "\n" +
    "Location: " + userLocation + "\n" +
    "Type: " + userType + "\n" +
    "Amenities: " + userAmenities + "\n" +
    "Capacity: " + userCapacity;


  return (

    <div>
      <Box sx={{ flexGrow: 1 }}>
        <Grid container spacing={5}>
          <Grid item xs={8}>
            <h2>Floor Map</h2>
            <div style={{ marginTop: "20px", marginLeft: "60px" }}>
              <ImageMapper
                src={a1}
                map={userLayout}
                width={700}
                onImageClick={(evt) => clickedOutside(evt)}
                onImageMouseMove={(evt) => moveOnImage(evt)}
                onClick={(area) => clicked(area)}
                onMouseEnter={(area) => enterArea(area)}
                onMouseLeave={(area) => leaveArea(area)}
              />
            </div>
          </Grid>
          <Grid item xs={4}>
            <div style={{
              marginTop: "75px",
              backgroundColor: "#85d1f4",
              textDecorationColor: "red",
              marginLeft: "40px",
              borderRadius: "25px",
              fontSize: "20px",
              borderWidth: "2px",
              border: "solid",
              padding: "20px",
              width: "300px",
              height: "250px"
            }} >

              Name: {textData}
              <br></br>
              Location: {userLocation}<br></br>
              Type: {userType}<br></br>
              Amenities: {userAmenities}<br></br>
              Capacity: {userCapacity}
            </div>

          </Grid>

        </Grid>
      </Box>
    </div>
  )
}
