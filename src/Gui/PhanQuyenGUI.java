package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PhanQuyenGUI extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem, btnSua, btnXoa, btnChiTiet, btnXuatExcel, btnLamMoi;
    private JTextField txtTimKiem;
    private JComboBox<String> cbxTimKiem;

    public PhanQuyenGUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. KHU VỰC ĐIỀU KHIỂN (Phía trên)
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.setBackground(Color.WHITE);

        // 1.1 Các nút chức năng (Bên trái)
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelButtons.setBackground(Color.WHITE);
        btnThem = new JButton("THÊM");
        btnSua = new JButton("SỬA");
        btnXoa = new JButton("XÓA");
        btnChiTiet = new JButton("CHI TIẾT");

        panelButtons.add(btnThem);
        panelButtons.add(btnSua);
        panelButtons.add(btnXoa);
        panelButtons.add(btnChiTiet);

        // 1.2 Khu vực tìm kiếm (Bên phải)
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelSearch.setBackground(Color.WHITE);
        cbxTimKiem = new JComboBox<>(new String[]{"Tất cả", "Mã nhóm", "Tên nhóm"});
        txtTimKiem = new JTextField(15);
        btnLamMoi = new JButton("Làm mới");

        panelSearch.add(cbxTimKiem);
        panelSearch.add(txtTimKiem);
        panelSearch.add(btnLamMoi);

        panelTop.add(panelButtons, BorderLayout.WEST);
        panelTop.add(panelSearch, BorderLayout.EAST);

        // ==========================================
        // XỬ LÝ SỰ KIỆN NÚT "THÊM"
        // ==========================================
        btnThem.addActionListener(e -> {
            // Lấy reference của main JFrame để làm parent cho JDialog
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            ThemQuyenGUI dialog = new ThemQuyenGUI(parentFrame);
            dialog.setVisible(true); // Hiển thị Dialog
        });
    // ... code hiện tại ...

        // 2. KHU VỰC BẢNG DỮ LIỆU (Ở giữa)
        String[] columnNames = {"Mã nhóm quyền", "Tên nhóm quyền"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép sửa trực tiếp trên bảng
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(table);

        // Thêm dữ liệu giả để Test giao diện
        tableModel.addRow(new Object[]{"1", "Quản lý kho"});
        tableModel.addRow(new Object[]{"2", "Nhân viên nhập hàng"});
        tableModel.addRow(new Object[]{"3", "Nhân viên xuất hàng"});

        // 3. THÊM VÀO PANEL CHÍNH
        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ==========================================
        // 4. XỬ LÝ SỰ KIỆN NÚT "SỬA"
        // ==========================================
        btnSua.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhóm quyền để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
                // Lấy dữ liệu từ dòng được chọn
                String maNhom = tableModel.getValueAt(selectedRow, 0).toString();
                String tenNhom = tableModel.getValueAt(selectedRow, 1).toString();
                
                // Mở cửa sổ Chi tiết
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                ChiTietNhomQuyenGUI dialog = new ChiTietNhomQuyenGUI(parentFrame, maNhom, tenNhom);
                dialog.setVisible(true); // Hiển thị Dialog
            }
        });
    }
}