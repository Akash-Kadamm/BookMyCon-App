import React, { useState, useEffect } from "react";
import "../../src/App.css";
import ImageMapper from "react-image-mapper";
import a1 from "../Image/OfficePlan.jpg";
import Popper from '@mui/material/Popper';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import Fade from '@mui/material/Fade';
import Paper from '@mui/material/Paper';
import { getOverlayAlpha } from "@mui/material";
import TextField from '@mui/material/TextField';
import Popover from '@mui/material/Popover';
import axios from 'axios'
import { styled, alpha } from '@mui/material/styles';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import InputBase from '@mui/material/InputBase';
import Badge from '@mui/material/Badge';
import MenuItem from '@mui/material/MenuItem';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import AccountCircle from '@mui/icons-material/AccountCircle';
import MailIcon from '@mui/icons-material/Mail';
import NotificationsIcon from '@mui/icons-material/Notifications';
import MoreIcon from '@mui/icons-material/MoreVert';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';





const Search = styled('div')(({ theme }) => ({
  position: 'relative',
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  '&:hover': {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginRight: theme.spacing(2),
  marginLeft: 0,
  width: '100%',
  [theme.breakpoints.up('sm')]: {
    marginLeft: theme.spacing(3),
    width: 'auto',
  },
}));

const SearchIconWrapper = styled('div')(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: '100%',
  position: 'absolute',
  pointerEvents: 'none',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: 'inherit',
  '& .MuiInputBase-input': {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('md')]: {
      width: '20ch',
    },
  },
}));

export const UserFloorMap = () => {

  const [anchorEl, setAnchorEl] = React.useState(null);
  const [mobileMoreAnchorEl, setMobileMoreAnchorEl] = React.useState(null);

  const isMenuOpen = Boolean(anchorEl);
  const isMobileMenuOpen = Boolean(mobileMoreAnchorEl);

  const handleProfileMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMobileMenuClose = () => {
    setMobileMoreAnchorEl(null);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
    handleMobileMenuClose();
  };

  const handleMobileMenuOpen = (event) => {
    setMobileMoreAnchorEl(event.currentTarget);
  };

  const [state, setState] = React.useState({

    left: false

  });

  const toggleDrawer = (anchor, open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };

  const list = (anchor) => (
    <Box
      sx={{ width: anchor === 'top' || anchor === 'bottom' ? 'auto' : 250 }}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List>
        {['Bookings'].map((text, index) => (
          <ListItem key={text} disablePadding>
            <ListItemButton>
              <ListItemIcon>
                {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
              </ListItemIcon>
              <ListItemText primary={text} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
      <Divider />
      <List>
        {[].map((text, index) => (
          <ListItem key={text} disablePadding>
            <ListItemButton>
              <ListItemIcon>
                {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
              </ListItemIcon>
              <ListItemText primary={text} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
    </Box>
  );
  const menuId = 'primary-search-account-menu';
  const renderMenu = (
    <Menu
      anchorEl={anchorEl}
      anchorOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      id={menuId}
      keepMounted
      transformOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      open={isMenuOpen}
      onClose={handleMenuClose}
    >
      <MenuItem onClick={handleMenuClose}>Profile</MenuItem>
      <MenuItem onClick={handleMenuClose}>My account</MenuItem>
    </Menu>
  );

  const mobileMenuId = 'primary-search-account-menu-mobile';
  const renderMobileMenu = (
    <Menu
      anchorEl={mobileMoreAnchorEl}
      anchorOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      id={mobileMenuId}
      keepMounted
      transformOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      open={isMobileMenuOpen}
      onClose={handleMobileMenuClose}
    >
      <MenuItem>
        <IconButton size="large" aria-label="show 4 new mails" color="inherit">
          <Badge badgeContent={4} color="error">
            <MailIcon />
          </Badge>
        </IconButton>
        <p>Messages</p>
      </MenuItem>
      <MenuItem>
        <IconButton
          size="large"
          aria-label="show 17 new notifications"
          color="inherit"
        >
          <Badge badgeContent={17} color="error">
            <NotificationsIcon />
          </Badge>
        </IconButton>
        <p>Notifications</p>
      </MenuItem>
      <MenuItem onClick={handleProfileMenuOpen}>
        <IconButton
          size="large"
          aria-label="account of current user"
          aria-controls="primary-search-account-menu"
          aria-haspopup="true"
          color="inherit"
        >
          <AccountCircle />
        </IconButton>
        <p>Profile</p>
      </MenuItem>
    </Menu>
  );




  const [msg, setMsg] = useState(null);
  const [hoveredArea, setHoveredArea] = useState(null);
  const [moveMsg, setMoveMsg] = useState(null);
  let [errorMsg, setErrorMsg] = useState('');

  const [reset, setReset] = useState(false);
  const [dots, setDots] = useState([]);

  // const [anchorEl, setAnchorEl] = React.useState(null);
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

    alert('I am alert, nice to meet you' + <button onClick={() => resetHandler()}>Reset</button>);
    //     
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


  const addPolygon = (evt) => {
    const coords = { x: evt.nativeEvent.layerX, y: evt.nativeEvent.layerY };

    console.log(userLayout)
    const areasCopy = { ...userLayout.areas }

    let obj = {
      name: "11",
      shape: "poly",
      coords: areasCopy[0].coords,
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



    console.log("UserLayout :" + userLayout)

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
    //  handleAllAreas();

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

    console.log("hi")

  }
  // console.log(areaslist);
  console.log("UserLayout :" + JSON.stringify(userLayout))


  // console.log("areaList :"+ JSON.stringify(areaslist))

  return (
    <div>
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static" className="bcg">
          <Toolbar>
            <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="open drawer"
              sx={{ mr: 2 }}
            >
              {['Menu'].map((anchor) => (
                <React.Fragment key={anchor}>
                  <Button onClick={toggleDrawer(anchor, true)}>{anchor}</Button>
                  <Drawer
                    anchor={anchor}
                    open={state[anchor]}
                    onClose={toggleDrawer(anchor, false)}
                  >
                    {list(anchor)}
                  </Drawer>
                </React.Fragment>
              ))}
              <MenuIcon />
            </IconButton>
            <Typography
              variant="h6"
              noWrap
              component="div"
              sx={{ display: { xs: 'none', sm: 'block' } }}
            >
              Book your Space
            </Typography>
            {/* <Search>
          <SearchIconWrapper>
            <SearchIcon />
          </SearchIconWrapper>
          <StyledInputBase
            placeholder="Search…"
            inputProps={{ 'aria-label': 'search' }}
          />
        </Search> */}
            <Box sx={{ flexGrow: 1 }} />
            <Box sx={{ display: { xs: 'none', md: 'flex' } }}>
              {/* <IconButton size="large" aria-label="show 4 new mails" color="inherit">
            <Badge badgeContent={4} color="error">
              <MailIcon />
            </Badge>
          </IconButton> */}
              <IconButton
                size="large"
                aria-label="show 17 new notifications"
                color="inherit"
              >
                {/* <Badge badgeContent={17} color="error">
              <NotificationsIcon />
            </Badge> */}
              </IconButton>
              <IconButton
                size="large"
                edge="end"
                aria-label="account of current user"
                aria-controls={menuId}
                aria-haspopup="true"
                onClick={handleProfileMenuOpen}
                color="inherit"
              >
                <AccountCircle />
              </IconButton>
            </Box>
            <Box sx={{ display: { xs: 'flex', md: 'none' } }}>
              <IconButton
                size="large"
                aria-label="show more"
                aria-controls={mobileMenuId}
                aria-haspopup="true"
                onClick={handleMobileMenuOpen}
                color="inherit"
              >
                <MoreIcon />
              </IconButton>
            </Box>
          </Toolbar>
        </AppBar>
        {renderMobileMenu}
        {renderMenu}
      </Box>




      <div className="presenter"  >

        <div >

          <h2>FloorPlan</h2>
          {/* <Popover
    
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
  </Popover> */}

          <ImageMapper

            src={a1}
            map={userLayout}
            width={800}

            onImageClick={(evt) => clickedOutside(evt)}
            onImageMouseMove={(evt) => moveOnImage(evt)}
            // onClick={(evt) => handleClick(evt)}
            onClick={(evt) => clicked(evt)}

            // onClick={(area) => openModal(area)}
            // onClick={(area) => handleClick(area.name)}
            onMouseEnter={(area) => enterArea(area)}
            onMouseLeave={(area) => leaveArea(area)}
          />
          {/* <button onClick={(evt) => addPolygon(evt)}>Add polygon</button> */}


        </div>
      </div>
    </div>
  )
}
