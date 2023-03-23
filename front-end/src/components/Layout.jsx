import React from 'react'
import BloqueCategorias from './BloqueCategorias/BloqueCategorias'
import BloqueRecomendaciones from './BloqueRecomendaciones/BloqueRecomendaciones'
import Footer from './Footer/Footer'
import Header from './Header/Header'
import Navbar from './Navbar/Navbar'

const Layout = () => {
  return (
    <>
      <Header onChange={'home'}/>
      <Navbar />
      <BloqueCategorias />
      <BloqueRecomendaciones/>
    </>
    
  )
}

export default Layout