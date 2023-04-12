import React from 'react'
import ImgL from "../img/ImgL.png"
import "./navbar.css";
import { Link } from 'react-router-dom';

export default function navbar() {
  return (
    <div className='navbar'>
        <img src={ImgL} alt="" itemType='submit' url='http://localhost:3000/'/>
        <ul className="nav-menu"></ul>


        <Link to="/Home">
        <li>Home</li></Link>

        <Link to="/profile"> 
        <li>Company Details</li></Link>

        <Link to="/None"> 
        <li>User Details</li></Link>
    </div>
    
  )
}
