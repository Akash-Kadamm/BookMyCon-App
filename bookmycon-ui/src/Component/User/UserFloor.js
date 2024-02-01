import React, { useState, useEffect } from 'react';
import ModalPieChart from './ModalPieChart';
import FloorService from './FloorService';
import Grid from '@mui/material/Grid';
import { Link } from 'react-router-dom';
 import { Button } from '@mui/material';
 import "../../../src/App.css";
const floorstyle = {
    marginLeft:-127,
    marginRight:0
}

class UserFloor extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            floors:[]
        }
    }

    componentDidMount(){
        FloorService.getFloors().then((response) => {
            this.setState({ floors: response.data})
        });
    }


    render (){
        return (
            <><div>
                <h1 className="text-center" style={floorstyle}> Floor List</h1>
                <table className="table table-striped" style={floorstyle}>
                    <thead>
                        <tr>
                            <td> Name </td>
                            <td> Location </td>
                            <td> Type </td>
                            <td> Amenties</td>
                            <td> Availability </td>
                        </tr>

                    </thead>
                    <tbody>
                        {this.state.floors.map(
                            floor => <tr key={floor.id}>
                                <td> {floor.name}</td>
                                <td> {floor.location}</td>
                                <td> {floor.type}</td>
                                <td> {floor.amenities}</td>
                                <td>{floor.availability} <Button component={Link} to="/auditorium-Booking" variant="contained" color="secondary"
                                >Check</Button></td>

                            </tr>
                        )}

                    </tbody>
                </table>
            </div>
            <Grid item style={{marginRight:'10vh'}}>
            <ModalPieChart/>  
            </Grid>
                   <Grid item style={{marginLeft:'112vh',marginTop:'-4vh'}}>
                    <Button component={Link} to="/floormap" variant="contained" color="primary" >
                        Back
                    </Button>
                </Grid>
                </>

        )
    }
}


export default UserFloor;
