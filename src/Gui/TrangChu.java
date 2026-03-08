package Gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TrangChu extends JFrame{
    JPanel panelMenu,panelContent,panelTop;
    JButton btnSach,btnKhachHang,btnHoaDon,btnNhanVien,btnThongKe,btnDangXuat,btnNhaCungCap;
    public TrangChu(){
        setTitle("HỆ THỐNG QUẢN LÝ CỬA HÀNG SÁCH");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //menu bên trái
        panelMenu=new JPanel();
        panelMenu.setPreferredSize(new Dimension(200,0));
        panelMenu.setBackground(new Color(33,150,243));
        panelMenu.setLayout(new GridLayout(8,1,10,10));
        btnSach = new JButton("Sách");
        btnKhachHang = new JButton("Khách Hàng");
        btnNhaCungCap = new JButton("Nhà Cung Cấp");
        btnNhanVien = new JButton("Nhân Viên");
        btnHoaDon = new JButton("Hóa Đơn");
        btnThongKe = new JButton("Thống Kê");
        btnDangXuat = new JButton("Đăng Xuất");
        panelMenu.add(new JLabel("MENU",JLabel.CENTER));
        panelMenu.add(btnSach);
        panelMenu.add(btnKhachHang);
        panelMenu.add(btnNhaCungCap);
        panelMenu.add(btnNhanVien);
        panelMenu.add(btnHoaDon);
        panelMenu.add(btnThongKe);
        panelMenu.add(btnDangXuat);
        btnDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new FormDangNhap();
                dispose();
            }
        });
        btnKhachHang.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new FormKhachHang();
            }
        });
        btnNhaCungCap.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new FormNhaCungCap();
            }
        });
        //phần trên
        panelTop=new JPanel();
        panelTop.setPreferredSize(new Dimension(0,60));
        panelTop.setBackground(Color.WHITE);
        JLabel lblTitle=new JLabel("TRANG CHỦ - QUẢN LÝ CỬA HÀNG SÁCH");
        lblTitle.setFont(new Font("Arial",Font.BOLD,20));
        panelContent =new JPanel();
        panelContent.setLayout(new GridLayout(2,2,20,20));
        panelContent.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panelContent.add(createCard("Tổng Sách","150"));
        panelContent.add(createCard("Khách Hàng", "80"));
        panelContent.add(createCard("Hóa Đơn", "45"));
        panelContent.add(createCard("Doanh Thu", "12,500,000 VNĐ"));
      
            add(panelMenu, BorderLayout.WEST);
            add(panelTop, BorderLayout.NORTH);
            add(panelContent, BorderLayout.CENTER);
    
            setVisible(true);
        
        
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
}
