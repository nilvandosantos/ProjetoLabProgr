package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import control.CadGarcom;

import codigo.Garcom;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFgorjeta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFgorjeta frame = new JFgorjeta();
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
	public JFgorjeta() {
		setResizable(false);
		setTitle("Gorjeta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 235, 149);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigoDoGarom = new JLabel("Codigo do Gar\u00E7om");
		lblCodigoDoGarom.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCodigoDoGarom.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoDoGarom.setBounds(10, 11, 209, 30);
		contentPane.add(lblCodigoDoGarom);
		
		textField = new JTextField();
		textField.setBounds(66, 40, 102, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int indice_garcom = 0;
		        boolean achou_garcom = false;

		        //Confere se os campos foram devidamente preenchidos
		        if (textField.getText().equals("")) {
		            JOptionPane.showMessageDialog(JFgorjeta.this, "Preencha o campo!", "Erro", JOptionPane.ERROR_MESSAGE);
		        } else {

		            for (Garcom g : CadGarcom.getGarcons()) {
		                try {
		                    if (Integer.parseInt(textField.getText()) <= 0) {
		                            JOptionPane.showMessageDialog(JFgorjeta.this, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
		                            textField.setText("");
		                            return;
		                        }
		                    if (g.getCodigo() == Integer.parseInt(textField.getText())) {
		                        achou_garcom = true;
		                        break;
		                    }
		                } catch (NumberFormatException n) {
		                    JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
		                    textField.setText("");
		                    return;
		                }
		                indice_garcom++;

		            }

		            //Verifica se o garçom foi ou não encontrado.
		            //Em caso positivo, retorna o total de gorjetas dele.
		            if (achou_garcom) {
		            	textField.setText("");
		                JOptionPane.showMessageDialog(JFgorjeta.this, "" + CadGarcom.retornaUmGarcom(indice_garcom).toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);

		            } else {
		            	textField.setText("");
		                JOptionPane.showMessageDialog(JFgorjeta.this, "Gar�om n�o encontrado!", "Erro", JOptionPane.INFORMATION_MESSAGE);
		            }

		        }
			}
		});
		btnOk.setBounds(10, 71, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(130, 71, 89, 23);
		contentPane.add(btnCancelar);
	}
}
