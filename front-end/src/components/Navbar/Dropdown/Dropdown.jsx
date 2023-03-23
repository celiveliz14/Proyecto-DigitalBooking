import React, { useState } from 'react'
import { useGlobalStates } from '../../../context/GlobalContext'
import './dropdown.css'

const Dropdown = ({data,value}) => {
    const [open,setOpen]=useState(false)
    const {cities,setCities,time,setTime}=useGlobalStates()

    function handleClick(e){
        setOpen(!open)
      }  
    let text = cities ? `${cities.nombre_ciudad}`: '¿A dónde vamos?';
    let text2= time ? `${time.hour}` : 'Seleccioná la hora de llegada';
  return (
    <div className='dropdown'>
        <div className='dropdown-btn'onClick={handleClick}>
            {
                value == 'city' && (
                    <div style={{display:'flex',alignItems:'center'}}>
                        <svg fill="#000000" width="24px" height="24px" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" strokeWidth="0"></g><g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M12,2a8,8,0,0,0-7.992,8A12.816,12.816,0,0,0,12,22v0H12v0a12.816,12.816,0,0,0,7.988-12A8,8,0,0,0,12,2Zm0,11a3,3,0,1,1,3-3A3,3,0,0,1,12,13Z"></path></g></svg>
                        {text}
                    </div>
                )
            }
            {
                value == 'booking' && (
                     <div>
                        {text2}
                     </div>
                )
            }
            <svg style={{paddingLeft:'10px'}} width="34px" height="34px" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" strokeWidth="0"></g><g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g><g id="SVGRepo_iconCarrier"> <path d="M17 10.5L12.5 15L8 10.5" stroke="#121923" strokeWidth="1.2"></path> </g></svg>
        </div>
        {open && value == 'city' && (
            <div className='dropdown-content'>
            {data?.map((data) => (
                <div
                className='dropdown-item'
                onClick={()=>{
                  setCities(data)
                  setOpen(false)  
                }}
                key={data.id}
                >
                  <div className='svgDrop'>
                  <svg className='svgDropdown' width="24px" height="24px" viewBox="0 0 24 24" fill='none'  xmlns="http://www.w3.org/2000/svg" stroke="#af2323"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path opacity="0.1"  clip-rule="evenodd" d="M12 21C16.9706 21 21 16.9706 21 12C21 7.02944 16.9706 3 12 3C7.02944 3 3 7.02944 3 12C3 16.9706 7.02944 21 12 21ZM14.149 15.1848C13.4576 17.1053 10.6665 16.8584 10.323 14.8464C10.2169 14.2248 9.72996 13.7379 9.10837 13.6318C7.09631 13.2882 6.84941 10.4971 8.76993 9.80572L12.6761 8.39948C14.4674 7.75462 16.2001 9.48732 15.5553 11.2786L14.149 15.1848Z" ></path> <path d="M21 12C21 16.9706 16.9706 21 12 21C7.02944 21 3 16.9706 3 12C3 7.02944 7.02944 3 12 3C16.9706 3 21 7.02944 21 12Z" stroke="#F0572D" stroke-width="2"></path> <path d="M13.9137 15.1001L15.32 11.1939C15.8932 9.60167 14.353 8.06149 12.7608 8.6347L8.85455 10.0409C7.1758 10.6453 7.39162 13.085 9.15038 13.3853C9.87655 13.5093 10.4454 14.0781 10.5694 14.8043C10.8696 16.5631 13.3094 16.7789 13.9137 15.1001Z" stroke="#F0572D" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path> </g></svg>
                    {data.nombre_ciudad}
                  </div>
                    
                </div>
            ))}
            </div>
        )}
        {
           open && value == 'booking' && (
            <div className='otherDropdown-content'>
            {data?.map((data) => (
                <div
                className='dropdown-item'
                onClick={()=>{
                  setTime(data)
                  setOpen(false)  
                }}
                key={data.id}
                >
                    {data.hour}
                </div>
            ))}
            </div>
        ) 
        }    
    </div>
    
  )
}

export default Dropdown