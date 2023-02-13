import React, { useState, useEffect } from "react";
import "./App.css";
import ImageMapper from "react-image-mapper";
import a1 from "./plan.png"
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

import axios from 'axios'
// import {Popup } from 'react-leaflet'
const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));

// import Popup from 'react-popup';
const App1 = (props) => {
  const [msg, setMsg] = useState(null);
  const [hoveredArea, setHoveredArea] = useState(null);
  const [moveMsg, setMoveMsg] = useState(null);
   let[errorMsg,setErrorMsg]=useState('');
 
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

 const [checkUserLay, setUserLay] = useState({nameId:checkNameId});
  // const [checkData, setData] = useState({});


//   const handleNameChange = (e) => {
//     setName(e.target.value);
// }
// const handleAreasChange = (e) => {
//   setAreas(e.target.value);
// }

  const addAreas=()=>{
 
   let data={
   
    // name: checkName,
    // shape: checkShape,
    // coords: checkCoords,
    // preFillColor: checkPreFillColor,
    // fillColor: checkFillColor,
    // nameId:checkNameId


    name: "11",
    shape: "poly",
    coords: checkCoords,
    preFillColor: "green",
    fillColor: "black",
    nameId:"ak"
   }
   // name: checkName,
      // areas: checkAreas,
    // axios
    // //  .get("https://jsonplaceholder.typicode.com/users")
    //   .post("http://localhost:8081/userLayout/addUserLayout")
  


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
      // {
      //   name: "2",
      //   shape: "poly",
      //   coords: [121, 68, 193, 68, 195, 112, 116, 108],
      //   // preFillColor: "transperant",
      //   preFillColor: "green",
      //   fillColor: "blue"
      // } 
  // ,
    
  
  //    {
  //      name: "3",
  //      shape: "poly",
  //      coords:[350, 67, 422, 64, 423, 131, 341, 139],
  //       // preFillColor: "transperant",
  //       preFillColor: "green",
  //       fillColor: "black"
  //     }
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

//     <Popup  minWidth={90}>
//     <span>
       

//     </span>
// </Popup>


alert('I am alert, nice to meet you'+  <button onClick={() => resetHandler()}>Reset</button>);
//     
console.log(`Click on Area:`+` ${area.name}`)
  };

  const clickedOutside = (evt) => {
    // const coords = { x: evt.nativeEvent.layerX, y: evt.nativeEvent.layerY };
    // // setMsg(`You clicked on the image at coords ${JSON.stringify(coords)} !`);

    // setDots((oldArray) => [
    //   ...oldArray,
    //   {
    //     name: "1",
    //     shape: "circle",
    //     coords: [coords.x, coords.y, 3],
    //     preFillColor: "black",
    //     lineWidth: 11
    //   }
    // ]);
  };

  const moveOnImage = (evt) => {
    const coords = { x: evt.nativeEvent.layerX, y: evt.nativeEvent.layerY };
    setMoveMsg(`You moved on the image at coords ${JSON.stringify(coords)} !`);
    // console.log(userLayout);
  };

  const enterArea = (area) => {
    setHoveredArea(area);
    setMsg(
      `You entered ${area.shape} ${area.name} at coords ${JSON.stringify(
        area.coords
      )} !`
    );
    console.log("nter in area")
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

  // useEffect(() => {
  //   const areasCopy = [...userLayout.areas];
  //   areasCopy[0].coords.push(50);
  //   const userLayoutCopy = { ...userLayout, areas: areasCopy };

  //   setUserLayoutCoords(userLayoutCopy);
  // }, [userLayoutCoords]);

  const resetHandler = () => {
    const areasCopy = [...userLayout.areas];
    areasCopy[0].coords = [];

    const userLayoutCopy = { ...userLayout, areas: areasCopy };
    setUserLayoutCoords(userLayoutCopy);

    setDots([]);
  };


  const addPolygon=(evt)=>{
  //  const areasCopy={...userLayout.areas}

    // let obj={ name: "3",
    // shape: "poly",
    // coords:[350, 67, 422, 64, 423, 131, 341, 139],
    //  // preFillColor: "transperant",
    //  preFillColor: "green",
    //  fillColor: "black"

    // }

    


  //   const userLayoutCopy = { ...userLayout, areas: obj };
  //   setUserLayout(userLayoutCopy);
  const coords = { x: evt.nativeEvent.layerX, y: evt.nativeEvent.layerY };
//  const coords1=[coords.x, coords.y, 3];
  console.log(userLayout)
  const areasCopy={...userLayout.areas}
  // areasCopy[0].coords.push(x);
  // areasCopy[0].coords.push(y);


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
   
   
   
   
   
    // let dataObj={
    //   name: "3",
    //   shape: "poly",
    //   coords:areasCopy[0].coords,
    //    // preFillColor: "transperant",
    //    preFillColor: "green",
    //    fillColor: "black",
    //    nameId:userLayout.name,
    // }

 
    // setNameId(userLayout.name);

      // setData(dataObj);
      // try {
      //   for (var i = 0; i < areaslist.length; i++) {
      //     var object = areaslist[i];
      //     if(object.coords != null){
      //      userLayout.areas.push(object);
      //     }
      //   }
      // } catch (error) {
      //   console.log("error");
      // }
      console.log("out :")
      // setCoords(areasCopy[0].coords);

    //  if(checkCoords!=null){
      console.log("in")
      // setCoords(userLayout.areas[0].coords);
      setCoords(areasCopy[0].coords);

      // setCoords(areasCopy[0].coords);
      userLayout.areas.push(obj);
      addAreas();
    //  }
     
  
    console.log("UserLayout :"+userLayout)

  // const areasCopy1 = [...userLayout.areas];
  // areasCopy1[0].coords = [];
  resetHandlerForAddPolygon();

  }
  const resetHandlerForAddPolygon = () => {
    const areasCopy = [...userLayout.areas];
    areasCopy[0].coords = [];

    // setCoords([]);
    const userLayoutCopy = { ...userLayout, areas: areasCopy };
    setUserLayoutCoords(userLayoutCopy);
  setUserLayout(userLayoutCopy)
    setDots([]);
  };


  // useEffect(() => {}, [reset]);
  useEffect(()=>
  {
    getAllAreas();
    //  handleAllAreas();

  }, [reset]);


  const getAllAreas=()=>{
    axios
    //  .get("https://jsonplaceholder.typicode.com/users")
      .get("http://localhost:8080/userLayout/areasList")
      .then(response =>setUserLayout((prevState)=>({
        ...prevState,
        areas:[...userLayout.areas,...response.data]            
      })))

      // .then(response => response.data.map((row)=>{ 


      //   const areasCopy = [...userLayout.areas];
      //   let obj1={
      //     name: row.name,
      //    shape: row.shape,
      //    coords:row.coords,
      //     // preFillColor: "transperant",
      //     preFillColor:row.preFillColor,
      //     fillColor: row.fillColor,
      //    //  userLayout:checkUserLay,
      //    }

      //    areasCopy[0].push(obj1);
      //    const userLayoutCopy = { ...userLayout, areas: areasCopy };
      //    setUserLayout(userLayoutCopy);

      //      userLayout.areas.push(obj1);
      //      console.log("row:"+row)}))
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
    // checkAreaslist.map((areas)=>(
      //   userLayout.areas.push(areas)
      // ))
    // areaslist.array.forEach(element => {
    //   userLayout.areas.push(element);
    // });
    

  //   checkAreaslist.forEach((areas, index) => {
    //   //    <hr />
    //   // </div>,
 
  //   //   <div key={index}>
  //   //   <h2>name: {employee.name}</h2>
  //   //   <h2>country: {employee.country}</h2>

   
     
  //  let ojb1={ name: areas.name,
  //   shape: areas.shape,
  //   coords:areas.coords,
  //    // preFillColor: "transperant",
  //    preFillColor:areas.preFillColor,
  //    fillColor: areas.fillColor,
  //   //  userLayout:checkUserLay,
  //   }
  //   userLayout.areas.push(obj1);
  //     // userLayout.push(obj1);
  //     console.log(areas)
  //   });
  // userLayout.areas.forEach(element => {
    //   if(element.coords != null){
      //   drawPolygon(element.coords.x, element.coords.y);}
      // });
      // drawPolygon(coords.x, coords.y);
      console.log("hi")
      
    }
    // console.log(areaslist);
    console.log("UserLayout :"+ JSON.stringify(userLayout))
    
    
  // console.log("areaList :"+ JSON.stringify(areaslist))

  return (
   
    <div className="grid">
     

    
        <div className="presenter">
        <div style={{ position: "relative" }}>
        {/* <Popper id={id} open={open} anchorEl={anchorEl}>
        <Box sx={{ border: 1, p: 1, bgcolor: 'background.paper' }}>
          The content of the Popper.
        </Box>
      </Popper> */}
          {/* <ImageMapper
            src={URL}
            map={MAP2}
            width={500}
            onLoad={() => load()}
            onMouseMove={(area, _, evt) => moveOnArea(area, evt)}
            onClick={(area) => clicked(area)}
            onMouseEnter={(area) => enterArea(area)}
            onMouseLeave={(area) => leaveArea(area)}
            onImageClick={(evt) => clickedOutside(evt)}
            onImageMouseMove={(evt) => moveOnImage(evt)}
            lineWidth={4}
            strokeColor={"white"}
          /> */}

          {/* {hoveredArea && (
            <span
              className="tooltip"
              style={{ ...getTipPosition(hoveredArea) }}
            >
              {hoveredArea && hoveredArea.name}
            </span>
          )} */}

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
          {/* <button onClick={(evt) => handleAllAreas()}>handle Areas</button> */}

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
            // onClick={(evt) => handleClick(evt)}
            onClick={(evt) => clicked(evt)}

            // onClick={(area) => openModal(area)}
            // onClick={(area) => handleClick(area.name)}
            onMouseEnter={(area) => enterArea(area)}
            onMouseLeave={(area) => leaveArea(area)}
          />
 


           {/* <ImageMapper
             src={a1}
             map={userLayout}
            width={500}
            onLoad={() => load()}
            onMouseMove={(area, _, evt) => moveOnArea(area, evt)}
            onClick={(area) => clicked(area)}
            onMouseEnter={(area) => enterArea(area)}
            onMouseLeave={(area) => leaveArea(area)}
            onImageClick={(evt) => clickedOutside(evt)}
            onImageMouseMove={(evt) => moveOnImage(evt)}
            lineWidth={4}
            strokeColor={"white"}  
            /> */}
        </div>
        {/* <pre className="message">{msg ? msg : null}</pre> */}
        {/* <pre>{moveMsg ? moveMsg : null}</pre> */}
      </div>
    
    </div>


  );
};

export default App1;
