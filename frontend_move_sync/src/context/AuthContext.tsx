import { createContext, useContext, useEffect, useState, ReactNode } from 'react';
import { User } from '@supabase/supabase-js';
import { supabase } from '../lib/supabase';
import { UserProfile } from '../types';
import { loginApi } from '../services/loginApi';
import { ApiUserResponse } from '../entities/apiUserResponse';
import { CreateUserRequest } from '../entities/apiUserRequest';
import { createUser } from '../services/userApi';

interface AuthContextType {
  user: User | null;
  profile: UserProfile | null;
  loading: boolean;
  signIn: (email: string, password: string) => Promise<{ error: string | null }>;
  signUp: (user: CreateUserRequest) => Promise<{ error: string | null }>;
  signOut: () => Promise<void>;
  isAdmin: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);
  const [profile, setProfile] = useState<UserProfile | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    supabase.auth.getSession().then(({ data: { session } }) => {
      setUser(session?.user ?? null);
      if (session?.user) {
        loadProfile(session.user.id);
      } else {
        setLoading(false);
      }
    });

    const { data: { subscription } } = supabase.auth.onAuthStateChange((_event, session) => {
      (async () => {
        setUser(session?.user ?? null);
        if (session?.user) {
          await loadProfile(session.user.id);
        } else {
          setProfile(null);
          setLoading(false);
        }
      })();
    });

    return async (user: any) => {
      subscription.unsubscribe();
    };
  }, []);

  const loadProfile = async (userId: string) => {
    try {
      const { data, error } = await supabase
        .from('user_profiles')
        .select('*')
        .eq('id', userId)
        .maybeSingle();

      if (error) throw error;
      setProfile(data);
    } catch (error) {
      console.error('Error loading profile:', error);
    } finally {
      setLoading(false);
    }
  };

  const signIn = async (userEmail: string, pass: string) => {
    try {
      const userData: ApiUserResponse = await loginApi(userEmail, pass);
      console.log('USER DATA: ', userData);
      if (userData.data != undefined) {
        setUser({
          id: userData.data.idUsuario,
          email: userData.data.correoElectronico,
          full_name: userData.data.nombreCompleto,
          role: userData.data.rol,
        });
        setProfile({
          id: userData.data.idUsuario,
          email: userData.data.correoElectronico,
          full_name: userData.data.nombreCompleto,
          role: userData.data.rol,
        });
      }
      
    } catch (error) {
      return { error: 'An unexpected error occurred' };
    }
  };

  const signUp = async (user: CreateUserRequest) => {
    try {
      const newUser = await createUser(user);
      if (newUser.data) {
        setUser({
          id: newUser.data.idUsuario,
          email: newUser.data.correoElectronico,
          full_name: newUser.data.nombreCompleto,
          role: newUser.data.rol,
        });
        setProfile({
          id: newUser.data.idUsuario,
          email: newUser.data.correoElectronico,
          full_name: newUser.data.nombreCompleto,
          role: newUser.data.rol,
        });
      }
    } catch (error) {
      return { error: 'Error al crear usuario' };
    }
  };

  const signOut = async () => {
    await supabase.auth.signOut();
    setProfile(null);
  };

  const value = {
    user,
    profile,
    loading,
    signIn,
    signUp,
    signOut,
    isAdmin: user?.role === 'Admin',
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
