package Gui;

import Bus.PhieuXuatBUS;
import Dto.PhieuXuatDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

public class PhieuXuatGUI extends JPanel {

    JTextField txtMa, txtTrangThai, txtNguoiTao, txtMaKH;
    JTable table;
    DefaultTableModel model;
    PhieuXuatBUS bus = new PhieuXuatBUS();

    public PhieuXuatGUI() {
        // QUAN TRỌNG: Sét Layout cho JPanel chính
        this.setLayout(new BorderLayout(10, 10));

        // ===== PANEL NHẬP LIỆU (BÊN TRÁI) =====
        JPanel panelLeft = new JPanel(new GridLayout(5, 2, 5, 5));
        panelLeft.setBorder(BorderFactory.createTitledBorder("Thông tin phiếu xuất"));
        
        panelLeft.add(new JLabel("Mã Phiếu Xuất:"));
        txtMa = new JTextField();
        panelLeft.add(txtMa);

        panelLeft.add(new JLabel("Trạng Thái:"));
        txtTrangThai = new JTextField();
        panelLeft.add(txtTrangThai);

        panelLeft.add(new JLabel("Người Tạo:"));
        txtNguoiTao = new JTextField();
        panelLeft.add(txtNguoiTao);

        panelLeft.add(new JLabel("Mã Khách Hàng:"));
        txtMaKH = new JTextField();
        panelLeft.add(txtMaKH);
        
        // Bọc panelLeft vào một panel khác để tránh nó bị kéo giãn quá cao
        JPanel wrapLeft = new JPanel(new BorderLayout());
        wrapLeft.add(panelLeft, BorderLayout.NORTH);
        this.add(wrapLeft, BorderLayout.WEST);

        // ===== BẢNG DỮ LIỆU (Ở GIỮA) =====
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Mã PX", "Thời Gian", "Trạng Thái", "Người Tạo", "Mã KH"
        });
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        this.add(sp, BorderLayout.CENTER);

        // ===== PANEL NÚT BẤM (PHÍA DƯỚI) =====
        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JButton btnLoad = new JButton("Làm mới");
        JButton btnChiTiet = new JButton("Xem Chi Tiết");

        JPanel pBtn = new JPanel();
        pBtn.add(btnThem);
        pBtn.add(btnXoa);
        pBtn.add(btnLoad);
        pBtn.add(btnChiTiet);
        this.add(pBtn, BorderLayout.SOUTH);

        // ===== XỬ LÝ SỰ KIỆN =====
        btnLoad.addActionListener(e -> loadData());

        btnThem.addActionListener(e -> {
            PhieuXuatDTO px = new PhieuXuatDTO();
            px.setMaphieuxuat(txtMa.getText());
            px.setTrangthai(txtTrangThai.getText());
            px.setNguoitaophieuxuat(txtNguoiTao.getText());
            px.setMakhachhang(txtMaKH.getText());
            px.setThoigian(new Date());

            if(bus.add(px)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }
        });

        btnXoa.addActionListener(e -> {
            String ma = txtMa.getText();
            if(ma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã để xóa");
                return;
            }
            bus.delete(ma);
            loadData();
        });

        btnChiTiet.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Hãy chọn một phiếu xuất trong bảng!");
                return;
            }
            String maPX = table.getValueAt(row, 0).toString();
            // Đảm bảo class ChiTietPhieuXuatGUI là một JDialog hoặc JFrame
            new ChiTietPhieuXuatGUI(maPX).setVisible(true);
        });
        
        // Load dữ liệu lần đầu khi mở tab
        loadData();
    }

    void loadData() {
        model.setRowCount(0);
        for (PhieuXuatDTO px : bus.getAll()) {
            model.addRow(new Object[]{
                    px.getMaphieuxuat(),
                    px.getThoigian(),
                    px.getTrangthai(),
                    px.getNguoitaophieuxuat(),
                    px.getMakhachhang()
            });
        }
    }
}