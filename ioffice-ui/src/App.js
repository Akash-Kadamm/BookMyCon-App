import './App.css';
import Login from './Component/Login';
import UpdateUser from './Component/UpdateUser';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { UserList } from './Component/UserList';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Navigation from './Component/Navigation';
import AddAudi from './Component/AddAudi';
import { AuditoriumList } from './Component/AuditoriumList';
import UpdateAuditorium from './Component/UpdateAuditorium';
import BookMeeting from './Component/BookMeeting';
import Registration from './Component/Registration';
import AboutUs from './Component/AboutUs';
import ContactUs from './Component/ContactUs';
import HomePage from './Component/HomePage'

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
          <Route path="/user-list" element={<UserList />} />
          <Route path="/auditorium-update/:id" element={<UpdateAuditorium />} />
          <Route path="/add-auditorium" element={<AddAudi />} />
          <Route path="/auditorium-list" element={<AuditoriumList />} />
          <Route path="/auditorium-Booking" element={<BookMeeting />} />
          <Route path="/about-us" element={<AboutUs />} />
          <Route path="/contact-us" element={<ContactUs />} />
        </Routes>
      </BrowserRouter>
      <ToastContainer theme='colored' />
    </div>
  );
}

export default App;
