import React, { useEffect, useState } from 'react'
import NavbarComponent from '../components/Navbar/NavbarComponent'
import UserApi from '../service/UserApi.js';
import Backdrop from '@mui/material/Backdrop';
import CircularProgress from '@mui/material/CircularProgress';
import { jwtDecode } from 'jwt-decode';
import BmiForm from '../components/BmiForm/BmiForm.jsx';

const Home = () => {

  const userToken = localStorage.getItem("token");
  let currentUserName = null;
  if (userToken != null) {
    const data = jwtDecode(userToken);
    currentUserName = data.sub;
  }


  return (
      <>
        <NavbarComponent systemUserName={currentUserName} />
        <BmiForm />
      </>
  )
}

export default Home