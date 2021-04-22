package org.ciberfarma.app;

import java.awt.EventQueue;
import javax.swing.JPanel;
import org.ciberfarma.modelo.Producto;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrameProducto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCod_p;
	private JTextField txtDescr;
	private JTextField txtStock;
	private JTextArea textS;
	private JComboBox cboCategoria;
	private JComboBox cboEstado;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameProducto frame = new FrameProducto();
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
	public FrameProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btnRegistrar.setBounds(358, 9, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(358, 115, 89, 23);
		contentPane.add(btnBuscar);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(29, 13, 66, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 206, 364, 113);
		contentPane.add(scrollPane);
		
		textS = new JTextArea();
		scrollPane.setViewportView(textS);
		
		JLabel lblNewLabel_2 = new JLabel("stock:");
		lblNewLabel_2.setBounds(29, 77, 81, 14);
		contentPane.add(lblNewLabel_2);
		
		txtCod_p = new JTextField();
		txtCod_p.setColumns(10);
		txtCod_p.setBounds(120, 9, 89, 20);
		contentPane.add(txtCod_p);
		
		txtDescr = new JTextField();
		txtDescr.setText("");
		txtDescr.setColumns(10);
		txtDescr.setBounds(120, 40, 108, 20);
		contentPane.add(txtDescr);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(120, 70, 108, 20);
		contentPane.add(txtStock);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(120, 101, 120, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblNewLabel_3 = new JLabel("Precio:");
		lblNewLabel_3.setBounds(29, 105, 81, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
			}
		});
		btnActualizar.setBounds(358, 43, 89, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Elimina();
			}
		});
		btnEliminar.setBounds(358, 79, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnListP = new JButton("Listar Productos");
		btnListP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listap();
			}
		});
		btnListP.setBounds(309, 330, 120, 23);
		contentPane.add(btnListP);
		
		JLabel lblNewLabel_5 = new JLabel("Categoria:");
		lblNewLabel_5.setBounds(29, 153, 81, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Estado:");
		lblNewLabel_6.setBounds(258, 153, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		cboCategoria = new JComboBox();
		cboCategoria.setModel(new DefaultComboBoxModel(new String[] {">---Seleccione", "--Pastillas", "--Jarabe", "--Cremas", "--Tocador", "--Cuidado", "--Otros"}));
		cboCategoria.setToolTipText("");
		cboCategoria.setBounds(115, 149, 125, 22);
		contentPane.add(cboCategoria);
		
		cboEstado = new JComboBox();
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {">--Seleccione", "--Activo", "--Suspendido"}));
		cboEstado.setBounds(327, 149, 120, 22);
		contentPane.add(cboEstado);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(29, 43, 81, 14);
		contentPane.add(lblNewLabel_1);
	}

	protected void Listap() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em=factory.createEntityManager();
		
		TypedQuery<Producto> consult= em.createNamedQuery("Producto.fiendall",Producto.class);
		List<Producto> LstProducto=consult.getResultList();
	
	    	em.close();
	    	textS.setText("");
	    	for (Producto producto : LstProducto) {
	    		textS.append(producto.getIdprod()+"   \t"+producto.getDescripcion()+"         \t"+producto.getStock()+"           \t"+producto.getPrecio()+"\n");
				
			
	    }
	}

	protected void Elimina() {
		String cod= leercodigo();
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		em.getTransaction().begin();
		Producto p = em.find(Producto.class, cod);
		em.remove(p);
		em.getTransaction().commit();
		MensageConfirmd("Resitro eliminado");
		em.close();
		setvalores();
		list();
		
	}

	protected void Actualizar() {
		String cod=leercodigo();
		String descrip= leerdescrip();
		double precio=leerprecio();
		int stok = leerstock();
		int categoria =leercate();
		int estado=leerstado();
		
		Producto p  = new Producto();
		
		p.setIdprod(cod);
		p.setDescripcion(descrip);
		p.setEstado(estado);
		p.setIdcategoria(categoria);
		p.setPrecio(precio);
		p.setStock(stok);
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(p);
		MensageConfirmd("Registro actualizado");
		em.getTransaction().commit();
		setvalores();
		Listap();
		
	}

	protected void buscar() {
		String cod=leercodigo();
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		Producto p = em.find(Producto.class, cod);
		em.close();
		if(p==null) {
			Mensage("Producto no existe por este id :"+cod);
		} else {
			txtCod_p.setText(p.getIdprod());
			txtDescr.setText(p.getDescripcion());
			txtPrecio.setText(p.getPrecio()+"");
			txtStock.setText(p.getStock()+"");
			cboCategoria.setSelectedIndex(p.getIdcategoria());
			cboEstado.setSelectedIndex(p.getEstado());
		}
		
	}

	protected void registrar() {
		String descrip= leerdescrip();
		double precio=leerprecio();
		int stok = leerstock();
		int categoria =leercate();
		int estado=leerstado();
		
		Producto p = new Producto();
		p.setDescripcion(descrip);
		p.setPrecio(precio);
		p.setStock(stok);
		p.setEstado(estado);
		p.setIdcategoria(categoria);
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(p);
		
		MensageConfirmd("Producto Agregado :"+p.getDescripcion());
		em.getTransaction().commit();
		setvalores();
		
		Listap();
		
	}
	
	private void setvalores() {
		txtCod_p.setText("");
		txtDescr.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
		cboCategoria.setSelectedIndex(0);
		cboEstado.setSelectedIndex(0);
		
	}

	private int leerstado() {
		int esta = -1;
		esta=cboEstado.getSelectedIndex();
		return esta;
	}

	private int leercate() {
		int cate=-1;
		cate=cboCategoria.getSelectedIndex();
		return cate;
	}

	private int leerstock() {
		int stock=-1;
		stock=Integer.parseInt(txtStock.getText());
		return stock;
	}

	private double leerprecio() {
		double pre=-1.0;
		pre=Double.parseDouble(txtPrecio.getText());
		return pre;
	}

	private String leerdescrip() {
		String descrip=null;
		descrip=txtDescr.getText();
		return descrip;
	}

	private String leercodigo() {
		String cod=null;
		cod=txtCod_p.getText();
		return cod;
	}
	private void MensageConfirmd(String m) {
		JOptionPane.showMessageDialog(this, m, " Aviso del sistema", JOptionPane.DEFAULT_OPTION);

	}
	private void Mensage(String m) {

		JOptionPane.showMessageDialog(this, m, " Aviso del sistema", 2);
	}

}
