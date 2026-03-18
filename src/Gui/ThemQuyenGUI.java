package Gui;
import Bus.NhomQuyenBUS;
import Bus.ChiTietQuyenBUS;
import Bus.DanhMucChucNangBUS;
import Dto.NhomQuyenDTO;
import Dto.ChiTietQuyenDTO;
import Dto.DanhMucChucNangDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
public class ThemQuyenGUI extends JDialog{
    private JTextField txtMaNhom;
    private JTextField txtTenNhom;
    private JTable tableQuyen;
    private DefaultTableModel tableModel;
    private JButton btnLuu, btnHuy;
    private PhanQuyenGUI parentGUI;
    private ArrayList<DanhMucChucNangDTO> listChucNang; 
    public ThemQuyenGUI(JFrame parent, PhanQuyenGUI parentGUI) {
        super(parent, "Thêm mới Nhóm quyền", true);
        this.parentGUI = parentGUI;
        setSize(800, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelTop = new JPanel(new GridLayout(2, 2, 10, 10)); 
        panelTop.add(new JLabel("Mã nhóm quyền:"));
        txtMaNhom = new JTextField();
        panelTop.add(txtMaNhom);
        panelTop.add(new JLabel("Tên nhóm quyền mới:"));
        txtTenNhom = new JTextField();
        panelTop.add(txtTenNhom);

        String[] columns = {"Danh mục chức năng", "Xem", "Thêm", "Sửa", "Xóa"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? String.class : Boolean.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 0;
            }
        };

        tableQuyen = new JTable(tableModel);
        tableQuyen.setRowHeight(35); 
        JScrollPane scrollPane = new JScrollPane(tableQuyen);

        DanhMucChucNangBUS dmBus = new DanhMucChucNangBUS();
        listChucNang = dmBus.getAll();
        for (DanhMucChucNangDTO dm : listChucNang) {
            tableModel.addRow(new Object[]{dm.getTenChucNang(), false, false, false, false});
        }

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnLuu = new JButton("THÊM MỚI");
        btnLuu.setBackground(new Color(46, 204, 113)); 
        btnLuu.setForeground(Color.WHITE);
        btnHuy = new JButton("HUỶ BỎ");
        btnHuy.setBackground(new Color(231, 76, 60)); 
        btnHuy.setForeground(Color.WHITE);
        panelBottom.add(btnLuu);
        panelBottom.add(btnHuy);

        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(panelBottom, BorderLayout.SOUTH);
        add(mainPanel);

        btnHuy.addActionListener(e -> dispose()); 
        
        btnLuu.addActionListener(e -> {
            String maNhom = txtMaNhom.getText().trim();
            String tenNhom = txtTenNhom.getText().trim();
            
            NhomQuyenBUS bus = new NhomQuyenBUS();
            String thongBao = bus.themNhomQuyen(new NhomQuyenDTO(maNhom, tenNhom));
            
            if (thongBao.equals("Thành công")) {
                ArrayList<ChiTietQuyenDTO> listQuyenTiepTheo = new ArrayList<>();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    ArrayList<String> hanhDongList = new ArrayList<>();
                    if ((boolean) tableModel.getValueAt(i, 1)) hanhDongList.add("Xem");
                    if ((boolean) tableModel.getValueAt(i, 2)) hanhDongList.add("Thêm");
                    if ((boolean) tableModel.getValueAt(i, 3)) hanhDongList.add("Sửa");
                    if ((boolean) tableModel.getValueAt(i, 4)) hanhDongList.add("Xóa");
                    
                    if (!hanhDongList.isEmpty()) {
                        String maChucNang = listChucNang.get(i).getMaChucNang();
                        String hanhDongStr = String.join(", ", hanhDongList);
                        listQuyenTiepTheo.add(new ChiTietQuyenDTO(maNhom, maChucNang, hanhDongStr));
                    }
                }
                new ChiTietQuyenBUS().luuDanhSachQuyen(maNhom, listQuyenTiepTheo);

                JOptionPane.showMessageDialog(this, "Thêm nhóm quyền thành công!");
                parentGUI.loadDataToTable(); 
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, thongBao, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
