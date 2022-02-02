window.onload = inicio;

function inicio() {
	mostrarOfertas();
	$("#Enviar").click(crearOferta());
	$("#filtrarPorPrioridad").click(filtrarOferta());

	$(document).on("click", "#borrar", function() {
		var tr = $(this).closest("tr");
		var id = tr[0].childNodes[0].innerText;
		let ruta = "/borrar/" + id;
		window.location.href = ruta;
		$(this).closest("tr").remove();
	});
}

function mostrarOfertas() {
	fetch('/ofertas', { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for pl n response)
		.then(response => {
			for (let oferta of response) {
				let listaOfertas = document.getElementById("listaOfertas");
				let tr = document.createElement("tr");

				let tdId = document.createElement("td");
				tdId.textContent = oferta.id;
				let tdNombre = document.createElement("td");
				tdNombre.textContent = oferta.nombre;
				let tdPrecio = document.createElement("td");
				tdPrecio.textContent = oferta.precio;
				let tdInfo = document.createElement("td");
				let tdBorrar = document.createElement("td");

				if (oferta.prioridad == "Baja") {
					tr.setAttribute("class", "table-active");
				} else if (oferta.prioridad == "Media") {
					tr.setAttribute("class", "table-warning");
				} else if (oferta.prioridad == "Alta") {
					tr.setAttribute("class", "table-danger");
				}


				let btnInfo = document.createElement("input");
				btnInfo.setAttribute("type", "button");
				btnInfo.setAttribute("class", "btn btn-info");
				btnInfo.setAttribute("id", "info");
				btnInfo.setAttribute("value", "Info");

				let btnBorrar = document.createElement("input");
				btnBorrar.setAttribute("type", "button");
				btnBorrar.setAttribute("class", "btn btn-danger");
				btnBorrar.setAttribute("id", "borrar");
				btnBorrar.setAttribute("value", "Borrar");

				tr.appendChild(tdId);
				tr.appendChild(tdNombre);
				tr.appendChild(tdPrecio);
				tdInfo.appendChild(btnInfo);
				tdBorrar.appendChild(btnBorrar);
				tr.appendChild(tdInfo);
				tr.appendChild(tdBorrar);
				listaOfertas.appendChild(tr);
			}
		})

}

$(document).on("click", "#info", function mostrarInfo() {
	$("#modal").modal("show");
	$(".btn-close").on("click", function(){
		$("#modal").modal("hide");
	}) 
    var tr = $(this).closest("tr");
    var id = tr[0].childNodes[0].innerText;
	fetch('/oferta/oferta'+id, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
		.then(oferta => {
			let modal = document.getElementsByClassName("modal-body")[0];
			modal.replaceChildren();
			let p1 = document.createElement('p');
			p1.textContent = oferta.id;
			let p2 = document.createElement('p');
			p2.textContent = oferta.nombre;
			modal.appendChild(p1);
			modal.appendChild(p2);
		})
}
);

function filtrarOferta() {

	if ($('#prioridadBaja').val()) {
		fetch('/filtrarPrio', {
			headers: {
				'Content-type': 'application/json'
			},
			method: 'POST',
			body: JSON.stringify({ prioridad: $('#prioridadBaja').val() })
		})
           .then(function(response) {
                if(response.ok) {
                    return response.json()
                } else {
                    throw "El email ya existe";
                }

            })
            .then(data => {
                
            })
    }
}


function crearOferta() {
	let errorDiv = document.getElementById("errorDiv");
	errorDiv.replaceChildren();
	if ($('#inputNombre').val() != "" && $('#selectProducto').val() != "" && $('#inputPrecio').val() != "" && $('#hiperenlace').val() != "" && $('#inputDescripcion').val() != "") {
		fetch('/crearOferta', {
			headers: {
				'Content-type': 'application/json'
			},
			method: 'POST',
			body: JSON.stringify({ nombre: $('#inputNombre').val(), prioridad: $('#selectProducto').val(), precio: $('#inputPrecio'), hiperenlace: $('#hiperenlace'), descripcion: $('#inputDescripcion') })
		})
			.then(function(response) {
				if (response.ok) {
					return response.json()
				} else {
					throw "El producto ya existe";
				}

			}).catch(function(messsageDeError) {
				let errorDiv = document.getElementById("errorDiv");
				let div = document.createElement('div');
				div.classList.add("alert", "alert-dismissible", "alert-danger");
				let button = document.createElement('button');
				button.classList.add("btn-close");
				button.setAttribute("data-bs-dismiss", "alert");
				let link = document.createElement('a');
				link.classList.add("alert-link");
				link.setAttribute("href", "#");
				var linkText = document.createTextNode(messsageDeError);
				link.appendChild(linkText);
				div.appendChild(button);
				div.appendChild(link);
				var textNode = document.createTextNode(" Prueba con otro");
				div.appendChild(textNode);
				errorDiv.appendChild(div);
			});
	}
}
