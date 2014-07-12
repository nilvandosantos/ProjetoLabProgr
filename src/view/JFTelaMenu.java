package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class JFTelaMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFTelaMenu frame = new JFTelaMenu();
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
	public JFTelaMenu() {
		setResizable(false);
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 495);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCaixa = new JMenu("Caixa");
		menuBar.add(mnCaixa);

		JMenuItem mntmAberturaDoCaixa = new JMenuItem("Abertura Do Caixa");
		mntmAberturaDoCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JFAberturaCaixa().setVisible(true);
			}
		});
		mnCaixa.add(mntmAberturaDoCaixa);

		JMenuItem mntmBalano = new JMenuItem("Balan\u00E7o");
		mnCaixa.add(mntmBalano);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmGarom = new JMenuItem("Gar\u00E7om");
		mntmGarom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JFcadastroGarcon().setVisible(true);
			}
		});
		mnCadastro.add(mntmGarom);

		JMenuItem mntmProdutos = new JMenuItem("Produtos");
		mntmProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new jFcadastroProduto().setVisible(true);
			}
		});
		mnCadastro.add(mntmProdutos);

		JMenu mnPedido = new JMenu("Pedido");
		menuBar.add(mnPedido);

		JMenuItem mntmAdiciona = new JMenuItem("Adiciona");
		mnPedido.add(mntmAdiciona);

		JMenuItem mntmConsulta = new JMenuItem("Consulta");
		mnPedido.add(mntmConsulta);

		JMenuItem mntmAtualiza = new JMenuItem("Atualiza");
		mnPedido.add(mntmAtualiza);

		JMenuItem mntmEncerra = new JMenuItem("Encerra");
		mnPedido.add(mntmEncerra);

		JMenu mnPesquisa = new JMenu("Pesquisa");
		menuBar.add(mnPesquisa);

		JMenuItem mntmGarom_1 = new JMenuItem("Gar\u00E7om");
		mnPesquisa.add(mntmGarom_1);

		JMenuItem mntmProduto = new JMenuItem("Produto");
		mnPesquisa.add(mntmProduto);

		JMenu mnConsultas = new JMenu("Consulta");
		menuBar.add(mnConsultas);

		JMenuItem mntmGorjeta = new JMenuItem("Gorjeta");
		mnConsultas.add(mntmGorjeta);

		JMenu mnVendas = new JMenu("Vendas");
		menuBar.add(mnVendas);

		JMenuItem mntmProdutosMaisVendidos = new JMenuItem(
				"Produtos Mais Vendidos");
		mnVendas.add(mntmProdutosMaisVendidos);

		JMenuItem mntmProdutosMenosVendidos = new JMenuItem(
				"Produtos Menos Vendidos");
		mnVendas.add(mntmProdutosMenosVendidos);

		JMenu mnSair = new JMenu("Sair");
		mnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		menuBar.add(mnSair);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\NRSS\\Pictures\\images.jpg"));
		lblNewLabel.setBounds(255, 71, 206, 287);
		contentPane.add(lblNewLabel);
	}
}
