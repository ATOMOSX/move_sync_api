import { useAuth } from '../../context/AuthContext';
import { Activity, LogOut, User } from 'lucide-react';

export const Header = () => {
  const { profile, signOut, isAdmin } = useAuth();

  return (
    <header className="bg-white shadow-sm">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          <div className="flex items-center gap-3">
            <div className="bg-blue-600 p-2 rounded-lg">
              <Activity className="w-6 h-6 text-white" />
            </div>
            <div>
              <h1 className="text-xl font-bold text-gray-900">MoveSync</h1>
              <p className="text-xs text-gray-600">
                {isAdmin ? 'Portal Administrador' : 'Portal de Usuario'}
              </p>
            </div>
          </div>

          <div className="flex items-center gap-4">
            <div className="flex items-center gap-2 text-gray-700">
              <User className="w-5 h-5" />
              <div className="text-sm">
                <div className="font-medium">{profile?.full_name}</div>
                <div className="text-xs text-gray-500">{profile?.email}</div>
              </div>
            </div>

            <button
              onClick={signOut}
              className="flex items-center gap-2 px-4 py-2 text-gray-700 hover:bg-gray-100 rounded-lg transition-colors"
              title="Salir"
            >
              <LogOut className="w-5 h-5" />
              <span className="text-sm font-medium">Salir</span>
            </button>
          </div>
        </div>
      </div>
    </header>
  );
};
