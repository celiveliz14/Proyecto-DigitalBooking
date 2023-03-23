import './Navbar.css'
import {DateObject } from "react-multi-date-picker"
import DatePicker from "react-multi-date-picker"
import { useState,useEffect } from 'react'
import transition from "react-element-popper/animations/transition"
import axios from 'axios'
import logoGps from "../../img/logo-gps.png"
import Dropdown from './Dropdown/Dropdown'
import { useGlobalStates } from '../../context/GlobalContext'
const Navbar = () => {

  const [ciudad, setCiudad] = useState([])
  const [data,setData]=useState([])
  const {pressBtn,setPressBtn}=useGlobalStates()

  useEffect(() =>  {
      loadCategorias()
  }, [])

  const loadCategorias = async () => {
      const data = await axios.get("http://localhost:8080/ciudades")
      setCiudad(data.data)
  }
  const [value, setValue] = useState([])

  function handleChange(value){
    setValue(value)
  }
  function handleSubmit(e){
    e.preventDefault()
  }
  function handleClick(e){
    e.preventDefault()
    setPressBtn(!pressBtn)
  }
  return (
  <div className='navBar'>
    <h1 className='h1Navbar' style={{color:"white",marginBottom:'20px'}}>Busca ofertas en hoteles, casas y mucho más</h1>
    <form className='formNavbar'>
      <Dropdown data={ciudad} value={'city'}/>
        <DatePicker
          placeholder="Check in - Check out"
          style={{height:'35px',border:'none',fontWeight:'900'}}
          range
          onChange={handleChange}
          class='testClass'
          minDate={new Date()}
          animations={[
            transition({ duration: 800, from: 35 })
          ]} 
        />      
      <button className='buttonNavBar'onClick={handleClick}>Buscar</button>  
    </form>
</div>
  )
}

export default Navbar