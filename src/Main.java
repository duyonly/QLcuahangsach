package Main;
import Gui.TrangChu;
import Nhanvien.*;
import Gui.FormDangNhap;
import java.awt.EventQueue;
public class Main {
//    public static void main(String[] args){
//        EventQueue.invokeLater(new Runnable(){
//            @Override
//            public void run(){
//                new FormDangNhap();
//            }
//        });
//} }
public static void main(String[] args){
    EventQueue.invokeLater(() ->{
        NhanvienModel admin = new NhanvienModel();
        admin.setChucVu("Admin");
        new TrangChu(admin);
    });
}}
