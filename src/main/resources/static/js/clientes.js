const URL_CLIENTES = "http://localhost:8999/clientes"; // Asegúrate que esta ruta sea la de tu @RequestMapping

const listarClientes = async () => {
    try {
        const res = await fetch(URL_CLIENTES);
        const data = await res.json();
        const tabla = document.getElementById('cuerpo-tabla-clientes');
        if(!tabla) return; // Evita errores si no encuentra el elemento

        tabla.innerHTML = "";
        data.forEach(c => {
            // Manejamos ambos posibles nombres de propiedad para que no salga 'undefined'
            tabla.innerHTML += `
                <tr>
                    <td>${c.dpiCliente || c.dpi_cliente}</td>
                    <td class="fw-bold">${c.nombreCliente || c.nombre_cliente}</td>
                    <td>${c.apellidoCliente || c.apellido_cliente}</td>
                    <td>${c.direccion}</td>
                    <td class="text-center">
                        <button class="btn btn-warning btn-sm">Editar</button>
                        <button class="btn btn-danger btn-sm">Eliminar</button>
                    </td>
                </tr>`;
        });
    } catch (e) {
        console.error("Error al cargar:", e);
    }
};

const guardarCliente = async () => {
    // IMPORTANTE: Los nombres de aquí deben ser IGUALES a los atributos de tu clase Cliente.java
    const nuevoCliente = {
        dpiCliente: document.getElementById('dpi').value,
        nombreCliente: document.getElementById('nom').value,
        apellidoCliente: document.getElementById('ape').value,
        direccion: document.getElementById('dir').value,
        estado: 1
    };

    try {
        const res = await fetch(URL_CLIENTES, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevoCliente)
        });

        if (res.ok) {
            alert("¡Cliente guardado!");
            location.reload();
        } else {
            alert("Error en el servidor. Revisá que el DPI no esté repetido o la consola de IntelliJ.");
        }
    } catch (e) {
        console.error("Error post:", e);
    }
};

document.addEventListener('DOMContentLoaded', listarClientes);