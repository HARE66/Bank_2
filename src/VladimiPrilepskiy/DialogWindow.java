package VladimiPrilepskiy;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * ����� ������������ ��� ������ ����������� ���� ������ �����.
 * 
 * @author Vladimir Prilepskiy
 *
 */
public class DialogWindow {

	private static String put_k_failu;

	/**
	 * ����� ��������� String ���������� � ��������� �� ��������� (����� ����
	 * ������� ������� � ������) � ���������� String ���������� - ����������
	 * ���� � �����
	 * 
	 * @param KatalogPoUmochaniu
	 *            - String ����������
	 * @return String ���������� - ���� � ���������� �����
	 */

	public static String DialogWin(String KatalogPoUmochaniu) {
		JFileChooser fileopen = new JFileChooser(KatalogPoUmochaniu); // ������
																		// �������
					 													// ��
																		// ���������
		int ret = fileopen.showDialog(null, "������� ����");
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fileopen.getSelectedFile();
			System.out.println(file.getAbsolutePath());
			put_k_failu = file.getAbsolutePath();
		}
		return put_k_failu;
	}

}
