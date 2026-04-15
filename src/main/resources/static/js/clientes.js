const URL_CLIENTES = "http://localhost:8999/clientes";

const listarClientes = async () => {
    try {
        const res = await fetch(URL_CLIENTES);
        const clientes = await res.json();
        const tabla = document.getElementById('cuerpo-tabla-clientes');
        tabla.innerHTML = "";
        clientes.forEach(c => {
            tabla.innerHTML += `
                <tr>
                    <td>${c.DPICliente}</td>
                    <td>${c.nombreCliente}</td>
                    <td>${c.apellidoCliente}</td>
                    <td>${c.direccion}</td>
                    <td class="text-center">
                        <button class="btn btn-warning btn-sm" onclick="alert('No se puede editar la PK (DPI)')">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="alert('ERROR: No se puede eliminar un cliente con ventas asociadas (Integridad Referencial)')">Eliminar</button>
                    </td>
                </tr>`;
        });
    } catch (e) { console.error(e); }
};
document.addEventListener('DOMContentLoaded', listarClientes);