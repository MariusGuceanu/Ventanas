package vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conector {
	protected Connection conector;

	public void conectar() {
		try {
			String url = "jdbc:mysql://localhost/viajes";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conector = (Connection) DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			System.out.println("Error: No se ha podido encontrar la base de datos ");
		}
	}

	public void cerrar() {
		try {
			conector.close();
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido cerrar la conexion");
		}
	}

	public void descargarDatos(ArrayList<Cliente> clientes) {
		try {
			String sentenciaSelect = "SELECT * FROM clientes";
			Statement st = conector.createStatement();
			ResultSet resultado = st.executeQuery(sentenciaSelect);
			while (resultado.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(resultado.getString("dni"));
				cliente.setNombre((resultado.getString("nombre")));
				cliente.setApellidos((resultado.getString("apellidos")));
				cliente.setDireccion(resultado.getString("direccion"));
				cliente.setLocalidad(resultado.getString("localidad"));

				clientes.add(cliente);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: No se han podido descargar los datos:\n" + e);
		}
	}
}
