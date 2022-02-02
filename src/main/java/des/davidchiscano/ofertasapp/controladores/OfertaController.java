package des.davidchiscano.ofertasapp.controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import des.davidchiscano.ofertasapp.entidades.Oferta;
import des.davidchiscano.ofertasapp.modelo.OfertaDao;

@Controller
public class OfertaController {
	@Autowired
	private OfertaDao ofertaModelo;
	
	
	@GetMapping("/crearOferta")
	public String getCrearOferta() {
		return "/index";
	}

	@PostMapping("/crearOferta")
	public String postCrearOferta(@RequestParam String nombre,@RequestParam String prioridad,@RequestParam String hiperenlace,@RequestParam String descripcion,@RequestParam double precio) {
		Oferta oferta = new Oferta();
		LocalDate fecha = LocalDate.now();
		oferta.setNombre(nombre);
		oferta.setFecha(fecha.toString());
		oferta.setPrioridad(prioridad);
		oferta.setHiperenlace(hiperenlace);
		oferta.setDescripcion(descripcion);
		oferta.setPrecio(precio);
		ofertaModelo.crearOferta(oferta);
		return "redirect:/";
	}
	
	@GetMapping("/buscarOferta")
	public String getBuscarProducto(Model modelo, @RequestParam String busqueda) {
		List<Oferta> ListaOfertas = ofertaModelo.buscarOferta(busqueda);
		modelo.addAttribute("ListaBusqueda", (ListaOfertas));
		return "index";
	}
	
	@GetMapping("/borrar/{id}")
	public String getBorrarIdProducto(@PathVariable long id) {
		ofertaModelo.borrarOferta(id);
		return "redirect:/";
	}
	
	@GetMapping("/filtrarPrio")
	public String filtrarPrio(Model modelo, @RequestParam String optionsRadios) {
// 		List<Oferta> ListaOfertas = ofertaModelo.filtrarOferta(optionsRadios);
//     	modelo.addAttribute("ListaBusqueda", (ListaOfertas));
		ofertaModelo.filtrarOferta(optionsRadios);
		return "index";
	}
	
}
