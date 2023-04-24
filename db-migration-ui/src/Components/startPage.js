import React from 'react'
import "./startPage.css";
import img4 from "../img/img4.png"




export default function startPage () {
  return (
   
    
         
    <div className='page-banner-container'>
     
    <div className='page-text-section'>
      <h3 className='pri-heading'>
      Data migration 
      </h3>
      <p className='pri-text'>
      The origin of data migration can be traced back to the early days of computer systems 
      when organizations began to realize the need for transferring data from one system to another. 
      As technology evolved and businesses grew, data migration became a 
      critical process for organizations that needed to upgrade their hardware, software, or storage systems. 
      </p>
    </div>
    <div className='page-image-container'>
      <img src={img4} alt="">
      </img>
    </div>
        </div>
    
    
  )
}