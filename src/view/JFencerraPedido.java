package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import control.CoordCaixa;
import control.CoordPedido;

import codigo.Caixa;
import codigo.Pedido;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFencerraPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodPed;

	private int indice_caixa;
	private int indice_comanda;
	private boolean aux;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFencerraPedido frame = new JFencerraPedido();
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
	public JFencerraPedido() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 383, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDigiteOCodigo = new JLabel("Digite o codigo do Pedido para encerra-lo:");
		lblDigiteOCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigiteOCodigo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDigiteOCodigo.setBounds(10, 11, 347, 32);
		contentPane.add(lblDigiteOCodigo);
		
		textFieldCodPed = new JTextField();
		textFieldCodPed.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodPed.setBounds(137, 54, 86, 20);
		contentPane.add(textFieldCodPed);
		textFieldCodPed.setColumns(10);
		
		JButton btnEncerrar = new JButton("Encerrar");
		btnEncerrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				 boolean achou = false;
			        indice_caixa = 0;
			        indice_comanda = 0;
			        //Verifica se o campo está preenchido
			        if (textFieldCodPed.getText().equals("")) {

			            JOptionPane.showMessageDialog(JFencerraPedido.this, "Preencha o campo!", "Erro", JOptionPane.ERROR_MESSAGE);

			        } else {

			            aux = true;

			            //For-each para varrer a LinkedList de Caixa           
			            for (Caixa c : CoordCaixa.retornaCaixas()) {

			                //For-each para varrer a LinkedList de Comanda dentro de um objeto da LinkedList caixa em busca da comanda com ID fornecido
			                for (Pedido d : CoordPedido.retornaPedido(indice_caixa)) {

			                    try {
			                        //Verifica se a comanda tem o indice fornecido pelo usuário
			                        if (Integer.parseInt(textFieldCodPed.getText()) <= 0) {
			                            JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
			                            textFieldCodPed.setText("");
			                            return;
			                        }

			                        if (d.getNumero().equals(Integer.parseInt(textFieldCodPed.getText())) && d.getPedidoAberto()) {
			                            achou = true;
			                            break;
			                        }
			                    } catch (NumberFormatException n) {
			                        JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
			                        textFieldCodPed.setText("");
			                        return;
			                    }

			                    indice_comanda++;

			                }

			                if (achou) {
			                    break;
			                }

			                indice_caixa++;
			            }

			            if (achou) {

			                new JFpedidoEncerrado(JFencerraPedido.this, indice_caixa, indice_comanda).setVisible(true);

			            } else {

			                JOptionPane.showMessageDialog(JFencerraPedido.this, "Comanda não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);

			            }

			        }
			}
		});
		btnEncerrar.setBounds(24, 90, 112, 23);
		contentPane.add(btnEncerrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(220, 90, 112, 23);
		contentPane.add(btnCancelar);
	}
}
