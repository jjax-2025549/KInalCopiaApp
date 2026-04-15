const URL_PROD = "http://localhost:8999/productos";

const listarProductos = async () => {
    try {
        const res = await fetch(URL_PROD);
        const productos = await res.json();
        const tabla = document.getElementById('cuerpo-tabla-productos');
        tabla.innerHTML = "";

        productos.forEach(p => {
            tabla.innerHTML += `
                <tr>
                    <td>${p.codigoProducto}</td>
                    <td>${p.descripcion}</td>
                    <td>${p.stock} units</td>
                    <td>Q${p.precioUnitario.toFixed(2)}</td>
                    <td class="text-center">
                        <button class="btn btn-warning btn-sm" onclick="alert('ERROR: No se puede editar la PK')">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="errorIntegridad()">Eliminar</button>
                    </td>
                </tr>`;
        });
        console.log("Productos cargados.");
    } catch (e) {
        console.error("Error al cargar productos:", e);
    }
};

const errorIntegridad = () => {
    alert("ERROR: No se puede eliminar el producto. Tiene registros asociados en Detalle Venta (Integridad Referencial).");
};

document.addEventListener('DOMContentLoaded', listarProductos);