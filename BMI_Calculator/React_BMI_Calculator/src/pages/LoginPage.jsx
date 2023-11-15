import React from 'react'
import LoginForm from '../components/LoginForm/LoginForm'
import Box from '@mui/material/Box'
import Grid from '@mui/material/Grid'

const LoginPage = () => {
  return (
    <>
      <Grid container spacing={1} justifyContent="center" alignItems="center">
        <Grid item lg={6} md={8} sm={10} xs={10} marginTop={20} >
          <LoginForm />
        </Grid>
      </Grid>
    </>
  )
}

export default LoginPage