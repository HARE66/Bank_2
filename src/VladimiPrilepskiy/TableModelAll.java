package VladimiPrilepskiy;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;



public class TableModelAll implements TableModel {

	//������ �������� ������
	public static ArrayList<ArrayList<String>> arrList_Dannie = new ArrayList<ArrayList<String>>();
	// ������ �������� ��������
	public static ArrayList<String> arrList_Kolonki = new ArrayList<String>();	
	

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return Object.class;
	}

	@Override
	public int getColumnCount() {
		//���-�� ��������
		return arrList_Kolonki.size(); 
	}

	@Override
	public String getColumnName(int columnIndex) {
		// ������������ ��������
		return arrList_Kolonki.get(columnIndex);
	}

	@Override
	public int getRowCount() {
		// ���������� �����
		return arrList_Dannie.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// �������
		return arrList_Dannie.get(rowIndex).get(columnIndex);
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
