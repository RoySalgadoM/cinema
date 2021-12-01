const url = "http://localhost:8080/cinema_war_exploded/movie";

const getMovie = () => {
    $.ajax({
        method: "GET",
        url: url
    }).done(function (res) {
        content = "";
        for (let i = 0; i < res.length; i++) {
            content += `
                        <tr>
                            <td>${res[i].id}</td>
                            <td>${res[i].title}</td>
                            <td>${res[i].description}</td>
                            <td>${res[i].synopsis}</td>
                            <td>${res[i].rating}</td>
                            <td>${res[i].registered_date}</td>
                            <td>${res[i].updated_date}</td>
                            <td>${res[i].state ==1?"Activo":"Inactivo"} </td>
                            <td>${res[i].category} </td>
                            <td>
                                <button type="button" onclick="getMovieById(${res[i].id})" data-bs-toggle="modal" data-bs-target="#modificarPelicula" class="btn btn-outline-primary"><i class="fas fa-edit"></i></button>
                            </td>
                            <td>
                                <button onclick="deleteMovie(${res[i].id})" class="btn btn-outline-danger"><i class="fas fa-trash"></i></button>
                            </td>
                        </tr>
                    `;
        }
        $("#table > tbody").html(content);

    });
};

const getMovieById = async (id) => {
    await $.ajax({
        method: "GET",
        url: url + '/' + id
    }).done(res =>{
        document.getElementById("idM").value = res.id;
        document.getElementById("titleM").value = res.title;
        document.getElementById("descriptionM").value = res.description;
        document.getElementById("synopsisM").value = res.synopsis;
        document.getElementById("ratingM").value = res.rating;
        document.getElementById("idCategoriaM").value =res.category;
    });
};

const createMovie = async() => {
    let movie = new Object();
    movie.title = document.getElementById("title").value;
    movie.description = document.getElementById("description").value;
    movie.synopsis = document.getElementById("synopsis").value;
    movie.rating = document.getElementById("rating").value;
    movie.category = document.getElementById("idCategoria").value;
    await $.ajax({
        method: "POST",
        url: url + '/save',
        data: movie
    }).done(res =>{
        Swal.fire({
            title: 'La pelicula ha sido registrada',
            confirmButtonText: 'Recargar tabla de peliculas',
            icon: 'success',
        }).then((result) => {
            if (result.isConfirmed) {
                getMovie();
                document.getElementById("title").value ="";
                document.getElementById("description").value="";
                document.getElementById("synopsis").value="";
                document.getElementById("rating").value="";
                document.getElementById("idCategoria").value="";
                document.getElementById("closeRegister").click();
            }
        })
    });
}

const modifyMovie= async()=>{
    let movie = new Object();
    movie.title = document.getElementById("titleM").value;
    movie.description = document.getElementById("descriptionM").value;
    movie.synopsis = document.getElementById("synopsisM").value;
    movie.rating = document.getElementById("ratingM").value;
    movie.category = document.getElementById("idCategoriaM").value;
    movie.id = document.getElementById("idM").value;
    await $.ajax({
        method: "POST",
        url: url + '/save/'+document.getElementById("idM").value,
        data: movie
    }).done(res =>{
        Swal.fire({
            title: 'La pelicula ha sido modificada',
            confirmButtonText: 'Recargar la tabla de peliculas',
            icon: 'success',
        }).then((result) => {
            if (result.isConfirmed) {
                getMovie();
                document.getElementById("closeModify").click();
            }
        })
    });
}
const deleteMovie= async(id)=>{
    await $.ajax({
        method: "POST",
        url: url + '/delete/'+id
    }).done(res =>{
        Swal.fire({
            title: 'La pelicula ha sido eliminada',
            confirmButtonText: 'Recargar la tabla de peliculas',
            icon: 'success',
        }).then((result) => {
            if (result.isConfirmed) {
                getMovie();
            }
        })
    });
}