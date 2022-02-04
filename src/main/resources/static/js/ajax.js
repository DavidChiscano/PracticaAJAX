window.onload = inicio;

function inicio() {
	mostrarOfertas();
	$("#Enviar").click(crearOferta);
	$("#filtrarPorPrioridad").click(filtrarOferta);
}

//Borrar Ofertas
$(document).on("click", "#borrar", function() {
	var tr = $(this).closest("tr");
	var id = tr[0].childNodes[0].innerText;
	let ruta = "/borrar/" + id;
	window.location.href = ruta;
	$(this).closest("tr").remove();
});


function addOfertaTabla(id, nombre, precio) {
	let listaOfertas = document.getElementById("listaOfertas");
	let tr = document.createElement("tr");

	let tdId = document.createElement("td");
	tdId.textContent = id;
	let tdNombre = document.createElement("td");
	tdNombre.textContent = nombre;
	let tdPrecio = document.createElement("td");
	tdPrecio.textContent = precio;

	let tdInfo = document.createElement("td");
	let tdBorrar = document.createElement("td");

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


function mostrarOfertas() {
	let listaOfertas = document.getElementById("listaOfertas");
	listaOfertas.replaceChildren();
	fetch('/ofertas', { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for pl n response)
		.then(ofertas => {
			for (let oferta of ofertas) {

				if (oferta.prioridad == "Baja") {

					$("tr").addClass("table-active");

				} else if (oferta.prioridad == "Media") {

					$("tr").addClass("table-warning");

				} else if (oferta.prioridad == "Alta") {

					$("tr").addClass("table-danger");
				}
	
				addOfertaTabla(oferta.id, oferta.nombre, oferta.precio);

			}
		})
}

//Mostrar MOdal
$(document).on("click", "#info", function mostrarInfo() {
	$("#modal").modal("show");
	$(".btn-close").on("click", function() {
		$("#modal").modal("toggle");
	})
	var tr = $(this).closest("tr");
	var id = tr[0].childNodes[0].innerText;
	fetch('/oferta/oferta' + id, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
		.then(oferta => {
			let modal = document.getElementsByClassName("modal-body")[0];
			modal.replaceChildren();
			let p1 = document.createElement('p');
			p1.textContent = "ID: " + oferta.id;
			let p2 = document.createElement('p');
			p2.textContent = "Nombre: " + oferta.nombre;
			modal.appendChild(p1);
			modal.appendChild(p2);
		})
}
);

//Filtrar Ofertas
function filtrarOferta(e) {
	e.preventDefault();
	if ($("#prioridadMedia").is(':checked') || $("#prioridadAlta").is(':checked') || $("#prioridadBaja").is(':checked')) {
		fetch('/filtrar', {
			headers: {
				'Content-type': 'application/json'
			},
			method: 'POST',
			body: JSON.stringify({ prioridad: $("#prioForm input[type='radio']:checked").val() })

		})
			.then(res => res.json())
			.then(ofertas => {
				let listaOfertas = document.getElementById("listaOfertas");
				listaOfertas.replaceChildren();
				for (let oferta of ofertas) {
					if (oferta.prioridad == "Baja") {
						$("tr").toggleClass("table-active");
					} else if (oferta.prioridad == "Media") {
						$("tr").toggleClass("table-warning");
					} else if (oferta.prioridad == "Alta") {
						$("tr").toggleClass("table-danger");
					}
					addOfertaTabla(oferta.id, oferta.nombre, oferta.precio);
				}
			})
	}
}


//Crear ofertas
function crearOferta(e) {
	e.preventDefault();
	if ($('#inputNombre').val() != "" && $('#selectProducto').val() != "" && $('#inputPrecio').val() != "" && $('#hiperenlace').val() != "" && $('#inputDescripcion').val() != "") {
		fetch('/crearOferta', {
			headers: {
				'Content-type': 'application/json'
			},
			method: 'POST',
			body: JSON.stringify({ id: $('id').val(), nombre: $('#inputNombre').val(), prioridad: $('#selectProducto').val(), hiperenlace: $('#hiperenlace').val(), descripcion: $('#inputDescripcion').val(), precio: $('#inputPrecio').val() })
		})
			.then(function(response) {
				if (response.ok) {
					return response.json()
				} else {
					throw "Error";
				}
			}).then(ofertas => {
				for (let oferta of ofertas) {
					if (oferta.prioridad == "Baja") {
						$("tr").addClass("table-active");
					} else if (oferta.prioridad == "Media") {
						$("tr").addClass("table-warning");
					} else if (oferta.prioridad == "Alta") {
						$("tr").addClass("table-danger");
					}
					addOfertaTabla(oferta.id, oferta.nombre, oferta.precio);
				}
			})
	}
}

