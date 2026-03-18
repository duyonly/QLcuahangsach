package Gui;
import Bus.PhieuNhapBUS;
import Dto.PhieuNhapDTO;
import Dto.CtPhieuNhapDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
public class NhapHangGUI extends JPanel{
    private JTable tblSanPham;
    private DefaultTableModel modelSanPham;
    private JTextField txtTimKiemSP, txtSoLuongNhap, txtDonGiaNhap;
    private JButton btnThemVaoPhieu, btnTimSP; 

    private JTable tblPhieuNhap;
    private DefaultTableModel modelPhieuNhap;
    private JComboBox<String> cbxNhaCungCap;
    private JTextField txtNguoiTao;
    private JLabel lblTongTien;
    private JButton btnXoaKhoiPhieu, btnXacNhanNhap;

    public NhapHangGUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.5); 
        splitPane.setLeftComponent(taoPanelTrai_SanPham());
        splitPane.setRightComponent(taoPanelPhai_PhieuNhap());

        add(splitPane, BorderLayout.CENTER);
        ganSuKien();
    }

    private JPanel taoPanelTrai_SanPham() {
        JPanel panelTrai = new JPanel(new BorderLayout(5, 5));
        panelTrai.setBorder(BorderFactory.createTitledBorder("Danh sách Sản phẩm"));
        panelTrai.setBackground(Color.WHITE);

        JPanel panelTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTimKiem.setBackground(Color.WHITE);
        panelTimKiem.add(new JLabel("Tìm sản phẩm: "));
        txtTimKiemSP = new JTextField(15);
        panelTimKiem.add(txtTimKiemSP);
        
        btnTimSP = new JButton("Tìm"); // Khởi tạo nút TÌM
        btnTimSP.setBackground(new Color(52, 152, 219));
        btnTimSP.setForeground(Color.WHITE);
        panelTimKiem.add(btnTimSP);

        String[] colsSP = {"Mã SP", "Tên sản phẩm", "SL Tồn"};
        modelSanPham = new DefaultTableModel(colsSP, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblSanPham = new JTable(modelSanPham);
        tblSanPham.setRowHeight(25);
        JScrollPane scrollSP = new JScrollPane(tblSanPham);

        // Dữ liệu giả lập
        modelSanPham.addRow(new Object[]{"SP001", "Áo thun nam đen", 15});
        modelSanPham.addRow(new Object[]{"SP002", "Áo khoác dù trắng", 8});
        modelSanPham.addRow(new Object[]{"SP003", "Quần Jean xanh", 20});

        // TẠO LAYOUT 2 TẦNG CHỐNG BỊ CHE MẤT NÚT
        JPanel panelThaoTac = new JPanel(new BorderLayout()); 
        panelThaoTac.setBackground(Color.WHITE);

        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelInput.setBackground(Color.WHITE);
        panelInput.add(new JLabel("Số lượng:"));
        txtSoLuongNhap = new JTextField(5);
        panelInput.add(txtSoLuongNhap);
        panelInput.add(new JLabel("Đơn giá nhập:"));
        txtDonGiaNhap = new JTextField(8);
        panelInput.add(txtDonGiaNhap);

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelButton.setBackground(Color.WHITE);
        btnThemVaoPhieu = new JButton("Thêm vào phiếu");
        btnThemVaoPhieu.setBackground(new Color(46, 204, 113));
        btnThemVaoPhieu.setForeground(Color.WHITE);
        btnThemVaoPhieu.setPreferredSize(new Dimension(200, 35));
        panelButton.add(btnThemVaoPhieu);

        panelThaoTac.add(panelInput, BorderLayout.NORTH);
        panelThaoTac.add(panelButton, BorderLayout.CENTER);

        panelTrai.add(panelTimKiem, BorderLayout.NORTH);
        panelTrai.add(scrollSP, BorderLayout.CENTER);
        panelTrai.add(panelThaoTac, BorderLayout.SOUTH);

        return panelTrai;
    }

    private JPanel taoPanelPhai_PhieuNhap() {
        JPanel panelPhai = new JPanel(new BorderLayout(5, 5));
        panelPhai.setBorder(BorderFactory.createTitledBorder("Chi tiết Phiếu nhập"));
        panelPhai.setBackground(Color.WHITE);

        JPanel panelThongTin = new JPanel(new GridLayout(2, 2, 10, 10));
        panelThongTin.setBackground(Color.WHITE);
        panelThongTin.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

        panelThongTin.add(new JLabel("Nhà cung cấp:"));
        cbxNhaCungCap = new JComboBox<>(new String[]{"NCC01 - Xưởng may Vina", "NCC02 - Tổng kho Quảng Châu"});
        panelThongTin.add(cbxNhaCungCap);

        panelThongTin.add(new JLabel("Người tạo phiếu:"));
        txtNguoiTao = new JTextField("NV01 - Phạm Minh Duy");
        txtNguoiTao.setEditable(false); 
        panelThongTin.add(txtNguoiTao);

        String[] colsPN = {"Mã SP", "Tên sản phẩm", "SL", "Đơn giá", "Thành tiền"};
        modelPhieuNhap = new DefaultTableModel(colsPN, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblPhieuNhap = new JTable(modelPhieuNhap);
        tblPhieuNhap.setRowHeight(25);
        JScrollPane scrollPN = new JScrollPane(tblPhieuNhap);

        JPanel panelXacNhan = new JPanel(new BorderLayout());
        panelXacNhan.setBackground(Color.WHITE);
        
        JPanel panelTongTien = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTongTien.setBackground(Color.WHITE);
        lblTongTien = new JLabel("Tổng tiền: 0 VNĐ");
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 18));
        lblTongTien.setForeground(Color.RED);
        panelTongTien.add(lblTongTien);

        JPanel panelCacNut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCacNut.setBackground(Color.WHITE);
        
        btnXoaKhoiPhieu = new JButton("Xóa dòng chọn");
        btnXoaKhoiPhieu.setBackground(new Color(231, 76, 60));
        btnXoaKhoiPhieu.setForeground(Color.WHITE);
        
        btnXacNhanNhap = new JButton("XÁC NHẬN NHẬP HÀNG");
        btnXacNhanNhap.setBackground(new Color(46, 204, 113));
        btnXacNhanNhap.setForeground(Color.WHITE);
        btnXacNhanNhap.setFont(new Font("Arial", Font.BOLD, 14));

        panelCacNut.add(btnXoaKhoiPhieu);
        panelCacNut.add(btnXacNhanNhap);

        panelXacNhan.add(panelTongTien, BorderLayout.NORTH);
        panelXacNhan.add(panelCacNut, BorderLayout.CENTER);

        panelPhai.add(panelThongTin, BorderLayout.NORTH);
        panelPhai.add(scrollPN, BorderLayout.CENTER);
        panelPhai.add(panelXacNhan, BorderLayout.SOUTH);

        return panelPhai;
    }

    private void ganSuKien() {
        btnTimSP.addActionListener(e -> {
            String keyword = txtTimKiemSP.getText().trim().toLowerCase();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelSanPham);
            tblSanPham.setRowSorter(sorter);
            if (keyword.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
            }
        });

        tblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtSoLuongNhap.requestFocus();
            }
        });

        btnThemVaoPhieu.addActionListener(e -> {
            int row = tblSanPham.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm từ danh sách bên trái!");
                return;
            }
            
            int modelRow = tblSanPham.convertRowIndexToModel(row);
            String maSP = modelSanPham.getValueAt(modelRow, 0).toString();
            String tenSP = modelSanPham.getValueAt(modelRow, 1).toString();
            
            try {
                int sl = Integer.parseInt(txtSoLuongNhap.getText());
                double gia = Double.parseDouble(txtDonGiaNhap.getText());
                double thanhTien = sl * gia;
                
                modelPhieuNhap.addRow(new Object[]{maSP, tenSP, sl, gia, thanhTien});
                
                txtSoLuongNhap.setText("");
                txtDonGiaNhap.setText("");
                capNhatTongTien();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số lượng và đơn giá phải là số hợp lệ!");
            }
        });

        // 4. SỰ KIỆN XÓA KHỎI PHIẾU
        btnXoaKhoiPhieu.addActionListener(e -> {
            int row = tblPhieuNhap.getSelectedRow();
            if (row != -1) {
                modelPhieuNhap.removeRow(row);
                capNhatTongTien();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong phiếu để xóa!");
            }
        });
        
        // 5. SỰ KIỆN XÁC NHẬN LƯU VÀO DATABASE
        btnXacNhanNhap.addActionListener(e -> {
            int rowCount = modelPhieuNhap.getRowCount();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(this, "Phiếu nhập đang trống!");
                return;
            }

            PhieuNhapBUS bus = new PhieuNhapBUS();
            
            String maPhieu = bus.getMaPhieuMoi(); 
            if (maPhieu.equals("ERROR")) {
                JOptionPane.showMessageDialog(this, "Lỗi kết nối CSDL, không thể sinh mã tự động!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Timestamp thoiGian = new Timestamp(System.currentTimeMillis()); 
            String maNCC = cbxNhaCungCap.getSelectedItem().toString().split(" - ")[0]; 
            String maNV = "NV01"; 
            
            PhieuNhapDTO phieuNhap = new PhieuNhapDTO(maPhieu, thoiGian, "Hoàn thành", maNCC, maNV);

            ArrayList<CtPhieuNhapDTO> listChiTiet = new ArrayList<>();
            for (int i = 0; i < rowCount; i++) {
                String maSP = modelPhieuNhap.getValueAt(i, 0).toString();
                int sl = Integer.parseInt(modelPhieuNhap.getValueAt(i, 2).toString());
                double gia = Double.parseDouble(modelPhieuNhap.getValueAt(i, 3).toString());
                
                CtPhieuNhapDTO ct = new CtPhieuNhapDTO(maPhieu, maSP, sl, gia);
                listChiTiet.add(ct);
            }

            String thongBao = bus.taoPhieuNhap(phieuNhap, listChiTiet);
            
            if (thongBao.equals("Thành công")) {
                JOptionPane.showMessageDialog(this, "Nhập hàng thành công! Mã phiếu: " + maPhieu);
                modelPhieuNhap.setRowCount(0); 
                capNhatTongTien();
            } else {
                JOptionPane.showMessageDialog(this, thongBao, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });    
    }

    private void capNhatTongTien() {
        double tong = 0;
        for (int i = 0; i < modelPhieuNhap.getRowCount(); i++) {
            tong += (double) modelPhieuNhap.getValueAt(i, 4);
        }
        lblTongTien.setText("Tổng tiền: " + String.format("%,.0f", tong) + " VNĐ");
    }

}
