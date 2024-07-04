import { useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "../../css/Header.css";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import Container from "@mui/material/Container";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import Tooltip from "@mui/material/Tooltip";
import MenuItem from "@mui/material/MenuItem";
import {Image} from 'mui-image'
import AdbIcon from "@mui/icons-material/Adb";
// import Link  from '@mui/material/Link';
import { ThemeProvider, createTheme } from "@mui/material/styles";
import { Badge, CardMedia } from "@mui/material";
import CartIcon from "@mui/icons-material/ShoppingCart";
import { useSelector } from "react-redux";
import EputraLogo from '../../../src/Image/EpturaLogo.png'
// const pages = ["Home  ", "Floor Map Updation"];
// const commonPages = ["Profile", "Logout"];
// const userPages = ["Home ", "Floor Map", "Calender", "Feedback"];
// const nonLoginUser = ["Home"];

const pages = [];
const commonPages = ["Profile", "Logout"];
const userPages = [];
const nonLoginUser = [];
const login = "Login";

const Navigation = () => {
  const navigate = useNavigate();
  const cart = useSelector((state) => state);

  let userSignIn = JSON.parse(sessionStorage.getItem("userLogin"));

  const [anchorElNav, setAnchorElNav] = useState(null);
  const [anchorElUser, setAnchorElUser] = useState(null);

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleCloseNavMenu = (page) => {
    setAnchorElNav(null);
    if (page === "Home") {
      navigate("/");
    } else if (page === "Add Auditorium") {
      navigate("/add-auditorium");
    } else if (page === "List of Auditoriums") {
      navigate("/auditorium-list");
    }
    // else if (page === 'Auditorium') {
    //   navigate("/auditorium-view")
    // }
    else if (page === "Booking") {
      navigate("/auditorium-Booking");
    } else if (page === "About Us") {
      navigate("/about-us");
    } else if (page === "Contact Us") {
      navigate("/contact-us");
    } else if (page === "Calender") {
      navigate("/calender-view");
    } else if (page === "User Bookings") {
      navigate("/booking-List-user");
    } else if (page === "Feedback") {
      navigate("/feedback");
    } else if (page === "Food") {
      navigate("/product-list");
    } else if (page === "Cart") {
      navigate("/cart");
    } else if (page === "Floor Map") {
      navigate("/floormap");
    } else if (page === "Home ") {
      navigate("/auditorium-view");
    } else if (page === "Home  ") {
      navigate("/dashboard");
    } else if (page === "Floor Map Updation") {
      navigate("/admin-floormap");
    } else {
      navigate("/user-list");
    }
  };

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = (page) => {
    setAnchorElUser(null);
    if (page === "Profile") {
      navigate("/user-update");
    } else if (page === "Logout") {
      onLogOut();
    } else if (page === "Login") {
      navigate("/signin");
    }
  };
  const onLogOut = () => {
    sessionStorage.clear();
    navigate("/");
    window.location.reload();
  };

  const darkTheme = createTheme({
    palette: {
      // mode: "dark",
      primary: {
        main: "#dcdcdc",
      },
    },
  });

  return (
    <ThemeProvider theme={darkTheme}>
      <AppBar position="static">
        <Container maxWidth="xl">
          <Toolbar disableGutters>
            <CardMedia
               component="img"
               style={{ height: '40px', width: 'auto' }} 
               image={EputraLogo} >
      </CardMedia>
            <Typography
              variant="h6"
              noWrap
              component="a"
              href="/"
              sx={{
                mr: 1,
                display: { xs: "none", md: "flex" },
                fontFamily: "cursive",
                fontWeight: 700,
                letterSpacing: ".3rem",
                color: "inherit",
                textDecoration: "none",
                marginLeft:"10"
              }}
            >
               BookMyCon
            </Typography>

            <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
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
                  vertical: "bottom",
                  horizontal: "left",
                }}
                keepMounted
                transformOrigin={{
                  vertical: "top",
                  horizontal: "left",
                }}
                open={Boolean(anchorElNav)}
                onClose={handleCloseNavMenu}
                sx={{
                  display: { xs: "block", md: "none" },
                }}
              >
                {userSignIn &&
                  userSignIn.userRole === "admin" &&
                  pages.map((page) => (
                    <MenuItem
                      key={page}
                      onClick={() => handleCloseNavMenu(page)}
                    >
                      <Typography sx={{ color: "white" }} textAlign="center">
                        {page}
                      </Typography>
                    </MenuItem>
                  ))}
                {userSignIn &&
                  userSignIn.userRole === "user" &&
                  userPages.map((page) => (
                    <MenuItem
                      key={page}
                      onClick={() => handleCloseNavMenu(page)}
                    >
                      <Typography sx={{ color: "white" }} textAlign="center">
                        {page}
                      </Typography>
                    </MenuItem>
                  ))}
              </Menu>
            </Box>

            <Typography
              variant="h5"
              noWrap
              component="a"
              href=""
              sx={{
                mr: 2,
                display: { xs: "flex", md: "none" },
                flexGrow: 1,
                fontFamily: "cursive",
                fontWeight: 700,
                letterSpacing: ".3rem",
                color: "inherit ",
                textDecoration: "none",
              }}
            >
              BookMyCon
            </Typography>

            {userSignIn && userSignIn.userRole === "admin" && (
              <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
                {pages.map((page) => (
                  <Button
                    key={page}
                    onClick={() => handleCloseNavMenu(page)}
                    sx={{ my: 2, color: "black", display: "block" }}
                  >
                    {page}
                  </Button>
                ))}
              </Box>
            )}

{userSignIn && userSignIn.userRole === "vendor" && (
              <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
                
              </Box>
            )}

            {userSignIn && userSignIn.userRole === "user" && (
              <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
                {userPages.map((userPage) => (
                  <Button
                    key={userPage}
                    onClick={() => handleCloseNavMenu(userPage)}
                    sx={{ my: 2, color: "black", display: "block" }}
                  >
                    {userPage}
                  </Button>
                ))}
                {/* <Link className="nav-link mt-2 .text-dark" to={"/cart"}>
                  <Badge
                    badgeContent={cart.length}
                    color="secondary"
                    sx={{ mr: 1 }}
                  >
                    <CartIcon />
                  </Badge>
                  Cart
                </Link> */}
              </Box>
            )}

            {userSignIn && userSignIn.userRole === "admin" && (
              <Box sx={{ flexGrow: 0 }}>
                <Tooltip title="Open settings">
                  <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                    <Avatar sx={{ backgroundColor: "black" }} variant="soft" />
                  </IconButton>
                </Tooltip>
                <Menu
                  sx={{ mt: "45px" }}
                  id="menu-appbar"
                  anchorEl={anchorElUser}
                  anchorOrigin={{
                    vertical: "top",
                    horizontal: "right",
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                  }}
                  open={Boolean(anchorElUser)}
                  onClose={handleCloseUserMenu}
                >
                  {commonPages.map((commonPage) => (
                    <MenuItem
                      key={commonPage}
                      onClick={() => handleCloseUserMenu(commonPage)}
                    >
                      <Typography sx={{ color: "black" }} textAlign="center">
                        {commonPage}
                      </Typography>
                    </MenuItem>
                  ))}
                </Menu>
              </Box>
            )}

            {userSignIn && userSignIn.userRole === "user" && (
              <Box sx={{ flexGrow: 0 }}>
                <Tooltip title="Open settings">
                  <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                    <Avatar sx={{ backgroundColor: "black" }} variant="soft" />
                  </IconButton>
                </Tooltip>
                <Menu
                  sx={{ mt: "45px" }}
                  id="menu-appbar"
                  anchorEl={anchorElUser}
                  anchorOrigin={{
                    vertical: "top",
                    horizontal: "right",
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                  }}
                  open={Boolean(anchorElUser)}
                  onClose={handleCloseUserMenu}
                >
                  {commonPages.map((commonPage) => (
                    <MenuItem
                      key={commonPage}
                      onClick={() => handleCloseUserMenu(commonPage)}
                    >
                      <Typography sx={{ color: "black" }} textAlign="center">
                        {commonPage}
                      </Typography>
                    </MenuItem>
                  ))}
                </Menu>
              </Box>
            )}

            {userSignIn && userSignIn.userRole === "vendor" && (
              <Box sx={{ flexGrow: 0 ,float:"right"}}>
                <Tooltip title="Open settings">
                  <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                    <Avatar sx={{ backgroundColor: "black" }} variant="soft" />
                  </IconButton>
                </Tooltip>
                <Menu
                  sx={{ mt: "45px" }}
                  id="menu-appbar"
                  anchorEl={anchorElUser}
                  anchorOrigin={{
                    vertical: "top",
                    horizontal: "right",
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                  }}
                  open={Boolean(anchorElUser)}
                  onClose={handleCloseUserMenu}
                >
                  {commonPages.map((commonPage) => (
                    <MenuItem
                      key={commonPage}
                      onClick={() => handleCloseUserMenu(commonPage)}
                    >
                      <Typography sx={{ color: "black" }} textAlign="center">
                        {commonPage}
                      </Typography>
                    </MenuItem>
                  ))}
                </Menu>
              </Box>
            )}

            {!userSignIn && (
              <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
                {nonLoginUser.map((nonLoginUserPage) => (
                  <Button
                    key={nonLoginUserPage}
                    onClick={() => handleCloseNavMenu(nonLoginUserPage)}
                    sx={{ my: 2, color: "black", display: "block" }}
                  >
                    {nonLoginUserPage}
                  </Button>
                ))}
              </Box>
            )}

            {!userSignIn && (
              <Box sx={{ flexGrow: 0 }}>
                <Button
                  key={login}
                  sx={{ my: 2, color: "black", display: "block" }}
                  onClick={() => handleCloseUserMenu(login)}
                >
                  {login}
                </Button>
              </Box>
            )}
          </Toolbar>
        </Container>
      </AppBar>
    </ThemeProvider>
  );
};

export default Navigation;
