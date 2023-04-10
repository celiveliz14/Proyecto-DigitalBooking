import * as React from 'react';
import './reservas.css'


const CardReservas = ({titulo,fechaInicio,fechaFinal,imagen}) => {
  return (
    <div className='bloqueRecomendaciones'>
    <div>
        <img className='imagenRecomendaciones' src={imagen} alt="imagen" />
    </div>
    <div className='divCardRecomendaciones'>
        <div>
            <h1 className='tituloSpan'>{titulo}</h1>
        </div>
        <p className='ubicacionP'>{fechaInicio}</p>
        <p className='ubicacionP'>{fechaFinal}</p>
    </div>
</div>
  )
}

export default CardReservas