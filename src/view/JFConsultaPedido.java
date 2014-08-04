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
	 * Esta classe tem como objetivo criar a interface grafica para consultar pedido.
	 *. 
	 *@author Marco Lucas,Nayara,Nilvando.
	 *@version 1.0
	 *  
	 */
	public JFConsultaPedido() {
		
		JButton btnConsultar = new JButton("Consultar");
		JButton btnCancelar = new JButton("Cancelar");
		JButton btnAjuda = new JButton("?");
		
		setTitle("Consuta Pedido");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 356, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getRootPane().setDefaultButton(btnConsultar);
		
		JLabel lblCdigoDoGarom = new JLabel("C\u00F3digo do Gar\u00E7om:");
		lblCdigoDoGarom.setBounds(10, 34, 127, 14);
		contentPane.add(lblCdigoDoGarom);
		
		textFieldCodGarcom = new JTextField();
		textFieldCodGarcom.setBounds(158, 31, 106, 20);
		contentPane.add(textFieldCodGarcom);
		textFieldCodGarcom.setColumns(10);
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 //Verifica se o campo esta preenchido
		        if (textFieldCodGarcom.getText().equals("")) {

		            JOptionPane.showMessageDialog(JFConsultaPedido.this, "Preencha o campo!", "Erro", JOptionPane.ERROR_MESSAGE);

		        } else {



		            //Variaveis necessarias para o funcionamento do algoritmo
		            Calendar calendario_temporario = Calendar.getInstance();
		            String data_temporaria = "" + calendario_temporario.get(Calendar.DAY_OF_MONTH) + "/" + (calendario_temporario.get(Calendar.MONTH) + 1) + "/" + calendario_temporario.get(Calendar.YEAR);
		            int indice_de_caixa = 0;
		            int indice_de_pedidos = 0;
		            boolean achou = false;
		            boolean achou_garcon = false;
		            boolean achou_pedido = false;
		            LinkedList<Integer> lista_de_pedidos = new LinkedList<Integer>();

		            //Varre a LinkedLista "caixas" em busca de caixas com a data do dia corrente
		            for (Caixa c : CoordCaixa.retornaCaixas()) {

		                //Verifica se existe algum caixa aberto com a data de hoje
		                if (data_temporaria.equals(c.getData())) {
		                    achou = true;
		                    break;

		                }

		                //Variavel para armazenar o indice do caixa
		                indice_de_caixa++;
		            }

		            //Se achou um caixa com dia de hoje, entra aqui
		            if (achou) {

		                //Varre a LinkedList garçons em busca do garçons com codigo informado pelo usuario
		                for (Garcom g : CadGarcom.getGarcons()) {
		                    //Verifica se existe algum garçons cadastrado com codigo fornecido
		                    try {
		                        if (Integer.parseInt(textFieldCodGarcom.getText()) <= 0) {
		                            JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
		                            textFieldCodGarcom.setText("");
		                            return;
		                        }
		                        if (g.getCodigo() == Integer.parseInt(textFieldCodGarcom.getText())) {
		                            achou_garcon = true;
		                            break;
		                        }
		                    } catch (NumberFormatException n) {
		                        JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Entrada invlida", JOptionPane.ERROR_MESSAGE);
		                        textFieldCodGarcom.setText("");
		                        return;

		                    }
		                }

		                //Se achou um garçom com ID informa, entra aqui
		                if (achou_garcon) {

		                    //For-each para varrer a LinkedList de pedidos na LinkedList do indice achado no primeiro for
		                    for (Pedido d : CoordPedido.retornaPedido(indice_de_caixa)) {

		                        //Verifica as pedidos que estao com o indice do garçom                        
		                        try {
		                            if (Integer.parseInt(textFieldCodGarcom.getText()) <= 0) {
		                            JOptionPane.showMessageDialog(null, "Digite um valor vÃ¡lido!", "Entrada invÃ¡lida", JOptionPane.ERROR_MESSAGE);
		                            textFieldCodGarcom.setText("");
		                            return;
		                        }
		                            if (d.getCodigoGarcon() == Integer.parseInt(textFieldCodGarcom.getText()) && d.getPedidoAberto()) {

		                                //Adiciona o indice dos pedidos que estao em nome do garçom em uma LinkedList de integer
		                                lista_de_pedidos.add(indice_de_pedidos);
		                                achou_pedido = true;

		                            }
		                        } catch (NumberFormatException n) {
		                            JOptionPane.showMessageDialog(null, "Digite um valor vÃ¡lido!", "Entrada invÃ¡lida", JOptionPane.ERROR_MESSAGE);
		                            textFieldCodGarcom.setText("");
		                            return;
		                        }
		                        indice_de_pedidos++;
		                    }

		                    //Limpar o campo de texto
		                    textFieldCodGarcom.setText("");

		                    //Se achou alguma pedido em nome do garçom
		                    if (achou_pedido) {

		                        //For-each para exibir em JOption todas as pedidos em nome do garçom
		                        for (Integer a : lista_de_pedidos) {

		                            JOptionPane.showMessageDialog(JFConsultaPedido.this, CoordPedido.retornaUmPedido(indice_de_caixa, a).toString(), "pedido", JOptionPane.INFORMATION_MESSAGE);

		                        }

		                        JFConsultaPedido.this.setVisible(false);
		                        //Else para nenhum pedido encontrado em nome do garçom   
		                    } else {

		                        JOptionPane.showMessageDialog(JFConsultaPedido.this, "Não existe nenhuma pedido em seu nome!", "Ok", JOptionPane.INFORMATION_MESSAGE);
		                    }
		                    //Else para nenhum garçom encontrado
		                } else {
		                    JOptionPane.showMessageDialog(JFConsultaPedido.this, "Não existe nenhum garçom com esse codigo!", "Erro", JOptionPane.ERROR_MESSAGE);
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
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(217, 86, 112, 23);
		contentPane.add(btnCancelar);
		
		btnAjuda.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				CadGarcom ajuda = new CadGarcom();
                JOptionPane.showMessageDialog(null, "" + ajuda.ajuda(), "Códigos dos Garçons", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnAjuda.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAjuda.setBounds(285, 30, 44, 23);
		contentPane.add(btnAjuda);
	}
}
