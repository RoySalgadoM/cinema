const url = "http://localhost:8080/cinema_war_exploded/category";

const getCategoria = () => {
    $.ajax({
        method: "GET",
        url: url
    }).done(function (res) {
        content = "";
        for (let i = 0; i < res.length; i++) {
            content += `
                        <tr>
                            <td>${res[i].id}</td>
                            <td>${res[i].name}</td>
                            <td>
                                <button type="button" onclick="getCategoriaById(${res[i].id})" data-bs-toggle="modal" data-bs-target="#modificarCategoria" class="btn btn-outline-primary"><i class="fas fa-edit"></i></button>
                            </td>
                            <td>
                                <button onclick="deleteCategoria(${res[i].id})" class="btn btn-outline-danger"><i class="fas fa-trash"></i></button>
                            </td>
                        </tr>
                    `;
        }
        $("#table > tbody").html(content);

    });
};

const getCategoriaById = async (id) => {
    await $.ajax({
        method: "GET",
        url: url + '/' + id
    }).done(res =>{
        document.getElementById("idM").value = res.id;
        document.getElementById("nombreM").value = res.name;
    });
};

const crearCategoria = async() => {
    let marca = new Object();
    marca.name = document.getElementById("nombre").value;
    await $.ajax({
        method: "POST",
        url: url + '/save',
        data: marca
    }).done(res =>{
        Swal.fire({
            title: 'La categoria ha sido registrada',
            confirmButtonText: 'Recargar tabla de categoria',
            icon: 'success',
        }).then((result) => {
            if (result.isConfirmed) {
                getCategoria();
                document.getElementById("nombre").value ="";
                document.getElementById("closeRegister").click();
            }
        })
    });
}

const modificarCategoria= async()=>{
    let marca = new Object();
    marca.name = document.getElementById("nombreM").value;
    marca.id = document.getElementById("idM").value;
    await $.ajax({
        method: "POST",
        url: url + '/save/'+document.getElementById("idM").value,
        data: marca
    }).done(res =>{
        Swal.fire({
            title: 'La ctaegoria ha sido modificada',
            confirmButtonText: 'Recargar la tabla de categorias',
            icon: 'success',
        }).then((result) => {
            if (result.isConfirmed) {
                getCategoria();
                document.getElementById("closeModify").click();
            }
        })
    });
}
const deleteCategoria= async(id)=>{
    await $.ajax({
        method: "POST",
        url: url + '/delete/'+id
    }).done(res =>{
        Swal.fire({
            title: 'La categoria ha sido eliminada',
            confirmButtonText: 'Recargar la tabla de categoria',
            icon: 'success',
        }).then((result) => {
            if (result.isConfirmed) {
                getCategoria();
            }
        })
    });
}