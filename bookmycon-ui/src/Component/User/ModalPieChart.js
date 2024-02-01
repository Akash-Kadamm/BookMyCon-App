
import React, { useState } from 'react';
import { Button } from '@material-ui/core';
import PieChartFloor from './PieChartFloor';


const ModalPieChart = () => {
  const [open, setOpen] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <Button variant="contained" color="default" onClick={handleOpen}>
        Capacity Pie Chart 
      </Button>
      <PieChartFloor open={open} handleClose={handleClose} />
    </div>
  );
};

export default ModalPieChart;
