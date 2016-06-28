package VladimiPrilepskiy;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * Класс предназначен для вызова диалогового окна выбора файла.
 * 
 * @author Vladimir Prilepskiy
 *
 */
public class DialogWindow {

	private static String put_k_failu;

	/**
	 * Метод принемает String переменную с каталогом по умолчанию (можно даже
	 * указать каталог с файлом) и возвращает String переменную - абсолютный
	 * путь к файлу
	 * 
	 * @param KatalogPoUmochaniu
	 *            - String переменная
	 * @return String переменная - путь к выбранному вайлу
	 */

	public static String DialogWin(String KatalogPoUmochaniu) {
		JFileChooser fileopen = new JFileChooser(KatalogPoUmochaniu); // задать
																		// каталог
					 													// по
																		// умолчанию
		int ret = fileopen.showDialog(null, "Открыть файл");
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fileopen.getSelectedFile();
			System.out.println(file.getAbsolutePath());
			put_k_failu = file.getAbsolutePath();
		}
		return put_k_failu;
	}

}
