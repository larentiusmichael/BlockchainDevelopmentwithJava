package UI;

import DataControl.DateTimeConverter;
import DataControl.EventData;
import Dataset.DatasetIndex;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Stack;

public class ViewEventGUI extends JFrame {
    private JButton backButton;
    private JTable dataTable;
    private JPanel mainPanel;
    private Stack<String[]> allData = new Stack<>();
    private int totalData = 0;
    private String[] colName = {"Event ID", "Name", "Start Date", "End Date", "Location"};

    public ViewEventGUI(String title, GUIInterchange guiInterchange) {
        setContentPane(mainPanel);
        setTitle(title);
        setSize(470,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        File file = new File(DatasetIndex.eventFile);
        if(file.exists()) {
            EventData data = new EventData();
            allData = data.getEventData();
            totalData = allData.size();

            showTable(allData);
        }
        else {
            totalData = 0;
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiInterchange.mainPage(guiInterchange);
                dispose();
            }
        });
    }

    private void showTable(Stack<String[]> tableData) {
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        TableModel dataModel = new TableModel() {
            @Override
            public int getRowCount() {
                return tableData.size();
            }

            @Override
            public int getColumnCount() {
                return colName.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return colName[columnIndex].toString();
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0, columnIndex).getClass();
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {

                int realCol=columnIndex;
//                if(columnIndex >= 3) {
//                    realCol = columnIndex + 1;
//                }
//                else {
//                    realCol = columnIndex;
//                }

                if(realCol >=2 && realCol<=3) {
                    return dateTimeConverter.getFormattedDate(tableData.get(rowIndex)[realCol]);
                }
                else {
                    return tableData.get(rowIndex)[realCol];
                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

                int realCol=columnIndex;
//                if(columnIndex >= 3) {
//                    realCol = columnIndex + 1;
//                }
//                else {
//                    realCol = columnIndex;
//                }

                tableData.get(rowIndex)[realCol] = (String) aValue;
            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        };
        dataTable.setModel(dataModel);
    }
}
