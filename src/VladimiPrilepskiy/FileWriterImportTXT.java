package VladimiPrilepskiy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * ����� ������������ ��� ������ � ������ ������.
 * �������� ��� ������:
 * riterFile(String KudaZapisat, String ChtoZapisat, boolean Dozapisat)
 * ReaderFile(String FilePut, boolean b_Karetka) 
 * @author VladimirPrilepskiy
 */


public class FileWriterImportTXT {

	/**
	 * ����� ��� ������ String ���������� � ����. ��������� ��� ��������.
	 * @param KudaZapisat - String ����������, ������: "c:/������� �� �����/import.txt"
	 * @param ChtoZapisat - String ����������, ������: "����� ������ �����"
	 * @param Dozapisat - boolean ����������. (true-���������� � ����)
	 */
	public static void WriterFile(String KudaZapisat, String ChtoZapisat, boolean Dozapisat) {

		FileWriter f;
		try {
			f = new FileWriter(KudaZapisat, Dozapisat);
			f.write(ChtoZapisat);
			f.append('\n');
			f.flush();
			f.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "������ ������ �����! :" + e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ����� ��� ������ �����. ��������� ��� �������� � ���������� String ����������.
	 * @param FilePut - String ����������. ������ ���� � �����.
	 * @param b_Karetka - boolean ����������. ���������
	 * @return
	 */
	public static String ReaderFile(String FilePut, boolean b_Karetka) {
		String S_Karetka = "";
		if(b_Karetka == true){S_Karetka = "\n";}
		String S_Text = "";
		String S_stroka = "";
			try {
				FileReader f = new FileReader(FilePut);
				@SuppressWarnings("resource")
				BufferedReader b = new BufferedReader(f);
			
				while ((S_stroka = b.readLine()) != null) {
					S_Text = S_Text + S_stroka + S_Karetka;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "������ ������ �����! :" + e);
				e.printStackTrace();
			}
		return S_Text;
	}
}

