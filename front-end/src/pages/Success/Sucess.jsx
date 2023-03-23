import React from 'react'
import { Link } from 'react-router-dom'
import Header from '../../components/Header/Header'
import './success.css'

const Success = () => {
  return (
    <>
    <Header onChange={"home"}/>
    <div className='containerSuccess'>
        <div className='cardSuccess'>
          <div className='success'> 
           <svg width="90px" height="100px" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" strokeWidth="0"></g><g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g><g id="SVGRepo_iconCarrier"> <rect width="48" height="48" fill="white" fill-opacity="0.01"></rect> <path d="M24 4L29.2533 7.83204L35.7557 7.81966L37.7533 14.0077L43.0211 17.8197L41 24L43.0211 30.1803L37.7533 33.9923L35.7557 40.1803L29.2533 40.168L24 44L18.7467 40.168L12.2443 40.1803L10.2467 33.9923L4.97887 30.1803L7 24L4.97887 17.8197L10.2467 14.0077L12.2443 7.81966L18.7467 7.83204L24 4Z" fill="#F0572D" stroke="#F0572D" strokeWidth="4" strokeLinecap="round" strokeLinejoin="round"></path> <path d="M17 24L22 29L32 19" stroke="white" strokeWidth="4" strokeLinecap="round" strokeLinejoin="round"></path> </g></svg>
           <h1>¡Muchas Gracias!</h1>
           <h2 style={{marginTop:'10px'}}>Su reserva se ha realizado con exito</h2>
           <Link to={'/'}>
            <button style={{cursor:'pointer'}} className='btnSuccess'>
            Ok
           </button>
           </Link>
           
          </div>
        </div>
    </div>
    </>
    
  )
}

export default Success