package QuanLyCuaHang;

import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyKhachHang extends KhachHang{
	public void hienThiKhachHang() {
		System.out.format("|%10s|%10s|%10s|%20s|%20s|%10s|%5s|",maKh, tenCongTy, tenGiaoDich, diaChi, email, dienThoai, fax );
		System.out.println("");
		System.out.println("----------------------------------------------------------------------------------------------------");
	}
	
	public static void hamHienThiKhachHang(ArrayList<QuanLyKhachHang> listKhachHang) {
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("|  Mã KH   |Tên Công Ty|Tên Giao Dịch|Địa Chỉ|Email|Điện Thoại|Fax|");
		System.out.println("----------------------------------------------------------------------------------------------------");
		for(int i = 0; i < listKhachHang.size(); i++) {
			listKhachHang.get(i).hienThiKhachHang();
		}
	}
	
	public void nhapKhachHang() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập mã khách hàng: ");
		maKh = sc.nextLine();
		System.out.println("Nhập tên công ty");
		tenCongTy = sc.nextLine();
		System.out.println("Nhập tên giao dịch");
		tenGiaoDich = sc.nextLine();
		System.out.println("Nhập địa chỉ");
		diaChi = sc.nextLine();
		System.out.println("Nhập email");
		email = sc.nextLine();
		System.out.println("Nhập điện thoại");
		dienThoai = sc.nextLine();
		System.out.println("Nhập fax");
		fax = sc.nextLine();
	}
	
	public static void hamNhapKhachHang(ArrayList<QuanLyKhachHang> listKhachHang) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập số khách hàng bạn muốn nhập: ");
		int n = sc.nextInt();
		
		for(int i = 0; i < n; i++) {
			QuanLyKhachHang khachHang = new QuanLyKhachHang();
			khachHang.nhapKhachHang();
			listKhachHang.add(khachHang);
		}
	}
}
