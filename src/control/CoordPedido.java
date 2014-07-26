package control;

import java.util.LinkedList;

import codigo.Pedido;
import control.CoordCaixa;;

public class CoordPedido {
	//private static LinkedList<Pedido> pedidos = new LinkedList<Pedido>();

	public CoordPedido() {

	}

	// Metodo para adicionar uma comanda na LinkedList caixas de acordo com o indice da caixa
	public static void adicionaPedido(int i, Pedido c) {
		CoordCaixa.getCaixas().get(i).adicionaPedido(c);
	}

	// Metodo para retornar apenas uma comanda de acordo com o indice do caixa
	// o indice da comanda
	public static Pedido retornaUmPedido(int indice_caixas,
			int indice_pedido) {

		return CoordCaixa.getCaixas().get(indice_caixas).retornaUmPedido(indice_pedido);

	}

	public static LinkedList<Pedido> retornaPedido(int indice) {

		return CoordCaixa.getCaixas().get(indice).retornaPedidos();

	}
}
