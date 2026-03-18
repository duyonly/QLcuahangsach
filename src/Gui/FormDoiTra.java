package Gui;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.awt.event.*;

import Bus.DoiTraBus;
import Dto.DoiTraDto;
import Dto.chitiethoadon;
import java.awt.*;
public class FormDoiTra extends JPanel {
    JTextField txtMaHD,txtMaSach,txtSoLuong;
    JTextArea txtLyDo;
    JButton btnTim;
    JTable table;
    DefaultTableModel model;
    DoiTraBus Bus=new DoiTraBus();
     public FormDoiTra() {
      
        setLayout(new BorderLayout());
        JPanel top = new JPanel();
        top.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel("Mã Hóa Đơn");
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        
        txtMaHD = new JTextField(15);
        txtMaHD.setFont(new Font("Arial", Font.PLAIN, 14));
        
        btnTim = new JButton("Tìm");
        btnTim.setFocusPainted(false);
        btnTim.setBackground(new Color(52,152,219));
        btnTim.setForeground(Color.WHITE);
        
        top.add(lbl);
        top.add(txtMaHD);
        top.add(btnTim);
        
        add(top, BorderLayout.NORTH);

        String[] cot={"Mã HD","Mã Sách","Số Lượng","Dơn Giá","Thành Tiền","Đổi","Trả"};
        model = new DefaultTableModel(cot,0);
        table =new JTable(model);
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane Scroll=new JScrollPane(table);
        Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
add(Scroll, BorderLayout.CENTER);
       
        table.setRowHeight(28);
table.setFont(new Font("Arial", Font.PLAIN, 14));
table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
table.getTableHeader().setBackground(new Color(41,128,185));
table.getTableHeader().setForeground(Color.WHITE);
table.setSelectionBackground(new Color(52,152,219));
table.setGridColor(new Color(220,220,220));
DefaultTableCellRenderer center = new DefaultTableCellRenderer();
center.setHorizontalAlignment(JLabel.CENTER);

for(int i=0;i<table.getColumnCount();i++){
    table.getColumnModel().getColumn(i).setCellRenderer(center);

}
table.getColumn("Đổi").setCellRenderer(new buttonrender());
table.getColumn("Đổi").setCellEditor(new buttonedit(new JCheckBox(),table));

table.getColumn("Trả").setCellRenderer(new buttonrender());
table.getColumn("Trả").setCellEditor(new buttonedit(new JCheckBox(),table));

        bangCTHD(Bus.CThoadon(""));
        btnTim.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e){
                timKiem();
            }
        });
    }
    private void timKiem(){
        String MHD=txtMaHD.getText();
        bangCTHD(Bus.CThoadon(MHD));
    }

private void bangCTHD(ArrayList<chitiethoadon> cthd){
model.setRowCount(0);
for(chitiethoadon ct : cthd){
    model.addRow(new Object[]{
        ct.getMaHD(),
        ct.getMaSach(),
        ct.getSoLuong(),
        ct.getDonGia(),
        ct.getThanhTien(),
        "Đổi",
       "Trả"
    });
}
}
}