import React, { useState } from 'react';
import { Button} from '@material-ui/core';
import BookMeeting3 from './BookMeeting3';

const BookingDialog = () => {
    const [open, setOpen] = useState(false);
   
    const handleOpen = () => {
      setOpen(true);
    };
   
    const handleClose = () => {
      setOpen(false);
    };
   
    return (
      <div>
        <Button variant="contained" color="secondary" onClick={handleOpen}>
       Check
        </Button>
        <BookMeeting3 open={open} handleClose={handleClose} />
      </div>
    );
  };

export default BookingDialog;
