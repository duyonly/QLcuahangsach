package Gui;

import Bus.NhaCungCapBus;
import Dto.NhaCungCapDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FormNhaCungCap extends JFrame {
    private NhaCungCapBus bus = new NhaCungCapBus();
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaNCC, txtTenNCC, txtDienThoai, txtEmail, txtDiaChi;
    private JTextField txtSearch;
    private JButton btnAdd, btnEdit, btnDelete, btnView, btnList;

    public FormNhaCungCap(){
        setTitle("Quản Lý Nhà Cung Cấp");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
        JPanel formPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Nhà Cung Cấp"));
        formPanel.setBackground(new Color(240, 240, 240));

        formPanel.add(new JLabel("Mã NCC:"));
        txtMaNCC = new JTextField();
        txtMaNCC.setEditable(false);
        formPanel.add(txtMaNCC);
        formPanel.add(new JLabel("Tên NCC:"));
        txtTenNCC = new JTextField();
        formPanel.add(txtTenNCC);

        formPanel.add(new JLabel("Điện Thoại:"));
        txtDienThoai = new JTextField();
        formPanel.add(txtDienThoai);
        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Địa Chỉ:"));
        txtDiaChi = new JTextField();
        formPanel.add(txtDiaChi);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        JPanel formWrapper = new JPanel(new BorderLayout());
        formWrapper.add(formPanel, BorderLayout.CENTER);
        middlePanel.add(formWrapper, BorderLayout.NORTH);

        // Right side: Table
        model = new DefaultTableModel(new Object[]{"Mã NCC", "Tên NCC", "Điện Thoại", "Email", "Địa Chỉ"}, 0) {
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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Nhà Cung Cấp"));
        middlePanel.add(scrollPane, BorderLayout.CENTER);

        add(middlePanel, BorderLayout.CENTER);

        // ===== BOTTOM PANEL: Action Buttons =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(200, 200, 200));
        btnList = new JButton("Danh Sách");
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnView = new JButton("Xem Chi Tiết");
        buttonPanel.add(btnList);
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

        btnList.addActionListener(e -> {
            clearForm();
            loadData();
        });

        btnAdd.addActionListener(e -> openEditDialog(null));

        btnEdit.addActionListener(e -> {
            int idx = table.getSelectedRow();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để sửa");
                return;
            }
            String ma = txtMaNCC.getText().trim();
            NhaCungCapDTO n = bus.xemChiTiet(ma);
            openEditDialog(n);
        });

        btnDelete.addActionListener(e -> {
            int idx = table.getSelectedRow();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để xóa");
                return;
            }
            String ma = txtMaNCC.getText().trim();
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
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để xem chi tiết");
                return;
            }
            String ma = txtMaNCC.getText().trim();
            xemChiTiet(ma);
        });

        setVisible(true);
    }

    private void populateFormFromTable(int row) {
        if (row >= 0 && row < model.getRowCount()) {
            txtMaNCC.setText(model.getValueAt(row, 0).toString());
            txtTenNCC.setText(model.getValueAt(row, 1).toString());
            txtDienThoai.setText(model.getValueAt(row, 2).toString());
            txtEmail.setText(model.getValueAt(row, 3).toString());
            txtDiaChi.setText(model.getValueAt(row, 4).toString());
        }
    }

    private void clearForm() {
        txtMaNCC.setText("");
        txtTenNCC.setText("");
        txtDienThoai.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        table.clearSelection();
    }

    public void xemChiTiet(String ma) {
        NhaCungCapDTO n = bus.xemChiTiet(ma);
        if (n == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy chi tiết nhà cung cấp");
            return;
        }
        ChiTietNhaCungCapDialog dlg = new ChiTietNhaCungCapDialog(this, n);
        dlg.setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0);
        List<NhaCungCapDTO> list = bus.hienDanhSach();
        for (NhaCungCapDTO n : list) {
            model.addRow(new Object[]{
                    n.getMaNCC(),
                    n.getTenNCC(),
                    n.getDienThoai(),
                    n.getEmail(),
                    n.getDiaChi()
            });
        }
    }

    private void search(String q) {
        model.setRowCount(0);
        List<NhaCungCapDTO> list = bus.timKiem(q);
        for (NhaCungCapDTO n : list) {
            model.addRow(new Object[]{
                    n.getMaNCC(),
                    n.getTenNCC(),
                    n.getDienThoai(),
                    n.getEmail(),
                    n.getDiaChi()
            });
        }
    }

    public void openEditDialog(NhaCungCapDTO n) {
        JDialog dlg = new JDialog(this, "Thông tin Nhà Cung Cấp", true);
        dlg.setSize(500, 350);
        dlg.setLayout(new GridLayout(4, 2, 10, 10));
        dlg.setLocationRelativeTo(this);

        JLabel lblTen = new JLabel("Tên NCC:");
        JTextField txtTen = new JTextField();
        dlg.add(lblTen);
        dlg.add(txtTen);

        JLabel lblDienThoai = new JLabel("Điện Thoại:");
        JTextField txtSDT = new JTextField();
        dlg.add(lblDienThoai);
        dlg.add(txtSDT);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        dlg.add(lblEmail);
        dlg.add(txtEmail);

        JLabel lblDiaChi = new JLabel("Địa Chỉ:");
        JTextField txtDiaChi = new JTextField();
        dlg.add(lblDiaChi);
        dlg.add(txtDiaChi);

        JButton btnSave = new JButton("Lưu");
        JButton btnCancel = new JButton("Hủy");
        dlg.add(btnSave);
        dlg.add(btnCancel);

        if (n != null) {
            txtTen.setText(n.getTenNCC());
            txtSDT.setText(n.getDienThoai());
            txtEmail.setText(n.getEmail());
            txtDiaChi.setText(n.getDiaChi());
        }

        btnSave.addActionListener(e -> {
            String ten = txtTen.getText().trim();
            String dienthoai = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            String diachi = txtDiaChi.getText().trim();

            if (ten.isEmpty() || dienthoai.isEmpty()) {
                JOptionPane.showMessageDialog(dlg, "Vui lòng điền đầy đủ thông tin");
                return;
            }

            if (n == null) {
                NhaCungCapDTO nn = new NhaCungCapDTO("0", ten, dienthoai, email, diachi);
                boolean ok = bus.them(nn);
                if (ok) {
                    loadData();
                    dlg.dispose();
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
            } else {
                n.setTenNCC(ten);
                n.setDienThoai(dienthoai);
                n.setEmail(email);
                n.setDiaChi(diachi);
                boolean ok = bus.sua(n);
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

