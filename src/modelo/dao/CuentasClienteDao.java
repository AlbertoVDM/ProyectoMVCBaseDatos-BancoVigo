package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CuentasClienteDao {

	public void registrarCuentasCliente(CuentasCliente miCuentaCliente) {
		Conexion conDB = new Conexion();

		String query = "INSERT INTO cuentasCliente (ccDNI, ccCodCuenta) VALUES (?, ?)";

		try (Connection conn = conDB.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, miCuentaCliente.getCcDNI());
			pstmt.setInt(2, miCuentaCliente.getCcCodCuenta());

			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registró");
		} finally {
			conDB.desconectar();
		}
	}
	public void actualizarCuentasCliente(CuentasCliente miCuentaCliente) {
		Conexion conDB = new Conexion();

		try {
			Statement estatuto = conDB.getConnection().createStatement();
			estatuto.executeUpdate("UPDATE cuentascliente SET ccCodCuenta = " + miCuentaCliente.getCcCodCuenta() +
                    " WHERE ccDNI = '" + miCuentaCliente.getCcDNI() + "'");
			JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conDB.desconectar();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se pudo actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static DefaultTableModel obtenerCuentasPorCliente(String dniCliente) {
	    Conexion conDB = new Conexion();
	    
	    String sql = "SELECT c.* FROM cuenta c JOIN cuentascliente cc ON c.cuCodCuenta = cc.ccCodCuenta WHERE cc.ccDNI= ?";
	    
	    DefaultTableModel modeloTabla = new DefaultTableModel();

	    // Agregar las columnas al modelo de la tabla
	    modeloTabla.addColumn("Cod Cuentas");
	    modeloTabla.addColumn("Cod Sucursal");
	    modeloTabla.addColumn("Fecha");
	    modeloTabla.addColumn("Saldo");

	    try (Connection connection = conDB.getConnection();
	            PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	        statement.setString(1, dniCliente);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Integer cuCodCuenta = resultSet.getInt("cuCodCuenta");
	                Integer cuCodSucursal = resultSet.getInt("cuCodSucursal");
	                Date cuFechaCreacion = resultSet.getDate("cuFechaCreacion");
	                Integer cuSaldo = resultSet.getInt("cuSaldo");

	                
	                modeloTabla.addRow(new Object[]{cuCodCuenta, cuCodSucursal, cuFechaCreacion, cuSaldo});
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        JOptionPane.showMessageDialog(null, "Error al obtener cuentas");
	    } finally {
	        conDB.desconectar();
	    }
	    
	    return modeloTabla;
	}
	
	
	public static List<Integer> obtenerCuentasClientePorDni(String dni) {
	    Conexion conDB = new Conexion();
	    List<Integer> cuentasCliente = new ArrayList<>();

	    String query = "SELECT ccCodCuenta FROM cuentascliente WHERE ccDNI = ?";

	    try (Connection conn = conDB.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, dni);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int ccCodCuenta = rs.getInt("ccCodCuenta");
	            cuentasCliente.add(ccCodCuenta);
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        JOptionPane.showMessageDialog(null, "Error al obtener cuentas cliente por DNI");
	    } finally {
	        conDB.desconectar();
	    }

	    return cuentasCliente;
	}
	
}
