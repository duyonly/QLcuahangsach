package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TrangChu extends JFrame {
    JPanel panelMenu, panelContent, panelTop;
    
    // Bổ sung thêm btnNhapHang và btnPhanQuyen
    JButton btnSach, btnKhachHang, btnHoaDon, btnNhanVien, btnThongKe, btnNhapHang, btnPhanQuyen, btnDangXuat;

    public TrangChu() {
        setTitle("HỆ THỐNG QUẢN LÝ CỬA HÀNG SÁCH");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1. Menu bên trái
        panelMenu = new JPanel();
        panelMenu.setPreferredSize(new Dimension(200, 0));
        panelMenu.setBackground(new Color(33, 150, 243));
        
        // Thay đổi GridLayout từ 7 hàng lên 9 hàng để chứa thêm nút
        panelMenu.setLayout(new GridLayout(9, 1, 10, 10));

        // Khởi tạo các nút
        btnSach = new JButton("Sách");
        btnNhapHang = new JButton("Nhập Hàng"); // Nút mới thêm
        btnHoaDon = new JButton("Hóa Đơn");
        btnKhachHang = new JButton("Khách Hàng");
        btnNhanVien = new JButton("Nhân Viên");
        btnThongKe = new JButton("Thống Kê");
        btnPhanQuyen = new JButton("Phân Quyền"); // Nút mới thêm
        btnDangXuat = new JButton("Đăng Xuất");

        // Thêm vào panelMenu theo thứ tự hợp lý
        panelMenu.add(new JLabel("MENU", JLabel.CENTER));
        panelMenu.add(btnSach);
        panelMenu.add(btnNhapHang);
        panelMenu.add(btnHoaDon);
        panelMenu.add(btnKhachHang);
        panelMenu.add(btnNhanVien);
        panelMenu.add(btnThongKe);
        panelMenu.add(btnPhanQuyen);
        panelMenu.add(btnDangXuat);

        btnDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạm comment form đăng nhập nếu bạn chưa tạo file FormDangNhap.java để tránh lỗi biên dịch
                // new FormDangNhap(); 
                dispose();
            }
        });

        // 2. Phần trên (Header)
        panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(0, 60));
        panelTop.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel("TRANG CHỦ - QUẢN LÝ CỬA HÀNG SÁCH");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        panelTop.add(lblTitle); // Đã bổ sung dòng này để title hiển thị lên giao diện

        // 3. Phần nội dung (Content)
        panelContent = new JPanel();
        panelContent.setLayout(new GridLayout(2, 2, 20, 20));
        panelContent.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        panelContent.add(createCard("Tổng Sách", "150"));
        panelContent.add(createCard("Khách Hàng", "80"));
        panelContent.add(createCard("Hóa Đơn", "45"));
        panelContent.add(createCard("Doanh Thu", "12,500,000 VNĐ"));

        // Add các phần chính vào JFrame
        add(panelMenu, BorderLayout.WEST);
        add(panelTop, BorderLayout.NORTH);
        add(panelContent, BorderLayout.CENTER);

        setVisible(true);
    }

    // Hàm tạo thẻ thống kê
    private JPanel createCard(String title, String value) {
        JPanel card = new JPanel();
        card.setBackground(new Color(240, 240, 240));
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel lblTitle = new JLabel(title, JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblValue = new JLabel(value, JLabel.CENTER);
        lblValue.setFont(new Font("Arial", Font.BOLD, 22));
        lblValue.setForeground(Color.RED);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);

        return card;
    }
}