package des.davidchiscano.ofertasapp.modelo;

import java.util.List;
import java.util.Optional;

import des.davidchiscano.ofertasapp.entidades.Oferta;

public interface OfertaDao extends DaoGenerico<Oferta>{
	List<Oferta> getOfertas();
	//Oferta crearOferta(Oferta o);
	//long borrarOferta(long id);
	Optional<Oferta>buscarId(long id); 
	List<Oferta> buscarOferta(String busqueda);
	List<Oferta> filtrarOferta(String prioridad);
}
