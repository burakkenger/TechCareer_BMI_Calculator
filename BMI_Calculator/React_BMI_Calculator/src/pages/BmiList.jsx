import React, { useState, useEffect } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import NavbarComponent from '../components/Navbar/NavbarComponent'
import Grid from '@mui/material/Grid'
import '../../public/BmiList.css'
import BmiApi from '../service/BmiApi'
import Backdrop from '@mui/material/Backdrop';
import CircularProgress from '@mui/material/CircularProgress';
import { jwtDecode } from 'jwt-decode';

const BmiList = () => {

    const userToken = localStorage.getItem("token");
    let currentUserName = null;
    if (userToken != null) {
        const data = jwtDecode(userToken);
        currentUserName = data.sub;
    }

    const [getBmiList, setBmiList] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const getData = async () => {
            const response = await BmiApi.Get();
            setBmiList(response.data);
            console.log(response.data)
            setLoading(false);
        }
        getData();
    }, []);

    const handleDelete = (key) => {
        const response = BmiApi.Delete(key);
        window.location.reload();
        console.log(response.data)
    }

    return (
        loading ? <div>
            <NavbarComponent systemUserName={currentUserName} />
            <Backdrop
                sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                open={true}
            >
                <CircularProgress color="inherit" />
            </Backdrop>
        </div> :
            <>
                <NavbarComponent systemUserName={currentUserName} />
                <Grid container spacing={1} justifyContent="center" alignItems="center">
                    <Grid item lg={6} md={8} sm={10} xs={10} marginTop={20} >
                        <table id='bmi-table' className="table table-dark border rounded table-borderless">
                            <thead style={{ backgroundColor: "red" }}>
                                <tr>
                                    <th>Boy</th>
                                    <th>Kilo</th>
                                    <th>Vücut Kitle İndeksi</th>
                                    <th>Sonuç</th>
                                    <th>Sil</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    getBmiList.map((data, index) => (
                                        <tr key={data.id}>
                                            <td >{data.height}</td>
                                            <td >{data.weight}</td>
                                            <td >{data.result}</td>
                                            <td >{data.resultName}</td>
                                            <td style={{ cursor: "pointer" }} onClick={() => handleDelete(data.id)}><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                                                {/*! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. */}
                                                <path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z" />
                                            </svg></td>
                                        </tr>
                                    ))
                                }
                            </tbody>
                        </table>
                    </Grid>
                </Grid>
            </>
    )
}

export default BmiList