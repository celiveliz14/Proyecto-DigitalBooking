import { createContext,useContext,useState } from "react";

export const ContextGlobal = createContext();


export const ContextProvider = ({ children }) => {

  const [data,setData]=useState({id:0, first_name:"", last_name:"", email:"", token:"", valid: false, roles:[]});
  const [pressCategory,setPressCategory]=useState(false);
  const [category,setCategory]=useState(null);
  const [cities,setCities]=useState(null);
  const [pressBtn,setPressBtn]=useState(true);
  const [time,setTime]=useState(null)
  const [validateLogin,setValidateLogin]=useState(false)
  const [validateSvg,setValidateSvg]=useState(false)
  const [cat,setCat]=useState(null)
  const [images,setImages]=useState([])
  const [succes, setSucces] = useState("")
  const [categorias, setCategorias] = useState([])
  const [ciudades, setCiudades] = useState([])
  const [categoriaId, setCategoriaId] = useState(null)
  const [ciudadId, setCiudadId] = useState(null)
  const [caracteristicas, setCaracteristicas] = useState([])
  const [atributos, setAtributos] = useState([])
  const [fechaInicio, setFechaInicio] = useState("")
  const [fechaFinal,setFechaFinal]=useState("")

  return (
    <ContextGlobal.Provider
    value={{data,setData,pressCategory,setPressCategory,category,setCategory,cities,setCities,
      pressBtn,setPressBtn,time,setTime,validateLogin,setValidateLogin,validateSvg,setValidateSvg
      ,cat,setCat,images,setImages,succes,setSucces, categorias,setCategorias, ciudades,setCiudades,
      categoriaId, setCategoriaId, ciudadId, setCiudadId,caracteristicas,setCaracteristicas, atributos,
       setAtributos,fechaInicio, setFechaInicio,fechaFinal,setFechaFinal,
       }}
    >
      {children}
    </ContextGlobal.Provider>
  );
};

export const useGlobalStates = () =>{
  return useContext(ContextGlobal)
}