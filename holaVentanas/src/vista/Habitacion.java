package vista;

public class Habitacion {

	private int id;
	private int id_hotel;
	private String numero;
	private String descripcion;
	private double precio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public Habitacion(int id, int id_hotel, String numero, String descripcion, double precio) {
		super();
		this.id = id;
		this.id_hotel = id_hotel;
		this.numero = numero;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Habitacion() {

	}

	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", id_hotel=" + id_hotel + ", numero=" + numero + ", descripcion=" + descripcion
				+ ", precio=" + precio + "]";
	}

}
