import './App.css';
import  Navbar from './Components/navbar';
import { BrowserRouter,Routes,Route } from "react-router-dom";
import Home from './Components/Home';
import Profile from './Components/Profile';
import Footer from './Components/footer';
import None from './Components/None';
import StartPage  from './Components/startPage';



function App() {
  return (
    <BrowserRouter>
    <div className="App">
     <Navbar></Navbar> 
     <Routes>
     <Route path="/" element={<Home/>}></Route>
      <Route path="/Home" element={<Home/>}></Route>
      <Route path="/profile" element={<Profile/>}></Route> 
      <Route path="/None" element={<None/>}></Route>
      <Route path="/startPage" element={<StartPage/>}></Route>
     </Routes>
     <Footer></Footer>
    </div>

    </BrowserRouter>
  
  );
}

export default App;


