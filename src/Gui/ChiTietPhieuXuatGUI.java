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

    public ChiTietPhieuXuatGUI(){

        setTitle("Chi Tiết Phiếu Xuất");
        setSize(800,500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4,2));

        panel.add(new JLabel("Mã Phiếu Xuất"));
        txtMaPX = new JTextField();
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

        add(panel,BorderLayout.WEST);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Mã PX","Mã SP","Số Lượng","Đơn Giá"
        });

        table = new JTable(model);

        add(new JScrollPane(table),BorderLayout.CENTER);

        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JButton btnLoad = new JButton("Load");

        JPanel p = new JPanel();
        p.add(btnThem);
        p.add(btnXoa);
        p.add(btnLoad);

        add(p,BorderLayout.SOUTH);

        btnLoad.addActionListener(e -> loadData());

        btnThem.addActionListener(e -> {

            ChiTietPhieuXuatDTO ct = new ChiTietPhieuXuatDTO();

            ct.setMaphieuxuat(txtMaPX.getText());
            ct.setMasanpham(txtMaSP.getText());
            ct.setSoluong(Integer.parseInt(txtSoLuong.getText()));
            ct.setDongia(Double.parseDouble(txtDonGia.getText()));

            bus.add(ct);

            loadData();
        });

        btnXoa.addActionListener(e -> {

            bus.delete(txtMaPX.getText(), txtMaSP.getText());

            loadData();
        });

        setVisible(true);
    }

    void loadData(){

        model.setRowCount(0);

        for(ChiTietPhieuXuatDTO ct : bus.getAll()){

            model.addRow(new Object[]{
                    ct.getMaphieuxuat(),
                    ct.getMasanpham(),
                    ct.getSoluong(),
                    ct.getDongia()
            });
        }
    }
}