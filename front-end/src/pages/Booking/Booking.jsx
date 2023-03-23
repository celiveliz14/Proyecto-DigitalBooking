import React, { useEffect, useState } from "react";
import Header from "../../components/Header/Header";
import "./booking.css";
import imgFlecha from "../../img/flecha-izquierda.png";
import { Link, useParams } from "react-router-dom";
import { Calendar } from "react-multi-date-picker";
import Dropdown from "../../components/Navbar/Dropdown/Dropdown";
import Stars from "../../components/CardProductsDetails/Stars/Stars";
import axios from "axios";
import Input from "../../components/Actions/useInput";
import { useGlobalStates } from "../../context/GlobalContext";
import timesInput from '../../times.json'

const Booking = () => {
  const [value, setValue] = useState([]);
  const [product,setProduct]=useState();
  const [useInput,setUseInput]=useState({ value: "", valid: null });
  const {time, data}=useGlobalStates()
  const [error,setError]=useState(false)
  const weekDays = ["D", "L", "M", "M", "J", "V", "S"];
  const months = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre",];

  const {id}=useParams()

  const regularExpressions = {
    nameAndLastName: /^[a-zA-ZÀ-ÿ\s]{4,40}$/,
  };

  function handleChange(value) {
    setValue(value);
  }
  function handleClick(){
    const reserva = {
      fechaInicio:(`${value[0].year}-${value[0].month.number}-${value[0].day}`),
      fechaFinal:(`${value[1].year}-${value[1].month.number}-${value[1].day}`),
      producto:{
          id: id
      },
      user:{
          id: JSON.stringify(data.id)
      }
    }
    console.log(reserva);
    fetch("http://3.137.136.152:8080/reservas", {
      method: "POST",
      body: JSON.stringify(reserva),
      headers: {
        'Content-type': 'application/json',
        'Cookie': `jwt=${data.token}`
      }})
      .then((response) =>  response.text())
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
        setError(true);
      })

    if(value[0] && value[1]){
    }  
  }
  useEffect(() => {
    axios.get(`http://3.137.136.152:8080/productos/${id}`)
    .then(res=> setProduct(res.data))
  }, [])
  return (
    <>
      <Header onChange={"home"} />
      <div className="containerBooking">
        <div className="containerProductName">
          <div style={{ width: "100vw" }}>
            <p>{product?.categoria.titulo}</p>
            <h1>{product?.titulo}</h1>
          </div>
          <Link to={"/"}>
            <img className="flechaProduct" src={imgFlecha} alt="" />
          </Link>
        </div>
        <div className="containerBookingFormDetails">
          <div>
            <div className="containerBookingForm">
              <h2 style={{ marginBottom: "14px" }}>Completá tus datos</h2>
              <form action="" className="bookingForm">
                <div className="containerBookingInput">
                  <label style={{ fontWeight: "bold" }} htmlFor="">
                    Nombre
                  </label>
                  <input placeholder={data.first_name} className="bookingInput" type="text" disabled/>
                </div>
                <div className="containerBookingInput">
                  <label style={{ fontWeight: "bold" }} htmlFor="">
                    Apellido
                  </label>
                  <input placeholder={data.last_name} className="bookingInput" type="text" disabled/>
                </div>
                <div className="containerBookingInput">
                  <label style={{ fontWeight: "bold" }} htmlFor="">
                    Email
                  </label>
                  <input placeholder={data.email} className="bookingInput" type="email" disabled/>
                </div>
                <div className="containerBookingInput">
                  <Input
                state={useInput}
                changeState={setUseInput}
                label="Ciudad"
                type="text"
                id="name"
                name="text"
                error="Debe tener 4 caracteres como mínimo"
                regex={regularExpressions.nameAndLastName}
              />
                </div>
              { error && (<p className="errorBooking">Lamentablemente la reserva no ha podido realizarse. Por favor, intente más tarde</p>)}
              </form>
            </div>
            <div className="containerBookingCalendar">
              <h2 style={{ marginBottom: "14px" }}>
                Seleccioná tu fecha de reserva
              </h2>
              <div className="bookingCalendar">
                <Calendar
                  value={value}
                  onChange={handleChange}
                  weekDays={weekDays}
                  months={months}
                  numberOfMonths={2}
                  range
                  disableMonthPicker
                  disableYearPicker
                  minDate={new Date()}
                />
              </div>
            </div>
            <div className="containerBookingTime">
              <h2 style={{ marginBottom: "14px" }}>Tu horario de llegada</h2>
              <div className="bookingTime">
                <h4
                  style={{
                    marginBottom: "14px",
                    display: "flex",
                    alignItems: "center",
                  }}
                >
                  <svg fill="#F0572D"version="1.1"id="Uploaded to svgrepo.com"xmlns="http://www.w3.org/2000/svg"xmlnsXlink="http://www.w3.org/1999/xlink"
                    width="2vw"height="3vh"viewBox="0 0 32 32"xmlSpace="preserve" ><g id="SVGRepo_bgCarrier" strokeWidth="0"></g><g id="SVGRepo_tracerCarrier"strokeLinecap="round"
                    strokeLinejoin="round"></g><g id="SVGRepo_iconCarrier">{" "}<style type="text/css"> </style>{" "}<path className="bentblocks_een"
                    d="M16,4C9.373,4,4,9.373,4,16c0,6.627,5.373,12,12,12s12-5.373,12-12C28,9.373,22.627,4,16,4z M16,26 c-5.514,0-10-4.486-10-10c0-5.514,4.486-10,10-10s10,4.486,10,10C26,21.514,21.514,26,16,26z M23.429,12.172l-9.777,9.777 l-5.08-5.08l1.414-1.414l3.666,3.666l8.363-8.363L23.429,12.172z" ></path>{" "}</g>
                  </svg>
                  Tu habitacion va a estar lista para el check-in entre las
                  10:00 AM y 11:00 PM
                </h4>
                <p style={{ color: "black", marginBottom: "10px",fontWeight:'bold'}}>
                  Indica tu horario estimado de llegada
                </p>
                <Dropdown data={timesInput} value={'booking'}/>
              </div>
            </div>
            <div className="containerBookingTerms">
                <div className="backgBookingTerms">
                    <div style={{paddingTop:'40px'}}>
                      <h2 style={{marginLeft:'30px',marginBottom:'14px'}}>Que tenes que saber</h2>
                      <hr style={{marginBottom:'48px'}}/>
                      <div className="bookingTerms">
                          <div className="productTerms">
                              <h3 style={{marginBottom:'15px'}}>Normas de la casa</h3>
                              <p style={{color:'black',marginBottom:'10px'}}>{product?.politicaLugar}</p>
                              </div>
                              <div className="productTerms">
                              <h3 style={{marginBottom:'15px'}}>Salud y seguridad</h3>
                              <p style={{color:'black',marginBottom:'10px'}}>{product?.politicaSaludSeguridad}</p>
                              </div>
                              <div className="productTerms">
                              <h3 style={{marginBottom:'15px'}}>Politica de cancelacion</h3>
                              <p style={{color:'black',marginBottom:'10px'}}>{product?.politicaCancelacion}</p>
                              </div>
                          </div>
                      </div>
                    </div>
                </div>
            </div>
          <div className="containerBookingDetails">
            <h2 style={{textAlign:'center',margin:'20px'}}>Detalle de la reserva</h2>
            <img className="imgBookingDetails" src={product?.listImagen[0].url} alt="" />
            <div className="bookingDetails">
              <p style={{color:'gray',fontSize:'14px',fontWeight:'bold',marginLeft:'0.6px'}}>HOTEL</p>
              <h2 style={{marginTop:'-6px'}}>{product?.titulo}</h2>
              <Stars/>
              <p style={{color:'black',display:'flex',marginTop:'20px',fontWeight:'bold',fontSize:'14px'}}>
                <svg fill="#000000" width="21px" height="19px" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" strokeWidth="0"></g><g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M12,2a8,8,0,0,0-7.992,8A12.816,12.816,0,0,0,12,22v0H12v0a12.816,12.816,0,0,0,7.988-12A8,8,0,0,0,12,2Zm0,11a3,3,0,1,1,3-3A3,3,0,0,1,12,13Z"></path></g></svg>
                {product?.ciudad.nombre_ciudad}, {product?.ciudad.nombre_pais}
              </p>
              <hr style={{marginTop:'40px'}}/>
              <div className="bookingDetailsCheck">
                <p style={{color:'black'}}>Check in</p>
                <p style={{color:'red'}}>{value[0] ? (`${value[0].day}/${value[0].month.number}/${value[0].year}`):'Seleccione la fecha'}</p>
              </div>
              <hr style={{marginTop:'40px'}}/>
              <div className="bookingDetailsCheck">
                <p style={{color:'black'}}>Check out</p>
                <p style={{color:'red'}}>{value[1] ? (`${value[1].day}/${value[1].month.number}/${value[1].year}`):'Seleccione la fecha'}</p>
              </div>
              <hr style={{marginTop:'40px',marginBottom:'40px'}}/>
              <button onClick={handleClick} className="btnBooking">Confirmar reserva</button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Booking;
