import React, { useState } from 'react'
import style from './input.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons'
import { useGlobalStates } from '../../context/GlobalContext'

const Input = ({
  state,
  changeState,
  label,
  type,
  id,
  name,
  error,
  placeholder,
  regex,
  executeFunction,
  readonly,
  on,
}) => {
  const [viewPassword, setViewPassword] = useState(false);
  const [changeType, setChangeType] = useState(null);
  const {setValidateSvg}=useGlobalStates()

  const onChange = (e) => {
      changeState({ ...state, value: e.target.value })
  }

  const TogglePassword = () => {
    !viewPassword ? setViewPassword(true) : setViewPassword(false);
    !changeType === 'password'
      ? setChangeType('text')
      : setChangeType('password')
  };

  const validation = () => {
    if (regex) {
      if (regex.test(state.value)) {
        changeState({ ...state, valid: 'true' })
      } else {
        changeState({ ...state, valid: 'false' })
      }
    }

    if (executeFunction) {
      executeFunction()
    }
  };
  return (
    <div className={name=='text' ? style.otherInputContainer : style.inputContainer}>
      <label className={name=='text' ? style.otherLabel :style.useLabel} htmlFor={name}>
        {label}
      </label>
      <div className={style.inputChildren}>
        <input
          className={`${name =='text' ? style.otherInput : style.useInput} ${
            state.valid === 'false' ? style.inputError : ''}
            ${id =='link' ? style.input3 : style.useInput} `}
          type={id !== 'password' ? type : viewPassword ? 'text' : 'password'}
          id={name}
          name={name}
          value={state.value}
          onChange={onChange}
          placeholder={placeholder}
          onBlur={validation}
          valid={state.valid}
          readOnly={readonly && true}
        />
        {id === 'password' && state.value.length > 0 && (
          <span
            className={style.iconVisibility}
            id="iconVisibility"
            data-activo="false"
          >
            {viewPassword ? (
              <FontAwesomeIcon
                onClick={TogglePassword}
                icon={faEye}
                fontSize="24px"
                width="24px"
                style={{ userSelect: 'none'}}
              />
            ) : (
              <FontAwesomeIcon
                onClick={TogglePassword}
                icon={faEyeSlash}
                fontSize="24px"
                width="24px"
                style={{ userSelect: 'none' }}
              />
            )}
          </span>
        )}
      </div>
      {state.valid === 'false' && (
        <span className={style.msgError}>{error}</span>
      )}
      {state.valid === 'false' && setValidateSvg(true)}
      {state.valid === 'true'  && setValidateSvg(false)}
    </div>
  )
}

export default Input;
