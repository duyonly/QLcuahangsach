package Gui;
import javax.swing.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Bus.ChiTietHoaDonBUS;
import Dto.chitiethoadon;
public class ChiTietHoaDonGUI extends JPanel{
    JTextField txtMaHD, txtMaSach, txtSoLuong, txtDonGia, txtSearch;
    JTable table;
    DefaultTableModel model;

    ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS();

    public ChiTietHoaDonGUI() {
        setLayout(new BorderLayout(10,10));

        // ===== FORM =====
        JPanel panelTop = new JPanel(new GridLayout(2, 4, 10, 10));
        panelTop.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));

        txtMaHD = new JTextField();
        txtMaSach = new JTextField();
        txtSoLuong = new JTextField();
        txtDonGia = new JTextField();
        txtSearch = new JTextField(20);

        panelTop.add(new JLabel("Mã HD"));
        panelTop.add(txtMaHD);
        panelTop.add(new JLabel("Mã Sách"));
        panelTop.add(txtMaSach);

        panelTop.add(new JLabel("Số lượng"));
        panelTop.add(txtSoLuong);
        panelTop.add(new JLabel("Đơn giá"));
        panelTop.add(txtDonGia);

        // ===== SEARCH =====
        JPanel panelSearch = new JPanel();
        panelSearch.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

        JButton btnSearch = new JButton("Tìm");

        panelSearch.add(new JLabel("Tìm:"));
        panelSearch.add(txtSearch);
        panelSearch.add(btnSearch);

        JPanel top = new JPanel(new BorderLayout());
        top.add(panelTop, BorderLayout.NORTH);
        top.add(panelSearch, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"Mã HD", "Mã Sách", "SL", "Đơn giá", "Thành tiền"}, 0);

        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BUTTON =====
        JPanel bottom = new JPanel();

        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");

        bottom.add(btnThem);
        bottom.add(btnSua);
        bottom.add(btnXoa);

        add(bottom, BorderLayout.SOUTH);

        // ===== LOAD DATA =====
        load(bus.getByMaHD(""));

        // ===== EVENTS =====

        // THÊM
        btnThem.addActionListener(e -> {
            try {
                int sl = Integer.parseInt(txtSoLuong.getText());
                double gia = Double.parseDouble(txtDonGia.getText());
                double thanhTien = sl * gia;

                chitiethoadon ct = new chitiethoadon(
                        txtMaHD.getText(),
                        txtMaSach.getText(),
                        sl,
                        gia,
                        thanhTien
                );

                if (bus.add(ct)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    load(bus.getByMaHD(""));
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
            }
        });

        // XÓA
        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String maHD = model.getValueAt(row, 0).toString();
                String maSach = model.getValueAt(row, 1).toString();

                if (bus.delete(maHD, maSach)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    load(bus.getByMaHD(""));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chọn dòng để xóa!");
            }
        });

        // SỬA
        btnSua.addActionListener(e -> {
            try {
                int sl = Integer.parseInt(txtSoLuong.getText());
                double gia = Double.parseDouble(txtDonGia.getText());
                double thanhTien = sl * gia;

                chitiethoadon ct = new chitiethoadon(
                        txtMaHD.getText(),
                        txtMaSach.getText(),
                        sl,
                        gia,
                        thanhTien
                );

                if (bus.update(ct)) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công!");
                    load(bus.getByMaHD(""));
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
            }
        });

        // TÌM
        btnSearch.addActionListener(e -> {
            load(bus.search(txtSearch.getText()));
        });

        // CLICK TABLE
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                txtMaHD.setText(model.getValueAt(row, 0).toString());
                txtMaSach.setText(model.getValueAt(row, 1).toString());
                txtSoLuong.setText(model.getValueAt(row, 2).toString());
                txtDonGia.setText(model.getValueAt(row, 3).toString());
            }
        });
    }

    // ===== LOAD TABLE =====
    void load(ArrayList<chitiethoadon> list) {
        model.setRowCount(0);
        for (chitiethoadon ct : list) {
            model.addRow(new Object[]{
                    ct.getMaHD(),
                    ct.getMaSach(),
                    ct.getSoLuong(),
                    ct.getDonGia(),
                    ct.getThanhTien()
            });
        }
    }


}
