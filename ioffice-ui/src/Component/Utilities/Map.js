import React from 'react'
import pic1 from '../Image/1.png'; 
import pic2 from '../Image/2.png'; 
import pic5 from '../Image/5.png'; 

import pic6 from '../Image/6.png'; 
import pic7 from '../Image/7.png'; 

import pic11 from '../Image/11.png'; 
import pic14 from '../Image/14.png'; 

import pic15 from '../Image/15.png'; 

import pic16 from '../Image/16.png'; 

import pic18 from '../Image/18.png'; 

import pic20 from '../Image/20.png'; 
import { useNavigate } from 'react-router-dom';

export const Map = () => {
        const navigate = useNavigate();
        const handleDiv1= (e) => {
                  
                navigate('/bookMeeting')
              }
        const handleDiv5= (e) => {
          
                navigate('/bookMeeting')
              }
        const handleDiv6= (e) => {
                navigate('/bookMeeting')
              }
              const handleDiv7= (e) => {
                navigate('/bookMeeting')
              }
              const handleDiv8= (e) => {
                navigate('/bookMeeting')
              }
              const handleDiv9= (e) => {
                navigate('/bookMeeting')
              }
              const handleDiv14= (e) => {
                navigate('/bookMeeting')
              }
              const handleDiv15= (e) => {
                navigate('/bookMeeting')
              }

  return (
<div style={{  border: '2px solid black',
        width: 630, height: 490,}}>
            
    <div style={{  float:'left' }}>
            <div 
            style={{  }}><img src={pic1}  style={{ 
                    width: 35, height: 220,}} /></div>
            
    </div>


    <div  style={{  float:'left' }}>

            <div onClick={handleDiv1} 
            id="audi1"
            style={{  }}>
                    <img src={pic2}  style={{   
                    width: 230, height: 172,}} /></div>

            <div style={{ }}>
                    <img src={pic20}  style={{
                    width: 230, height: 40,}} /></div>
    </div>
            
            
            
    <div style={{ float:'left' }}>
            <div style={{  }}>
                    <img src={pic20}  style={{   
                     width: 285, height: 80,}} />
                     
            </div>
             
             
             
             <div style={{float:'left' }}>
           
           
            <div  onClick={handleDiv5} 
            style={{ }}>
                 <img src={pic5}  style={{  
                     width: 110, height: 110,}} /></div>
             <div style={{ }}>
                  <img src={pic20}  style={{  
                  width: 110, height: 18,}} /></div>

            </div>
            <div onClick={handleDiv6}  style={{   }}>
            <img src={pic6}  style={{  
          width: 175, height: 136,}} /></div>

            
    </div>
    <div style={{  }}>

                     <div style={{  }}>
                    <img src={pic20}  style={{   
                    width: 64, height:220,}} /></div>
                    
                   
    </div> 
    <div style={{ float:'left'  }}>

                    <div onClick={handleDiv7} 
                    style={{  }}>
                    <img src={pic16}  style={{  
                    width: 135, height:85,}} /></div>
                    
                    
                    <div onClick={handleDiv8}  
                     style={{ }}>
                    <img src={pic16}  style={{  
                     width: 135, height:85}} /></div>


                    <div onClick={handleDiv9} 
                     style={{ }}>
                    <img src={pic16}  style={{  
                     width: 135, height:85}} /></div>
                 
    </div>   
    <div style={{  }}>

                     <div 
                      style={{ float:'left'  }}>
                    <img src={pic11}  style={{  
                    width: 430, height:50,}} /></div>
                    
                    <div  style={{  }}>
                    <img src={pic20}  style={{   
                     width: 57, height:50}} /></div>


    </div>             


    <div style={{ float:'left'  }}>

                  <div onClick={handleDiv14}  style={{  }}>
                    <img src={pic14}  style={{  
                     width: 300, height:130}} /></div>
                     <div style={{ }}>
                    <img src={pic20}  style={{  
                     width: 300, height:55}} /></div>
                     
    </div>     
    <div style={{ float:'left'  }}>
                 <div onClick={handleDiv15}   style={{ }}>
                    <img src={pic15}  style={{   
                     width: 190, height:185}} /></div>
                 
                 

    </div> 
    <div style={{ float:'left'}}>
                    <img src={pic18}  style={{
                     width:491, height:15}} /></div>
</div>
  )
}


// border: '2px solid black',