import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";



const EditUser = () => {


  
  
  const navigate = useNavigate();

  const {id} =useParams()
  const [user, setUser] = useState({
    name: "",
    username: "",
    email: "",
    password:"",
    contactNo:"",
  });
  

  const { name, username, email,password,contactNo } = user;

  const onInputChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };


  const onSubmit = async (e) => {
    e.preventDefault();
   // setFormErrors(validate)
    await axios.put(`http://localhost:8080/user/${id}`, user);
    navigate("/")
  };

  const loadUsers = async() => {
    const result = await axios.get(`http://localhost:8080/user/${id}`)
    setUser(result.data);
  }

  


  useEffect(()=>{
    loadUsers()
  },[])

  return (
    <>
    



    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit Profile</h2>
          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your name"
                name="name"
                value={name}
                //ref={register()}
                onChange={(e) => onInputChange(e)} />
            </div>

            <div className="mb-3">
              <label htmlFor="Username" className="form-label">
                User Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your Username"
                name="username"
                value={username}
               // ref={register({required:"User Name is required"})}
                onChange={(e) => onInputChange(e)} />
            </div>
            {/* <p>{errors.username.message}</p> */}

            <div className="mb-3">
              <label htmlFor="Email" className="form-label">
                Email
              </label>
              <input
              readOnly
                type={"text"}
                className="form-control"
                placeholder="Enter your Email"
                name="email"
                value={email}
               // ref={register()}
                onChange={(e) => onInputChange(e)} />
            </div>

            <div className="mb-3">
              <label htmlFor="Password" className="form-label">
                Password
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your Password"
                name="password"
                value={password}
                //ref={register()}
                onChange={(e) => onInputChange(e)} />
            </div>


            <div className="mb-3">
              <label htmlFor="Contact" className="form-label">
                Contact No
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your Contact No"
                name="contactNo"
                value={contactNo}
                //ref={register()}
                onChange={(e) => onInputChange(e)} />
            </div>




            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  

      </>
  );
};

export default EditUser;


