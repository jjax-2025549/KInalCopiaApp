const URL_DETALLES = "http://localhost:8999/detalleVentas";

const listarDetalles = async () => {
    try {
        const res = await fetch(URL_DETALLES);
        const data = await res.json();
        const tabla = document.getElementById('cuerpo-tabla-detalles');
        tabla.innerHTML = "";

        data.forEach(d => {
            tabla.innerHTML += `
                <tr>
                    <td>${d.codigoDetalleVenta}</td>
                    <td>${d.venta.codigoVenta}</td>
                    <td>${d.producto.descripcion}</td>
                    <td>${d.cantidad}</td>
                    <td class="text-center">
                        <button class="btn btn-danger btn-sm" onclick="alert('ERROR: No se puede eliminar por integridad referencial (FK)')">
                            Eliminar
                        </button>
                    </td>
                </tr>`;
        });
    } catch (e) {
        console.error("Error en Detalle Venta:", e);
    }
};

document.addEventListener('DOMContentLoaded', listarDetalles);