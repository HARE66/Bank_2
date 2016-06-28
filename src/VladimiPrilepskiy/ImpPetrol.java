package VladimiPrilepskiy;

import java.util.ArrayList;

/**
 * ������ ����� ������������ ��� ����������� ������� � ������ ��� ������� �
 * ������������ ����� Petrol+
 * 
 * �������� ���� public ����� ����������� ������ ���������� � ��, ��������� ������ � ���������� ������ ��� ������� � ��
 * �������� ���������� ������ � ���������� �������.
 * �������� ��������� ������ � ������� ����������� �� ��.
 * 
 * @author VLADIMIR PRILEPSKIY
 *
 */
public class ImpPetrol {

	// ������ �������� ������ ���������� � ���������� ������ ������ ImportOC (��� JTable)
	public static ArrayList<ArrayList<String>> arrList_platej = new ArrayList<ArrayList<String>>(); 
	// ������ �������� �������� (��� JTable)
	public static ArrayList<String> arrList_kolonki = new ArrayList<String>();

	private static String url;
	private static String name;
	private static String password;

	private static String S_probel = "                                                                                      ";
	private static String S_import = "";
	
	/**
	 * ����� ��������� ��������� ������, ������ ��� �������� � ��. ���������� ����� (String) ��� ������� � �� Petrol+
	 * @param KakoitoMassiv - ��������� ������ (ArrayList<ArrayList<String>>)
	 * @param S_url - String ����������. url ��� �������� � ��.
	 * @param S_name - String ����������. name ��� �������� � ��.
	 * @param S_password - String ����������. pssw ��� �������� � ��.
	 * @return String ���������� �� �������� ��� ������� � ��
	 */
	public static String ImportOC(ArrayList<ArrayList<String>> KakoitoMassiv, String S_url, String S_name,
			String S_password) {

		url = S_url;
		name = S_name;
		password = S_password;
		
		S_import = "";
		
		arrList_platej.clear();  // �������� ��������� ������ ������ ����� �����������
		arrList_kolonki.clear(); // �������� ������ �������� �������� ����� �����������
		MassivTitle();// ��������� ������ �������� �������� (����� ��� ����������� � JTable)
		// System.out.println("������ ������ : ");
		for (int i = 0; i < KakoitoMassiv.size(); i++) {
			if (ZaprosIDclient(KakoitoMassiv.get(i).get(5)) != null) {// ���� �� ������� ��
														// ����� NULL
				
				/*��������� ���������� ������� �� �������*/
				String S_ppNomer = KakoitoMassiv.get(i).get(1);
				String S_date = KakoitoMassiv.get(i).get(2);
				String S_date_case = S_date.substring(0, 2) + "/" + S_date.substring(3, 5) + "/"
						+ S_date.substring(8, 10);
				String S_operaciya = KakoitoMassiv.get(i).get(3);
				String S_INN = KakoitoMassiv.get(i).get(5);
				String S_idClient = ZaprosIDclient(S_INN); //SQL ������ ID ������� �� ���
				String S_Client = ZaprosNameClient(S_INN); //SQL ������ Name ������� �� ���
				String S_summa = KakoitoMassiv.get(i).get(6);
				String S_summa_visible = KakoitoMassiv.get(i).get(4);
				String S_summa_case = (S_summa.substring(0, S_summa.length() - 3))
						+ (S_summa.substring(S_summa.length() - 2, S_summa.length()));

				/*������ ������� � ��/��/��. �������� �Ϲ 12345 ����� 03/04/05*/
				/*��� ���������� ��������� Petrol Plus*/
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
				
				/*��������� String ���������� � ���� ������*/
				/*������:
				2,117       ,01,1,10400000  ,�Ϲ356                                  ,14/06/16,03:05:06
				2,142       ,01,1,12000000  ,�Ϲ1121                                 ,14/06/16,01:02:01
				*/
				S_import = S_import + "2," + (S_idClient + S_probel).substring(0, 10) + ",01," + S_operaciya + ","
						+ (S_summa_case + S_probel).substring(0, 10) + ","
						+ ("�Ϲ" + S_ppNomer + S_probel).substring(0, 40) + "," + S_date_case + "," + "0" + S_timeH
						+ ":0" + S_timeM + ":0" + S_timeS + '\n';
				
				/* ������� ������ */
				ArrayList<String> row = new ArrayList<String>();
				row.add(S_idClient);
				row.add(S_Client);
				row.add(S_date);
				row.add(S_ppNomer);
				row.add(S_summa_visible);
				
				// ��������� ������ � ArreyList
				arrList_platej.add(row);
			}
		}
		return S_import;
	}

	/**
	 * ����� ��������� String ��������� � ������� ��� � ���������� String ��_������ 
	 * @param S_INN - String ��� �������
	 * @return - String ��_������ 
	 */
	private static String ZaprosIDclient(String S_INN) {

		// String url = "localhost:1521:MAGICASH";
		// String name = "MAGICASH5_498";
		// String password = "***********";

		String S_bd_id_client = "0";

		if (S_INN != null) {
			// SQL ������ ������������ ��_������� �� ���
			String sql = "select ID_FIRMY from ECFIL002 where INN = '" + S_INN + "'";
			OraConn.SetStringSQL(url, name, password, sql);
			S_bd_id_client = (String) OraConn.getDannieZaprosa();
			OraConn.DropDannieZaprosa();
			//System.out.println("���������� SQL " + OraConn.S_sql);
		}
		return S_bd_id_client;
	}

	/**
	 * ����� ��������� String ��������� � ������� ��� � ���������� String ��������_������ 
	 * @param S_INN - String ��� �������
	 * @return - String ��������_������ 
	 */
	private static String ZaprosNameClient(String S_INN) {

		// String url = "localhost:1521:MAGICASH";
		// String name = "MAGICASH5_498";
		// String password = "NCTMAGICASH5";

		String S_bd_client_name = "0";
		if (S_INN != null) {
			System.out.println("Connection url: " + url + ", " + name + ", " + password);
			// SQL ������ ������������ ��������_������� �� ���
			String sql = "select NAME from ECFIL002 where INN = '" + S_INN + "'";
			OraConn.SetStringSQL(url, name, password, sql);
			S_bd_client_name = (String) OraConn.getDannieZaprosa();
			OraConn.DropDannieZaprosa();
			//System.out.println("���������� SQL " + OraConn.S_sql);
		}
		return S_bd_client_name;
	}

	/**
	 * ����� ��������� � ������ ������ � ��������� ������� (��� JTable)
	 */
	private static void MassivTitle() {
		// ��������� ������ �������� ��������
		arrList_kolonki.add("�� �������");
		arrList_kolonki.add("������");
		arrList_kolonki.add("����");
		arrList_kolonki.add("����� ��");
		arrList_kolonki.add("�����"); // � ������ ��� �������
	}

}
