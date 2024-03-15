package vista;

import java.awt.EventQueue;

import controlador.Controlador;
import java.awt.Image;
import java.awt.Toolkit;
import modelo.*;
import modelo.dao.*;
	

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

public class VentanaCuentas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNCuenta;
	private JTextField tfSaldo;
	private JTable tableCuentas;
	private static Controlador miControlador;
	
	

	public void setMiCoordinador(Controlador miControlador) {
	    this.miControlador = miControlador;
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 Controlador miCoordinador = new  Controlador();
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                VentanaCuentas frame = new VentanaCuentas(miControlador);
	                frame.setMiCoordinador(miCoordinador); // Configura el coordinador en la instancia de VentanaCuentas
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
	@Override
	public Image getIconImage() {
	   Image retValue = Toolkit.getDefaultToolkit().
	         getImage(ClassLoader.getSystemResource("image/banco.png"));


	   return retValue;
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VentanaCuentas(Controlador miCoordinador) throws SQLException {
		this.miControlador = miCoordinador;
		// Obtener la lista de clientes de la base de datos
		ClienteDao clienteDao = new ClienteDao();
		DefaultTableModel model = clienteDao.obtenerClientes();
		
		// Convertir DefaultTableModel a List<Cliente>
		List<Cliente> clientes = new ArrayList<>();
		for (int i = 0; i < model.getRowCount(); i++) {
			String clDni = (String) model.getValueAt(i,0);
			String clNombre = (String) model.getValueAt(i, 1); 
			String clApellido = (String) model.getValueAt(i, 2);
			String clTelefonoString = (String) model.getValueAt(i, 3); 
			int clTelefono = Integer.parseInt(clTelefonoString); 
			Cliente cliente = new Cliente(clDni,clNombre, clApellido, clTelefono); 
			clientes.add(cliente);
		}
		SucursalesDao sucursalesDao = new SucursalesDao();
		DefaultTableModel modelS = sucursalesDao.obtenerSucursales();
		// Convertir DefaultTableModel a List<Sucursales>
				List<Sucursales> sucursales = new ArrayList<>();
				for (int i = 0; i < modelS.getRowCount(); i++) {
					String suCodSucursalString = (String) modelS.getValueAt(i, 0); 
					int suCodSucursal = Integer.parseInt(suCodSucursalString); 
					String suCiudad = (String) modelS.getValueAt(i, 1); 
					BigDecimal suActivos = new BigDecimal((String) modelS.getValueAt(i, 2));
					Sucursales sucursal = new Sucursales(suCodSucursal,suCiudad, suActivos); 
					sucursales.add(sucursal);
				}
		

		setTitle("Ventana Cuentas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(928, 532);
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cuentas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(69, 10, 766, 193);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nº de Cuenta:");
		lblNewLabel.setBounds(38, 27, 84, 22);
		panel.add(lblNewLabel);
		
		tfNCuenta = new JTextField();
		tfNCuenta.setName("tfNCuenta");
		tfNCuenta.setBounds(132, 28, 420, 22);
		panel.add(tfNCuenta);
		tfNCuenta.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(38, 60, 84, 22);
		panel.add(lblCliente);
		
		JLabel lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setBounds(38, 93, 84, 22);
		panel.add(lblSucursal);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(38, 126, 84, 22);
		panel.add(lblFecha);
		
		// Obtener la fecha actual del coordinador
		int currentDay = miCoordinador.getCurrentDay();
		int currentMonth = miCoordinador.getCurrentMonth();
		int currentYear = miCoordinador.getCurrentYear();
		 // Obtener días, meses y años utilizando la clase de lógica
		String[] diasDelMes = Logica.obtenerDiasDelMes(currentMonth, currentYear);
        String[] meses = Logica.obtenerMeses();
        String[] años = Logica.obtenerAños();


        // Crear JComboBox para días, meses y años
        JComboBox<String> cbDia = new JComboBox<>(diasDelMes);
        cbDia.setName("cbDia");
        cbDia.setBounds(132, 126, 45, 22);
        // Establecer valor predeterminado
        cbDia.setSelectedItem(String.valueOf(currentDay));
        panel.add(cbDia);

        JComboBox<String> cbMes = new JComboBox<>(meses);
        cbMes.setName("cbMes");
        cbMes.setBounds(187, 126, 93, 22);
        // Establecer valor predeterminado
        cbMes.setSelectedIndex(currentMonth - 1);
        panel.add(cbMes);
        

        JComboBox<String> cbYear = new JComboBox<>(años);
        cbYear.setName("cbYear");
        cbYear.setBounds(290, 126, 53, 22);
        // Establecer valor predeterminado
        cbYear.setSelectedItem(String.valueOf(currentYear));
        panel.add(cbYear);
        panel.add(cbYear);
		
		JComboBox cbCliente = new JComboBox<>(clientes.toArray(new Cliente[0]));
		cbCliente.setName("cbCliente");
		cbCliente.setBounds(132, 60, 420, 22);
		panel.add(cbCliente);
		
		
//		JComboBox<Sucursales> cbSucursal = new JComboBox<>(sucursales.toArray(new Sucursales[0]));
//		cbSucursal.setName("cbSucursal");
//		cbSucursal.setBounds(292, 256, 420, 22);
//		contentPane.add(cbSucursal);
		
		JComboBox cbSurcusales = new JComboBox<>(sucursales.toArray(new Sucursales[0]));
		cbSurcusales.setName("cbSucursales");
		cbSurcusales.setBounds(132, 93, 420, 22);
		panel.add(cbSurcusales);
		
		cbMes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el mes seleccionado
                int mesSeleccionado = cbMes.getSelectedIndex() + 1; // Suma 1 porque los índices comienzan desde 0
                
                // Obtener el año seleccionado del JComboBox correspondiente
                int añoSeleccionado = Integer.parseInt((String) cbYear.getSelectedItem());
                
                // Determinar el número de días del mes
                int numDias;
                switch (mesSeleccionado) {
                    case 2: // febrero
                        if ((añoSeleccionado % 4 == 0 && añoSeleccionado % 100 != 0) || añoSeleccionado % 400 == 0) {
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
                
                // Actualizar el JComboBox de los días del mes
                String[] dias = new String[numDias];
                for (int i = 0; i < numDias; i++) {
                    dias[i] = String.valueOf(i + 1);
                }
                cbDia.setModel(new DefaultComboBoxModel<>(dias));
            }
        });
		
		
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(353, 126, 37, 22);
		panel.add(lblSaldo);
		
		tfSaldo = new JTextField();
		tfSaldo.setName("tfSaldo");
		tfSaldo.setBounds(400, 127, 152, 22);
		panel.add(tfSaldo);
		tfSaldo.setColumns(10);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener los datos de la cuenta
			    //int numeroCuenta = Integer.parseInt(tfNCuenta.getText());
			    int numeroCuenta=0;
			    int saldo = Integer.parseInt(tfSaldo.getText());
			    
			    // Obtener la fecha seleccionada
			    String dia = (String) cbDia.getSelectedItem();
			    String mes = (String) cbMes.getSelectedItem();
			    String year = (String) cbYear.getSelectedItem();
			    String fechaString = year + "-" + mes + "-" + dia;
			    
			    // Convertir la cadena de texto a un objeto Date
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMMM-dd");
			    java.sql.Date fecha = null;
			    try {
			    	java.util.Date parsedDate = dateFormat.parse(fechaString);
			    	fecha = new java.sql.Date(parsedDate.getTime());
			    } catch (ParseException ex) {
			        ex.printStackTrace();
			    }
			    
			    
			    
			    // Obtener la sucursal seleccionada
			    Sucursales sucursalSeleccionada = (Sucursales) cbSurcusales.getSelectedItem();
			    int codigoSucursal = sucursalSeleccionada.getSuCodSucursal();
			    
			    // Crear el objeto Cuenta
			    Cuenta cuenta = new Cuenta(numeroCuenta, codigoSucursal, fecha, saldo);
			    miCoordinador.insertarDatosCuenta(cuenta);
			    
			    int nCuenta = miCoordinador.obtenerNumeroCuentaUltimaInsercion();
			    // Obtener el cliente seleccionado
			    Cliente clienteSeleccionado = (Cliente) cbCliente.getSelectedItem();
			    String dniCliente = clienteSeleccionado.getClDni();
			    // Crear el objeto CuentasCliente
			    CuentasCliente cuentasCliente = new CuentasCliente(dniCliente, nCuenta);
			    
			    // Llamar al método del coordinador para insertar los datos
			    
			    miCoordinador.insertarDatosCuentaClientes(cuentasCliente);
				
			}
		});
		btnNuevo.setName("btnNuevo");
		btnNuevo.setBounds(597, 27, 117, 23);
		panel.add(btnNuevo);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(597, 60, 117, 23);
		panel.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(597, 93, 117, 23);
		panel.add(btnEliminar);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			        // Obtener todas las cuentas del coordinador
			        DefaultTableModel modeloTabla = null;
					try {
						modeloTabla = miCoordinador.obtenerCuentas();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        // Establecer el modelo de la tabla con todas las cuentas
			        tableCuentas.setModel(modeloTabla);
			    }
		});
		btnListado.setBounds(597, 126, 117, 23);
		panel.add(btnListado);
		
		JLabel lblSelecionarCliente = new JLabel("Selecionar Cliente:");
		lblSelecionarCliente.setBounds(79, 213, 111, 22);
		contentPane.add(lblSelecionarCliente);
		

		JComboBox<Cliente> cbClientes2 = new JComboBox<>(clientes.toArray(new Cliente[0]));
		cbClientes2.setBounds(200, 213, 420, 22);
		contentPane.add(cbClientes2);
		
		cbClientes2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener el cliente seleccionado del JComboBox
		        Cliente clienteSeleccionado = (Cliente) cbClientes2.getSelectedItem();
		        
		        // Verificar si se seleccionó un cliente
		        if (clienteSeleccionado != null) {
		            // Obtener el DNI del cliente
		            String dniCliente = clienteSeleccionado.getClDni();
		            DefaultTableModel modeloTabla = null;
					modeloTabla = miCoordinador.traerCuentasCliente(dniCliente);
			        // Establecer el modelo de la tabla con todas las cuentas
			        tableCuentas.setModel(modeloTabla);
		        }
		       
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 245, 766, 167);
		contentPane.add(scrollPane);
		
		tableCuentas = new JTable();
		tableCuentas.setName("tableCuentas");
		scrollPane.setViewportView(tableCuentas);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        DefaultTableModel modeloTabla = null;
				try {
					modeloTabla = miCoordinador.obtenerCuentas();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        tableCuentas.setModel(modeloTabla);
		    }
		});
		
//		tableCuentas.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
//		    @Override
//		    public void valueChanged(ListSelectionEvent e) {
//		        // Verificar si la selección no está vacía y si no se está ajustando el modelo de la tabla
//		        if (!e.getValueIsAdjusting() && tableCuentas.getSelectedRow() != -1) {
//		            // Obtener los valores de la fila seleccionada
//		            String suCodSucursal = tableCuentas.getValueAt(tableCuentas.getSelectedRow(), 0).toString();
//		            String suCiudad = tableCuentas.getValueAt(tableCuentas.getSelectedRow(), 1).toString();
//		            String suActivo = tableCuentas.getValueAt(tableCuentas.getSelectedRow(), 2).toString();
//
//		            
//		        }
//		    }
//		});
		
		tableCuentas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && tableCuentas.getSelectedRow() != -1) {
		            // Obtener los datos de la fila seleccionada
		            String numeroCuenta = tableCuentas.getValueAt(tableCuentas.getSelectedRow(), 0).toString();
		            String codigoSucursal = tableCuentas.getValueAt(tableCuentas.getSelectedRow(), 1).toString();
		            String fecha = tableCuentas.getValueAt(tableCuentas.getSelectedRow(), 2).toString();
		            String saldo = tableCuentas.getValueAt(tableCuentas.getSelectedRow(), 3).toString();

		            // Actualizar los combobox con los datos obtenidos
		            tfNCuenta.setText(numeroCuenta);
		            tfSaldo.setText(saldo);
		            cbSurcusales.setSelectedItem(codigoSucursal);
		            // Aquí debes completar para los demás campos del formulario (fecha, saldo)
		        }
		    }
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setName("btnVolver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(746, 422, 89, 23);
		contentPane.add(btnVolver);
	}
}
