import React, { useEffect, useState } from "react";
import { reportLogrosAdmin } from "../../services/logrosApi";
import { Bar, BarChart, CartesianGrid, Cell, Legend, Pie, PieChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from "recharts";

interface EventFormProps {
    onCancel: () => void;
}
const COLORS = ["#3b82f6", "#10b981", "#f59e0b", "#ef4444"];

export default function ReporteLogros({ onCancel }: EventFormProps) {
    const [reporteData, setReporteData] = useState<any>({});
    const [datos, setDatos] = useState<any[]>([]);

    useEffect(() => {
        loadStats();
    }, []);

    async function loadStats() {
        const { data } = await reportLogrosAdmin();
        console.log('logros: ', data);
        setReporteData(data);
        setDatos(data.datos);
    }

    return (
        <div className="p-6 max-w-5xl mx-auto">
            <div className="bg-white shadow-lg rounded-2xl p-6 mb-6 border border-gray-200">
                <h2 className="text-2xl font-bold text-gray-800 mb-2">{reporteData.titulo}</h2>
                <p className="text-gray-600 mb-4">{reporteData.descripcion}</p>

                <div className="flex flex-wrap gap-6 text-sm text-gray-700">
                    <div>
                        <strong className="text-gray-900">Fecha generación: </strong>
                        {new Date(reporteData.fechaGeneracion).toLocaleString("es-ES")}
                    </div>

                    <div>
                        <strong className="text-gray-900">Total registros: </strong>
                        {reporteData.totalRegistros}
                    </div>
                </div>
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-2 gap-6">
                {datos.map((item, index) => (
                    <div
                        key={index}
                        className="bg-white shadow-md rounded-2xl p-5 border border-gray-200 hover:shadow-xl transition-all"
                    >
                        <h3 className="text-lg font-semibold text-gray-800 mb-2">
                            {item.tipo}
                        </h3>

                        <p className="text-gray-700 mb-1">
                            <span className="font-semibold">Cantidad:</span> {item.cantidad}
                        </p>

                        <p className="text-gray-700 mb-3">
                            <span className="font-semibold">Porcentaje:</span>{" "}
                            {item.percentaje}%
                        </p>

                        <div className="w-full bg-gray-200 h-3 rounded-full overflow-hidden">
                            <div
                                className="h-3 bg-blue-500 rounded-full"
                                style={{ width: `${item.percentaje}%` }}
                            ></div>
                        </div>
                    </div>
                ))}
            </div>
            <br />

            <div className="grid grid-cols-1 lg:grid-cols-1 gap-10">

                <br />
                <div className="bg-white shadow-md rounded-2xl p-6 border border-gray-200">
                    <h3 className="text-xl font-semibold text-gray-800 mb-4 text-center">
                        Distribución por Tipo
                    </h3>
                    <ResponsiveContainer width="100%" height={300}>
                        <PieChart>
                            <Pie
                                data={datos}
                                dataKey="porcentaje"
                                nameKey="tipo"
                                outerRadius={120}
                                label
                            >
                                {datos.map((entry, index) => (
                                    <Cell key={index} fill={COLORS[index % COLORS.length]} />
                                ))}
                            </Pie>

                            <Tooltip />
                        </PieChart>
                    </ResponsiveContainer>
                </div>
            </div>
            <br />
            <div className="bg-white shadow-md rounded-2xl p-6 border border-gray-200">
                <h3 className="text-xl font-semibold text-gray-800 mb-4 text-center">
                    Cantidades por Tipo (Bar)
                </h3>

                <ResponsiveContainer width="100%" height={300}>
                    <BarChart data={datos}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="tipo" />
                        <YAxis />
                        <Tooltip />
                        <Legend />

                        <Bar dataKey="cantidad" fill="#3b82f6" radius={[6, 6, 0, 0]} />
                    </BarChart>
                </ResponsiveContainer>
            </div>

        </div>
    );
}
