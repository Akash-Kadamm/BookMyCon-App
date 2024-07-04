import React, { useState } from 'react';
import { Button } from '@material-ui/core';
import { useNavigate } from 'react-router-dom';
import "../../../src/App.css";
 
const ModalPieChart = () => {
  const [open, setOpen] = useState(false);
  const navigate = useNavigate();
 
  const handleOpen = () => {
    setOpen(true);
  };
 
  const handleClose = () => {
    setOpen(false);
  };
 
  const handleViewTable = () => {
   
    navigate('/piechart');
  };
  const handleViewBar = () => {
   
    navigate('/BarChart');
  };
 
  return (
    <div className="position-relative" style={{ display: 'flex',  alignItems: 'center', gap: '20px' }}>
  <div className="Button1">
    <Button variant="contained" color="default" onClick={handleViewBar}>
      View as BarChart
    </Button>
  </div>
  <div className="Button2">
    <Button variant="contained" color="default" onClick={handleViewTable}>
      View as PieChart
    </Button>
  </div>
</div>

  );
};
 
export default ModalPieChart;