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
import BookMeeting from './Component/User/BookMeeting';
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

import AddGuest from './Component/User/AddGuest';
import Guest from './Component/User/Guest';
import Register from './Component/Utilities/Register';

import PaymentGateway from './Component/User/PaymentGateway';
import PaypalPayment from './Component/User/RazorpayPayment'

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navigation />
        <Routes>
        <Route exact path="/" element={<NonUserHomePage />} />
          <Route exact path="/calender-view" element={<HomePage />} />
          <Route exact path="/floormap" element={<UserFloorMap />} />
          <Route exact path="/auditorium-view" element={<ViewForUser />} />
          <Route path="/signin" element={<Login />} />
          <Route path="/user-update" element={<UpdateUser />} />
          <Route path="/cart" element={<Cart/>} />
          <Route path="/auditorium-update/:id" element={<UpdateAuditorium />} />
          <Route path="/add-auditorium" element={<AddAudi />} />
          <Route path="/auditorium-list" element={<AuditoriumList />} />
          <Route path="/auditorium-Booking" element={<BookMeeting />} />
          <Route path="/aboutus" element={<AboutUs />} />
          <Route path="/contact-us" element={<ContactUs />} />
          <Route path="/booking-List-user" element={<BookingListOfUser />} />
          <Route path='/feedback' element={<Feedback />} />
          <Route path="/booking-List-user" element={<BookingListOfUser/>} />
          <Route path="/product-List" element={<ProductList/>} />
          <Route path="/admin_dashboard" element={<AdminDashboard/>}/>
          <Route path="/admin-FloorMap" element={<AdminFloorMap/>}/>
          <Route path="/user_details" element={<User/>}/>
          <Route path="/all_booking" element={<AllBooking/>}/>
          <Route path="/make-complaint" element={<Complaint/>}/>
          <Route path="/dashboard" element={<Dashboard/>}/>
          <Route path="/vendor" element={<VendorViewOfComplaints/>}/>
          <Route path="/add-guest" element={ <AddGuest/>} />
          <Route path="/guest" element={ <Guest/>} />
          <Route path="/signup" element={<Register/>} />
          <Route path='/payment' element={<PaypalPayment />} />
        </Routes>
      </BrowserRouter>
      <ToastContainer theme='colored' />
    </div>
  );
}

export default App;
