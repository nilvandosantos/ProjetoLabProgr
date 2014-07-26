package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;

import codigo.Garcom;

import control.CadGarcom;
import control.Validacao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFcadastroGarcom extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFcadastroGarcom frame = new JFcadastroGarcom();
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
	public JFcadastroGarcom() {
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

		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNome.setBounds(26, 27, 46, 14);
		contentPane.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(76, 24, 331, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSalvarActionPerformed(arg0);
			}

			private void btnSalvarActionPerformed(ActionEvent arg0) {
				String nome = textFieldNome.getText();
				CadGarcom cadastro = new CadGarcom();
				Garcom novo = new Garcom(nome);
				Validacao valida = new Validacao();

				if (valida.valida(nome) == true) {
					//cadastro.iniciarGarcom();
					cadastro.adicionaGarcom(novo);
					cadastro.gravarGarcom();
					JOptionPane.showMessageDialog(null,
							"Garçom cadastrado com sucesso!\n\nNome: " + nome
									+ "\nCódigo: " + novo.getCodigo()
									+ "Sucesso");

					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Preenchimento Obrigatorio");
				}
			}
		});

		btnSalvar.setBounds(110, 72, 110, 23);
		contentPane.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
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
