import React, { useState, useEffect } from "react";
import "../../../src/App.css";
import Avatar from '@mui/material/Avatar';
import ImageMapper from "react-image-mapper";
import a1 from "../../Image/OfficePlan.jpg"
import Box from '@mui/material/Box';
import Popper from '@mui/material/Popper';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import Fade from '@mui/material/Fade';
import Paper from '@mui/material/Paper';
import { getOverlayAlpha, InputAdornment } from "@mui/material";
import { styled } from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import Popover from '@mui/material/Popover';
import Link from '@mui/material/Link';
import axios from 'axios'
import Checkbox from '@mui/material/Checkbox';
import FormControlLabel from '@mui/material/FormControlLabel';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { AccountCircle } from "@material-ui/icons";
import { useNavigate } from 'react-router-dom';
export const AdminFloorMap = () => {
  const navigate = useNavigate();
    const [msg, setMsg] = useState(null);
    const [hoveredArea, setHoveredArea] = useState(null);
    const [moveMsg, setMoveMsg] = useState(null);
     let[errorMsg,setErrorMsg]=useState('');
   
    const [reset, setReset] = useState(false);
  

    const [dots, setDots] = useState([]);
  
    const [anchorEl, setAnchorEl] = React.useState(null);
   
   
   
 
    const [checkCoords, setCoords] = useState();
  
    const [checkNameId, setNameId] = useState('');
   
   
    const [open, setOpen] = React.useState(false);
    const [placement, setPlacement] = React.useState();
   
     const [areaslist, setAreaslist] = useState([]);
     const [areadata, setAreadata] = useState({});
     const [areaName, setAreaName] = useState("");
     const [areaId, setAreaId] = useState();
     const [auditoriumName, setAuditoriumName] = useState("");
     const [auditorium, setAuditorium] = useState({});
     const handleAuditoriumName = (e) => {
      setAuditoriumName(e);
    };


    const handleAuditoriumNameOnChange = (e) => {
      setAuditoriumName(e.target.value);
    };

    const [location, setLocation] = useState("");
  
     const handleLocation = (e) => {
      setLocation(e);
    };

    const handleLocationOnChage = (e) => {
      setLocation(e.target.value);
    };

    const [type, setType] = useState("");  
     const handleType = (e) => {
      setType(e);
    };
    const handleTypeOnChage = (e) => {
      setType(e.target.value);
    };
    const [amenities, setAmenities] = useState("");  
    const handleAmenities = (e) => {
     setAmenities(e);
   };
   const handleAmenitiesOnChage = (e) => {
    setAmenities(e.target.value);
  };
   const [coordsSubmit, setCoordsSubmit] = useState([]);  
  
  
  
   const [capacity, setCapacity] = useState(0);
  
   const handleCapacity = (e) => {
    setCapacity(e);
  };

  const handleCapacityOnChage = (e) => {
    setCapacity(e.target.value);
  };

  const [addAreasName, setaddAreasName] = useState("default");  
  const handleAddAreasName = (e) => {
    setaddAreasName(e.target.value);
 };

  const handleCoordsSubmiteOnChage = (e) => {
    setCoordsSubmit(e.target.value);
  };
    const addAreas=()=>{
   
     let data={
   
      name: addAreasName,
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
      navigate('/admin-Floormap')
    }

   
  
    const handleClickOnMapAreas = (newPlacement) => (event) => {
      setAnchorEl(event.currentTarget);
      setOpen((prev) => placement !== newPlacement || !prev);
      setPlacement(newPlacement);
    };
  
  
    const handleSubmit = (event) => {
      event.preventDefault();




      axios

      .get(`http://localhost:8080/admin/getAuditoriunByName/${areaName}`)
      .then((response )=>
      {
        console.log(response.data);
        setAuditorium(response.data);
      
      })


      const data2={
  "auditoriumId": auditorium.auditoriumId,
  "auditoriumName": auditoriumName,
  "auditoriumLocation": location,
 "auditoriumCapacity": capacity,
  "auditoriumType":type,
  "auditoriumAminity": amenities
}




      axios.post("http://localhost:8080/admin/addAudi", data2,
      {
        headers: {
          "Content-Type": "application/json",
      }
      })
      .then((response) => {
          console.log(response.data);
       
    
      }).catch((err) => console.log(err + "Incorrect Data"));
    



const data3={
  "areaId": areadata.areaId,
  "name": auditoriumName,
  "shape":areadata.shape,
  "coords": areadata.coords,
  "preFillColor": areadata.preFillColor,
  "fillColor": areadata.fillColor,
  "userLayout": {
      "nameId": "UserLayout"
  }
  
}


    
      axios.put("http://localhost:8080/userLayout/addareas", data3,
      {
        headers: {
          "Content-Type": "application/json",
      }
      })
      .then((response) => {
          console.log(response.data);
       
    
      }).catch((err) => console.log(err + "Incorrect Data"));

     navigate("/admin-FloorMap")
    };
    const handleClick = (event) => {
  
      
      setAnchorEl(true);
      
    };
  
    const handleClose = () => {
      setAnchorEl(null);
    };
  


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
 
      console.log(`Click on Area:`+` ${area.name}`)
      axios

      .get(`http://localhost:8080/userLayout/areasByCoords/${area.coords}`)
      .then((response )=>
      {
        console.log(response.data.areaId+"----areaName");
        setAreadata(response.data);
        setAreaName(response.data.name);
       
        // setAreaId(response.data.);
      })

      .catch((error=>setErrorMsg("error ")));



      
      axios

      .get(`http://localhost:8080/admin/getAuditoriunByName/${area.name}`)
      .then((response )=>
      {
        console.log(response.data);
        setAuditorium(response.data);
       handleAuditoriumName(auditorium.auditoriumName);
        handleLocation(auditorium.auditoriumLocation);
      handleType(auditorium.auditoriumType);
      handleAmenities(auditorium.auditoriumAminity);
      handleCapacity(auditorium.auditoriumCapacity);
      })

      

      .catch((error=>setErrorMsg("error ")));
      console.log(JSON.stringify(auditorium)+"---:Auditorum ");
      console.log(auditorium.auditoriumName+" ---: Name");

      setAuditoriumName(area.name);
      
      setCoordsSubmit(area.coords);
 // alert('I am alert, nice to meet you'+  <button onClick={() => resetHandler()}>Reset</button>);
  //
  // handleClickOnMapAreas(${area}.Popover)

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

    let data1={
      
      "auditoriumName":addAreasName,
  
  }
  
  axios.post("http://localhost:8080/admin/addAudi", data1,
  {
    headers: {
      "Content-Type": "application/json",
  }
  })
  .then((response) => {
      console.log(response.data);
   

  }).catch((err) => console.log(err + "Incorrect Data"));



  
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
  
    return (
     
      <div >
       
{/*








      <Popper open={open} anchorEl={anchorEl} placement={placement} transition>
        {({ TransitionProps }) => (
          <Fade {...TransitionProps} timeout={350}>
            <Paper>
              <Typography sx={{ p: 2 }}>The content of the Popper.</Typography>
              <Typography sx={{ p: 2 }}>The content of the Popper.</Typography>

            </Paper>
          </Fade>
        )}
      </Popper>









          <Grid item xs={8}>


            </Grid>
  <Grid item xs={4}>
  <div>1 dsgsdgadg</div>
  </Grid>

            <h2>userLayout</h2>

            <ImageMapper

              src={a1}
              map={userLayout}
              width={500}
              onImageClick={(evt) => clickedOutside(evt)}
              onImageMouseMove={(evt) => moveOnImage(evt)}
              // onClick={(evt) => handleClick(evt)}
              // onClick={(evt) => clicked(evt)}
              onClick={(area) => clicked(area)}

              // onClick={(area) => openModal(area)}
              // onClick={(area) => handleClick(area.name)}
              onMouseEnter={(area) => enterArea(area)}
              onMouseLeave={(area) => leaveArea(area)}
            />

   <Button onClick={handleClickOnMapAreas("right")}>top</Button>

      */}

<Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={2}>
    
        <Grid item xs={8}>
        <div style={{marginTop:"50px",
        marginTop:"70px",
      width:"700px",
     }} >
       
        <h4>Admin Layout</h4>

            <ImageMapper
              src={a1}
              map={adminLayout}
              width={500}
              onImageClick={(evt) => makeDot(evt)}
              onImageMouseMove={(evt) => moveOnImage(evt)}
            />
              
              <TextField
              alignItems="left"
              margin="normal"
              onChange={handleAddAreasName}
             
           
              name="Location"
            
              type="Location"
              id="Location"
              defaultValue={"Enter Auditorium Name"}
            />
            <br></br>
            <Button onClick={(evt) => addPolygon(evt)}>Add Auditorium</Button>

            <h4>User Layout</h4>

            <ImageMapper
  
              src={a1}
              map={userLayout}
              width={500}

              onImageClick={(evt) => clickedOutside(evt)}
              onImageMouseMove={(evt) => moveOnImage(evt)}
              // onClick={(evt) => handleClick(evt)}
              // onClick={(evt) => clicked(evt)}
              onClick={(area) => clicked(area)}

              // onClick={(area) => openModal(area)}
              // onClick={(area) => handleClick(area.name)}
              onMouseEnter={(area) => enterArea(area)}
              onMouseLeave={(area) => leaveArea(area)}
            />
   
  
         </div>
        </Grid>
     
       
        <Grid item xs={4}>
        <div style={{marginTop:"70px",marginRight:"25px"}}>
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
         
          <Typography component="h1" variant="h5">
           Update Auditorium Details
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
             
              <Grid item xs={12}>
              Auditorium Name
              <TextField
              margin="normal"
         onChange={handleAuditoriumNameOnChange}
              fullWidth
              name="Name"
        
              type="Name"
              id="Name"
              value={auditoriumName}
            
            />
              </Grid>
              <Grid item xs={12}>
              Location
              <TextField
              margin="normal"
           onChange={handleLocationOnChage}
             
              fullWidth
              name="Location"
            
              type="Location"
              id="Location"
              value={location}
            />
              </Grid>
              <Grid item xs={12}>
              <TextField
              onChange={handleCapacityOnChage}
          id="capacity"
          label="Capacity"
          type="number"
         value={capacity}
          
        />
              </Grid>
              <Grid item xs={12}>
              Type
              <TextField
  onChange={handleTypeOnChage}
                margin="normal"
      
              fullWidth
              name="Type"
            
              type="Type"
              id="Type"
              value={type}
            />
              </Grid>
              <Grid item xs={12}>
              Amenities
              <TextField
        onChange={handleAmenitiesOnChage}
              margin="normal"
              
              fullWidth
              name="Amenities"
              
              type="Amenities"
              id="Amenities"
              value={amenities}
            />

              </Grid>
              <Grid item xs={12}>
              <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Update
            </Button>
            </Grid>
           
             
              
            </Grid>
          
            <Grid container justifyContent="flex-end">
             
            </Grid>
          </Box>
        </Box>
        </div>
        </Grid>
        
      </Grid>



    </Box>


      </div>
  
  
    )
}
