import React, { useEffect, useState } from 'react'
import './LoginForm.css'
//import 'bootstrap/dist/css/bootstrap.min.css'
import 'jquery/dist/jquery.min.js'
//import 'bootstrap/dist/js/bootstrap.min.js'
import Grid from '@mui/material/Grid'
import UsernameSVG from '../Svg/UsernameSVG'
import PasswordSVG from '../Svg/PasswordSVG'
import AuthenticationApi from '../../service/AuthenticationApi'
import { useNavigate } from 'react-router-dom'

const LoginForm = () => {

  const navigation = useNavigate();
  const [getUsername, setUsername] = useState("");
  const [getPassword, setPassword] = useState("");

  const handleSumbit = async(e) => {
    e.preventDefault();

    const payload = {
      username: getUsername,
      password: getPassword
    }

    try {
      const response = await AuthenticationApi.Login(payload);
      localStorage.setItem("token", response.data.authToken);
      navigation("/Home");
    } 
    catch (error) {
      return error;
    }
    
  }


  return (
    <>
      <form onSubmit={(e) => handleSumbit(e)} className="form_main" action="">
        <p className="heading">Giriş Yap</p>
        <div className="inputContainer">
          <UsernameSVG />
          <input
            value={getUsername}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Kullanıcı Adı"
            id="username"
            className="inputField"
            type="text"
          />
        </div>
        <div className="inputContainer">
          <PasswordSVG />
          <input
            value={getPassword}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Şifre"
            id="password"
            className="inputField"
            type="password"
          />
        </div>
        <button type='submit' id="button">Giriş Yap</button>
        <div className="signupContainer">
          <p>Hesabın Yok Mu ?</p>
          <a href='#' onClick={() => navigation("/Register")}>Kayıt Ol</a>
        </div>
      </form>
    </>
  )
}

export default LoginForm