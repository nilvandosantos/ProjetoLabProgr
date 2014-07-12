package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import codigo.Garcom;
import control.CadGarcon;
import control.Validacao;

public class JFcadastroGarcon extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFcadastroGarcon frame = new JFcadastroGarcon();
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
	public JFcadastroGarcon() {

		JLabel lblNome = new JLabel("Nome: ");
		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");

		setResizable(false);
		setBackground(new Color(255, 255, 255));
		setTitle("Cadastro Garçon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Panel.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getRootPane().setDefaultButton(btnSalvar);

		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNome.setBounds(26, 27, 46, 14);
		contentPane.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(76, 24, 331, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSalvarActionPerformed(arg0);
			}

			private void btnSalvarActionPerformed(ActionEvent arg0) {
				String nome = textFieldNome.getText();
				CadGarcon cadastro = new CadGarcon();
				Garcom novo = new Garcom(nome);
				Validacao valida = new Validacao();

				if (valida.valida(nome) == true) {
					// cadastro.iniciarGarcom();
					if (!cadastro.isExistGarcon(nome)) {

						cadastro.adicionaGarcom(novo);
						cadastro.gravarGarcom();
						JOptionPane.showMessageDialog(
								null,
								"Garçom cadastrado com sucesso!\n\nNome: "
										+ nome + "\nCódigo: "
										+ novo.getCodigo() + "Sucesso");

						dispose();
					}else{
						 JOptionPane.showMessageDialog(JFcadastroGarcon.this, "Já possui um cadastro com o nome " + nome + "!\nFavor digite um nome diferente.");
		                 textFieldNome.setText("");
					}
						
				} else {
					JOptionPane.showMessageDialog(null,
							"Preenchimento Obrigatorio");
				}
			}
		});

		btnSalvar.setBounds(110, 72, 110, 23);
		contentPane.add(btnSalvar);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCancelarActionPerformed(arg0);
			}

			private void btnCancelarActionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(261, 72, 110, 23);
		contentPane.add(btnCancelar);
	}
}
