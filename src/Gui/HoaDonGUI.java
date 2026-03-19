package Gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Bus.HoaDonBUS;
import Dto.HoaDonDto;
public class HoaDonGUI extends JPanel {
    JTextField txtMaHD, txtMaNV, txtMaKH, txtTongTien, txtSearch;
    JComboBox<String> cbTrangThai;
    JTable table;
    DefaultTableModel model;

    HoaDonBUS bus = new HoaDonBUS();

    public HoaDonGUI() {
        setLayout(new BorderLayout(10,10));

        // ===== FORM =====
        JPanel panelTop = new JPanel(new GridLayout(3, 4, 10, 10));
        panelTop.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        txtMaHD = new JTextField();
        txtMaNV = new JTextField();
        txtMaKH = new JTextField();
        txtTongTien = new JTextField();
        txtSearch = new JTextField(20);

        cbTrangThai = new JComboBox<>(new String[]{"Chưa thanh toán", "Đã thanh toán"});

        panelTop.add(new JLabel("Mã HD"));
        panelTop.add(txtMaHD);
        panelTop.add(new JLabel("Mã NV"));
        panelTop.add(txtMaNV);

        panelTop.add(new JLabel("Mã KH"));
        panelTop.add(txtMaKH);
        panelTop.add(new JLabel("Tổng tiền"));
        panelTop.add(txtTongTien);

        panelTop.add(new JLabel("Trạng thái"));
        panelTop.add(cbTrangThai);

        // ===== SEARCH =====
        JPanel panelSearch = new JPanel();
        panelSearch.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

        JButton btnSearch = new JButton("Tìm");
        panelSearch.add(new JLabel("Tìm kiếm:"));
        panelSearch.add(txtSearch);
        panelSearch.add(btnSearch);

        // ===== GỘP TOP =====
        JPanel top = new JPanel(new BorderLayout());
        top.add(panelTop, BorderLayout.NORTH);
        top.add(panelSearch, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"Mã HD", "Mã NV", "Mã KH", "Tổng tiền", "Trạng thái"}, 0);

        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BUTTON =====
        JPanel panelBtn = new JPanel();

        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");

        panelBtn.add(btnThem);
        panelBtn.add(btnSua);
        panelBtn.add(btnXoa);

        add(panelBtn, BorderLayout.SOUTH);

        // ===== LOAD DATA =====
        loadTable(bus.getAll());

        // ===== EVENTS =====

        // THÊM
        btnThem.addActionListener(e -> {
            try {
                HoaDonDto hd = new HoaDonDto(
                        txtMaHD.getText(),
                        "2026-03-10",
                        txtMaNV.getText(),
                        txtMaKH.getText(),
                        Double.parseDouble(txtTongTien.getText()),
                        cbTrangThai.getSelectedItem().toString()
                );

                if (bus.add(hd)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    loadTable(bus.getAll());
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
                String ma = model.getValueAt(row, 0).toString();

                if (bus.delete(ma)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    loadTable(bus.getAll());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chọn dòng để xóa!");
            }
        });

        // SỬA
        btnSua.addActionListener(e -> {
            try {
                HoaDonDto hd = new HoaDonDto();
                hd.setMaHD(txtMaHD.getText());
                hd.setTongTien(Double.parseDouble(txtTongTien.getText()));
                hd.setTrangThai(cbTrangThai.getSelectedItem().toString());

                if (bus.update(hd)) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công!");
                    loadTable(bus.getAll());
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
            }
        });

        // TÌM
        btnSearch.addActionListener(e -> {
            loadTable(bus.search(txtSearch.getText()));
        });

        // CLICK TABLE
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                txtMaHD.setText(model.getValueAt(row, 0).toString());
                txtMaNV.setText(model.getValueAt(row, 1).toString());
                txtMaKH.setText(model.getValueAt(row, 2).toString());
                txtTongTien.setText(model.getValueAt(row, 3).toString());
                cbTrangThai.setSelectedItem(model.getValueAt(row, 4));
            }
        });
    }

    // ===== LOAD TABLE =====
    void loadTable(ArrayList<HoaDonDto> list) {
        model.setRowCount(0);
        for (HoaDonDto hd : list) {
            model.addRow(new Object[]{
                    hd.getMaHD(),
                    hd.getMaNV(),
                    hd.getMaKH(),
                    hd.getTongTien(),
                    hd.getTrangThai()
            });
        }
    }
}
