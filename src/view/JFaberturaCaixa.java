package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

import codigo.Caixa;
import control.CoordCaixa;
import control.Validacao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFaberturaCaixa extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFaberturaCaixa frame = new JFaberturaCaixa();
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
	public JFaberturaCaixa() {
		
		JLabel lblValorAtualmenteNo = new JLabel("Valor Atualmente no Caixa:");
		JButton btnAbrirCaixa = new JButton("Abrir Caixa");
		JButton btnCancelar = new JButton("Cancelar");
		
		setResizable(false);
		setTitle("Abertura Do Caixa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getRootPane().setDefaultButton(btnAbrirCaixa);

		lblValorAtualmenteNo.setBounds(35, 42, 158, 14);
		contentPane.add(lblValorAtualmenteNo);

		textFieldValor = new JTextField();
		textFieldValor.setBounds(203, 39, 154, 20);
		contentPane.add(textFieldValor);
		textFieldValor.setColumns(10);

		btnAbrirCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String valor = textFieldValor.getText();
				double valorInicial;

				CoordCaixa caixa = new CoordCaixa();
				Caixa novoCaixa;
				Validacao valida = new Validacao();

				if (valida.valida(valor) == true) {

					if (!caixa.isCaixaAberto()) {

						try {
							if (Double.parseDouble(valor) < 0) {
								JOptionPane.showMessageDialog(null,
										"Digite um valor válido!",
										"Entrada inválida",
										JOptionPane.ERROR_MESSAGE);
								textFieldValor.setText("");
								return;
							}

							valorInicial = Double.parseDouble(valor);
						} catch (NumberFormatException n) {
							JOptionPane.showMessageDialog(null,
									"Digite um valor válido!",
									"Entrada inválida",
									JOptionPane.ERROR_MESSAGE);
							textFieldValor.setText("");
							return;
						}

						// Trecho que adiciona um novo caixa caso esteja tudo
						// certo
						novoCaixa = new Caixa(valorInicial, caixa.getData());
						textFieldValor.setText("");
						caixa.addCaixa(novoCaixa);

						JOptionPane.showMessageDialog(null, "Caixa aberto!\n"
								+ "Fundo de caixa: " + valorInicial
								+ "\nData de hoje: " + caixa.getData(), "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								"Caixa já foi aberto hoje!", "Erro",
								JOptionPane.ERROR_MESSAGE);
						textFieldValor.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Digite um valor para abrir o caixa", "Erro",
							JOptionPane.ERROR_MESSAGE);
					textFieldValor.setText("");
				}
			}
		});

		btnAbrirCaixa.setBounds(35, 90, 129, 23);
		contentPane.add(btnAbrirCaixa);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(228, 90, 129, 23);
		contentPane.add(btnCancelar);

	}
}
