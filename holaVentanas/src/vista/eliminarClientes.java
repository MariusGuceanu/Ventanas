package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class eliminarClientes extends JFrame {

	private JPanel contentPane;
	private JTextField textDni;
	private GestorBBDD gbd = new GestorBBDD();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eliminarClientes frame = new eliminarClientes();
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
	public eliminarClientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(52, 29, 46, 14);
		contentPane.add(lblDni);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                try {
                	
                	gbd.conectar();
                	
                    Cliente cliente = new Cliente();
                    
                    cliente.setDni(textDni.getText());

                    
                    gbd.eliminarCliente(cliente);

                    gbd.cerrar();
                    
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                textDni.setText("");

			}
		});
		btnNewButton.setBounds(52, 70, 89, 23);
		contentPane.add(btnNewButton);
		
		
		textDni = new JTextField();
		textDni.setBounds(108, 26, 86, 20);
		contentPane.add(textDni);
		textDni.setColumns(10);
	}
}
