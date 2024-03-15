package modelo.dao;

                                                                      

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.*;

public class SucursalesDao {
	public void registrarSucursal(Sucursales miSucursal) {
	    Conexion conDB = new Conexion();
	    
	    String query = "INSERT INTO sucursales (suCodSucursal, suCiudad, suActivos) VALUES (?, ?, ?)";
	    
	    try (Connection conn = conDB.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {
	        
	        pstmt.setInt(1, miSucursal.getSuCodSucursal());
	        pstmt.setString(2, miSucursal.getSuCiudad());
	        pstmt.setBigDecimal(3, miSucursal.getSuActivo());
	        
	        pstmt.executeUpdate();
	        
	        JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        JOptionPane.showMessageDialog(null, "No se Registró");
	    } finally {
	        conDB.desconectar();
	    }
	}
	public void actualizarSucursal(Sucursales miSucursal) {
		Conexion conDB = new Conexion();
	    
	    try {
	        Statement estatuto = conDB.getConnection().createStatement();
	        estatuto.executeUpdate("UPDATE sucursales SET clNombre = '" + miSucursal.getSuCodSucursal() +
	                                "', clApellido = '" + miSucursal.getSuCiudad() +
	                                "', clTelefono = '" + miSucursal.getSuActivo() +
	                                "' WHERE clDni = '" + miSucursal.getSuCodSucursal() + "'");
	        JOptionPane.showMessageDialog(null, "Sucursal actualizada exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
	        estatuto.close();
	        conDB.desconectar();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        JOptionPane.showMessageDialog(null, "No se pudo actualizar la sucursal", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public DefaultTableModel obtenerSucursales() throws SQLException {
        Conexion conDB = new Conexion();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        
        // Agregar las columnas al modelo de la tabla
        modeloTabla.addColumn("Cod Sucursal");
        modeloTabla.addColumn("Ciudad");
        modeloTabla.addColumn("Activo");
        
        try (Connection connection = conDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM sucursales")) {
            
            while (resultSet.next()) {
                String suCodSucursal = resultSet.getString("suCodSucursal");
                String suCiudad = resultSet.getString("suCiudad");
                String suActivo = resultSet.getString("suActivo");

                // Agregar una fila al modelo de la tabla
                modeloTabla.addRow(new Object[]{suCodSucursal, suCiudad, suActivo});
            }
        }
        
        return modeloTabla;
    }
	
	public List<Sucursales> obtenerListaSucursales() {
	    Conexion conDB = new Conexion();
	    List<Sucursales> sucursales = new ArrayList<>();
	    
	    String query = "SELECT * FROM sucursales";
	    
	    try (Connection conn = conDB.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query);
	         ResultSet rs = pstmt.executeQuery()) {
	        
	        while (rs.next()) {
	            int suCodSucursal = rs.getInt("suCodSucursal");
	            String suCiudad = rs.getString("suCiudad");
	            BigDecimal suActivo = rs.getBigDecimal("suActivo");
	            
	            // Verificar si suActivo es nulo antes de crear un BigDecimal
	            if (rs.wasNull()) {
	                suActivo = BigDecimal.ZERO; // o cualquier otro valor por defecto
	            }
	            
	            // Crear un nuevo objeto Sucursales y asignar valores
	            Sucursales sucursal = new Sucursales(suCodSucursal, suCiudad, suActivo);
	            
	            // Agregar el objeto a la lista de sucursales
	            sucursales.add(sucursal);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Registrar la traza completa del error
	        JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL para obtener sucursales: " + e.getMessage());
	    } finally {
	        conDB.desconectar();
	    }
	    
	    return sucursales;
	}
	
	
}
