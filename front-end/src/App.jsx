import React from 'react'
import { Route, Routes } from 'react-router-dom';
import Footer from './components/Footer/Footer';
import Layout from './components/Layout';
import Login from './pages/Login/Login';
import Signup from './pages/Signup/Signup';
import ProductDetails from './pages/Product/ProductDetails';
import Booking from './pages/Booking/Booking';
import Sucess from './pages/Success/Sucess';
import Administration from './pages/Administration/Administration';
import Reservas from './pages/Reservas/Reservas';

const App = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Layout/>}/>
        <Route path="/login" element={<Login/>} />
        <Route path="/signup" element={<Signup/>} />
        <Route path='/details/:id'element={<ProductDetails/>}/>
        <Route path='/details/:id/booking' element={<Booking/>}/>
        <Route path='/booking/success'element={<Sucess/>}/>
        <Route path='/administration'element={<Administration/>} />
        <Route path='/administration/success'element={<Sucess/>}/>
        <Route path='/reservas' element={<Reservas/>}/>
        <Route path="*" element={NotFound()} />
      </Routes>
      <Footer/>
    </div>
  )
}
function NotFound() {
  return <h1 style={{color:'black'}}>Ha llegado a una página que no existe</h1>;
}
export default App
