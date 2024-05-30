import React, { useState } from 'react';
import { Button } from '@material-ui/core';
import { useNavigate } from 'react-router-dom';
import "../../../src/App.css";
 
const BackButton = () => {
  const [open, setOpen] = useState(false);
  const navigate = useNavigate();
 
  const handleOpen = () => {
    setOpen(true);
  };
 
  const handleClose = () => {
    setOpen(false);
  };
 
  const handleViewTable = () => {
   
    navigate('/floormap');
  };
 
 
  return (
    <div className="position-relative">
     
      <div className="user-floor-map">
       
      </div>
 
      {/* Buttons */}
      <div >
      <div className="Button1">
        <Button variant="contained" color="default" onClick={handleViewTable}>
          Back To FloorMap
        </Button>
       
      </div>
 
 
   
    </div>
 
    </div>
  );
};
 
export default BackButton;