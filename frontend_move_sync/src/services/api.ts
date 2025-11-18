import { Event, UserProfile, ApiResponse } from '../types';
import { mockEvents, mockUsers } from './mockData';

const USE_MOCK_DATA = import.meta.env.VITE_USE_MOCK_DATA === 'true';
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api';

const delay = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));

export const apiService = {
  async getEvents(): Promise<ApiResponse<Event[]>> {
    /*if (USE_MOCK_DATA) {
      await delay(500);
      return { data: mockEvents };
    }

    try {
      const response = await fetch(`${API_BASE_URL}/events`);
      const data = await response.json();
      return { data };
    } catch (error) {
      return { data: [], error: 'Failed to fetch events' };
    }*/
    await delay(500);
      return { data: mockEvents };
  },

  async getEvent(id: string): Promise<ApiResponse<Event | null>> {
    /*if (USE_MOCK_DATA) {
      await delay(300);
      const event = mockEvents.find(e => e.id === id);
      return { data: event || null };
    }

    try {
      const response = await fetch(`${API_BASE_URL}/events/${id}`);
      const data = await response.json();
      return { data };
    } catch (error) {
      return { data: null, error: 'Failed to fetch event' };
    }*/
    await delay(300);
        const event = mockEvents.find(e => e.id === id);
        return { data: event || null };
  },

  async createEvent(event: Omit<Event, 'id' | 'created_at' | 'updated_at' | 'current_participants'>): Promise<ApiResponse<Event>> {
    if (USE_MOCK_DATA) {
      await delay(500);
      const newEvent: Event = {
        ...event,
        id: `mock-${Date.now()}`,
        current_participants: 0,
        created_at: new Date().toISOString(),
        updated_at: new Date().toISOString(),
      };
      return { data: newEvent };
    }

    try {
      const response = await fetch(`${API_BASE_URL}/events`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(event),
      });
      const data = await response.json();
      return { data };
    } catch (error) {
      return { data: event as Event, error: 'Failed to create event' };
    }
  },

  async updateEvent(id: string, event: Partial<Event>): Promise<ApiResponse<Event>> {
    if (USE_MOCK_DATA) {
      await delay(500);
      const existingEvent = mockEvents.find(e => e.id === id);
      const updatedEvent: Event = {
        ...(existingEvent || {} as Event),
        ...event,
        id,
        updated_at: new Date().toISOString(),
      };
      return { data: updatedEvent };
    }

    try {
      const response = await fetch(`${API_BASE_URL}/events/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(event),
      });
      const data = await response.json();
      return { data };
    } catch (error) {
      return { data: event as Event, error: 'Failed to update event' };
    }
  },

  async deleteEvent(id: string): Promise<ApiResponse<boolean>> {
    if (USE_MOCK_DATA) {
      await delay(500);
      return { data: true };
    }

    try {
      await fetch(`${API_BASE_URL}/events/${id}`, {
        method: 'DELETE',
      });
      return { data: true };
    } catch (error) {
      return { data: false, error: 'Failed to delete event' };
    }
  },

  async getUsers(): Promise<ApiResponse<UserProfile[]>> {
    if (USE_MOCK_DATA) {
      await delay(500);
      return { data: mockUsers };
    }

    try {
      const response = await fetch(`${API_BASE_URL}/users`);
      const data = await response.json();
      return { data };
    } catch (error) {
      return { data: [], error: 'Failed to fetch users' };
    }
  },

  async registerForEvent(eventId: string, userId: string): Promise<ApiResponse<boolean>> {
    if (USE_MOCK_DATA) {
      await delay(500);
      return { data: true };
    }

    try {
      const response = await fetch(`${API_BASE_URL}/events/${eventId}/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ userId }),
      });
      const data = await response.json();
      return { data: data.success || true };
    } catch (error) {
      return { data: false, error: 'Failed to register for event' };
    }
  },

  async unregisterFromEvent(eventId: string, userId: string): Promise<ApiResponse<boolean>> {
    if (USE_MOCK_DATA) {
      await delay(500);
      return { data: true };
    }

    try {
      const response = await fetch(`${API_BASE_URL}/events/${eventId}/unregister`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ userId }),
      });
      const data = await response.json();
      return { data: data.success || true };
    } catch (error) {
      return { data: false, error: 'Failed to unregister from event' };
    }
  },
};
