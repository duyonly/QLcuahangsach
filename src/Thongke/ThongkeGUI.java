package Thongke;

import Thongke.Model.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ThongkeGUI extends JPanel{
    private ThongkeBus bus = new ThongkeBus();
    private JLabel lblDoanhThu = new JLabel();
    private JLabel lblNhap = new JLabel();
    private JLabel lblLoiNhuan = new JLabel();
    private JTable tableTop = new JTable();
    private JTable tableNV = new JTable();

    public ThongkeGUI(){
        setLayout(new BorderLayout());
        // Panel tổng tiền
        JPanel pnlTop = new JPanel(new GridLayout(1,3));
        pnlTop.add(lblDoanhThu);
        pnlTop.add(lblNhap);
        pnlTop.add(lblLoiNhuan);
        add(pnlTop, BorderLayout.NORTH);
        // Center panel
        JTabbedPane tab = new JTabbedPane();
        tab.add("Top Sách", createTopSachPanel());
        tab.add("Nhân viên", createNhanVienPanel());
        tab.add("Biểu đồ", createChartPanel());
        add(tab, BorderLayout.CENTER);
        loadData();
    }
    private JPanel createTopSachPanel(){
        tableTop.setModel(new DefaultTableModel(
                new String[]{"Tên sách","Số lượng bán"},0));
        return new JPanel(new BorderLayout()) {{
            add(new JScrollPane(tableTop), BorderLayout.CENTER);
        }};
    }
    private JPanel createNhanVienPanel(){
        tableNV.setModel(new DefaultTableModel(
                new String[]{"Mã NV","Tên NV","Doanh thu"},0));
        return new JPanel(new BorderLayout()) {{
            add(new JScrollPane(tableNV), BorderLayout.CENTER);
        }};
    }
    private JPanel createChartPanel(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            List<DoanhthuthangModel> list = bus.getDoanhThuTheoThang();
            for(DoanhthuthangModel m : list) {
                dataset.addValue(
                        m.getDoanhThu(),
                        "Doanh thu",
                        "Tháng " + m.getThang()
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Doanh thu theo tháng",
                "Tháng",
                "Tiền",
                dataset,
                PlotOrientation.VERTICAL,
                false,true,false
        );
        return new ChartPanel(chart);
    }
    private void loadData(){
        try {
            lblDoanhThu.setText("Doanh thu: " + bus.getTongDoanhThu());
            lblNhap.setText("Tổng nhập: " + bus.getTongNhap());
            lblLoiNhuan.setText("Lợi nhuận: " + bus.getLoiNhuan());
            // Topsach
            DefaultTableModel modelTop =
                    (DefaultTableModel) tableTop.getModel();
            List<TopsachModel> topList = bus.getTopSach();
            for(TopsachModel m : topList){
                modelTop.addRow(new Object[]{
                        m.getTenSach(),
                        m.getSoLuongBan()
                });
            }
            // Nhanvien
            DefaultTableModel modelNV =
                    (DefaultTableModel) tableNV.getModel();
            List<ThongkenhanvienModel> nvList =
                    bus.getThongKeNhanVien();
            for(ThongkenhanvienModel m : nvList){
                modelNV.addRow(new Object[]{
                        m.getMaNV(),
                        m.getTenNV(),
                        m.getDoanhThu()
                });
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
