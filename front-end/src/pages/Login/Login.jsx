import React, { useState } from "react";
import Input from "../../components/Actions/useInput";
import './login.css';
import { Link, useNavigate } from "react-router-dom";
import Header from "../../components/Header/Header";
import { useGlobalStates } from "../../context/GlobalContext";
import endpoint from '../../utils/endpoint.json';
import Swal from "sweetalert2";

const Login = () => {

  const [email, setEmail] = useState({ value: "", valid: null });
  const [password, setPassword] = useState({ value: "", valid: null });
  const [isFormValid, setIsFormValid] = useState(null);
  const [error,setError]=useState(false)
  const {setData,validateLogin}=useGlobalStates()
  const regularExpressions = {
    email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
    password: /^.{7,30}$/,
  }
  const navigate=useNavigate();

  const mostrarAlerta = () => {
    Swal.fire({
        title:"EL USUARIO NO EXISTE",
        text:"Por favor registrese antes de iniciar sesion",
        icon:'error',
        timer:'25000',
        confirmButtonColor: "#F0572D"
    })
}

  const handleSubmit = (e) => {
    e.preventDefault();
    //setIsFormValid(true); 
    if (email.valid === 'true' && password.valid === 'true') {
      const obj = {
        email:email.value,
        password:password.value
      }
      fetch(`${endpoint.url}/api/v1/auth/authenticate`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: {
          'Content-type': 'application/json'
        }})
        .then((response) =>  response.json())
        .then((res) => {
          console.log(res);
          setData({
                  id:res.id,
                  first_name:res.first_name,
                  last_name:res.last_name,
                  email:res.email,
                  token:res.token,
                  valid:true,
                  roles:res.roles
              })
              navigate('/')})
        .catch((err) => {
          mostrarAlerta()
          console.log(err);
          setError(true)
        })
    }   
  }

  return (
    <>
    <Header onChange={'login'}/>
    <div className='containerFormLogin'>
      <div style={{display: validateLogin ? 'flex' : 'none' }} className="containerAlertLogin">
         <p className="alertLogin" style={{color:'black'}}>El login es obligatorio y en caso de no estar registrad@, deberá
          <Link className="linkAlertLogin" to={'/signup'}>registrarse</Link></p>
      </div>
     
      <form className='formSignup'  onSubmit={handleSubmit}>
        <h1 style={{ color: '#f0572b', marginBottom: '30px', fontWeight:"bold" }}>Iniciar Sesión</h1>
        <div>
          <Input
            state={email}
            changeState={setEmail}
            label="Correo electrónico"
            type="email"
            id="email"
            name="email"
            error="Ingrese un correo electronico valido"
            regex={regularExpressions.email}
          />
          <Input
            state={password}
            changeState={setPassword}
            label="Contraseña"
            type="password"
            id="password"
            name="password"
            error="La contraseña debe tener entre 7 y 30 caracteres"
            regex={regularExpressions.password}
          />
          {isFormValid === false && (
            <p className='msgErrorForm'>
              "Por favor vuelva a intentarlo, sus credenciales son inválidas."
            </p>
          )}
          {error && (<p className='msgErrorForm'>
          Lamentablemente no ha podido iniciar sesión. Por favor intente más tarde.
            </p>)}
          <div className='containerBtnSignup'>
            
              <button type="submit"className="btnLogin" >
                Ingresar
              </button> 
            <p className='linkContainer' style={{color:'black'}}>
              ¿Aún no tenés cuenta?{" "}
              <Link to="/signup"className='linkAction'>
                Registrate
              </Link>
            </p>
          </div>
          
        </div>
      </form>
    </div>
    </>
  )
}

export default Login;
