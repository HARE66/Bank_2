package VladimiPrilepskiy;

import java.io.IOException;
import java.sql.SQLException;

/**
 * ������� �����
 * @author Vladimir Prilepskiy
 *
 */
public class MainClass {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		//OraConn.SetStringSQL("select * from ECFIL002 t where t.id_phone = 01231"); // �������� SQL ������
		//Object Obj = OraConn.getDannieZaprosa(); // ������� � ���������� ���������
		//System.out.println(Obj); 
		
		//ArrayList<ArrayList<Object>> KakoitoMassiv = OraConn.getArrayList_DannieZaprosa(); // ������� � ���������� ��������� (������)
		//ReadArreyList.readArrey(KakoitoMassiv); //������� ���������� � �������� � ����� ��� ������
		
		//ReaderFileBank.ReadFile();
		//ReadArreyList.readArrey(ReaderFileBank.arrList_platej);
		//OraConn.SetStringSQL(null);
		//System.out.println(OraConn.getDannieZaprosa());
		//ImpPetrol.ImportOC(ReaderFileBank.arrList_platej);
		
		GeneralFrame.mainFrame();
		
		//System.out.println(DialogWindow.DialogWin("C:\\"));
		//DialogWindow.DialogWin(null);
		
		//ReaderFileBank.ReadFile("C:\\������� �� �����\\����\\ubrr.txt");
		//System.out.println(ImpPetrol.ImportOC(ReaderFileBank.arrList_platej));	
		
	//String url = FileWriterImportTXT.ReaderFile("ConfConnURL_OC.cfg", false);
	//String name = FileWriterImportTXT.ReaderFile("ConfConnName_OC.cfg", false);
	//String password = FileWriterImportTXT.ReaderFile("ConfConnPass_OC.cfg", false);
		
	//System.out.println("Connection url: " + url + ", " + name + ", " + password);
		
	 //url = FileWriterImportTXT.ReaderFile("ConfConnURL_PC.cfg", false);
	// name = FileWriterImportTXT.ReaderFile("ConfConnName_PC.cfg", false);
	// password = FileWriterImportTXT.ReaderFile("ConfConnPass_PC.cfg", false);
		
	//System.out.println("Connection url: " + url + ", " + name + ", " + password);
	
	}

}

//select ID_FIRMY from ECFIL002 where INN = '123456789'
//