const listarVentas = async () => {
    try {
        const respuesta = await fetch(URL_API);
        const ventas = await respuesta.json();

        const tabla = document.getElementById('cuerpo-tabla-ventas');
        tabla.innerHTML = "";

        ventas.forEach(venta => {
            tabla.innerHTML += `
                <tr>
                    <td>${venta.codigoVenta}</td>
                    <td>${venta.fechaVenta}</td>
                    <td>Q${venta.total.toFixed(2)}</td>
                    <td>${venta.cliente.DPICliente}</td>
                    <td class="text-center">
                        <button class="btn btn-warning btn-sm" onclick="prepararEdicion(${venta.codigoVenta})">
                            Editar
                        </button>
                        <button class="btn btn-danger btn-sm" onclick="errorRestriccion()">
                            Eliminar
                        </button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error("Error:", error);
    }
};

// Función para mostrar el error que pidió el profe
const errorRestriccion = () => {
    alert("ERROR: No se pueden eliminar ni actualizar llaves primarias o foráneas por integridad referencial.");
};

document.addEventListener('DOMContentLoaded', listarVentas);