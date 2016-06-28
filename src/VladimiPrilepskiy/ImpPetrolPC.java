package VladimiPrilepskiy;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * ����� ��� ���������� �� � ������� ��
 * ����� ���� ����� ����������� ���������� ���������.
 * @author Vladimir Prilepskiy
 *
 */
public class ImpPetrolPC {
	
	/**
	 * ����� ��������� ��������� ������ � ��������� ������ ����������� � ������� SPC_MONEY_ORDER
	 * ���������� ���������� ���� � ���-�� ����������� �������
	 * @param KakoitoMassiv
	 */
	public static void insertPaymentPC(ArrayList<ArrayList<String>> KakoitoMassiv) {

		int i_updateCount = 0;
		
		//����� ���������� � ��
		//String url = "localhost:1521:MAGICASH";
		//String name = "MAGICASH5_498";
		//String password = "NCTMAGICASH5";

		String url = FileWriterImportTXT.ReaderFile("ConfConnURL_PC.cfg", false);
		String name = FileWriterImportTXT.ReaderFile("ConfConnName_PC.cfg", false);
		String password = FileWriterImportTXT.ReaderFile("ConfConnPass_PC.cfg", false);
		
		System.out.println("Connection url: " + url + ", " + name + ", " + password);
				
		// ������ ������
		for (int i = 0; i < KakoitoMassiv.size(); i++) {
			String S_sekciyaDocument = KakoitoMassiv.get(i).get(0);
			String S_ppNomer = KakoitoMassiv.get(i).get(1);
			String S_date = KakoitoMassiv.get(i).get(2);
			String S_summa = KakoitoMassiv.get(i).get(4);
			String S_inn = KakoitoMassiv.get(i).get(5);	// 7 ������� �� 5
			System.out.println(S_sekciyaDocument + S_ppNomer + S_date + S_summa + S_inn);
			
			//������� ������
			String sql = "insert into SPC_MONEY_ORDER (SEKCIYA_DOC, NOMER, DATA_PP, SUMMA, ID_CLIENT, INN, KEY_UNIQUE)"
+ " select " + "'" + S_sekciyaDocument + "'" + "," + S_ppNomer + "," + "to_date('" + S_date + "', 'dd.mm.yyyy')" + "," + S_summa + ","
+ "(select cl.id_client from pc_clients cl where cl.inn = '" + S_inn + "'), " + S_inn + "," + "'" + S_ppNomer + S_date + S_inn + "'"
+ "from dual where not exists (select 1 from SPC_MONEY_ORDER where key_unique = '" + S_ppNomer + S_date + S_inn + "')";
			System.out.println(sql);
			
			OraConn.DropDannieZaprosa();
			OraConn.SetStringSQL(url, name, password, sql);
			i_updateCount = i_updateCount + OraConn.getUpdateCount();
			OraConn.DropDannieZaprosa();
			
		}
		JOptionPane.showMessageDialog(null, "��������� ������� � ��: " + i_updateCount);
	}
}

/* ������ sql ������� � ��5805
insert into SPC_MONEY_ORDER (SEKCIYA_DOC, NOMER, DATA_PP, SUMMA, ID_CLIENT, INN, KEY_UNIQUE)
select '��������� �������� ��', 123456, to_date('25.03.2016', 'dd.mm.yyyy'), -50000.00, (select cl.id_client from pc_clients cl where cl.inn = '6625035086'), 6625035086, '4'
from dual where not exists (select 1 from SPC_MONEY_ORDER where key_unique = '4')
*/