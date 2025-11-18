import { useState, useEffect } from 'react';
import { Event } from '../../types';
import { apiService } from '../../services/api';
import { useAuth } from '../../context/AuthContext';
import { EventCard } from './EventCard';
import { allEvents } from '../../services/eventApi';
import { Plus } from 'lucide-react';
import Meta from './metas';
import ReportMeta from './reportMetas';

export const UserDashboard = () => {
  const { user } = useAuth();
  const [events, setEvents] = useState<Event[]>([]);
  const [loading, setLoading] = useState(true);
  const [registeredEventIds, setRegisteredEventIds] = useState<Set<string>>(new Set());
  const [processingEventId, setProcessingEventId] = useState<string | null>(null);
  const [metaVisible, setMetaVisible] = useState(false);
  const [eventsVisible, setEventsVisible] = useState(true);
  const [reportMetasVisible, setReportMetasVisible] = useState(false);

  useEffect(() => {
    loadEvents();
  }, []);

  const loadEvents = async () => {
    setLoading(true);
    const { data } = await allEvents();
    setEvents(data);
    setLoading(false);
  };

  const handleRegister = async (eventId: string) => {
    if (!user) return;
    setProcessingEventId(eventId);

    const { data } = await apiService.registerForEvent(eventId, user.id);
    if (data) {
      setRegisteredEventIds(new Set([...registeredEventIds, eventId]));
      /*
      setEvents(events.map(e =>
        e.id === eventId ? { ...e, current_participants: e.current_participants + 1 } : e
      ));*/
    }

    setProcessingEventId(null);
  };


  if (loading) {
    return (
      <div className="flex items-center justify-center h-64">
        <div className="text-gray-600">Loading events...</div>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-2">Eventos Disponibles</h1>
        <p className="text-gray-600">Explora las próximas actividades</p>
      </div>
      <div className='flex m-2 gap-4 mb-6'>
        <button
          onClick={() => (setMetaVisible(false), setEventsVisible(true), setReportMetasVisible(false))}
          className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
        >
          <Plus />
          Eventos
        </button>
        <button
          onClick={() => (setMetaVisible(true), setEventsVisible(false), setReportMetasVisible(false))}
          className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
        >
          <Plus />
          Metas
        </button>
        <button
          onClick={() => (setReportMetasVisible(true), setMetaVisible(true), setEventsVisible(false))}
          className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
        >
          <Plus />
          Reporte Metas
        </button>
      </div>


      {eventsVisible &&
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {events.map((event) => (
            <EventCard
              key={event.idEvento}
              event={event}
            />
          ))}
        </div>
      }

      {
        metaVisible && <Meta onCancel={() => setMetaVisible(false)} />
      }

      {reportMetasVisible && <ReportMeta onCancel={() => setReportMetasVisible(false)} idUsuario={user?.id || ''} />}

    </div>
  );
};
