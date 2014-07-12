package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import control.CadProduto;
import control.Validacao;

import codigo.Produto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFcadastroProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldPreco;
	private JTextField textFieldDescrio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFcadastroProduto frame = new JFcadastroProduto();
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
	public JFcadastroProduto() {
		JLabel lblNome = new JLabel("Nome:");
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");

		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");

		setTitle("Cadastro De Produtos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getRootPane().setDefaultButton(btnSalvar);

		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNome.setBounds(10, 37, 46, 14);
		contentPane.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(84, 34, 326, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		lblPreo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPreo.setBounds(10, 88, 46, 14);
		contentPane.add(lblPreo);

		textFieldPreco = new JTextField();
		textFieldPreco.setBounds(84, 85, 86, 20);
		contentPane.add(textFieldPreco);
		textFieldPreco.setColumns(10);

		lblDescrio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrio.setBounds(10, 132, 70, 14);
		contentPane.add(lblDescrio);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSalvarActionPerformed(arg0);
			}

			private void btnSalvarActionPerformed(ActionEvent arg0) {
				
				String nome = textFieldNome.getText();
				CadProduto cadastro = new CadProduto();
				Produto novo = new Produto(textFieldNome.getText(), Double
						.parseDouble(textFieldPreco.getText()),
						textFieldDescrio.getText());
				Validacao valida = new Validacao();

				if (valida.valida(textFieldNome.getText(),
						textFieldPreco.getText(), textFieldDescrio.getText()) == true) {

					if (!cadastro.isExistProduto(nome)) {
						
						cadastro.iniciarProduto();
						cadastro.adicionaProduto(novo);
						cadastro.gravarProduto();
						dispose();
					}else{
						JOptionPane.showMessageDialog(JFcadastroProduto.this, "J� possui um cadastro com o nome " + nome + "!\nFavor digite um nome diferente.");
		                 textFieldNome.setText("");
		                 textFieldPreco.setText("");
		                 textFieldDescrio.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Preenchimento Obrigatorio");
				}
			}
		});
		btnSalvar.setBounds(84, 210, 114, 23);
		contentPane.add(btnSalvar);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCancelarActionPerformed(arg0);
			}

			private void btnCancelarActionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(236, 210, 114, 23);
		contentPane.add(btnCancelar);

		textFieldDescrio = new JTextField();
		textFieldDescrio.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldDescrio.setBounds(84, 130, 326, 49);
		contentPane.add(textFieldDescrio);
		textFieldDescrio.setColumns(10);
	}
}