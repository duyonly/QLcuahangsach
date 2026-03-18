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

public class FormTra extends JFrame{
    JTextField txtMaHD, txtSachCu, txtSoLuong, txtTienHoan;
    JTextArea txtLyDo;
    JButton btnXacNhan;
    DoiTraBus bus = new DoiTraBus();

    public FormTra(String maHD,String maSach){
        
        setTitle("Trả Sách");
        setSize(350,300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6,2,10,10));

        txtMaHD = new JTextField(maHD);
        txtSachCu = new JTextField(maSach);
        txtSoLuong = new JTextField();
         txtTienHoan = new JTextField();
        txtLyDo = new JTextArea();

        btnXacNhan = new JButton("Xác Nhận Trả");

        add(new JLabel("Mã HĐ"));
        add(txtMaHD);

        add(new JLabel("Sách"));
        add(txtSachCu);

        add(new JLabel("Số Lượng"));
        add(txtSoLuong);

        add(new JLabel("Tiền Hoàn"));
        add(txtTienHoan);
         add(new JLabel("Lý Do"));
        add(new JScrollPane(txtLyDo));

        add(new JLabel());
        add(btnXacNhan);

        btnXacNhan.addActionListener(e -> xuLyTra());

        setVisible(true);
    }
     private void xuLyTra(){

        DoiTraDto dto = new DoiTraDto();

        dto.setMaHD(txtMaHD.getText());
        dto.setMaSachCu(txtSachCu.getText());
       
        dto.setLoai("Trả");
        dto.setLyDo(txtLyDo.getText());
       
        dto.setTienThem(0);
        
        dto.setMaNV(session.maNV);
        dto.setNgayYeuCau(new java.util.Date());
        String tien = txtTienHoan.getText().trim();

double tienHoan = 0;

if(!tien.isEmpty()){
    tienHoan = Double.parseDouble(tien);
}

dto.setTienHoan(tienHoan);
int soLuong = 0;

try{
    soLuong = Integer.parseInt(txtSoLuong.getText().trim());
}catch(Exception e){
    JOptionPane.showMessageDialog(this,"Số lượng không hợp lệ");
    return;
}

dto.setSoLuong(soLuong);
if(bus.taoYeuCau(dto)){
            JOptionPane.showMessageDialog(this,"Trả sách thành công");
            dispose();
        }else{
            JOptionPane.showMessageDialog(this,"Trả sách thất bại");
        }
    }
}
