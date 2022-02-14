package des.davidchiscano.ofertasapp.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import des.davidchiscano.ofertasapp.entidades.Oferta;
import des.davidchiscano.ofertasapp.modelo.OfertaDao;
import des.davidchiscano.ofertasapp.servicios.OfertaServicio;

@Controller
public class OfertaController {
	@Autowired
	OfertaServicio ofertaServicio;
	@Autowired
	OfertaDao ofertaDao;

	@ResponseBody
	@PostMapping("/crearOferta")
	public ResponseEntity postCrearOferta(@RequestBody Map<String, String> json) {
		Oferta oferta = ofertaServicio.crearOferta(new Oferta(json.get("nombre"), json.get("prioridad"),
		json.get("hiperenlace"), json.get("descripcion"), Double.parseDouble(json.get("precio"))));
		ResponseEntity<Object> ofert = new ResponseEntity<Object>(oferta, HttpStatus.OK);
		return ofert;
	}
	
	@ResponseBody
	@PostMapping("/oferta/oferta{id}")
	public ResponseEntity<Object> postEditarOferta(@RequestBody Map<String, String> json,@PathVariable long id ) {
		Oferta oferta = ofertaServicio.editarOferta(new Oferta(json.get("nombre"), json.get("prioridad")), id);
		ResponseEntity<Object> ofert = new ResponseEntity<Object>(oferta, HttpStatus.OK);
		return ofert;
	}

	@GetMapping("/buscarOferta{id}")
	public Oferta getBuscarProducto(Model modelo, @PathVariable long id) {
		return ofertaDao.buscar(id);
	}

	@GetMapping("/borrar/{id}")
	public String getBorrarIdProducto(@PathVariable long id) {
		ofertaDao.borrar(id);
		return "redirect:/";
	}
	@ResponseBody
	@PostMapping("/filtrar")
	public List<Oferta> filtrarPrio(@RequestBody Map<String, String> json) {
		
		return ofertaDao.filtrarOferta(json.get("prioridad"));
	}


}
