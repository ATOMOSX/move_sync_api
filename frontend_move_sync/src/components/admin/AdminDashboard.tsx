import { useState, useEffect } from 'react';
import { Event } from '../../types';
import { apiService } from '../../services/api';
import { allEvents, createEvent, updateEvent } from '../../services/eventApi';
import { EventForm } from './EventForm';
import { Plus, Edit, Trash2, Calendar, Clock4, TimerReset } from 'lucide-react';
import EventosEstadistica from './EventStadistics';
import Users from './users';

export const AdminDashboard = () => {
  const [events, setEvents] = useState<Event[]>([]);
  const [loading, setLoading] = useState(true);
  const [showForm, setShowForm] = useState(false);
  const [editingEvent, setEditingEvent] = useState<Event | null>(null);
  const [statsVisible, setStatsVisible] = useState(false);
  const [usersVisible, setUsersVisible] = useState(false);

  useEffect(() => {
    loadEvents();
  }, []);

  const loadEvents = async () => {
    setLoading(true);
    const { data } = await allEvents();
    console.log('eventos: ', events);

    setEvents(data);
    setLoading(false);
  };

  const showchart = () => {
    setStatsVisible(true);
  };

  const showUsers = () => {
    setUsersVisible(true);
  };

  const showEvents = () => {
    setUsersVisible(false);
  };


  const handleCreate = () => {
    setEditingEvent(null);
    setShowForm(true);
  };

  const handleEdit = (event: Event) => {
    console.log('evento para editar', event);

    setEditingEvent(event);
    setShowForm(true);
  };

  const handleDelete = async (id: string) => {
    if (!confirm('Are you sure you want to delete this event?')) return;

    await apiService.deleteEvent(id);
    setEvents(events.filter(e => e.idEvento !== id));
  };

  const handleSubmit = async (eventData: Partial<Event>) => {
    console.log('data del evento a guardar', eventData);
    const [anio, mes, dia] = eventData.fecha?.slice(0, 19).split('T')[0].split('-') || [];
    const hora = eventData.fecha?.slice(0, 19).split('T')[1]
    const fechaGuardar = `${dia}-${mes}-${anio} ${hora}`;

    if (editingEvent) {
      const event = {
        distancia: Number(eventData.distancia),
        duracion: eventData.duracion,
        fecha: fechaGuardar,
        nombre: eventData.nombre,
        idEvento: eventData.idEvento,
      }
      const { data } = await updateEvent(event);
      setEvents(events.map(e => e.idEvento === editingEvent.idEvento ? data : e));
    } else {
      console.log(fechaGuardar);

      const event = {
        distancia: Number(eventData.distancia),
        duracion: eventData.duracion,
        fecha: fechaGuardar,
        nombre: eventData.nombre,
      }
      console.log('evento createEvent', event);

      const { data } = await createEvent(event);
      setEvents([...events, data]);
    }
    setShowForm(false);
    setEditingEvent(null);
  };

  const getActivityColor = (type: string) => {
    const colors: Record<string, string> = {
      running: 'bg-green-100 text-green-800',
      marathon: 'bg-blue-100 text-blue-800',
      swimming: 'bg-cyan-100 text-cyan-800',
      crossfit: 'bg-orange-100 text-orange-800',
      gym: 'bg-red-100 text-red-800',
      other: 'bg-gray-100 text-gray-800',
    };
    return colors[type] || colors.other;
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center h-64">
        <div className="text-gray-600">Cargando eventos...</div>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto">
      <div className="flex justify-between items-center mb-8">
        <div>
          <h1 className="text-3xl font-bold text-gray-900">Panel de Administrador</h1>
          <p className="text-gray-600 mt-1">Gestiona tus eventos y registros</p>
        </div>
        <button
          onClick={showUsers}
          className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
        >
          <Plus />
          Usuarios
        </button>

        <button
          onClick={showEvents}
          className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
        >
          <Plus />
          Eventos
        </button>

        <button
          onClick={showchart}
          className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
        >
          <Plus />
          Estadísticas de Eventos
        </button>
        <button
          onClick={handleCreate}
          className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
        >
          <Plus className="w-5 h-5" />
          Crear Evento
        </button>
      </div>

      {usersVisible ? <Users onCancel={() => setUsersVisible(false)} /> :

      <div className="grid gap-6">
        {events.map((event) => (
          <div
            key={event.idEvento}
            className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow"
          >
            <div className="flex justify-between items-start">
              <div className="flex-1">
                <div className="flex items-center gap-3 mb-2">
                  <h3 className="text-xl font-semibold text-gray-900">{event.nombre}</h3>
                </div>
                <div className="flex flex-wrap gap-4 text-sm text-gray-600">
                  <div className="flex items-center gap-1">
                    <Calendar className="w-4 h-4" />
                    {new Date(event.fecha.split('T')[0]).toLocaleDateString()}
                  </div>
                  <div className="flex items-center gap-1">
                    <Clock4 className="w-4 h-4" />
                    {(event.fecha.split('T')[1] || '').substring(0, 5)}
                  </div>
                  <div className="flex items-center gap-1">
                    <TimerReset className="w-4 h-4" />
                    {(event.duracion)}
                  </div>
                </div>
              </div>

              <div className="flex gap-2 ml-4">
                <button
                  onClick={() => handleEdit(event)}
                  className="p-2 text-blue-600 hover:bg-blue-50 rounded-lg transition-colors"
                  title="Edit event"
                >
                  <Edit className="w-5 h-5" />
                </button>
                <button
                  onClick={() => handleDelete(event.idEvento)}
                  className="p-2 text-red-600 hover:bg-red-50 rounded-lg transition-colors"
                  title="Delete event"
                >
                  <Trash2 className="w-5 h-5" />
                </button>
              </div>
            </div>
          </div>
        ))}
      </div> }

      {events.length === 0 && (
        <div className="text-center py-12">
          <p className="text-gray-600 mb-4">No events created yet</p>
          <button
            onClick={handleCreate}
            className="inline-flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
          >
            <Plus className="w-5 h-5" />
            Create Your First Event
          </button>
        </div>
      )}
  

      {showForm && (
        <EventForm
          event={editingEvent}
          onSubmit={handleSubmit}
          onCancel={() => {
            setShowForm(false);
            setEditingEvent(null);
          }}
        />
      )}

      {statsVisible && <EventosEstadistica
        onCancel={() => {
          setStatsVisible(false);
        }} />
      }
    </div>
  );
};
