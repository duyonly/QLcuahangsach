package Gui;

import java.awt.Event;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import Bus.TaiKhoanBus;
import Dto.TaiKhoanDTO;

public class FormQuenMK {
    
    JFrame main;
    JTextField txtUser, txtEmail;
    JPasswordField txtNewPass;
    JButton btnXacNhan, btnHuy;

    public FormQuenMK() {

        main = new JFrame("Quên Mật Khẩu");
        main.setSize(450, 300);
        main.setLocationRelativeTo(null);
        main.setLayout(null);
        main.setResizable(false);

        JLabel title = new JLabel("KHÔI PHỤC MẬT KHẨU");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(90, 20, 300, 30);
        main.add(title);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(60, 80, 100, 25);
        main.add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(160, 80, 200, 25);
        main.add(txtUser);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(60, 120, 100, 25);
        main.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(160, 120, 200, 25);
        main.add(txtEmail);

        JLabel lblNewPass = new JLabel("Mật khẩu mới:");
        lblNewPass.setBounds(60, 160, 100, 25);
        main.add(lblNewPass);

        txtNewPass = new JPasswordField();
        txtNewPass.setBounds(160, 160, 200, 25);
        main.add(txtNewPass);

        btnXacNhan = new JButton("Xác Nhận");
        btnXacNhan.setBounds(100, 210, 100, 30);
        main.add(btnXacNhan);

        btnHuy = new JButton("Hủy");
        btnHuy.setBounds(230, 210, 100, 30);
        main.add(btnHuy);

        main.setVisible(true);
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                XuLyQuenMK();
            }
        });
        btnHuy.addActionListener(e -> main.dispose());
    }
    public void XuLyQuenMK(){
        String user=txtUser.getText().trim();
        String email=txtEmail.getText().trim();
        String newPass=new String(txtNewPass.getPassword()).trim();
        if (user.isEmpty() || email.isEmpty() || newPass.isEmpty()) {
            JOptionPane.showMessageDialog(main,
                    "Vui lòng nhập đầy đủ thông tin!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        TaiKhoanDTO tk=new TaiKhoanDTO();
        tk.setTen(user);
        tk.setEmail(email);
        tk.setMatKhau(newPass);
        TaiKhoanBus Bus=new TaiKhoanBus();
        boolean ketQua=Bus.QuenMK(tk);
        if (ketQua) {
            JOptionPane.showMessageDialog(main,
                    "Đổi mật khẩu thành công!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
            main.dispose();
        } else {
            JOptionPane.showMessageDialog(main,
                    "Username hoặc Email không đúng!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
