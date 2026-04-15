const URL_USUARIOS = "http://localhost:8999/usuarios";

const listarUsuarios = async () => {
    try {
        const res = await fetch(URL_USUARIOS);
        const data = await res.json();
        const tabla = document.getElementById('cuerpo-tabla-usuarios');
        tabla.innerHTML = "";
        data.forEach(u => {
            tabla.innerHTML += `
                <tr>
                    <td>${u.codigoUsuario}</td>
                    <td>${u.username}</td>
                    <td>${u.email}</td>
                    <td><span class="badge bg-secondary">${u.rol}</span></td>
                    <td class="text-center">
                        <button class="btn btn-warning btn-sm" onclick="alert('No se puede editar el usuario')">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="alert('ERROR: No se pueden eliminar usuarios con historial')">Eliminar</button>
                    </td>
                </tr>`;
        });
    } catch (e) { console.error(e); }
};
document.addEventListener('DOMContentLoaded', listarUsuarios);