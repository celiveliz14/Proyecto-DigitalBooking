import * as React from 'react';
import './reservas.css'

const CardReservas = ({fechaInicio,fechaFinal,producto}) => {
  return (
  <div className='bloqueReservas'style={{backgroundColor:'bisque'}}>
    <div>
        <img className='imagenReservas' src={producto?.listImagen[0]?.url} alt="imagen" />
    </div>
    <div className='divCardReservas'>
        <div>
          <p style={{color:'gray',fontSize:'14px',fontWeight:'bold',marginLeft:'0.6px'}}>{producto?.categoria?.titulo}</p>
          <h1 className='tituloSpan'>{producto?.titulo}</h1>
        </div>
              <p style={{color:'black',display:'flex',fontWeight:'bold',fontSize:'14px'}}>
                <svg fill="#000000" width="21px" height="19px" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" strokeWidth="0"></g><g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M12,2a8,8,0,0,0-7.992,8A12.816,12.816,0,0,0,12,22v0H12v0a12.816,12.816,0,0,0,7.988-12A8,8,0,0,0,12,2Zm0,11a3,3,0,1,1,3-3A3,3,0,0,1,12,13Z"></path></g></svg>
                {producto?.ciudad.nombre_ciudad}, {producto?.ciudad.nombre_pais}
              </p>
              <div className="bookingCardCheck">
                <p className='pCardCheck' style={{color:'black'}}>Check in</p>
                <p className='pCardCheck' style={{color:'red'}}>{fechaInicio}</p>
              </div>
              <div className="bookingCardCheck">
                <p className='pCardCheck' style={{color:'black'}}>Check out</p>
                <p className='pCardCheck' style={{color:'red'}}>{fechaFinal}</p>
              </div>
    </div>
  </div>
  )
}

export default CardReservas