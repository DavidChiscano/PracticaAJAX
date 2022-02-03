package des.davidchiscano.ofertasapp.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "oferta")
public class Oferta implements Serializable {
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "fecha")
	private String fecha;
	@Column(name = "prioridad")
	private String prioridad;
	@Column(name = "hiperenlace")
	private String hiperenlace;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "precio")
	private double precio;
	
	//CONSTRUCTORES
	public Oferta(long id, String nombre, String fecha, String prioridad, String hiperenlace, String descripcion,
			double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.prioridad = prioridad;
		this.hiperenlace = hiperenlace;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public Oferta() {
		super();
	}

	public Oferta(long id, String nombre, double precio, String prioridad) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.prioridad = prioridad;
	}
	public Oferta(String nombre, String prioridad, String hiperenlace, String descripcion, double precio) {
		this.nombre = nombre;
		this.prioridad = prioridad;
		this.hiperenlace = hiperenlace;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	//GETTERS && SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getHiperenlace() {
		return hiperenlace;
	}
	public void setHiperenlace(String hiperenlace) {
		this.hiperenlace = hiperenlace;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Oferta [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", prioridad=" + prioridad
				+ ", hiperenlace=" + hiperenlace + ", descripcion=" + descripcion + ", precio=" + precio + "]";
	}
	
	
}
