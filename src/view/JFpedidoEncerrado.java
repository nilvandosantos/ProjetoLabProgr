package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;

import control.CoordCaixa;
import control.CoordPedido;

import codigo.Caixa;
import codigo.Pedido;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class JFpedidoEncerrado extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSubTotal;
	private JTextField textFieldGorjeta;
	private JTextField textFieldTotal;

	private JFencerraPedido jFa_ser_fechado1;
	private int indice_caixa;
	private int indice_comanda;
	private Pedido c;
	private double subtotal;
	private double total;

	
	
	/**
	 * Cria a tela para Encerrar pedido.
	 */
	public JFpedidoEncerrado(JFencerraPedido jFa_ser_fechado, int ind_cax,	int ind_ped) {
		this.jFa_ser_fechado1 = jFa_ser_fechado;
		indice_caixa = ind_cax;
		indice_comanda = ind_ped;
		
		JButton btnDinheiro = new JButton("Dinheiro");
		JButton btnCarto = new JButton("Cart\u00E3o");
		JButton btnCheque = new JButton("Cheque");
		
		setFont(new Font("Times New Roman", Font.BOLD, 12));
		setTitle("Encerrar Pedido");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 325, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSubTotal = new JLabel("Sub Total:");
		lblSubTotal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSubTotal.setBounds(10, 24, 79, 14);
		contentPane.add(lblSubTotal);

		JLabel lblGorjeta = new JLabel("Gorjeta:");
		lblGorjeta.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGorjeta.setBounds(10, 49, 65, 14);
		contentPane.add(lblGorjeta);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTotal.setBounds(10, 74, 65, 14);
		contentPane.add(lblTotal);

		textFieldSubTotal = new JTextField();
		textFieldSubTotal.setBounds(150, 21, 159, 20);
		contentPane.add(textFieldSubTotal);
		textFieldSubTotal.setColumns(10);

		textFieldGorjeta = new JTextField();
		textFieldGorjeta.setBounds(150, 46, 159, 20);
		contentPane.add(textFieldGorjeta);
		textFieldGorjeta.setColumns(10);

		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(150, 71, 159, 20);
		contentPane.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		iniciar();
		
		JLabel lblFormasDePagamento = new JLabel("Formas de Pagamento:");
		lblFormasDePagamento
				.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFormasDePagamento.setBounds(77, 118, 159, 24);
		contentPane.add(lblFormasDePagamento);

		btnDinheiro.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnDinheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            new JFpagtoDinheiro(jFa_ser_fechado1, JFpedidoEncerrado.this, indice_caixa, indice_comanda).setVisible(true);
	            dispose();
			}
		});
		btnDinheiro.setBounds(10, 153, 95, 23);
		contentPane.add(btnDinheiro);

		btnCarto.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnCarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JFpagtoCartao(jFa_ser_fechado1, JFpedidoEncerrado.this, indice_caixa, indice_comanda).setVisible(true);
				dispose();
			}
		});
		btnCarto.setBounds(118, 153, 89, 23);
		contentPane.add(btnCarto);

		btnCheque.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            new JFpagtoCheque(jFa_ser_fechado1, JFpedidoEncerrado.this, indice_caixa, indice_comanda).setVisible(true);
	            dispose();
			}
		});
		btnCheque.setBounds(220, 153, 89, 23);
		contentPane.add(btnCheque);
	}
	/**
	 * Metodo para inicar a com as informações referente aos valores do pedido
	 */
	public void iniciar() {

		c = CoordPedido.retornaUmPedido(indice_caixa, indice_comanda);
		subtotal = c.calculaValorPedido();
		total = subtotal + subtotal * 0.1;
		DecimalFormat d = new DecimalFormat("0.00");

		textFieldSubTotal.setText("" + subtotal);
		textFieldGorjeta.setText("" + d.format((total - subtotal)));
		textFieldTotal.setText("" + d.format(total));

	}
}
