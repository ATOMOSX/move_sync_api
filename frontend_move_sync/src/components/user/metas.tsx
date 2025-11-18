import { Calendar, Dumbbell, Edit, Plus, Trash2, } from "lucide-react";
import { useEffect, useState } from "react";

import { allMetas } from "../../services/metasApi";
import { useAuth } from "../../context/AuthContext";

interface EventFormProps {
    onCancel: () => void;
}
export default function Meta({ onCancel }: EventFormProps) {
    const { user } = useAuth();
    const [metas, setMetas] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [reportAdvanceVisible, setReportAdvanceVisible] = useState(false);
    const [reportGenderVisible, setReportGenderVisible] = useState(false);
   

    useEffect(() => {
        loadMetas();
    }, []);

    const loadMetas = async () => {
        setLoading(true);
        const { data } = await allMetas();
        console.log('metas: ', metas);
        if (!data || data.length === 0) {
            return <p className="text-center text-gray-600">No hay datos para mostrar.</p>;
        }
        setMetas(data);
        setLoading(false);
        console.log('userLogin: ', user);

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

            </div>

            {metas.map((meta) => (
                <div
                    key={meta.idMeta}
                    className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow"
                >
                    <div className="flex justify-between items-start">
                        <div className="flex-1">
                            <div className="flex items-center gap-3 mb-2">
                                <h3 className="text-xl font-semibold text-gray-900">{meta.objetivo}</h3>
                            </div>
                            <div className="flex flex-wrap gap-4 text-sm text-gray-600">
                                <div className="p-2 text-blue-600 hover:bg-blue-50 rounded-lg transition-colors">
                                    <Calendar className="w-4 h-4" /> <span>Inicio </span>
                                    {(meta.fechaInicio)}
                                </div>
                                <div className="p-2 text-red-600 hover:bg-red-50 rounded-lg transition-colors">
                                    <Calendar className="w-4 h-4" /> <span>Fin </span>
                                    {(meta.fechaFin)}
                                </div>
                                <div className="p-2 text-green-600 hover:bg-green-50 rounded-lg transition-colors">
                                    <Dumbbell className="w-4 h-4" />
                                    {meta.perdidaCaloriasDiarias}
                                </div>
                            </div>
                        </div>

                        <div className="flex gap-2 ml-4">
                            <button
                                onClick={() => handleEdit(meta)}
                                className="p-2 text-blue-600 hover:bg-blue-50 rounded-lg transition-colors"
                                title="Edit event"
                            >
                                <Edit className="w-5 h-5" />
                            </button>
                            <button
                                onClick={() => handleDelete(meta.idMeta)}
                                className="p-2 text-red-600 hover:bg-red-50 rounded-lg transition-colors"
                                title="Delete event"
                            >
                                <Trash2 className="w-5 h-5" />
                            </button>
                        </div>
                    </div>
                </div>
            ))}
        </div>
    )
}