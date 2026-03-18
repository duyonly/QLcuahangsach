package Gui;

import Bus.SanPhamBUS;
import Dto.SanPhamDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SanPhamGUI extends JPanel {

    JTextField txtMa, txtTen, txtTacGia, txtTheLoai, txtNXB, txtNamXB, txtGiaNhap, txtGiaBan, txtTim;
    JButton btnThem, btnSua, btnXoa, btnTim, btnLoad;
    JTable table;
    DefaultTableModel model;

    SanPhamBUS bus = new SanPhamBUS();

    public SanPhamGUI() {
        // Sử dụng BorderLayout để co giãn tốt
        setLayout(new BorderLayout(10, 10));

        // ----- Panel Nhập Liệu (Phía Tây) -----
        JPanel panelInput = new JPanel(new GridLayout(8, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Thông tin sách"));

        panelInput.add(new JLabel("Mã sách"));
        txtMa = new JTextField();
        panelInput.add(txtMa);

        panelInput.add(new JLabel("Tên sách"));
        txtTen = new JTextField();
        panelInput.add(txtTen);

        panelInput.add(new JLabel("Tác giả"));
        txtTacGia = new JTextField();
        panelInput.add(txtTacGia);

        panelInput.add(new JLabel("Thể loại"));
        txtTheLoai = new JTextField();
        panelInput.add(txtTheLoai);

        panelInput.add(new JLabel("Nhà xuất bản"));
        txtNXB = new JTextField();
        panelInput.add(txtNXB);

        panelInput.add(new JLabel("Năm XB"));
        txtNamXB = new JTextField();
        panelInput.add(txtNamXB);

        panelInput.add(new JLabel("Giá nhập"));
        txtGiaNhap = new JTextField();
        panelInput.add(txtGiaNhap);

        panelInput.add(new JLabel("Giá bán"));
        txtGiaBan = new JTextField();
        panelInput.add(txtGiaBan);

        // Bọc vào một Panel khác để không bị giãn theo chiều dọc
        JPanel wrapInput = new JPanel(new BorderLayout());
        wrapInput.add(panelInput, BorderLayout.NORTH);
        add(wrapInput, BorderLayout.WEST);

        // ----- Bảng Dữ Liệu (Trung tâm) -----
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho sửa trực tiếp trên bảng
            }
        };
        model.setColumnIdentifiers(new String[]{
                "Mã", "Tên", "Tác giả", "Thể loại", "NXB", "Năm", "Giá nhập", "Giá bán"
        });

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ----- Panel Nút và Tìm Kiếm (Phía Nam) -----
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLoad = new JButton("Làm mới");

        txtTim = new JTextField(20);
        btnTim = new JButton("Tìm nhanh");

        panelBottom.add(btnThem);
        panelBottom.add(btnSua);
        panelBottom.add(btnXoa);
        panelBottom.add(btnLoad);
        panelBottom.add(new JLabel(" | "));
        panelBottom.add(txtTim);
        panelBottom.add(btnTim);

        add(panelBottom, BorderLayout.SOUTH);

        // ----- Sự kiện -----
        loadTable();

        btnThem.addActionListener(e -> them());
        btnSua.addActionListener(e -> sua());
        btnXoa.addActionListener(e -> xoa());
        btnLoad.addActionListener(e -> {
            clearFields();
            loadTable();
        });
        btnTim.addActionListener(e -> tim());

        // Tìm nhanh khi gõ phím (Real-time Search)
        txtTim.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tim();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtMa.setText(model.getValueAt(row, 0).toString());
                    txtTen.setText(model.getValueAt(row, 1).toString());
                    txtTacGia.setText(model.getValueAt(row, 2).toString());
                    txtTheLoai.setText(model.getValueAt(row, 3).toString());
                    txtNXB.setText(model.getValueAt(row, 4).toString());
                    txtNamXB.setText(model.getValueAt(row, 5).toString());
                    txtGiaNhap.setText(model.getValueAt(row, 6).toString());
                    txtGiaBan.setText(model.getValueAt(row, 7).toString());
                }
            }
        });
    }

    void loadTable() {
        model.setRowCount(0);
        ArrayList<SanPhamDTO> list = bus.getAll();
        for (SanPhamDTO sp : list) {
            model.addRow(new Object[]{
                    sp.getMasp(), sp.getTensp(), sp.getTentg(),
                    sp.getMatheloai(), sp.getNhaxuatban(), sp.getNamxuatban(),
                    sp.getGianhap(), sp.getGiaban()
            });
        }
    }

    // Hàm lấy dữ liệu từ form và bắt lỗi nhập liệu
    private SanPhamDTO getEntityFromForm() {
        try {
            String ma = txtMa.getText().trim();
            String ten = txtTen.getText().trim();
            if (ma.isEmpty() || ten.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã và Tên không được để trống!");
                return null;
            }

            SanPhamDTO sp = new SanPhamDTO();
            sp.setMasp(ma);
            sp.setTensp(ten);
            sp.setTentg(txtTacGia.getText());
            sp.setMatheloai(txtTheLoai.getText());
            sp.setNhaxuatban(txtNXB.getText());
            sp.setNamxuatban(Integer.parseInt(txtNamXB.getText()));
            sp.setGianhap(Integer.parseInt(txtGiaNhap.getText()));
            sp.setGiaban(Integer.parseInt(txtGiaBan.getText()));
            return sp;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Năm XB, Giá nhập, Giá bán phải là số nguyên!");
            return null;
        }
    }

    void them() {
        SanPhamDTO sp = getEntityFromForm();
        if (sp != null) {
            if (bus.add(sp)) {
                JOptionPane.showMessageDialog(this, "Thêm sách thành công!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Mã sách đã tồn tại!");
            }
        }
    }

    void sua() {
        SanPhamDTO sp = getEntityFromForm();
        if (sp != null) {
            if (bus.update(sp)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadTable();
            }
        }
    }

    void xoa() {
        String ma = txtMa.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sách cần xóa!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            bus.delete(ma);
            loadTable();
            clearFields();
        }
    }

    void tim() {
        model.setRowCount(0);
        ArrayList<SanPhamDTO> list = bus.searchByName(txtTim.getText().trim());
        for (SanPhamDTO sp : list) {
            model.addRow(new Object[]{
                    sp.getMasp(), sp.getTensp(), sp.getTentg(),
                    sp.getMatheloai(), sp.getNhaxuatban(), sp.getNamxuatban(),
                    sp.getGianhap(), sp.getGiaban()
            });
        }
    }

    void clearFields() {
        txtMa.setText("");
        txtTen.setText("");
        txtTacGia.setText("");
        txtTheLoai.setText("");
        txtNXB.setText("");
        txtNamXB.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtTim.setText("");
    }
}