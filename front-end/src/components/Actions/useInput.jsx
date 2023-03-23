import React, { useState } from 'react'
import style from './input.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons'

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
}) => {
  const [viewPassword, setViewPassword] = useState(false);
  const [changeType, setChangeType] = useState(null);

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
    <div className={style.inputContainer}>
      <label className={name=='text' ? style.otherLabel :style.useLabel} htmlFor={name}>
        {label}
      </label>
      <div className={style.inputChildren}>
        <input
          className={`${name=='text' ? style.otherInput : style.useInput} ${
            state.valid === 'false' ? style.inputError : ''
          } `}
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
    </div>
  )
}

export default Input;
