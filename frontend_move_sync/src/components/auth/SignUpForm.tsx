import { useState } from 'react';
import { useAuth } from '../../context/AuthContext';
import { Activity } from 'lucide-react';
import { CreateUserRequest } from '../../entities/apiUserRequest';

interface SignUpFormProps {
  onToggleMode: () => void;
}

export const SignUpForm = ({ onToggleMode }: SignUpFormProps) => {
  const { signUp } = useAuth();
  const [formData, setFormData] = useState({
    firstName: '',
    secondName: '',
    firstLastName: '',
    secondLastName: '',
    identification: '',
    gender: '',
    weight: '',
    height: '',
    birthDate: '',
    email: '',
    password: '',
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    if (formData.password.length < 6) {
      setError('Contraseña debe tener al menos 6 caracteres');
      return;
    }

    setLoading(true);

    const date = formData.birthDate.split("-");
    const newUser: CreateUserRequest = {
      primerNombre: formData.firstName,
      segundoNombre: formData.secondName,
      primerApellido: formData.firstLastName,
      segundoApellido: formData.secondLastName,
      cedula: formData.identification,
      peso: Number(formData.weight),
      estatura: Number(formData.height),
      genero: formData.gender,
      contrasena: formData.password,
      correo: formData.email,
      fechaNacimiento: `${date[2]}-${date[1]}-${date[0]}`,
    }

    console.log("usuario creado: ", newUser);
    const response = await signUp(newUser);
    console.log("usuario creado: ", response);
    if (response.error) {
      setError(response.error);
      setLoading(false);
    } else {
      setSuccess(true);
      setTimeout(() => {
        onToggleMode();
      }, 2000);
    }
    
  };

  if (success) {
    return (
      <div className="min-h-screen bg-gradient-to-br from-blue-50 to-cyan-50 flex items-center justify-center p-4">
        <div className="bg-white rounded-2xl shadow-xl max-w-md w-full p-8 text-center">
          <div className="flex justify-center mb-6">
            <div className="bg-green-600 p-3 rounded-full">
              <Activity className="w-8 h-8 text-white" />
            </div>
          </div>
          <h2 className="text-2xl font-bold text-gray-900 mb-2">Account Created!</h2>
          <p className="text-gray-600">Redirecting to login...</p>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-cyan-50 flex items-center justify-center p-4">
      <div className="bg-white rounded-2xl shadow-xl max-w-md w-full p-8">
        <div className="flex justify-center mb-6">
          <div className="bg-blue-600 p-3 rounded-full">
            <Activity className="w-8 h-8 text-white" />
          </div>
        </div>

        <h2 className="text-3xl font-bold text-center text-gray-900 mb-2">Crear Cuenta</h2>
        <p className="text-center text-gray-600 mb-8">Únete a nosotros para eventos increíbles</p>

        <form onSubmit={handleSubmit} className="space-y-4">
          {error && (
            <div className="bg-red-50 text-red-600 px-4 py-3 rounded-lg text-sm">
              {error}
            </div>
          )}

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Nombres
            </label>
            <div className="flex space-x-4">
              <input
                type="text"
                required
                value={formData.firstName}
                onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Primer nombre"
              />
              <input
                type="text"
                required
                value={formData.secondName}
                onChange={(e) => setFormData({ ...formData, secondName: e.target.value })}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Segundo nombre"
              />
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Apellidos
            </label>
            <div className="flex space-x-4">
              <input
                type="text"
                required
                value={formData.firstLastName}
                onChange={(e) => setFormData({ ...formData, firstLastName: e.target.value })}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Primer apellido"
              />
              <input
                type="text"
                required
                value={formData.secondLastName}
                onChange={(e) => setFormData({ ...formData, secondLastName: e.target.value })}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Segundo apellido"
              />
            </div>
          </div>

          <div className="flex space-x-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Cédula
              </label>
              <input
                type="text"
                required
                value={formData.identification}
                onChange={(e) => setFormData({ ...formData, identification: e.target.value })}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="123456789"
              />
            </div>
            <div className="flex items-center gap-6">
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Género
              </label>
              <button
                type="button"
                onClick={() => setFormData({ ...formData, gender: "M" })}
                className={`px-4 py-2 rounded-lg border ${formData.gender === "M"
                  ? "bg-blue-500 text-white border-blue-600"
                  : "bg-white text-gray-700 border-gray-300"
                  }`}
              >
                M
              </button>

              <button
                type="button"
                onClick={() => setFormData({ ...formData, gender: "F" })}
                className={`px-4 py-2 rounded-lg border ${formData.gender === "F"
                  ? "bg-pink-500 text-white border-pink-600"
                  : "bg-white text-gray-700 border-gray-300"
                  }`}
              >
                F
              </button>
            </div>
          </div>

          <div className="flex space-x-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Peso
              </label>
              <input
                type="number"
                required
                value={formData.weight}
                onChange={(e) => setFormData({ ...formData, weight: e.target.value })}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="70"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Estatura
              </label>
              <input
                type="number"
                required
                value={formData.height}
                onChange={(e) => setFormData({ ...formData, height: e.target.value })}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="170"
              />
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Fecha de Nacimiento
            </label>
            <input
              type="date"
              required
              value={formData.birthDate}
              onChange={(e) => setFormData({ ...formData, birthDate: e.target.value })}
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"

            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Correo Electrónico
            </label>
            <input
              type="email"
              required
              value={formData.email}
              onChange={(e) => setFormData({ ...formData, email: e.target.value })}
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="you@example.com"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Contraseña
            </label>
            <input
              type="password"
              required
              value={formData.password}
              onChange={(e) => setFormData({ ...formData, password: e.target.value })}
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="••••••••"
            />
          </div>

          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 text-white py-2 px-4 rounded-lg hover:bg-blue-700 transition-colors font-medium disabled:opacity-50"
          >
            {loading ? 'Creando cuenta...' : 'Crear Cuenta'}
          </button>
        </form>

        <div className="mt-6 text-center">
          <button
            onClick={onToggleMode}
            className="text-blue-600 hover:text-blue-700 text-sm font-medium"
          >
            ¿Ya tienes una cuenta? Iniciar sesión
          </button>
        </div>
      </div>
    </div>
  );
};
