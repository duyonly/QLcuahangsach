package Gui;

import Dto.KhachHangDTO;

import javax.swing.*;
import java.awt.*;

public class ChiTietKhachHangDialog extends JDialog {
    public ChiTietKhachHangDialog(Frame owner, KhachHangDTO k){
        super(owner, "Chi tiết khách hàng", true);
        setSize(400,320);
        setLayout(null);
        setLocationRelativeTo(owner);

        JLabel lblMa = new JLabel("Mã:"); lblMa.setBounds(20,20,100,25); add(lblMa);
        JTextField txtMa = new JTextField(String.valueOf(k.getMaKH())); txtMa.setBounds(120,20,240,25); txtMa.setEditable(false); add(txtMa);

        JLabel lblTen = new JLabel("Tên:"); lblTen.setBounds(20,60,100,25); add(lblTen);
        JTextField txtTen = new JTextField(k.getTenKH()==null?"":k.getTenKH()); txtTen.setBounds(120,60,240,25); txtTen.setEditable(false); add(txtTen);

        JLabel lblDiaChi = new JLabel("Địa chỉ:"); lblDiaChi.setBounds(20,100,100,25); add(lblDiaChi);
        JTextField txtDiaChi = new JTextField(k.getDiaChi()==null?"":k.getDiaChi()); txtDiaChi.setBounds(120,100,240,25); txtDiaChi.setEditable(false); add(txtDiaChi);

        JLabel lblSDT = new JLabel("SĐT:"); lblSDT.setBounds(20,140,100,25); add(lblSDT);
        JTextField txtSDT = new JTextField(k.getSDT()==null?"":k.getSDT()); txtSDT.setBounds(120,140,240,25); txtSDT.setEditable(false); add(txtSDT);

        JLabel lblEmail = new JLabel("Email:"); lblEmail.setBounds(20,180,100,25); add(lblEmail);
        JTextField txtEmail = new JTextField(k.getEmail()==null?"":k.getEmail()); txtEmail.setBounds(120,180,240,25); txtEmail.setEditable(false); add(txtEmail);

        JButton btnEdit = new JButton("Sửa"); btnEdit.setBounds(140,230,90,30); add(btnEdit);
        JButton btnClose = new JButton("Đóng"); btnClose.setBounds(260,230,100,30); add(btnClose);

        btnClose.addActionListener(e -> dispose());

        btnEdit.addActionListener(e -> {
            dispose();
            try{
                if(getOwner() instanceof FormKhachHang){
                    ((FormKhachHang)getOwner()).openEditDialog(k);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        setResizable(false);
    }
}

