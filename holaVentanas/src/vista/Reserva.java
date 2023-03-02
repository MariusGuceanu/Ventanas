package vista;

import java.sql.Date;

public class Reserva {

	private int id;
	private int id_habitacion;
	private String dni;
	private Date desde;
	private Date hasta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_habitacion() {
		return id_habitacion;
	}

	public void setId_habitacion(int id_habitacion) {
		this.id_habitacion = id_habitacion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(java.sql.Date date) {
		this.desde = (Date) date;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(java.sql.Date hasta) {
		this.hasta = (Date) hasta;
	}

	public Reserva(int id, int id_habitacion, String dni, Date desde, Date hasta) {
		super();
		this.id = id;
		this.id_habitacion = id_habitacion;
		this.dni = dni;
		this.desde = desde;
		this.hasta = hasta;
	}

	public Reserva() {

	}

	public String toString() {
		return "Reserva [id=" + id + ", id_habitacion=" + id_habitacion + ", dni=" + dni + ", desde=" + desde
				+ ", hasta=" + hasta + "]";
	}

}
