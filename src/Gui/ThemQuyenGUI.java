package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ThemQuyenGUI extends JDialog {
    private JTextField txtTenNhom;
    private JTable tableQuyen;
    private DefaultTableModel tableModel;
    private JButton btnLưu, btnHủy;

    public ThemQuyenGUI(JFrame parent) {
        // Khởi tạo một modal JDialog relative to main frame
        super(parent, "Thêm mới Nhóm quyền", true);
        setSize(800, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // Panel chính có đệm (padding) để đẹp hơn
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ==========================================
        // 1. PHẦN TRÊN: Nhập Tên nhóm quyền
        // ==========================================
        JPanel panelTop = new JPanel(new GridBagLayout()); // Dùng GridBagLayout để căn chỉnh chính xác hơn
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 10); // Đệm phải cho Label

        JLabel lblTenNhom = new JLabel("Tên nhóm quyền mới:");
        lblTenNhom.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Căn trái label
        panelTop.add(lblTenNhom, gbc);

        txtTenNhom = new JTextField();
        txtTenNhom.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTenNhom.setToolTipText("Nhập tên cho nhóm quyền mới (vd: Quản lý nhân sự)");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Cho JTextField chiếm hết chiều ngang còn lại
        gbc.fill = GridBagConstraints.HORIZONTAL; // JTextField co giãn theo chiều ngang
        panelTop.add(txtTenNhom, gbc);

        // ==========================================
        // 2. PHẦN GIỮA: Bảng phân quyền (Checkbox)
        // ==========================================
        // Định nghĩa các cột. Cột đầu là tên, 4 cột sau là Checkbox
        String[] columns = {"Danh mục chức năng", "Xem", "Thêm", "Sửa", "Xoá"};
        
        // MẸO SWING: Ép kiểu các cột thành Boolean để hiện Checkbox
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Cột 0 là String (tên module), các cột 1-4 là Boolean (Checkbox)
                if (columnIndex == 0) return String.class;
                return Boolean.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép click vào các ô Checkbox, không cho sửa tên module
                return column > 0;
            }
        };

        tableQuyen = new JTable(tableModel);
        tableQuyen.setRowHeight(35); // Tăng chiều cao hàng cho dễ tick
        tableQuyen.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(tableQuyen);
        
        // Thêm một viền bao quanh bảng cho rõ ràng
        scrollPane.setBorder(BorderFactory.createTitledBorder("Cấu hình quyền chi tiết"));

        // *** Thêm dữ liệu giả để Test (Sau này sẽ gọi lớp BUS để đổ dữ liệu thật) ***
        tableModel.addRow(new Object[]{"Quản lý sản phẩm", false, false, false, false});
        tableModel.addRow(new Object[]{"Quản lý khách hàng", false, false, false, false});
        tableModel.addRow(new Object[]{"Quản lý hóa đơn", false, false, false, false});
        tableModel.addRow(new Object[]{"Quản lý nhân viên", false, false, false, false});
        tableModel.addRow(new Object[]{"Quản lý phiếu nhập", false, false, false, false});
        tableModel.addRow(new Object[]{"Phân quyền", false, false, false, false});

        // ==========================================
        // 3. PHẦN DƯỚI: Các nút hành động (Lưu, Hủy)
        // ==========================================
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        btnLưu = new JButton("THÊM MỚI");
        btnLưu.setBackground(new Color(46, 204, 113)); // Màu xanh lá (Emerald)
        btnLưu.setForeground(Color.WHITE);
        btnLưu.setFont(new Font("Arial", Font.BOLD, 14));
        btnLưu.setPreferredSize(new Dimension(200, 40));

        btnHủy = new JButton("HUỶ BỎ");
        btnHủy.setBackground(new Color(231, 76, 60)); // Màu đỏ (Alizarin)
        btnHủy.setForeground(Color.WHITE);
        btnHủy.setFont(new Font("Arial", Font.BOLD, 14));
        btnHủy.setPreferredSize(new Dimension(100, 40));

        panelBottom.add(btnLưu);
        panelBottom.add(btnHủy);

        // ==========================================
        // 4. RÁP CÁC PANEL VÀO MAIN PANEL
        // ==========================================
        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(panelBottom, BorderLayout.SOUTH);

        add(mainPanel);

        // ==========================================
        // 5. XỬ LÝ SỰ KIỆN CÁC NÚT
        // ==========================================
        btnHủy.addActionListener(e -> dispose()); // Đóng cửa sổ khi ấn Hủy
        
        btnLưu.addActionListener(e -> {
            String tenNhom = txtTenNhom.getText().trim();
            if (tenNhom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhóm quyền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Dừng lại nếu chưa nhập tên
            }
            
            // Xử lý lấy dữ liệu quyền. Vì đây là demo GUI, ta chỉ in ra console
            System.out.println("--- Đang thêm mới nhóm quyền: " + tenNhom + " ---");
            // Lấy dữ liệu dòng đầu tiên làm ví dụ
            System.out.println("Module: " + tableModel.getValueAt(0, 0));
            System.out.println("- Xem: " + tableModel.getValueAt(0, 1));
            System.out.println("- Thêm: " + tableModel.getValueAt(0, 2));
            System.out.println("- Sửa: " + tableModel.getValueAt(0, 3));
            System.out.println("- Xoá: " + tableModel.getValueAt(0, 4));
            
            JOptionPane.showMessageDialog(this, "Thêm nhóm quyền thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Đóng cửa sổ sau khi lưu
        });
    }
}