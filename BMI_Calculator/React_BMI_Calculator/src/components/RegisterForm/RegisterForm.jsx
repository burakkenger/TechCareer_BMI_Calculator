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

const RegisterForm = () => {

  const navigation = useNavigate();
  const [getUsername, setUsername] = useState("");
  const [getPassword, setPassword] = useState("");
  const [getName, setName] = useState("");
  const [getSurname, setSurname] = useState("");
  const [getMailAdress, setMailAdress] = useState("");
  const [getError, setError] = useState("");

  const handleSumbit = async (e) => {
    e.preventDefault();

    const payload = {
      username: getUsername,
      password: getPassword,
      name: getName,
      surname: getSurname,
      email: getMailAdress
    }

    try {
      const response = await AuthenticationApi.Register(payload);
      if (response.status == 200) {
        navigation("/Login");
      }
    }
    catch (err) {
      setError(err.response.data.validationErrors)
      console.log(err)
    }

  }


  return (
    <>
      <form onSubmit={(e) => handleSumbit(e)} className="form_main" action="">
        <p className="heading">Kayıt Ol</p>
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

        {
          getError ?
            <div>{getError.username}</div>
            : ''
        }

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
        <div className="inputContainer">
          <input
            value={getName}
            onChange={(e) => setName(e.target.value)}
            placeholder="Ad"
            id="username"
            className="inputField"
            type="text"
          />
        </div>
        <div className="inputContainer">
          <input
            value={getSurname}
            onChange={(e) => setSurname(e.target.value)}
            placeholder="Soyad"
            id="username"
            className="inputField"
            type="text"
          />
        </div>
        <div className="inputContainer">
          <input
            value={getMailAdress}
            onChange={(e) => setMailAdress(e.target.value)}
            placeholder="Mail Adresi"
            id="username"
            className="inputField"
            type="text"
          />
        </div>
        <button type='submit' id="button">Kayıt Ol</button>
        <div className="signupContainer">
        </div>
      </form>
    </>
  )
}

export default RegisterForm