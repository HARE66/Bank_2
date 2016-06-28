package VladimiPrilepskiy;

import java.util.ArrayList;

/**
 * Данный класс предназначен для конвертации массива в строки для импорта в
 * Операционный Центр Petrol+
 * 
 * Содержит один public метод принимающий строки соединения с БД, двумерный массив и возвращает строки для импорта в ОЦ
 * Содержит одномерный массив с названиями колонок.
 * Содержит двумерный массив с данными полученными из БД.
 * 
 * @author VLADIMIR PRILEPSKIY
 *
 */
public class ImpPetrol {

	// Массив массивов данных полученных в результате работы метода ImportOC (для JTable)
	public static ArrayList<ArrayList<String>> arrList_platej = new ArrayList<ArrayList<String>>(); 
	// Массив названий столбцов (для JTable)
	public static ArrayList<String> arrList_kolonki = new ArrayList<String>();

	private static String url;
	private static String name;
	private static String password;

	private static String S_probel = "                                                                                      ";
	private static String S_import = "";
	
	/**
	 * Метод принимает двумерный массив, строки для коннекта к бд. Возвращает текст (String) для импорта в ОЦ Petrol+
	 * @param KakoitoMassiv - двумерный массив (ArrayList<ArrayList<String>>)
	 * @param S_url - String переменная. url для коннекта с бд.
	 * @param S_name - String переменная. name для коннекта с бд.
	 * @param S_password - String переменная. pssw для коннекта с бд.
	 * @return String переменная со строками для импорта в ОЦ
	 */
	public static String ImportOC(ArrayList<ArrayList<String>> KakoitoMassiv, String S_url, String S_name,
			String S_password) {

		url = S_url;
		name = S_name;
		password = S_password;
		
		S_import = "";
		
		arrList_platej.clear();  // очистить двумерный массив данных перед заполнением
		arrList_kolonki.clear(); // очистить массив названий столбцов перед заполнением
		MassivTitle();// заполняем Массив названий столбцов (нужен для отображения в JTable)
		// System.out.println("Читаем массив : ");
		for (int i = 0; i < KakoitoMassiv.size(); i++) {
			if (ZaprosIDclient(KakoitoMassiv.get(i).get(5)) != null) {// если ИД Клиента не
														// равно NULL
				
				/*Заполняем переменные данными из массива*/
				String S_ppNomer = KakoitoMassiv.get(i).get(1);
				String S_date = KakoitoMassiv.get(i).get(2);
				String S_date_case = S_date.substring(0, 2) + "/" + S_date.substring(3, 5) + "/"
						+ S_date.substring(8, 10);
				String S_operaciya = KakoitoMassiv.get(i).get(3);
				String S_INN = KakoitoMassiv.get(i).get(5);
				String S_idClient = ZaprosIDclient(S_INN); //SQL запрос ID клиента по ИНН
				String S_Client = ZaprosNameClient(S_INN); //SQL запрос Name клиента по ИНН
				String S_summa = KakoitoMassiv.get(i).get(6);
				String S_summa_visible = KakoitoMassiv.get(i).get(4);
				String S_summa_case = (S_summa.substring(0, S_summa.length() - 3))
						+ (S_summa.substring(S_summa.length() - 2, S_summa.length()));

				/*Теперь колдуем с ЧЧ/ММ/СС. Например ПП№ 12345 будет 03/04/05*/
				/*Это требование программы Petrol Plus*/
				String S_timeH;
				if (S_ppNomer.length() >= 3) {
					S_timeH = S_ppNomer.substring(S_ppNomer.length() - 3, S_ppNomer.length() - 2);
				} else {
					S_timeH = "0";
				}

				String S_timeM;
				if (S_ppNomer.length() >= 2) {
					S_timeM = S_ppNomer.substring(S_ppNomer.length() - 2, S_ppNomer.length() - 1);
				} else {
					S_timeM = "0";
				}

				String S_timeS;
				if (S_ppNomer.length() >= 1) {
					S_timeS = S_ppNomer.substring(S_ppNomer.length() - 1, S_ppNomer.length());
				} else {
					S_timeS = "0";
				}
				
				/*Соединяем String переменные в одну строку*/
				/*Пример:
				2,117       ,01,1,10400000  ,ПП№356                                  ,14/06/16,03:05:06
				2,142       ,01,1,12000000  ,ПП№1121                                 ,14/06/16,01:02:01
				*/
				S_import = S_import + "2," + (S_idClient + S_probel).substring(0, 10) + ",01," + S_operaciya + ","
						+ (S_summa_case + S_probel).substring(0, 10) + ","
						+ ("ПП№" + S_ppNomer + S_probel).substring(0, 40) + "," + S_date_case + "," + "0" + S_timeH
						+ ":0" + S_timeM + ":0" + S_timeS + '\n';
				
				/* Создаем строку */
				ArrayList<String> row = new ArrayList<String>();
				row.add(S_idClient);
				row.add(S_Client);
				row.add(S_date);
				row.add(S_ppNomer);
				row.add(S_summa_visible);
				
				// Добавляем строку в ArreyList
				arrList_platej.add(row);
			}
		}
		return S_import;
	}

	/**
	 * Метод принимает String переменую с номером ИНН и возвращает String ИД_Клиета 
	 * @param S_INN - String ИНН клиента
	 * @return - String ИД_Клиета 
	 */
	private static String ZaprosIDclient(String S_INN) {

		// String url = "localhost:1521:MAGICASH";
		// String name = "MAGICASH5_498";
		// String password = "***********";

		String S_bd_id_client = "0";

		if (S_INN != null) {
			// SQL запрос возвращающий ИД_КЛИЕНТА по ИНН
			String sql = "select ID_FIRMY from ECFIL002 where INN = '" + S_INN + "'";
			OraConn.SetStringSQL(url, name, password, sql);
			S_bd_id_client = (String) OraConn.getDannieZaprosa();
			OraConn.DropDannieZaprosa();
			//System.out.println("Переменная SQL " + OraConn.S_sql);
		}
		return S_bd_id_client;
	}

	/**
	 * Метод принимает String переменую с номером ИНН и возвращает String Название_Клиета 
	 * @param S_INN - String ИНН клиента
	 * @return - String Название_Клиета 
	 */
	private static String ZaprosNameClient(String S_INN) {

		// String url = "localhost:1521:MAGICASH";
		// String name = "MAGICASH5_498";
		// String password = "NCTMAGICASH5";

		String S_bd_client_name = "0";
		if (S_INN != null) {
			System.out.println("Connection url: " + url + ", " + name + ", " + password);
			// SQL запрос возвращающий НАЗВАНИЕ_КЛИЕНТА по ИНН
			String sql = "select NAME from ECFIL002 where INN = '" + S_INN + "'";
			OraConn.SetStringSQL(url, name, password, sql);
			S_bd_client_name = (String) OraConn.getDannieZaprosa();
			OraConn.DropDannieZaprosa();
			//System.out.println("Переменная SQL " + OraConn.S_sql);
		}
		return S_bd_client_name;
	}

	/**
	 * Метод добавляет в массив данные о назавниях колонок (для JTable)
	 */
	private static void MassivTitle() {
		// заполняем Массив названий столбцов
		arrList_kolonki.add("ИД Клиента");
		arrList_kolonki.add("Клиент");
		arrList_kolonki.add("Дата");
		arrList_kolonki.add("Номер ПП");
		arrList_kolonki.add("Сумма"); // с плюсом или минусом
	}

}
