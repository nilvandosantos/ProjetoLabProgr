package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import codigo.Caixa;
import codigo.Garcom;
import codigo.Pedido;
import codigo.Produto;

import control.CadGarcom;
import control.CadProduto;
import control.CoordCaixa;
import control.CoordPedido;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.LinkedList;

public class JFcadastroPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldQtd;
	private JTextField textFieldCodGarc;
	private JTextField textFieldProd;
	static int codigo_do_pedido_temporario;
	LinkedList<Produto> temporaria_produtos = new LinkedList<Produto>();
	LinkedList<Integer> temporaria_quantidade = new LinkedList<Integer>();

	/**
	 * Esta classe tem o intuito de cadastrar os pedidos.
	 * 
	 */
	public JFcadastroPedido() {
		
		JButton btnCadastrar = new JButton("Cadastrar");
		JButton btnCancelar = new JButton("Cancelar");
		
		setTitle("Novo Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getRootPane().setDefaultButton(btnCadastrar);
		
		JLabel lblDigiteOCdigo = new JLabel(
				"Digite o C\u00F3digo do Gar\u00E7on:");
		lblDigiteOCdigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDigiteOCdigo.setBounds(23, 11, 194, 20);
		contentPane.add(lblDigiteOCdigo);

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int codigo_do_garcom;
				if (textFieldCodGarc.getText().equals("")
						|| textFieldProd.getText().equals("")
						|| textFieldQtd.getText().equals("")) {
					JOptionPane.showMessageDialog(JFcadastroPedido.this,
							"Preencha o campo!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						if (Integer.parseInt(textFieldCodGarc.getText()) < 0
								|| Integer.parseInt(textFieldProd.getText()) < 0
								|| Integer.parseInt(textFieldQtd.getText()) < 0) {
							JOptionPane.showMessageDialog(null,
									"Digite um valor válido!",
									"Entrada inválida",
									JOptionPane.ERROR_MESSAGE);
							textFieldCodGarc.setText("");
							textFieldProd.setText("");
							textFieldQtd.setText("");
							return;
						}
						codigo_do_garcom = Integer.parseInt(textFieldCodGarc
								.getText());

					} catch (NumberFormatException n) {
						JOptionPane.showMessageDialog(null,
								"Digite um valor vÃ¡lido!",
								"Entrada invÃ¡lida", JOptionPane.ERROR_MESSAGE);
						textFieldCodGarc.setText("");
						textFieldProd.setText("");
						textFieldQtd.setText("");
						return;
					}
					Garcom garcom_temporario = new Garcom();
					Produto Produto_temporario = new Produto();

					// Variaveis para armazear valores se a busca por
					// determinado item foi bem sucedidada
					boolean achouGarcom = false;
					boolean achouCaixa = false;
					boolean achouProd = false;
					boolean achou4 = false;

					// For-each para varrer a LinkeList "garcons" em busca do
					// garcons com codigo informado
					for (Garcom a : CadGarcom.getGarcons()) {

						if (a.getCodigo() == codigo_do_garcom) {
							garcom_temporario = a;
							achouGarcom = true;
							break;
						}

					}

					// Variavel no formato String que armazena o valor da data
					// de hoje
					Calendar temp = Calendar.getInstance();
					String data_temp = "" + temp.get(Calendar.DAY_OF_MONTH)
							+ "/" + (temp.get(Calendar.MONTH) + 1) + "/"
							+ temp.get(Calendar.YEAR);

					int indice = 0;

					// For-each para varrer a LinkeList "caixas" em busca de um
					// caixa aberto com data do dia
					for (Caixa c : CoordCaixa.retornaCaixas()) {

						if (c.getData().equals(data_temp)) {
							achouCaixa = true;

							break;

						}
						indice++;
					}
					/* Varre a linkedList de protudos em busca do produto com ID
					 * do textField
					*/
					for (Produto p : CadProduto.getProdutos()) {
						if (p.getCodigo() == Integer.parseInt(textFieldProd
								.getText())) {
							achouProd = true;
							
							Produto_temporario = p;
							break;
						}
					}

					// Se achou um garçom com codigo informado
					if (achouGarcom) {

						// Se já  existe um caixa aberto com a data de hoje
						if (achouCaixa) {

							// Cria um objeto pedido já instanciado com o
							// garçom de codigo informado
							Pedido temporaria = new Pedido(garcom_temporario,
									data_temp);
							// Adiciona ao caixa de indice achado no for-each
							// com o pedido temporario
							codigo_do_pedido_temporario = temporaria
									.getNumero();

							// Adiciona uma pedido ao objeto caixa de indice
							// "indice" achado na LinkeList de caixas
							CoordPedido.adicionaPedido(indice, temporaria);
							/* Varre a linkedlist de pedidos dentro da
                             * linkedlist de caixas do dia corrente
							 * 
							 */
							for (Pedido c : CoordPedido.retornaPedido(indice)) {

								// Verifica a compatibilidade da pedido
								// temporaria
								// com a pedido final
								if (c.getNumero() == codigo_do_pedido_temporario) {
									// Se achou, seta de verdade a pedido
									// temporaria na pedido final
									achou4 = true;
									c.setProdutosEQuantidade(
											temporaria_quantidade,
											temporaria_produtos);
									break;

								}

							}

							if (achou4) {
								// Exibe na tela o sucesso da operação
								JOptionPane.showMessageDialog(
										JFcadastroPedido.this,
										"pedido cadastrado com sucesso!\n\nID do pedido: "
												+ codigo_do_pedido_temporario,
										"Ok", JOptionPane.INFORMATION_MESSAGE);
								/*Else para o caso de nao achar a
								 * compatibilidade  dos pedidos
								 */
							} else {
								JOptionPane.showMessageDialog(
										JFcadastroPedido.this,
										"Erro! Tente novamente", "Erro",
										JOptionPane.ERROR_MESSAGE);
								JFcadastroPedido.this.setVisible(false);
							}

						} else {
							JOptionPane.showMessageDialog(
									JFcadastroPedido.this,
									"O caixa ainda não foi aberto", "Saida",
									JOptionPane.ERROR_MESSAGE);
						}
						if (achouProd) {
							// Adiciona um produto a uma LinkedList de produtos
							// temporarios
							temporaria_produtos.add(Produto_temporario);
							/*Adiciona a quantidade do produto a uma LinkedList
							 * de quantidades temporarias
							 */
							temporaria_quantidade.add(Integer
									.parseInt(textFieldQtd.getText()));
							// Mensagem de confirmaçao
							JOptionPane
									.showMessageDialog(
											JFcadastroPedido.this,
											"Produto adicionado a pedido com sucesso!",
											"Sucesso",
											JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(
									JFcadastroPedido.this,
									"Produto não encontrado!", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(JFcadastroPedido.this,
								"Codigo de garçom não encontrado", "Saida",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			dispose();
			}
			
		});

		btnCadastrar.setBounds(71, 109, 89, 23);
		contentPane.add(btnCadastrar);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(248, 109, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProduto.setBounds(23, 42, 89, 14);
		contentPane.add(lblProduto);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantidade.setBounds(23, 73, 89, 14);
		contentPane.add(lblQuantidade);

		textFieldQtd = new JTextField();
		textFieldQtd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textFieldQtd.setBounds(342, 71, 68, 20);
		contentPane.add(textFieldQtd);
		textFieldQtd.setColumns(10);

		textFieldCodGarc = new JTextField();
		textFieldCodGarc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		textFieldCodGarc.setBounds(227, 13, 183, 20);
		contentPane.add(textFieldCodGarc);
		textFieldCodGarc.setColumns(10);

		textFieldProd = new JTextField();
		textFieldProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textFieldProd.setBounds(342, 40, 68, 20);
		contentPane.add(textFieldProd);
		textFieldProd.setColumns(10);
	}
}
