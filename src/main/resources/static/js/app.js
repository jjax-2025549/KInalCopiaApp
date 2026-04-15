const URL_API = "http://localhost:8999/ventas";

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
                    <td>Q${venta.total}</td>
                    <td>${venta.cliente.DPICliente}</td>
                    <td class="text-center">
                        <button class="btn btn-warning btn-sm" onclick="alert('No se puede editar PK/FK')">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="errorRestriccion()">Eliminar</button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error("Error al jalar datos:", error);
    }
};

const errorRestriccion = () => {
    alert("ERROR: No se pueden eliminar ni actualizar llaves primarias o foráneas por integridad referencial.");
};

document.addEventListener('DOMContentLoaded', listarVentas);