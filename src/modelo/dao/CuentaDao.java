package modelo.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.JOptionPane;
import java.sql.*;

import modelo.*;
import controlador.*;

	public class CuentaDao{

		public void registrarCuenta(Cuenta miCuenta) {
			Conexion conDB = new Conexion();

			String query = "INSERT INTO cuenta (cuCodSucursal, cuFechaCreacion, cuSaldo) VALUES (?, ?, ?)";

			try (Connection conn = conDB.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(query)) {

				pstmt.setInt(1, miCuenta.getCuCodSucursal());
				pstmt.setDate(2, miCuenta.getCuFechaCreacion());
				pstmt.setInt(3, miCuenta.getCuSaldo());

				pstmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "No se Registró");
			} finally {
				conDB.desconectar();
			}
		}
		public void actualizarCliente(Cuenta miCuenta) {
			Conexion conDB = new Conexion();

			try {
				Statement estatuto = conDB.getConnection().createStatement();
				estatuto.executeUpdate("UPDATE cliente SET cuCodSucursal = '" + miCuenta.getCuCodSucursal() +
						"', cuFechaCreacion = '" + miCuenta.getCuFechaCreacion() +
						"', cuSaldo = '" + miCuenta.getCuSaldo() + "'");
				JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
				estatuto.close();
				conDB.desconectar();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "No se pudo actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		public DefaultTableModel obtenerCuentas() throws SQLException {
			Conexion conDB = new Conexion();
			DefaultTableModel modeloTabla = new DefaultTableModel();

			// Agregar las columnas al modelo de la tabla
			modeloTabla.addColumn("Cod Cuentas");
			modeloTabla.addColumn("Cod Sucursal");
			modeloTabla.addColumn("Fecha");
			modeloTabla.addColumn("Saldo");

			try (Connection connection = conDB.getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM cuenta")) {

				while (resultSet.next()) {
					Integer cuCodCuenta = resultSet.getInt("cuCodCuenta");
		            Integer cuCodSucursal = resultSet.getInt("cuCodSucursal");
		            Date cuFechaCreacion = resultSet.getDate("cuFechaCreacion");
		            Integer cuSaldo = resultSet.getInt("cuSaldo");

					// Agregar una fila al modelo de la tabla
					modeloTabla.addRow(new Object[]{cuCodCuenta, cuCodSucursal, cuFechaCreacion, cuSaldo});
				}
			}

			return modeloTabla;
		}

		public List<Cuenta> obtenerListaCuentas() {
			 Conexion conDB = new Conexion();
			    List<Cuenta> cuentas = new ArrayList<>();

			    String query = "SELECT * FROM cuenta";

			    try (Connection conn = conDB.getConnection();
			            PreparedStatement pstmt = conn.prepareStatement(query);
			            ResultSet rs = pstmt.executeQuery()) {

			        while (rs.next()) {
			            Integer cuCodCuenta = rs.getInt("cuCodCuenta");
			            Integer cuCodSucursal = rs.getInt("cuCodSucursal");
			            java.sql.Date cuFechaCreacion = rs.getDate("cuFechaCreacion");
			            Integer cuSaldo = rs.getInt("cuSaldo");

			            Cuenta cuenta = new Cuenta(cuCodCuenta, cuCodSucursal, cuFechaCreacion, cuSaldo);
			            cuentas.add(cuenta);
			        }
			    } catch (SQLException e) {
			        System.out.println(e.getMessage());
			        JOptionPane.showMessageDialog(null, "Error al obtener cuentas");
			    } finally {
			        conDB.desconectar();
			    }

			    return cuentas;
			}
		public int obtenerUltimoID() {
			Conexion conDB = new Conexion();
			int numeroCuenta = -1; 

	        
	        String sql = "SELECT MAX(cuCodCuenta) AS last_id FROM cuenta";
	        
	        try (Connection conn = conDB.getConnection();
	        		PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery()){
	            if (rs.next()) {
	                numeroCuenta = rs.getInt("last_id");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al obtener el id cuenta");
	            
	        }
	        
	        return numeroCuenta;
			
		}
		public Cuenta obtenerCuentasPorDni(int codigoCuenta) {
		    Conexion conDB = new Conexion();
		    Cuenta cuenta = null;

		    String query = "SELECT * FROM cuenta WHERE cuCodCuenta = ?";

		    try (Connection conn = conDB.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setInt(1, codigoCuenta);
		        ResultSet rs = pstmt.executeQuery();

		        if (rs.next()) {
		            int cuCodCuenta = rs.getInt("cuCodCuenta");
		            int cuCodSucursal = rs.getInt("cuCodSucursal");
		            Date cuFechaCreacion = rs.getDate("cuFechaCreacion");
		            int cuSaldo = rs.getInt("cuSaldo");

		            cuenta = new Cuenta(cuCodCuenta, cuCodSucursal, cuFechaCreacion, cuSaldo);
		        }
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		        JOptionPane.showMessageDialog(null, "Error al obtener cuenta por código");
		    } finally {
		        conDB.desconectar();
		    }

		    return cuenta;
		}
		public void sumarSaldoCuenta(int cuCodCuenta, int cantidad) {
		    Conexion conDB = new Conexion();

		    String query = "UPDATE cuenta SET cuSaldo = cuSaldo + ? WHERE cuCodCuenta = ?";

		    try (Connection conn = conDB.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setInt(1, cantidad);
		        pstmt.setInt(2, cuCodCuenta);

		        pstmt.executeUpdate();

		        JOptionPane.showMessageDialog(null, "Se ha sumado exitosamente " + cantidad + " al saldo de la cuenta", "Información", JOptionPane.INFORMATION_MESSAGE);
		        Transacciones transaccion= new Transacciones(); 
			    TransaccionesDao movimiento= new TransaccionesDao(); 
		        Date fechaActual = new Date();
		        java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());
		        char tipo = 'I';
		        transaccion.setTrCodCuenta(cuCodCuenta);
		        transaccion.setTrFechaTransaccion(fecha);
		        transaccion.setTrTipo(tipo);
		        transaccion.setTrCantidad(cantidad);
		        movimiento.registrarTransaccion(transaccion);
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		        JOptionPane.showMessageDialog(null, "No se pudo sumar saldo a la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
		    } finally {
		        conDB.desconectar();
		    }
		}
		public void restarSaldoCuenta(int cuCodCuenta, int cantidad) {
		    Conexion conDB = new Conexion();
		    
			

		    // Obtener el saldo actual de la cuenta
		    int saldoActual = obtenerSaldoCuenta(cuCodCuenta);

		    // Verificar si el importe a restar es mayor que el saldo disponible
		    if (cantidad > saldoActual) {
		        JOptionPane.showMessageDialog(null, "No hay saldo suficiente en la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
		        return; // Salir del método si no hay saldo suficiente
		    }

		    // Realizar la resta del saldo
		    String query = "UPDATE cuenta SET cuSaldo = cuSaldo - ? WHERE cuCodCuenta = ?";

		    try (Connection conn = conDB.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setInt(1, cantidad);
		        pstmt.setInt(2, cuCodCuenta);

		        pstmt.executeUpdate();

		        JOptionPane.showMessageDialog(null, "Se ha restado exitosamente " + cantidad + " al saldo de la cuenta", "Información", JOptionPane.INFORMATION_MESSAGE);
		        Transacciones transaccion= new Transacciones(); 
			    TransaccionesDao movimiento= new TransaccionesDao(); 
		        Date fechaActual = new Date();
		        java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());
		        char tipo = 'R';
		        transaccion.setTrCodCuenta(cuCodCuenta);
		        transaccion.setTrFechaTransaccion(fecha);
		        transaccion.setTrTipo(tipo);
		        transaccion.setTrCantidad(cantidad);
		        movimiento.registrarTransaccion(transaccion);
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		        JOptionPane.showMessageDialog(null, "No se pudo restar saldo a la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
		    } finally {
		        conDB.desconectar();
		    }
		}

		// Método auxiliar para obtener el saldo actual de una cuenta
		public int obtenerSaldoCuenta(int cuCodCuenta) {
		    Conexion conDB = new Conexion();
		    int saldo = 0;

		    String query = "SELECT cuSaldo FROM cuenta WHERE cuCodCuenta = ?";

		    try (Connection conn = conDB.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setInt(1, cuCodCuenta);
		        ResultSet rs = pstmt.executeQuery();

		        if (rs.next()) {
		            saldo = rs.getInt("cuSaldo");
		        }
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		        JOptionPane.showMessageDialog(null, "Error al obtener saldo de la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
		    } finally {
		        conDB.desconectar();
		    }

		    return saldo;
		}
		
		
		public void transferirSaldo(int idCuentaOrigen, int idcuentaDestino, int cantidad) {
			// Restar el saldo de la cuenta de origen
		    restarSaldoCuenta(idCuentaOrigen, cantidad);

		    // Sumar el saldo a la cuenta de destino
		    sumarSaldoCuenta(idcuentaDestino, cantidad);
		   
		    JOptionPane.showMessageDialog(null, "La transferencia se realizó exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		

	}

