package VladimiPrilepskiy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * ����� ������������ ��� �������������� � �� Oracle
 * ����� ����� ��������� ������:
 * SetStringSQL(String url, String name, String password, String sql) - � ���� ����� �������� String���� ������ ���������� � String���� SQL ������.
 * getArrayList_DannieZaprosa() - ����� ������ ���������� SELECT� � ���� ��������� ���������.
 * getDannieZaprosa() - ����� ������ ��������� SELECT� ������ ������ ������� � ������� ������� � ���� Object ����������.
 * getUpdateCount() - ����� ��������� update sql � ���������� ���-�� ���������� ��� ����������� �������
 * DropDannieZaprosa() - �������� ������ ���������� �� ������ ������� SetStringSQL()
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
	 * ����� ���������� ArrayList<ArrayList<Object>> arrayList_DannieZaprosa
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<ArrayList<Object>> getArrayList_DannieZaprosa() {
		try {
			OraConnectSelect();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "������ ������ OraConn: " + e);
			e.printStackTrace();
		}
		return arrayList_DannieZaprosa;
	}

	/**
	 * ����� ���������� �� ������� ������ ���� ��������. ��� ���������� ������
	 * ��������� �������� SQL ������ � ����� SetStringSQL
	 * 
	 * @return - Object ����������.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Object getDannieZaprosa() {
		try {
			OraConnectSelect();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "������ ������ OraConn: " + e1);
			e1.printStackTrace();
		}

		try {
			DannieZaprosa = arrayList_DannieZaprosa.get(0).get(0);
		} catch (Exception e) {
			System.out.println("������ ������ ������� ");
		}
		return DannieZaprosa;
	}

	/**
	 * ����� ��������� update sql � ���������� ���-�� ���������� ��� ����������� �������
	 * 
	 * @return int ���������� ���������� ����������� �������
	 */
	public static int getUpdateCount() {
		try {
			OraConnectUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "������ ������ OraConn: " + e);
			e.printStackTrace();
		}
		return UpdateCount;
	}

	
	/**
	 * ����� �������� ��� ���������� ������ SetStringSQL (String url, String
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
	 * ����� � ������� ���������� SQL ������ Select � ����� ���������� � ��
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
	 * ����� �������� � ��, �������� ������ �� ���������� SetStringSQL, � ������
	 * � arrayList_DannieZaprosa
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void OraConnectSelect() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@" + S_connUrl, S_ConnName, S_ConnPassword);

		if (conn == null) {
			System.out.println("��� ���������� � ��!");
			System.exit(0);
		} else {
			String version = conn.getMetaData().getDatabaseProductVersion();
			System.out.println(version);

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(S_sql);

			/* ������ � ������� ���������� ������� */
			int x = rs.getMetaData().getColumnCount(); // ���-�� ��������
			while (rs.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				for (int i = 1; i <= x; i++) {
					// System.out.print(rs.getString(i) + "\t");
					row.add(rs.getString(i));
				}
				// System.out.println();
				/* ��������� ������ � ArreyList */
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
		// ���� �� ������� ������� � ��, �� �� ������� �� ����������� ����������
		// ������������� �����������
		// (ORA-12519)���� ����� ����� ���������, �� ����� ����� ��������
		// ������� � Windows (�����������������, �������� �������)
		// �.�. � ��������� ��� �� �� ������ ����������.
	}

	private static void OraConnectUpdate() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@" + S_connUrl, S_ConnName, S_ConnPassword);

		if (conn == null) {
			System.out.println("��� ���������� � ��!");
			System.exit(0);
		} else {
			String version = conn.getMetaData().getDatabaseProductVersion();
			System.out.println(version);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(S_sql);

			/* ������ � ������� ���������� ������� */
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

/*--------------------���� ��� �� ������----------------------------*/

/* ����� ���������� ���������� � �� (��) */
/*
 * public static void OraConnect11() throws ClassNotFoundException, SQLException
 * { Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * Connection conn =
 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:MAGICASH",
 * "MAGICASH5_498", "NCTMAGICASH5");
 * 
 * if (conn == null) { System.out.println("��� ���������� � ��!");
 * System.exit(0); } else { String version =
 * conn.getMetaData().getDatabaseProductVersion(); System.out.println(version);
 * 
 * Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(
 * "select * from ECFIL002 t where t.id_phone = 0");
 * 
 * ������ � ������� ���������� ������� int x =
 * rs.getMetaData().getColumnCount(); // ���-�� �������� while (rs.next()) {
 * ArrayList<Object> row = new ArrayList<Object>(); for (int i = 1; i <= x; i++)
 * { System.out.print(rs.getString(i) + "\t"); row.add(rs.getString(i)); }
 * System.out.println(); ��������� ������ � ArreyList
 * arrayList_DannieZaprosa.add(row); } stmt.close();
 * 
 * }
 * 
 * }
 * 
 * 
 * 
 * 
 * ����� ���������� ���������� � �� (��) public static void OraConnect0() throws
 * ClassNotFoundException, SQLException {
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * Connection conn =
 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:MAGICASH",
 * "MAGICASH5_498", "NCTMAGICASH5");
 * 
 * if (conn == null) { System.out.println("��� ���������� � ��!");
 * System.exit(0); } else { String version =
 * conn.getMetaData().getDatabaseProductVersion(); System.out.println(version);
 * 
 * Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(
 * "SELECT * FROM ecfil001");
 * 
 * ������ � ������� ���������� ������� int x =
 * rs.getMetaData().getColumnCount(); // ���-�� �������� while (rs.next()) { for
 * (int i = 1; i <= x; i++) { System.out.print(rs.getString(i) + "\t"); }
 * System.out.println(); } stmt.close();
 * 
 * }
 * 
 * }
 * 
 * 
 * 
 * ����� ���������� ���������� � �� (�� 0177) public static Connection
 * OraConnect1() throws ClassNotFoundException, SQLException {
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * Connection conn =
 * DriverManager.getConnection("jdbc:oracle:thin:@OC:1521:MAGICASH",
 * "MAGICASH5_177", "NCTMAGICASH5");
 * 
 * if (conn == null) { System.out.println("��� ���������� � ��!");
 * System.exit(0); } else { String version =
 * conn.getMetaData().getDatabaseProductVersion(); System.out.println(version);
 * } return conn; }
 * 
 * //����� ���������� ���������� � �� (�� 5805) public static Connection
 * OraConnect_PC() throws ClassNotFoundException, SQLException {
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * Connection conn =
 * DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.144:1521:MAGICASH",
 * "MAGICASH5", "NCTMAGICASH5");
 * 
 * if (conn == null) { System.out.println("��� ���������� � ��!");
 * System.exit(0); } else { String version =
 * conn.getMetaData().getDatabaseProductVersion(); System.out.println(version);
 * } return conn; }
 * 
 * //����� ��������� ���������� public static void OraSelect(Object conn) throws
 * SQLException { Statement stmt = ((Connection) conn).createStatement();
 * ResultSet rs = stmt.executeQuery("SELECT * FROM ecfil001");
 * 
 * //������ � ������� ���������� ������� int x =
 * rs.getMetaData().getColumnCount(); // ���-�� �������� while (rs.next()) { for
 * (int i = 1; i <= x; i++) { System.out.print(rs.getString(i) + "\t"); }
 * System.out.println(); } stmt.close(); }
 * 
 * // ����� ��������� ����������, �������� String � �������� public static void
 * OraSelectIn_Conn_String(Object conn, String select) throws SQLException {
 * Statement stmt = ((Connection) conn).createStatement(); ResultSet rs =
 * stmt.executeQuery(select);
 * 
 * // ������ � ������� ���������� ������� int x =
 * rs.getMetaData().getColumnCount(); // ���-�� �������� while (rs.next()) { for
 * (int i = 1; i <= x; i++) { System.out.print(rs.getString(i) + "\t"); }
 * System.out.println(); } stmt.close(); }
 * 
 * // ����� ��������� ����������, �������� String �������� ��� INSERT public
 * static void OraInsert(Object conn, String insert) throws SQLException {
 * Statement stmt = ((Connection) conn).createStatement(); if
 * (stmt.execute(insert)) { System.out.println("TRUE"); } else
 * System.out.println("False"); System.out.println("���-�� �������: " +
 * stmt.getUpdateCount());
 * 
 * // ������ � ������� ���������� ������� // // int x =
 * rs.getMetaData().getColumnCount(); // ���-�� �������� while // (rs.next()) {
 * for (int i = 1; i <= x; i++) { // System.out.print(rs.getString(i) + "\t"); }
 * System.out.println(); } /// stmt.close(); }
 * 
 * }
 */