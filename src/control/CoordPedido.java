package control;

import java.util.LinkedList;

import codigo.Pedido;
import control.CoordCaixa;;

/**
 * Esta classe tem o objetivo coordenar os pedido do restaurante,criando as suas respectivas listas
 * e salvando-as em arquivos do tipo TXT.
 * @author Nayara,Nilvando e Marcos lucas.
 * @version 1.0
 */
public class CoordPedido {
	
	public CoordPedido() {

	}

	/**
	 * Metodo para adicionar um pedido na LinkedList caixas de acordo com o indice do caixa
	 * @param i valor do pedido
	 * @param c pedido
	 */
	public static void adicionaPedido(int i, Pedido c) {
		CoordCaixa.getCaixas().get(i).adicionaPedido(c);
	}

	/**
	 * Metodo para retornar apenas uma comanda de acordo com o indice do caixa, o indice do pedido
	 * @param indice_caixas Encontrar o caixa		
	 * @param indice_pedido Encontrar o pedido.
	 * @return Pedido com o seu codigo
	 */
	public static Pedido retornaUmPedido(int indice_caixas,
			int indice_pedido) {

		return CoordCaixa.getCaixas().get(indice_caixas).retornaUmPedido(indice_pedido);

	}

	public static LinkedList<Pedido> retornaPedido(int indice) {

		return CoordCaixa.getCaixas().get(indice).retornaPedidos();

	}
}
