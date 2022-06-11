const tableList = document.querySelector('.table-list') //contenedor de las filas con las personas
const addPersonaForm = document.querySelector('.add-person-form') //formulario agregar persona
const nombreValue = document.getElementById('nombre-value') //id del input donde ponemos el nombre
const edadValue = document.getElementById('edad-value') //id del input donde ponemos la edad

const btnSubmit = document.querySelector('.btn-submit') //boton submit del form


let output = '';

// let url = 'post.json' 

let url = 'http://localhost:8080/api/personas'



//GET - READ THE POST
//Method : GET
const obtenerPersonas = (url) => {
    fetch(url)
    .then(res=> res.json())
    .then(data => renderPersonas(data))
}
obtenerPersonas(url)




const renderPersonas = data => {
    data.forEach(persona => {
        // console.log(persona)
        const {id, nombre,edad} = persona 
        output += `
        <tr>
            <th scope="row">${id}</th>
            <td class='filaNombre'>${nombre}</td>
            <td class='filaEdad'>${edad}</td>
            <td id=${id}> <a href="#" class="btn btn-primary" id="editar">Editar</a> <a href="#" class="btn btn-danger" id="eliminar">Eliminar</a></td>
        </tr>   
        `
    });
    tableList.innerHTML = output;
}



//Create - insert THE POST
//Method : POST
addPersonaForm.addEventListener('submit', e =>{
    e.preventDefault() // para evitar que se recargue la pagina y podamos trabajar con este form
    if(nombreValue.value == '' || edadValue.value == '') {
        alert('Debes completar los datos solicitados')
    }else if ((/\d/g).test(nombreValue.value) || (/\D/g).test(edadValue.value) ) {
        alert('El nombre debe estar compuesto por letras y la edad con numeros')
    } else {
        fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                nombre : nombreValue.value,
                edad :  edadValue.value
            })
        })
        .then(res => res.json())
        .then(data => {
            const dataArr = [];
            dataArr.push(data);
            renderPersonas(dataArr)
        })
    }
    //una vez que agrego la persona, reseteo el input para que quede vacio
    nombreValue.value = '';
    edadValue.value = '';
})


//Delete and update
//Method : DELETE y PUT
tableList.addEventListener('click', (e)=>{
    e.preventDefault()
    // console.log(e.target.id)
    const btnEliminarApretado = e.target.id == 'eliminar'
    const btnEliminarEditar = e.target.id == 'editar'
    const idBnt = e.target.parentElement.id //parentElement me trae el elemento padre, el td y al poner id, me trae el id de ese td

    //Delete - eliminar persona
    if(btnEliminarApretado){
        fetch(`${url}/${idBnt}`,{
            method: 'DELETE'
        })
        // .then(res=> res.json())
        // .then(() => location.reload())
        location.reload()
    }

    //update - editar persona
    if(btnEliminarEditar){
        const parent = e.target.parentElement.parentElement //td => elemento padre
        let nombreContent = parent.querySelector('.filaNombre').textContent //tengo el valor del input que tiene el nombre
        let edadContent = parent.querySelector('.filaEdad').textContent //tengo la edad
        
        nombreValue.value = nombreContent
        edadValue.value = edadContent

        btnSubmit.innerText = 'Modificar Persona' 

    }

    btnSubmit.addEventListener('click', e=>{
        e.preventDefault();
        fetch(`${url}/${idBnt}`,{
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                nombre : nombreValue.value,
                edad :  edadValue.value
            })
        })
        .then(res=> res.json())
        .then(() => location.reload())
        // window.location.reload()
        btnSubmit.innerText = 'Agregar Persona' 

    })


})


