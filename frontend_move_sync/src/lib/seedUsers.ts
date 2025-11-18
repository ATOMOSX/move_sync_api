import { supabase } from './supabase';
import { mockUsers } from '../services/mockData';

const DEFAULT_PASSWORD = 'Password123!';

export const seedMockUsers = async () => {
  try {
    // Check if already seeded by looking for admin user profile
    const { data: existingAdmin } = await supabase
      .from('user_profiles')
      .select('id')
      .eq('email', 'admin@events.com')
      .maybeSingle();

    if (existingAdmin) {
      console.log('Mock users already seeded');
      return;
    }

    console.log('Seeding mock users...');

    for (const mockUser of mockUsers) {
      try {
        // Try to sign up the user
        const { data: authData, error: signUpError } = await supabase.auth.signUp({
          email: mockUser.email,
          password: DEFAULT_PASSWORD,
          options: {
            data: {
              full_name: mockUser.full_name,
            },
          },
        });

        if (signUpError && !signUpError.message.includes('already registered')) {
          console.error(`Error signing up ${mockUser.email}:`, signUpError);
          continue;
        }

        // If user was created or already exists, ensure profile exists
        if (authData?.user) {
          const { error: profileError } = await supabase
            .from('user_profiles')
            .upsert({
              id: authData.user.id,
              email: mockUser.email,
              full_name: mockUser.full_name,
              role: mockUser.role,
            }, {
              onConflict: 'id',
            });

          if (profileError) {
            console.error(`Error creating profile for ${mockUser.email}:`, profileError);
          } else {
            console.log(`✓ Seeded user: ${mockUser.email}`);
          }
        }
      } catch (error) {
        console.error(`Failed to seed user ${mockUser.email}:`, error);
      }
    }

    console.log('Mock users seeding completed');
  } catch (error) {
    console.error('Error seeding mock users:', error);
  }
};
