import './App.css';
import Login from './Component/Utilities/Login';
import UpdateUser from './Component/User/UpdateUser';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Navigation from './Component/Utilities/Navigation';
import AddAudi from './Component/Admin/AddAudi';
import { AuditoriumList } from './Component/Admin/AuditoriumList';
import UpdateAuditorium from './Component/Admin/UpdateAuditorium';
import Feedback from './Component/User/Feedback';
import Registration from './Component/Utilities/Registration';
import AboutUs from './Component/Utilities/AboutUs';
import ContactUs from './Component/Utilities/ContactUs';
import BookingListOfUser from './Component/User/BookingListOfUser';
import { AdminFloorMap } from './Component/Admin/AdminFloorMap';
import HomePage from './Component/Utilities/HomePage';
import ProductList from './Component/User/ProductList';
import Cart from './Component/User/Cart';
import AdminDashboard from './Component/Admin/AdminDashboard';
import User from './Component/Admin/User';
import AllBooking from './Component/Admin/AllBooking';
import Complaint from './Component/User/Complaint';
import {UserFloorMap} from './Component/User/UserFloorMap';
import {ViewForUser} from './Component/User/ViewForUser';
import Dashboard from './Component/Admin/Dashboard';
import NonUserHomePage from './Component/User/NonUserHomePage';
import VendorViewOfComplaints from './Component/Vendor/VendorViewOfComplaints';
import BookMeeting2 from './Component/User/BookMeeting2';
import UserDashboard from './Component/DashBoard/UserDashboard';
import { Grid } from '@mui/material';
import { AdminHomePage } from './Component/Admin/AdminHomePage';
import { UserHomePage } from './Component/User/UserHomePage';
import { NonUserHome} from './Component/Utilities/NonUserHome';
import { ReportPage } from './Component/Admin/ReportPage';
import  UserFloorCards from './Component/User/UserFloorCards';
import AddGuest from './Component/User/AddGuest';
import Guest from './Component/User/Guest';
import GuestUpdate from './Component/User/GuestUpdate';
import AddHousekeepingRequests from './Component/User/AddHousekeepingRequests';
import { DeleteHousekeepingRequest } from './Component/Admin/DeleteHousekeepingRequest';
import { GetHousekeepingRequests } from './Component/Admin/GetHousekeepingRequests';
import StripeContainer from './Component/User/Stripe/StripeContainer';
import ChatBot from './Component/Utilities/ChatBot';
import AuditoriumList1 from './Component/User/AuditoriumList1';
import UserFloorTable from './Component/User/UserFloorTable';
import BookMeeting from './Component/User/BookMeeting';
import PieChart from './Component/User/PieChart';
import BarChart from './Component/User/BarChart';
import ShowAuditorium from './Component/User/ShowAuditoirum';
import BookingForm from './Component/User/BookingForm';
import AuditoriumDetails
 from './Component/User/AudiDetails';
// const Card = () => (
//   <div style={{ backgroundColor: "wheat", margin: 10, height: 30, width: 90 }} />
// )

function App() {
  return (
    <div className="App">
      
 <BrowserRouter>
        <Navigation />
        
       
<Grid container>
  
  <Grid item xs={1.8}>
  <UserDashboard/>
  </Grid>
  <Grid item xs={10.2}>
  <Routes>
        <Route path ="/demo" element={<BookingForm />} />
        <Route exact path="/" element={<NonUserHomePage />} />
        <Route exact path="/bookm" element={<BookMeeting/>}/>
        <Route exact path="/adminHomePage" element={<AdminHomePage />} />
        <Route exact path="/userHomePage" element={<UserHomePage />} />
        <Route exact path="/nonUserHome" element={<NonUserHome />} />
          <Route exact path="/calender-view" element={<HomePage />} />
          <Route exact path='/demo' element={<AuditoriumList1/>}/>
          <Route exact path='/floortable' element={<UserFloorTable/>} />
          <Route exact path='/floormap' element={<UserFloorMap/>} />
          <Route exact path="/floormain" element={<UserFloorCards/>} />
           <Route exact path="/auditorium-view" element={<ViewForUser/>} />
          <Route path="/signin" element={<Login />} />
          <Route path="/signup" element={<Registration />} />
          <Route path="/user-update" element={<UpdateUser />} />
          <Route path="/cart" element={<Cart/>} />
          <Route path="/auditorium-update/:id" element={<UpdateAuditorium />} />
          <Route path="/add-auditorium" element={<AddAudi />} />
          <Route path="/auditorium-list" element={<AuditoriumList />} />
          <Route path="/auditoriumBooking" element={<BookMeeting2 />} />
          <Route path="/aboutus" element={<AboutUs />} />
          <Route path="/contact-us" element={<ContactUs />} />
          <Route path="/booking-List-user" element={<BookingListOfUser />} />
          <Route path='/feedback' element={<Feedback />} />
          <Route path="/product-List" element={<ProductList/>} />
          <Route path="/admin_dashboard" element={<AdminDashboard/>}/>
          <Route path="/admin-FloorMap" element={<AdminFloorMap/>}/>
          <Route path="/user_details" element={<User/>}/>
          <Route path="/all_booking" element={<AllBooking/>}/>
          <Route path="/make-complaint" element={<Complaint/>}/>
          <Route path="/dashboard" element={<Dashboard/>}/>
          <Route path="/vendor" element={<VendorViewOfComplaints/>}/>
          <Route path="/report" element={<ReportPage/>}/>
          <Route path ="/payment" element={<StripeContainer />} />
          <Route path="/assistant" element={<ChatBot/>}/>
           <Route path='/addHousekeeping' element={<AddHousekeepingRequests/>} />
          <Route path='/deleteHousekeeping' element={<DeleteHousekeepingRequest />} />
          <Route path='/getHousekeeping' element={<GetHousekeepingRequests />} />
          <Route path='/guest' element={<Guest/>}/>
          <Route path='/guest-update' element={<GuestUpdate/>}/>
          <Route path='/add-guest' element={<AddGuest/>}/>
          <Route path="/Piechart" element={<PieChart />} />
          <Route path="/Barchart" element={<BarChart />} />
          <Route path="/showAudis" element={<ShowAuditorium />} />
        <Route path="/book-auditorium/:auditoriumId" element={<AuditoriumDetails />} />
    
          </Routes>
  </Grid>
</Grid>
      </BrowserRouter>
      <ToastContainer theme='colored' />
    </div>
  );
}

export default App;
