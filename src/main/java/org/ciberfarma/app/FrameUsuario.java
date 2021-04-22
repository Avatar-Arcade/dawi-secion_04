package org.ciberfarma.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrameUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtUsu;
	private JTextField txtClave;
	private JTextField txtFecNan;
	private JTextField txtApellido;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextArea txtS;
	private JComboBox cboTipo;
	private JComboBox cboEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameUsuario frame = new FrameUsuario();
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
	public FrameUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrau();
			}
		});
		btnRegistrar.setBounds(358, 22, 89, 23);
		contentPane.add(btnRegistrar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarU();
			}
		});
		btnBuscar.setBounds(358, 139, 89, 23);
		contentPane.add(btnBuscar);

		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(29, 26, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(29, 62, 66, 14);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 219, 364, 113);
		contentPane.add(scrollPane);

		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);

		JLabel lblNewLabel_2 = new JLabel("Usuario:");
		lblNewLabel_2.setBounds(29, 90, 66, 14);
		contentPane.add(lblNewLabel_2);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(105, 22, 89, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setText("");
		txtNombre.setBounds(105, 53, 108, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtUsu = new JTextField();
		txtUsu.setBounds(105, 83, 108, 20);
		contentPane.add(txtUsu);
		txtUsu.setColumns(10);

		txtClave = new JTextField();
		txtClave.setBounds(105, 114, 120, 20);
		contentPane.add(txtClave);
		txtClave.setColumns(10);

		txtFecNan = new JTextField();
		txtFecNan.setBounds(171, 145, 141, 20);
		contentPane.add(txtFecNan);
		txtFecNan.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setText("");
		txtApellido.setColumns(10);
		txtApellido.setBounds(223, 53, 110, 20);
		contentPane.add(txtApellido);

		lblNewLabel_3 = new JLabel("Clave:");
		lblNewLabel_3.setBounds(29, 118, 66, 14);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Fecha de Nacimiento:");
		lblNewLabel_4.setBounds(29, 153, 132, 14);
		contentPane.add(lblNewLabel_4);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaru();
			}
		});
		btnActualizar.setBounds(358, 56, 89, 23);
		contentPane.add(btnActualizar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminaru();
			}
		});
		btnEliminar.setBounds(358, 92, 89, 23);
		contentPane.add(btnEliminar);

		JButton btnListU = new JButton("Listar Usuarios");
		btnListU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListU.setBounds(309, 343, 120, 23);
		contentPane.add(btnListU);

		JLabel lblNewLabel_5 = new JLabel("Tipo:");
		lblNewLabel_5.setBounds(29, 194, 46, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Estado:");
		lblNewLabel_6.setBounds(220, 194, 46, 14);
		contentPane.add(lblNewLabel_6);

		cboTipo = new JComboBox();
		cboTipo.setModel(new DefaultComboBoxModel(new String[] { ">--Seleccione", "-Administrativo", "-Cliente" }));
		cboTipo.setToolTipText("");
		cboTipo.setBounds(69, 186, 125, 22);
		contentPane.add(cboTipo);

		cboEstado = new JComboBox();
		cboEstado.setModel(
				new DefaultComboBoxModel(new String[] { ">--Seleccione", "--activo", "--suspendido", "--eliminado" }));
		cboEstado.setBounds(276, 186, 120, 22);
		contentPane.add(cboEstado);

	}

	protected void actualizaru() {
		// TODO Auto-generated method stub
		int cod = leercodigo();
		String nomb = leerNombre();
		String Apellido = leerApellido();
		String Usuario = leerUsuario();
		String Clave = leerclave();
		String fecha = leerfecha();
		int tipo = leertipo();
		int estado = leerEstado();

		Usuario u = new Usuario();

		u.setCodigo(cod);
		u.setNombre(nomb);
		u.setApellido(Apellido);
		u.setUsuario(Usuario);
		u.setClave(Clave);
		u.setFnacim(fecha);
		u.setTipo(tipo);
		u.setEstado(estado);

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		em.getTransaction().begin();

		em.merge(u);

		MensageConfirmd("Registro Actualizado");
		em.getTransaction().commit();
		em.close();
		setvalores();

		listado();

	}

	protected void Eliminaru() {
		// TODO Auto-generated method stub
		int cod = leercodigo();


		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		em.getTransaction().begin();
		Usuario u = em.find(Usuario.class, cod);
		em.remove(u);
		em.getTransaction().commit();
		MensageConfirmd("Registro Eliminado");
		em.close();
		
		setvalores();
		listado();

	}

	private void setvalores() {
		txtApellido.setText("");
		txtClave.setText("");
		txtCodigo.setText("");
		txtNombre.setText("");
		txtUsu.setText("");
		txtFecNan.setText("");
		cboEstado.setSelectedIndex(0);
		cboTipo.setSelectedIndex(0);
	}

	protected void BuscarU() {
		// TODO Auto-generated method stub
		int cod = leercodigo();

		// buscar en la tabla
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		Usuario usu = em.find(Usuario.class, cod);
		em.close();
		if (usu == null) {
			Mensage("Usuario no existe por este codigo :" + cod);
		} else {
			txtNombre.setText(usu.getNombre());
			txtApellido.setText(usu.getApellido());
			txtUsu.setText(usu.getUsuario());
			txtFecNan.setText(usu.getFnacim());
		}

	}

	private int leercodigo() {
		int cod = -1;
		if (!txtCodigo.getText().matches("[0-9]{1,5}")) {
			Mensage("Codigo invalido");
		} else {
			cod = Integer.parseInt(txtCodigo.getText());
		}
		return cod;
	}

	private void Mensage(String m) {

		JOptionPane.showMessageDialog(this, m, " Aviso del sistema", 2);
	}

	protected void Registrau() {
		// TODO Auto-generated method stub
		String nomb = leerNombre();
		String Apellido = leerApellido();
		String Usuario = leerUsuario();
		String Clave = leerclave();
		String fecha = leerfecha();
		int tipo = leertipo();
		int estado = leerEstado();

		Usuario u = new Usuario();

		u.setNombre(nomb);
		u.setApellido(Apellido);
		u.setUsuario(Usuario);
		u.setClave(Clave);
		u.setFnacim(fecha);
		u.setTipo(tipo);
		u.setEstado(estado);

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		em.getTransaction().begin();

		em.persist(u);

		MensageConfirmd("Registro agragado");
		em.getTransaction().commit();
		em.close();
		setvalores();

		listado();

	}

	private int leerEstado() {
		int estado;
		estado = cboEstado.getSelectedIndex();
		return estado;
	}

	private int leertipo() {
		int tipo;
		tipo = cboTipo.getSelectedIndex();
		return tipo;
	}

	private String leerfecha() {
		String fecha;
		fecha = txtFecNan.getText();
		return fecha;
	}

	private String leerclave() {
		String clave;
		clave = txtClave.getText();
		return clave;
	}

	private String leerUsuario() {
		String usu;
		usu = txtUsu.getText();
		return usu;
	}

	private String leerApellido() {
		String apel;
		apel = txtApellido.getText();
		return apel;
	}

	private String leerNombre() {
		String nom;
		nom = txtNombre.getText();
		return nom;
	}

	private void MensageConfirmd(String m) {
		JOptionPane.showMessageDialog(this, m, " Aviso del sistema", JOptionPane.DEFAULT_OPTION);

	}

	protected void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAll", Usuario.class);
		List<Usuario> LstUsuario = consulta.getResultList();

		em.close();
		txtS.setText("");
		for (Usuario u : LstUsuario) {
			txtS.append(u.getCodigo() + "\t" + u.getNombre() + "\t" + u.getApellido() + "\n");
		}
	}
}
