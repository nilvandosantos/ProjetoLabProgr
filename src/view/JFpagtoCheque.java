package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import control.CadGarcom;
import control.CadProduto;
import control.CoordCaixa;
import control.CoordPedido;

import codigo.Cheque;
import codigo.Garcom;
import codigo.Pedido;
import codigo.Produto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;

public class JFpagtoCheque extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTotal;
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAno;

	private double total;
	private double subtotal;
	private Pedido c;
	private int indice_do_produto_na_comanda;
	private JFencerraPedido jFa_ser_fechado1;
	private JFpedidoEncerrado jFa_ser_fechado2;
	private int indice_caixa;
	private int indice_comanda;
	
	/**
	 * Create the frame.
	 * @param indice_comanda 
	 * @param indice_caixa 
	 * @param jFpedidoEncerrado 
	 * @param jFa_ser_fechado1 
	 */
	public JFpagtoCheque(JFencerraPedido jFa_ser_fechado1, JFpedidoEncerrado jFpedidoEncerrado, int indice_cai, int indice_ped) {
		this.jFa_ser_fechado1 = jFa_ser_fechado1;
        this.jFa_ser_fechado2 = jFpedidoEncerrado;
        indice_caixa = indice_cai;
        indice_comanda = indice_ped;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 258, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTotal.setBounds(33, 25, 66, 22);
		contentPane.add(lblTotal);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(87, 26, 131, 20);
		contentPane.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		JLabel lblDataPara = new JLabel("Data para saque do Cheque:");
		lblDataPara.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataPara.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDataPara.setBounds(10, 58, 222, 33);
		contentPane.add(lblDataPara);
		
		txtDia = new JTextField();
		txtDia.setHorizontalAlignment(SwingConstants.CENTER);
		txtDia.setText("Dia");
		txtDia.setBounds(10, 89, 39, 20);
		contentPane.add(txtDia);
		txtDia.setColumns(10);
		
		JLabel label = new JLabel("/");
		label.setBounds(59, 92, 46, 14);
		contentPane.add(label);
		
		txtMes = new JTextField();
		txtMes.setHorizontalAlignment(SwingConstants.CENTER);
		txtMes.setText("Mes");
		txtMes.setBounds(69, 89, 46, 20);
		contentPane.add(txtMes);
		txtMes.setColumns(10);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(122, 92, 46, 14);
		contentPane.add(label_1);
		
		txtAno = new JTextField();
		txtAno.setHorizontalAlignment(SwingConstants.CENTER);
		txtAno.setText("Ano");
		txtAno.setBounds(132, 89, 86, 20);
		contentPane.add(txtAno);
		txtAno.setColumns(10);
		
		iniciar();
		
		JButton btnEncerrar = new JButton("Encerrar");
		btnEncerrar.addActionListener(new ActionListener() {
			

			

			

			public void actionPerformed(ActionEvent arg0) {
				 boolean ok = true;

			        //Verifica se os campos foram devidamente preenchidos
			        if (txtDia.getText().equals("") || txtMes.getText().equals("") || txtAno.getText().equals("")) {
			            JOptionPane.showMessageDialog(JFpagtoCheque.this, "Preencha os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
			        } else {
			            while (ok) {
			                try {
			                    if (Integer.parseInt(txtDia.getText()) <= 0 || Integer.parseInt(txtMes.getText()) <= 0 || Integer.parseInt(txtAno.getText()) <= 0) {
			                        JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
			                        txtDia.setText("");
			                        txtMes.setText("");
			                        txtAno.setText("");
			                        return;
			                    }
			                    Integer.parseInt(txtDia.getText());
			                    Integer.parseInt(txtMes.getText());
			                    Integer.parseInt(txtAno.getText());
			                    ok = false;
			                } catch (NumberFormatException n) {
			                    JOptionPane.showMessageDialog(null, "Digite a data em um formato válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
			                    txtDia.setText("");
		                        txtMes.setText("");
		                        txtAno.setText("");
			                    ok = true;
			                    return;
			                }
			            }
			            String data = "" + txtDia.getText() + "/" + txtMes.getText() + "/" + txtAno.getText();
			            Cheque d = new Cheque(total, total, data);

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

			            //Seta os dados da comanda encerrada
			            Calendar hora_temp = Calendar.getInstance();
			            String hora = "" + hora_temp.get(Calendar.HOUR_OF_DAY) + ":" + hora_temp.get(Calendar.MINUTE) + ":" + hora_temp.get(Calendar.SECOND);
			            double temp = CoordPedido.retornaUmPedido(indice_caixa, indice_comanda).calculaValorPedido();
			            temp = temp + temp * 0.1;
			            CoordCaixa.retornaUmCaixa(indice_caixa).atualizaTotalCaixa(temp);
			            c.setPagamento(d);
			            c.setHora(hora);
			            c.setPedidoAberta(false);

			            JOptionPane.showMessageDialog(JFpagtoCheque.this, "Comanda finalizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			        }
			        dispose();
			}
		});
		btnEncerrar.setBounds(10, 131, 89, 23);
		contentPane.add(btnEncerrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(143, 131, 89, 23);
		contentPane.add(btnCancelar);
	}
	 public void iniciar() {

	        c = CoordPedido.retornaUmPedido(indice_caixa, indice_comanda);
	        subtotal = c.calculaValorPedido();
	        total = subtotal + subtotal * 0.1;

	        textFieldTotal.setText("" + total);

	    }
}
