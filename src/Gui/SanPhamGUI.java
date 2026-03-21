package Gui;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.TableRowSorter;

public class SanPhamGUI extends JFrame {
    // Component khai báo giữ nguyên
    JTextField txtMa, txtTen, txtTacGia, txtTheLoai, txtNXB, txtNamXB, txtGiaNhap, txtGiaBan, txtSoLuongTon, txtMoTa, txtTrangThai, txtTim;
    JButton btnThem, btnSua, btnXoa, btnTim, btnLoad;
    JTable table;
    DefaultTableModel model;
    SanPhamBUS bus = new SanPhamBUS();

    // Màu sắc chủ đạo theo hình
    Color mainColor = new Color(50, 205, 50); // Xanh lá
    Color sidebarColor = new Color(222, 184, 135); // Màu gỗ/tan
    Color buttonColor = new Color(173, 216, 230); // Xanh dương nhạt cho nút sidebar

    public SanPhamGUI() {
        setTitle("HỆ THỐNG QUẢN LÝ CỬA HÀNG SÁCH");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- 1. HEADER (Xanh lá) ---
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(mainColor);
        panelHeader.setPreferredSize(new Dimension(0, 60));
        JLabel lblHeader = new JLabel("TRANG CHỦ - QUẢN LÝ CỬA HÀNG SÁCH");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        lblHeader.setForeground(Color.BLACK);
        panelHeader.add(lblHeader);
        add(panelHeader, BorderLayout.NORTH);


        
        // --- 3. CENTER PANEL (Chứa Form và Table) ---
        JPanel panelCenter = new JPanel(new BorderLayout());
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // -- Form nhập liệu (Phía trên) --
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Thông tin sách"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Cột 1
        addComponent(panelForm, new JLabel("Mã sách:"), 0, 0, gbc);
        txtMa = new JTextField(15); addComponent(panelForm, txtMa, 1, 0, gbc);
        addComponent(panelForm, new JLabel("Tên sách:"), 0, 1, gbc);
        txtTen = new JTextField(15); addComponent(panelForm, txtTen, 1, 1, gbc);
        addComponent(panelForm, new JLabel("Tác giả:"), 0, 2, gbc);
        txtTacGia = new JTextField(15); addComponent(panelForm, txtTacGia, 1, 2, gbc);

        // Cột 2
        addComponent(panelForm, new JLabel("Giá nhập:"), 2, 0, gbc);
        txtGiaNhap = new JTextField(15); addComponent(panelForm, txtGiaNhap, 3, 0, gbc);
        addComponent(panelForm, new JLabel("Giá bán:"), 2, 1, gbc);
        txtGiaBan = new JTextField(15); addComponent(panelForm, txtGiaBan, 3, 1, gbc);
        addComponent(panelForm, new JLabel("Số lượng:"), 2, 2, gbc);
        txtSoLuongTon = new JTextField(15); addComponent(panelForm, txtSoLuongTon, 3, 2, gbc);

        // Thanh tìm kiếm ngay dưới form
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.CENTER));
        txtTim = new JTextField(30);
        btnTim = new JButton("Tìm");
        panelSearch.add(new JLabel("Tìm kiếm: "));
        panelSearch.add(txtTim);
        panelSearch.add(btnTim);

        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.add(panelForm, BorderLayout.NORTH);
        panelTop.add(panelSearch, BorderLayout.CENTER);
        panelCenter.add(panelTop, BorderLayout.NORTH);

        // -- Table (Phía dưới) --
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã", "Tên", "Tác giả", "Thể loại", "NXB", "Năm", "Giá nhập", "Giá bán", "Số lượng", "Mô tả", "Trạng thái"});
        table = new JTable(model);
        table.setRowHeight(25);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));
        
        panelCenter.add(new JScrollPane(table), BorderLayout.CENTER);

        // -- Nút chức năng (Dưới cùng Center) --
        JPanel panelAction = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLoad = new JButton("Làm mới");
        panelAction.add(btnThem);
        panelAction.add(btnSua);
        panelAction.add(btnXoa);
        panelAction.add(btnLoad);
        panelCenter.add(panelAction, BorderLayout.SOUTH);

        add(panelCenter, BorderLayout.CENTER);

        // Sự kiện (Giữ nguyên logic của bạn)
        btnThem.addActionListener(e -> them());
        btnSua.addActionListener(e -> sua());
        btnXoa.addActionListener(e -> xoa());
        btnLoad.addActionListener(e -> loadTable());
        btnTim.addActionListener(e -> tim());

        loadTable();
        setVisible(true);
    }

    private void addComponent(JPanel p, Component c, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        p.add(c, gbc);
    }

    // --- Giữ nguyên các hàm loadTable, them, sua, xoa, tim của bạn bên dưới ---
    void loadTable() {
        model.setRowCount(0);
        ArrayList<SanPhamDTO> list = bus.getAll();
        for(SanPhamDTO sp : list){
            model.addRow(new Object[]{ sp.getMasp(), sp.getTensp(), sp.getTentg(), sp.getMatheloai(), sp.getNhaxuatban(), sp.getNamxuatban(), sp.getGianhap(), sp.getGiaban(), sp.getSoluongton(), sp.getMota(), sp.getTrangthai()});
        }
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



        if (row < 0) {

            JOptionPane.showMessageDialog(this, "Chọn sản phẩm!");

            return;

        }



        String ma = model.getValueAt(row, 0).toString();



        int confirm = JOptionPane.showConfirmDialog(this, "Ngừng bán sản phẩm này?");
    }
    void tim(){
        String keyword = txtTim.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if(keyword.isEmpty()){
            sorter.setRowFilter(null); // hiện lại tất cả
        }else{
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        }

    }
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        SwingUtilities.invokeLater(() -> new SanPhamGUI());
    }
}