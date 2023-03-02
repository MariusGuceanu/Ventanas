package vista;

import java.awt.Menu;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorBBDD extends Conector {

	public void consultarReservas(java.util.Date date1, java.util.Date date2) {
		try {

			PreparedStatement select = conector.prepareStatement("SELECT * FROM reservas WHERE desde > '"
					+ (java.sql.Date) date1 + "' AND hasta < '" + (java.sql.Date) date2 + "'");

			ResultSet resultado = select.executeQuery();
			System.out.println(" ----- Reservas ----- ");
			while (resultado.next()) {
				System.out.println(resultado.getInt(1) + " : " + resultado.getInt(2) + " : " + resultado.getInt(3)
						+ " : " + resultado.getDate(4) + " : " + resultado.getDate(5));
			}
		} catch (SQLException e) {
			System.out.println("Error: no se puede consultar \n" + e + "\n");
		}
	}

	public void crearReserva(Scanner scan) {
		try {
			PreparedStatement select = conector.prepareStatement("SELECT * FROM clientes WHERE dni = ?");
			System.out.println("Introduzca DNI del cliente");
			String dni = scan.nextLine();
			select.setString(1, dni);
			boolean dniExiste = false;

			ResultSet resultado = select.executeQuery();
			while (resultado.next()) {
				if (resultado.getString(1).equals(dni)) {
					dniExiste = true;
				}
			}

			if (dniExiste) {
				System.out.println("Introduzca el hotel en el que se va a alojar");
				String hotel = scan.nextLine();
				select = conector.prepareStatement(
						"SELECT * FROM habitaciones WHERE id_hotel = (SELECT id FROM hoteles WHERE nombre = ?)");
				select.setString(1, hotel);
				resultado = select.executeQuery();
				System.out.println("-----Habitaciones-----\n");
				
				while (resultado.next()) {
					System.out.println("Nº " + resultado.getInt(3) + " : " + resultado.getString(4) + " : "
							+ resultado.getDouble(5) + "€" + " : ID " + resultado.getInt(1));
				}

				PreparedStatement crearReserva = conector
						.prepareStatement("INSERT INTO reservas (id_habitacion,dni,desde,hasta) VALUES (?,?,?,?)");
			    Reserva reserva = new Reserva();

				crearReserva.setInt(1, reserva.getId_habitacion());
				crearReserva.setString(2, dni);
				crearReserva.setDate(3, (Date) reserva.getDesde());
				crearReserva.setDate(4, (Date) reserva.getHasta());
				crearReserva.execute();

			} else {
				System.out.println("Error: no existe el dni " + dni);
			}

		} catch (SQLException e) {
			System.out.println("Error: no se ha podido crear la reserva ");
		}

	}

	public void cancelarReserva(Scanner scan) {

		try {

			System.out.println("Introduzca DNI del cliente");
			String dni = scan.nextLine();
			PreparedStatement delete = conector.prepareStatement("SELECT * FROM reservas WHERE dni =" + dni + "");

			ResultSet resultado = delete.executeQuery();
			System.out.println(" ----- RESERVAS ----- ");
			
			while (resultado.next()) {
				System.out.println(resultado.getInt(1) + " : " + resultado.getInt(2) + " : " + resultado.getInt(3)
						+ " : " + resultado.getDate(4) + " : " + resultado.getDate(5));
			}

			delete = conector.prepareStatement("DELETE FROM reservas WHERE id = ?");

			System.out.println("Introduzca ID de la reserva que quiere cancelar");
			delete.setInt(1, Integer.parseInt(scan.nextLine()));
			delete.execute();

		} catch (SQLException e) {
			System.out.println("ERROR: No se ha podido cancelar la reserva:\n" + e);
		}
	}

	public void comprobarReservasCliente(Scanner scan) {
		try {
			System.out.println("Introduzca DNI del cliente");
			String dni = scan.nextLine();
			PreparedStatement delete;
			delete = conector.prepareStatement("SELECT * FROM reservas WHERE dni =" + dni + "");
			ResultSet resultado = delete.executeQuery();
			System.out.println(" ----- RESERVAS ----- ");
			while (resultado.next()) {
				System.out.println(resultado.getInt(1) + " : " + resultado.getInt(2) + " : " + resultado.getString(3)
						+ " : " + resultado.getDate(4) + " : " + resultado.getDate(5));
			}
		} catch (SQLException e) {

		}
	}

	public void crearCliente(Cliente cliente) {
		try {
			PreparedStatement crearCliente = conector.prepareStatement(
					"INSERT INTO clientes (dni,nombre,apellidos,direccion,localidad) VALUES (?,?,?,?,?)");
			crearCliente.setString(1, cliente.getDni());
			crearCliente.setString(2, cliente.getNombre());
			crearCliente.setString(3, cliente.getApellidos());
			crearCliente.setString(4, cliente.getDireccion());
			crearCliente.setString(5, cliente.getLocalidad());
			crearCliente.execute();
		} catch (SQLException e) {
			System.out.println("Error: no se ha podido registrar el cliente");
		}
	}

	public void crearHotel(Hotel hotel, Scanner scan) {
		int opcion_habitacion;
		GestorBBDD gestorBBDD = new GestorBBDD();
		PreparedStatement crearHotel;
		try {
			crearHotel = conector.prepareStatement(
					"INSERT INTO hoteles (cif,nombre,gerente,estrellas, compania) VALUES (?,?,?,?,?)");

			crearHotel.setString(1, hotel.getCif());
			crearHotel.setString(2, hotel.getNombre());
			crearHotel.setString(3, hotel.getGerente());
			crearHotel.setInt(4, hotel.getEstrellas());
			crearHotel.setString(5, hotel.getCompania());

			crearHotel.execute();
			System.out.println("Hotel creado");

			
		} catch (SQLException e) {
			System.out.println("Error al crear hotel " + e);
		}

	}

	public void crearHabitacion(Habitacion habitacion, Scanner scan) {
		PreparedStatement crearHabitacion;
		try {
			crearHabitacion = conector.prepareStatement(
					"INSERT INTO habitaciones (id,id_hotel,numero,descripcion,precio) VALUES (?,?,?,?,?)");
			crearHabitacion.setInt(1, habitacion.getId());
			crearHabitacion.setInt(2, habitacion.getId_hotel());
			crearHabitacion.setString(3, habitacion.getNumero());
			crearHabitacion.setString(4, habitacion.getDescripcion());
			crearHabitacion.setDouble(5, habitacion.getPrecio());

			crearHabitacion.execute();
		} catch (SQLException e) {
			System.out.println("Error al crear la habitacion " + e);
		}
	}
	
	public void eliminarHotel(Hotel hotel) {
		PreparedStatement eliminarHotel;
		try {
			eliminarHotel = conector.prepareStatement("DELETE FROM hoteles WHERE cif = ?");
			eliminarHotel.setString(1, hotel.getCif());

			eliminarHotel.execute();
		} catch (SQLException e) {
			System.out.println("Error al eliminar el hotel" + e);
		}
	}

	public void eliminarCliente(Cliente cliente) {
		PreparedStatement eliminarCliente;
		try {
			eliminarCliente = conector.prepareStatement("DELETE FROM clientes WHERE dni = ?");
			eliminarCliente.setString(1, cliente.getDni());

			eliminarCliente.execute();
		} catch (SQLException e) {
			System.out.println("Error al eliminar el cliente" + e);
		}
	}

	public void actualizarCliente(Cliente cliente) {
		PreparedStatement actualizarCliente;
		try {
			actualizarCliente = conector
					.prepareStatement("UPDATE clientes SET nombre=?, apellidos=?, direccion=?, localidad=? WHEREdni=?");
			actualizarCliente.setString(1, cliente.getNombre());
			actualizarCliente.setString(2, cliente.getApellidos());
			actualizarCliente.setString(3, cliente.getDireccion());
			actualizarCliente.setString(4, cliente.getLocalidad());
			actualizarCliente.setString(5, cliente.getDni());

			actualizarCliente.execute();
		} catch (SQLException e) {
			System.out.println("Error al actualizar los datos del cliente" + e);
		}
	}

	public void contiene(ArrayList<Cliente> clientes, String cadena) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNombre().contains(cadena) || clientes.get(i).getApellidos().contains(cadena)) {
				System.out.println(clientes.get(i));
			}
		}

	}

}