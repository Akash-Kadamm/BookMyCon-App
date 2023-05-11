import React from 'react'
import Grid from '@mui/material/Grid';
import { useNavigate } from "react-router";
import fileDownload from 'js-file-download'
import axios from "axios";
import BarChartIcon from '@mui/icons-material/BarChart';
import '../../css/Report.css'

export const ReportPage = () => {
    const navigate = useNavigate();

    const getReportOfAudi = () => {
        axios({url:"http://localhost:8080/admin/export-to-pdf-audi",method:"GET",responseType:"blob"}).then((response) => {
            fileDownload(response.data,'downlodedAudi.pdf')
            console.log(response)
        }).catch((error) => {
          console.log(error)
        })
      }

      const getReportOfUser = () => {
        axios({url:"http://localhost:8080/user/export-to-pdf",method:"GET",responseType:"blob"}).then((response) => {
            fileDownload(response.data,'downlodedUser.pdf')
            console.log(response)
        }).catch((error) => {
          console.log(error)
        })
      }

      const getReportOfBooking = () => {
    axios({url:"http://localhost:8080/admins/export-to-pdf-book",method:"GET",responseType:"blob"}).then((response) => {
        fileDownload(response.data,'downlodedBooking.pdf')
        console.log(response)
    }).catch((error) => {
      console.log(error)
    })
  }

  return (
  
  <div style={{marginLeft:"20px",marginTop:"80px", textAlign:"left"}} >
  <Grid container spacing={2}>

  <Grid item xs={12}>
    <div style={{color: "#9c27b0", textAlign:"left"}}   >
    <BarChartIcon  sx={{ fontSize: 40 }} color="secondary" />
    Report   
     </div>    
    </Grid>
    
    <Grid item xs={12}  >
    <div className='test' style={{color: "#9c27b0", textAlign:"left"}}  >
    <div>Auditorium</div>   
       <div onClick={() => { getReportOfAudi();}} className='dnwld'>Download PDF</div> 
     </div>    
    </Grid>

    <Grid item xs={12} >
    <div className='test' style={{color: "#9c27b0", textAlign:"left"}}  >
    <div>All User</div>
    <div onClick={() => { getReportOfUser();}} className='dnwld'>Download PDF</div> 
     </div>    
    </Grid>

    <Grid item xs={12} >
    <div className='test' style={{color: "#9c27b0", textAlign:"left"}}  >
    <div>Booking</div> 
    <div onClick={() => { getReportOfBooking();}} className='dnwld'>Download PDF</div> 
     </div>    
    </Grid>
    
  </Grid>
  </div>
  )
}
