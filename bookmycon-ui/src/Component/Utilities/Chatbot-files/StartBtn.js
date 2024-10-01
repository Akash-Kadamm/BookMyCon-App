import React from "react";

import { useNavigate, Link } from "react-router-dom";



const FileBtn = (props) => {
  const navigate = useNavigate();
  const fileComplaint = () => {
    navigate("/make-complaint");
  };

  const pageRedirectState = () => {
    props.actions.pageRedirectState();
  };
  return (
    <div>
      <button className="sol-btn" onClick={() => fileComplaint()}>
        File Complaint
      </button>
      <button className="sol-btn" onClick={() => pageRedirectState()}>
        Find Page{" "}
      </button>
    </div>
  );
};

const RedirectBtn = () => {
  const navigate = useNavigate();
  const redirectpage = (page) => {
    if (page === "Bookings") {
      navigate("/auditorium-view");
    }
   else if (page === "floor-map") {
      navigate("/floormain");
    }
    else if ("auditorium") {
      navigate("/showAudis");
    }
   else if ("feedback") {
      navigate("/feedback");
    }
  };
  return (
    <div className="pages-div"> 
    <div className="row">
      <button className="sol-btn" onClick={() => redirectpage("Bookings")}>
        Bookings
      </button>
      <button className="sol-btn" onClick={() => redirectpage("floor-map")}>
        Floor Map
      </button>
      </div>
    <div className="row">
    <button className="sol-btn" onClick={() => redirectpage("auditorium")}>
        Auditoriums
      </button>
      <button className="sol-btn" onClick={() => redirectpage("feedback")}>
        Feedback
      </button>
    </div>
    </div>
  );
};

const StartBtn = (props) => {
  const initialState = () => {
    props.actions.initialState();
  };

  return (
    <div>
      <button className="sol-btn" onClick={() => initialState()}>
        Lets get started
      </button>
    </div>
  );
};

export { FileBtn, StartBtn, RedirectBtn };
