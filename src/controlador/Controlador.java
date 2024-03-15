package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.*;
import modelo.dao.*;
import vista.*;

public class Controlador {
    private Logica miLogica;
    private VentanaPrincipal miVentanaPrincipal;
    private VentanaCliente miVentanaCliente;
    private VentanaCuentas miVentanaCuentas;
    private VentanaTransacciones miVentanaTransacciones;
    private ClienteDao miClienteDao;
    
    // Constructor
    public Controlador() {
        this.miLogica = new Logica();
        this.miClienteDao = new ClienteDao();
        obtenerFechaActual();
    }

    public Logica getMiLogica() {
        return miLogica;
    }

    public void setMiLogica(Logica miLogica) {
        this.miLogica = miLogica;
    }

    public VentanaPrincipal getMiVentanaPrincipal() {
        return miVentanaPrincipal;
    }

    public void setMiVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) {
        this.miVentanaPrincipal = miVentanaPrincipal;
    }

    public VentanaCliente getMiVentanaCliente() {
        return miVentanaCliente;
    }

    public void setMiVentanaCliente(VentanaCliente miVentanaCliente) {
        this.miVentanaCliente = miVentanaCliente;
    }

    public VentanaCuentas getMiVentanaCuentas() {
        return miVentanaCuentas;
    }

    public void setMiVentanaCuentas(VentanaCuentas miVentanaCuentas) {
        this.miVentanaCuentas = miVentanaCuentas;
    }

    public VentanaTransacciones getMiVentanaTransacciones() {
        return miVentanaTransacciones;
    }

    public void setMiVentanaTransacciones(VentanaTransacciones miVentanaTransacciones) {
        this.miVentanaTransacciones = miVentanaTransacciones;
    }

    public void mostrarVentanaPrincipal() {
        miVentanaPrincipal.setVisible(true);
    }

    public void mostrarVentanaCliente() {
        miVentanaCliente.setVisible(true);
    }

    public void mostrarVentanaCuentas() {
        miVentanaCuentas.setVisible(true);
    }

    public void mostrarVentanaTransacciones() {
        miVentanaTransacciones.setVisible(true);
    }

    public void setMiClienteDao(ClienteDao miClienteDao) {
        this.miClienteDao = miClienteDao;
    }

    public void registrarCliente(Cliente miCliente) {
    	miLogica.validarRegistroCliente(miCliente);
    }
    public void actualizarCliente(Cliente miCliente) {
    	miLogica.validarActualizacionCliente(miCliente);
    }
    public DefaultTableModel obtenerClientes() {
        try {
            return miClienteDao.obtenerClientes();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al obtener los datos de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            return new DefaultTableModel();
        }
    }
    /*------------------------- Obtener fecha actual ------------------------*/
    private int currentDay;
    private int currentMonth;
    private int currentYear;

    
    private void obtenerFechaActual() {
        Calendar cal = Calendar.getInstance();
        currentDay = cal.get(Calendar.DAY_OF_MONTH);
        currentMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH es zero-based, por eso sumamos 1
        currentYear = cal.get(Calendar.YEAR);
    }

    // Getters para currentDay, currentMonth y currentYear
    public int getCurrentDay() {
        return currentDay;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }
    /*------------------------------------------------------------------*/
	public void setMiClienteDao(Cliente miCliente) {
		// TODO Auto-generated method stub
		
	}
	public void setMiCliente(Cliente miCliente) {
		
	}

	public DefaultTableModel obtenerCuentas() throws SQLException {
	    CuentaDao cuentasDao = new CuentaDao(); // Crear una instancia de CuentaDao
	    return cuentasDao.obtenerCuentas(); // Llamar al método obtenerCuentas() de CuentasDao
	}


	public void insertarDatosCuenta(Cuenta cuenta) {
		miLogica.validarRegistroCuenta(cuenta);
	}

	public void insertarDatosCuentaClientes(CuentasCliente cuentasCliente) {
		miLogica.validarRegistroCuentaCliente(cuentasCliente);
		
	}

	public int obtenerNumeroCuentaUltimaInsercion() {
		int numCuenta=miLogica.ObtenerUltimoIDCuenta();
		return numCuenta;
	}

	public DefaultTableModel traerCuentasCliente(String dniCliente) {
		CuentasClienteDao cuentasCliente = new CuentasClienteDao(); // Crear una instancia de CuentaDao
	    return CuentasClienteDao.obtenerCuentasPorCliente(dniCliente);
	}

	public List<Integer> obtenerCuentasClientePorDNI(String dni) {
		CuentasClienteDao cuentasCliente = new CuentasClienteDao();
		return CuentasClienteDao.obtenerCuentasClientePorDni(dni);
	}

	public Cuenta obtenerCuentaPorCodigo(int codigoCuenta) {
		CuentaDao cuentasPorDni = new CuentaDao();
	    return cuentasPorDni.obtenerCuentasPorDni(codigoCuenta);
	}
	public List<Cuenta> obtenerListaCuentas() {
		CuentaDao cuentas= new CuentaDao(); 
	    return cuentas.obtenerListaCuentas();
	    		
	}

	public void realizarTrasferencia(int idCuentaOrigen, int idcuentaDestino, int cantidad) {
		CuentaDao cuenta= new CuentaDao(); 
		cuenta.transferirSaldo(idCuentaOrigen, idcuentaDestino, cantidad);
		
	}

	public void realizarReintegro(int idcuentaSeleccionada, int cantidad) {
		CuentaDao cuenta= new CuentaDao(); 
		cuenta.restarSaldoCuenta(idcuentaSeleccionada, cantidad);
		
		
	}

	public void realizarIngreso(int idcuentaSeleccionada, int cantidad) {
		CuentaDao cuenta= new CuentaDao(); 
		cuenta.sumarSaldoCuenta(idcuentaSeleccionada, cantidad);
		
	}
	
}

	