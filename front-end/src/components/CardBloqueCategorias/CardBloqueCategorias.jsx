import React from 'react'
import { useGlobalStates } from '../../context/GlobalContext'
import './CardBloqueCategorias.css'

const CardBloqueCategorias = ({categoria}) => {

    const {setPressCategory,setCategory,pressCategory}=useGlobalStates()

    const handleClick=(e)=>{
        setPressCategory(!pressCategory)
        setCategory(categoria.titulo)
    }
    return (
        <div className='cardCategoria' onClick={handleClick}>
            <img className='imagenCategoria' src={categoria.urlImagen} />
            <h2 style={{ fontSize: "20px", margin: 0, color: "#31363F" }}>{categoria.titulo}</h2>
            <p style={{ fontSize: "14px", margin: 0, color: "#383B58" }}>3 {categoria.titulo}</p>
        </div>
    )
}

export default CardBloqueCategorias