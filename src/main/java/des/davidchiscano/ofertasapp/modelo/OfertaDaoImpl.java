package des.davidchiscano.ofertasapp.modelo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import des.davidchiscano.ofertasapp.entidades.Oferta;
@Repository
public class OfertaDaoImpl extends DaoGenericoImpl<Oferta> implements OfertaDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Oferta> getOfertas() {
		return jdbcTemplate.query("select * from oferta",
				(rs, rowNum) -> new Oferta(rs.getLong("id"), rs.getString("nombre"), rs.getString("fecha"),rs.getString("prioridad"),rs.getString("hiperenlace"),rs.getString("descripcion"), rs.getDouble("precio")));
	}


	@SuppressWarnings("deprecation")
	@Override
	public Optional<Oferta> buscarId(long id) {
		return jdbcTemplate.queryForObject("select * from oferta where id = ?", new Object[] { id }, (rs,
				rowNum) -> Optional.of(new Oferta(rs.getLong("id"), rs.getString("nombre"), rs.getString("fecha"),rs.getString("prioridad"),rs.getString("hiperenlace"),rs.getString("descripcion"), rs.getDouble("precio"))));
	}

	@Override
	public List<Oferta> buscarOferta(String busqueda) {
		return jdbcTemplate.query("select * from oferta where nombre like ?", (rs,
				rowNum) -> new Oferta(rs.getLong("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("prioridad")), "%"+busqueda+"%");
	}

	@Override
	public List<Oferta> filtrarOferta(String prioridad) {
		return jdbcTemplate.query("select * from oferta where prioridad like ?", (rs,
				rowNum) -> new Oferta(rs.getLong("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("prioridad")), "%"+prioridad+"%");
	}

	@Override
	public long contarTodos(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}




}
