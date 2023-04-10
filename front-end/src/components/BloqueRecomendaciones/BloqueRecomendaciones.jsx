import React from 'react'
import CardBloqueRecomendaciones from '../CardBloqueRecomendaciones/CardBloqueRecomendaciones'
import './BloqueRecomendaciones.css'
import { useState, useEffect } from 'react'
import axios from 'axios'
import { useGlobalStates } from '../../context/GlobalContext'
import endpoint from '../../utils/endpoint.json'

const BloqueRecomendaciones = () => {

  const [recomendacion, setRecomendacion] = useState([])
  const {pressCategory,category,cities,pressBtn,fechaInicio, fechaFinal}=useGlobalStates()
  const [filterRecomend,setFilterRecomend]=useState([])
  const [filterRecomendation,setFilterRecomendation]=useState([])
  const [valid,setValid]=useState(false);


  useEffect(() =>  {
    loadRecomendaciones()
  }, [])

  useEffect(()=>{
    setFilterRecomend(recomendacion.filter((rec)=>rec.categoria.titulo==category))
  },[pressCategory])

  useEffect(()=>{
    if(cities !== null){
      // const data = axios.get(`${endpoint.url}/productos/dateCiudad?fechaInicio=${fechaInicio}&fechaFinal=${fechaFinal}&ciudadId=${cities.id}`)
      // .then(data => setFilterRecomend(data.data))
      // console.log(data);
      setFilterRecomendation(recomendacion.filter((rec)=>rec.ciudad.nombre_ciudad==cities.nombre_ciudad))
    }
    setValid(true)
  },[pressBtn])

  useEffect(()=>{
    setValid(false)
  },[pressCategory])

  const loadRecomendaciones = async () => {
      const data = await axios.get(`${endpoint.url}/productos/random`)
      setRecomendacion(data.data)
  }

  return (
  <div>
    <h2 style={{ marginLeft: "30px",marginTop:'20px' }}>{category == null && !valid != '' ? 'Recomendaciones' : 'Resultados de busqueda'}</h2>
    <div className='contenedorRecomendaciones'>
      <div className='divRecomendaciones'>
        {valid && cities !== null && (

          filterRecomendation?.map(recomendacion => <CardBloqueRecomendaciones key={recomendacion.id} recomendacion={recomendacion}/>)
        )}
        { category == null && !valid &&(
            
              recomendacion?.map(recomendacion => <CardBloqueRecomendaciones key={recomendacion.id} recomendacion={recomendacion}/>)
          
          )}
        {
          !valid && category != '' &&(
            filterRecomend?.map(recomendacion=><CardBloqueRecomendaciones key={recomendacion.id} recomendacion={recomendacion}/>)
          )
        }
      </div>
    </div>
</div>
  )
}

export default BloqueRecomendaciones