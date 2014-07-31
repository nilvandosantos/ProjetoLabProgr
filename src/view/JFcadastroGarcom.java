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

/**
 * Esta classe tem como objetivo criar a interface grafica para cadastro o garcom.
 *. 
 *@author Marco Lucas,Nayara,Nilvando.
 *@version 1.0
 *  
 */
public class JFcadastroGarcom extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;

/**
 * Este metodo pertence a criação da tela cadastroGarcom
 */
	public JFcadastroGarcom() {
		
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
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNome.setBounds(26, 27, 46, 14);
		contentPane.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(76, 24, 331, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		btnSalvar.addActionListener(new ActionListener() {
			//EVENTO DO BOTAO SALVAR
			public void actionPerformed(ActionEvent arg0) {
				btnSalvarActionPerformed(arg0);
			}
			
			private void btnSalvarActionPerformed(ActionEvent arg0) {
				String nome = textFieldNome.getText();
				CadGarcom cadastro = new CadGarcom();
				Garcom novo = new Garcom(nome);
				Validacao valida = new Validacao();
	            //TODOS OS CAMPOS EM BRANCO DA TELA PREENCHIDOS,É CRIADO UM NOVO GARÇOM 
				if (valida.valida(nome) == true) {
					cadastro.adicionaGarcom(novo);
					cadastro.gravarGarcom();
					JOptionPane.showMessageDialog(null,
							"Garçom cadastrado com sucesso!\n\nNome: " + nome
									+ "\nCódigo: " + novo.getCodigo()
									+ "\nSucesso");

					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Preenchimento Obrigatorio");
				}
			}
		});

		btnSalvar.setBounds(110, 72, 110, 23);
		contentPane.add(btnSalvar);

		btnCancelar.addActionListener(new ActionListener() {
			//EVENTO DO BOTAO CANCELAR.
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
