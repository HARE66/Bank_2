package VladimiPrilepskiy;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Класс предназначен для считывания файла из Клиент Банка, и заполнении массива
 * с одновременным обращением к классу OraConn для получения результатов (ID,
 * Название клиента) из БД Операционного Центра Petrol5.
 * 
 * @author Vladimir Prilepskiy
 *
 */
public class ReaderFileBank {

	// Двумерная коллекция 
	public static ArrayList<ArrayList<String>> arrList_platej = new ArrayList<ArrayList<String>>();
	// Массив названий столбцов
	public static ArrayList<String> arrList_kolonki = new ArrayList<String>();

	final public static String S_moi_inn = "6626013416"; // Мой ИНН

	/**
	 * Метод считывает данные из файла и загружает их в ArreyList
	 * @param inFileExpBank1C - String путь к файлу который надо прочитать
	 * @throws HeadlessException 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void ReadFile(String inFileExpBank1C) throws HeadlessException, IOException {

		if (inFileExpBank1C != null) { //если входной вайл не равно null
			System.out.println("inFileExpBank1C != null");

			
			arrList_platej.clear();
			arrList_kolonki.clear();
			
			// заполняем Массив названий столбцов
			arrList_kolonki.add("СекцияДокумент");
			arrList_kolonki.add("Номер");
			arrList_kolonki.add("Дата");
			arrList_kolonki.add("Операция");
			arrList_kolonki.add("Сумма_для_БД"); // с плюсом или минусом
			//arrList_kolonki.add("ID_Клиент_БД");
			//arrList_kolonki.add("Клиент_БД");
			arrList_kolonki.add("ИНН_БД");
			arrList_kolonki.add("Сумма");
			arrList_kolonki.add("Плательщик");
			arrList_kolonki.add("ПлательщикИНН");
			arrList_kolonki.add("Получатель");
			arrList_kolonki.add("ПолучательИНН");
			arrList_kolonki.add("НазначениеПлатежа");

			FileReader f = new FileReader(inFileExpBank1C); //
			@SuppressWarnings("resource")
			BufferedReader b = new BufferedReader(f);

			String S_stroka;
			String S_sekciya_dokument = "СекцияДокумент=";
			String S_sekciya_dokument_naiden = "ПУСТО";
			String S_nomer = "Номер=";
			String S_nomer_naiden = "ПУСТО";
			String S_date = "Дата=";
			String S_date_naiden = "ПУСТО";

			String S_operaciya = "";
			String S_mnogetel_summi = "";

			//String S_bd_id_client = "0";
			//String S_bd_client_name = " ";

			String S_summa = "Сумма=";
			String S_summa_naiden = "ПУСТО";
			String S_platelhik = "Плательщик=";
			String S_platelhik_naiden = "ПУСТО";
			String S_platelhik_inn = "ПлательщикИНН=";
			String S_platelhik_inn_naiden = "ПУСТО";
			String S_poluchatel = "Получатель=";
			String S_poluchatel_naiden = "ПУСТО";
			String S_poluchatel_inn = "ПолучательИНН=";
			String S_poluchatel_inn_naiden = "ПУСТО";
			String S_naznachenie = "НазначениеПлатежа=";
			String S_naznachenie_naiden = "";
			String S_naznachenie1 = "НазначениеПлатежа1=";
			String S_naznachenie1_naiden = "";
			String S_naznachenie2 = "НазначениеПлатежа2=";
			String S_naznachenie2_naiden = "";
			String S_end_document = "КонецДокумента";
			//String S_end_document_naiden = "ПУСТО";

			int i_sekciya_dokument = 0;
			int i_nomer_stroki = 0;
			String S_INN_for_BD = "00000000";

			// читаем построчно файл
			while ((S_stroka = b.readLine()) != null) {
				i_nomer_stroki++;
				//System.out.println("НОМЕР СТРОКИ" + i_nomer_stroki + ": " + S_stroka);

				if (S_stroka.indexOf(S_sekciya_dokument) != -1) {
					i_sekciya_dokument++;
					//System.out.println(" +++++++++++++++++ НАЙДЕНО СОВПАДЕНИЕ: " + i_sekciya_dikument); // номер
																										// совпадения
					S_sekciya_dokument_naiden = S_stroka.substring(S_sekciya_dokument.length());
					//System.out.println(S_sekciya_dikument_naiden);
				}

				if (S_stroka.indexOf(S_nomer) != -1) { // номер ПП
					S_nomer_naiden = S_stroka.substring(S_nomer.length());
					//System.out.println(" *********** " + S_nomer_naiden);
				}
				if (S_stroka.indexOf(S_date) != -1) { // Дата ПП
					S_date_naiden = S_stroka.substring(S_date.length());
					//System.out.println(" *********** " + S_date_naiden);
				}
				if (S_stroka.indexOf(S_summa) != -1) { // Сумма ПП
					S_summa_naiden = S_stroka.substring(S_summa.length());
					//System.out.println(" *********** " + S_summa_naiden);
					//если платеж 1000 а не 1000.00 то прицепить .00 (Сбер)
					if(S_summa_naiden.lastIndexOf(".") == -1){
						S_summa_naiden = S_summa_naiden + ".00";}
				}
				if (S_stroka.indexOf(S_platelhik) != -1) { // Плательщик
					S_platelhik_naiden = S_stroka.substring(S_platelhik.length());
					//System.out.println(" *********** " + S_platelhik_naiden);
				}
				if (S_stroka.indexOf(S_platelhik_inn) != -1) { // ПлательщикИНН
					S_platelhik_inn_naiden = S_stroka.substring(S_platelhik_inn.length());
					//System.out.println(" *********** " + S_platelhik_inn_naiden);
				}
				if (S_stroka.indexOf(S_poluchatel) != -1) { // Получатель
					S_poluchatel_naiden = S_stroka.substring(S_poluchatel.length());
					//System.out.println(" *********** " + S_poluchatel_naiden);
				}
				if (S_stroka.indexOf(S_poluchatel_inn) != -1) { // ПолучательИНН
					S_poluchatel_inn_naiden = S_stroka.substring(S_poluchatel_inn.length());
					//System.out.println(" *********** " + S_poluchatel_inn_naiden);
				}
				if (S_stroka.indexOf(S_naznachenie) != -1) { // Назначение
																// платежа
					S_naznachenie_naiden = S_stroka.substring(S_naznachenie.length());
					//System.out.println(" *********** " + S_naznachenie_naiden);
				}
				if (S_stroka.indexOf(S_naznachenie1) != -1) { // Назначение
																// платежа
					S_naznachenie1_naiden = S_stroka.substring(S_naznachenie1.length());
					//System.out.println(" *********** " + S_naznachenie1_naiden);
				}
				if (S_stroka.indexOf(S_naznachenie2) != -1) { // Назначение
																// платежа
					S_naznachenie2_naiden = S_stroka.substring(S_naznachenie2.length());
					//System.out.println(" *********** " + S_naznachenie2_naiden);
				}
				/*
				 * КОНЕЦ ДОКУМЕНТА (при обнаружении конца, начинается запись из
				 * переменных в ArrayList)
				 */
				if (S_stroka.indexOf(S_end_document) != -1) {
					//S_end_document_naiden = S_stroka.substring(S_end_document.length());
					//System.out.println(" *********** " + S_end_document_naiden);

					// проверка кто кому платит
					//если ПлатищикИНН = МойИНН
					if (S_platelhik_inn_naiden.equals(S_moi_inn)) {
						S_mnogetel_summi = "-";
						S_operaciya = "0";
						S_INN_for_BD = S_poluchatel_inn_naiden;
					}
					// если ПолучательИНН = МойИНН
					if (S_poluchatel_inn_naiden.equals(S_moi_inn)) { 
						S_mnogetel_summi = "";
						S_operaciya = "1";
						S_INN_for_BD = S_platelhik_inn_naiden;
					}
					//если S_INN_for_BD = пустоте то пусть будет четыре нуля
					if (S_INN_for_BD.equals("") == true){
						S_INN_for_BD = JOptionPane.showInputDialog(null,
								S_sekciya_dokument_naiden + " №" + S_nomer_naiden + " от " + S_date_naiden + "; "
										+ S_summa_naiden + "\n" + "Назначение: " + S_naznachenie_naiden + S_naznachenie1_naiden + S_naznachenie2_naiden + "\n" + "Плательщик: " + S_platelhik_naiden + "\n" + "Получатель: " + S_poluchatel_naiden, "Введите ИНН клиента", 3);
						//если введена пустота или нажата кнопка Cancel
						if(S_INN_for_BD != null){
							if(S_INN_for_BD.equals("")){
								S_INN_for_BD = "0000";}
						}else{
							S_INN_for_BD = "0000";
						}
					}

					//String url = "localhost:1521:MAGICASH";
					//String name = "MAGICASH5_498";
					//String password = "NCTMAGICASH5";
					
					/*String url = FileWriterImportTXT.ReaderFile("ConfConnURL_OC.cfg", false);
					String name = FileWriterImportTXT.ReaderFile("ConfConnName_OC.cfg", false);
					String password = FileWriterImportTXT.ReaderFile("ConfConnPass_OC.cfg", false);
					
					System.out.println("Connection url: " + url + ", " + name + ", " + password);
					
					String sql;

					// SQL запрос возвращающий ИД_КЛИЕНТА по ИНН
					sql = "select ID_FIRMY from ECFIL002 where INN = '" + S_INN_for_BD + "'";
					OraConn.SetStringSQL(url, name, password, sql);
					S_bd_id_client = (String) OraConn.getDannieZaprosa();
					OraConn.DropDannieZaprosa();
					System.out.println("Переменная SQL " + OraConn.S_sql);

					// SQL запрос возвращающий НАЗВАНИЕ_КЛИЕНТА по ИНН
					sql = "select NAME from ECFIL002 where INN = '" + S_INN_for_BD + "'";
					OraConn.SetStringSQL(url, name, password, sql);
					S_bd_client_name = (String) OraConn.getDannieZaprosa();
					OraConn.DropDannieZaprosa();
					System.out.println("Переменная SQL " + OraConn.S_sql);*/

					ArrayList<String> row = new ArrayList<String>(); // добавляем
																		// строку

					// Заполняем строку переменными
					row.add(S_sekciya_dokument_naiden);
					row.add(S_nomer_naiden);
					row.add(S_date_naiden);

					row.add(S_operaciya);
					row.add(S_mnogetel_summi + S_summa_naiden);
					//row.add(S_bd_id_client);
					//row.add(S_bd_client_name);
					row.add(S_INN_for_BD);

					row.add(S_summa_naiden);
					row.add(S_platelhik_naiden);
					row.add(S_platelhik_inn_naiden);
					row.add(S_poluchatel_naiden);
					row.add(S_poluchatel_inn_naiden);
					row.add(S_naznachenie_naiden + S_naznachenie1_naiden + S_naznachenie2_naiden);

					// Добавляем строку в ArreyList
					arrList_platej.add(row);

					if (S_stroka.indexOf(S_end_document) != -1) { // Обнуляем
																	// переменные
						//System.out.println(" ЗДЕСЬ НАДО ОБНУЛИТЬ ВСЕ СТРИНГОВЫЕ ПЕРЕМЕННЫЕ ");
						S_nomer_naiden = "0";
						S_date_naiden = "01.01.1900";

						S_operaciya = "";
						S_mnogetel_summi = "";
						//S_bd_id_client = "";
						//S_bd_client_name = "";
						S_INN_for_BD = "";

						S_summa_naiden = "0";
						S_platelhik_naiden = " ";
						S_platelhik_inn_naiden = "0";
						S_poluchatel_naiden = " ";
						S_poluchatel_inn_naiden = "0";
						S_naznachenie_naiden = "";
						S_naznachenie1_naiden = "";
						S_naznachenie2_naiden = "";
						//sql = "";

					}

				}

			}
			JOptionPane.showMessageDialog(null, "Прочитанно строк в файле: " + i_nomer_stroki + '\n' + "Найдено " + S_sekciya_dokument + " " + i_sekciya_dokument);
		} else {
			JOptionPane.showMessageDialog(null, "Файл = null");
		}
	};

}
