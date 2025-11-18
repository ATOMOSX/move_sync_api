export interface ApiUserResponse {
    success: boolean;
    message: string;
    data?: userResponseBody;
}

export interface userResponseBody {
    idUsuario: string;
    nombreCompleto: string;
    correoElectronico: string;
    rol: 'Admin' | 'Usuario';
}