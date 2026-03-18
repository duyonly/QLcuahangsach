package Gui;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class buttonrender extends JButton implements TableCellRenderer{
    public buttonrender(){
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table,Object value,
            boolean isSelected,boolean hasFocus,int row,int column){

        setText(value.toString());

        if(value.toString().equals("Đổi")){
            setBackground(new Color(52,152,219));
            setForeground(Color.WHITE);
        }else{
            setBackground(new Color(231,76,60));
            setForeground(Color.WHITE);
        }

        return this;
    }
}
