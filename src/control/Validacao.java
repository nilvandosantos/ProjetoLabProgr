package control;

public class Validacao {
	public boolean valida(String... entradas) {
		boolean apto = true;
		String nulo = "";
		for (String entrada : entradas) {
			if (entrada.equals(nulo)) {

				apto = false;
				return apto;
			}
		}

		return apto;
	}
}
