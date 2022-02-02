package des.davidchiscano.ofertasapp.controladores;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import des.davidchiscano.ofertasapp.entidades.Oferta;
import des.davidchiscano.ofertasapp.modelo.OfertaDao;

@Controller
public class IndexController {
	@Autowired
	private OfertaDao ofertaModelo;
	
//	@GetMapping("/index")
//	public String getIndex(Model modelo) {
//		List<Oferta>ListaOfertas = ofertaModelo.getOfertas();
//		modelo.addAttribute("ListaOfertas",ListaOfertas);
//		return "index";
//	}
//	@GetMapping("/")
//	public String getIndex2(Model modelo) {
//		List<Oferta>ListaOfertas = ofertaModelo.getOfertas();
//		modelo.addAttribute("ListaOfertas",ListaOfertas);
//		return "index";
//	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/ofertas")
	public List<Oferta> obtenerOfertas(){
		return ofertaModelo.getOfertas();
	}
	@ResponseBody
	@GetMapping("/oferta/oferta{id}")
	public Optional<Oferta> getPerfil(Model modelo, @PathVariable long id) {
		return ofertaModelo.buscarId(id);
	}	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("usuario");
		session.invalidate();
		return "redirect:/index";
	}
	
	@PostMapping("/crearUsuario")
	public String crearUsuario(@RequestParam String usuario, HttpSession session) {
		session.setAttribute("usuario", usuario);
		return "redirect:/index";
	}
}
