import { Event } from '../../types';
import { Calendar, MapPin, Users, CheckCircle } from 'lucide-react';

interface EventCardProps {
  event: Event;
  key: string;
}

export const EventCard = ({ event, key }: EventCardProps) => {
  return (
    <div className="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow">

      <div className="p-6">
        <div className="flex justify-between items-start mb-3">
          <div className="flex-1">
            <h3 className="text-xl font-semibold text-gray-900 mb-1">{event.nombre}</h3>
          </div>
        </div>

        <div className="space-y-2 mb-4">
          <div className="flex items-center gap-2 text-sm text-gray-700">
            <Calendar className="w-4 h-4 text-gray-400" />
            <span>
              {new Date(event.fecha).toLocaleDateString('es-CO', {
                weekday: 'long',
                year: 'numeric',
                month: 'long',
                day: 'numeric',
                hour: '2-digit',
                minute: '2-digit'
              })}
            </span>
          </div>

        </div>
      </div>
    </div>
  );
};
