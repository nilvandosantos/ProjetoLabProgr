package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import codigo.Garcom;
/**
 * Esta classe tem como objetivo cadastrar o garçom e verificar se o mesmo ja exite na lista, na qual
 * será armazenado apos a conclusão do cadastro.
 * 
 * @author Marcos Lucas,Nayara,Nilvando.
 * 
 * @version 1.0
 *
 */
public class CadGarcom {

	private static LinkedList<Garcom> garcons = new LinkedList<Garcom>();
	private DataOutputStream outputGarcom;
	private DataInputStream inputGarcom;
	private boolean moreRecordsGarcom = true;

	public CadGarcom() {
		iniciarGarcom();
	}

	public static void main(String[] args) {

	}
/**
 *  Este metodo tem como objetivo verificar se já existe um garçom com o 
 *  mesmo nome cadastrado no sistema .
 * @param- nome do garçom
 * @return boolean isExist
 */
	
	public boolean isExistGarcon(String nome) {

		boolean isExist = false;

		// Verifica se o campo Nome do Garçom está preenchido

		for (Garcom cadastrado : garcons) {

			if (cadastrado.getNome().equalsIgnoreCase(nome)) {
				isExist = true;
			}
		}

		return isExist;
	}
/**
 * Metodo criado para gerar um novo arquivo do tipo TXT,caso o mesmo ja tenha sido gerado ele 
 * apenas irá ler o arquivo e istanciará o objeto.
 * @see- class Garcom.
 */
	public void iniciarGarcom() {

		File arquivo = new File("Garcons.txt");
		try {
			if (!arquivo.exists()) {
				// cria um arquivo (vazio)
				arquivo.createNewFile();
				JOptionPane.showMessageDialog(null,
						"Arquivo criado com sucesso!");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ler o arquivo garcom
		try {
			inputGarcom = new DataInputStream(
					new FileInputStream("Garcons.txt"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha na Abertura do Arquivo para Leitura", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// InstanciarGarcom
		String nome;
		int codigo;
		double gorjeta;
		double totalGorjeta;
/*
 * Este metodo irá carregar os garcons do arquivo para as listas.
 */
		
		try {
			while (moreRecordsGarcom) {
				nome = inputGarcom.readUTF();
				codigo = inputGarcom.readInt();
				gorjeta = inputGarcom.readDouble();
				totalGorjeta = inputGarcom.readDouble();

				Garcom temporario = new Garcom(nome, codigo, gorjeta,
						totalGorjeta);
				adicionaGarcom(temporario);
				System.out.println(temporario);
			}
		} catch (EOFException eof) {
			moreRecordsGarcom = false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Erro durante leitura do arquivo", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// Fecha inputGarcom
		try {
			inputGarcom.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha no Fechamento do Arquivo durante Leitura", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

	}

	/** 
	 * Metodo para adicionar o objeto Garcom a LinkedList "garcons".
	 * 
	 */
	public static void adicionaGarcom(Garcom g) {
		garcons.add(g);
	}
/**
 * Metodo para abrir o arquivo e grava-lo,caso ocorra algum erro uma mensagem será retornada.
 * 
 */
	public void gravarGarcom() {
		
		
		try {
			outputGarcom = new DataOutputStream(new FileOutputStream(
					"Garcons.txt", false));
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha na Abertura do Arquivo para Gravação", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
/*
 *  Carrega toda as listas no arquivo,caso ocorra algum erro uma mensagem será retornada.
 */
		try {
			for (Garcom a : garcons) {
				outputGarcom.writeUTF(a.getNome());
				outputGarcom.writeInt(a.getCodigo());
				outputGarcom.writeDouble(a.getGorjeta());
				outputGarcom.writeDouble(a.getTotalGorjeta());
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Erro durante gravação no arquivo", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		/*
		 * Fecha os arquivos Output garcom,caso ocorra algum erro uma mensagem será retornada.
		 */
		
		try {
			outputGarcom.flush();
			outputGarcom.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha no Fechamento do Arquivo - Durante Gravação!!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

}
