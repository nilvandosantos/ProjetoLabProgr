package view;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import codigo.Cartao;
import codigo.Garcom;
import codigo.Pedido;
import codigo.Produto;

import control.CadGarcom;
import control.CadProduto;
import control.CoordCaixa;
import control.CoordPedido;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Calendar;

public class JFpagtoCartao extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTotal;
	private JTextField textFieldParcelas;
	private JTextField textFieldValParc;
	private JFencerraPedido jFa_ser_fechado1;
	private JFpedidoEncerrado jFa_ser_fechado2;
	private int indice_comanda;
	private int indice_caixa;
	private Pedido c;
	private double subtotal;
	private double total;
	private boolean pressionado = false;
	

	/**
	 * Create the frame.
	 * @param indice_comanda 
	 * @param indice_caixa 
	 * @param jFpedidoEncerrado 
	 * @param jFa_ser_fechado1 
	 */
	public JFpagtoCartao(JFencerraPedido jFa_ser_fechado1, JFpedidoEncerrado jFpedidoEncerrado, int ind_caixa, int ind_pedido) {
		this.jFa_ser_fechado1 = jFa_ser_fechado1;
        this.jFa_ser_fechado2 = jFpedidoEncerrado;
        this.indice_caixa = ind_caixa;
        this.indice_comanda = ind_pedido;
		
		setResizable(false);
		setTitle("Cart\u00E3o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 244, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotal.setBounds(10, 29, 46, 14);
		contentPane.add(lblTotal);
		
		JLabel lblParcelas = new JLabel("Parcelas:");
		lblParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblParcelas.setBounds(10, 54, 46, 14);
		contentPane.add(lblParcelas);
		
		JLabel lblValorDasParcelas = new JLabel("Valor Das Parcelas");
		lblValorDasParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorDasParcelas.setBounds(10, 79, 123, 14);
		contentPane.add(lblValorDasParcelas);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(142, 27, 86, 20);
		contentPane.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		textFieldParcelas = new JTextField();
		textFieldParcelas.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent evt) {
				DecimalFormat d = new DecimalFormat("0.00");
		        //Evento KeyPressed que verifica se o enter foi pressionado no textFieldParcelas
		        int i = evt.getKeyCode();
		        if (i == 10) {
		            pressionado = true;
		            try {
		                if (Integer.parseInt(textFieldParcelas.getText()) <= 0) {
		                            JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
		                            textFieldParcelas.setText("");
		                            return;
		                        }
		                textFieldValParc.setText("" + d.format((total / Double.parseDouble(textFieldParcelas.getText()))));
		            } catch (NumberFormatException n) {
		                JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
		                textFieldParcelas.setText("");
		                return;
		            }
		        }
			}
		});
		textFieldParcelas.setBounds(142, 52, 86, 20);
		contentPane.add(textFieldParcelas);
		textFieldParcelas.setColumns(10);
		
		textFieldValParc = new JTextField();
		textFieldValParc.setBounds(142, 77, 86, 20);
		contentPane.add(textFieldValParc);
		textFieldValParc.setColumns(10);
		
		iniciar();
		
		JButton btnEncerra = new JButton("Encerra");
		btnEncerra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				DecimalFormat d = new DecimalFormat("0.00");
			    //Evento KeyPressed que verifica se o enter foi pressionado no textfield2
			    int i = arg0.getKeyCode();
			    if ((i == 10) && !textFieldParcelas.getText().equals("")) {
			        pressionado = true;
			        try {
			            if (Integer.parseInt(textFieldParcelas.getText()) <= 0) {
			                        JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
			                        textFieldParcelas.setText("");
			                        return;
			                    }
			            textFieldValParc.setText("" + d.format((total / Double.parseDouble(textFieldParcelas.getText()))));
			        } catch (NumberFormatException n) {
			            JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
			            textFieldParcelas.setText("");
			            return;
			        }
			    }
			}
		});
		btnEncerra.addActionListener(new ActionListener() {
			
			private int indice_do_produto_na_comanda;

			public void actionPerformed(ActionEvent arg0) {
				Cartao d;

		        //Confere se os campos estão devidamente preenchidos
		        if (textFieldParcelas.getText().equals("")) {
		            JOptionPane.showMessageDialog(JFpagtoCartao.this, "Preencha o campo!", "Erro", JOptionPane.ERROR_MESSAGE);
		        } else if (!pressionado) {
		            JOptionPane.showMessageDialog(JFpagtoCartao.this, "Pressione a tecla Enter para realizar o cálculo das parcelas!", "Erro", JOptionPane.ERROR_MESSAGE);
		        } else {

		            try {
		                if (Integer.parseInt(textFieldParcelas.getText()) <= 0) {
		                    JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
		                    textFieldParcelas.setText("");
		                    return;
		                }
		                d = new Cartao(total, total, Integer.parseInt(textFieldParcelas.getText()));
		            } catch (NumberFormatException n) {
		                JOptionPane.showMessageDialog(null, "Digite um valor válido de parcelas!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
		                textFieldParcelas.setText("");
		                return;
		            }

		            //Calcula a gorjeta do garçom
		            for (Garcom g : CadGarcom.getGarcons()) {
		                if (g == c.getGarcons()) {
		                    g.setTotalGorjeta(total - subtotal);
		                }
		            }

		            for (Produto produto_na_comanda : c.retornaProdutos()) {

		                for (Produto produto_cadastrado : CadProduto.getProdutos()) {

		                    if (produto_na_comanda == produto_cadastrado) {
		                        int quantidade_de_produtos_vendidos = c.getQtde(indice_do_produto_na_comanda);
		                        produto_cadastrado.atualizaQuantidadeVendida(quantidade_de_produtos_vendidos);
		                    }

		                }

		                indice_do_produto_na_comanda++;

		            }

		            //Seta os dados da comanda
		            Calendar hora_temp = Calendar.getInstance();
		            String hora = "" + hora_temp.get(Calendar.HOUR_OF_DAY) + ":" + hora_temp.get(Calendar.MINUTE) + ":" + hora_temp.get(Calendar.SECOND);
		            double temp = CoordPedido.retornaUmPedido(indice_caixa, indice_comanda).calculaValorPedido();
		            temp = temp + temp * 0.1;
		            CoordCaixa.retornaUmCaixa(indice_caixa).atualizaTotalCaixa(temp);
		            c.setPagamento(d);
		            c.setHora(hora);
		            c.setPedidoAberta(false);



		            JOptionPane.showMessageDialog(JFpagtoCartao.this, "Comanda finalizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

		            dispose();

		        }
			}
		});
		btnEncerra.setBounds(10, 122, 89, 23);
		contentPane.add(btnEncerra);
		
		JButton btnCancela = new JButton("Cancela");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancela.setBounds(139, 122, 89, 23);
		contentPane.add(btnCancela);
	}
	public void iniciar() {

        c = CoordPedido.retornaUmPedido(indice_caixa, indice_comanda);
        subtotal = c.calculaValorPedido();
        total = subtotal + subtotal * 0.1;

        textFieldTotal.setText("" + total);

    }
}
