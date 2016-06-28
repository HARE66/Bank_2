package VladimiPrilepskiy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Класс предназначен для записи и чтения файлов.
 * Содержит два метода:
 * riterFile(String KudaZapisat, String ChtoZapisat, boolean Dozapisat)
 * ReaderFile(String FilePut, boolean b_Karetka) 
 * @author VladimirPrilepskiy
 */


public class FileWriterImportTXT {

	/**
	 * Метод для записи String переменных в файл. Принимает три значения.
	 * @param KudaZapisat - String переменная, Пример: "c:/Экспорт из банка/import.txt"
	 * @param ChtoZapisat - String переменная, Пример: "Какой нибудь текст"
	 * @param Dozapisat - boolean переменная. (true-дозаписать в файл)
	 */
	public static void WriterFile(String KudaZapisat, String ChtoZapisat, boolean Dozapisat) {

		FileWriter f;
		try {
			f = new FileWriter(KudaZapisat, Dozapisat);
			f.write(ChtoZapisat);
			f.append('\n');
			f.flush();
			f.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ОШИБКА записи файла! :" + e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Метод для чтения файла. Принемает два значения и возвращает String переменную.
	 * @param FilePut - String переменная. Полный путь к файлу.
	 * @param b_Karetka - boolean переменная. Считывать
	 * @return
	 */
	public static String ReaderFile(String FilePut, boolean b_Karetka) {
		String S_Karetka = "";
		if(b_Karetka == true){S_Karetka = "\n";}
		String S_Text = "";
		String S_stroka = "";
			try {
				FileReader f = new FileReader(FilePut);
				@SuppressWarnings("resource")
				BufferedReader b = new BufferedReader(f);
			
				while ((S_stroka = b.readLine()) != null) {
					S_Text = S_Text + S_stroka + S_Karetka;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ОШИБКА чтения файла! :" + e);
				e.printStackTrace();
			}
		return S_Text;
	}
}

