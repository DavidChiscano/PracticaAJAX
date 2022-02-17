window.onload = inicio;

function inicio() {
	mostrarOfertas();
	$("#Enviar").click(crearOferta);
	$("#filtrarPorPrioridad").click(filtrarOferta);
}

function addOfertaTabla(oferta) {
	let listaOfertas = document.getElementById("listaOfertas");
	let tr = document.createElement("tr");

	if (oferta.prioridad == "Baja") {
		tr.setAttribute("class", "table-active");
	} else if (oferta.prioridad == "Media") {
		tr.setAttribute("class", "table-warning");
	} else if (oferta.prioridad == "Alta") {
		tr.setAttribute("class", "table-danger");
	}

	let tdId = document.createElement("td");
	tdId.textContent = oferta.id;
	let tdNombre = document.createElement("td");
	tdNombre.textContent = oferta.nombre;
	let tdPrecio = document.createElement("td");
	tdPrecio.textContent = oferta.precio;

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
				addOfertaTabla(oferta);
			}
		})
}

//Borrar Ofertas
$(document).on("click", "#borrar", function() {
	var tr = $(this).closest("tr");
	var id = tr[0].childNodes[0].innerText;

	fetch("/borrar/" + id, {
		headers: { "Content-Type": "application/json; charset=utf-8" }
	})
		.then(function(response) {
			if (response.ok) {
				return response.json()
			} else {
				throw "Error";
			}

		}).then(res => {
			$(this).closest("tr").remove();
			mostrarOfertas();
		});

});


//Mostrar MOdal
$(document).on("click", "#info", function mostrarInfo() {
	var tr = $(this).closest("tr");
	var id = tr[0].childNodes[0].innerText;
	$("#modal").modal("show");
	$("#cerrar-modal").on("click", function() {
		$("#modal").modal("toggle");
	})

	$("#editar-oferta").on("click", function() {
		fetch('/oferta/oferta' + id, {
			headers: { "Content-Type": "application/json; charset=utf-8" }
		})
			.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
			.then(oferta => {
				let modal = document.getElementsByClassName("modal-body")[0];
				modal.replaceChildren();
				let guardar = document.createElement('INPUT');
				guardar.setAttribute("type", "button");
				guardar.setAttribute("id", "guardar");
				guardar.setAttribute("class", "btn btn-primary");
				guardar.setAttribute("value", "guardar");


				let pNombre = document.createElement('p');
				pNombre.textContent = "Nombre: " + oferta.nombre;
				var nombreTexto = document.createElement("INPUT");
				nombreTexto.setAttribute("type", "text");
				nombreTexto.setAttribute("id", "idNombre");
				pNombre.appendChild(nombreTexto);
				modal.appendChild(pNombre);


				let pPrio = document.createElement('p');
				pPrio.textContent = "Prioridad: " + oferta.prioridad;
				var prioTexto = document.createElement("INPUT");
				prioTexto.setAttribute("type", "text");
				prioTexto.setAttribute("id", "idPrio");
				pPrio.appendChild(prioTexto);
				modal.appendChild(pPrio);

				modal.appendChild(guardar);

				guardar.addEventListener("click", function() {
					fetch('/oferta/oferta' + id, {
						headers: {
							'Content-type': 'application/json'
						},
						method: 'POST',
						body: JSON.stringify({ id: oferta.id, nombre: $('#idNombre').val(), prioridad: $('#idPrio').val() })
					})
						.then(function(response) {
							if (response.ok) {
								return response.json()
							} else {
								throw "La oferta ya existe";
							}

						}).then(oferta => {
							var t = document.getElementById("tabla");
							var trs = t.getElementsByTagName("tr");
							var tds = null;
							for (let i = 0; i < trs.length; i++) {
								tds = trs[i].getElementsByTagName("td");
								for (var n = 0; n < tds.length; n++) {
									if(id == tds[n]){
										tds[n]
									}
									tds[n].lick = foncunction() { alert(oferta); }
								}
							}
						});
				});
			})
	})


	fetch('/oferta/oferta' + id, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
		.then(oferta => {
			let modal = document.getElementsByClassName("modal-body")[0];
			modal.replaceChildren();
			let p1 = document.createElement('p');
			p1.textContent = "ID: " + oferta.id;
			let p2 = document.createElement('p');
			p2.textContent = "Nombre: " + oferta.nombre;
			let p3 = document.createElement('p');
			p3.textContent = "Prioridad: " + oferta.prioridad;
			let p4 = document.createElement('p');
			p4.textContent = "Precio: " + oferta.precio;

			modal.appendChild(p1);
			modal.appendChild(p2);
			modal.appendChild(p3);
			modal.appendChild(p4);
		})
}
);

//Editar Ofertas
function editarOferta(id) {

}

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
					addOfertaTabla(oferta);
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
			}).then(oferta => {
				addOfertaTabla(oferta);
			})
	}
}

