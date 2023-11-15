import React from 'react'
import Grid from '@mui/material/Grid'
import RegisterForm from '../components/RegisterForm/RegisterForm'

export const RegisterPage = () => {
    return (
        <>
            <Grid container spacing={1} justifyContent="center" alignItems="center">
                <Grid item lg={6} md={8} sm={10} xs={10} marginTop={20} >
                    <RegisterForm />
                </Grid>
            </Grid>
        </>
    )
}
