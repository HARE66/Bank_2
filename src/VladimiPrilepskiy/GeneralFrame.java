package VladimiPrilepskiy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GeneralFrame extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();

	/**
	 * Launch the application.
	 */
	public static void mainFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneralFrame frame = new GeneralFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GeneralFrame() {
		setTitle("Export Bank 2 for Petrol Plus (2016 version 1.0)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 996, 369);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Open and Read File");
		btnNewButton.setToolTipText("\u041F\u0440\u043E\u0447\u0438\u0442\u0430\u0442\u044C \u0444\u0430\u0439\u043B \u044D\u043A\u0441\u043F\u043E\u0440\u0442\u0430 \u0434\u043B\u044F 1\u0421 \u0438\u0437 \u043A\u043B\u0438\u0435\u043D\u0442 \u0431\u0430\u043D\u043A\u0430.");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JButtonActionPerformed(arg0);
				table = new JTable(new TableModelAll());
				scrollPane.setViewportView(table);
				//scrollPane.repaint();
				
		//		table.getColumnModel().getColumn(0).setMinWidth(200);
		//		table.getColumnModel().getColumn(0).setMaxWidth(200);
			
			}
		});
		btnNewButton.setBounds(10, 391, 166, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Write File Export for OC 0805");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				NewButton_Write_File_0805(arg0);
				
				TableModelAll.arrList_Kolonki = null;
				TableModelAll.arrList_Kolonki = ImpPetrol.arrList_kolonki;
				TableModelAll.arrList_Dannie = null;
				TableModelAll.arrList_Dannie = ImpPetrol.arrList_platej;
				
				table = new JTable(new TableModelAll());
				scrollPane.setViewportView(table);
			}
		});
		btnNewButton_1.setToolTipText("\u042D\u043A\u0441\u043F\u043E\u0440\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C \u0432 txt \u0444\u0430\u0439\u043B \u0434\u043B\u044F \u043F\u0440\u0438\u0435\u043C\u0430 \u0432 \u041E\u0426");
		btnNewButton_1.setBounds(806, 425, 200, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exp Payment in PC 5805");
		btnNewButton_2.setToolTipText("\u042D\u043A\u0441\u043F\u043E\u0440\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C \u041F\u041F \u0432 \u0431\u0430\u0437\u0443 \u041F\u0426");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImpPetrolPC.insertPaymentPC(ReaderFileBank.arrList_platej);
			}
		});
		btnNewButton_2.setBounds(806, 391, 200, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Write File Export for OC 0254");
		btnNewButton_3.setToolTipText("\u042D\u043A\u0441\u043F\u043E\u0440\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C \u0432 txt \u0444\u0430\u0439\u043B \u0434\u043B\u044F \u043F\u0440\u0438\u0435\u043C\u0430 \u0432 \u041E\u0426");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NewButton_Write_File_0254(e);
				
				TableModelAll.arrList_Kolonki = null;
				TableModelAll.arrList_Kolonki = ImpPetrol.arrList_kolonki;
				TableModelAll.arrList_Dannie = null;
				TableModelAll.arrList_Dannie = ImpPetrol.arrList_platej;
				
				table = new JTable(new TableModelAll());
				scrollPane.setViewportView(table);
				
			}
		});
		btnNewButton_3.setBounds(806, 459, 200, 23);
		contentPane.add(btnNewButton_3);
		
		JButton toExcel = new JButton("Exp table to Excel");
		toExcel.setToolTipText("\u042D\u043A\u0441\u043F\u043E\u0440\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C \u043E\u0442\u043E\u0431\u0440\u0430\u0436\u0430\u0435\u043C\u0443\u044E \u0442\u0430\u0431\u043B\u0438\u0446\u0443 \u0432 \u0444\u0430\u0439\u043B xls (\u0432 \u043A\u043E\u0440\u043D\u0435 \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u044B)");
		toExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArreyToExcel.SaveToExcel();
			}
		});
		toExcel.setBounds(806, 532, 200, 23);
		contentPane.add(toExcel);
	}

	

	/*ќтдельно выненсены методы кнопок*/

	//private boolean B_JButtonActionPerformed = false; //переменна€ нажатой кнопки
	protected void JButtonActionPerformed(ActionEvent arg0){ // метод кнопки "Read File"
		B_NewButton_Write_File_0805 = false;
		B_NewButton_Write_File_0254 = false;
		//if(B_JButtonActionPerformed == false){ //чтобы можно было нажать только один раз
			String KatalogPoUmochaniu = FileWriterImportTXT.ReaderFile("Put.cfg", false);
			String PutKfailu = DialogWindow.DialogWin(KatalogPoUmochaniu);
			FileWriterImportTXT.WriterFile("Put.cfg", PutKfailu, false);
			try {
				ReaderFileBank.ReadFile(PutKfailu);
				TableModelAll.arrList_Kolonki = null;
				TableModelAll.arrList_Kolonki = ReaderFileBank.arrList_kolonki;
				TableModelAll.arrList_Dannie = null;
				TableModelAll.arrList_Dannie = ReaderFileBank.arrList_platej;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//B_JButtonActionPerformed = true;
		//}else{
			//JOptionPane.showMessageDialog(contentPane, "‘айл уже был открыт");
		//}
	}
	
	private boolean B_NewButton_Write_File_0805 = false; //переменна€ нажатой кнопки
	
	protected void NewButton_Write_File_0805(ActionEvent arg0) {
		if(B_NewButton_Write_File_0805 == false){
			String url = FileWriterImportTXT.ReaderFile("ConfConnURL_OC_0805.cfg", false);
			String name = FileWriterImportTXT.ReaderFile("ConfConnName_OC_0805.cfg", false);
			String password = FileWriterImportTXT.ReaderFile("ConfConnPass_OC_0805.cfg", false);
			String ChtoZapisat = ImpPetrol.ImportOC(ReaderFileBank.arrList_platej, url, name, password);
			System.out.println("805");
			FileWriterImportTXT.WriterFile("importOC_0805.txt", ChtoZapisat, true);
			JOptionPane.showMessageDialog(contentPane, ChtoZapisat);
			B_NewButton_Write_File_0805 = true;
		}else{
			JOptionPane.showMessageDialog(contentPane, "Ёкспорт уже выполнен");
		}
		
	}
	
	private boolean B_NewButton_Write_File_0254 = false; //переменна€ нажатой кнопки
	
	protected void NewButton_Write_File_0254(ActionEvent e) {
		if(B_NewButton_Write_File_0254 == false){
			String url = FileWriterImportTXT.ReaderFile("ConfConnURL_OC_0254.cfg", false);
			String name = FileWriterImportTXT.ReaderFile("ConfConnName_OC_0254.cfg", false);
			String password = FileWriterImportTXT.ReaderFile("ConfConnPass_OC_0254.cfg", false);
			String ChtoZapisat = ImpPetrol.ImportOC(ReaderFileBank.arrList_platej, url, name, password);
			System.out.println("254");
			FileWriterImportTXT.WriterFile("importOC_0254.txt", ChtoZapisat, true);
			JOptionPane.showMessageDialog(contentPane, ChtoZapisat);
			B_NewButton_Write_File_0254 = true;
		}else{
			JOptionPane.showMessageDialog(contentPane, "Ёкспорт уже выполнен");
		}
	}
}
