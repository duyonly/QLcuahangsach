package Gui;
import javax.swing.*;

import Bus.session;
import Dto.TaiKhoanDTO;

import java.awt.*;
import java.awt.event.*;
public class TrangChu extends JFrame{
    String maNV;
    JPanel panelMenu,panelContent,panelTop;
    JButton btnSach,btnKhachHang,btnHoaDon,btnNhanVien,btnThongKe,btnDangXuat,btnPhieuNhap,btnPhanQuyen,btnPhieuXuat,btnNhaCungCap,btnDoiTra,btnTaiKhoan;
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
       JPanel butTon = new JPanel();
       butTon.setLayout(new BoxLayout(butTon, BoxLayout.Y_AXIS)); 
       butTon.setOpaque(false);
       JScrollPane scrollPane = new JScrollPane(butTon);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
scrollPane.setBorder(null); // Xóa viền của thanh cuộn cho đẹp
scrollPane.setOpaque(false);
scrollPane.getViewport().setOpaque(false);

// Tăng tốc độ cuộn chuột
scrollPane.getVerticalScrollBar().setUnitIncrement(16);

// Add thanh cuộn vào trung tâm menu
panelMenu.add(scrollPane, BorderLayout.CENTER);
       // Thêm một khoảng đệm nhỏ ở trên cùng
       butTon.add(Box.createVerticalStrut(10));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        btnSach = new JButton("Sách");
        btnKhachHang = new JButton("Khách Hàng");
        btnNhaCungCap = new JButton("Nhà Cung Cấp"); 
        btnNhanVien = new JButton("Nhân Viên");
        btnHoaDon = new JButton("Hóa Đơn");
        btnThongKe = new JButton("Thống Kê");
        btnPhieuNhap=new JButton("phiếu nhập");
        btnPhieuXuat=new JButton("phiếu xuất");
        btnDoiTra = new JButton("Đổi Trả");
        btnPhanQuyen = new JButton("phân quyền");
        btnDangXuat = new JButton("Đăng Xuất");
        btnTaiKhoan=new JButton("tài Khoản");
//         JLabel lbMenu=new JLabel("MENU",JLabel.CENTER);
//         lbMenu.setFont(new Font("Arial",Font.BOLD,18));
// lbMenu.setForeground(Color.DARK_GRAY);
// panelMenu.add(lbMenu);
styleButton(btnSach);
styleButton(btnKhachHang);
styleButton(btnNhanVien);
styleButton(btnHoaDon);
styleButton(btnThongKe);
styleButton(btnNhaCungCap);
styleButton(btnTaiKhoan);
styleButton(btnPhieuNhap);
styleButton(btnDoiTra);
styleButton(btnDangXuat);
styleButton(btnSach);
styleButton(btnPhanQuyen);
styleButton(btnPhieuXuat);

        butTon.add(btnSach);
        butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnKhachHang);butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnNhanVien);butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnTaiKhoan);butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnHoaDon);butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnThongKe);butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnPhieuXuat);butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnPhanQuyen);butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnNhaCungCap);butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnPhieuNhap);butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnDoiTra);butTon.add(Box.createVerticalStrut(10));
        butTon.add(btnDangXuat);butTon.add(Box.createVerticalStrut(10));
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
        btnHoaDon.addActionListener(e -> {
            panelContent.removeAll();
        
            JTabbedPane tab = new JTabbedPane();
            tab.add("Hóa đơn", new HoaDonGUI());
            tab.add("Chi tiết HĐ", new ChiTietHoaDonGUI());
        
            panelContent.add(tab, BorderLayout.CENTER);
        
            panelContent.revalidate();
            panelContent.repaint();
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
        // Sự kiện nút Nhập Hàng
btnPhieuNhap.addActionListener(e -> {
    panelContent.removeAll();
    panelContent.add(new NhapHangGUI(), BorderLayout.CENTER);
    panelContent.revalidate();
    panelContent.repaint();
});

// Sự kiện nút Phân Quyền
btnPhanQuyen.addActionListener(e -> {
    panelContent.removeAll();
    panelContent.add(new PhanQuyenGUI(), BorderLayout.CENTER);
    panelContent.revalidate();
    panelContent.repaint();
});
        btnSach.addActionListener(e -> {
            panelContent.removeAll();
            panelContent.add(new SanPhamGUI(), BorderLayout.CENTER); // Đảm bảo PhieuXuatGUI cũng đã đổi sang JPanel
            panelContent.revalidate();
            panelContent.repaint();
        });
        
        btnPhieuXuat.addActionListener(e -> {
            panelContent.removeAll();
            panelContent.add(new PhieuXuatGUI(), BorderLayout.CENTER); // Đảm bảo PhieuXuatGUI cũng đã đổi sang JPanel
            panelContent.revalidate();
            panelContent.repaint();
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
        btnNhanVien.addActionListener(e -> {
            panelContent.removeAll();
            panelContent.add(new NhanvienGUI(Bus.session.quyen), BorderLayout.CENTER);
            panelContent.revalidate();
            panelContent.repaint();
        });
        btnThongKe.addActionListener(e -> {
            panelContent.removeAll();
            panelContent.add(new ThongkeGUI(), BorderLayout.CENTER);
            panelContent.revalidate();
            panelContent.repaint();
        });
        // btnKhachHang.addActionListener(new ActionListener(){
        //     @Override
        //     public void actionPerformed(ActionEvent e){
        //         new FormKhachHang();
        //     }
        // });
        // btnNhaCungCap.addActionListener(new ActionListener(){
        //     @Override
        //     public void actionPerformed(ActionEvent e){
        //         new FormNhaCungCap();
        //     }
        // });
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
        
      
            add(panelMenu, BorderLayout.WEST);
            add(panelTop, BorderLayout.NORTH);
            add(panelContent, BorderLayout.CENTER);
            loadDashboard();
            setVisible(true);
        
        if(session.quyen.equals("Quản Lý")){
            btnNhanVien.setVisible(false);
            btnThongKe.setVisible(false);
            btnPhieuNhap.setVisible(false);
            btnNhaCungCap.setVisible(false);
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
    public void loadDashboard() {
        panelContent.removeAll();
    
        panelContent.add(new ThongkeGUI(), BorderLayout.CENTER);
    
        panelContent.revalidate();
        panelContent.repaint();
    }
    public void styleButton(JButton btn){
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(210,230,255));
        btn.setForeground(Color.black);
        btn.setFont(new Font("Arial",Font.BOLD,14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btn.setPreferredSize(new Dimension(130, 40));
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
