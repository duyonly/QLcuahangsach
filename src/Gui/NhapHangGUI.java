package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NhapHangGUI extends JPanel {
    private JTable tblSanPham;
    private DefaultTableModel modelSanPham;
    private JTextField txtTimKiemSP, txtSoLuongNhap, txtDonGiaNhap;
    private JButton btnThemVaoPhieu;

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

        // Sử dụng JSplitPane để chia đôi màn hình có thể kéo dãn được
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.5); // Chia đều 50-50
        splitPane.setLeftComponent(taoPanelTrai_SanPham());
        splitPane.setRightComponent(taoPanelPhai_PhieuNhap());

        add(splitPane, BorderLayout.CENTER);
        
        ganSuKien();
    }

    // ==========================================
    // 1. THIẾT KẾ NỬA BÊN TRÁI: DANH SÁCH SẢN PHẨM
    // ==========================================
    private JPanel taoPanelTrai_SanPham() {
        JPanel panelTrai = new JPanel(new BorderLayout(5, 5));
        panelTrai.setBorder(BorderFactory.createTitledBorder("Danh sách Sản phẩm"));
        panelTrai.setBackground(Color.WHITE);

        // -- Thanh tìm kiếm phía trên --
        JPanel panelTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTimKiem.setBackground(Color.WHITE);
        panelTimKiem.add(new JLabel("Tìm sản phẩm: "));
        txtTimKiemSP = new JTextField(15);
        panelTimKiem.add(txtTimKiemSP);
        panelTimKiem.add(new JButton("Tìm"));

        // -- Bảng danh sách sản phẩm ở giữa --
        String[] colsSP = {"Mã SP", "Tên sản phẩm", "SL Tồn"};
        modelSanPham = new DefaultTableModel(colsSP, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblSanPham = new JTable(modelSanPham);
        tblSanPham.setRowHeight(25);
        JScrollPane scrollSP = new JScrollPane(tblSanPham);

        // Thêm dữ liệu giả để test
        modelSanPham.addRow(new Object[]{"SP001", "Áo thun nam đen", 15});
        modelSanPham.addRow(new Object[]{"SP002", "Áo khoác dù trắng", 8});
        modelSanPham.addRow(new Object[]{"SP003", "Quần Jean xanh", 20});

        // -- Khu vực nhập số lượng & đơn giá bên dưới --
        JPanel panelThaoTac = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelThaoTac.setBackground(Color.WHITE);
        
        panelThaoTac.add(new JLabel("Số lượng:"));
        txtSoLuongNhap = new JTextField(5);
        panelThaoTac.add(txtSoLuongNhap);
        
        panelThaoTac.add(new JLabel("Đơn giá nhập:"));
        txtDonGiaNhap = new JTextField(8);
        panelThaoTac.add(txtDonGiaNhap);
        
        btnThemVaoPhieu = new JButton("Thêm vào phiếu");
        btnThemVaoPhieu.setBackground(new Color(52, 152, 219));
        btnThemVaoPhieu.setForeground(Color.WHITE);
        panelThaoTac.add(btnThemVaoPhieu);

        panelTrai.add(panelTimKiem, BorderLayout.NORTH);
        panelTrai.add(scrollSP, BorderLayout.CENTER);
        panelTrai.add(panelThaoTac, BorderLayout.SOUTH);

        return panelTrai;
    }

    // ==========================================
    // 2. THIẾT KẾ NỬA BÊN PHẢI: CHI TIẾT PHIẾU NHẬP
    // ==========================================
    private JPanel taoPanelPhai_PhieuNhap() {
        JPanel panelPhai = new JPanel(new BorderLayout(5, 5));
        panelPhai.setBorder(BorderFactory.createTitledBorder("Chi tiết Phiếu nhập"));
        panelPhai.setBackground(Color.WHITE);

        // -- Thông tin chung của phiếu nhập phía trên --
        JPanel panelThongTin = new JPanel(new GridLayout(2, 2, 10, 10));
        panelThongTin.setBackground(Color.WHITE);
        panelThongTin.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

        panelThongTin.add(new JLabel("Nhà cung cấp:"));
        cbxNhaCungCap = new JComboBox<>(new String[]{"NCC01 - Xưởng may Vina", "NCC02 - Tổng kho Quảng Châu"});
        panelThongTin.add(cbxNhaCungCap);

        panelThongTin.add(new JLabel("Người tạo phiếu:"));
        txtNguoiTao = new JTextField("NV001 - Phạm Minh Duy");
        txtNguoiTao.setEditable(false); // Chỉ đọc, sẽ tự lấy theo tài khoản đăng nhập
        panelThongTin.add(txtNguoiTao);

        // -- Bảng giỏ hàng nhập ở giữa --
        String[] colsPN = {"Mã SP", "Tên sản phẩm", "SL", "Đơn giá", "Thành tiền"};
        modelPhieuNhap = new DefaultTableModel(colsPN, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblPhieuNhap = new JTable(modelPhieuNhap);
        tblPhieuNhap.setRowHeight(25);
        JScrollPane scrollPN = new JScrollPane(tblPhieuNhap);

        // -- Tổng kết và xác nhận bên dưới --
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

    // ==========================================
    // 3. XỬ LÝ SỰ KIỆN CƠ BẢN
    // ==========================================
    private void ganSuKien() {
        // Sự kiện: Bấm "Thêm vào phiếu"
        btnThemVaoPhieu.addActionListener(e -> {
            int row = tblSanPham.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm từ danh sách bên trái!");
                return;
            }
            
            String maSP = modelSanPham.getValueAt(row, 0).toString();
            String tenSP = modelSanPham.getValueAt(row, 1).toString();
            
            try {
                int sl = Integer.parseInt(txtSoLuongNhap.getText());
                double gia = Double.parseDouble(txtDonGiaNhap.getText());
                double thanhTien = sl * gia;
                
                // Thêm vào bảng phiếu nhập bên phải
                modelPhieuNhap.addRow(new Object[]{maSP, tenSP, sl, gia, thanhTien});
                
                // Reset ô nhập
                txtSoLuongNhap.setText("");
                txtDonGiaNhap.setText("");
                
                // Cập nhật lại tổng tiền (Tính nhẩm giả lập)
                capNhatTongTien();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số lượng và đơn giá phải là số hợp lệ!");
            }
        });

        // Sự kiện: Bấm "Xóa dòng chọn"
        btnXoaKhoiPhieu.addActionListener(e -> {
            int row = tblPhieuNhap.getSelectedRow();
            if (row != -1) {
                modelPhieuNhap.removeRow(row);
                capNhatTongTien();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong phiếu để xóa!");
            }
        });
        
        // Sự kiện: Bấm "Xác nhận nhập hàng"
        btnXacNhanNhap.addActionListener(e -> {
            if (modelPhieuNhap.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Phiếu nhập đang trống!");
                return;
            }
            // Logic gọi BUS và DAO sẽ nằm ở đây
            JOptionPane.showMessageDialog(this, "Nhập hàng thành công! Đã cập nhật vào Database.");
            modelPhieuNhap.setRowCount(0); // Làm sạch phiếu
            capNhatTongTien();
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