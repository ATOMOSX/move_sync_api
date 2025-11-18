export interface Event {
  idEvento: string;
  nombre: string;
  fecha: string;
  duracion: string;
  distancia: string;
}

export interface UserProfile {
  id: string;
  email: string;
  full_name: string;
  role: 'Admin' | 'Usuario';
}

export interface ApiResponse<T> {
  data: T;
  error?: string;
}
