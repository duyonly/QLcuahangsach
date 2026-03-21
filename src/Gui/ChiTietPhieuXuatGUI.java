package Gui;

import Bus.ChiTietPhieuXuatBUS;
import Dto.ChiTietPhieuXuatDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ChiTietPhieuXuatGUI extends JFrame {

    JTextField txtMaPX, txtMaSP, txtSoLuong, txtDonGia;
    JTable table;
    DefaultTableModel model;

    ChiTietPhieuXuatBUS bus = new ChiTietPhieuXuatBUS();
    String maPX;

    public ChiTietPhieuXuatGUI(String maPX){

        this.maPX = maPX;

        setTitle("Chi tiết phiếu xuất - " + maPX);
        setSize(800,500);
        setLocationRelativeTo(null);

        // ===== FORM =====
        JPanel panel = new JPanel(new GridLayout(4,2));

        panel.add(new JLabel("Mã Phiếu Xuất"));
        txtMaPX = new JTextField(maPX); // tự fill
        txtMaPX.setEditable(false); // không cho sửa
        panel.add(txtMaPX);

        panel.add(new JLabel("Mã Sản Phẩm"));
        txtMaSP = new JTextField();
        panel.add(txtMaSP);

        panel.add(new JLabel("Số Lượng"));
        txtSoLuong = new JTextField();
        panel.add(txtSoLuong);

        panel.add(new JLabel("Đơn Giá"));
        txtDonGia = new JTextField();
        panel.add(txtDonGia);

        add(panel, BorderLayout.WEST);

        // ===== TABLE =====
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Mã PX","Mã SP","Số Lượng","Đơn Giá"
        });

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BUTTON =====
        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");

        JPanel p = new JPanel();
        p.add(btnThem);
        p.add(btnXoa);

        add(p, BorderLayout.SOUTH);

        // ===== EVENT =====

        // Thêm
        btnThem.addActionListener(e -> {
            try{
                ChiTietPhieuXuatDTO ct = new ChiTietPhieuXuatDTO();

                ct.setMaphieuxuat(maPX);
                ct.setMasanpham(txtMaSP.getText());
                ct.setSoluong(Integer.parseInt(txtSoLuong.getText()));
                ct.setDongia(Double.parseDouble(txtDonGia.getText()));

                if(bus.add(ct)){
                    JOptionPane.showMessageDialog(this,"Thêm thành công!");
                    loadData();
                }else{
                    JOptionPane.showMessageDialog(this,"Thất bại!");
                }

            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,"Sai dữ liệu!");
            }
        });

        // Xóa
        btnXoa.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(this,"Chọn dòng!");
                return;
            }

            String masp = table.getValueAt(row,1).toString();

            if(bus.delete(maPX, masp)){
                JOptionPane.showMessageDialog(this,"Xóa thành công!");
                loadData();
            }
        });

        // Click table
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e){
                int row = table.getSelectedRow();

                txtMaSP.setText(String.valueOf(model.getValueAt(row,1)));
                txtSoLuong.setText(String.valueOf(model.getValueAt(row,2)));
                txtDonGia.setText(String.valueOf(model.getValueAt(row,3)));
            }
        });

        // Load data ban đầu
        loadData();

        setVisible(true);
    }

    void loadData(){

        model.setRowCount(0);

        for(ChiTietPhieuXuatDTO ct : bus.getByMaPhieuXuat(maPX)){

            model.addRow(new Object[]{
                    ct.getMaphieuxuat(),
                    ct.getMasanpham(),
                    ct.getSoluong(),
                    ct.getDongia()
            });
        }
    }
}