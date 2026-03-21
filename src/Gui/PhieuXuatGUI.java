// package Gui;

// import Bus.PhieuXuatBUS;
// import Dto.PhieuXuatDTO;

// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.awt.event.*;
// import java.util.*;

// public class PhieuXuatGUI extends JFrame {

//     JTextField txtMa, txtTrangThai, txtNguoiTao, txtMaKH;
//     JTable table;
//     DefaultTableModel model;
//     PhieuXuatBUS bus = new PhieuXuatBUS();
    
//     public PhieuXuatGUI() {

//         setTitle("Quản Lý Phiếu Xuất");
//         setSize(900, 500);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(EXIT_ON_CLOSE);

//         // ===== FORM =====
//         JPanel panel = new JPanel(new GridLayout(5,2,5,5));

//         panel.add(new JLabel("Mã Phiếu Xuất"));
//         txtMa = new JTextField();
//         panelLeft.add(txtMa);

//         panelLeft.add(new JLabel("Trạng Thái:"));
//         txtTrangThai = new JTextField();
//         panelLeft.add(txtTrangThai);

//         panelLeft.add(new JLabel("Người Tạo:"));
//         txtNguoiTao = new JTextField();
//         panelLeft.add(txtNguoiTao);

//         panelLeft.add(new JLabel("Mã Khách Hàng:"));
//         txtMaKH = new JTextField();
//         panel.add(txtMaKH);

//         add(panel, BorderLayout.WEST);

//         // ===== TABLE =====
//         model = new DefaultTableModel();
//         model.setColumnIdentifiers(new String[]{
//                 "Mã PX", "Thời Gian", "Trạng Thái", "Người Tạo", "Mã KH"
//         });
//         table = new JTable(model);
//         add(new JScrollPane(table), BorderLayout.CENTER);

//         // ===== BUTTON =====
//         JButton btnThem = new JButton("Thêm");
//         JButton btnXoa = new JButton("Xóa");
//         JButton btnLoad = new JButton("Làm mới");
//         JButton btnChiTiet = new JButton("Xem Chi Tiết");

//         JPanel p = new JPanel();
//         p.add(btnThem);
//         p.add(btnXoa);
//         p.add(btnLoad);
//         p.add(btnChiTiet);

//         add(p, BorderLayout.SOUTH);

//         // ===== EVENT =====

//         // Load data
//         btnLoad.addActionListener(e -> loadData());

//         // Thêm
//         btnThem.addActionListener(e -> {
//             if(txtMa.getText().isEmpty()){
//                 JOptionPane.showMessageDialog(this,"Không được để trống mã!");
//                 return;
//             }

//             PhieuXuatDTO px = new PhieuXuatDTO();
//             px.setMaphieuxuat(txtMa.getText());
//             px.setTrangthai(txtTrangThai.getText());
//             px.setNguoitaophieuxuat(txtNguoiTao.getText());
//             px.setMakhachhang(txtMaKH.getText());
//             px.setThoigian(new Date());

//             if(bus.add(px)){
//                 JOptionPane.showMessageDialog(this,"Thêm thành công!");
//                 loadData();
//                 clearForm();
//             }else{
//                 JOptionPane.showMessageDialog(this,"Thêm thất bại!");
//             }
//         });

//         // Xóa (soft delete khuyên dùng)
//         btnXoa.addActionListener(e -> {
//             int row = table.getSelectedRow();

//             if(row == -1){
//                 JOptionPane.showMessageDialog(this,"Chọn dòng cần xóa!");
//                 return;
//             }

//             String ma = table.getValueAt(row,0).toString();

//             int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xóa?");
//             if(confirm == JOptionPane.YES_OPTION){

//                 if(bus.delete(ma)){
//                     JOptionPane.showMessageDialog(this,"Xóa thành công!");
//                     loadData();
//                 }else{
//                     JOptionPane.showMessageDialog(this,"Xóa thất bại!");
//                 }
//             }
//         });

//         // Click table → fill form
//         table.addMouseListener(new MouseAdapter() {
//             public void mouseClicked(MouseEvent e){
//                 int row = table.getSelectedRow();

//                 txtMa.setText(String.valueOf(model.getValueAt(row,0)));
//                 txtTrangThai.setText(String.valueOf(model.getValueAt(row,2)));
//                 txtNguoiTao.setText(String.valueOf(model.getValueAt(row,3)));
//                 txtMaKH.setText(String.valueOf(model.getValueAt(row,4)));
//             }
//         });

//         // Xem chi tiết
//         btnChiTiet.addActionListener(e -> {
//             int row = table.getSelectedRow();

//             if(row == -1){
//                 JOptionPane.showMessageDialog(this,"Hãy chọn phiếu xuất");
//                 return;
//             }

//             String maPX = table.getValueAt(row,0).toString();
//             new ChiTietPhieuXuatGUI(maPX);
//         });

//         // Load ngay khi mở
//         loadData();

//         setVisible(true);
//     }

//     void loadData(){
//         model.setRowCount(0);

//         for(PhieuXuatDTO px : bus.getAll()){
//             model.addRow(new Object[]{
//                     px.getMaphieuxuat(),
//                     px.getThoigian(),
//                     px.getTrangthai(),
//                     px.getNguoitaophieuxuat(),
//                     px.getMakhachhang()
//             });
//         }
//     }

//     void clearForm(){
//         txtMa.setText("");
//         txtTrangThai.setText("");
//         txtNguoiTao.setText("");
//         txtMaKH.setText("");
//     }
//     public static void main(String[] args) {
//     SwingUtilities.invokeLater(new Runnable() {
//         @Override
//         public void run() {
//             new PhieuXuatGUI();
//         }
//     });
// }
// }