import React from 'react'
import '../../css/NonUserHomePage.css'
import logo from '../../Image/eptura-dark-svg-TM.svg'
import topRightImg from '../../Image/banner-image-home.jpg'
import middleContentImg from '../../Image/solution-img1.jpg'
import middleContentSubImg from '../../Image/solution-img2.jpg'
import Footer from '../Utilities/Footer'
 const NonUserHomePage = () => {
  return (
    <div>
        
            <div className='body'>
                <div className='topBody'>

                    <div className='topLeft'>
                        <span style={{color:"white"}}>
                            Book Your
                        </span>
                        <div>
                            Conference
                        </div>
                        <p>
                            a global worktech company that provides software solutions for workplaces, people, and assets that enable everyone to reach their full potential.
                        </p>
                     

                    </div>
                    <img src={topRightImg} />

                </div>
                <div id='blueLine'></div>

                <div className='middleBody'>
                    <div className='middleBodyHeader'>
                        <h2>
                            All your worktech needs in one place
                        </h2>
                    </div>
                    <div className='middleBodyContent'>
                        <div>
                            <h3>Workplace Solutions</h3>
                            <p>
                                We help employers reimagine their workplaces by giving them insightful
                                data to make strategic, well-informed decisions.
                                Through our integrated workplace solutions.
                            </p>
                            <div className='middleContentSubtext'>
                                <img src={middleContentSubImg} />
                                <div>
                                    <h3>People Solutions</h3>
                                    <p>
                                        We enable employees to perform at their best by giving them control over how they choose to work. Our workspace scheduling software, visitor management solutions, and suite of integrations help ensure.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <img src={middleContentImg} />

                    </div>

                </div>
            </div>
            <Footer/>
        </div>
  )
}

export default  NonUserHomePage;