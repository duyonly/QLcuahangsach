package Gui;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.*;

public class buttonedit extends DefaultCellEditor  {
    JButton nut;
    String label;
    JTable bang;
    
    public buttonedit(JCheckBox checkBox,JTable table){
        super(checkBox);
        
        this.bang=table;
        nut=new JButton();
        nut.setOpaque(true);
        nut.setBorderPainted(false);
        nut.addActionListener(e->fireEditingStopped());
    }
    public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelect,int hang,int cot){
        label=value.toString();
        nut.setText(label);
        if(label.equals("Đổi")){
            nut.setBackground(new Color(52,152,219));
            nut.setForeground(Color.WHITE);
        }else{
            nut.setBackground(new Color(231,76,60));
            nut.setForeground(Color.WHITE);
        }

        return nut;
    }
    public Object getCellEditorValue(){

        int hang = bang.getSelectedRow();

        String maHD = bang.getValueAt(hang,0).toString();
        String maSach = bang.getValueAt(hang,1).toString();

        if(label.equals("Đổi")){
            new FormDoi(maHD, maSach);
        }
        else if(label.equals("Trả")){
            new FormTra(maHD, maSach);
        }

        return label;
    }
}
