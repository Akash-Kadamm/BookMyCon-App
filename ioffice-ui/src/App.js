import logo from './logo.svg';
import './App.css';
import Login from './Component/Utilities/Login';
import UpdateUser from './Component/User/UpdateUser';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { UserList } from './Component/UserList';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Navigation from './Component/Navigation';
import AddAudi from './Component/Admin/AddAudi';
import { AuditoriumList } from './Component/Admin/AuditoriumList';
import UpdateAuditorium from './Component/UpdateAuditorium';
import BookMeeting from './Component/User/BookMeeting';
import Registration from './Component/Utilities/Registration';
import AboutUs from './Component/Utilities/AboutUs';
import ContactUs from './Component/Utilities/ContactUs';
import BookingListOfUser from './Component/User/BookingListOfUser';
import { AdminFloorMap } from './Component/Admin/AdminFloorMap';
import HomePage from './Component/Utilities/HomePage';
import ProductList from './Component/ProductList';
import Cart from './Component/User/Cart';
import AdminDashboard from './Component/Admin/AdminDashboard';
import User from './Component/User';
import AllBooking from './Component/Admin/AllBooking';
import Complaint from './Component/Complaint';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navigation />
        <Routes>
          <Route exact path="/" element={<HomePage />} />
          <Route path="/signin" element={<Login />} />
          <Route path="/signup" element={<Registration />} />
          <Route path="/user-update" element={<UpdateUser />} />
          <Route path="/cart" element={<Cart/>} />
          <Route path="/user-list" element={<UserList />} />
          <Route path="/auditorium-update/:id" element={<UpdateAuditorium />} />
          <Route path="/add-auditorium" element={<AddAudi />} />
          <Route path="/auditorium-list" element={<AuditoriumList />} />
          <Route path="/auditorium-Booking" element={<BookMeeting />} />
          <Route path="/about-us" element={<AboutUs />} />
          <Route path="/contact-us" element={<ContactUs />} />
          <Route path="/booking-List-user" element={<BookingListOfUser/>} />
          <Route path="/product-List" element={<ProductList/>} />
          <Route path="/admin_dashboard" element={<AdminDashboard/>}/>
          <Route path="/user_details" element={<User/>}/>
          <Route path="/all_booking" element={<AllBooking/>}/>
          <Route path="/make-complaint" element={<Complaint/>}/>
        </Routes>
      </BrowserRouter>
      <ToastContainer theme='colored' />
    </div>
  );
}

export default App;