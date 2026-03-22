package Gui;

import Bus.KhachHangBus;
import Dto.KhachHangDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FormKhachHang extends JFrame {
    private KhachHangBus bus = new KhachHangBus();
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaKH, txtTenKH, txtSDT, txtEmail, txtDiaChi, txtGioiTinh, txtLoaiKH, txtDiem;
    private JTextField txtSearch;
    private JButton btnAdd, btnEdit, btnDelete, btnView;

    public FormKhachHang(){
        setTitle("Quản Lý Khách Hàng");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== TOP PANEL: Search Section =====
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(new Color(200, 200, 200));
        topPanel.setBorder(BorderFactory.createTitledBorder("Tìm Kiếm"));
        topPanel.add(new JLabel("Tìm kiếm:"));
        txtSearch = new JTextField(25);
        topPanel.add(txtSearch);
        JButton btnSearch = new JButton("Tìm");
        topPanel.add(btnSearch);
        JButton btnRefresh = new JButton("Làm mới");
        topPanel.add(btnRefresh);
        add(topPanel, BorderLayout.NORTH);

        // ===== MIDDLE PANEL: Form Section + Table =====
        JPanel middlePanel = new JPanel(new BorderLayout(5, 5));
        middlePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Left side: Form inputs
        JPanel formPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Khách Hàng"));
        formPanel.setBackground(new Color(240, 240, 240));

        formPanel.add(new JLabel("Mã KH:"));
        txtMaKH = new JTextField();
        txtMaKH.setEditable(false);
        formPanel.add(txtMaKH);
        formPanel.add(new JLabel("Tên KH:"));
        txtTenKH = new JTextField();
        formPanel.add(txtTenKH);

        formPanel.add(new JLabel("Giới Tính:"));
        txtGioiTinh = new JTextField();
        formPanel.add(txtGioiTinh);
        formPanel.add(new JLabel("Điện Thoại:"));
        txtSDT = new JTextField();
        formPanel.add(txtSDT);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);
        formPanel.add(new JLabel("Địa Chỉ:"));
        txtDiaChi = new JTextField();
        formPanel.add(txtDiaChi);

        formPanel.add(new JLabel("Loại KH:"));
        txtLoaiKH = new JTextField();
        formPanel.add(txtLoaiKH);
        formPanel.add(new JLabel("Điểm Tích Lũy:"));
        txtDiem = new JTextField();
        formPanel.add(txtDiem);

        JPanel formWrapper = new JPanel(new BorderLayout());
        formWrapper.add(formPanel, BorderLayout.CENTER);
        middlePanel.add(formWrapper, BorderLayout.NORTH);

        // Right side: Table
        model = new DefaultTableModel(new Object[]{"Mã KH", "Tên KH", "Giới Tính", "Điện Thoại", "Email", "Địa Chỉ", "Loại KH", "Điểm"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        loadData();

        // Select row and populate form
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    populateFormFromTable(row);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Khách Hàng"));
        middlePanel.add(scrollPane, BorderLayout.CENTER);

        add(middlePanel, BorderLayout.CENTER);

        // ===== BOTTOM PANEL: Action Buttons =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(200, 200, 200));
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnView = new JButton("Xem Chi Tiết");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnView);
        add(buttonPanel, BorderLayout.SOUTH);

        // ===== EVENT HANDLERS =====
        btnSearch.addActionListener(e -> {
            String keyword = txtSearch.getText().trim();
            if (keyword.isEmpty()) {
                loadData();
            } else {
                search(keyword);
            }
        });

        btnRefresh.addActionListener(e -> {
            txtSearch.setText("");
            clearForm();
            loadData();
        });

        btnAdd.addActionListener(e -> openEditDialog(null));

        btnEdit.addActionListener(e -> {
            int idx = table.getSelectedRow();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để sửa");
                return;
            }
            String ma = txtMaKH.getText().trim();
            KhachHangDTO k = bus.xemChiTiet(ma);
            openEditDialog(k);
        });

        btnDelete.addActionListener(e -> {
            int idx = table.getSelectedRow();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để xóa");
                return;
            }
            String ma = txtMaKH.getText().trim();
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (bus.xoa(ma)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    clearForm();
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại");
                }
            }
        });

        btnView.addActionListener(e -> {
            int idx = table.getSelectedRow();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để xem chi tiết");
                return;
            }
            String ma = txtMaKH.getText().trim();
            xemChiTiet(ma);
        });

        setVisible(true);
    }

    private void populateFormFromTable(int row) {
        if (row >= 0 && row < model.getRowCount()) {
            txtMaKH.setText(model.getValueAt(row, 0).toString());
            txtTenKH.setText(model.getValueAt(row, 1).toString());
            txtGioiTinh.setText(model.getValueAt(row, 2).toString());
            txtSDT.setText(model.getValueAt(row, 3).toString());
            txtEmail.setText(model.getValueAt(row, 4).toString());
            txtDiaChi.setText(model.getValueAt(row, 5).toString());
            txtLoaiKH.setText(model.getValueAt(row, 6).toString());
            txtDiem.setText(model.getValueAt(row, 7).toString());
        }
    }

    private void clearForm() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtGioiTinh.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtLoaiKH.setText("");
        txtDiem.setText("");
        table.clearSelection();
    }

    public void xemChiTiet(String ma) {
        KhachHangDTO k = bus.xemChiTiet(ma);
        if (k == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy chi tiết khách hàng");
            return;
        }
        ChiTietKhachHangDialog dlg = new ChiTietKhachHangDialog(this, k);
        dlg.setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0);
        List<KhachHangDTO> list = bus.hienDanhSach();
        for (KhachHangDTO k : list) {
            model.addRow(new Object[]{
                    k.getMaKH(),
                    k.getTenKH(),
                    k.getGioiTinh() != null ? k.getGioiTinh() : "",
                    k.getSDT(),
                    k.getEmail(),
                    k.getDiaChi(),
                    k.getLoaiKhachHang() != null ? k.getLoaiKhachHang() : "",
                    k.getDiemTichLuy()
            });
        }
    }

    private void search(String q) {
        model.setRowCount(0);
        List<KhachHangDTO> list = bus.timKiem(q);
        for (KhachHangDTO k : list) {
            model.addRow(new Object[]{
                    k.getMaKH(),
                    k.getTenKH(),
                    k.getGioiTinh() != null ? k.getGioiTinh() : "",
                    k.getSDT(),
                    k.getEmail(),
                    k.getDiaChi(),
                    k.getLoaiKhachHang() != null ? k.getLoaiKhachHang() : "",
                    k.getDiemTichLuy()
            });
        }
    }

    public void openEditDialog(KhachHangDTO k) {
        JDialog dlg = new JDialog(this, "Thông tin Khách Hàng", true);
        dlg.setSize(500, 400);
        dlg.setLayout(new GridLayout(5, 2, 10, 10));
        dlg.setLocationRelativeTo(this);

        JLabel lblTen = new JLabel("Tên Khách Hàng:");
        JTextField txtTen = new JTextField();
        dlg.add(lblTen);
        dlg.add(txtTen);

        JLabel lblGioiTinh = new JLabel("Giới Tính:");
        JTextField txtGT = new JTextField();
        dlg.add(lblGioiTinh);
        dlg.add(txtGT);

        JLabel lblSDT = new JLabel("Số Điện Thoại:");
        JTextField txtSDT = new JTextField();
        dlg.add(lblSDT);
        dlg.add(txtSDT);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        dlg.add(lblEmail);
        dlg.add(txtEmail);

        JLabel lblDiaChi = new JLabel("Địa Chỉ:");
        JTextField txtDiaChi = new JTextField();
        dlg.add(lblDiaChi);
        dlg.add(txtDiaChi);

        JLabel lblLoaiKH = new JLabel("Loại Khách Hàng:");
        JTextField txtLoaiKH = new JTextField();
        dlg.add(lblLoaiKH);
        dlg.add(txtLoaiKH);

        JButton btnSave = new JButton("Lưu");
        JButton btnCancel = new JButton("Hủy");
        dlg.add(btnSave);
        dlg.add(btnCancel);

        if (k != null) {
            txtTen.setText(k.getTenKH());
            txtGT.setText(k.getGioiTinh() != null ? k.getGioiTinh() : "");
            txtSDT.setText(k.getSDT());
            txtEmail.setText(k.getEmail());
            txtDiaChi.setText(k.getDiaChi());
            txtLoaiKH.setText(k.getLoaiKhachHang() != null ? k.getLoaiKhachHang() : "");
        }

        btnSave.addActionListener(e -> {
            String ten = txtTen.getText().trim();
            String gioitinh = txtGT.getText().trim();
            String sdt = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            String diachi = txtDiaChi.getText().trim();
            String loai = txtLoaiKH.getText().trim();

            if (ten.isEmpty() || sdt.isEmpty()) {
                JOptionPane.showMessageDialog(dlg, "Vui lòng điền đầy đủ thông tin");
                return;
            }

            if (k == null) {
                KhachHangDTO nk = new KhachHangDTO("0", ten, gioitinh, sdt, email, diachi, loai, 0);
                boolean ok = bus.them(nk);
                if (ok) {
                    loadData();
                    dlg.dispose();
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
            } else {
                k.setTenKH(ten);
                k.setGioiTinh(gioitinh);
                k.setSDT(sdt);
                k.setEmail(email);
                k.setDiaChi(diachi);
                k.setLoaiKhachHang(loai);
                boolean ok = bus.sua(k);
                if (ok) {
                    loadData();
                    dlg.dispose();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                }
            }
        });

        btnCancel.addActionListener(e -> dlg.dispose());

        dlg.setVisible(true);
    }
}

