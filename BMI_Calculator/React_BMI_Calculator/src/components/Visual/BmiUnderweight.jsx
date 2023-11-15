import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import MuiAlert from '@mui/material/Alert';

const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

const BmiUnderweight = ({bmiResult}) => {
    console.log(bmiResult);
    return (
        <>
            <div className="d-flex flex-column mb-3">
                <div className="p-2"><img style={{ width: "200px", height: "500px" }} src="../../../public/images/BMI_underweight.png" alt="" /></div>
                <div className="p-2"><h4>{bmiResult}</h4></div>
                <div className="p-2" style={{width:"200px", margin:"auto"}}><Alert severity="info">Zayıf</Alert></div>
            </div>
        </>
    )
}

export default BmiUnderweight