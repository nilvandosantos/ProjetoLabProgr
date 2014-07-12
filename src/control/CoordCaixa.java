package control;

import java.util.Calendar;
import java.util.LinkedList;

import codigo.Caixa;

public class CoordCaixa {
	private static LinkedList<Caixa> caixas = new LinkedList<Caixa>();
	String data;
	
	public CoordCaixa(){
		getData();
	}
	
	public String getData() {
		// Trecho de código responsável por capturar a data em que o caixa está
		// aberto e armazená-la em uma String no formato DD/MM/AAAA
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
	
	//MÃ©todo para adicionar uma caixa a LinkedList caixas
    public static void addCaixa(Caixa c) {
        caixas.add(c);
    }
	
}
