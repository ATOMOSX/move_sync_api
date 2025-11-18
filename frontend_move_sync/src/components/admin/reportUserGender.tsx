import { useEffect, useState } from "react";
import { PieChart, Pie, Cell, Tooltip, Legend, BarChart, Bar, XAxis, YAxis, CartesianGrid } from "recharts";
import { reportGenderAdvance } from "../../services/userApi";
import { X } from "lucide-react";

interface EventFormProps {
    onCancel: () => void;
}

export default function ReporteGenero({ onCancel }: EventFormProps) {
    const [reporte, setReporte] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {

        load();
    }, []);

    async function load() {
        const { data } = await reportGenderAdvance();
        setReporte(data);
        setLoading(false);
    }

    if (loading) return <p className="text-gray-500">Cargando reporte...</p>;
    if (!reporte) return <p className="text-red-600">No se pudo cargar la información.</p>;

    const COLORS = ["#4F46E5", "#F43F5E"];

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
            <div className="bg-white rounded-lg w-[90vw] h-[90vh] overflow-y-auto p-6">
                <div className="p-6">
                    <button
                        onClick={onCancel}
                        className="text-gray-400 hover:text-gray-600 transition-colors"
                    >
                        <X className="w-6 h-6" />
                    </button>
                    <div className="p-6 bg-gray-50 rounded-lg shadow">
                        <h1 className="text-2xl font-bold mb-2">{reporte.titulo}</h1>
                        <p className="text-gray-600 mb-4">{reporte.descripcion}</p>

                        <div className="mb-4 text-sm text-gray-500">
                            <p><strong>Fecha de generación:</strong> {new Date(reporte.fechaGeneracion).toLocaleString()}</p>
                            <p><strong>Total usuarios:</strong> {reporte.totalRegistros}</p>
                        </div>

                        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mt-4">
                            <div className="bg-white p-4 rounded-lg shadow">
                                <h2 className="text-xl font-bold mb-2">Distribución porcentual</h2>
                                <PieChart width={300} height={300}>
                                    <Pie
                                        data={reporte.datos}
                                        dataKey="porcentaje"
                                        nameKey="descripcionGenero"
                                        cx="50%"
                                        cy="50%"
                                        outerRadius={100}
                                        label
                                    >
                                        {reporte.datos.map((_, index) => (
                                            <Cell key={index} fill={COLORS[index]} />
                                        ))}
                                    </Pie>
                                    <Tooltip />
                                    <Legend />
                                </PieChart>
                            </div>

                            <div className="bg-white p-4 rounded-lg shadow">
                                <h2 className="text-xl font-bold mb-2">Cantidad por género</h2>
                                <BarChart width={350} height={300} data={reporte.estadisticas}>
                                    <CartesianGrid strokeDasharray="3 3" />
                                    <XAxis dataKey="etiqueta" />
                                    <YAxis />
                                    <Tooltip />
                                    <Bar dataKey="cantidad" fill="#4F46E5" />
                                </BarChart>
                            </div>
                        </div>

                        <div className="mt-6 bg-white rounded-lg shadow p-4 overflow-x-auto">
                            <h2 className="text-xl font-bold mb-3">Detalle del reporte</h2>

                            <table className="min-w-full border border-gray-300 text-sm">
                                <thead className="bg-blue-600 text-white">
                                    <tr>
                                        <th className="px-3 py-2 border">Género</th>
                                        <th className="px-3 py-2 border">Descripción</th>
                                        <th className="px-3 py-2 border">Cantidad</th>
                                        <th className="px-3 py-2 border">Porcentaje</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {reporte.datos.map((row, i) => (
                                        <tr key={i} className="hover:bg-gray-100">
                                            <td className="px-3 py-2 border text-center">{row.genero}</td>
                                            <td className="px-3 py-2 border">{row.descripcionGenero}</td>
                                            <td className="px-3 py-2 border text-center">{row.cantidad}</td>
                                            <td className="px-3 py-2 border text-center">{row.porcentaje}%</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
