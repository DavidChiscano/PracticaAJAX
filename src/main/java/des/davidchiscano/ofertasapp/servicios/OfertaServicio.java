package des.davidchiscano.ofertasapp.servicios;

import des.davidchiscano.ofertasapp.entidades.Oferta;

public interface OfertaServicio{
	public Oferta crearOferta(Oferta o);
	public void borrarOferta(long id);
}
