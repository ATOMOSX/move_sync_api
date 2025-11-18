import { useEffect, useState } from "react";
import { getStadistics } from "../../services/eventApi";
import jsPDF from "jspdf";
import autoTable from "jspdf-autotable";
import { X } from "lucide-react";

interface EventFormProps {
    onCancel: () => void;
}

export default function EventosEstadistica({ onCancel }: EventFormProps) {
    const [eventos, setEventos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        loadStats();
    }, []);

    async function loadStats() {
        const { data } = await getStadistics();
        console.log('eventos: ', data);
        setEventos(data);
        setLoading(false);
    }


    const descargarPDF = () => {
        const doc = new jsPDF();

        doc.setFontSize(16);
        doc.text("Estadísticas de Eventos", 14, 15);

        const tableColumn = [
            "ID",
            "Evento",
            "Fecha",
            "Actividad",
            "Total calorías",
            "Promedio",
            "Máx indiv.",
            "Usuario top",
            "Δ Promedio",
        ];

        const tableRows = eventos.map(ev => [
            ev.idEvento,
            ev.nombreEvento,
            new Date(ev.fecha.replace(" ", "T")).toLocaleString(),
            ev.actividadMasRealizada ?? "—",
            ev.totalCalorias,
            ev.promedioCalorias.toFixed(2),
            ev.maximoIndividual,
            ev.usuarioTop ?? "—",
            ev.diferenciaPromedioGeneral.toFixed(2),
        ]);

        autoTable(doc, {
            startY: 22,
            head: [tableColumn],
            body: tableRows,
            styles: { fontSize: 8 },
            headStyles: { fillColor: [30, 64, 175] } // azul tailwind
        });

        doc.save("estadisticas_eventos.pdf");
    };


    if (loading) return <p className="text-gray-500">Cargando estadísticas...</p>;
    if (error) return <p className="text-red-600">{error}</p>;

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
            <div className="bg-white rounded-lg w-[90vw] h-[90vh] overflow-y-auto p-6">
                <div className="p-6">
                    <div className="p-6">
                        <div className="flex justify-between items-center mb-6">
                            <h2 className="text-2xl font-bold mb-4">Estadísticas de Eventos</h2>
                            <button
                                onClick={onCancel}
                                className="text-gray-400 hover:text-gray-600 transition-colors"
                            >
                                <X className="w-6 h-6" />
                            </button>
                        </div>

                        <button
                            onClick={descargarPDF}
                            className="px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700"
                        >
                            Descargar PDF
                        </button>
                        <div className="overflow-x-auto shadow-md rounded-lg">
                            <table className="min-w-full bg-white border border-gray-300">
                                <thead className="bg-blue-600 text-white">
                                    <tr>
                                        <th className="px-4 py-2 border">ID</th>
                                        <th className="px-4 py-2 border">Evento</th>
                                        <th className="px-4 py-2 border">Fecha</th>
                                        <th className="px-4 py-2 border">Actividad más realizada</th>
                                        <th className="px-4 py-2 border">Total calorías</th>
                                        <th className="px-4 py-2 border">Promedio calorías</th>
                                        <th className="px-4 py-2 border">Máximo individual</th>
                                        <th className="px-4 py-2 border">Usuario top</th>
                                        <th className="px-4 py-2 border">Δ Promedio general</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    {eventos.map((ev) => (
                                        <tr key={ev.idEvento} className="hover:bg-gray-100">
                                            <td className="px-4 py-2 border text-center">{ev.idEvento}</td>
                                            <td className="px-4 py-2 border">{ev.nombreEvento}</td>
                                            <td className="px-4 py-2 border">
                                                {new Date(ev.fecha.replace(" ", "T")).toLocaleString()}
                                            </td>
                                            <td className="px-4 py-2 border text-center">
                                                {ev.actividadMasRealizada ?? "—"}
                                            </td>
                                            <td className="px-4 py-2 border text-center">{ev.totalCalorias}</td>
                                            <td className="px-4 py-2 border text-center">{ev.promedioCalorias.toFixed(2)}</td>
                                            <td className="px-4 py-2 border text-center">{ev.maximoIndividual}</td>
                                            <td className="px-4 py-2 border text-center">{ev.usuarioTop ?? "—"}</td>
                                            <td className="px-4 py-2 border text-center">
                                                {ev.diferenciaPromedioGeneral.toFixed(2)}
                                            </td>
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
