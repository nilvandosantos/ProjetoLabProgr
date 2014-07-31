package control;

import java.util.Calendar;
import java.util.LinkedList;

import codigo.Caixa;
/**
 * Esta classe tem o objetivo coordenar o caixa do restaurante,criando as suas respectivas listas
 * e salvando-as em arquivos do tipo TXT.
 * 
 * @author Nayara,Nilvando e Marcos lucas.
 *
 * @version 1.0
 */

public class CoordCaixa {
	private static LinkedList<Caixa> caixas = new LinkedList<Caixa>();
	String data;

	public CoordCaixa() {
		//Data que foi gerado o pedido
		getData();
	}

	public static LinkedList<Caixa> getCaixas() {
		return caixas;
	}

	public static void setCaixas(LinkedList<Caixa> caixas) {
		CoordCaixa.caixas = caixas;
	}

	/**
	 *Metodo responsável por capturar a data em que o caixa está 
	 * aberto e armazená-la em uma String no formato DD/MM/AAAA
	 */
	public String getData() {
		
		Calendar temporario = Calendar.getInstance();
		data = "" + temporario.get(Calendar.DAY_OF_MONTH) + "/"
				+ (temporario.get(Calendar.MONTH) + 1) + "/"
				+ temporario.get(Calendar.YEAR);
		return data;
	}

	public boolean isCaixaAberto() {
		boolean isAberto = false;

		for (Caixa c : caixas) {

			if (data.equals(c.getData())) {
				isAberto = true;
				break;
			}
		}
		return isAberto;
	}

	/**
	 * Metodo para adicionar uma caixa a LinkedList caixas
	 */
	public static void addCaixa(Caixa c) {
		caixas.add(c);
	}

	public static LinkedList<Caixa> retornaCaixas() {
		return caixas;
	}
/**
 * Metodo para retornar o caixa em um indice escolhido
 */
	public static Caixa retornaUmCaixa(int indice) {
        return caixas.get(indice);
    }
}
