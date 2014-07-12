package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFAberturaCaixa extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFAberturaCaixa frame = new JFAberturaCaixa();
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
	public JFAberturaCaixa() {
		setResizable(false);
		setTitle("Abertura Do Caixa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblValorAtualmenteNo = new JLabel("Valor Atualmente no Caixa:");
		lblValorAtualmenteNo.setBounds(35, 42, 158, 14);
		contentPane.add(lblValorAtualmenteNo);
		
		textFieldValor = new JTextField();
		textFieldValor.setBounds(203, 39, 154, 20);
		contentPane.add(textFieldValor);
		textFieldValor.setColumns(10);
		
		JButton btnAbrirCaixa = new JButton("Abrir Caixa");
		btnAbrirCaixa.setBounds(35, 90, 129, 23);
		contentPane.add(btnAbrirCaixa);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(228, 90, 129, 23);
		contentPane.add(btnCancelar);
	}
}
