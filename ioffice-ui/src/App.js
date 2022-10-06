import logo from './logo.svg';
import './App.css';
import Login from './Component/Login';
import UpdateUser from './Component/UpdateUser';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { UserList } from './Component/UserList';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Navigation from './Component/Navigation';
import Home from './Component/Home';
import AddAudi from './Component/AddAudi';
import { AuditoriumList } from './Component/AuditoriumList';
import Register from './Component/Register';
 import UpdateAuditorium from './Component/UpdateAuditorium';
import BookMeeting from './Component/BookMeeting';




function App() {
  return (
    <div className="App">
     
      {/* <Login/> */}
       {/* <UserList/> */} 
        {/* <UpdateUser/> */}
        <BrowserRouter>
      <Navigation/>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path="/signin" element={<Login/>}/>
        <Route path="/admin-home" element={<Home/>}/>
        <Route path="/signup" element={<Register/>}/>
        <Route path="/user-update" element={<UpdateUser/>}/>
        <Route path="/user-list" element={<UserList/>}/>
        <Route path="/auditorium-update/:id" element={<UpdateAuditorium/>}/>
        <Route path="/add-auditorium" element={<AddAudi/> }/>
        <Route path="/auditorium-list" element={<AuditoriumList/> }/>
        <Route path="/auditorium-Booking" element={<BookMeeting/> }/>
      </Routes>
      </BrowserRouter>
      <ToastContainer theme='colored'/>
     {/* <BookMeeting/> */}
      {/* <AddAudi/> */}
      {/* <AuditoriumList/> */}
    </div>
  );
}

export default App;
