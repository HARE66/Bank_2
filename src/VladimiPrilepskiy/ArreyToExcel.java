package VladimiPrilepskiy;

import java.util.ArrayList;
/**
 * Класс для сохранения TableModel в Excel
 * @author Java
 *
 */
public class ArreyToExcel {

	
	public static void SaveToExcel (){
		String S_kolonki = readArrey(TableModelAll.arrList_Kolonki);
		//System.out.println(S_kolonki);
		
		String S_table = readArreyArrey(TableModelAll.arrList_Dannie);
		//System.out.println(S_table);
		
		FileWriterImportTXT.WriterFile("TableModel.xls", S_kolonki + S_table, false);
	}
	
	private static String readArrey(ArrayList<String> KakoitoMassiv) {
		String Arrey = "";
		for (int i = 0; i < KakoitoMassiv.size(); i++) { // выводим на экран
			Arrey = Arrey + KakoitoMassiv.get(i) + "	";
		}
		Arrey = Arrey + "\n";
		return Arrey;
	}

	/**
	 * Метод выводит данные в консоль из ArrayList<ArrayList<Object>>
	 * 
	 * @param KakoitoMassiv
	 * @return 
	 */
	private static String readArreyArrey(ArrayList<ArrayList<String>> KakoitoMassiv) {
		String ArreyArrey = "";
		for (int i = 0; i < KakoitoMassiv.size(); i++) { // выводим на экран
			String S_row = "";
			for (int j = 0; j < KakoitoMassiv.get(i).size(); j++) {
				S_row = S_row + KakoitoMassiv.get(i).get(j) + "	";
			}
			ArreyArrey = ArreyArrey + S_row + "\n";
		} // i - вертикаль j - горизонт
		return ArreyArrey;
	}

}
