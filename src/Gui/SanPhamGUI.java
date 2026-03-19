package Gui;

import Bus.SanPhamBUS;
import Dto.SanPhamDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SanPhamGUI extends JPanel {

    JTextField txtMa, txtTen, txtTacGia, txtTheLoai, txtNXB, txtNamXB, txtGiaNhap, txtGiaBan, txtSoLuongTon, txtMoTa, txtTrangThai, txtTim;
    JButton btnThem, btnSua, btnXoa, btnTim, btnLoad;
    JTable table;
    DefaultTableModel model;

    SanPhamBUS bus = new SanPhamBUS();

    public SanPhamGUI() {
        // Sử dụng BorderLayout để co giãn tốt
        setLayout(new BorderLayout(10, 10));

        // panel nhập dữ liệu
        JPanel panelInput = new JPanel(new GridLayout(11,2,10,10));

        panelInput.add(new JLabel("Mã sách"));
        txtMa = new JTextField();
        panelInput.add(txtMa);

        panelInput.add(new JLabel("Tên sách"));
        txtTen = new JTextField();
        panelInput.add(txtTen);

        panelInput.add(new JLabel("Tác giả"));
        txtTacGia = new JTextField();
        panelInput.add(txtTacGia);

        panelInput.add(new JLabel("Thể loại"));
        txtTheLoai = new JTextField();
        panelInput.add(txtTheLoai);

        panelInput.add(new JLabel("Nhà xuất bản"));
        txtNXB = new JTextField();
        panelInput.add(txtNXB);

        panelInput.add(new JLabel("Năm XB"));
        txtNamXB = new JTextField();
        panelInput.add(txtNamXB);

        panelInput.add(new JLabel("Giá nhập"));
        txtGiaNhap = new JTextField();
        panelInput.add(txtGiaNhap);

        panelInput.add(new JLabel("Giá bán"));
        txtGiaBan = new JTextField();
        panelInput.add(txtGiaBan);
        
        panelInput.add(new JLabel("Số lượng"));
        txtSoLuongTon = new JTextField();
        panelInput.add(txtSoLuongTon);
        
        panelInput.add(new JLabel("Mô tả"));
        txtMoTa = new JTextField();
        panelInput.add(txtMoTa);
        
        panelInput.add(new JLabel("Trạng thái"));
        txtTrangThai = new JTextField();
        panelInput.add(txtTrangThai);

        // Bọc vào một Panel khác để không bị giãn theo chiều dọc
        JPanel wrapInput = new JPanel(new BorderLayout());
        wrapInput.add(panelInput, BorderLayout.NORTH);
        add(wrapInput, BorderLayout.WEST);

        // ----- Bảng Dữ Liệu (Trung tâm) -----
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho sửa trực tiếp trên bảng
            }
        };
        model.setColumnIdentifiers(new String[]{
                "Mã","Tên","Tác giả","Thể loại","NXB","Năm","Giá nhập","Giá bán","Số lượng","Mô tả","Trạng thái"
        });

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ----- Panel Nút và Tìm Kiếm (Phía Nam) -----
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLoad = new JButton("Làm mới");

        txtTim = new JTextField(20);
        btnTim = new JButton("Tìm nhanh");

        panelBottom.add(btnThem);
        panelBottom.add(btnSua);
        panelBottom.add(btnXoa);
        panelBottom.add(btnLoad);
        panelBottom.add(new JLabel(" | "));
        panelBottom.add(txtTim);
        panelBottom.add(btnTim);

        add(panelBottom, BorderLayout.SOUTH);

        // ----- Sự kiện -----
        loadTable();
        
        // sự kiện
        btnThem.addActionListener(e -> them());
        btnSua.addActionListener(e -> sua());
        btnXoa.addActionListener(e -> xoa());
        btnLoad.addActionListener(e -> {
            
            loadTable();
        });
        btnTim.addActionListener(e -> tim());

        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int row = table.getSelectedRow();
                if(row >= 0){
                    txtMa.setText(String.valueOf(model.getValueAt(row,0)));
                    txtTen.setText(String.valueOf(model.getValueAt(row,1)));
                    txtTacGia.setText(String.valueOf(model.getValueAt(row,2)));
                    txtTheLoai.setText(String.valueOf(model.getValueAt(row,3)));
                    txtNXB.setText(String.valueOf(model.getValueAt(row,4)));
                    txtNamXB.setText(String.valueOf(model.getValueAt(row,5)));
                    txtGiaNhap.setText(String.valueOf(model.getValueAt(row,6)));
                    txtGiaBan.setText(String.valueOf(model.getValueAt(row,7)));
                    txtSoLuongTon.setText(String.valueOf(model.getValueAt(row,8)));
                    txtMoTa.setText(String.valueOf(model.getValueAt(row,9)));
                    txtTrangThai.setText(String.valueOf(model.getValueAt(row,10)));
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtMa.setText(model.getValueAt(row, 0).toString());
                    txtTen.setText(model.getValueAt(row, 1).toString());
                    txtTacGia.setText(model.getValueAt(row, 2).toString());
                    txtTheLoai.setText(model.getValueAt(row, 3).toString());
                    txtNXB.setText(model.getValueAt(row, 4).toString());
                    txtNamXB.setText(model.getValueAt(row, 5).toString());
                    txtGiaNhap.setText(model.getValueAt(row, 6).toString());
                    txtGiaBan.setText(model.getValueAt(row, 7).toString());
                }
            }
        });
    }

    void loadTable() {
        model.setRowCount(0);
        ArrayList<SanPhamDTO> list = bus.getAll();
        for (SanPhamDTO sp : list) {
            model.addRow(new Object[]{
                    sp.getMasp(),
                    sp.getTensp(),
                    sp.getTentg(),
                    sp.getMatheloai(),
                    sp.getNhaxuatban(),
                    sp.getNamxuatban(),
                    sp.getGianhap(),
                    sp.getGiaban(),
                    sp.getSoluongton(),
                    sp.getMota(),
                    sp.getTrangthai(),
            });
        }
    }
    void clearForm(){
        txtMa.setText("");
        txtTen.setText("");
        txtTacGia.setText("");
        txtTheLoai.setText("");
        txtNXB.setText("");
        txtNamXB.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtSoLuongTon.setText("");
        txtMoTa.setText("");
        txtTrangThai.setText("");
    }
    void them(){

            try{
                SanPhamDTO sp = new SanPhamDTO();

                sp.setMasp(txtMa.getText());
                sp.setTensp(txtTen.getText());
                sp.setTentg(txtTacGia.getText());
                sp.setMatheloai(txtTheLoai.getText());
                sp.setNhaxuatban(txtNXB.getText());
                sp.setNamxuatban(Integer.parseInt(txtNamXB.getText()));
                sp.setGianhap(Integer.parseInt(txtGiaNhap.getText()));
                sp.setGiaban(Integer.parseInt(txtGiaBan.getText()));
                sp.setSoluongton(Integer.parseInt(txtSoLuongTon.getText()));
                sp.setMota(txtMoTa.getText());
                sp.setTrangthai(txtTrangThai.getText());
               
                if(bus.findByID(txtMa.getText()) != null){
                    JOptionPane.showMessageDialog(this,"Mã sách đã tồn tại!");
                    return;
                }
                if(bus.add(sp)){
                    JOptionPane.showMessageDialog(this,"Thêm thành công!");
                    loadTable();
                    clearForm();
                }else{
                    JOptionPane.showMessageDialog(this,"Thêm thất bại!");
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(this,"Lỗi nhập dữ liệu!");
            }
    }

    void sua(){

            try{
            SanPhamDTO sp = new SanPhamDTO();

            sp.setMasp(txtMa.getText());
            sp.setTensp(txtTen.getText());
            sp.setTentg(txtTacGia.getText());
            sp.setMatheloai(txtTheLoai.getText());
            sp.setNhaxuatban(txtNXB.getText());
            sp.setNamxuatban(Integer.parseInt(txtNamXB.getText()));
            sp.setGianhap(Integer.parseInt(txtGiaNhap.getText()));
            sp.setGiaban(Integer.parseInt(txtGiaBan.getText()));
            sp.setSoluongton(Integer.parseInt(txtSoLuongTon.getText()));
            sp.setMota(txtMoTa.getText());
            sp.setTrangthai(txtTrangThai.getText());

            if(bus.update(sp)){
                JOptionPane.showMessageDialog(this,"Sửa thành công!");
                loadTable();
                clearForm();
            }else{
                JOptionPane.showMessageDialog(this,"Sửa thất bại!");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Lỗi dữ liệu!");
        }
    }

    void xoa(){

        int row = table.getSelectedRow();

        if(row < 0){
            JOptionPane.showMessageDialog(this,"Chọn sản phẩm!");
            return;
        }

        String ma = model.getValueAt(row,0).toString();

        int confirm = JOptionPane.showConfirmDialog(this,"Ngừng bán sản phẩm này?");
        if(confirm == JOptionPane.YES_OPTION){

            SanPhamDTO sp = bus.findByID(ma);
            sp.setTrangthai("Ngung ban");

            if(bus.update(sp)){
                JOptionPane.showMessageDialog(this,"Đã ngừng bán!");
                loadTable();
            }else{
                JOptionPane.showMessageDialog(this,"Thất bại!");
            }
        }
    }

    void tim() {
        model.setRowCount(0);

        ArrayList<SanPhamDTO> list = bus.searchByName(txtTim.getText());

        if(list.isEmpty()){
            JOptionPane.showMessageDialog(this,"Không tìm thấy!");
        }

        for(SanPhamDTO sp : list){
            model.addRow(new Object[]{
                    sp.getMasp(),
                    sp.getTensp(),
                    sp.getTentg(),
                    sp.getMatheloai(),
                    sp.getNhaxuatban(),
                    sp.getNamxuatban(),
                    sp.getGianhap(),
                    sp.getGiaban(),
                    sp.getSoluongton(),
                    sp.getMota(),
                    sp.getTrangthai()
            });
        }
    }
    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new SanPhamGUI();
        }
    });
}
}
