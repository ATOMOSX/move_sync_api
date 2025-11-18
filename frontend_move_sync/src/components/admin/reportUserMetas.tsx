import { useEffect, useState } from "react";
import { reportMetaAdmin } from "../../services/metasApi";

interface EventFormProps {
    onCancel: () => void;
}

export default function AdminMetasGrid({ onCancel }: EventFormProps) {
    const [metas, setMetas] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        loadStats();
    }, []);

    async function loadStats() {
        const { data } = await reportMetaAdmin();
        console.log('metas: ', data);
        setMetas(data);
        setLoading(false);
    }
    return (
        <div className="p-6">
            <h2 className="text-2xl font-semibold mb-6 text-gray-800">
                Estadísticas de Metas por Usuario
            </h2>

            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                {metas.map((meta) => (
                    <div
                        key={meta.idMeta}
                        className="bg-white shadow-lg rounded-2xl p-5 border border-gray-200 hover:shadow-xl transition-all"
                    >
                        <div className="flex justify-between items-center mb-3">
                            <h3 className="text-xl font-semibold text-gray-800">
                                Meta #{meta.idMeta}
                            </h3>

                            <span
                                className={`px-3 py-1 rounded-full text-sm font-semibold ${meta.diasRestantes < 0
                                    ? "bg-red-100 text-red-600"
                                    : "bg-green-100 text-green-600"
                                    }`}
                            >
                                {meta.diasRestantes < 0
                                    ? "Vencida"
                                    : `Restan ${meta.diasRestantes} días`}
                            </span>
                        </div>

                        <div className="mb-4">
                            <p className="text-gray-600 text-sm">Usuario</p>
                            <p className="text-gray-900 font-medium">{meta.usuario}</p>
                        </div>

                        <div className="grid grid-cols-2 gap-4 text-sm">
                            <div>
                                <p className="text-gray-600">Días registrados</p>
                                <p className="text-gray-900 font-semibold">{meta.diasRegistrados}</p>
                            </div>

                            <div>
                                <p className="text-gray-600">Calorías por día</p>
                                <p className="text-gray-900 font-semibold">
                                    {meta.perdidaCaloriasDiarias}
                                </p>
                            </div>

                            <div className="col-span-2">
                                <p className="text-gray-600">Total calorías objetivo</p>
                                <p className="text-gray-900 font-bold text-lg">
                                    {meta.totalCalorias.toLocaleString()}
                                </p>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}
