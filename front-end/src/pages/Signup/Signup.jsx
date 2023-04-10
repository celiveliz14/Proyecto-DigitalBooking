import React, { useState } from 'react';
import Input from '../../components/Actions/useInput';
import './signup.css';
import { Link } from 'react-router-dom';
import Header from '../../components/Header/Header';
import endpoint from '../../utils/endpoint.json';
import Swal from "sweetalert2";


const Signup = () => {
  const [name, setName] = useState({ value: '', valid: null });
  const [lastName, setLastName] = useState({ value: '', valid: null });
  const [email, setEmail] = useState({ value: '', valid: null });
  const [password, setPassword] = useState({ value: '', valid: null });
  const [password2, setPassword2] = useState({ value: '', valid: null });
  const [isValid, setisValid] = useState(null);
  const [msgError, setMsgError] = useState('');
  const [error,setError]=useState(false);


  const regularExpressions = {
    nameAndLastName: /^[a-zA-ZÀ-ÿ\s]{4,40}$/, 
    email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
    password: /^.{7,30}$/, // 
  }
  
  const handleSubmit = async (e) => {
      e.preventDefault()
      validateInputs()
      setisValid(true)
      const obj={
        first_name:name.value,
        last_name:lastName.value,
        email:email.value,
        password:password.value,
      }
      if(email.valid == 'true' && password.valid == 'true'
      && lastName.valid == 'true' && name.valid == 'true' && password2.valid == 'true'){
          fetch(`${endpoint.url}/api/v1/auth/register`, {
            method: "POST",
            body: JSON.stringify(obj),
            headers: {
              "Content-type": "application/json; charset=UTF-8"
            }})
            .then((response) =>  response.text())
            .then((res) => {
              mostrarAlerta() 
            })
            .catch((err) => {
              console.log(err);
              setError(true)
            })
      }     
    }

  const handleForm = () => {
  return (
    name.valid === 'true' &&
    lastName.valid === 'true' &&
    email.valid === 'true' &&
    password.valid === 'true' &&
    password2.valid === 'true'
    )
  }
  const validateInputs = () => {
      if (name.value === '') {
        setName((prevState) => {
          return { ...prevState, valid: 'false' }
        })
      }
      if (lastName.value === '') {
        setLastName((prevState) => {
          return { ...prevState, valid: 'false' }
        })
      }
      if (email.value === '') {
        setEmail((prevState) => {
          return { ...prevState, valid: 'false' }
        })
      }
      if (password.value === '') {
        setPassword((prevState) => {
          return { ...prevState, valid: 'false' }
        })
      }
      if (password2.value === '') {
        setPassword2((prevState) => {
          return { ...prevState, valid: 'false' }
        })
      }
    }

  const validatePassword = () => {
    if (password.value.length > 0) {
      if (password.value !== password2.value) {
        setPassword2((prevState) => {
          return { ...prevState, valid: 'false' }
        })
      } else if (password.valid === 'false') {
        setPassword2((prevState) => {
          return { ...prevState, valid: 'false' }
        })
      } else {
        setPassword2((prevState) => {
          return { ...prevState, valid: 'true' }
        })
      }
    } else if (password.value === '') {
      setPassword2((prevState) => {
        return { ...prevState, valid: 'false' }
      })
    }
  }

  const mostrarAlerta = () => {
    Swal.fire({
        title:"Usario Registrado exitosamente",
        text:"Muchas gracias por elegirnos!",
        icon:'success',
        timer:'25000',
    })}

  return (
    <>
    <Header onChange={'signup'}/>
    <div className='containerForm' >
        <form
          className='formSignup'
          onSubmit={handleSubmit}
          onChange={handleForm}
        >
          <h1 style={{ color: '#f0572b', marginBottom: '30px', fontWeight:"bold" }} >Crear cuenta</h1>
          <div>
            <div className='containerInputSignup'>
              <Input
                state={name}
                changeState={setName}
                label="Nombre"
                type="text"
                id="name"
                name="name"
                error="Sólo se permiten letras y mínimo cuatro carácteres"
                regex={regularExpressions.nameAndLastName}
              />
              <Input
                state={lastName}
                changeState={setLastName}
                label="Apellido"
                type="text"
                id="lastName"
                name="lastName"
                error="Sólo se permiten letras y mínimo cuatro carácteres"
                regex={regularExpressions.nameAndLastName}
              />
            </div>
            <Input
              state={email}
              changeState={setEmail}
              label="Correo electrónico"
              type="email"
              id="email"
              name="email"
              error="Correo electrónico incorrecto"
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
            <Input
              state={password2}
              changeState={setPassword2}
              label="Confirmar contraseña"
              type="password"
              id="password2"
              name="password2"
              error="Las contraseñas deben ser iguales"
              regex={regularExpressions.password}
              executeFunction={validatePassword}
            />
            {isValid === false && (
              <p className='msgErrorForm' >
                {msgError
                  ? msgError
                  : 'Por favor vuelva a intentarlo, algunos de los datos ingresados no son correctos.'}
              </p>
            )}
            {error && (<p className='msgErrorForm' >
            Lamentablemente no ha podido registrarse. Por favor intente más tarde
            </p>)}
            <div className='containerBtnSignup'>
                <button type="submit" className='btnSignup'>
                Crear cuenta
              </button>
              <p className='linkContainer'>
                ¿Ya tienes una cuenta?{' '}
                <Link className='linkAction' to="/login">
                  Iniciar sesión
                </Link>
              </p>
            </div>
          </div>
        </form>
    </div>
    </>
  )
}

export default Signup;