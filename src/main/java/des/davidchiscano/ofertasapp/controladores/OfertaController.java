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
import org.springframework.web.bind.annotation.RequestParam;
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


	@PostMapping("/crearOferta")
	public String postCrearOferta(@RequestParam Map<String, String> json) {
		Oferta oferta = ofertaServicio.crearOferta(new Oferta(json.get("nombre"), json.get("prioridad"),
		json.get("hiperenlace"), json.get("descripcion"), Double.parseDouble(json.get("precio"))));
		ResponseEntity<Object> ofert = new ResponseEntity<Object>(oferta, HttpStatus.OK);
		return "redirect:/";
	}

	@GetMapping("/buscarOferta")
	public String getBuscarProducto(Model modelo, @RequestParam String busqueda) {
		List<Oferta> ListaOfertas = ofertaDao.buscarOferta(busqueda);
		modelo.addAttribute("ListaBusqueda", (ListaOfertas));
		return "index";
	}

	@GetMapping("/borrar/{id}")
	public String getBorrarIdProducto(@PathVariable long id) {
		ofertaDao.borrar(id);
		return "redirect:/";
	}

	@GetMapping("/filtrarPrio")
	public String filtrarPrio(@RequestParam String optionsRadios) {
// 		List<Oferta> ListaOfertas = ofertaDao.filtrarOferta(optionsRadios);
//     	modelo.addAttribute("ListaBusqueda", (ListaOfertas));
		ofertaDao.filtrarOferta(optionsRadios);
		return "index";
	}


}
