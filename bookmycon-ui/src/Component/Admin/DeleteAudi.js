import React from 'react';
import axios from 'axios';

export const DeleteAudi = (id) => {
  const url = `http://localhost:8080/admin/${id}`;
  axios.delete(url)
    .then((response) => {
      console.log(response)
    }).catch((error => console.log("error:")));
  alert("deleted successfully")


  return (
    <div></div>
  )
}