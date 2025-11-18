import { useEffect, useState } from "react"; import jsPDF from "jspdf";
import autoTable from "jspdf-autotable";
import { reportUserAdvance } from "../../services/userApi";
import { X } from "lucide-react";

interface EventFormProps {
    onCancel: () => void;
}

export default function UsuariosEstadistica({ onCancel }: EventFormProps) {
    const [usuarios, setUsuarios] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        load();
    }, []);

    async function load() {
        const { data } = await reportUserAdvance();
        setUsuarios(data);
        setLoading(false);
    }

    const descargarPDF = () => {
        const doc = new jsPDF({
            orientation: "landscape",
            unit: "pt",
            format: "a4",
        });

        doc.setFontSize(22);
        doc.text("Reporte Avanzado de Usuarios", 40, 50);

        const tableColumn = [
            "ID",
            "Nombre",
            "Nivel",
            "Gasto Energético",
            "IMC",
            "Prom. Calorías",
            "Actividad Favorita",
            "Calorías Máx Día",
            "Promedio General",
            "Dif. Promedio",
            "Meta Actual",
            "Días Reg.",
            "Días Rest."
        ];

        const tableRows = usuarios.map((u) => [
            u.idUsuario,
            u.nombreCompleto,
            u.nivelActividad ?? "—",
            u.gastoEnergetico ?? "—",
            u.imc ?? "—",
            u.promedioCaloriasUsuario ?? "—",
            u.actividadFavorita ?? "—",
            u.caloriasDiaTop ?? "—",
            u.promedioGeneral.toFixed(2),
            u.diferenciaPromedio ?? "—",
            u.metaActual ?? "—",
            u.diasRegistrados,
            u.diasRestantes ?? "—",
        ]);

        autoTable(doc, {
            startY: 80,
            head: [tableColumn],
            body: tableRows,
            theme: "striped",
            styles: { fontSize: 8, cellPadding: 3 },
            headStyles: { fillColor: [30, 64, 175], textColor: 255 },
        });

        doc.save("reporte_usuarios.pdf");
    };

    if (loading) return <p className="text-gray-600">Cargando...</p>;

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
            <div className="bg-white rounded-lg w-[90vw] h-[90vh] overflow-y-auto p-6">

                <div className="p-6">
                    <div className="flex justify-between items-center mb-4">
                        <h2 className="text-2xl font-bold">Reporte de Usuarios</h2>

                        <button
                            onClick={descargarPDF}
                            className="px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700"
                        >
                            Descargar PDF
                        </button>
                        <button
                            onClick={onCancel}
                            className="text-gray-400 hover:text-gray-600 transition-colors"
                        >
                            <X className="w-6 h-6" />
                        </button>
                    </div>

                    <div className="overflow-x-auto shadow rounded-lg">
                        <table className="min-w-full bg-white border border-gray-300">
                            <thead className="bg-gray-800 text-white">
                                <tr>
                                    <th className="p-2 border">ID</th>
                                    <th className="p-2 border">Nombre</th>
                                    <th className="p-2 border">Nivel</th>
                                    <th className="p-2 border">Gasto Energético</th>
                                    <th className="p-2 border">IMC</th>
                                    <th className="p-2 border">Prom. Calorías</th>
                                    <th className="p-2 border">Actividad Favorita</th>
                                    <th className="p-2 border">Calorías Día Top</th>
                                    <th className="p-2 border">Promedio General</th>
                                    <th className="p-2 border">Dif. Promedio</th>
                                    <th className="p-2 border">Meta Actual</th>
                                    <th className="p-2 border">Días Reg.</th>
                                    <th className="p-2 border">Días Rest.</th>
                                </tr>
                            </thead>

                            <tbody>
                                {usuarios.map((u) => (
                                    <tr key={u.idUsuario} className="hover:bg-gray-100">
                                        <td className="p-2 border text-center">{u.idUsuario}</td>
                                        <td className="p-2 border">{u.nombreCompleto}</td>
                                        <td className="p-2 border text-center">{u.nivelActividad ?? "—"}</td>
                                        <td className="p-2 border text-center">{u.gastoEnergetico ?? "—"}</td>
                                        <td className="p-2 border text-center">{u.imc ?? "—"}</td>
                                        <td className="p-2 border text-center">{u.promedioCaloriasUsuario ?? "—"}</td>
                                        <td className="p-2 border">{u.actividadFavorita ?? "—"}</td>
                                        <td className="p-2 border text-center">{u.caloriasDiaTop ?? "—"}</td>
                                        <td className="p-2 border text-center">
                                            {u.promedioGeneral?.toFixed(2)}
                                        </td>
                                        <td className="p-2 border text-center">{u.diferenciaPromedio ?? "—"}</td>
                                        <td className="p-2 border text-center">{u.metaActual ?? "—"}</td>
                                        <td className="p-2 border text-center">{u.diasRegistrados}</td>
                                        <td className="p-2 border text-center">{u.diasRestantes ?? "—"}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
}
