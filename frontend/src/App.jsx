import { useState, useEffect } from 'react';

// --- Componentes ---

const PerfilEditable = ({ user }) => {
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState(user);
  const toggleEdit = () => setIsEditing(!isEditing);
  
  return (
    <div className="card shadow-sm border-0 p-4 rounded-3 h-100">
      <div className="text-center mb-4 position-relative">
        <img src={formData.avatar} alt="Avatar" className="rounded-circle border border-2 shadow-sm" style={{ width: '120px', height: '120px', objectFit: 'cover' }} />
        <div className="position-absolute" style={{ bottom: '10px', left: '60%' }}>
          <i className={`bi ${isEditing ? 'bi-check-circle-fill text-success' : 'bi-pencil-fill text-dark'} bg-white p-2 rounded-circle shadow-sm`}></i>
        </div>
      </div>
      <div className="mb-3">
        <div className="input-group">
          <input type="text" className={`form-control ${isEditing ? 'border-primary shadow-sm' : 'bg-light'}`} value={formData.nombre} readOnly={!isEditing} onChange={(e) => setFormData({...formData, nombre: e.target.value})} />
          <span className="input-group-text bg-light border-0"><i className="bi bi-person"></i></span>
        </div>
      </div>
      <div className="mb-3">
        <div className="input-group">
          <input type="text" className={`form-control ${isEditing ? 'border-primary shadow-sm' : 'bg-light'}`} value={formData.rut} readOnly={!isEditing} onChange={(e) => setFormData({...formData, rut: e.target.value})} />
          <span className="input-group-text bg-light border-0"><i className="bi bi-card-text"></i></span>
        </div>
      </div>
      <button className={`btn w-100 mt-2 ${isEditing ? 'btn-success' : 'btn-outline-dark'}`} onClick={toggleEdit}>
        {isEditing ? <><i className="bi bi-check-lg"></i> Guardar</> : <><i className="bi bi-pencil"></i> Editar Perfil</>}
      </button>
    </div>
  );
};

const EstudianteTable = ({ estudiantes = [] }) => (
  <div className="card shadow-sm border-0 p-0 overflow-hidden mb-4">
    <div className="card-header bg-dark text-white py-3">
      <h5 className="mb-0">ESTUDIANTES INSCRITOS</h5>
    </div>
    <table className="table table-hover mb-0">
      <thead className="table-light"><tr><th>ID</th><th>Nombre</th><th>RUT</th></tr></thead>
      <tbody>
        {estudiantes.length > 0 ? (
            estudiantes.map(e => <tr key={e.id}><td>{e.id}</td><td>{e.nombre}</td><td>{e.rut}</td></tr>)
        ) : (<tr><td colSpan="3" className="text-center">Sin estudiantes</td></tr>)}
      </tbody>
    </table>
  </div>
);

const AsistenciaTable = ({ asistencias = [] }) => (
  <div className="card shadow-sm border-0 p-0 overflow-hidden">
    <div className="card-header bg-secondary text-white py-3">
      <h5 className="mb-0">ASISTENCIAS</h5>
    </div>
    <table className="table table-hover mb-0">
      <thead className="table-light"><tr><th>Est. ID</th><th>Fecha</th><th>Estado</th></tr></thead>
      <tbody>
        {asistencias.length > 0 ? (
            asistencias.map((a, i) => <tr key={i}><td>{a.idEstudiante}</td><td>{a.fecha}</td><td>{a.estado}</td></tr>)
        ) : (<tr><td colSpan="3" className="text-center">Sin asistencias</td></tr>)}
      </tbody>
    </table>
  </div>
);

// Manejo seguro para comunicaciones (por si viene texto o objeto)
const ComunicacionCard = ({ com }) => (
  <div className="card mb-3 border-primary shadow-sm" style={{ borderWidth: '2px' }}>
    <div className="card-body">
      {typeof com === 'string' ? 
        <p className="card-text text-danger">{com}</p> : 
        <>
            <h5 className="card-title text-primary">{com.titulo}</h5>
            <p className="card-text text-muted">{com.contenido}</p>
        </>
      }
    </div>
  </div>
);

// --- Componente Principal ---

function App() {
  const [data, setData] = useState({ estudiantes: [], comunicaciones: [], asistencias: [] });
  const [loading, setLoading] = useState(true);
  const [userProfile] = useState({ 
    nombre: 'Valeria González', 
    rut: '19.876.543-2', 
    avatar: 'https://ui-avatars.com/api/?name=Valeria+Gonzalez&background=0D8ABC&color=fff&size=150'
  });

  useEffect(() => {
    // IMPORTANTE: Asegúrate que esta URL sea la correcta de tu BFF
    fetch('http://localhost:8082/api/bff/dashboard')
      .then(res => res.json())
      .then(json => {
        setData({
            estudiantes: json.estudiantes || [],
            comunicaciones: json.comunicaciones || [],
            asistencias: json.asistencias || []
        });
        setLoading(false);
      })
      .catch(err => {
        console.error("Error al cargar datos:", err);
        setLoading(false);
      });
  }, []);

  return (
    <div className="bg-light min-vh-100">
      <nav className="navbar navbar-dark bg-dark mb-5">
        <div className="container-fluid px-4">
          <span className="navbar-brand h1">ColegioDashboard</span>
        </div>
      </nav>

      <div className="container-fluid px-4">
        {loading ? <div className="text-center mt-5"><h5>Cargando datos...</h5></div> : (
            <div className="row g-4">
            <div className="col-md-3">
                <h5 className="text-secondary mb-3">MI PERFIL</h5>
                <PerfilEditable user={userProfile} />
            </div>
            
            <div className="col-md-5">
                <EstudianteTable estudiantes={data.estudiantes} />
                <AsistenciaTable asistencias={data.asistencias} />
            </div>

            <div className="col-md-4">
                <h5 className="text-secondary mb-3">ÚLTIMOS AVISOS</h5>
                {data.comunicaciones.map((c, i) => <ComunicacionCard key={i} com={c} />)}
            </div>
            </div>
        )}
      </div>
    </div>
  );
}

export default App;