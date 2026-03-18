package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TrangChu extends JFrame {
    JPanel panelMenu, panelTop;
    JPanel panelMainContent;
    CardLayout cardLayout;   

    JButton btnSach, btnKhachHang, btnHoaDon, btnNhanVien, btnThongKe, btnNhapHang, btnPhanQuyen, btnDangXuat;

    public TrangChu() {
        setTitle("HỆ THỐNG QUẢN LÝ CỬA HÀNG"); 
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1. TẠO MENU BÊN TRÁI
        panelMenu = new JPanel();
        panelMenu.setPreferredSize(new Dimension(200, 0));
        panelMenu.setBackground(new Color(33, 150, 243));
        panelMenu.setLayout(new GridLayout(9, 1, 10, 10));

        btnSach = new JButton("Sản Phẩm");
        btnNhapHang = new JButton("Nhập Hàng"); 
        btnHoaDon = new JButton("Hóa Đơn");
        btnKhachHang = new JButton("Khách Hàng");
        btnNhanVien = new JButton("Nhân Viên");
        btnThongKe = new JButton("Thống Kê");
        btnPhanQuyen = new JButton("Phân Quyền"); 
        btnDangXuat = new JButton("Đăng Xuất");

        panelMenu.add(new JLabel("MENU", JLabel.CENTER));
        panelMenu.add(btnSach);
        panelMenu.add(btnNhapHang);
        panelMenu.add(btnHoaDon);
        panelMenu.add(btnKhachHang);
        panelMenu.add(btnNhanVien);
        panelMenu.add(btnThongKe);
        panelMenu.add(btnPhanQuyen);
        panelMenu.add(btnDangXuat);

        panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(0, 60));
        panelTop.setBackground(Color.WHITE);
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ CỬA HÀNG");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        panelTop.add(lblTitle);

        cardLayout = new CardLayout();
        panelMainContent = new JPanel(cardLayout);

        JPanel panelThongKe = new JPanel();
        panelThongKe.setLayout(new GridLayout(2, 2, 20, 20));
        panelThongKe.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panelThongKe.add(createCard("Tổng Sản Phẩm", "150"));
        panelThongKe.add(createCard("Khách Hàng", "80"));
        panelThongKe.add(createCard("Hóa Đơn", "45"));
        panelThongKe.add(createCard("Doanh Thu", "12,500,000 VNĐ"));

        panelMainContent.add(panelThongKe, "TrangThongKe");
        panelMainContent.add(new NhapHangGUI(), "TrangNhapHang");
        panelMainContent.add(new PhanQuyenGUI(), "TrangPhanQuyen");

        btnThongKe.addActionListener(e -> cardLayout.show(panelMainContent, "TrangThongKe"));
        btnNhapHang.addActionListener(e -> cardLayout.show(panelMainContent, "TrangNhapHang"));
        btnPhanQuyen.addActionListener(e -> cardLayout.show(panelMainContent, "TrangPhanQuyen"));

        btnDangXuat.addActionListener(e -> System.exit(0));

        add(panelMenu, BorderLayout.WEST);
        add(panelTop, BorderLayout.NORTH);
        add(panelMainContent, BorderLayout.CENTER);

        setVisible(true);
    }

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