import { ArrowUp, Award, Calendar, Edit, Mail, Newspaper, PersonStanding, Plus, TimerReset, Trash2, } from "lucide-react";
import { useEffect, useState } from "react";
import { allUsers } from "../../services/userApi";
import UsuariosEstadistica from "./reportUserAdvance";
import ReporteGenero from "./reportUserGender";
import AdminMetasGrid from "./reportUserMetas";
import ReporteLogros from "./reportUserLogros";

interface EventFormProps {
    onCancel: () => void;
}
export default function Users({ onCancel }: EventFormProps) {
    const [users, setUsers] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [reportAdvanceVisible, setReportAdvanceVisible] = useState(false);
    const [reportGenderVisible, setReportGenderVisible] = useState(false);
    const [reportMetasVisible, setReportMetasVisible] = useState(false);
    const [userStats, setUserStats] = useState(true);
    const [reportLogrosVisible, setReportLogrosVisible] = useState(false);

    useEffect(() => {
        console.log('hola desde users');

        loadUsers();
    }, []);

    const loadUsers = async () => {
        setLoading(true);
        const { data } = await allUsers();
        console.log('users: ', users);

        setUsers(data);
        setLoading(false);
    };

    function handleEdit(user: any): void {
        throw new Error("Function not implemented.");
    }

    function handleDelete(idEvento: any): void {
        throw new Error("Function not implemented.");
    }

    function reportAdvance(): void {
        setReportAdvanceVisible(true);
    }

    return (
        
        <div className="grid gap-6">
            <div className="flex items-center gap-3 mb-2">
                <button
                    onClick={() => { setReportMetasVisible(false); setUserStats(true); }}
                    className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
                >
                    <PersonStanding  />
                    Usuarios
                </button>
                <button
                    onClick={reportAdvance}
                    className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
                >
                    <ArrowUp />
                    Reporte Avanzado
                </button>
                <button
                    onClick={() => setReportGenderVisible(true)}
                    className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
                >
                    <PersonStanding  />
                    Reporte Género
                </button>
                <button
                    onClick={() => { setReportMetasVisible(true); setUserStats(false); }}
                    className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
                >
                    <Award  />
                    Reporte Metas
                </button>
                <button
                    onClick={() => { setReportLogrosVisible(true); setUserStats(false); setReportMetasVisible(false); }}
                    className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors font-medium"
                >
                    <Award  />
                    Reporte Logros
                </button>
            </div>

            {userStats &&users.map((user) => (
                <div
                    key={user.idUsuario}
                    className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow"
                >
                    <div className="flex justify-between items-start">
                        <div className="flex-1">
                            <div className="flex items-center gap-3 mb-2">
                                <h3 className="text-xl font-semibold text-gray-900">{user.primerNombre + " " + user.primerApellido}</h3>
                            </div>
                            <div className="flex flex-wrap gap-4 text-sm text-gray-600">
                                <div className="flex items-center gap-1">
                                    <Mail className="w-4 h-4" />
                                    {user.correo}
                                </div>
                                <div className="flex items-center gap-1">
                                    <Newspaper className="w-4 h-4" />
                                    {user.cedula}
                                </div>
                                <div className="flex items-center gap-1">
                                    <Calendar className="w-4 h-4" />
                                    {(user.fechaNacimiento)}
                                </div>
                            </div>
                        </div>

                        <div className="flex gap-2 ml-4">
                            <button
                                onClick={() => handleEdit(user)}
                                className="p-2 text-blue-600 hover:bg-blue-50 rounded-lg transition-colors"
                                title="Edit event"
                            >
                                <Edit className="w-5 h-5" />
                            </button>
                            <button
                                onClick={() => handleDelete(user.idEvento)}
                                className="p-2 text-red-600 hover:bg-red-50 rounded-lg transition-colors"
                                title="Delete event"
                            >
                                <Trash2 className="w-5 h-5" />
                            </button>
                        </div>
                    </div>
                </div>
            ))}

            {
                reportAdvanceVisible && <UsuariosEstadistica onCancel={() => { setReportAdvanceVisible(false) }}/>
            }

            {
                reportGenderVisible && <ReporteGenero onCancel={() => { setReportGenderVisible(false) }}/>
            }

            {
                reportMetasVisible && <AdminMetasGrid onCancel={() => { setReportMetasVisible(false) }}/>
            }

            {
                reportLogrosVisible && <ReporteLogros onCancel={() => { setReportLogrosVisible(false) }}/>
            }
        </div>
    )
}