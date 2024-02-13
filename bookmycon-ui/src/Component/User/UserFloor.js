import React, { useState, useEffect } from 'react';
import ModalPieChart from './ModalPieChart';
import FloorService from './FloorService';
import BookingDialog from './BookingDialog';
import Grid from '@mui/material/Grid';
import { Link } from 'react-router-dom';
import { Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import "../../../src/App.css";

const floorstyle = {
    marginLeft: 73,
    maxWidth: 1200, 
   
};

function UserFloor() {
    const [floors, setFloors] = useState([]);

    useEffect(() => {
        FloorService.getFloors().then((response) => {
            setFloors(response.data);
        });
    }, []);

    return (
        <>
            <div>
                <h1 className="text-center" style={floorstyle}> Floor List</h1>
                <TableContainer>
                    <Table className="table table-striped" style={floorstyle}>
                        <TableHead>
                            <TableRow>
                                <TableCell>Name</TableCell>
                                <TableCell>Location</TableCell>
                                <TableCell>Type</TableCell>
                                <TableCell>Amenities</TableCell>
                                <TableCell>Availability</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {floors.map((floor) => (
                                <TableRow key={floor.fid}>
                                    <TableCell>{floor.name}</TableCell>
                                    <TableCell>{floor.location}</TableCell>
                                    <TableCell>{floor.type}</TableCell>
                                    <TableCell>{floor.amenities}</TableCell>
                                    <TableCell> <BookingDialog/> </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
            <Grid item style={{ marginRight: '10vh' }}>
                <ModalPieChart />
            </Grid>
            <Grid item style={{ marginLeft: '112vh', marginTop: '-4vh' }}>
                <Button component={Link} to="/floormap" variant="contained" color="primary">
                    Back
                </Button>
            </Grid>
        </>
    );
}

export default UserFloor;
