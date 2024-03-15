package controlador;

import java.sql.SQLException;

import modelo.Cliente;
import modelo.Logica;
import vista.VentanaCliente;
import vista.VentanaCuentas;
import vista.VentanaPrincipal;
import vista.VentanaTransacciones;
import controlador.Controlador;

public class LAUNCHER {
	public VentanaPrincipal miVentanaPrincipal;
	public static void main(String[] args) throws SQLException {
		LAUNCHER miLAUNCHER=new LAUNCHER();
		miLAUNCHER.iniciar();
	}
	public void iniciar() throws SQLException {
		VentanaPrincipal miVentanaPrincipal=new VentanaPrincipal();
		Logica miLogica=new Logica();
		Controlador miControlador= new Controlador();
		Cliente miCliente=new Cliente();
		VentanaCliente miVentanaCliente=new VentanaCliente(miControlador);
		VentanaCuentas miVentanaCuentas=new VentanaCuentas(miControlador);
		VentanaTransacciones miVentanaTransacciones=new VentanaTransacciones(miControlador);
		
		miVentanaPrincipal.setMiCoordinador(miControlador);
		miLogica.setMiCoordinador(miControlador);
		miCliente.setMiCoordinador(miControlador);
		miVentanaCliente.setMiContrrolador(miControlador);
		miVentanaCuentas.setMiCoordinador(miControlador);
		miVentanaTransacciones.setMiCoordinador(miControlador);
		
		miControlador.setMiVentanaPrincipal(miVentanaPrincipal);
		miControlador.setMiLogica(miLogica);
		miControlador.setMiCliente(miCliente);
		miControlador.setMiVentanaCliente(miVentanaCliente);
		miControlador.setMiVentanaCuentas(miVentanaCuentas);
		miControlador.setMiVentanaTransacciones(miVentanaTransacciones);

		miVentanaPrincipal.setVisible(true);
	}
	public void mostrarVentanaPrincipal() {
        miVentanaPrincipal.setVisible(true);
    }
}
