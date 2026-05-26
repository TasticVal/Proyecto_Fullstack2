import { useEffect, useState } from 'react'
import './App.css'

function App() {
  const [data, setData] = useState("Cargando...");

  useEffect(() => {
    // Probamos conectarnos al microservicio académico
    fetch('http://localhost:8080/api/estudiantes')
      .then(response => response.json())
      .then(json => setData(JSON.stringify(json)))
      .catch(err => setData("Error conectando al backend: " + err));
  }, []);

  return (
    <div className="App">
      <h1>¡Mi Proyecto Fullstack!</h1>
      <p>Respuesta del Backend: {data}</p>
    </div>
  )
}

export default App