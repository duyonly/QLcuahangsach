package Gui;

import Bus.NhaCungCapBus;
import Dto.NhaCungCapDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.util.List;

public class FormNhaCungCap extends JFrame {
    private NhaCungCapBus bus = new NhaCungCapBus();
    private JTable table;
    private DefaultTableModel model;

    public FormNhaCungCap(){
        setTitle("Quản Lý Nhà Cung Cấp");
        setSize(800,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm");
        JButton btnRefresh = new JButton("Làm mới");
        JButton btnExport = new JButton("Xuất CSV");
        top.add(new JLabel("Tìm kiếm:")); top.add(txtSearch); top.add(btnSearch); top.add(btnRefresh); top.add(btnExport);
        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"Mã","Tên","Địa Chỉ","SĐT","Email"},0){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        table = new JTable(model);
        loadData();

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        JPanel controls = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnView = new JButton("Xem chi tiết");
        controls.add(btnAdd);
        controls.add(btnEdit);
        controls.add(btnDelete);
        controls.add(btnView);
        add(controls, BorderLayout.SOUTH);

        btnSearch.addActionListener(e -> {
            String q = txtSearch.getText().trim();
            if(q.isEmpty()) loadData(); else search(q);
        });
        btnRefresh.addActionListener(e -> { txtSearch.setText(""); loadData(); });
        btnExport.addActionListener(e -> exportCsv());

        btnAdd.addActionListener(e -> openEditDialog(null));
        btnEdit.addActionListener(e -> {
            int idx = table.getSelectedRow();
            if(idx==-1){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để sửa");
                return;
            }
            int ma = Integer.parseInt(model.getValueAt(idx,0).toString());
            String ten = model.getValueAt(idx,1).toString();
            String diachi = model.getValueAt(idx,2).toString();
            String sdt = model.getValueAt(idx,3).toString();
            String email = model.getValueAt(idx,4).toString();
            NhaCungCapDTO n = new NhaCungCapDTO(ma,ten,diachi,sdt,email);
            openEditDialog(n);
        });

        btnView.addActionListener(e -> {
            int idx = table.getSelectedRow();
            if(idx==-1){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để xem chi tiết");
                return;
            }
            int ma = Integer.parseInt(model.getValueAt(idx,0).toString());
            NhaCungCapDTO n = bus.layChiTiet(ma);
            if(n==null){
                JOptionPane.showMessageDialog(this, "Không tìm thấy chi tiết nhà cung cấp");
                return;
            }
            ChiTietNhaCungCapDialog dlg = new ChiTietNhaCungCapDialog(this, n);
            dlg.setVisible(true);
        });
        btnDelete.addActionListener(e -> {
            int idx = table.getSelectedRow();
            if(idx==-1){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để xóa");
                return;
            }
            int ma = Integer.parseInt(model.getValueAt(idx,0).toString());
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?","Xác nhận",JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION){
                boolean ok = bus.xoa(ma);
                if(ok){
                    loadData();
                    JOptionPane.showMessageDialog(this,"Xóa thành công");
                }else{
                    JOptionPane.showMessageDialog(this,"Xóa thất bại");
                }
            }
        });

        setVisible(true);
    }

    private void loadData(){
        model.setRowCount(0);
        List<NhaCungCapDTO> list = bus.layTatCa();
        for(NhaCungCapDTO n: list){
            model.addRow(new Object[]{n.getMaNCC(),n.getTenNCC(),n.getDiaChi(),n.getSDT(),n.getEmail()});
        }
    }

    private void search(String q){
        model.setRowCount(0);
        List<NhaCungCapDTO> list = bus.timKiem(q);
        for(NhaCungCapDTO n: list){
            model.addRow(new Object[]{n.getMaNCC(),n.getTenNCC(),n.getDiaChi(),n.getSDT(),n.getEmail()});
        }
    }

    private void exportCsv(){
        try{
            JFileChooser chooser = new JFileChooser();
            if(chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
            String path = chooser.getSelectedFile().getAbsolutePath();
            if(!path.toLowerCase().endsWith(".csv")) path += ".csv";
            try(FileWriter fw = new FileWriter(path)){
                fw.write("MaNCC,TenNCC,DiaChi,SDT,Email\n");
                for(int i=0;i<model.getRowCount();i++){
                    fw.write(model.getValueAt(i,0)+","+model.getValueAt(i,1)+","+model.getValueAt(i,2)+","+model.getValueAt(i,3)+","+model.getValueAt(i,4)+"\n");
                }
            }
            JOptionPane.showMessageDialog(this,"Xuất CSV thành công");
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi khi xuất CSV");
        }
    }

    public void openEditDialog(NhaCungCapDTO n){
        JDialog dlg = new JDialog(this,"Thông tin Nhà Cung Cấp",true);
        dlg.setSize(400,300);
        dlg.setLayout(null);
        dlg.setLocationRelativeTo(this);

        JLabel lblTen = new JLabel("Tên:"); lblTen.setBounds(20,20,100,25); dlg.add(lblTen);
        JTextField txtTen = new JTextField(); txtTen.setBounds(120,20,230,25); dlg.add(txtTen);
        JLabel lblDiaChi = new JLabel("Địa chỉ:"); lblDiaChi.setBounds(20,60,100,25); dlg.add(lblDiaChi);
        JTextField txtDiaChi = new JTextField(); txtDiaChi.setBounds(120,60,230,25); dlg.add(txtDiaChi);
        JLabel lblSDT = new JLabel("SĐT:"); lblSDT.setBounds(20,100,100,25); dlg.add(lblSDT);
        JTextField txtSDT = new JTextField(); txtSDT.setBounds(120,100,230,25); dlg.add(txtSDT);
        JLabel lblEmail = new JLabel("Email:"); lblEmail.setBounds(20,140,100,25); dlg.add(lblEmail);
        JTextField txtEmail = new JTextField(); txtEmail.setBounds(120,140,230,25); dlg.add(txtEmail);

        JButton btnSave = new JButton("Lưu"); btnSave.setBounds(120,200,90,30); dlg.add(btnSave);
        JButton btnCancel = new JButton("Hủy"); btnCancel.setBounds(260,200,90,30); dlg.add(btnCancel);

        if(n!=null){
            txtTen.setText(n.getTenNCC());
            txtDiaChi.setText(n.getDiaChi());
            txtSDT.setText(n.getSDT());
            txtEmail.setText(n.getEmail());
        }

        btnSave.addActionListener(e -> {
            String ten = txtTen.getText().trim();
            String diachi = txtDiaChi.getText().trim();
            String sdt = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            if(ten.isEmpty()){
                JOptionPane.showMessageDialog(dlg,"Tên không được để trống");
                return;
            }
            if(n==null){
                NhaCungCapDTO nn = new NhaCungCapDTO(0,ten,diachi,sdt,email);
                boolean ok = bus.them(nn);
                if(ok){ loadData(); dlg.dispose(); JOptionPane.showMessageDialog(this,"Thêm thành công"); }
                else JOptionPane.showMessageDialog(this,"Thêm thất bại");
            }else{
                n.setTenNCC(ten); n.setDiaChi(diachi); n.setSDT(sdt); n.setEmail(email);
                boolean ok = bus.capNhat(n);
                if(ok){ loadData(); dlg.dispose(); JOptionPane.showMessageDialog(this,"Cập nhật thành công"); }
                else JOptionPane.showMessageDialog(this,"Cập nhật thất bại");
            }
        });
        btnCancel.addActionListener(e -> dlg.dispose());

        dlg.setVisible(true);
    }
}

