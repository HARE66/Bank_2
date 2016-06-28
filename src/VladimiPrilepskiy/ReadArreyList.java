package VladimiPrilepskiy;

import java.util.ArrayList;

public class ReadArreyList {

	/**Метод не участвует в проекте. Он нужен для дебага.
	 * Метод выводит данные в консоль из ArrayList<ArrayList<Object>>
	 * @param KakoitoMassiv
	 */
	public static void readArrey(ArrayList<ArrayList<String>> KakoitoMassiv) { 
		System.out.println("Читаем массив : ");
		for (int i = 0; i < KakoitoMassiv.size(); i++) { // выводим на экран
			for (int j = 0; j < KakoitoMassiv.get(i).size(); j++) {
				System.out.print(KakoitoMassiv.get(i).get(j) + "	");
			}
			System.out.println();
		}//i - вертикаль j - горизонт
	}
	
}
