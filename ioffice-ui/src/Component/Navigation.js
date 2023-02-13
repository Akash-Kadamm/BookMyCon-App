import { useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "../css/Header.css";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import AdbIcon from '@mui/icons-material/Adb';
// import Link  from '@mui/material/Link';
import { ThemeProvider, createTheme } from '@mui/material/styles';


const pages = ['Home', 'Add Auditorium', 'List of Auditoriums', 'Users'];
const commonPages = ['Profile', 'Logout'];
const userPages = ['Home', 'Auditorium', 'Booking','User Bookings', 'About Us', 'Contact Us','Food']
const nonLoginUser = ['Home', 'About Us', 'Contact Us']
const login = 'Login'

const Navigation = () => {
  const navigate = useNavigate();

  let userSignIn = JSON.parse(sessionStorage.getItem("userLogin"));

  const [anchorElNav, setAnchorElNav] = useState(null);
  const [anchorElUser, setAnchorElUser] = useState(null);

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };
  
  const handleCloseNavMenu = (page) => {
    setAnchorElNav(null);
    if (page === 'Home') {
      navigate("/")
    } else if (page === 'Add Auditorium') {
      navigate("/add-auditorium")
    } else if (page === 'List of Auditoriums') {
      navigate("/auditorium-list")
    } else if (page === 'Auditorium') {
      navigate("/auditorium-view")
    } else if (page === 'Booking') {
      navigate("/auditorium-Booking")
    } else if (page === 'About Us') {
      navigate("/about-us")
    } else if (page === 'Contact Us') {
      navigate("/contact-us")
    } else if (page === 'Auditoriums') {
      navigate("/auditorium-view")
    } else if (page === 'User Bookings') {
      navigate("/booking-List-user")
    }else if(page === 'Food'){
      navigate("/product-List")
    }else {
      navigate("/user-list")
    }
  };

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = (page) => {
    setAnchorElUser(null);
    if (page === 'Profile') {
      navigate("/user-update")
    } else if (page === 'Logout') {
      onLogOut()
    } else {
      navigate("/signin")
    }

  };
  const onLogOut = () => {
    sessionStorage.clear();
    navigate("/")
    window.location.reload();
  };

  const darkTheme = createTheme({
    palette: {
      mode: 'dark',
      primary: {
        main: '#1976d2',
      },
    },
  });

  return (
    <ThemeProvider theme={darkTheme}>
      <AppBar position="static">
        <Container maxWidth="xl">
          <Toolbar disableGutters>
            <AdbIcon sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />
            <Typography
              variant="h6"
              noWrap
              component="a"
              href="/"
              sx={{
                mr: 2,
                display: { xs: 'none', md: 'flex' },
                fontFamily: 'monospace',
                fontWeight: 700,
                letterSpacing: '.3rem',
                color: 'inherit',
                textDecoration: 'none',
              }}
            >
              BookMyCoN
            </Typography>

            <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
              <IconButton
                size="large"
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={handleOpenNavMenu}
                color="inherit"
              >
                <MenuIcon />
              </IconButton>
              <Menu
                id="menu-appbar"
                anchorEl={anchorElNav}
                anchorOrigin={{
                  vertical: 'bottom',
                  horizontal: 'left',
                }}
                keepMounted
                transformOrigin={{
                  vertical: 'top',
                  horizontal: 'left',
                }}
                open={Boolean(anchorElNav)}
                onClose={handleCloseNavMenu}
                sx={{
                  display: { xs: 'block', md: 'none' },
                }}
              >
                {userSignIn && userSignIn.userRole === "admin" &&( pages.map((page) => (
                  <MenuItem key={page} onClick={()=>handleCloseNavMenu(page)}>
                    <Typography  sx={{color:"white"}} textAlign="center">{page}</Typography>
                  </MenuItem>
                )))}
                {userSignIn && userSignIn.userRole === "user" &&(userPages.map((page) => (
                  <MenuItem key={page} onClick={()=>handleCloseNavMenu(page)}>
                    <Typography  sx={{color:"white"}} textAlign="center">{page}</Typography>
                  </MenuItem>
                )))}
              </Menu>
            </Box>
            <AdbIcon sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }} />
            <Typography
              variant="h5"
              noWrap
              component="a"
              href=""
              sx={{
                mr: 2,
                display: { xs: 'flex', md: 'none' },
                flexGrow: 1,
                fontFamily: 'monospace',
                fontWeight: 700,
                letterSpacing: '.3rem',
                color: 'inherit',
                textDecoration: 'none',
              }}
            >
              BookMyCoN
            </Typography>


            {userSignIn && userSignIn.userRole === "admin" && (
              <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                {pages.map((page) => (
                  <Button
                    key={page}
                    onClick={() => handleCloseNavMenu(page)}
                    sx={{ my: 2, color: 'white', display: 'block' }}
                  >
                    {page}
                  </Button>
                ))}
              </Box>
            )}


            {userSignIn && userSignIn.userRole === "user" && (
              <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                {userPages.map((userPage) => (
                  <Button
                    key={userPage}
                    onClick={() => handleCloseNavMenu(userPage)}
                    sx={{ my: 2, color: 'white', display: 'block' }}
                  >
                    {userPage}
                  </Button>
                ))}
              </Box>
            )}



            {userSignIn && userSignIn.userRole === 'admin' &&
              (<Box sx={{ flexGrow: 0 }}>
                <Tooltip title="Open settings">
                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                    <Avatar sx={{ backgroundColor: "white" }} variant="soft" />
                  </IconButton>
                </Tooltip>
                <Menu
                  sx={{ mt: '45px' }}
                  id="menu-appbar"
                  anchorEl={anchorElUser}
                  anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  open={Boolean(anchorElUser)}
                  onClose={handleCloseUserMenu}
                >
                  {commonPages.map((commonPage) => (
                    <MenuItem key={commonPage} onClick={() => handleCloseUserMenu(commonPage)}>
                      <Typography  sx={{ color: "white" }} textAlign="center">{commonPage}</Typography>
                    </MenuItem>
                  ))}
                </Menu>
              </Box>
              )}


            {userSignIn && userSignIn.userRole === 'user' &&
              (<Box sx={{ flexGrow: 0 }}>
                <Tooltip title="Open settings">
                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                    <Avatar sx={{ backgroundColor: "white" }} variant="soft" />
                  </IconButton>
                </Tooltip>
                <Menu
                  sx={{ mt: '45px' }}
                  id="menu-appbar"
                  anchorEl={anchorElUser}
                  anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  open={Boolean(anchorElUser)}
                  onClose={handleCloseUserMenu}
                >
                  {commonPages.map((commonPage) => (
                    <MenuItem key={commonPage} onClick={() => handleCloseUserMenu(commonPage)}>
                      <Typography  sx={{ color: "white" }} textAlign="center">{commonPage}</Typography>
                    </MenuItem>
                  ))}
                </Menu>
              </Box>
              )}


            {!userSignIn && (
              <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                {nonLoginUser.map((nonLoginUserPage) => (
                  <Button
                    key={nonLoginUserPage}
                    onClick={() => handleCloseNavMenu(nonLoginUserPage)}
                    sx={{ my: 2, color: 'white', display: 'block' }}
                  >
                    {nonLoginUserPage}
                  </Button>
                ))}
              </Box>
            )}


            {!userSignIn && (
              <Box sx={{ flexGrow: 0 }}>
                <Button key={login} sx={{ my: 2, color: 'white', display: 'block' }} onClick={() => handleCloseUserMenu(login)}>
                  {login}
                </Button>
              </Box>
            )}
          </Toolbar>
        </Container>
      </AppBar>
    </ThemeProvider>

    // <div>
    //   <nav className="navbar navbar-expand-lg navbar-dark bg-dark myNav">
    //     <div className="container-fluid">
    //       <Link className="navbar-brand" to="/">
    //         BookMyCoN
    //       </Link>

    //       <button
    //         className="navbar-toggler"
    //         type="button"
    //         data-bs-toggle="collapse"
    //         data-bs-target="#navbarSupportedContent"
    //         aria-controls="navbarSupportedContent"
    //         aria-expanded="false"
    //         aria-label="Toggle navigation"
    //       >
    //         <span className="navbar-toggler-icon"></span>
    //       </button>

    //       {userSignIn && userSignIn.userRole === "admin" && (
    //         <div
    //           className="collapse navbar-collapse"
    //           id="navbarSupportedContent"
    //         >
    //           <ul className="navbar-nav me-auto mb-2 mb-lg-0">
    //             <li className="nav-item">
    //               <Link className="nav-link active" to="/admin-home">
    //                 Home
    //               </Link>
    //             </li>

    //             <li className="nav-item">
    //               <Link className="nav-link" to="/conference-hall">
    //                 {" "}
    //                 Conference Hall
    //               </Link>
    //             </li>
    //             <li className="nav-item">
    //               <Link className="nav-link" to="/booking-list">
    //                 Bookings
    //               </Link>
    //             </li>

    //             <li className="nav-item">
    //               <Link className="nav-link" to="/add-auditorium">
    //                 Add Conference Hall
    //               </Link>
    //             </li>

    //             <li className="nav-item">
    //               <Link className="nav-link" to="/auditorium-list">
    //                List Conference Hall
    //               </Link>
    //             </li>

    //             <li className="nav-item">
    //               <Link className="nav-link" to="/user-list">
    //                 Users
    //               </Link>
    //             </li>
    //           </ul>

    //           <div className="d-flex">
    //             <button onClick={onLogOut} className="btn btn-outline-info">
    //               Logout
    //             </button>
    //           </div>
    //         </div>
    //       )}

    //       {userSignIn && userSignIn.userRole === "user" && (
    //         <div
    //           className="collapse navbar-collapse"
    //           id="navbarSupportedContent"
    //         >
    //           <ul className="navbar-nav me-auto mb-2 mb-lg-0">
    //             <li className="nav-item">
    //               <Link className="nav-link active" to="/">
    //                 Home
    //               </Link>
    //             </li>
    //             <li className="nav-item">
    //               <Link className="nav-link" to="/conference-hall">
    //                 {" "}
    //                 Conference Hall
    //               </Link>
    //             </li>
    //             <li className="nav-item">
    //               <Link className="nav-link" to="/auditorium-Booking">
    //                 {" "}
    //                 Bookings
    //               </Link>
    //             </li>
    //             <li className="nav-item">
    //               <Link className="nav-link" to="/user-update">
    //                 {" "}
    //                 Edit Profile
    //               </Link>
    //             </li>
    //           </ul>
    //           <div className="d-flex">
    //             <button onClick={onLogOut} className="btn btn-outline-info">
    //               Logout
    //             </button>
    //           </div>
    //         </div>
    //       )}

    //       {!userSignIn && (
    //         <div
    //           className="collapse navbar-collapse"
    //           id="navbarSupportedContent"
    //         >
    //           <ul className="navbar-nav me-auto mb-2 mb-lg-0">
    //             <li className="nav-item">
    //               <Link className="nav-link active" aria-current="page" to="/">
    //                 Home
    //               </Link>
    //             </li>

    //             <li className="nav-item">
    //               <Link className="nav-link" to="/about">
    //                 {" "}
    //                 About
    //               </Link>
    //             </li>

    //             <li className="nav-item">
    //               <Link className="nav-link" to="/contact">
    //                 Contact
    //               </Link>
    //             </li>
    //           </ul>

    //           <div className="d-flex">
    //             <Link
    //               to="/signin"
    //               className="btn btn-outline-info"
    //               type="button"
    //             >
    //               Login
    //             </Link>
    //           </div>
    //         </div>
    //       )}
    //     </div>
    //   </nav>
    // </div>
  );
};

export default Navigation;
