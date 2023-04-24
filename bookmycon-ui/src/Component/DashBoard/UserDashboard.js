import * as React from 'react';
import { styled, createTheme, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Badge from '@mui/material/Badge';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Link from '@mui/material/Link';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import NotificationsIcon from '@mui/icons-material/Notifications';
import { mainListItems, mainListIcon } from './ListItems';
import { useNavigate } from "react-router";
// import Chart from './Chart';
// import Deposits from './Deposits';
// import Orders from './Orders';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import ListSubheader from '@mui/material/ListSubheader';
import DashboardIcon from '@mui/icons-material/Dashboard';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import PeopleIcon from '@mui/icons-material/People';
import BarChartIcon from '@mui/icons-material/BarChart';
import LayersIcon from '@mui/icons-material/Layers';
import MapIcon from '@mui/icons-material/Map';
import BookIcon from '@mui/icons-material/Book';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';
import LoginIcon from '@mui/icons-material/Login';
import HowToRegIcon from '@mui/icons-material/HowToReg';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import CycloneIcon from '@mui/icons-material/Cyclone';
import EmergencyShareIcon from '@mui/icons-material/EmergencyShare';
import SyncAltIcon from '@mui/icons-material/SyncAlt';
import { useSelector } from 'react-redux';
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

const drawerWidth = 240;

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));


const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    '& .MuiDrawer-paper': {
      position: 'relative',
      whiteSpace: 'nowrap',
      width: drawerWidth,
      transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
      }),
      boxSizing: 'border-box',
      ...(!open && {
        overflowX: 'hidden',
        transition: theme.transitions.create('width', {
          easing: theme.transitions.easing.sharp,
          duration: theme.transitions.duration.leavingScreen,
        }),
        width: theme.spacing(7),
        [theme.breakpoints.up('sm')]: {
          width: theme.spacing(9),
        },
      }),
    },
  }),
);

const mdTheme = createTheme();

function DashboardContent() {
  const cart = useSelector((state) => state);
  const navigate = useNavigate();
  const [open, setOpen] = React.useState(true);
  const toggleDrawer = () => {
    setOpen(!open);
  };
  const handlehomepage=()=>{

    navigate("/")
  }
  return (
    <ThemeProvider theme={mdTheme}>
      <Box sx={{ display: 'flex' }}>
   
      
        <Drawer variant="permanent" open={open}>
          <Toolbar
            sx={{
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'flex-end',
              px: [0],
            }}
          >
            <IconButton onClick={toggleDrawer}>
              <ChevronLeftIcon />
            </IconButton>
          </Toolbar>
         
          <List onClick={toggleDrawer} component="nav"> 
         
    
           {sessionStorage.getItem("userRole")===`"user"`
           ?      
            <React.Fragment>
               <ListItemButton   >
             <ListItemIcon>
               <DashboardIcon color="secondary"  />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/userHomePage");
              }}  primary="Dashboard" />
           </ListItemButton>
               <ListItemButton   >
             <ListItemIcon>
               <EmergencyShareIcon color="secondary"  />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/alexa");
              }}  primary="Alexa" />
           </ListItemButton>
           <ListItemButton   >
             <ListItemIcon>
               <CycloneIcon color="secondary"  />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/assistant");
              }}  primary="Solution Assistant" />
           </ListItemButton>
          
           <ListItemButton>
             <ListItemIcon>
               <MapIcon color="secondary"  onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/floormap");
              }} primary="Floor Map" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               <CalendarMonthIcon color="secondary"  onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/calender-view");
              }} primary="Calender" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               <CollectionsBookmarkIcon color="secondary"  onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/auditorium-view");
              }} primary="All Bookings" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               <ThumbUpAltIcon color="secondary"  onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/feedback");
              }} primary="Feedback" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
             <Badge  badgeContent={cart.length}  sx={{ mr: 1}} >
               <ShoppingCartIcon color="secondary"  onClick={toggleDrawer} />
      </Badge>

             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/cart");
              }} primary="Cart" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
             <CollectionsBookmarkIcon color="secondary"  onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/guest");
              }} primary="Guest" />
           </ListItemButton>     
           
         </React.Fragment>
           :
           sessionStorage.getItem("userRole")===`"admin"`
            ?
           <React.Fragment>
           <ListItemButton>
             <ListItemIcon>
               <DashboardIcon color="secondary"  onClick={toggleDrawer}  />
             </ListItemIcon>
             <ListItemText  onClick={()=>{navigate("/adminHomePage");
              }}  primary="Dashboard" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               <MapIcon color="secondary"  onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/admin-floormap");
              }} primary="Floormap Updation" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               <AccountBalanceIcon color="secondary"  onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/auditorium-list");
              }} primary="Auditorium" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               <AccountCircleIcon  color="secondary" onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText  onClick={()=>{toggleDrawer(); navigate("/user_details");
              }} primary="All User" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               <BookIcon color="secondary"  onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/all_booking");
              }} primary="Booking" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               <SyncAltIcon color="secondary"  onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/logs");
              }} primary="Logs" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               <BarChartIcon color="secondary"  onClick={toggleDrawer} />
             </ListItemIcon>
             <ListItemText primary="Reports" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               
             </ListItemIcon>
             1.
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/auditorium-list");
              }} primary="Auditoriums" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
               
             </ListItemIcon>
             2.
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/user_details");
              }} primary="All User" />
           </ListItemButton>
           <ListItemButton>
             <ListItemIcon>
              
             </ListItemIcon>
             3.
             <ListItemText onClick={()=>{toggleDrawer(); navigate("/all_booking");
              }} primary="Bookings" />
           </ListItemButton>
         </React.Fragment>
          : 
          <React.Fragment>
          <ListItemButton>
            <ListItemIcon>
              <DashboardIcon  color="secondary"  onClick={toggleDrawer}  />
            </ListItemIcon>
            <ListItemText onClick={()=>{navigate("/nonUserHome");
              }}   primary="Dashboard" />
          </ListItemButton>
          <ListItemButton>
            <ListItemIcon>
              <LoginIcon  color="secondary"  onClick={toggleDrawer} />
            </ListItemIcon>
            <ListItemText onClick={()=>{toggleDrawer(); navigate("/signin");
              }} primary="Login" />
          </ListItemButton>
          <ListItemButton>
            <ListItemIcon>
              <HowToRegIcon  color="secondary"  onClick={toggleDrawer} />
            </ListItemIcon>
            <ListItemText onClick={()=>{toggleDrawer(); navigate("/signup");
              }} primary="Registration" />
          </ListItemButton>
        
        </React.Fragment>}
            

         
         
          </List>
         
        </Drawer>
  
      </Box>
    </ThemeProvider>
  );
}

export default function UserDashboard() {
  return <DashboardContent />;
}