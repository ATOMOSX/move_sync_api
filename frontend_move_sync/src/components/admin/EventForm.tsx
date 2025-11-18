import { useState } from 'react';
import { Event } from '../../types';
import { X } from 'lucide-react';

interface EventFormProps {
  event?: Event | null;
  onSubmit: (event: Partial<Event>) => void;
  onCancel: () => void;
}

export const EventForm = ({ event, onSubmit, onCancel }: EventFormProps) => {
  const [formData, setFormData] = useState({
    idEvento: event?.idEvento || '',
    nombre: event?.nombre || '',
    fecha: event?.fecha ? new Date(event.fecha).toISOString() : '',
    duracion: event?.duracion || '',
    durationHours: event?.duracion ? event.duracion.split(':')[0] : '',
    durationMinutes: event?.duracion ? event.duracion.split(':')[1] : '',
    distancia: event?.distancia ? event?.distancia : '',
  });

  const handleSubmit = (e: React.FormEvent) => {
    console.log('evento', formData);
    console.log({
      ...formData,
      fecha: new Date(formData.fecha).toISOString().slice(0,19),
      nombre: formData.nombre,
      duracion: `${formData.durationHours}:${formData.durationMinutes}:00`,
      distancia: formData.distancia,
    });
    
    
    e.preventDefault();
    onSubmit({
      ...formData,
      fecha: new Date(formData.fecha).toISOString(),
      nombre: formData.nombre,
      duracion: `${formData.durationHours}:${formData.durationMinutes}:00`,
      distancia: formData.distancia,
    });
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
      <div className="bg-white rounded-lg max-w-2xl w-full max-h-[90vh] overflow-y-auto">
        <div className="p-6">
          <div className="flex justify-between items-center mb-6">
            <h2 className="text-2xl font-bold text-gray-900">
              {event ? 'Editar Evento' : 'Crear Nuevo Evento'}
            </h2>
            <button
              onClick={onCancel}
              className="text-gray-400 hover:text-gray-600 transition-colors"
            >
              <X className="w-6 h-6" />
            </button>
          </div>

          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Nombre del evento
              </label>
              <input
                type="text"
                required
                value={formData.nombre}
                onChange={(e) => setFormData({ ...formData, nombre: e.target.value })}
                className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">
                  Fecha y hora del evento
                </label>
                <input
                  type="datetime-local"
                  required
                  value={formData.fecha}
                  onChange={(e) => setFormData({ ...formData, fecha: e.target.value })}
                  className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Duración
              </label>
              <div className="flex gap-2 w-full">
                {/* HORAS */}
                <select
                  required
                  value={formData.durationHours}
                  onChange={(e) =>
                    setFormData({ ...formData, durationHours: e.target.value })
                  }
                  className="w-1/2 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                >
                  <option value="">Horas</option>
                  {[...Array(13)].map((_, i) => (
                    <option key={i} value={i.toString().padStart(2, "0")}>
                      {i} h
                    </option>
                  ))}
                </select>

                {/* MINUTOS */}
                <select
                  required
                  value={formData.durationMinutes}
                  onChange={(e) =>
                    setFormData({ ...formData, durationMinutes: e.target.value })
                  }
                  className="w-1/2 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                >
                  <option value="">Minutos</option>
                  {[0, 15, 30, 45].map((m) => (
                    <option key={m} value={m.toString().padStart(2, "0")}>
                      {m} min
                    </option>
                  ))}
                </select>
              </div>
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">
                  Distancia
                </label>
                <input
                  type="number"
                  required
                  value={formData.distancia}
                  onChange={(e) => setFormData({ ...formData, distancia: e.target.value })}
                  className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
            </div>

            <div className="flex gap-3 pt-4">
              <button
                type="submit"
                className="flex-1 bg-blue-600 text-white py-2 px-4 rounded-lg hover:bg-blue-700 transition-colors font-medium"
              >
                {event ? 'Actualizar Evento' : 'Crear Evento'}
              </button>
              <button
                type="button"
                onClick={onCancel}
                className="flex-1 bg-gray-200 text-gray-800 py-2 px-4 rounded-lg hover:bg-gray-300 transition-colors font-medium"
              >
                Cancelar
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};
