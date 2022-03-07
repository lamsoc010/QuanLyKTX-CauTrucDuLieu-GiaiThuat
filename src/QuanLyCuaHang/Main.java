package QuanLyCuaHang;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<NhanVien> listNhanVien = new ArrayList<NhanVien>();
		ArrayList<QuanLyKhachHang> listKhachHang = new ArrayList<QuanLyKhachHang>();
		KetNoiCSDL ketNoi = new KetNoiCSDL();
		NhanVien nhanVien = new NhanVien();
		QuanLyKhachHang khachHang = new QuanLyKhachHang();
		listNhanVien = ketNoi.getListNhanVien();
		listKhachHang = ketNoi.getListKhachHang();
		

		khachHang.hamHienThiKhachHang(listKhachHang);
		
		
		
	}

}
