package vista;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import controlador.LAUNCHER;
import modelo.Cuenta;
import modelo.dao.CuentaDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;

public class VentanaTransacciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador miControlador;
	private JTextField tfDni;
	private JTextField tfCantidad;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbCuentas1;
	private JComboBox cbCuentas2;
	private LAUNCHER launcher;
	
	
	public Controlador getMiCoordinador() {
		return miControlador;
	}

	public void setMiCoordinador(Controlador miCoordinador) {
		this.miControlador = miCoordinador;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Controlador miCoordinador = new Controlador();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTransacciones frame = new VentanaTransacciones(miCoordinador);
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
	public VentanaTransacciones(Controlador miCoordinador) {
		 this.miControlador = miCoordinador;
		setMaximumSize(new Dimension(2147483647, 2147483538));

		setTitle("Ventana Transacciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(838, 417);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		//setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setMaximumSize(new Dimension(32767, 25000));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(87, 30, 648, 60);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Selecionar Operaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton rbIngreso = new JRadioButton("Ingreso");
		buttonGroup.add(rbIngreso);
		rbIngreso.setBounds(6, 16, 109, 23);
		panel.add(rbIngreso);
		rbIngreso.setName("rbIngreso");
		
		JRadioButton rbReintegro = new JRadioButton("Reintegro");
		buttonGroup.add(rbReintegro);
		rbReintegro.setBounds(247, 16, 109, 23);
		panel.add(rbReintegro);
		rbReintegro.setName("rbReintegro");
		
		JRadioButton rbTransferencia = new JRadioButton("Transferencia");
		buttonGroup.add(rbTransferencia);
		rbTransferencia.setBounds(531, 16, 109, 23);
		panel.add(rbTransferencia);
		rbTransferencia.setName("rbTransferencia");
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(87, 100, 648, 80);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setBounds(6, 20, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		tfDni = new JTextField();
		tfDni.setBounds(33, 17, 120, 20);
		panel_1.add(tfDni);
		tfDni.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccionar cuenta:");
		lblNewLabel_2.setBounds(162, 20, 100, 14);
		panel_1.add(lblNewLabel_2);
		
		cbCuentas1 = new JComboBox();
		cbCuentas1.setName("cbCuentas1");
		cbCuentas1.setBounds(260, 16, 220, 22);
		panel_1.add(cbCuentas1);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(490, 20, 65, 14);
		panel_1.add(lblNewLabel_3);
		
		tfCantidad = new JTextField();
		tfCantidad.setName("tfCantidad");
		tfCantidad.setBounds(540, 17, 100, 20);
		panel_1.add(tfCantidad);
		tfCantidad.setColumns(10);

		tfCantidad.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        // Restaurar el color de fondo predeterminado
		        tfCantidad.setBackground(Color.WHITE);
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	String tipoOperacion = "";
		        if (rbIngreso.isSelected()) {
		            tipoOperacion = "Ingreso";
		        } else if (rbReintegro.isSelected()) {
		            tipoOperacion = "Reintegro";
		        } else if (rbTransferencia.isSelected()) {
		            tipoOperacion = "Transferencia";
		        }

		        // Si es una operación de ingreso, no se tiene en cuenta el saldo
		        if (!tipoOperacion.equals("Ingreso")) {
		            int saldo = Integer.parseInt(cbCuentas1.getSelectedItem().toString().split(", Saldo: ")[1]);
		            int cantidad = Integer.parseInt(tfCantidad.getText());

		            // Verificar si la cantidad ingresada es mayor que el saldo
		            if (cantidad > saldo) {
		                // Mostrar mensaje de advertencia
		                JOptionPane.showMessageDialog(null, "La cantidad es mayor que el saldo actual", "Advertencia", JOptionPane.WARNING_MESSAGE);
		                // Cambiar el fondo del JTextField a rojo
		                tfCantidad.setBackground(Color.RED);
		            }
		    }
		}
		});
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener el DNI ingresado
		        String dni = tfDni.getText();
		        
		        // Limpiar el combobox antes de agregar nuevos elementos
		        cbCuentas1.removeAllItems();
		        
		        // Realizar la consulta en la tabla cuentasCliente para obtener los ccCodCuenta asociados al DNI ingresado
		        List<Integer> cuentasClienteCodigos = miCoordinador.obtenerCuentasClientePorDNI(dni);
		        
		        // Verificar si se encontraron cuentas asociadas al DNI ingresado
		        if (cuentasClienteCodigos.isEmpty()) {
		            // Si no se encontraron cuentas, mostrar un mensaje de error
		            JOptionPane.showMessageDialog(null, "No se encontraron cuentas asociadas al DNI ingresado", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            // Si se encontraron cuentas, iterar sobre los ccCodCuenta obtenidos
		            for (int codigoCuenta : cuentasClienteCodigos) {
		                // Realizar la consulta en la tabla cuenta para obtener el número de cuenta y el saldo asociados al ccCodCuenta
		                Cuenta cuenta = miCoordinador.obtenerCuentaPorCodigo(codigoCuenta);
		                
		                // Verificar si se encontró la cuenta
		                if (cuenta != null) {
		                    // Si se encontró la cuenta, agregar el número de cuenta y el saldo al combobox
		                    cbCuentas1.addItem("Núm de Cuenta: " + cuenta.getCuCodCuenta() + ", Saldo: " + cuenta.getCuSaldo());
		                } else {
		                    // Si no se encontró la cuenta, mostrar un mensaje de error
		                    JOptionPane.showMessageDialog(null, "No se encontró la cuenta asociada al código " + codigoCuenta, "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        }
		    }
		});
		btnConsultar.setName("btnConsultar");
		btnConsultar.setBounds(32, 45, 89, 23);
		panel_1.add(btnConsultar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Destinatario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(87, 201, 648, 60);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Seleccionar Cuenta");
		lblNewLabel_4.setBounds(110, 20, 120, 14);
		panel_2.add(lblNewLabel_4);
		
		cbCuentas2 = new JComboBox();
		cbCuentas2.setBounds(260, 16, 220, 22);
		panel_2.add(cbCuentas2);
		cargarComboBox2();
		cbCuentas2.setEnabled(false);
		
		
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
			}
		});
		
		
		btnNewButton.setName("btnVolver");
		btnNewButton.setBounds(646, 293, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnOk = new JButton("Realizar Operación");
		btnOk.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Verificar qué radio button está seleccionado
		        if (rbIngreso.isSelected()) {
		        	comprobarMovimiento(1);
		        } else if (rbReintegro.isSelected()) {
		        	comprobarMovimiento(2);
		        } else if (rbTransferencia.isSelected()) {
		        	comprobarMovimiento(3);
		        } else {
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona una operación", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		        tfCantidad.setText("");
		        cargarComboBox2();
		    }
		});

		
		
		
		rbTransferencia.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            // Si se selecciona el radioButton, habilita el JComboBox
		            cbCuentas2.setEnabled(true);
		        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
		            // Si se deselecciona el radioButton, deshabilita el JComboBox
		            cbCuentas2.setEnabled(false);
		        }
		    }
		});
		btnOk.setName("btnOk");
		btnOk.setBounds(87, 293, 150, 23);
		contentPane.add(btnOk);
	}
	private void cargarComboBox2() {
		// Limpiar el combobox antes de agregar nuevos elementos
		cbCuentas2.removeAllItems();
        
        // Obtener la lista de cuentas del coordinador
        List<Cuenta> cuentas = miControlador.obtenerListaCuentas();

        // Verificar si se encontraron cuentas
        if (cuentas.isEmpty()) {
            // Si no se encontraron cuentas, mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, "No se encontraron cuentas", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Si se encontraron cuentas, iterar sobre ellas
            for (Cuenta cuenta : cuentas) {
                // Agregar la información de la cuenta al JComboBox
            	cbCuentas2.addItem("Núm de Cuenta: " + cuenta.getCuCodCuenta() + ", Saldo: " + cuenta.getCuSaldo());
            }
        }
    }
	// Método para realizar un ingreso
			private void realizarIngreso() {
			    // Obtener los datos necesarios: DNI, cuenta seleccionada, cantidad
			    String dni = tfDni.getText();
			    String cuentaSeleccionada = cbCuentas1.getSelectedItem().toString();
			    int cantidad = Integer.parseInt(tfCantidad.getText());

			    int idcuentaSeleccionada = obtenerIdCuenta(cuentaSeleccionada);
			    miControlador.realizarIngreso(idcuentaSeleccionada,cantidad);
			}

			// Método para realizar un reintegro
			private void realizarReintegro() {
			    // Obtener los datos necesarios: DNI, cuenta seleccionada, cantidad
			    String dni = tfDni.getText();
			    String cuentaSeleccionada = cbCuentas1.getSelectedItem().toString();
			    int cantidad = Integer.parseInt(tfCantidad.getText());
			    int idcuentaSeleccionada = obtenerIdCuenta(cuentaSeleccionada);

			    miControlador.realizarReintegro(idcuentaSeleccionada,cantidad);
			}

			// Método para realizar una transferencia
			private void realizarTransferencia() {
			    // Obtener los datos necesarios: DNI, cuenta de origen, cuenta de destino, cantidad
			    String dni = tfDni.getText();
			    String cuentaOrigen = cbCuentas1.getSelectedItem().toString();
			    String cuentaDestino = cbCuentas2.getSelectedItem().toString();
			    int cantidad = Integer.parseInt(tfCantidad.getText());
			    
			    int idCuentaOrigen = obtenerIdCuenta(cuentaOrigen);
			    int idcuentaDestino = obtenerIdCuenta(cuentaDestino);
			    
			    miControlador.realizarTrasferencia(idCuentaOrigen,idcuentaDestino,cantidad);
			}
			private int obtenerIdCuenta(String cuentaSeleccionada) {
			    String[] partes = cuentaSeleccionada.split(": ");
			    String idCuentaString = partes[1].split(",")[0].trim();
			    return Integer.parseInt(idCuentaString);
			}
			private void comprobarMovimiento(int control) {
				CuentaDao cuenta= new CuentaDao(); 
			    String dni = tfDni.getText();
			    String cuentaSeleccionada = cbCuentas1.getSelectedItem().toString();
			    int cantidad = Integer.parseInt(tfCantidad.getText());
			    int idCuentaSeleccionada = obtenerIdCuenta(cuentaSeleccionada);
			    int saldoCuenta = cuenta.obtenerSaldoCuenta(idCuentaSeleccionada); 
			    if (cantidad > saldoCuenta) {
			        // Mostrar mensaje de advertencia
			        JOptionPane.showMessageDialog(null, "No hay saldo suficiente para realizar el reintegro", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    } else {
			    	switch (control) {
			        case 1:
			        	realizarIngreso();
			            break;
			        case 2:
			        	realizarReintegro();
			            break;
			        case 3:
			        	realizarTransferencia();
			            break;
			        default:
			        	JOptionPane.showMessageDialog(null, "Error! al realizar la operacion", cuentaSeleccionada, JOptionPane.WARNING_MESSAGE);
			            break;
			    }
			    }
			}
}
