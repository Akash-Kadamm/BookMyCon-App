import React from 'react'
import '../css/ContactUs.css'

const ContactUs = () => {
    return (
        <div className="container contact-group text-center ">
             <br />
            <img src='https://images.unsplash.com/photo-1596524430615-b46475ddff6e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8Y29udGFjdCUyMHVzfGVufDB8fDB8fA%3D%3D&w=1000&q=80' width="100%" height="600px" alt="location" className="img-responsive " />
            <br />
            <div className="row">
                <div className="container">

                    <table className="table">
                        <tbody>
                            <tr>
                                <td>Mobile : </td>
                                <td> 8945632147 </td>
                            </tr>
                            <tr>
                                <td>Whatsapp : </td>
                                <td> 9456123687 </td>
                            </tr>
                            <tr>
                                <td>Email : </td>
                                <td><strong> Conference.hall@gmail.com</strong> </td>
                            </tr>
                            <tr>
                                <td>Address: </td>
                                <td>Sr.No 22, Cybage software tower 1,kalyani Nagar, Pune 41014 </td>
                            </tr>
                        </tbody>
                    </table>
                    <br />
                </div>
            </div>
        </div>
    )
}

export default ContactUs
