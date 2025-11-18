import { X } from "lucide-react";
import { reportMeta } from "../../services/metasApi";
import { useEffect, useState } from "react";
import { useAuth } from "../../context/AuthContext";

interface EventFormProps {
    onCancel: () => void;
    idUsuario: string;
}

export default function ReportMeta({ onCancel, idUsuario }: EventFormProps) {
    const { user } = useAuth();
    const [meta, setMetas] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        load();
    }, []);

    async function load() {

        const { data } = user ? await reportMeta(user.id) : [];
        console.log('META USUARIO', data);
        
        if (!data) {
            return <p className="text-center text-gray-600">No hay datos para mostrar.</p>;
        }

        setMetas(data);
        setLoading(false);
    }

    
    if (loading) return <p className="text-gray-600">Cargando...</p>;

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
            <div className="bg-white rounded-lg w-[90vw] h-[90vh] overflow-y-auto p-6">

                <div className="p-6">
                    <div className="flex justify-between items-center mb-4">
                        <h2 className="text-2xl font-bold">Reporte de Usuarios</h2>
                        <button
                            onClick={onCancel}
                            className="text-gray-400 hover:text-gray-600 transition-colors"
                        >
                            <X className="w-6 h-6" />
                        </button>
                    </div>
                    <div className="p-4 grid gap-4">
                        
                            <div
                                key={meta?.idMeta}
                                className="bg-white shadow rounded-2xl p-4 border border-gray-200"
                            >
                                <h2 className="text-xl font-bold mb-2 text-gray-800">
                                    {meta.objetivo}
                                </h2>

                                <div className="grid grid-cols-2 gap-2 text-sm text-gray-700">
                                    <p><span className="font-semibold">ID Meta:</span> {meta?.idMeta}</p>
                                    <p><span className="font-semibold">Inicio:</span> {meta?.fechaInicio}</p>
                                    <p><span className="font-semibold">Fin:</span> {meta?.fechaFin}</p>
                                    <p><span className="font-semibold">Calorías diarias:</span> {meta?.perdidaCaloriasDiarias}</p>
                                    
                                </div>
                            </div>
                    
                    </div>
                </div>
            </div>
        </div>
    );
}
