import { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Header.css";

const Navigation = () => {
  const navigate = useNavigate();
  let userSignIn = JSON.parse(sessionStorage.getItem("userLogin"));

  const onLogOut = () => {
    // window.location.reload();
    sessionStorage.clear();
    navigate("/")
  };

  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark myNav">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">
            BookMyCoN
          </Link>

          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          {userSignIn && userSignIn.userRole === "admin" && (
            <div
              className="collapse navbar-collapse"
              id="navbarSupportedContent"
            >
              <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                <li className="nav-item">
                  <Link className="nav-link active" to="/admin-home">
                    Home
                  </Link>
                </li>

                <li className="nav-item">
                  <Link className="nav-link" to="/conference-hall">
                    {" "}
                    Conference Hall
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/booking-list">
                    Bookings
                  </Link>
                </li>

                <li className="nav-item">
                  <Link className="nav-link" to="/add-auditorium">
                    Add Conference Hall
                  </Link>
                </li>

                <li className="nav-item">
                  <Link className="nav-link" to="/auditorium-list">
                   List Conference Hall
                  </Link>
                </li>

                <li className="nav-item">
                  <Link className="nav-link" to="/user-list">
                    Users
                  </Link>
                </li>
              </ul>

              <div className="d-flex">
                <button onClick={onLogOut} className="btn btn-outline-info">
                  Logout
                </button>
              </div>
            </div>
          )}

          {userSignIn && userSignIn.userRole === "user" && (
            <div
              className="collapse navbar-collapse"
              id="navbarSupportedContent"
            >
              <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                <li className="nav-item">
                  <Link className="nav-link active" to="/">
                    Home
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/conference-hall">
                    {" "}
                    Conference Hall
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/auditorium-Booking">
                    {" "}
                    Bookings
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/user-update">
                    {" "}
                    Edit Profile
                  </Link>
                </li>
              </ul>
              <div className="d-flex">
                <button onClick={onLogOut} className="btn btn-outline-info">
                  Logout
                </button>
              </div>
            </div>
          )}

          {!userSignIn && (
            <div
              className="collapse navbar-collapse"
              id="navbarSupportedContent"
            >
              <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                <li className="nav-item">
                  <Link className="nav-link active" aria-current="page" to="/">
                    Home
                  </Link>
                </li>

                <li className="nav-item">
                  <Link className="nav-link" to="/about">
                    {" "}
                    About
                  </Link>
                </li>

                <li className="nav-item">
                  <Link className="nav-link" to="/contact">
                    Contact
                  </Link>
                </li>
              </ul>
              <div className="d-flex m-2">
                <Link
                  to="/signup"
                  className="btn btn-outline-info"
                  type="button"
                >
                  Sign up
                </Link>
              </div>

              <div className="d-flex">
                <Link
                  to="/signin"
                  className="btn btn-outline-info"
                  type="button"
                >
                  Login
                </Link>
              </div>
            </div>
          )}
        </div>
      </nav>
    </div>
  );
};

export default Navigation;
