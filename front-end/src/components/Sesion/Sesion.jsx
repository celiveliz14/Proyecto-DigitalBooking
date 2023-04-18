import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import './Sesion.css'
import SideMenuBar from '../Header/Menu/SideMenuBar'

const Sesion = ({onChange}) => {
  const [toogle,setToogle]=useState(onChange)
  return (
    <>
    <div className='divSesion' >
      {
        toogle == 'login' && <Link to={'/signup'} style={{marginLeft:'20vw'}}><button className='btnSesion'>Crear cuenta</button></Link>
      }
      {
        toogle == 'signup' && <Link to={'/login'} style={{marginLeft:'20vw'}}><button className='btnSesion'>Iniciar sesión</button></Link>
      }
      {
        toogle == 'home' && (
          <>
          <Link to={'/signup'}><button className='btnSesion'>Crear cuenta</button></Link>
          <Link to={'/login'}><button className='btnSesion2'>Iniciar sesión</button></Link>
          </>       
        )
      }
    </div>
    <div className='logoSesion'>
       <SideMenuBar/>
    </div>
    </>
  )
}

export default Sesion