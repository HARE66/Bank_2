package VladimiPrilepskiy;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Класс для добавления ПП в таблицу ПЦ
 * Имеет один метод принимающий одномерную коллекцию.
 * @author Vladimir Prilepskiy
 *
 */
public class ImpPetrolPC {
	
	/**
	 * Метод принимает двумерный массив и добавляет данные недостающие в таблицу SPC_MONEY_ORDER
	 * выкидывает диалоговое окно о кол-ве добавленных записях
	 * @param KakoitoMassiv
	 */
	public static void insertPaymentPC(ArrayList<ArrayList<String>> KakoitoMassiv) {

		int i_updateCount = 0;
		
		//Стоки соединения с ПЦ
		//String url = "localhost:1521:MAGICASH";
		//String name = "MAGICASH5_498";
		//String password = "NCTMAGICASH5";

		String url = FileWriterImportTXT.ReaderFile("ConfConnURL_PC.cfg", false);
		String name = FileWriterImportTXT.ReaderFile("ConfConnName_PC.cfg", false);
		String password = FileWriterImportTXT.ReaderFile("ConfConnPass_PC.cfg", false);
		
		System.out.println("Connection url: " + url + ", " + name + ", " + password);
				
		// Читаем массив
		for (int i = 0; i < KakoitoMassiv.size(); i++) {
			String S_sekciyaDocument = KakoitoMassiv.get(i).get(0);
			String S_ppNomer = KakoitoMassiv.get(i).get(1);
			String S_date = KakoitoMassiv.get(i).get(2);
			String S_summa = KakoitoMassiv.get(i).get(4);
			String S_inn = KakoitoMassiv.get(i).get(5);	// 7 заменил на 5
			System.out.println(S_sekciyaDocument + S_ppNomer + S_date + S_summa + S_inn);
			
			//создаем запрос
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
		JOptionPane.showMessageDialog(null, "Добавлено записей в ПЦ: " + i_updateCount);
	}
}

/* пример sql запроса в ПЦ5805
insert into SPC_MONEY_ORDER (SEKCIYA_DOC, NOMER, DATA_PP, SUMMA, ID_CLIENT, INN, KEY_UNIQUE)
select 'Четвертое тестовое ПП', 123456, to_date('25.03.2016', 'dd.mm.yyyy'), -50000.00, (select cl.id_client from pc_clients cl where cl.inn = '6625035086'), 6625035086, '4'
from dual where not exists (select 1 from SPC_MONEY_ORDER where key_unique = '4')
*/