package des.davidchiscano.ofertasapp.modelo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import des.davidchiscano.ofertasapp.entidades.Oferta;
@Repository
public class OfertaDaoImpl implements OfertaDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int crearOferta(Oferta o) {
		return jdbcTemplate.update("INSERT INTO Oferta(nombre, fecha, prioridad, hiperenlace, descripcion,precio) values(?,?,?,?,?,?)", 
				o.getNombre(), o.getFecha(), o.getPrioridad(), o.getHiperenlace(), o.getDescripcion(), o.getPrecio()); 
	}

	@Override
	public List<Oferta> getOfertas() {
		return jdbcTemplate.query("select * from Oferta",
				(rs, rowNum) -> new Oferta(rs.getLong("id"), rs.getString("nombre"), rs.getString("fecha"),rs.getString("prioridad"),rs.getString("hiperenlace"),rs.getString("descripcion"), rs.getDouble("precio")));
	}

	@Override
	public long borrarOferta(long id) {
		return jdbcTemplate.update("delete from Oferta where id = ?", id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<Oferta> buscarId(long id) {
		return jdbcTemplate.queryForObject("select * from Oferta where id = ?", new Object[] { id }, (rs,
				rowNum) -> Optional.of(new Oferta(rs.getLong("id"), rs.getString("nombre"), rs.getString("fecha"),rs.getString("prioridad"),rs.getString("hiperenlace"),rs.getString("descripcion"), rs.getDouble("precio"))));
	}

	@Override
	public List<Oferta> buscarOferta(String busqueda) {
		return jdbcTemplate.query("select * from Oferta where nombre like ?", (rs,
				rowNum) -> new Oferta(rs.getLong("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("prioridad")), "%"+busqueda+"%");
	}

	@Override
	public List<Oferta> filtrarOferta(String prioridad) {
		return jdbcTemplate.query("select * from Oferta where prioridad like ?", (rs,
				rowNum) -> new Oferta(rs.getLong("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("prioridad")), "%"+prioridad+"%");
	}


}
