package Gui;
import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.awt.event.*;
import java.util.ArrayList;

import Bus.TaiKhoanBus;
    import Dto.TaiKhoanDTO;
public class TaiKhoanGui extends JPanel{
    
    
    
        TaiKhoanBus bus = new TaiKhoanBus();
        DefaultTableModel model = new DefaultTableModel();
        JTable table;
    
        JTextField txtMaTK, txtMaNV, txtUser, txtPass, txtEmail;
        JComboBox<String> cbQuyen, cbTrangThai;
        JTextField txtSearch;
        
        public TaiKhoanGui() {
            setLayout(new BorderLayout());
            
    
            // ===== FORM =====
            JPanel p = new JPanel(new GridLayout(4,4,5,5));
    
            txtMaTK = new JTextField();
            txtMaNV = new JTextField();
            txtUser = new JTextField();
            txtPass = new JTextField();
            txtEmail = new JTextField();
    
            cbQuyen = new JComboBox<>(new String[]{"Quản lý","Nhân viên"});
            cbTrangThai = new JComboBox<>(new String[]{"Hoạt động","Khóa"});
    
            p.add(new JLabel("Mã TK")); p.add(txtMaTK);
            p.add(new JLabel("Mã NV")); p.add(txtMaNV);
            p.add(new JLabel("User")); p.add(txtUser);
            p.add(new JLabel("Pass")); p.add(txtPass);
            p.add(new JLabel("Quyền")); p.add(cbQuyen);
            p.add(new JLabel("Trạng thái")); p.add(cbTrangThai);
            p.add(new JLabel("Email")); p.add(txtEmail);
    // ===== SEARCH PANEL =====
JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

txtSearch = new JTextField(25);
JButton btnSearch = new JButton("Tìm");

searchPanel.add(new JLabel("Tìm kiếm: "));

searchPanel.add(txtSearch);
searchPanel.add(btnSearch);

// Gom nhóm Form nhập liệu và Search Panel vào phía Bắc
JPanel northPanel = new JPanel(new BorderLayout());
northPanel.add(p, BorderLayout.NORTH); // p là panel chứa các JTextField nhập liệu cũ
northPanel.add(searchPanel, BorderLayout.SOUTH);

add(northPanel, BorderLayout.NORTH);
            // ===== TABLE =====
            model.setColumnIdentifiers(new String[]{
                "Mã TK","Mã NV","User","Pass","Quyền","Trạng thái","Email"
            });
            table = new JTable(model);
            JScrollPane sp = new JScrollPane(table);
    
            // ===== BUTTON =====
            JButton btnThem = new JButton("Thêm");
            JButton btnSua = new JButton("Sửa");
            JButton btnXoa = new JButton("Xóa");
    
            JPanel btnPanel = new JPanel();
            btnPanel.add(btnThem);
            btnPanel.add(btnSua);
            btnPanel.add(btnXoa);
    
            JPanel mainNorth = new JPanel(new BorderLayout());
            mainNorth.add(p, BorderLayout.NORTH);       // Form nhập liệu ở trên
            mainNorth.add(searchPanel, BorderLayout.SOUTH); // Thanh tìm kiếm ở dưới form
            
            add(mainNorth, BorderLayout.NORTH);  // Add cả cụm vào phía Bắc
            add(sp, BorderLayout.CENTER);         // Bảng dữ liệu ở giữa
            add(btnPanel, BorderLayout.SOUTH);
    
            loadTable();
    
            // ===== EVENTS =====
    
            btnThem.addActionListener(e -> {
                TaiKhoanDTO tk = getForm();
                if (bus.add(tk)) {
                    loadTable(); // Cập nhật lại bảng ngay lập tức
                    clearForm(); // Xóa trắng ô nhập liệu (nếu cần)
                    JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại! Vui lòng kiểm tra lại mã hoặc kết nối.");
                }
            });
    btnSearch.addActionListener(e -> {
    String value = txtSearch.getText().trim();
    
    if (value.isEmpty()) {
        loadTable(); // Nếu trống thì hiện tất cả
        return;
    }
    
    // Gọi hàm tìm kiếm từ Bus
    ArrayList<TaiKhoanDTO> result = bus.search(value);
    
    // Đổ dữ liệu tìm được vào table
    model.setRowCount(0);
    for (TaiKhoanDTO tk : result) {
        model.addRow(new Object[]{
            tk.getMaTK(), tk.getMaNV(), tk.getTenDangNhap(),
            tk.getMatKhau(), tk.getQuyen(), tk.getTrangThai(), tk.getEmail()
        });
    }
});
            btnSua.addActionListener(e -> {
                TaiKhoanDTO tk = getForm();
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (bus.update(tk)) {
                        loadTable(); //
                        JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại!");
                    }
                }
            });
    
            btnXoa.addActionListener(e -> {
                String maTK = txtMaTK.getText();
    if (maTK.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xóa từ bảng!");
        return;
    }
    
    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xóa tài khoản " + maTK + "?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        if (bus.delete(maTK)) {
            loadTable(); //
            clearForm();
            JOptionPane.showMessageDialog(this, "Đã xóa tài khoản thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }
    }
            });
    
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int i = table.getSelectedRow();
                    txtMaTK.setText(model.getValueAt(i,0).toString());
                    txtMaNV.setText(model.getValueAt(i,1).toString());
                    txtUser.setText(model.getValueAt(i,2).toString());
                    txtPass.setText(model.getValueAt(i,3).toString());
                    cbQuyen.setSelectedItem(model.getValueAt(i,4).toString());
                    cbTrangThai.setSelectedItem(model.getValueAt(i,5).toString());
                    txtEmail.setText(model.getValueAt(i,6).toString());
                }
            });
        }
    
        TaiKhoanDTO getForm() {
            return new TaiKhoanDTO(
                txtMaTK.getText(),
                txtMaNV.getText(),
                txtUser.getText(),
                txtPass.getText(),
                cbQuyen.getSelectedItem().toString(),
                cbTrangThai.getSelectedItem().toString(),
                txtEmail.getText()
            );
        }
    
        void loadTable() {
            model.setRowCount(0);
            for (TaiKhoanDTO tk : bus.getList()) {
                model.addRow(new Object[]{
                    tk.getMaTK(),
                    tk.getMaNV(),
                    tk.getTenDangNhap(),
                    tk.getMatKhau(),
                    tk.getQuyen(),
                    tk.getTrangThai(),
                    tk.getEmail()
                });
            }
        }
        void clearForm() {
            txtMaTK.setText("");
            txtMaNV.setText("");
            txtUser.setText("");
            txtPass.setText("");
            txtEmail.setText("");
            cbQuyen.setSelectedIndex(0);
            cbTrangThai.setSelectedIndex(0);
        }
    
        
    }




