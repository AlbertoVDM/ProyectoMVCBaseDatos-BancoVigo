package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Conexion;
import modelo.Transacciones;

public class TransaccionesDao {
	public void registrarTransaccion(Transacciones miTransaccion) {
		
		Conexion conDB = new Conexion();
		long tipoNumerico = miTransaccion.getTrTipo();
		char tipoChar = (char) tipoNumerico;
        String query = "INSERT INTO transacciones (trCodCuenta, trFechaTransaccion, trTipo, trCantidad) VALUES (?, ?, ?, ?)";
       
        try (Connection conn = conDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, miTransaccion.getTrCodCuenta());
            pstmt.setDate(2, miTransaccion.getTrFechaTransaccion());
            pstmt.setString(3, String.valueOf(tipoChar));
            pstmt.setInt(4, miTransaccion.getTrCantidad());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Registró");
        } finally {
            conDB.desconectar();
        }
		
	}

    public void actualizarTransaccion(Transacciones miTransaccion) {
        Conexion conDB = new Conexion();
        try {
            Statement estatuto = conDB.getConnection().createStatement();
            estatuto.executeUpdate("UPDATE transacciones SET trCodCuenta = " + miTransaccion.getTrCodCuenta() +
                    ", trFechaTransaccion = '" + miTransaccion.getTrFechaTransaccion() +
                    "', trTipo = '" + miTransaccion.getTrTipo() +
                    "', trCantidad = " + miTransaccion.getTrCantidad() +
                    " WHERE id = " + miTransaccion.getId());
            JOptionPane.showMessageDialog(null, "Transacción actualizada exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            conDB.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la transacción", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel obtenerTransacciones() throws SQLException {
        Conexion conDB = new Conexion();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Código de Cuenta");
        modeloTabla.addColumn("Fecha de Transacción");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Cantidad");
        try (Connection connection = conDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM transacciones")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int trCodCuenta = resultSet.getInt("trCodCuenta");
                String trFechaTransaccion = resultSet.getString("trFechaTransaccion");
                String trTipo = resultSet.getString("trTipo");
                int trCantidad = resultSet.getInt("trCantidad");
                modeloTabla.addRow(new Object[]{id, trCodCuenta, trFechaTransaccion, trTipo, trCantidad});
            }
        }
        return modeloTabla;
    }

	
}


