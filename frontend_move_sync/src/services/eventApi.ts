export async function allEvents(): Promise<any> {
    try {
        const response = await fetch("http://localhost:8080/api/eventos", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        });
        console.log("RESPONSE: ", response);


        return await response.json();
    } catch (error) {
        console.error("Error API:", error);
        throw error;
    }
}

export async function createEvent(event: any): Promise<any> {
    try {
        const response = await fetch("http://localhost:8080/api/eventos", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(event),
        });
        console.log("RESPONSE: ", response);
        return await response.json();
    } catch (error) {
        console.error("Error API:", error);
        throw error;
    }
}

export async function updateEvent(event: any): Promise<any> {
    try {
        const response = await fetch(`http://localhost:8080/api/eventos/${event.idEvento}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                duracion: event.duracion,
                fecha: event.fecha,
                nombre: event.nombre,
                distancia: event.distancia,
            }),
        });

        return await response.json();
    } catch (error) {
        console.error("Error en updateEvent:", error);
        throw error;
    }
}

export async function getStadistics(): Promise<any> {
    try {
        const response = await fetch("http://localhost:8080/api/eventos/estadisticas", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        });
        console.log("RESPONSE: ", response);


        return await response.json();
    } catch (error) {
        console.error("Error API:", error);
        throw error;
    }
}
