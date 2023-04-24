import React from 'react'
import "./Home.css"
import IM from "../img/IM.png"
import'./footer.js';
import { Link } from 'react-router-dom'


const Home = () => {
  
  return (
<>
    <div className='home-banner-container'>
     
    <div className='home-text-section'>
      <h3 className='primary-heading'>
      Data migration 
      </h3>
      <p className='primary-text'>
      It is the process transforming it to meet the requirements of the target system,and loading it into the destination.It is essential for upgrading systems,changing platforms,
      and consolidating data.
      </p>

      <Link to="/startPage">
      <button  className='secondary-button' >
        Get started

        
        <span style={{
                cursor:"pointer"}}>
                  
                </span>
                
      </button>
      </Link>
    </div>
    <div className='home-image-container'>
      <img src={IM} alt="">
      </img>
    </div>

    {/* <div className='home-bannerImage-container'>
        <img src={img2} alt=""></img>
    </div> */}



    </div>
    </>
      )
}

export default Home
