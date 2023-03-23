import React from 'react'
import CardProductDetails from '../../components/CardProductsDetails/CardProductDetails'
import Header from '../../components/Header/Header'
import './productDetails.css'
const ProductDetails = () => {
  return (
    <>
    <Header onChange={'home'}/>
    <div className='containerProduct'>
      <CardProductDetails/>
    </div>
    </>
  )
}

export default ProductDetails