package modelo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import javax.swing.JOptionPane;


import modelo.*;
import controlador.*;



public class ClienteDao{

	public void registrarCliente(Cliente miCliente) {
		Conexion conDB = new Conexion();

		String query = "INSERT INTO cliente (clDni, clNombre, clApellido, clTelefono) VALUES (?, ?, ?, ?)";

		try (Connection conn = conDB.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, miCliente.getClDni());
			pstmt.setString(2, miCliente.getClNombre());
			pstmt.setString(3, miCliente.getClApellido());
			pstmt.setInt(4, miCliente.getClTelefono());

			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registró");
		} finally {
			conDB.desconectar();
		}
	}
	public void actualizarCliente(Cliente miCliente) {
		Conexion conDB = new Conexion();

		try {
			Statement estatuto = conDB.getConnection().createStatement();
			estatuto.executeUpdate("UPDATE cliente SET clNombre = '" + miCliente.getClNombre() +
					"', clApellido = '" + miCliente.getClApellido() +
					"', clTelefono = '" + miCliente.getClTelefono() +
					"' WHERE clDni = '" + miCliente.getClDni() + "'");
			JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conDB.desconectar();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se pudo actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public DefaultTableModel obtenerClientes() throws SQLException {
		Conexion conDB = new Conexion();
		DefaultTableModel modeloTabla = new DefaultTableModel();
		
		modeloTabla.addColumn("DNI");
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Apellido");
		modeloTabla.addColumn("Teléfono");

		try (Connection connection = conDB.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM cliente")) {

			while (resultSet.next()) {
				String dni = resultSet.getString("clDni");
				String nombre = resultSet.getString("clNombre");
				String apellido = resultSet.getString("clApellido");
				String telefono = resultSet.getString("clTelefono");

				
				modeloTabla.addRow(new Object[]{dni, nombre, apellido, telefono});
			}
		}

		return modeloTabla;
	}

	public List<Cliente> obtenerListaClientes() {
		Conexion conDB = new Conexion();
		List<Cliente> clientes = new ArrayList<>();

		String query = "SELECT * FROM cliente";

		try (Connection conn = conDB.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				String clDni = rs.getString("clDni");
				String clNombre = rs.getString("clNombre");
				String clApellido = rs.getString("clApellido");
				int clTelefono = rs.getInt("clTelefono");

				Cliente cliente = new Cliente(clDni, clNombre, clApellido, clTelefono);
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al obtener clientes");
		} finally {
			conDB.desconectar();
		}

		return clientes;
	} 


}

