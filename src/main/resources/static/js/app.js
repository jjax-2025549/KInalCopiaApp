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
                    <td>Q${venta.total.toFixed(2)}</td>
                    <td>${venta.cliente.DPICliente}</td>
                    <td class="text-center">
                        <button class="btn btn-warning btn-sm">Editar</button>
                    </td>
                </tr>
            `;
        });
        console.log("Ventas cargadas para Julian Jax");
    } catch (error) {
        console.error("Error al conectar con la API:", error);
    }
};

document.addEventListener('DOMContentLoaded', listarVentas);