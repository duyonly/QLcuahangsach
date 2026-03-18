package Gui;

import Bus.NhomQuyenBUS;
import Dto.NhomQuyenDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class PhanQuyenGUI extends JPanel{
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem, btnSua, btnXoa, btnChiTiet, btnLamMoi;
    private JTextField txtTimKiem;
    private JComboBox<String> cbxTimKiem;

    public PhanQuyenGUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.setBackground(Color.WHITE);

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

        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelSearch.setBackground(Color.WHITE);
        cbxTimKiem = new JComboBox<>(new String[]{"Tất cả", "Mã nhóm", "Tên nhóm"});
        txtTimKiem = new JTextField(15);
        btnLamMoi = new JButton("LÀM MỚI");

        panelSearch.add(cbxTimKiem);
        panelSearch.add(txtTimKiem);
        panelSearch.add(btnLamMoi);

        panelTop.add(panelButtons, BorderLayout.WEST);
        panelTop.add(panelSearch, BorderLayout.EAST);

        String[] columnNames = {"Mã nhóm quyền", "Tên nhóm quyền"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(table);

        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ==========================================
        // GẮN CÁC SỰ KIỆN NÚT BẤM
        // ==========================================
        
        // 1. Nút THÊM
        btnThem.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            ThemQuyenGUI dialog = new ThemQuyenGUI(parentFrame, this);
            dialog.setVisible(true); 
        });

        // 2. Nút SỬA
        btnSua.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhóm quyền để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            } 
            String maNhom = table.getValueAt(selectedRow, 0).toString();
            String tenNhom = table.getValueAt(selectedRow, 1).toString();
            
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            // isReadOnly = false (Cho phép sửa)
            ChiTietNhomQuyenGUI dialog = new ChiTietNhomQuyenGUI(parentFrame, this, maNhom, tenNhom, false);
            dialog.setVisible(true); 
        });

        // 3. Nút CHI TIẾT (Tương tự Sửa, nhưng không cho lưu)
        btnChiTiet.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhóm quyền để xem!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            } 
            String maNhom = table.getValueAt(selectedRow, 0).toString();
            String tenNhom = table.getValueAt(selectedRow, 1).toString();
            
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            // isReadOnly = true (Chỉ xem)
            ChiTietNhomQuyenGUI dialog = new ChiTietNhomQuyenGUI(parentFrame, this, maNhom, tenNhom, true);
            dialog.setVisible(true); 
        });

        // 4. Nút XÓA
        btnXoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhóm quyền để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            } 
            String maNhom = table.getValueAt(selectedRow, 0).toString();
            
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhóm quyền " + maNhom + " không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                NhomQuyenBUS bus = new NhomQuyenBUS();
                String kq = bus.xoaNhomQuyen(maNhom);
                if(kq.equals("Thành công")) {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công!");
                    loadDataToTable(); // Làm mới bảng
                } else {
                    JOptionPane.showMessageDialog(this, kq, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 5. Nút TÌM KIẾM (Tự động lọc khi gõ phím hoặc ấn Enter)
        txtTimKiem.addActionListener(e -> performSearch()); // Khi ấn Enter
        
        // 6. Nút LÀM MỚI
        btnLamMoi.addActionListener(e -> {
            txtTimKiem.setText("");
            table.setRowSorter(null); // Tắt bộ lọc
            loadDataToTable(); // Kéo lại từ DB
        });

        // Tải dữ liệu lần đầu
        loadDataToTable();
    }

    private void performSearch() {
        String keyword = txtTimKiem.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        if (keyword.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            // Lọc trên toàn bộ bảng, không phân biệt hoa thường
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        }
    }

    public void loadDataToTable() {
        tableModel.setRowCount(0); 
        NhomQuyenBUS bus = new NhomQuyenBUS();
        ArrayList<NhomQuyenDTO> list = bus.getList();
        
        for (NhomQuyenDTO nq : list) {
            tableModel.addRow(new Object[]{
                nq.getMaNhomQuyen(), 
                nq.getTenNhomQuyen()
            });
        }
    }

}
