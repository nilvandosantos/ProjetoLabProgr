package control;
/**
 * Esta classe tem como objetivo permitir que campos em branco pertecente as interfaces graficas 
 * não sejam salvos ate que sejam totalmente preenchidos.
 * 
 * @author Marcos Lucas,Nayara,Nilvando.
 *
 */
public class Validacao {
	//VERIFICA SE O CAMPO ESTA EM BRANCO NAS TELAS.
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
