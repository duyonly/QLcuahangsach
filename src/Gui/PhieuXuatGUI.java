package Gui;

import Bus.PhieuXuatBUS;
import Dto.PhieuXuatDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class PhieuXuatGUI extends JFrame{

    JTextField txtMa, txtTrangThai, txtNguoiTao, txtMaKH;
    JTable table;
    DefaultTableModel model;

    PhieuXuatBUS bus = new PhieuXuatBUS();

    public PhieuXuatGUI(){

        setTitle("Quản Lý Phiếu Xuất");
        setSize(800,500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5,2));

        panel.add(new JLabel("Mã Phiếu Xuất"));
        txtMa = new JTextField();
        panel.add(txtMa);

        panel.add(new JLabel("Trạng Thái"));
        txtTrangThai = new JTextField();
        panel.add(txtTrangThai);

        panel.add(new JLabel("Người Tạo"));
        txtNguoiTao = new JTextField();
        panel.add(txtNguoiTao);

        panel.add(new JLabel("Mã Khách Hàng"));
        txtMaKH = new JTextField();
        panel.add(txtMaKH);

        add(panel,BorderLayout.WEST);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Mã PX","Thời Gian","Trạng Thái","Người Tạo","Mã KH"
        });

        table = new JTable(model);

        add(new JScrollPane(table),BorderLayout.CENTER);

        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JButton btnLoad = new JButton("Load");
        JButton btnChiTiet = new JButton("Xem Chi Tiết");

        JPanel p = new JPanel();
        p.add(btnThem);
        p.add(btnXoa);
        p.add(btnLoad);
        p.add(btnChiTiet);

        add(p,BorderLayout.SOUTH);

        btnLoad.addActionListener(e -> loadData());

        btnThem.addActionListener(e -> {

            PhieuXuatDTO px = new PhieuXuatDTO();

            px.setMaphieuxuat(txtMa.getText());
            px.setTrangthai(txtTrangThai.getText());
            px.setNguoitaophieuxuat(txtNguoiTao.getText());
            px.setMakhachhang(txtMaKH.getText());
            px.setThoigian(new Date());

            bus.add(px);

            loadData();
        });

        btnXoa.addActionListener(e -> {

            bus.delete(txtMa.getText());

            loadData();
        });

        setVisible(true);
        btnChiTiet.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(null,"Hãy chọn phiếu xuất");
                return;
            }

            String maPX = table.getValueAt(row,0).toString();

            new ChiTietPhieuXuatGUI(maPX);

        });
    }

    void loadData(){

        model.setRowCount(0);

        for(PhieuXuatDTO px : bus.getAll()){

            model.addRow(new Object[]{
                    px.getMaphieuxuat(),
                    px.getThoigian(),
                    px.getTrangthai(),
                    px.getNguoitaophieuxuat(),
                    px.getMakhachhang()
            });
        }
    }
}