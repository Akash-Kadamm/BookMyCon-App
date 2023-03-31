import React from 'react'
import '../../css/AboutUs.css'
import Footer from './Footer'


function AboutUs() {
    return (
        <div>
            <div className='topBody'>
                <div className='topLeft'>
                    <span>
                        About Us
                    </span>

                    <p>
                        a global worktech company that provides software solutions for workplaces, people, and assets that enable everyone to reach their full potential.
                    </p>
                </div>
            </div>
            <div id='blueLine'></div>

            <div className='middleBody'>
                <h3>We enable workplaces, people, and assets to perform at their best through a better understanding of how the world works.</h3>
            </div>

            <div className='bottomPage'>
                <h2>
                    Why Book Conference
                </h2>
                <p>
                    Expertise in how the world works? We’ve got it. We’re the only worktech company with a comprehensive suite of products that truly understand what makes your business tick.
                </p>
                <div className='belowTitle'>
                    <div>
                        <div>
                            <h3>Global reach</h3>
                            <p>Our 1,000+ employees are based all around the world. Wherever you are, we’re never more than a click away.</p>
                        </div>
                        <div>
                            <h3>Powerful partnerships</h3>
                            <p>We know the power of good friends. We’re Microsoft Gold Partners, members of the Autodesk Alliance, and work closely with our trusted partners around the world.</p>
                        </div>

                    </div>
                    <div>
                        <div>
                            <h3>Global reach</h3>
                            <p>Our 1,000+ employees are based all around the world. Wherever you are, we’re never more than a click away.</p>
                        </div>
                        <div>
                            <h3>Powerful partnerships</h3>
                            <p>We know the power of good friends. We’re Microsoft Gold Partners, members of the Autodesk Alliance, and work closely with our trusted partners around the world.</p>
                        </div>

                    </div>
                    <div>
                        <div>
                            <h3>Global reach</h3>
                            <p>Our 1,000+ employees are based all around the world. Wherever you are, we’re never more than a click away.</p>
                        </div>
                        <div>
                            <h3>Powerful partnerships</h3>
                            <p>We know the power of good friends. We’re Microsoft Gold Partners, members of the Autodesk Alliance, and work closely with our trusted partners around the world.</p>
                        </div>

                    </div>
                </div>
            </div>
            <Footer />

        </div>
    )
}

export default AboutUs
