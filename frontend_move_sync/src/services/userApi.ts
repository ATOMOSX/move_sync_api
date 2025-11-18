import { CreateUserRequest } from "../entities/apiUserRequest";

export async function createUser(user: CreateUserRequest) {
  try {
    const response = await fetch("http://localhost:8080/api/usuarios", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    });

    if (!response.ok) {
      throw new Error("Error al crear usuario");
    }

    return await response.json();
  } catch (error) {
    console.error(error);
    throw error;
  }
}

export async function allUsers() {
  try {
    const response = await fetch("http://localhost:8080/api/usuarios", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      }
    });

    if (!response.ok) {
      throw new Error("Error al crear usuario");
    }

    return await response.json();
  } catch (error) {
    console.error(error);
    throw error;
  }
}

export async function reportUserAdvance() {
    try {
    const response = await fetch("http://localhost:8080/api/usuarios/reporte-avanzado", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      }
    });

    if (!response.ok) {
      throw new Error("Error al crear usuario");
    }

    return await response.json();
  } catch (error) {
    console.error(error);
    throw error;
  }
}

export async function reportGenderAdvance() {
    try {
    const response = await fetch("http://localhost:8080/api/usuarios/usuarios-por-genero", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      }
    });

    if (!response.ok) {
      throw new Error("Error al crear usuario");
    }

    return await response.json();
  } catch (error) {
    console.error(error);
    throw error;
  }
}

