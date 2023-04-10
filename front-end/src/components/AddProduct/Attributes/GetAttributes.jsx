import React from 'react'
import './getAttributes.css'
import { useGlobalStates } from '../../../context/GlobalContext'
import { useEffect, useState } from 'react'
import axios from 'axios'
import endpoint from '../../../utils/endpoint.json'

const GetAttributes = () => {

  const { caracteristicas, setCaracteristicas, atributos, setAtributos } = useGlobalStates()
  const [objetoAtributo, setObjetoAtributo] = useState({id:"1"})

  useEffect(() => {
    loadCaracteristicas()
  }, [])

  const loadCaracteristicas = async () => {
    const data = await axios.get(`${endpoint.url}/caracteristicas`)
    setCaracteristicas(data.data)
  }

  return (
    <>
      <h3 style={{ paddingTop: '30px', paddingBottom: '10px', paddingLeft: '30px' }}>Agregar atributo</h3>
      <div className='containerAttributes'>
        <div className='attributes'>
          {caracteristicas?.map((c) => (
            <div className='containerAttributesInput'>
              <div className='inptImgAttributes'>
                <img style={{height:"25px",width:"25px"}} src={c.icono} alt="logo"/>
                <label style={{fontWeight:'bolder'}}> {c.nombre}</label>
              </div>
              <div className='inputAttributes'>
                <input style={{marginTop:'5px',marginLeft:'5px'}} onChange={(e) => {
                console.log(e)
                setObjetoAtributo({id:[e.target.id][0]})
                setAtributos([...atributos, objetoAtributo])
                }} type="checkbox" id={c.id}/>
            </div>
              </div>
              
          ))}
        </div>
      </div>
    </>

  )
}

export default GetAttributes