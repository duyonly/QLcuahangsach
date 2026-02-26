package Gui;
import javax.swing.*;
import Dao.ConnectDB;
import Dao.XuLyDangNhap;
import Dto.TaiKhoanDTO;
import Bus.TaiKhoanBus;
import java.sql.Connection;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Nhanvien.NhanvienModel;
public class FormDangNhap {
    JFrame main;
    JTextField txtUser;
    JPasswordField   txtPass;
    JButton btnDangNhap, btnThoat,btnQMK;


public FormDangNhap(){
Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
double dai=screen.getWidth();
double cao=screen.getHeight();
main = new JFrame("Đăng Nhập");
main.setBounds((int) dai/2-250,(int) cao/2-200,500,400);
main.setLayout(null);
main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
main.setResizable(false);
JLabel label=new JLabel("Quản Lý Cửa hàng Sách");
label.setFont(new Font("Courier New",Font.BOLD,25));
label.setBounds(85,20,500,30);
main.add(label);
JLabel Ten=new JLabel("Username:");
Ten.setFont(new Font("Courier New",Font.BOLD,14));
Ten.setBounds(70,100,200,30);
main.add(Ten);
txtUser =new JTextField();
txtUser.setBounds(150,100,200,30);
JLabel matKhau=new JLabel("Password:");
matKhau.setFont(new Font("Courier New",Font.BOLD,14));
matKhau.setBounds(70,150,200,30);
main.add(matKhau);
txtPass = new JPasswordField();
txtPass.setBounds(150,150,200,30);
btnDangNhap = new JButton("Đăng Nhập");
btnDangNhap.setBounds(150,200,100,30);
btnThoat =new JButton("Thoát");
btnThoat.setBounds(260,200,90,30);
btnQMK=new JButton("Quên Mật Khẩu");
btnQMK.setBounds(100,250,300,30);
main.add(txtUser);
main.add(txtPass);
main.add(btnDangNhap);
main.add(btnThoat);
main.add(btnQMK);
main.setVisible(true);

txtPass.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            DangNhap();
        }
    }
});
btnDangNhap.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e){
        DangNhap();
    }
});
btnThoat.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e){
        System.exit(0);
    }
}); 
btnQMK.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e){
        QuenMK();
    }
});
}
public void DangNhap(){
   String user=txtUser.getText().trim();
   String pass=new String(txtPass.getPassword()).trim();
   
   TaiKhoanDTO tk=new TaiKhoanDTO();
   tk.setTen(user);
   tk.setMatKhau(pass);
   TaiKhoanBus Bus=new TaiKhoanBus();
   boolean ketQua=Bus.dangNhap(tk);
//   if(ketQua){
//    JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);//
//    new TrangChu();
//    main.dispose();
//   }
//
 //
 //
   //
    if(ketQua){
        JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
        // Tạo nhân viên đăng nhập
        Nhanvien.NhanvienModel nv = new Nhanvien.NhanvienModel();
        nv.setChucVu("Admin");
        new TrangChu(nv);
        main.dispose();
    }
   else{
    JOptionPane.showMessageDialog(null, "Tên Không Tồn Tại", "Thông Báo", JOptionPane.ERROR_MESSAGE);
   }
}
public void QuenMK(){
    new FormQuenMK();
}
}

