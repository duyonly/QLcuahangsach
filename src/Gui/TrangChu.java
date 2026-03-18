package Gui;
import javax.swing.*;

import Bus.session;
import Dto.TaiKhoanDTO;

import java.awt.*;
import java.awt.event.*;
public class TrangChu extends JFrame{
    String maNV;
    JPanel panelMenu,panelContent,panelTop;
    JButton btnSach,btnKhachHang,btnHoaDon,btnNhanVien,btnThongKe,btnDangXuat,btnPhieuNhap,btnPhieuXuat,btnNCC,btnDoiTra,btnTaiKhoan;
    public TrangChu(){
        setTitle("HỆ THỐNG QUẢN LÝ CỬA HÀNG SÁCH");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //menu bên trái
        panelMenu=new JPanel();
        panelMenu.setPreferredSize(new Dimension(150,0));
        panelMenu.setBackground(new Color(231,208,178));
        panelMenu.setLayout(new BorderLayout());
        JPanel butTon=new JPanel();
        butTon.setLayout(new GridLayout(11,1,7,7));
        butTon.setOpaque(false);
        panelMenu.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        btnSach = new JButton("Sách");
        btnKhachHang = new JButton("Khách Hàng");
        btnNCC=new JButton("Nhà Cung Cấp");
        btnTaiKhoan = new JButton("Tài Khoản");
        btnPhieuNhap = new JButton("Phiếu Nhập");
        btnPhieuXuat=new JButton("Phiếu Xuất");
        btnNhanVien = new JButton("Nhân Viên");
        btnHoaDon = new JButton("Hóa Đơn");
        btnThongKe = new JButton("Thống Kê");
        btnDoiTra = new JButton("Đổi Trả");
        btnDangXuat = new JButton("Đăng Xuất");
//         JLabel lbMenu=new JLabel("MENU",JLabel.CENTER);
//         lbMenu.setFont(new Font("Arial",Font.BOLD,18));
// lbMenu.setForeground(Color.DARK_GRAY);
// panelMenu.add(lbMenu);
styleButton(btnSach);
styleButton(btnKhachHang);
styleButton(btnNhanVien);
styleButton(btnHoaDon);
styleButton(btnThongKe);
styleButton(btnNCC);
styleButton(btnTaiKhoan);
styleButton(btnPhieuNhap);
styleButton(btnDoiTra);
styleButton(btnDangXuat);
        butTon.add(btnSach);
        butTon.add(btnKhachHang);
        butTon.add(btnNhanVien);
        butTon.add(btnTaiKhoan);
        butTon.add(btnHoaDon);
        butTon.add(btnThongKe);
        
        butTon.add(btnNCC);
        butTon.add(btnPhieuNhap);
        butTon.add(btnDoiTra);
        butTon.add(btnDangXuat);
        panelMenu.add(butTon,BorderLayout.NORTH);
        panelMenu.add(btnDangXuat,BorderLayout.SOUTH);
        btnDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int thongtin=JOptionPane.showConfirmDialog(null, "bạn có muốn đăng xuất không", "xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
                if(thongtin==JOptionPane.YES_OPTION){
                    new FormDangNhap();
                    dispose();
                }
              
            }
        });
        btnDoiTra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
              panelContent.removeAll();
              panelContent.add(new FormDoiTra(),BorderLayout.CENTER);
              panelContent.revalidate();
              panelContent.repaint();
            }
        });
        btnTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelContent.removeAll();
                // Vì TaiKhoanGui là JFrame, chúng ta lấy ContentPane của nó để add vào Panel chính
                panelContent.add(new TaiKhoanGui(), BorderLayout.CENTER);
                panelContent.revalidate();
                panelContent.repaint();
            }
        });
        //phần trên
        panelTop=new JPanel();
        panelTop.setLayout(new BorderLayout());
        panelTop.setPreferredSize(new Dimension(0,60));
        panelTop.setBackground(new Color(0,204,101));

        JLabel lblTitle=new JLabel("TRANG CHỦ - QUẢN LÝ CỬA HÀNG SÁCH");
        lblTitle.setFont(new Font("Arial",Font.BOLD,20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        panelTop.add(lblTitle,BorderLayout.CENTER);
        panelContent =new JPanel();
        panelContent.setLayout(new BorderLayout());
        JPanel dashboard = new JPanel(new GridLayout(2,2,20,20));
dashboard.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

dashboard.add(createCard("Tổng Sách","150"));
dashboard.add(createCard("Khách Hàng", "80"));
dashboard.add(createCard("Hóa Đơn", "45"));
dashboard.add(createCard("Doanh Thu", "12,500,000 VNĐ"));

panelContent.add(dashboard, BorderLayout.CENTER);
      
            add(panelMenu, BorderLayout.WEST);
            add(panelTop, BorderLayout.NORTH);
            add(panelContent, BorderLayout.CENTER);
    
            setVisible(true);
        
        if(session.quyen.equals("Quản Lý")){
            btnNhanVien.setVisible(false);
            btnThongKe.setVisible(false);
            btnPhieuNhap.setVisible(false);
            btnNCC.setVisible(false);
        }
        if(session.quyen.equals("kho")){
            btnHoaDon.setVisible(false);
            btnKhachHang.setVisible(false);
            btnThongKe.setVisible(false);
        }
    }

//hàm tạo thẻ thốn kế
    private JPanel createCard(String title,String value ){
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
    public void styleButton(JButton btn){
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(210,230,255));
        btn.setForeground(Color.black);
        btn.setFont(new Font("Arial",Font.BOLD,14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                btn.setBackground(new Color(200,230,255));
            }
            public void mouseExited(MouseEvent e){
                btn.setBackground(Color.WHITE);
            }
        });
    }

}
