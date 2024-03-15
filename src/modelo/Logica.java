package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.Calendar;

import javax.swing.JOptionPane;
import controlador.*;
import modelo.*;
import vista.*;
import modelo.dao.*;

public class Logica {
	private Controlador miControlador;
	public static boolean consultaCliente=false;
	public static boolean modficaCliente=false;
	
	public void setMiCoordinador(Controlador miControlador) {
		this.miControlador=miControlador;
	}
	
	public void validarRegistroCliente(Cliente miCliente){
		ClienteDao miClienteDao;
			miClienteDao = new ClienteDao();
			miClienteDao.registrarCliente(miCliente);						
		
		
	}
	public void validarActualizacionCliente(Cliente miCliente) {
	    ClienteDao miClienteDao = new ClienteDao();
	    miClienteDao.actualizarCliente(miCliente);
	}
	
	//*------------------------ Configuracion fecha ---------------------*//
	public static String[] obtenerDiasDelMes(int mes, int año) {
        int numDias;

        switch (mes) {
            case 2: // febrero
                if ((año % 4 == 0 && año % 100 != 0) || año % 400 == 0) {
                    numDias = 29; // Año bisiesto
                } else {
                    numDias = 28; // Año no bisiesto
                }
                break;
            case 4: // abril
            case 6: // junio
            case 9: // septiembre
            case 11: // noviembre
                numDias = 30;
                break;
            default:
                numDias = 31;
                break;
        }

        String[] dias = new String[numDias];
        for (int i = 0; i < numDias; i++) {
            dias[i] = String.valueOf(i + 1);
        }

        return dias;
    }

    public static String[] obtenerMeses() {
        String[] meses = {"MEnero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses;
    }

    public static String[] obtenerAños() {
        String[] años = new String[11];
        for (int i = 0; i < 11; i++) {
            años[i] = String.valueOf(2020 + i);
        }
        return años;
    }

	public void validarRegistroCuenta(Cuenta cuenta) {
		CuentaDao miCuentaDao;
		miCuentaDao = new CuentaDao();
		miCuentaDao.registrarCuenta(cuenta);
		
	}

	public void validarRegistroCuentaCliente(CuentasCliente cuentasCliente) {
		CuentasClienteDao miCuentasClienteDao;
		miCuentasClienteDao = new CuentasClienteDao();
		miCuentasClienteDao.registrarCuentasCliente(cuentasCliente);
	}

	public int ObtenerUltimoIDCuenta() {
		CuentaDao miCuentaDao;
		miCuentaDao = new CuentaDao();
		int nCuenta=miCuentaDao.obtenerUltimoID();
		return nCuenta;
		
	}
	
}
