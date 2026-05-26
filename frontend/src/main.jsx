import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
// Importamos Bootstrap aquí para que funcione en todo el proyecto
import 'bootstrap/dist/css/bootstrap.min.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)