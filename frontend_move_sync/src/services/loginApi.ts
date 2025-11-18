import axios from "axios";

const host = import.meta.env.BASE_URL || '';

export const loginApi = async (usuario: string, contrasena: string) => {
    const response = await fetch("http://localhost:8080/api/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            usuario,
            contrasena
        }),
    });

    const data = await response.json();
    console.log(data);
    return data;
};