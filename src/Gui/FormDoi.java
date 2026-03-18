package Gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Bus.session;

import java.awt.*;

import Bus.DoiTraBus;
import Dto.DoiTraDto;

public class FormDoi extends JFrame {
     JTextField txtMaHD, txtSachCu, txtSachMoi, txtSoLuong, txtTienThem;
    JTextArea txtLyDo;
    JButton btnXacNhan;

    DoiTraBus bus = new DoiTraBus();

    public FormDoi(String maHD,String maSach){
        
        setTitle("Đổi Sách");
        setSize(350,350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7,2,10,10));

        txtMaHD = new JTextField(maHD);
        txtSachCu = new JTextField(maSach);
        txtSachMoi = new JTextField();
        txtSoLuong = new JTextField();
        txtTienThem = new JTextField();
        txtLyDo = new JTextArea();

        btnXacNhan = new JButton("Xác Nhận Đổi");

        add(new JLabel("Mã HĐ"));
        add(txtMaHD);

        add(new JLabel("Sách Cũ"));
        add(txtSachCu);

        add(new JLabel("Sách Mới"));
        add(txtSachMoi);

        add(new JLabel("Số Lượng"));
        add(txtSoLuong);
         add(new JLabel("Tiền Thêm"));
        add(txtTienThem);

        add(new JLabel("Lý Do"));
        add(new JScrollPane(txtLyDo));

        add(new JLabel());
        add(btnXacNhan);

        btnXacNhan.addActionListener(e -> xuLyDoi());

        setVisible(true);
    }
    private void xuLyDoi(){

        DoiTraDto dto = new DoiTraDto();

        dto.setMaHD(txtMaHD.getText());
        dto.setMaSachCu(txtSachCu.getText());
        dto.setMaSachMoi(txtSachMoi.getText());
        dto.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        dto.setLoai("Đổi");
        dto.setLyDo(txtLyDo.getText());
        dto.setTienThem(Double.parseDouble(txtTienThem.getText()));
        dto.setTienHoan(0);
        dto.setMaNV(session.maNV);
        dto.setNgayYeuCau(new java.util.Date());
        if(bus.taoYeuCau(dto)){
            JOptionPane.showMessageDialog(this,"Đổi sách thành công");
            dispose();
        }else{
               JOptionPane.showMessageDialog(this,"Đổi sách thất bại");
        }
    }
}
