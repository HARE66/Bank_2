package VladimiPrilepskiy;

import java.util.ArrayList;

public class ReadArreyList {

	/**����� �� ��������� � �������. �� ����� ��� ������.
	 * ����� ������� ������ � ������� �� ArrayList<ArrayList<Object>>
	 * @param KakoitoMassiv
	 */
	public static void readArrey(ArrayList<ArrayList<String>> KakoitoMassiv) { 
		System.out.println("������ ������ : ");
		for (int i = 0; i < KakoitoMassiv.size(); i++) { // ������� �� �����
			for (int j = 0; j < KakoitoMassiv.get(i).size(); j++) {
				System.out.print(KakoitoMassiv.get(i).get(j) + "	");
			}
			System.out.println();
		}//i - ��������� j - ��������
	}
	
}
