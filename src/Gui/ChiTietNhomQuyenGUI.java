package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ChiTietQuyenGUI extends JDialog {
    private JTextField txtTenNhom;
    private JTable tableQuyen;
    private DefaultTableModel tableModel;
    private JButton btnLuu, btnHuy;

    // Tham số maNhom dùng để sau này lấy dữ liệu từ Database, tạm thời để hiển thị tên
    public ChiTietQuyenGUI(JFrame parent, String maNhom, String tenNhom) {
        super(parent, "Chỉnh sửa nhóm quyền", true); // true = Modal (Không cho click ra ngoài khi đang mở)
        setSize(800, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. PHẦN TRÊN: Tên nhóm quyền
        JPanel panelTop = new JPanel(new BorderLayout(10, 10));
        panelTop.add(new JLabel("Tên nhóm quyền:"), BorderLayout.WEST);
        txtTenNhom = new JTextField(tenNhom);
        txtTenNhom.setFont(new Font("Arial", Font.BOLD, 14));
        panelTop.add(txtTenNhom, BorderLayout.CENTER);

        // 2. PHẦN GIỮA: Bảng phân quyền (Checkbox)
        String[] columns = {"Danh mục chức năng", "Xem", "Tạo mới", "Cập nhật", "Xoá"};
        
        // MẸO SWING: Ép kiểu cột thành Boolean để hiện Checkbox
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return String.class; // Cột đầu tiên là Tên chức năng (String)
                return Boolean.class; // Các cột còn lại là Checkbox (Boolean)
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 0; // Chỉ cho phép click vào các ô Checkbox, không cho sửa tên chức năng
            }
        };

        tableQuyen = new JTable(tableModel);
        tableQuyen.setRowHeight(35);
        tableQuyen.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(tableQuyen);

        // Thêm dữ liệu giả để Test (Sau này sẽ gọi lớp BUS để đổ dữ liệu thật)
        tableModel.addRow(new Object[]{"Quản lý khách hàng", true, true, true, false});
        tableModel.addRow(new Object[]{"Quản lý khu vực kho", true, false, false, false});
        tableModel.addRow(new Object[]{"Quản lý nhà cung cấp", true, true, true, true});
        tableModel.addRow(new Object[]{"Quản lý sản phẩm", true, true, false, false});

        // 3. PHẦN DƯỚI: Các nút hành động
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnLuu = new JButton("Cập nhật nhóm quyền");
        btnLuu.setBackground(new Color(52, 152, 219));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setPreferredSize(new Dimension(200, 40));

        btnHuy = new JButton("Huỷ bỏ");
        btnHuy.setBackground(new Color(231, 76, 60));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setPreferredSize(new Dimension(100, 40));

        panelBottom.add(btnLuu);
        panelBottom.add(btnHuy);

        // Ráp vào mainPanel
        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(panelBottom, BorderLayout.SOUTH);

        add(mainPanel);

        // 4. Xử lý sự kiện nút
        btnHuy.addActionListener(e -> dispose()); // Đóng cửa sổ
        
        btnLuu.addActionListener(e -> {
            // Demo lấy dữ liệu từ Checkbox khi nhấn Lưu
            System.out.println("Đang lưu quyền cho nhóm: " + txtTenNhom.getText());
            boolean canView = (boolean) tableModel.getValueAt(0, 1);
            System.out.println("Quyền xem khách hàng: " + canView);
            
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            dispose();
        });
    }
}