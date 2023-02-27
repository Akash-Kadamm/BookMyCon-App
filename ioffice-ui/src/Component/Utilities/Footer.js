import React from 'react'
import '../../css/Footer.css'
// import logo from '../Img/eptura-dark-svg-TM.svg'
import { useNavigate } from "react-router-dom";





function Footer() {
    const navigate = useNavigate();
const    handleClick=()=>{
    navigate('/aboutus')

    }
    return (
        <div>
            <footer className='bottomFooter'>
                <div className='footerContent'>
                    <div className='footerText'>
                        <h4>Company</h4>
                        <p>We're a worktech company providing global solutions for workplaces, people, and assets to create a better future for everyone</p>
                        
                    </div>
                    <div className='footerText'>
                        <h4>Product</h4>
                        <p>Our work solutions are built with technology that gets you, so you can perform at your best.</p>
                        
                    </div>
                    <div className='footerText'>
                        <h4>Discover</h4>
                        <ul>
                            <li
                            onClick={()=>handleClick()}>
                                About Us
                            </li>
                        </ul>
                    </div>
                </div>
                <hr></hr>
                <div className='belowHr'>
                    {/* <img src={logo}/> */}
                    <ul>
                        <li>
                            Saas Policy
                        </li>
                        <li>
                            Privacy Policy
                        </li>
                        <li>
                            Terms and Conditions
                        </li>
                    </ul>
                </div>
            </footer>
        </div>
    )
}

export default Footer
