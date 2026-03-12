package Gui;

import BUS.SanPhamBUS;
import Dto.SanPhamDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SanPhamGUI extends JFrame {

    JTextField txtMa, txtTen, txtTacGia, txtTheLoai, txtNXB, txtNamXB, txtGiaNhap, txtGiaBan, txtTim;
    JButton btnThem, btnSua, btnXoa, btnTim, btnLoad;
    JTable table;
    DefaultTableModel model;

    SanPhamBUS bus = new SanPhamBUS();

    public SanPhamGUI(){

        setTitle("QUẢN LÝ SÁCH");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // panel nhập dữ liệu
        JPanel panelInput = new JPanel(new GridLayout(8,2,10,10));

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

        add(panelInput,BorderLayout.WEST);

        // bảng dữ liệu
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Mã","Tên","Tác giả","Thể loại","NXB","Năm","Giá nhập","Giá bán"
        });

        table = new JTable(model);
        add(new JScrollPane(table),BorderLayout.CENTER);

        // panel nút
        JPanel panelButton = new JPanel();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLoad = new JButton("Load");

        txtTim = new JTextField(15);
        btnTim = new JButton("Tìm");

        panelButton.add(btnThem);
        panelButton.add(btnSua);
        panelButton.add(btnXoa);
        panelButton.add(btnLoad);
        panelButton.add(txtTim);
        panelButton.add(btnTim);

        add(panelButton,BorderLayout.SOUTH);

        // load dữ liệu
        loadTable();

        // sự kiện
        btnThem.addActionListener(e -> them());
        btnSua.addActionListener(e -> sua());
        btnXoa.addActionListener(e -> xoa());
        btnLoad.addActionListener(e -> loadTable());
        btnTim.addActionListener(e -> tim());

        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int row = table.getSelectedRow();

                txtMa.setText(model.getValueAt(row,0).toString());
                txtTen.setText(model.getValueAt(row,1).toString());
                txtTacGia.setText(model.getValueAt(row,2).toString());
                txtTheLoai.setText(model.getValueAt(row,3).toString());
                txtNXB.setText(model.getValueAt(row,4).toString());
                txtNamXB.setText(model.getValueAt(row,5).toString());
                txtGiaNhap.setText(model.getValueAt(row,6).toString());
                txtGiaBan.setText(model.getValueAt(row,7).toString());
            }
        });

        setVisible(true);
    }

    void loadTable(){
        model.setRowCount(0);

        ArrayList<SanPhamDTO> list = bus.getAll();

        for(SanPhamDTO sp : list){
            model.addRow(new Object[]{
                    sp.getMasp(),
                    sp.getTensp(),
                    sp.getTentg(),
                    sp.getMatheloai(),
                    sp.getNhaxuatban(),
                    sp.getNamxuatban(),
                    sp.getGianhap(),
                    sp.getGiaban()
            });
        }
    }

    void them(){

        SanPhamDTO sp = new SanPhamDTO();

        sp.setMasp(txtMa.getText());
        sp.setTensp(txtTen.getText());
        sp.setTentg(txtTacGia.getText());
        sp.setMatheloai(txtTheLoai.getText());
        sp.setNhaxuatban(txtNXB.getText());
        sp.setNamxuatban(Integer.parseInt(txtNamXB.getText()));
        sp.setGianhap(Integer.parseInt(txtGiaNhap.getText()));
        sp.setGiaban(Integer.parseInt(txtGiaBan.getText()));

        bus.add(sp);

        loadTable();
    }

    void sua(){

        SanPhamDTO sp = new SanPhamDTO();

        sp.setMasp(txtMa.getText());
        sp.setTensp(txtTen.getText());
        sp.setTentg(txtTacGia.getText());
        sp.setMatheloai(txtTheLoai.getText());
        sp.setNhaxuatban(txtNXB.getText());
        sp.setNamxuatban(Integer.parseInt(txtNamXB.getText()));
        sp.setGianhap(Integer.parseInt(txtGiaNhap.getText()));
        sp.setGiaban(Integer.parseInt(txtGiaBan.getText()));

        bus.update(sp);

        loadTable();
    }

    void xoa(){

        bus.delete(txtMa.getText());

        loadTable();
    }

    void tim(){

        model.setRowCount(0);

        ArrayList<SanPhamDTO> list = bus.searchByName(txtTim.getText());

        for(SanPhamDTO sp : list){
            model.addRow(new Object[]{
                    sp.getMasp(),
                    sp.getTensp(),
                    sp.getTentg(),
                    sp.getMatheloai(),
                    sp.getNhaxuatban(),
                    sp.getNamxuatban(),
                    sp.getGianhap(),
                    sp.getGiaban()
            });
        }
    }

}