import React, { useState } from 'react'
import Grid from '@mui/material/Grid'
import "./BmiForm.css"
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import Button from '@mui/material/Button';
import BmiCalculate from '../../util/BmiCalculate'
import BmiUnderweight from '../Visual/BmiUnderweight'
import BmiNormal from '../Visual/BmiNormal'
import BmiRiskOverweight from '../Visual/BmiRiskOverweight'
import BmiOverweight from '../Visual/BmiOverweight'
import BmiObese from '../Visual/BmiObese'
import BmiApi from '../../service/BmiApi'



const BmiForm = () => {

    const [getHeight, setHeight] = useState("");
    const [getWeight, setWeight] = useState("");
    const [getPayload, setPayload] = useState("");
    const [resultFormActive, setResultFormActive] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();

        const info = {
            height: getHeight,
            weight: getWeight
        }

        console.log(info);


        setPayload(BmiCalculate.Calculate(info));

        setResultFormActive(true);


    }

    const PostData = async() => {
        console.log(getPayload);
        const userToken = localStorage.getItem("token");
        if (userToken != null) {
            try {
                const response = await BmiApi.Add(getPayload);
                console.log(response);
            }
            catch (error) {
                return error;
            }
        }
    }

    const ResultForm = () => {
        PostData();
        const BmiType = () => {
            switch (getPayload.resultName) {
                case "Zayıf":
                    return (<BmiUnderweight bmiResult={getPayload.result} />)
                case "Normal":
                    return (<BmiNormal bmiResult={getPayload.result} />)
                case "Biraz Kilolu":
                    return (<BmiRiskOverweight bmiResult={getPayload.result} />)
                case "Kilolu":
                    return (<BmiOverweight bmiResult={getPayload.result} />)
                case "Obez":
                    return (<BmiObese bmiResult={getPayload.result} />)
            }
        }

        return (
            <>
                <Grid container spacing={1} justifyContent="center" alignItems="center" style={{ color: "white" }}>
                    <Grid item lg={6} md={8} sm={10} xs={10} marginTop={20} >
                        {BmiType()}
                    </Grid>
                </Grid>
            </>
        );
    }

    return (

        resultFormActive ? ResultForm() :
            <>
                <Grid container spacing={1} justifyContent="center" alignItems="center" style={{ color: "white" }}>
                    <Grid item lg={6} md={8} sm={10} xs={10} marginTop={20} >
                        <form onSubmit={(e) => handleSubmit(e)} action="">
                            <div id='mainDiv'>
                                <h5>Bilgilerinizi Giriniz</h5>
                                <div id='input-height' className="input-group flex-nowrap">
                                    <span className="input-group-text" id="addon-wrapping">
                                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 256 512">
                                            {/*! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. */}
                                            <path d="M0 48C0 21.5 21.5 0 48 0H208c26.5 0 48 21.5 48 48V96H176c-8.8 0-16 7.2-16 16s7.2 16 16 16h80v64H176c-8.8 0-16 7.2-16 16s7.2 16 16 16h80v64H176c-8.8 0-16 7.2-16 16s7.2 16 16 16h80v64H176c-8.8 0-16 7.2-16 16s7.2 16 16 16h80v48c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V48z" />
                                        </svg>
                                    </span>
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Boyunuz"
                                        aria-label="Username"
                                        aria-describedby="addon-wrapping"
                                        onChange={(e) => setHeight(e.target.value)}
                                    />
                                </div>

                                <div id='input-weight' className="input-group flex-nowrap">
                                    <span className="input-group-text" id="addon-wrapping">
                                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                            {/*! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. */}
                                            <path d="M128 176a128 128 0 1 1 256 0 128 128 0 1 1 -256 0zM391.8 64C359.5 24.9 310.7 0 256 0S152.5 24.9 120.2 64H64C28.7 64 0 92.7 0 128V448c0 35.3 28.7 64 64 64H448c35.3 0 64-28.7 64-64V128c0-35.3-28.7-64-64-64H391.8zM296 224c0-10.6-4.1-20.2-10.9-27.4l33.6-78.3c3.5-8.1-.3-17.5-8.4-21s-17.5 .3-21 8.4L255.7 184c-22 .1-39.7 18-39.7 40c0 22.1 17.9 40 40 40s40-17.9 40-40z" />
                                        </svg>
                                    </span>
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Kilonuz"
                                        aria-label="Username"
                                        aria-describedby="addon-wrapping"
                                        onChange={(e) => setWeight(e.target.value)}
                                    />
                                </div>
                                <Button type='submit' id='calculate-button' variant="contained">Hesapla</Button>
                            </div>
                        </form>
                    </Grid>
                </Grid>
            </>
    );
}

export default BmiForm