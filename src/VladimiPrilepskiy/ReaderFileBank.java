package VladimiPrilepskiy;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * ����� ������������ ��� ���������� ����� �� ������ �����, � ���������� �������
 * � ������������� ���������� � ������ OraConn ��� ��������� ����������� (ID,
 * �������� �������) �� �� ������������� ������ Petrol5.
 * 
 * @author Vladimir Prilepskiy
 *
 */
public class ReaderFileBank {

	// ��������� ��������� 
	public static ArrayList<ArrayList<String>> arrList_platej = new ArrayList<ArrayList<String>>();
	// ������ �������� ��������
	public static ArrayList<String> arrList_kolonki = new ArrayList<String>();

	final public static String S_moi_inn = "6626013416"; // ��� ���

	/**
	 * ����� ��������� ������ �� ����� � ��������� �� � ArreyList
	 * @param inFileExpBank1C - String ���� � ����� ������� ���� ���������
	 * @throws HeadlessException 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void ReadFile(String inFileExpBank1C) throws HeadlessException, IOException {

		if (inFileExpBank1C != null) { //���� ������� ���� �� ����� null
			System.out.println("inFileExpBank1C != null");

			
			arrList_platej.clear();
			arrList_kolonki.clear();
			
			// ��������� ������ �������� ��������
			arrList_kolonki.add("��������������");
			arrList_kolonki.add("�����");
			arrList_kolonki.add("����");
			arrList_kolonki.add("��������");
			arrList_kolonki.add("�����_���_��"); // � ������ ��� �������
			//arrList_kolonki.add("ID_������_��");
			//arrList_kolonki.add("������_��");
			arrList_kolonki.add("���_��");
			arrList_kolonki.add("�����");
			arrList_kolonki.add("����������");
			arrList_kolonki.add("�������������");
			arrList_kolonki.add("����������");
			arrList_kolonki.add("�������������");
			arrList_kolonki.add("�����������������");

			FileReader f = new FileReader(inFileExpBank1C); //
			@SuppressWarnings("resource")
			BufferedReader b = new BufferedReader(f);

			String S_stroka;
			String S_sekciya_dokument = "��������������=";
			String S_sekciya_dokument_naiden = "�����";
			String S_nomer = "�����=";
			String S_nomer_naiden = "�����";
			String S_date = "����=";
			String S_date_naiden = "�����";

			String S_operaciya = "";
			String S_mnogetel_summi = "";

			//String S_bd_id_client = "0";
			//String S_bd_client_name = " ";

			String S_summa = "�����=";
			String S_summa_naiden = "�����";
			String S_platelhik = "����������=";
			String S_platelhik_naiden = "�����";
			String S_platelhik_inn = "�������������=";
			String S_platelhik_inn_naiden = "�����";
			String S_poluchatel = "����������=";
			String S_poluchatel_naiden = "�����";
			String S_poluchatel_inn = "�������������=";
			String S_poluchatel_inn_naiden = "�����";
			String S_naznachenie = "�����������������=";
			String S_naznachenie_naiden = "";
			String S_naznachenie1 = "�����������������1=";
			String S_naznachenie1_naiden = "";
			String S_naznachenie2 = "�����������������2=";
			String S_naznachenie2_naiden = "";
			String S_end_document = "��������������";
			//String S_end_document_naiden = "�����";

			int i_sekciya_dokument = 0;
			int i_nomer_stroki = 0;
			String S_INN_for_BD = "00000000";

			// ������ ��������� ����
			while ((S_stroka = b.readLine()) != null) {
				i_nomer_stroki++;
				//System.out.println("����� ������" + i_nomer_stroki + ": " + S_stroka);

				if (S_stroka.indexOf(S_sekciya_dokument) != -1) {
					i_sekciya_dokument++;
					//System.out.println(" +++++++++++++++++ ������� ����������: " + i_sekciya_dikument); // �����
																										// ����������
					S_sekciya_dokument_naiden = S_stroka.substring(S_sekciya_dokument.length());
					//System.out.println(S_sekciya_dikument_naiden);
				}

				if (S_stroka.indexOf(S_nomer) != -1) { // ����� ��
					S_nomer_naiden = S_stroka.substring(S_nomer.length());
					//System.out.println(" *********** " + S_nomer_naiden);
				}
				if (S_stroka.indexOf(S_date) != -1) { // ���� ��
					S_date_naiden = S_stroka.substring(S_date.length());
					//System.out.println(" *********** " + S_date_naiden);
				}
				if (S_stroka.indexOf(S_summa) != -1) { // ����� ��
					S_summa_naiden = S_stroka.substring(S_summa.length());
					//System.out.println(" *********** " + S_summa_naiden);
					//���� ������ 1000 � �� 1000.00 �� ��������� .00 (����)
					if(S_summa_naiden.lastIndexOf(".") == -1){
						S_summa_naiden = S_summa_naiden + ".00";}
				}
				if (S_stroka.indexOf(S_platelhik) != -1) { // ����������
					S_platelhik_naiden = S_stroka.substring(S_platelhik.length());
					//System.out.println(" *********** " + S_platelhik_naiden);
				}
				if (S_stroka.indexOf(S_platelhik_inn) != -1) { // �������������
					S_platelhik_inn_naiden = S_stroka.substring(S_platelhik_inn.length());
					//System.out.println(" *********** " + S_platelhik_inn_naiden);
				}
				if (S_stroka.indexOf(S_poluchatel) != -1) { // ����������
					S_poluchatel_naiden = S_stroka.substring(S_poluchatel.length());
					//System.out.println(" *********** " + S_poluchatel_naiden);
				}
				if (S_stroka.indexOf(S_poluchatel_inn) != -1) { // �������������
					S_poluchatel_inn_naiden = S_stroka.substring(S_poluchatel_inn.length());
					//System.out.println(" *********** " + S_poluchatel_inn_naiden);
				}
				if (S_stroka.indexOf(S_naznachenie) != -1) { // ����������
																// �������
					S_naznachenie_naiden = S_stroka.substring(S_naznachenie.length());
					//System.out.println(" *********** " + S_naznachenie_naiden);
				}
				if (S_stroka.indexOf(S_naznachenie1) != -1) { // ����������
																// �������
					S_naznachenie1_naiden = S_stroka.substring(S_naznachenie1.length());
					//System.out.println(" *********** " + S_naznachenie1_naiden);
				}
				if (S_stroka.indexOf(S_naznachenie2) != -1) { // ����������
																// �������
					S_naznachenie2_naiden = S_stroka.substring(S_naznachenie2.length());
					//System.out.println(" *********** " + S_naznachenie2_naiden);
				}
				/*
				 * ����� ��������� (��� ����������� �����, ���������� ������ ��
				 * ���������� � ArrayList)
				 */
				if (S_stroka.indexOf(S_end_document) != -1) {
					//S_end_document_naiden = S_stroka.substring(S_end_document.length());
					//System.out.println(" *********** " + S_end_document_naiden);

					// �������� ��� ���� ������
					//���� ����������� = ������
					if (S_platelhik_inn_naiden.equals(S_moi_inn)) {
						S_mnogetel_summi = "-";
						S_operaciya = "0";
						S_INN_for_BD = S_poluchatel_inn_naiden;
					}
					// ���� ������������� = ������
					if (S_poluchatel_inn_naiden.equals(S_moi_inn)) { 
						S_mnogetel_summi = "";
						S_operaciya = "1";
						S_INN_for_BD = S_platelhik_inn_naiden;
					}
					//���� S_INN_for_BD = ������� �� ����� ����� ������ ����
					if (S_INN_for_BD.equals("") == true){
						S_INN_for_BD = JOptionPane.showInputDialog(null,
								S_sekciya_dokument_naiden + " �" + S_nomer_naiden + " �� " + S_date_naiden + "; "
										+ S_summa_naiden + "\n" + "����������: " + S_naznachenie_naiden + S_naznachenie1_naiden + S_naznachenie2_naiden + "\n" + "����������: " + S_platelhik_naiden + "\n" + "����������: " + S_poluchatel_naiden, "������� ��� �������", 3);
						//���� ������� ������� ��� ������ ������ Cancel
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

					// SQL ������ ������������ ��_������� �� ���
					sql = "select ID_FIRMY from ECFIL002 where INN = '" + S_INN_for_BD + "'";
					OraConn.SetStringSQL(url, name, password, sql);
					S_bd_id_client = (String) OraConn.getDannieZaprosa();
					OraConn.DropDannieZaprosa();
					System.out.println("���������� SQL " + OraConn.S_sql);

					// SQL ������ ������������ ��������_������� �� ���
					sql = "select NAME from ECFIL002 where INN = '" + S_INN_for_BD + "'";
					OraConn.SetStringSQL(url, name, password, sql);
					S_bd_client_name = (String) OraConn.getDannieZaprosa();
					OraConn.DropDannieZaprosa();
					System.out.println("���������� SQL " + OraConn.S_sql);*/

					ArrayList<String> row = new ArrayList<String>(); // ���������
																		// ������

					// ��������� ������ �����������
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

					// ��������� ������ � ArreyList
					arrList_platej.add(row);

					if (S_stroka.indexOf(S_end_document) != -1) { // ��������
																	// ����������
						//System.out.println(" ����� ���� �������� ��� ���������� ���������� ");
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
			JOptionPane.showMessageDialog(null, "���������� ����� � �����: " + i_nomer_stroki + '\n' + "������� " + S_sekciya_dokument + " " + i_sekciya_dokument);
		} else {
			JOptionPane.showMessageDialog(null, "���� = null");
		}
	};

}
