package Gui;

import Bus.ChiTietQuyenBUS;
import Bus.DanhMucChucNangBUS;
import Bus.NhomQuyenBUS;
import Dto.ChiTietQuyenDTO;
import Dto.DanhMucChucNangDTO;
import Dto.NhomQuyenDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ChiTietNhomQuyenGUI extends JDialog {
    private JTextField txtTenNhom;
    private JTable tableQuyen;
    private DefaultTableModel tableModel;
    private JButton btnLuu, btnHuy;
    private ArrayList<DanhMucChucNangDTO> listChucNang;
    private PhanQuyenGUI parentGUI; // Biến để gọi Refresh bảng mẹ

    // Thêm tham số PhanQuyenGUI parentGUI vào hàm tạo
    public ChiTietNhomQuyenGUI(JFrame parent, PhanQuyenGUI parentGUI, String maNhom, String tenNhom, boolean isReadOnly) {
        super(parent, isReadOnly ? "Chi tiết nhóm quyền" : "Chỉnh sửa nhóm quyền", true); 
        this.parentGUI = parentGUI;
        setSize(800, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelTop = new JPanel(new BorderLayout(10, 10));
        panelTop.add(new JLabel("Tên nhóm quyền:"), BorderLayout.WEST);
        txtTenNhom = new JTextField(tenNhom);
        txtTenNhom.setEditable(!isReadOnly); // ĐÃ MỞ KHÓA CHO PHÉP SỬA TÊN
        panelTop.add(txtTenNhom, BorderLayout.CENTER);

        String[] columns = {"Danh mục chức năng", "Xem", "Thêm", "Sửa", "Xóa"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? String.class : Boolean.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) { return column > 0 && !isReadOnly; } // Khóa tick nếu chỉ xem
        };

        tableQuyen = new JTable(tableModel);
        tableQuyen.setRowHeight(35);
        JScrollPane scrollPane = new JScrollPane(tableQuyen);

        listChucNang = new DanhMucChucNangBUS().getAll();
        for (DanhMucChucNangDTO dm : listChucNang) {
            tableModel.addRow(new Object[]{dm.getTenChucNang(), false, false, false, false});
        }

        ArrayList<ChiTietQuyenDTO> listQuyenCu = new ChiTietQuyenBUS().getQuyenByMaNhom(maNhom);
        for (int i = 0; i < listChucNang.size(); i++) {
            String maCN = listChucNang.get(i).getMaChucNang();
            for (ChiTietQuyenDTO ct : listQuyenCu) {
                if (ct.getMaChucNang().equals(maCN)) {
                    String hd = ct.getHanhDong();
                    if (hd.contains("Xem")) tableModel.setValueAt(true, i, 1);
                    if (hd.contains("Thêm")) tableModel.setValueAt(true, i, 2);
                    if (hd.contains("Sửa")) tableModel.setValueAt(true, i, 3);
                    if (hd.contains("Xóa")) tableModel.setValueAt(true, i, 4);
                    break;
                }
            }
        }

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnLuu = new JButton("Cập nhật nhóm quyền");
        btnLuu.setBackground(new Color(52, 152, 219));
        btnLuu.setForeground(Color.WHITE);
        
        btnHuy = new JButton(isReadOnly ? "Đóng" : "Huỷ bỏ");
        btnHuy.setBackground(new Color(231, 76, 60));
        btnHuy.setForeground(Color.WHITE);
        
        if (!isReadOnly) panelBottom.add(btnLuu); // Ẩn nút lưu nếu đang ở chế độ xem chi tiết
        panelBottom.add(btnHuy);

        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(panelBottom, BorderLayout.SOUTH);
        add(mainPanel);

        btnHuy.addActionListener(e -> dispose()); 
        
        btnLuu.addActionListener(e -> {
            // 1. Lưu Tên nhóm quyền mới
            String tenMoi = txtTenNhom.getText().trim();
            NhomQuyenBUS nqBus = new NhomQuyenBUS();
            String res = nqBus.suaNhomQuyen(new NhomQuyenDTO(maNhom, tenMoi));

            if(res.equals("Thành công")) {
                // 2. Lưu lại các Checkbox
                ArrayList<ChiTietQuyenDTO> listQuyenMoi = new ArrayList<>();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    ArrayList<String> hanhDongList = new ArrayList<>();
                    if ((boolean) tableModel.getValueAt(i, 1)) hanhDongList.add("Xem");
                    if ((boolean) tableModel.getValueAt(i, 2)) hanhDongList.add("Thêm");
                    if ((boolean) tableModel.getValueAt(i, 3)) hanhDongList.add("Sửa");
                    if ((boolean) tableModel.getValueAt(i, 4)) hanhDongList.add("Xóa");
                    
                    if (!hanhDongList.isEmpty()) {
                        String maChucNang = listChucNang.get(i).getMaChucNang();
                        String hanhDongStr = String.join(", ", hanhDongList);
                        listQuyenMoi.add(new ChiTietQuyenDTO(maNhom, maChucNang, hanhDongStr));
                    }
                }
                new ChiTietQuyenBUS().luuDanhSachQuyen(maNhom, listQuyenMoi);
                JOptionPane.showMessageDialog(this, "Cập nhật quyền thành công!");
                parentGUI.loadDataToTable(); // Làm mới bảng ở màn hình chính
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, res, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}