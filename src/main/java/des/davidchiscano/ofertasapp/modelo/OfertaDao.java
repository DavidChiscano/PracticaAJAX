package des.davidchiscano.ofertasapp.modelo;

import java.util.List;
import java.util.Optional;

import des.davidchiscano.ofertasapp.entidades.Oferta;

public interface OfertaDao {
	List<Oferta> getOfertas();
	int crearOferta(Oferta o);
	long borrarOferta(long id);
	Optional<Oferta>buscarId(long id); 
	List<Oferta> buscarOferta(String busqueda);
	List<Oferta> filtrarOferta(String prioridad);
}
