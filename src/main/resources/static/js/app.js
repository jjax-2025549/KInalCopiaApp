const URL_API = "http://localhost:8999/ventas";

// 1. Esta función jala los datos del servidor
const listarVentas = async () => {
    try {
        const respuesta = await fetch(URL_API); // Petición GET
        const ventas = await respuesta.json();  // Convertimos a JSON

        const tabla = document.getElementById('cuerpo-tabla-ventas');
        tabla.innerHTML = ""; // Limpiamos la tabla antes de llenarla

        // 2. Recorremos cada venta y creamos la fila
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
                    </td>
                </tr>
            `;
        });
        console.log("Datos cargados con éxito");
    } catch (error) {
        console.error("No se pudo jalar la info. ¿Está encendido el servidor?", error);
    }
};

// 3. Ejecutar la función automáticamente al abrir la página
document.addEventListener('DOMContentLoaded', listarVentas);