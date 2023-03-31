import React, { useState, useEffect } from "react";
import "./App.css";
import ImageMapper from "react-image-mapper";
import a1 from "./plan.png"
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import Popover from '@mui/material/Popover';
import axios from 'axios';

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));


const App1 = (props) => {
  const [msg, setMsg] = useState(null);
  const [hoveredArea, setHoveredArea] = useState(null);
  const [moveMsg, setMoveMsg] = useState(null);
  let [errorMsg, setErrorMsg] = useState('');
  const [reset, setReset] = useState(false);
  const [cords2, setcords2] = useState([]);
  const [dots, setDots] = useState([]);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [checkName, setName] = useState('3');
  const [checkShape, setShape] = useState('poly');
  const [checkCoords, setCoords] = useState();
  const [checkPreFillColor, setPreFillColor] = useState('green');
  const [checkFillColor, setFillColor] = useState('black');
  const [checkNameId, setNameId] = useState('');
  const [areaslist, setAreaslist] = useState([]);
  const [checkUserLay, setUserLay] = useState({ nameId: checkNameId });

  const addAreas = () => {
    let data = {
      name: "11",
      shape: "poly",
      coords: checkCoords,
      preFillColor: "green",
      fillColor: "black",
      nameId: "ak"
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

  const URL = "";

  const load = () => {
    setMsg("Interact with image !");
  };

  const clicked = (area) => {
    setMsg(
      `You clicked on ${area.shape} ${area.name} at coords ${JSON.stringify(
        area.coords
      )} !`
    );

    alert('I am alert, nice to meet you' + <button onClick={() => resetHandler()}>Reset</button>);
    console.log(`Click on Area:` + ` ${area.name}`)
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

    return (

      <div className="grid">
        <div className="presenter">
          <div style={{ position: "relative" }}>

            <h2>AdminLayout</h2>
            <ImageMapper
              src={a1}
              map={adminLayout}
              width={500}
              onImageClick={(evt) => makeDot(evt)}
              onImageMouseMove={(evt) => moveOnImage(evt)}
            />
            <button onClick={() => resetHandler()}>Reset</button>
            <button onClick={(evt) => addPolygon(evt)}>Add polygon</button>

            <h2>userLayout</h2>
            <Popover

              open={open}
              anchorEl={anchorEl}
              onClose={handleClose}
              anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
              }}
            >
              <Typography sx={{ p: 2 }}>The content of the Popover.
                Hi this is from popover<br></br>
                <TextField id="outlined-basic" label="Room Name" variant="outlined" />
                <br></br>
                <br></br>
                <Button variant="contained">Contained</Button>
              </Typography>
            </Popover>

            <ImageMapper
              src={a1}
              map={userLayout}
              width={500}
              onImageClick={(evt) => clickedOutside(evt)}
              onImageMouseMove={(evt) => moveOnImage(evt)}
              onClick={(evt) => clicked(evt)}
              onMouseEnter={(area) => enterArea(area)}
              onMouseLeave={(area) => leaveArea(area)}
            />
          </div>
        </div>
      </div>
    )

  }
}

export default App1;
