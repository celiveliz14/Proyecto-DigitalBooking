import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import Header from '../Header/Header.jsx';
import Dropdown from '../Navbar/Dropdown/Dropdown.jsx';
import imgFlecha from "../../img/flecha-izquierda.png";
import './addProduct.css'
import Input from '../Actions/useInput.jsx';
import GetImages from './Images/getImages.jsx';
import GetAttributes from './Attributes/getAttributes.jsx';
import { useGlobalStates } from '../../context/GlobalContext.jsx';
import endpoint from '../../utils/endpoint.json'

const AddProduct = () => {
    const [nameProp,setNameProp]=useState({ value: '', valid: null })
    const [address,setAddress]=useState({ value: '', valid: null })
    const [description,setDescription]=useState('')
    const [tituloDescripcion, setTituloDescripcion] = useState('')
    const [terms,setTerms]=useState(null)
    const [terms1,setTerms1]=useState(null)
    const [terms2,setTerms2]=useState(null)
    const [errorImg,setErrorImg]=useState(false)
    const [errorCategory,setErrorCategory]=useState(false)
    const {images, ciudades, categorias, ciudadId, categoriaId, atributos, setSucces, data,cat,cities}=useGlobalStates()
    const navigate=useNavigate()
    const [errorNameAd,setErrorNameAd]=useState(false)
    const [errorAttributes,setErrorAttributes]=useState(false)
    
    const regularExpressions = {
        text: /^[a-zA-ZÀ-ÿ\s]{4,40}$/,
        direction:/^[A-Za-z0-9\s]{4,40}$/g,
      }
      function handleSubmit(e){
        e.preventDefault();
        if (images.length < 5) {
            setErrorImg(true)
            return
          }
        if(cities ==null || cat ==null){
            setErrorCategory(true)
            return
        }
        if(nameProp.valid !== 'true' || address.valid !== 'true') {
            setErrorNameAd(true)
            return
        }
        if(atributos.length<1){
            setErrorAttributes(true)
            return
        }
        const imageness = [
        {url: images[0][0].value, titulo: images[0][1].value},
        {url: images[1][0].value, titulo: images[1][1].value},
        {url: images[2][0].value, titulo: images[2][1].value},
        {url: images[3][0].value, titulo: images[3][1].value},
        {url: images[4][0].value, titulo: images[4][1].value}
    ]
        const obj={
            titulo:nameProp.value,
            categoria:{id:categoriaId},
            ciudad:{id:ciudadId},
            caracteristicas:atributos,
            listImagen:imageness,
            tituloDescripcion:tituloDescripcion,
            descripcion:description,
            politicaLugar:terms,
            politicaSaludSeguridad:terms1,
            politicaCancelacion:terms2,
        }

        console.log(obj);

        fetch(`${endpoint.url}/productos`, {
            method: "POST",
            body: JSON.stringify(obj),
            headers: {
              'Content-type': 'application/json',
              'Authotization': `Bearer ${data.token}`
            }})
            .then((response) =>  response.text())
            .then((res) => {
                setSucces('product')
                navigate('/administration/success')
            })
            .catch((err) => {
              console.log(err);
            })
      }
  return (
    <>
    <Header onChange={"home"} />
        <div className="containerProductName containerHeaderAdmin">
          <div style={{ width: "100vw" }}>
            <h1>Administración</h1>
          </div>
          <Link to={"/"}>
            <img className="flechaProduct" src={imgFlecha} alt="" />
          </Link>
        </div>
        <h2 style={{marginBottom:'20px',marginTop:'30px',marginLeft:'40px'}}>Crear propiedad</h2>
        <div className='formAdministrationContainer'>  
            <form onSubmit={handleSubmit} action=""className='formAdministration'>
                <div className='containerAdministration'>
                    <div>
                        <Input
                        state={nameProp}
                        changeState={setNameProp}
                        label="Nombre de la propiedad"
                        type="text"
                        id="name"
                        name="text"
                        error="Sólo se permiten letras y mínimo 4 caracteres"
                        regex={regularExpressions.text}
                          />
                    </div>
                    <div className='dropdownAdministration'>
                        <p style={{color:'black',marginBottom:'6px',fontWeight:'600'}}>Categoria</p>
                        <Dropdown data={categorias} value={'category'} admin={true}/>   
                    </div>
                    <div>
                        <Input
                            state={address}
                            changeState={setAddress}
                            label="Dirección"
                            type="text"
                            id="name"
                            name="text"
                            error="Sólo se permiten letras,números y mínimo 4 caracteres"
                            regex={regularExpressions.direction}
                        />
                    </div>
                    <div className='dropdownAdministration'>
                        <p style={{color:'black',marginBottom:'6px',fontWeight:'bold'}}>Ciudad</p>
                        <Dropdown data={ciudades} value={'city'} admin={true}/>   
                    </div>  
                </div>      
                <div className='administration'>
                    <label style={{fontWeight:'bold'}} htmlFor="">Titulo Descripcion</label>
                    <textarea onChange={(e)=>setTituloDescripcion(e.target.value)} className='tAreaAdministration' minLength={5} required name="" id="" cols="30" rows="10" placeholder='Escribir aqui'></textarea>
                </div>          
                <div className='administration'>
                    <label style={{fontWeight:'bold'}} htmlFor="">Descripción</label>
                    <textarea onChange={(e)=>setDescription(e.target.value)} className='tAreaAdministration' minLength={5} required name="" id="" cols="30" rows="10" placeholder='Escribir aqui'></textarea>
                </div>
                <GetAttributes/> 
                <div>
                    <h3 className='h3Admin'>Politicas del producto</h3>
                    <div className='containerAdministrationTerms'>
                        <div className='administrationTerms'>
                            <div>
                                <h4>Normas de la casa</h4>
                                <p style={{color:'black',marginTop:'15px'}}>Descripción</p>
                                <textarea onChange={(e)=>setTerms(e.target.value)} className='tAdministrationTerms' minLength={5} required name="" id="" cols="30" rows="10"></textarea>
                            </div>
                            <div className='marTerms'>
                                <h4>Salud y seguridad</h4>
                                <p style={{color:'black',marginTop:'15px'}}>Descripción</p>
                                <textarea onChange={(e)=>setTerms1(e.target.value)} className='tAdministrationTerms'minLength={5} required name="" id="" cols="30" rows="10"></textarea>
                            </div>
                            <div className='marTerms'>
                                <h4>Politica de cancelación</h4>
                                <p style={{color:'black',marginTop:'15px'}}>Descripción</p>
                                <textarea onChange={(e)=>setTerms2(e.target.value)} className='tAdministrationTerms'minLength={5} required name="" id="" cols="30" rows="10"></textarea>
                            </div>
                        </div>
                    </div>    
                </div>
                <GetImages/>
                {errorImg && (
                    <div className='containerErrorImg'>
                      <p className='errorImg'>Debe agregar cinco imagenes</p>  
                    </div>
                )}
                {errorCategory && (
                    <div className='containerErrorImg'>
                      <p className='errorImg'>Falta agregar la categoria o la ciudad</p>  
                    </div>
                )}
                {errorNameAd && (
                    <div className='containerErrorImg'>
                      <p className='errorImg'>Falta agregar el nombre o la dirección</p>  
                     </div>
                )}
                {errorAttributes && (
                    <div className='containerErrorImg'>
                       <p className='errorImg'>Falta agregar uno o más atributos</p>  
                     </div>
                )}
                <div className='containerBtnAdministration'>                  
                    <button className='btnAdministration'>
                        Crear               
                    </button>
                </div>
                
            </form>
        </div>
    </>
  )
}

export default AddProduct