import React, { useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid'
import { jwtDecode } from 'jwt-decode';
import NavbarComponent from '../components/Navbar/NavbarComponent';
import UserApi from '../service/UserApi';
import { useNavigate } from 'react-router-dom';

const Userpage = () => {
    const userToken = localStorage.getItem("token");
    let currentUserName = null;
    if (userToken != null) {
        const data = jwtDecode(userToken);
        currentUserName = data.sub;
    }

    const navigation = useNavigate();
    const [getUsername, setUsername] = useState("");

    const handleUpdate = async (e) => {
        e.preventDefault();
        const response = await UserApi.Update(getUsername);
        console.log(response);
        localStorage.removeItem("token");
        navigation("/home");
    }

    return (
        <>  
            <NavbarComponent systemUserName={currentUserName}/>
            <Grid container spacing={1} justifyContent="center" alignItems="center" style={{ color: "white" }}>
                <Grid item lg={6} md={8} sm={10} xs={10} marginTop={20} >
                    <form onSubmit={(e) => handleUpdate(e)} action="">
                        <div id='mainDiv'>
                            <h5>Yeni kullanıcı adınızı giriniz</h5>
                            <div id='input-height' className="input-group flex-nowrap">
                                <span className="input-group-text" id="addon-wrapping">
                                    @
                                </span>
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Kullanıc Adı"
                                    aria-label="Username"
                                    aria-describedby="addon-wrapping"
                                    onChange={(e) => setUsername(e.target.value)}
                                />
                            </div>
                            <Button type='submit' id='calculate-button' variant="contained">Güncelle</Button>
                        </div>
                    </form>
                </Grid>
            </Grid>
        </>
    )
}

export default Userpage