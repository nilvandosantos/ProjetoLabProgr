package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

import codigo.Dinheiro;
import codigo.Garcom;
import codigo.Pedido;
import codigo.Produto;

import control.CadGarcom;
import control.CadProduto;
import control.CoordCaixa;
import control.CoordPedido;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Calendar;

public class JFpagtoDinheiro extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTotal;
	private JTextField textFieldValRec;
	private JTextField textFieldTroco;
	
	private Pedido c;
	private double subtotal;
	private double total;
	private JFencerraPedido jFa_ser_fechado1;
	private JFpedidoEncerrado jFa_ser_fechado2;
	private int indice_caixa;
	private int indice_pedido;
	private boolean pressionado;
	private int indice_do_produto_no_pedido;

	/**
	 * Cria a tela para a subclasse pagtoDinheiro.
	 * @param indice_pedido 
	 * @param indice_caixa 
	 * @param jFpedidoEncerrado 
	 * @param jFa_ser_fechado1 
	 */
	public JFpagtoDinheiro(JFencerraPedido jFa_ser_fechado1, JFpedidoEncerrado jFpedidoEncerrado, int indice_cai, int indice_ped) {
		this.jFa_ser_fechado1 = jFa_ser_fechado1;
        this.jFa_ser_fechado2 = jFpedidoEncerrado;
        indice_caixa = indice_cai;
        indice_pedido = indice_ped;
		
		setTitle("Dinheiro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 229, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotal.setBounds(10, 58, 110, 14);
		contentPane.add(lblTotal);
		
		JLabel lblValorRecebido = new JLabel("Valor Recebido:");
		lblValorRecebido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorRecebido.setBounds(10, 82, 110, 14);
		contentPane.add(lblValorRecebido);
		
		JLabel lblTroco = new JLabel("Troco:");
		lblTroco.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTroco.setBounds(10, 107, 110, 14);
		contentPane.add(lblTroco);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(123, 55, 86, 20);
		contentPane.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		iniciar();
		
		textFieldValRec = new JTextField();
		textFieldValRec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				 DecimalFormat n = new DecimalFormat("0.00");
			        //Evento KeyPressed que verifica se o enter foi pressionado no textfield2
			        int i = arg0.getKeyCode();
			        if (i == 10) {
			            pressionado = true;
			            textFieldTroco.setText("" + n.format((Double.parseDouble(textFieldValRec.getText()) - total)));
			        }
			}
		});
		textFieldValRec.setBounds(123, 79, 86, 20);
		contentPane.add(textFieldValRec);
		textFieldValRec.setColumns(10);
		
		textFieldTroco = new JTextField();
		textFieldTroco.setBounds(123, 104, 86, 20);
		contentPane.add(textFieldTroco);
		textFieldTroco.setColumns(10);
		
		JButton btnEncerrar = new JButton("Encerrar");
		btnEncerrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				DecimalFormat d = new DecimalFormat("0.00");
		        //Evento KeyPressed que verifica se o enter foi pressionado no textfield2
		        int i = arg0.getKeyCode();
		        if ((i == 10) && !textFieldValRec.getText().equals("")) {
		            pressionado = true;
		            textFieldTroco.setText("" + d.format((Double.parseDouble(textFieldValRec.getText()) - total)));
		        }
			}
		});
		btnEncerrar.addActionListener(new ActionListener() {
			


			public void actionPerformed(ActionEvent arg0) {
				Dinheiro d;

		        //Verifica se os campos foram devidamente preenchidos
		        if (textFieldValRec.getText().equals("")) {
		            JOptionPane.showMessageDialog(JFpagtoDinheiro.this, "Preencha o campo!", "Erro", JOptionPane.ERROR_MESSAGE);
		        } else if (!pressionado) {
		            JOptionPane.showMessageDialog(JFpagtoDinheiro.this, "Pressiona a tecla Enter para realizar calculo do troco!", "Erro", JOptionPane.ERROR_MESSAGE);
		        } else {
		            try {
		                if (Double.parseDouble(textFieldValRec.getText()) <= 0) {
		                            JOptionPane.showMessageDialog(null, "Digite um valor valido!", "Entrada invalida", JOptionPane.ERROR_MESSAGE);
		                            textFieldValRec.setText("");
		                            return;
		                        }
		                d = new Dinheiro(total, Double.parseDouble(textFieldValRec.getText()));
		            } catch (NumberFormatException n) {
		                JOptionPane.showMessageDialog(null, "Digite um valor valido!", "Entrada invalida", JOptionPane.ERROR_MESSAGE);
		                textFieldValRec.setText("");
		                return;
		            }

		            //Calcula a gorjeta do garçom
		            for (Garcom g : CadGarcom.getGarcons()) {
		                if (g == c.getGarcons()) {
		                    g.setTotalGorjeta(total - subtotal);
		                }
		            }
		            //For-each varre a LinkedList produtos para verificar se o mesmo esta cadastrado
		            for (Produto produto_na_pedido : c.retornaProdutos()) {

		                for (Produto produto_cadastrado : CadProduto.getProdutos()) {

		                    if (produto_na_pedido == produto_cadastrado) {
		                        int quantidade_de_produtos_vendidos = c.getQtde(indice_do_produto_no_pedido);
		                        produto_cadastrado.atualizaQuantidadeVendida(quantidade_de_produtos_vendidos);
		                    }

		                }

		                indice_do_produto_no_pedido++;

		            }

		            //Seta os dados da pedido encerrada
		            Calendar hora_temp = Calendar.getInstance();
		            String hora = "" + hora_temp.get(Calendar.HOUR_OF_DAY) + ":" + hora_temp.get(Calendar.MINUTE) + ":" + hora_temp.get(Calendar.SECOND);
		            double temp = CoordPedido.retornaUmPedido(indice_caixa, indice_pedido).calculaValorPedido();
		            temp = temp + temp * 0.1;
		            CoordCaixa.retornaUmCaixa(indice_caixa).atualizaTotalCaixa(temp);
		            c.setPagamento(d);
		            c.setHora(hora);
		            c.setPedidoAberta(false);

		            JOptionPane.showMessageDialog(JFpagtoDinheiro.this, "pedido finalizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

		            dispose();

		        }
			}
		});
		btnEncerrar.setBounds(14, 180, 89, 23);
		contentPane.add(btnEncerrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(124, 180, 89, 23);
		contentPane.add(btnCancelar);
	}
	/**
	 * Metodo para inicar a com as informações referente aos valores do pedido
	 */
	public void iniciar() {

        c = CoordPedido.retornaUmPedido(indice_caixa, indice_pedido);
        subtotal = c.calculaValorPedido();
        total = subtotal + subtotal * 0.1;

        textFieldTotal.setText("" + total);

    }
}
