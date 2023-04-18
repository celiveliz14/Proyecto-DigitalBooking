import React from 'react'
import Logos from '../Logos/Logos'
import './Footer.css'

const Footer = () => {
  return (
    <footer className='footer'>
        <p className='pFooter'>©2023 Digital Booking</p>  
        <Logos menu={false}/>
    </footer>
  )
}

export default Footer