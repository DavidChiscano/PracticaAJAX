package des.davidchiscano.ofertasapp.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import des.davidchiscano.ofertasapp.entidades.Oferta;
import des.davidchiscano.ofertasapp.modelo.OfertaDao;
@Service
@Transactional
public class OfertaServiceImpl implements OfertaServicio {
	@Autowired
	OfertaDao ofertaDao;

	@Override
	public Oferta crearOferta(Oferta o) {
		Oferta oferta = ofertaDao.crear(o);
		return oferta;
	}

	@Override
	public void borrarOferta(long id) {
		ofertaDao.borrar(id);
	}

}
