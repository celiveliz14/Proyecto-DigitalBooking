import React from 'react'
import CardBloqueCategorias from '../CardBloqueCategorias/CardBloqueCategorias'
import './BloqueCategorias.css'
import axios from 'axios'
import { useEffect,useState } from 'react'
import endpoint from '../../utils/endpoint.json'
import { useGlobalStates } from '../../context/GlobalContext'

const BloqueCategorias = () => {

    const [categoria, setCategoria] = useState([])
    const {categorias, setCategorias} = useGlobalStates()
    

    useEffect(() =>  {
        loadCategorias()
    }, [])

    const loadCategorias = async () => {
        const data = await axios.get(`${endpoint.url}/categorias`)
        setCategoria(data.data)
        setCategorias(data.data)
    }
    
    return (
        <div className='hola'>
            <span>
                <h2 style={{ marginLeft: "20px",padding:'10px' }}>Buscar por tipo de alojamiento</h2>
            </span>
            <div className='containerCategorias'>
                <div className='divCategorias'>
                {
                    categoria?.map(categoria => <CardBloqueCategorias key={categoria.id} categoria={categoria} />)
                }
                 </div>
            </div>
            

        </div>
    )
}

export default BloqueCategorias