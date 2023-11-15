import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from './pages/Home'
import './App.css'
import LoginPage from './pages/LoginPage';
import { RegisterPage } from './pages/RegisterPage';
import BmiList from './pages/BmiList';
import Userpage from './pages/Userpage';

function App() {
  return (
    <>
    <Router>
      <Routes>
        <Route path="/home"  element = {<Home />}/>
        <Route path="/"  element = {<Home />}/>
        <Route path="/login" element = {<LoginPage />}/>
        <Route path="/register" element = {<RegisterPage />}/>
        <Route path="/bmi" element = {<BmiList />}/>
        <Route path="/userpage" element = {<Userpage />}/>
      </Routes>
    </Router>
    </>
  )
}

export default App
