package view;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	static int codigo_da_comanda_temporaria;
	LinkedList<Produto> temporaria_produtos = new LinkedList<Produto>();
    LinkedList<Integer> temporaria_quantidade = new LinkedList<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFcadastroPedido frame = new JFcadastroPedido();
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
	public JFcadastroPedido() {
		setTitle("Novo Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDigiteOCdigo = new JLabel(
				"Digite o C\u00F3digo do Gar\u00E7on:");
		lblDigiteOCdigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDigiteOCdigo.setBounds(23, 11, 194, 20);
		contentPane.add(lblDigiteOCdigo);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int codigo_do_garcom;

				// Confere se o campo foi devidamente preenchido
				if (textFieldCodGarc.getText().equals("")
						|| textFieldQtd.getText().equals("")
						|| textFieldProd.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Preencha todos os campos!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						if (Integer.parseInt(textFieldCodGarc.getText()) <= 0
								|| Integer.parseInt(textFieldProd.getText()) < 0
								|| (Integer.parseInt(textFieldQtd.getText()) <= 0)) {
							JOptionPane.showMessageDialog(null,
									"Digite um valor válido!",
									"Entrada inválida",
									JOptionPane.ERROR_MESSAGE);
							textFieldCodGarc.setText("");
							return;
						}
						codigo_do_garcom = Integer.parseInt(textFieldCodGarc
								.getText());
					} catch (NumberFormatException n) {
						JOptionPane.showMessageDialog(null,
								"Digite um valor válido!",
								"Entrada inválida", JOptionPane.ERROR_MESSAGE);
						textFieldCodGarc.setText("");
						return;
					}

					Garcom garcom_temporario = new Garcom();
					Produto Produto_temporario = new Produto();
					// Váriaveis para armazear valores se a busca por
					// determinado item foi bem sucedidada
					
					boolean achou = false;
					boolean achou1 = false;
					boolean achou2 = false;

					// For-each para varrer a LinkeList "garcons" em busca do
					// garçom com código informado
					for (Garcom a : CadGarcom.getGarcons()) {

						if (a.getCodigo() == codigo_do_garcom) {
							garcom_temporario = a;
							achou1 = true;
							break;
						}

					}
					for (Produto p : CadProduto.getProdutos()) {

		                //Verifca se achou um produto com ID igual
		                //Verifica se os campos foram preenchidos devidamente
		                try {
		                    
		                    if (p.getCodigo() == Integer.parseInt(textFieldProd.getText())) {
		                        achou = true;
		                        //Pega o objeto produto
		                        Produto_temporario = p;
		                        break;
		                    }
		                } catch (NumberFormatException n) {
		                    JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
		                    textFieldProd.setText("");
		                    return;
		                }


		            }

					// Váriavel no formato String que armazena o valor da data
					// de hoje
					Calendar temp = Calendar.getInstance();
					String data_temp = "" + temp.get(Calendar.DAY_OF_MONTH)
							+ "/" + (temp.get(Calendar.MONTH) + 1) + "/"
							+ temp.get(Calendar.YEAR);

					int indice = 0;

					// For-each para varrer a LinkeList "caixas" em busca de um
					// caixa aberto com data do dia
					for (Caixa c : CoordCaixa.getCaixas()) {

						if (c.getData().equals(data_temp)) {
							achou2 = true;
							break;

						}

						indice++;

					}

					// Se achou um garçom com código informado
					if (achou1) {

						// Se já existe um caixa aberto com a data de hoje
						if (achou2) {

							// Cria um objeto comanda já instanciado com o
							// garçom de código informado
							Pedido temporaria = new Pedido(garcom_temporario,
									data_temp);
							// Adiciona ao caixa de indice achado no for-each
							// com a comanda temporaria
							codigo_da_comanda_temporaria = temporaria
									.getNumero();

							// Adiciona uma comanda ao objeto caixa de indice
							// "indice" achado na LinkeList de caixas
							CoordPedido.adicionaPedido(indice, temporaria);

							// new
							// jFcadastraComandaProduto(jFcadastraComanda.this).show();

						} else {
							JOptionPane.showMessageDialog(null,
									"O caixa ainda não foi aberto", "Saída",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Código de garçom não encontrado", "Saída",
								JOptionPane.ERROR_MESSAGE);
					}
					if (achou) {
		                //Adiciona um produto a uma LinkedList de produtos temporarios
		                temporaria_produtos.add(Produto_temporario);
		                //Adiciona a quantidade do produto a uma LinkedList de quantidades temporarias
		                temporaria_quantidade.add(Integer.parseInt(textFieldQtd.getText()));
		                //Limpa os campos                
		                textFieldProd.setText("");
		                textFieldQtd.setText("");
		                //Mensagem de confirmação
		                JOptionPane.showMessageDialog(null, "Produto adicionado a comanda com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
		            }

				}
			}
		});

		btnCadastrar.setBounds(71, 109, 89, 23);
		contentPane.add(btnCadastrar);

		JButton btnCancelar = new JButton("Cancelar");
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
