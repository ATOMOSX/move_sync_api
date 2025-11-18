export async function reportLogrosAdmin(): Promise<any> {
    try {
        const response = await fetch(`http://localhost:8080/api/logros/logros-por-tipo`, {
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