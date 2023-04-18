import React from 'react'
import "./reservas.css"
import { useEffect, useState } from 'react';
import Swal from "sweetalert2";
import { useGlobalStates } from '../../context/GlobalContext';
import axios from 'axios';
import Header from '../Header/Header';
import CardReservas from './CardReserva';
import endpoint from '../../utils/endpoint.json'

const ListReservas = () => {
    const { data } = useGlobalStates()
    const [reservas, setReservas] = useState([])
    const [vacio, setVacio] = useState("")
    const [cardSize,setCardSize]=useState()

    const loadReservas = async () => {
        const r = await axios.get(`${endpoint.url}/reservas/user/${data.id}`)
        .then((data) => {
            setReservas(data.data)
        })
        .catch((err) => {
          setVacio("VACIA")
        })
    }
    useEffect(() => {
        loadReservas()
      }, [])

    const mostrarAlerta = () => {
        Swal.fire({
            title:"RESERVAS VACIAS",
            text:"Realice una reserva para verla aqui",
            icon:'warning',
            timer:'25000',
            confirmButtonColor: "#F0572D"
        })
    }

  return (
    <>
    <Header onChange={"home"} />
    <h1 className='centro'>Mis reservas</h1>
    <div className='containerListReservas'> 
      <div className='containerReservas'>
        {reservas?.map((r)=> <CardReservas key={r.id} producto={r.producto} fechaInicio={r.fechaInicio} fechaFinal={r.fechaFinal}/>)}
        {vacio === "VACIA" ? mostrarAlerta() : ""}
      </div>
    </div>
    </>
  )
}

export default ListReservas