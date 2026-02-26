package Nhanvien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class NhanvienGUI extends JPanel {

    private NhanvienBus bus;
    private NhanvienModel currentUser;

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtMa, txtTen, txtDiaChi, txtLuong, txtUser, txtNgaySinh;
    private JPasswordField txtPass;
    private JComboBox<String> cboChucVu, cboTrangThai;
    private JRadioButton rdoNam, rdoNu;

    private JButton btnThem, btnSua, btnXoa, btnTim;

    public NhanvienGUI(NhanvienModel user) {
        this.currentUser = user;
        bus = new NhanvienBus();
        setLayout(new BorderLayout(10,10));

        add(createFormPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);

        loadTable();
        phanQuyen();
    }

    private JPanel createFormPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMa = new JTextField(15);
        txtTen = new JTextField(15);
        txtDiaChi = new JTextField(15);
        txtLuong = new JTextField(15);
        txtUser = new JTextField(15);
        txtPass = new JPasswordField(15);
        txtNgaySinh = new JTextField(15);

        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rdoNam);
        bg.add(rdoNu);

//        cboChucVu = new JComboBox<>(new String[]{"Admin", "Nhân viên"});
//        cboTrangThai = new JComboBox<>(new String[]{"Đang làm", "Nghỉ việc"});
        cboChucVu = new JComboBox<>(new String[]{
                "Tất cả", "Admin", "Nhân viên"
        });
        cboTrangThai = new JComboBox<>(new String[]{
                "Tất cả", "Đang làm", "Đã nghỉ việc"
        });
        //
        gbc.gridx=0; gbc.gridy=0;
        panel.add(new JLabel("Mã NV:"), gbc);
        gbc.gridx=1;
        panel.add(txtMa, gbc);

        gbc.gridx=2;
        panel.add(new JLabel("Tên NV:"), gbc);
        gbc.gridx=3;
        panel.add(txtTen, gbc);
        //
        gbc.gridx=0; gbc.gridy=1;
        panel.add(new JLabel("Giới tính:"), gbc);
        gbc.gridx=1;
        panel.add(rdoNam, gbc);
        gbc.gridx=2;
        panel.add(rdoNu, gbc);
        //
        gbc.gridx=0; gbc.gridy=2;
        panel.add(new JLabel("Ngày sinh (yyyy-MM-dd):"), gbc);
        gbc.gridx=1;
        panel.add(txtNgaySinh, gbc);

        gbc.gridx=2;
        panel.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx=3;
        panel.add(txtDiaChi, gbc);
        //
        gbc.gridx=0; gbc.gridy=3;
        panel.add(new JLabel("Chức vụ:"), gbc);
        gbc.gridx=1;
        panel.add(cboChucVu, gbc);

        gbc.gridx=2;
        panel.add(new JLabel("Lương:"), gbc);
        gbc.gridx=3;
        panel.add(txtLuong, gbc);
        //
        gbc.gridx=0; gbc.gridy=4;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx=1;
        panel.add(txtUser, gbc);

        gbc.gridx=2;
        panel.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx=3;
        panel.add(txtPass, gbc);
        //
        gbc.gridx=0; gbc.gridy=5;
        panel.add(new JLabel("Trạng thái:"), gbc);
        gbc.gridx=1;
        panel.add(cboTrangThai, gbc);

        return panel;
    }

    private JPanel createTablePanel() {

        JPanel panel = new JPanel(new BorderLayout());

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Mã", "Tên", "Giới tính", "Ngày sinh",
                "Địa chỉ", "Chức vụ", "Lương",
                "Username", "Trạng thái"
        });

        table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(createButtonPanel(), BorderLayout.SOUTH);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                txtMa.setText(model.getValueAt(row,0).toString());
                txtTen.setText(model.getValueAt(row,1).toString());

                if(model.getValueAt(row,2).toString().equals("Nam"))
                    rdoNam.setSelected(true);
                else
                    rdoNu.setSelected(true);

                txtNgaySinh.setText(model.getValueAt(row,3).toString());
                txtDiaChi.setText(model.getValueAt(row,4).toString());
                cboChucVu.setSelectedItem(model.getValueAt(row,5).toString());
                txtLuong.setText(model.getValueAt(row,6).toString());
                txtUser.setText(model.getValueAt(row,7).toString());
                cboTrangThai.setSelectedItem(model.getValueAt(row,8).toString());
            }
        });

        return panel;
    }

    private JPanel createButtonPanel() {

        JPanel panel = new JPanel();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTim = new JButton("Tìm kiếm");

        panel.add(btnThem);
        panel.add(btnSua);
        panel.add(btnXoa);
        panel.add(btnTim);

        btnThem.addActionListener(e -> {
            NhanvienModel nv = getFormData();
            if(bus.add(nv)){
                JOptionPane.showMessageDialog(this,"Thêm thành công");
                refreshTable();
            }
        });

        btnSua.addActionListener(e -> {
            NhanvienModel nv = getFormData();
            if(bus.update(nv)){
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
                refreshTable();
            }
        });

        btnXoa.addActionListener(e -> {
            if(bus.deleteNhanVien(txtMa.getText())){
                JOptionPane.showMessageDialog(this,"Xóa thành công");
                refreshTable();
            }
        });

        btnTim.addActionListener(e -> timKiem());

        return panel;
    }

    private NhanvienModel getFormData() {

        NhanvienModel nv = new NhanvienModel();

        nv.setMaNV(txtMa.getText().trim());
        nv.setTenNV(txtTen.getText().trim());
        nv.setGioiTinh(rdoNam.isSelected() ? "Nam" : "Nữ");
        nv.setNgaySinh(LocalDate.parse(txtNgaySinh.getText().trim()));
        nv.setDiaChi(txtDiaChi.getText().trim());
        nv.setChucVu(cboChucVu.getSelectedItem().toString());
        nv.setLuong(Double.parseDouble(txtLuong.getText().trim()));
        nv.setTenDangNhap(txtUser.getText().trim());
        nv.setMatKhau(new String(txtPass.getPassword()));

        nv.setTrang_thai(
                cboTrangThai.getSelectedItem().toString().equals("Đang làm") ? 1 : 0
        );

        return nv;
    }

    private void loadTable(){
        model.setRowCount(0);
        List<NhanvienModel> list = bus.getAll();
        for(NhanvienModel nv : list){
            model.addRow(new Object[]{
                    nv.getMaNV(),
                    nv.getTenNV(),
                    nv.getGioiTinh(),
                    nv.getNgaySinh(),
                    nv.getDiaChi(),
                    nv.getChucVu(),
                    nv.getLuong(),
                    nv.getTenDangNhap(),
                    nv.getTrang_thai()==1?"Đang làm":"Đã nghỉ việc"
            });
        }
    }
    private void refreshTable(){
        bus.refresh();
        loadTable();
    }
    private void timKiem(){
        String ten = txtTen.getText().trim();
        //chucvu
        String chucVu = null;
        if(!cboChucVu.getSelectedItem().toString().equals("Tất cả")){
            chucVu = cboChucVu.getSelectedItem().toString();
        }
        //gioitinh
        String gioiTinh = null;
        if(rdoNam.isSelected()) gioiTinh = "Nam";
        else if(rdoNu.isSelected()) gioiTinh = "Nữ";
        // TRẠNG THÁI
        Integer trangThai = null;
        if(!cboTrangThai.getSelectedItem().toString().equals("Tất cả")){
            trangThai = cboTrangThai.getSelectedItem().toString().equals("Đang làm") ? 1 : 0;
        }
        // diachi
        String diaChi = txtDiaChi.getText().trim();
        if(diaChi.isEmpty()) diaChi = null;
        //luong
        Double luong = null;
        if(!txtLuong.getText().trim().isEmpty()){
            luong = Double.parseDouble(txtLuong.getText().trim());
        }
//        String ten = txtTen.getText().trim();
//        String chucVu = null;
//        if(!cboChucVu.getSelectedItem().toString().equals("Tất cả")){
//            chucVu = cboChucVu.getSelectedItem().toString();
//        }
//        String gioiTinh = rdoNam.isSelected() ? "Nam" : "Nữ";
//        Integer trangThai = null;
//        if(!cboTrangThai.getSelectedItem().toString().equals("Tất cả")){
//            trangThai = cboTrangThai.getSelectedItem().toString().equals("Đang làm") ? 1 : 0;
//        }
//        Integer trangThai = null;
//        if (cboTrangThai.getSelectedIndex() != -1){
//            trangThai = cboTrangThai.getSelectedItem().toString().equals("Đang làm") ? 1 : 0;
//        }
        List<NhanvienModel> list = bus.search(ten, chucVu, gioiTinh, trangThai, diaChi, luong);
        model.setRowCount(0);
        for (NhanvienModel n : list){
            model.addRow(new Object[]{
                    n.getMaNV(),
                    n.getTenNV(),
                    n.getGioiTinh(),
                    n.getNgaySinh(),
                    n.getDiaChi(),
                    n.getChucVu(),
                    n.getLuong(),
                    n.getTenDangNhap(),
                    n.getTrang_thai() == 1 ? "Đang làm" : "Đã nghỉ việc"
            });
        }
    }

    private void phanQuyen(){
        boolean isAdmin = currentUser.getChucVu().equalsIgnoreCase("Admin");
        btnXoa.setVisible(isAdmin);
    }
}