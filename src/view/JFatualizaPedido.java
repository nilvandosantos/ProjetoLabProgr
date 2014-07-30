package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import control.CadProduto;
import control.CoordCaixa;
import control.CoordPedido;

import codigo.Caixa;
import codigo.Pedido;
import codigo.Produto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFatualizaPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodPedido;
	private JTextField textFieldProduto;
	private JTextField textFieldQtd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFatualizaPedido frame = new JFatualizaPedido();
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
	public JFatualizaPedido() {
		setTitle("Atualiza o Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 299, 191);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCodigoDoPedido = new JLabel("Codigo do pedido:");
		lblCodigoDoPedido.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCodigoDoPedido.setBounds(10, 25, 107, 14);
		contentPane.add(lblCodigoDoPedido);

		textFieldCodPedido = new JTextField();
		textFieldCodPedido.setBounds(166, 22, 107, 20);
		contentPane.add(textFieldCodPedido);
		textFieldCodPedido.setColumns(10);

		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblProduto.setBounds(10, 50, 64, 14);
		contentPane.add(lblProduto);

		textFieldProduto = new JTextField();
		textFieldProduto.setBounds(166, 47, 107, 20);
		contentPane.add(textFieldProduto);
		textFieldProduto.setColumns(10);

		textFieldQtd = new JTextField();
		textFieldQtd.setBounds(166, 72, 107, 20);
		contentPane.add(textFieldQtd);
		textFieldQtd.setColumns(10);

		JLabel lblQtd = new JLabel("Quantidade");
		lblQtd.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQtd.setBounds(10, 75, 86, 14);
		contentPane.add(lblQtd);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int indice_caixa = 0;
				int indice_comanda = 0;

				boolean achou = false;
				// Verifica se todos os campos estão preenchidos
				if (textFieldCodPedido.getText().equals("")
						|| textFieldProduto.getText().equals("")
						|| textFieldQtd.getText().equals("")) {
					JOptionPane.showMessageDialog(JFatualizaPedido.this,
							"Preencha os campos!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {

					// For-each para varrer a LinkedList de Caixa
					for (Caixa c : CoordCaixa.retornaCaixas()) {

						// For-each para varrer a LinkedList de Comanda dentro
						// de um objeto da LinkedList caixa em busca da comanda
						// com ID fornecido
						for (Pedido d : CoordPedido.retornaPedido(indice_caixa)) {

							// Verifica se a comanda tem o indice fornecido pelo
							// usuário
							try {
								if (Integer.parseInt(textFieldCodPedido
										.getText()) <= 0) {
									JOptionPane.showMessageDialog(null,
											"Digite um valor válido!",
											"Entrada inválida",
											JOptionPane.ERROR_MESSAGE);
									textFieldCodPedido.setText("");
									return;
								}
								if (d.getNumero().equals(
										Integer.parseInt(textFieldCodPedido
												.getText()))
										&& d.getComandaAberta()) {
									achou = true;
									break;
								}
							} catch (NumberFormatException n) {
								JOptionPane
										.showMessageDialog(
												null,
												"Digite um código de comanda válido!",
												"Entrada inválida",
												JOptionPane.ERROR_MESSAGE);
								textFieldCodPedido.setText("");
								return;
							}

							indice_comanda++;

						}

						if (achou) {
							break;
						}

						indice_caixa++;

					}

					// Se achou a comanda com ID fornecido
					if (achou) {

						Pedido comanda_alterar = CoordPedido.retornaUmPedido(
								indice_caixa, indice_comanda);
						int indice_do_produto = 0;
						boolean achou_produto = false;

						// Varre a LinkedList de produtos para verificar se o
						// produto já esta na comanda
						for (Produto p : comanda_alterar.retornaProdutos()) {

							// Verifica se o produto já está na comanda
							try {
								if (Integer.parseInt(textFieldProduto.getText()) <= 0) {
									JOptionPane.showMessageDialog(null,
											"Digite um valor válido!",
											"Entrada inválida",
											JOptionPane.ERROR_MESSAGE);
									textFieldProduto.setText("");
									return;
								}
								if (p.getCodigo() == Integer
										.parseInt(textFieldProduto.getText())) {
									achou_produto = true;
									break;
								}
							} catch (NumberFormatException n) {
								JOptionPane
										.showMessageDialog(
												null,
												"Digite um código de produto válido!",
												"Entrada inválida",
												JOptionPane.ERROR_MESSAGE);
								textFieldProduto.setText("");
								return;
							}

							indice_do_produto++;

						}

						// Se achou o produto na comanda, entra aqui
						if (achou_produto) {

							Integer quantidade = 0;
							quantidade = comanda_alterar
									.getQtde(indice_do_produto);
							try {
								if (Integer.parseInt(textFieldQtd.getText()) < 0) {
									JOptionPane.showMessageDialog(null,
											"Digite um valor v�lido!",
											"Entrada inválida",
											JOptionPane.ERROR_MESSAGE);
									textFieldQtd.setText("");
									return;
								}
								quantidade = quantidade
										+ Integer.parseInt(textFieldQtd
												.getText());
							} catch (NumberFormatException n) {
								JOptionPane.showMessageDialog(null,
										"Digite uma quantidade v�lida!",
										"Entrada inválida",
										JOptionPane.ERROR_MESSAGE);
								textFieldQtd.setText("");
								return;
							}
							comanda_alterar.alteraQtde(indice_do_produto,
									quantidade);

							textFieldCodPedido.setText("");
							textFieldProduto.setText("");
							textFieldQtd.setText("");
							JOptionPane.showMessageDialog(
									JFatualizaPedido.this,
									"Produto atualizado.\nComanda ["
											+ comanda_alterar.getNumero()
											+ "] atualizada com sucesso!",
									"Sucesso", JOptionPane.INFORMATION_MESSAGE);

							// Se o produto ainda não está na comanda, será
							// adicionado
						} else {

							Produto produto_a_ser_adicionado = new Produto();
							Integer quantidade;
							int produto = 0;
							int indice2 = 0;
							boolean achou_produto2 = false;

							// Varre a LinkedList de produtos
							for (Produto a : CadProduto.getProdutos()) {

								// Verifica se o produto com código fornecido
								// pelo usuário está na LinkeList de produtos
								try {
									if (Integer.parseInt(textFieldProduto
											.getText()) <= 0) {
										JOptionPane.showMessageDialog(null,
												"Digite um valor v�lido!",
												"Entrada inv�lida",
												JOptionPane.ERROR_MESSAGE);
										textFieldProduto.setText("");
										return;
									}
									if (a.getCodigo() == Integer
											.parseInt(textFieldProduto
													.getText())) {
										achou_produto2 = true;
										produto_a_ser_adicionado = a;
										break;
									}
								} catch (NumberFormatException n) {
									JOptionPane.showMessageDialog(null,
											"Digite um c�digo v�lido!",
											"Entrada inv�lida",
											JOptionPane.ERROR_MESSAGE);
									textFieldProduto.setText("");
									return;
								}

								indice2++;

							}

							// Se achou o produto na LinkedList de produtos,
							// adiciona ele na comanda
							if (achou_produto2) {

								comanda_alterar.adicionaProduto(
										produto_a_ser_adicionado, Integer
												.parseInt(textFieldQtd
														.getText()));
								textFieldCodPedido.setText("");
								textFieldProduto.setText("");
								textFieldQtd.setText("");
								JOptionPane.showMessageDialog(
										JFatualizaPedido.this,
										"Produto adicionado.\nComanda ["
												+ comanda_alterar.getNumero()
												+ "] atualizada com sucesso!",
										"Sucesso",
										JOptionPane.INFORMATION_MESSAGE);

								// Se não, exibe uma mensagem de erro
							} else {

								textFieldCodPedido.setText("");
								textFieldProduto.setText("");
								textFieldQtd.setText("");
								JOptionPane.showMessageDialog(
										JFatualizaPedido.this,
										"Produto não encontrado!", "Erro",
										JOptionPane.ERROR_MESSAGE);

							}

						}// Fecha o else para produto não encontrado na comanda

						// Else para comanda não econtrada
					} else {

						JOptionPane.showMessageDialog(JFatualizaPedido.this,
								"Comanda não encontrada!", "Erro",
								JOptionPane.ERROR_MESSAGE);

					}

				}// Fecha else para campos preenchidos
			}
		});
		btnAtualizar.setBounds(10, 112, 107, 23);
		contentPane.add(btnAtualizar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(158, 112, 107, 23);
		contentPane.add(btnCancelar);
	}
}
