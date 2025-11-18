export async function allMetas(): Promise<any> {
    try {
        const response = await fetch("http://localhost:8080/api/metas", {
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

export async function reportMeta(id: string): Promise<any> {
    try {
        const response = await fetch(`http://localhost:8080/api/metas/${id}`, {
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

export async function reportMetaAdmin(): Promise<any> {
    try {
        const response = await fetch(`http://localhost:8080/api/metas/reporte/admin`, {
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

