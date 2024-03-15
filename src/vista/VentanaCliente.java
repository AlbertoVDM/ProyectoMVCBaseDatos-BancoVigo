package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Cliente;
import modelo.Conexion;
import modelo.Logica;
import modelo.dao.ClienteDao;

public class VentanaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfclDni;
	private JTextField tfclNombre;
	private JTextField tfclApellido;
	private JTextField tfclTelefono;
	private Controlador miControlador;
	private JTable table;
	private JTable table_1;


	

	public static void main(String[] args) {
		Controlador miControlador = new Controlador();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCliente frame = new VentanaCliente(miControlador);
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
	 */
	public VentanaCliente(Controlador miControlador) {
		this.miControlador=miControlador;
		setTitle("Ventana Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(918, 518);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(86, 28, 773, 196);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("DNI: ");
		lblNewLabel_1.setBounds(98, 61, 80, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre: ");
		lblNewLabel_1_1.setBounds(98, 92, 80, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Apellido: ");
		lblNewLabel_1_1_1.setBounds(98, 122, 80, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Telefono: ");
		lblNewLabel_1_1_1_1.setBounds(98, 152, 80, 14);
		panel.add(lblNewLabel_1_1_1_1);
		
		tfclDni = new JTextField();
		tfclDni.setName("tfclDni");
		tfclDni.setBounds(198, 58, 300, 20);
		panel.add(tfclDni);
		tfclDni.setColumns(10);
		
		tfclNombre = new JTextField();
		tfclNombre.setName("tfclNombre");
		tfclNombre.setBounds(198, 89, 300, 20);
		panel.add(tfclNombre);
		tfclNombre.setColumns(10);
		
		tfclApellido = new JTextField();
		tfclApellido.setName("tfclApellido");
		tfclApellido.setBounds(198, 119, 300, 20);
		panel.add(tfclApellido);
		tfclApellido.setColumns(10);
		
		tfclTelefono = new JTextField();
		tfclTelefono.setName("tfclTelefono");
		tfclTelefono.setBounds(198, 149, 300, 20);
		panel.add(tfclTelefono);
		tfclTelefono.setColumns(10);
		
		
		
		JButton btnNuevoCL = new JButton("Nuevo");
		btnNuevoCL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente miCliente=new Cliente();
				miCliente.setClDni(tfclDni.getText());
				miCliente.setClNombre(tfclNombre.getText());
				miCliente.setClApellido(tfclApellido.getText());
				miCliente.setClTelefono(Integer.parseInt(tfclTelefono.getText()));
                miControlador.registrarCliente(miCliente);
                
			}
		});
		btnNuevoCL.setName("btnNuevoCL");
		btnNuevoCL.setBounds(560, 57, 120, 23);
		panel.add(btnNuevoCL);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

	        // Crear un objeto Cliente con los datos actualizados
	        Cliente clienteActualizado = new Cliente();
	        clienteActualizado.setClDni(tfclDni.getText());
	        clienteActualizado.setClNombre(tfclNombre.getText());
	        clienteActualizado.setClApellido(tfclApellido.getText());
	        clienteActualizado.setClTelefono(Integer.parseInt(tfclTelefono.getText()));

	        // Llamar al método actualizarCliente del coordinador
	        miControlador.actualizarCliente(clienteActualizado);

	        // Mensaje de éxito
	        JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	    }
	});
		btnActualizar.setBounds(560, 102, 120, 23);
		panel.add(btnActualizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 259, 769, 166);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        DefaultTableModel modeloTabla = miControlador.obtenerClientes();
		        table_1.setModel(modeloTabla);
		    }
		});
		btnBuscar.setBounds(560, 148, 120, 23);
		panel.add(btnBuscar);
		
		JButton btnSalir = new JButton("Volver");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();
			}
		});
		btnSalir.setBounds(770, 446, 89, 23);
		contentPane.add(btnSalir);
		
		table_1.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        // Verificar si la selección no está vacía y si no se está ajustando el modelo de la tabla
		        if (!e.getValueIsAdjusting() && table_1.getSelectedRow() != -1) {
		            // Obtener los valores de la fila seleccionada
		            String dni = table_1.getValueAt(table_1.getSelectedRow(), 0).toString();
		            String nombre = table_1.getValueAt(table_1.getSelectedRow(), 1).toString();
		            String apellido = table_1.getValueAt(table_1.getSelectedRow(), 2).toString();
		            String telefono = table_1.getValueAt(table_1.getSelectedRow(), 3).toString();

		            // Establecer los valores en los campos de texto
		            tfclDni.setText(dni);
		            tfclNombre.setText(nombre);
		            tfclApellido.setText(apellido);
		            tfclTelefono.setText(telefono);
		        }
		    }
		});
		
		
		
		
	}
	public void setMiContrrolador(Controlador miControlador) {
		// TODO Auto-generated method stub
		
	}
	
	

}
