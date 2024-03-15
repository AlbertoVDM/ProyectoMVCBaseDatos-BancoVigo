package vista;

import java.awt.Dimension;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import controlador.*;
import modelo.*;
import vista.*;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private Controlador miControlador;

	public Controlador getMiCoordinador() {
		return miControlador;
	}

	public void setMiCoordinador(Controlador miCoordinador) {
		this.miControlador = miCoordinador;
	}

	public static void main(String[] args) {
		Controlador miCoordinador = new Controlador();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
        
        setTitle("BancoVigo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(587, 245);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear un JLayeredPane para manejar las capas
        layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

                JButton btnClientes = new JButton("Clientes");
                btnClientes.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		VentanaCliente ventanaCliente = new VentanaCliente(miControlador);
                        ventanaCliente.setVisible(true);
                	}
                });
                btnClientes.setName("btnClientes");
                btnClientes.setBorder(null);
                btnClientes.setBounds(111, 92, 98, 23);
                layeredPane.add(btnClientes);

                JButton btnCuentas = new JButton("Cuentas");
                btnCuentas.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		miControlador.mostrarVentanaCuentas();
                	}
                	
                });
                btnCuentas.setName("btnCuentas");
                btnCuentas.setBorder(null);
                btnCuentas.setBounds(219, 92, 98, 23);
                layeredPane.add(btnCuentas);

                JButton btnTransacciones = new JButton("Transacciones");
                btnTransacciones.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		VentanaTransacciones ventanaTransacciones = new VentanaTransacciones(miControlador);
                        ventanaTransacciones.setVisible(true);
                	}
                });
                btnTransacciones.setName("btnTransacciones");
                btnTransacciones.setBorder(null);
                btnTransacciones.setBounds(327, 92, 98, 23);
                layeredPane.add(btnTransacciones);

               
    }
}
