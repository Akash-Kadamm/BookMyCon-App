import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom'
// import '../css/Dashboard.css'

const Dashboard=()=>{
const [usercount, setusercount]=useState();
// const [ordercount, setordercount]=useState();
const [audicount, setaudicount]=useState();
const [bookingcount,setbookingcount] = useState([])

const navigate = useNavigate()

  const FetchData=()=>{
    axios.get(`http://localhost:8080/admin/userCount`).then((response)=>{
      // console.log(response.data);
      setusercount(response.data)
    })
  }

  const FetchAudi=()=>{
    axios.get(`http://localhost:8080/admin/audiCount`).then((response)=>{
      // console.log(response.data);
      setaudicount(response.data)
    })
  }

  const FetchBookings = () => {
    axios.get('http://localhost:8080/admin/bookingCount').then((response) => {
      setbookingcount(response.data)
    }).catch((error) => {
      console.log(error)
    })
  }

  const getAllUser = () => {
    navigate('/user_details')
   }

   const getAllAuditorium =()=>{
     navigate('/auditorium-list')
   }

   const getAllbooking = () =>{
     navigate('/all_booking')
   }


return(  
  <div className="dashboard">
   <head>
   <link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/css/bootstrap-extended.min.css"/>
<link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/fonts/simple-line-icons/style.min.css"/>
<link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/css/colors.min.css"/>
   </head>
<div className="row">
    <div classNameName="col-sm-10">
        <h2 style={{textAlign:'center', fontSize:"50px"}}>Statistics</h2>
        <div className="row para">
        <div className="col-xl-3 col-sm-6 col-12">
          <div className="card">
            <div className="card-content">
              <div className="card-body">
                <div className="media d-flex">
                  <div className="align-self-center">
                    <i className="icon-speech warning font-large-2 float-left"></i>
                  </div>
                  <div className="media-body text-right" onChange={FetchAudi()} onClick={()=>getAllAuditorium()}>
                    <h3>{audicount}</h3>
                    <span>Total Auditorium</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="col-xl-3 col-sm-6 col-12">
          <div className="card">
            <div className="card-content">
              <div className="card-body">
                <div className="media d-flex">
                  <div className="align-self-center">
                    <i className="icon-graph success font-large-2 float-left"></i>
                  </div>
                  <div className="media-body text-right" onChange={FetchData()} onClick={()=>getAllUser()}>
                    <h3>{usercount}</h3>
                    <span>Total Users</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="col-xl-3 col-sm-6 col-12">
          <div className="card">
            <div className="card-content">
              <div className="card-body">
                <div className="media d-flex">
                  <div className="align-self-center">
                    <i className="icon-graph success font-large-2 float-left"></i>
                  </div>
                  <div className="media-body text-right" onChange={FetchBookings()} onClick={()=>getAllbooking()}>
                    <h3>{bookingcount}</h3>
                    <span>Total Bookings</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    </div>
  </div>
</div>
</div> 
)
}
export default Dashboard
