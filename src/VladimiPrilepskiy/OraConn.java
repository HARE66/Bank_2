package VladimiPrilepskiy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Класс предназначен для взаимодействия с БД Oracle
 * Класс имеет следующие методы:
 * SetStringSQL(String url, String name, String password, String sql) - в него нужно передать Stringовые строки соединения и Stringовый SQL запрос.
 * getArrayList_DannieZaprosa() - метод вернет результаты SELECTа в виде двумерной коллекции.
 * getDannieZaprosa() - метод вернет результат SELECTа только первой колонки и первого столбца в виде Object переменной.
 * getUpdateCount() - Метод выполняет update sql и возвращает кол-во измененных или добавленных записей
 * DropDannieZaprosa() - очистить данные переданные от метода сеттера SetStringSQL()
 * 
 * 
 * @author Vladimir Prilepskiy
 *
 */
public class OraConn {

	private static ArrayList<ArrayList<Object>> arrayList_DannieZaprosa = new ArrayList<ArrayList<Object>>();
	private static Object DannieZaprosa;
	private static int UpdateCount = 0;
	private static String S_sql; // "select * from ECFIL002 t where t.id_phone =
								// 0"
	private static String S_connUrl; // localhost:1521:MAGICASH
	private static String S_ConnName; // MAGICASH5_498
	private static String S_ConnPassword; // NCTMAGICASH5

	/**
	 * Метод возвращает ArrayList<ArrayList<Object>> arrayList_DannieZaprosa
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<ArrayList<Object>> getArrayList_DannieZaprosa() {
		try {
			OraConnectSelect();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Ошибка класса OraConn: " + e);
			e.printStackTrace();
		}
		return arrayList_DannieZaprosa;
	}

	/**
	 * Метод возвращает из запроса только одно значение. Для выполнения метода
	 * требуется передать SQL запрос в метод SetStringSQL
	 * 
	 * @return - Object переменная.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Object getDannieZaprosa() {
		try {
			OraConnectSelect();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "Ошибка класса OraConn: " + e1);
			e1.printStackTrace();
		}

		try {
			DannieZaprosa = arrayList_DannieZaprosa.get(0).get(0);
		} catch (Exception e) {
			System.out.println("Ошибка чтения массива ");
		}
		return DannieZaprosa;
	}

	/**
	 * Метод выполняет update sql и возвращает кол-во измененных или добавленных записей
	 * 
	 * @return int возвращает количество обновленных записей
	 */
	public static int getUpdateCount() {
		try {
			OraConnectUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Ошибка класса OraConn: " + e);
			e.printStackTrace();
		}
		return UpdateCount;
	}

	
	/**
	 * Метод обнуляет все переменные метода SetStringSQL (String url, String
	 * name, String password, String sql)
	 */
	public static void DropDannieZaprosa() {
		DannieZaprosa = null;
		UpdateCount = 0;
		arrayList_DannieZaprosa.clear();
		S_sql = null;
		S_connUrl = null;
		S_ConnName = null;
		S_ConnPassword = null;
	}

	/**
	 * Метод в который передается SQL запрос Select и стоки соединения с БД
	 * 
	 * @param url
	 *            - localhost:1521:MAGICASH
	 * @param name
	 *            - MAGICASH5_498
	 * @param password
	 *            - NCTMAGICASH5
	 * @param sql
	 *            - select * from ECFIL002 t where t.id_phone = 0
	 */
	public static void SetStringSQL(String url, String name, String password, String sql) {
		S_connUrl = url;
		S_ConnName = name;
		S_ConnPassword = password;
		S_sql = sql;
	}

	/**
	 * Метод соедится с БД, выполнит запрос из переменной SetStringSQL, и вернет
	 * в arrayList_DannieZaprosa
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void OraConnectSelect() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@" + S_connUrl, S_ConnName, S_ConnPassword);

		if (conn == null) {
			System.out.println("Нет соединения с БД!");
			System.exit(0);
		} else {
			String version = conn.getMetaData().getDatabaseProductVersion();
			System.out.println(version);

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(S_sql);

			/* вывети в консоль результаты запроса */
			int x = rs.getMetaData().getColumnCount(); // кол-во столбцов
			while (rs.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				for (int i = 1; i <= x; i++) {
					// System.out.print(rs.getString(i) + "\t");
					row.add(rs.getString(i));
				}
				// System.out.println();
				/* Добавляем строку в ArreyList */
				arrayList_DannieZaprosa.add(row);
			}
			stmt.close();
		}
		conn.close();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// если не закрыть коннект к БД, то БД встанет по ограничению количества
		// одновременных подключений
		// (ORA-12519)если вдруг такое произошло, то нужно будет очистить
		// журналы в Windows (Администрирование, просмотр событий)
		// т.к. в следующий раз БД не сможет стартовать.
	}

	private static void OraConnectUpdate() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@" + S_connUrl, S_ConnName, S_ConnPassword);

		if (conn == null) {
			System.out.println("Нет соединения с БД!");
			System.exit(0);
		} else {
			String version = conn.getMetaData().getDatabaseProductVersion();
			System.out.println(version);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(S_sql);

			/* вывети в консоль результаты запроса */
			UpdateCount = stmt.getUpdateCount();

			stmt.close();
		}
		conn.close();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

/*--------------------НИЖЕ ВСЕ НЕ НУЖНОЕ----------------------------*/

/* Метод поставляет соединение с БД (ОЦ) */
/*
 * public static void OraConnect11() throws ClassNotFoundException, SQLException
 * { Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * Connection conn =
 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:MAGICASH",
 * "MAGICASH5_498", "NCTMAGICASH5");
 * 
 * if (conn == null) { System.out.println("Нет соединения с БД!");
 * System.exit(0); } else { String version =
 * conn.getMetaData().getDatabaseProductVersion(); System.out.println(version);
 * 
 * Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(
 * "select * from ECFIL002 t where t.id_phone = 0");
 * 
 * вывети в консоль результаты запроса int x =
 * rs.getMetaData().getColumnCount(); // кол-во столбцов while (rs.next()) {
 * ArrayList<Object> row = new ArrayList<Object>(); for (int i = 1; i <= x; i++)
 * { System.out.print(rs.getString(i) + "\t"); row.add(rs.getString(i)); }
 * System.out.println(); Добавляем строку в ArreyList
 * arrayList_DannieZaprosa.add(row); } stmt.close();
 * 
 * }
 * 
 * }
 * 
 * 
 * 
 * 
 * Метод поставляет соединение с БД (ОЦ) public static void OraConnect0() throws
 * ClassNotFoundException, SQLException {
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * Connection conn =
 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:MAGICASH",
 * "MAGICASH5_498", "NCTMAGICASH5");
 * 
 * if (conn == null) { System.out.println("Нет соединения с БД!");
 * System.exit(0); } else { String version =
 * conn.getMetaData().getDatabaseProductVersion(); System.out.println(version);
 * 
 * Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(
 * "SELECT * FROM ecfil001");
 * 
 * вывети в консоль результаты запроса int x =
 * rs.getMetaData().getColumnCount(); // кол-во столбцов while (rs.next()) { for
 * (int i = 1; i <= x; i++) { System.out.print(rs.getString(i) + "\t"); }
 * System.out.println(); } stmt.close();
 * 
 * }
 * 
 * }
 * 
 * 
 * 
 * Метод поставляет соединение с БД (ОЦ 0177) public static Connection
 * OraConnect1() throws ClassNotFoundException, SQLException {
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * Connection conn =
 * DriverManager.getConnection("jdbc:oracle:thin:@OC:1521:MAGICASH",
 * "MAGICASH5_177", "NCTMAGICASH5");
 * 
 * if (conn == null) { System.out.println("Нет соединения с БД!");
 * System.exit(0); } else { String version =
 * conn.getMetaData().getDatabaseProductVersion(); System.out.println(version);
 * } return conn; }
 * 
 * //Метод поставляет соединение с БД (ПЦ 5805) public static Connection
 * OraConnect_PC() throws ClassNotFoundException, SQLException {
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * Connection conn =
 * DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.144:1521:MAGICASH",
 * "MAGICASH5", "NCTMAGICASH5");
 * 
 * if (conn == null) { System.out.println("Нет соединения с БД!");
 * System.exit(0); } else { String version =
 * conn.getMetaData().getDatabaseProductVersion(); System.out.println(version);
 * } return conn; }
 * 
 * //Метод принемает соединение public static void OraSelect(Object conn) throws
 * SQLException { Statement stmt = ((Connection) conn).createStatement();
 * ResultSet rs = stmt.executeQuery("SELECT * FROM ecfil001");
 * 
 * //вывети в консоль результаты запроса int x =
 * rs.getMetaData().getColumnCount(); // кол-во столбцов while (rs.next()) { for
 * (int i = 1; i <= x; i++) { System.out.print(rs.getString(i) + "\t"); }
 * System.out.println(); } stmt.close(); }
 * 
 * // Метод принемает соединение, значение String с запросом public static void
 * OraSelectIn_Conn_String(Object conn, String select) throws SQLException {
 * Statement stmt = ((Connection) conn).createStatement(); ResultSet rs =
 * stmt.executeQuery(select);
 * 
 * // вывети в консоль результаты запроса int x =
 * rs.getMetaData().getColumnCount(); // кол-во столбцов while (rs.next()) { for
 * (int i = 1; i <= x; i++) { System.out.print(rs.getString(i) + "\t"); }
 * System.out.println(); } stmt.close(); }
 * 
 * // Метод принемает соединение, значение String записями для INSERT public
 * static void OraInsert(Object conn, String insert) throws SQLException {
 * Statement stmt = ((Connection) conn).createStatement(); if
 * (stmt.execute(insert)) { System.out.println("TRUE"); } else
 * System.out.println("False"); System.out.println("Кол-во Записей: " +
 * stmt.getUpdateCount());
 * 
 * // вывети в консоль результаты запроса // // int x =
 * rs.getMetaData().getColumnCount(); // кол-во столбцов while // (rs.next()) {
 * for (int i = 1; i <= x; i++) { // System.out.print(rs.getString(i) + "\t"); }
 * System.out.println(); } /// stmt.close(); }
 * 
 * }
 */