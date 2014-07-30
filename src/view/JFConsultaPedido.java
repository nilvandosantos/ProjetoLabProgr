package view;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import control.CadGarcom;
import control.CoordCaixa;
import control.CoordPedido;

import codigo.Caixa;
import codigo.Garcom;
import codigo.Pedido;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Calendar;
import java.util.LinkedList;

public class JFConsultaPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodGarcom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFConsultaPedido frame = new JFConsultaPedido();
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
	public JFConsultaPedido() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 356, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCdigoDoGarom = new JLabel("C\u00F3digo do Gar\u00E7om:");
		lblCdigoDoGarom.setBounds(10, 34, 127, 14);
		contentPane.add(lblCdigoDoGarom);
		
		textFieldCodGarcom = new JTextField();
		textFieldCodGarcom.setBounds(158, 31, 106, 20);
		contentPane.add(textFieldCodGarcom);
		textFieldCodGarcom.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 //Verifica se o campo está preenchido
		        if (textFieldCodGarcom.getText().equals("")) {

		            JOptionPane.showMessageDialog(JFConsultaPedido.this, "Preencha o campo!", "Erro", JOptionPane.ERROR_MESSAGE);

		        } else {



		            //Variáveis necessárias para o funcionamento do algoritmo
		            Calendar calendario_temporario = Calendar.getInstance();
		            String data_temporaria = "" + calendario_temporario.get(Calendar.DAY_OF_MONTH) + "/" + (calendario_temporario.get(Calendar.MONTH) + 1) + "/" + calendario_temporario.get(Calendar.YEAR);
		            int indice_de_caixa = 0;
		            int indice_de_comandas = 0;
		            boolean achou = false;
		            boolean achou_garcon = false;
		            boolean achou_comanda = false;
		            LinkedList<Integer> lista_de_comandas = new LinkedList<Integer>();

		            //Varre a LinkedLista "caixas" em busca de caixas com a data do dia corrente
		            for (Caixa c : CoordCaixa.retornaCaixas()) {

		                //Verifica se existe algum caixa aberto com a data de hoje
		                if (data_temporaria.equals(c.getData())) {
		                    achou = true;
		                    break;

		                }

		                //Váriavel para armazenar o índice do caixa
		                indice_de_caixa++;
		            }

		            //Se achou um caixa com dia de hoje, entra aqui
		            if (achou) {

		                //Varre a LinkedList garçons em busca do garçom com código informado pelo usuário
		                for (Garcom g : CadGarcom.getGarcons()) {
		                    //Verifica se existe algum garçom cadastrado com código fornecido
		                    try {
		                        if (Integer.parseInt(textFieldCodGarcom.getText()) <= 0) {
		                            JOptionPane.showMessageDialog(null, "Digite um valor v�lido!", "Entrada inv�lida", JOptionPane.ERROR_MESSAGE);
		                            textFieldCodGarcom.setText("");
		                            return;
		                        }
		                        if (g.getCodigo() == Integer.parseInt(textFieldCodGarcom.getText())) {
		                            achou_garcon = true;
		                            break;
		                        }
		                    } catch (NumberFormatException n) {
		                        JOptionPane.showMessageDialog(null, "Digite um valor v�lido!", "Entrada invlida", JOptionPane.ERROR_MESSAGE);
		                        textFieldCodGarcom.setText("");
		                        return;

		                    }
		                }

		                //Se achou um garçom com ID informada, entra aqui
		                if (achou_garcon) {

		                    //For-each para varrer a LinkedList de comandas na LinkedList do indice achado no primeiro for
		                    for (Pedido d : CoordPedido.retornaPedido(indice_de_caixa)) {

		                        //Verifica as comandas que estão com o índice do garçon                        
		                        try {
		                            if (Integer.parseInt(textFieldCodGarcom.getText()) <= 0) {
		                            JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
		                            textFieldCodGarcom.setText("");
		                            return;
		                        }
		                            if (d.getCodigoGarcon() == Integer.parseInt(textFieldCodGarcom.getText()) && d.getComandaAberta()) {

		                                //Adiciona o índice das comandas que estão em nome do garçon em uma LinkedList de integer
		                                lista_de_comandas.add(indice_de_comandas);
		                                achou_comanda = true;

		                            }
		                        } catch (NumberFormatException n) {
		                            JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
		                            textFieldCodGarcom.setText("");
		                            return;
		                        }
		                        indice_de_comandas++;
		                    }

		                    //Limpar o campo de texto
		                    textFieldCodGarcom.setText("");

		                    //Se achou alguma comanda em nome do garçon
		                    if (achou_comanda) {

		                        //For-each para exibir em JOption todas as comandas em nome do garçon
		                        for (Integer a : lista_de_comandas) {

		                            JOptionPane.showMessageDialog(JFConsultaPedido.this, CoordPedido.retornaUmPedido(indice_de_caixa, a).toString(), "Comanda", JOptionPane.INFORMATION_MESSAGE);

		                        }

		                        JFConsultaPedido.this.setVisible(false);
		                        //Else para nenhuma Comanda encontrada em nome do garçom   
		                    } else {

		                        JOptionPane.showMessageDialog(JFConsultaPedido.this, "Não existe nenhuma comanda em seu nome!", "Ok", JOptionPane.INFORMATION_MESSAGE);
		                    }
		                    //Else para nenhum garçom encontrado
		                } else {
		                    JOptionPane.showMessageDialog(JFConsultaPedido.this, "Não existe nenhum garçom com esse código!", "Erro", JOptionPane.ERROR_MESSAGE);
		                }
		                //Else para nenhum Caixa encontrado na LinkedList caixas com a data de hoje    
		            } else {
		                JOptionPane.showMessageDialog(JFConsultaPedido.this, "O caixa de hoje ainda não foi aberto!", "Erro", JOptionPane.ERROR_MESSAGE);
		            }

		        }
			}
		});
		btnConsultar.setBounds(10, 86, 112, 23);
		contentPane.add(btnConsultar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(217, 86, 112, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAjuda = new JButton("?");
		btnAjuda.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				CadGarcom ajuda = new CadGarcom();
                JOptionPane.showMessageDialog(null, "" + ajuda.ajuda(), "Ok", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnAjuda.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAjuda.setBounds(285, 30, 44, 23);
		contentPane.add(btnAjuda);
	}
}
