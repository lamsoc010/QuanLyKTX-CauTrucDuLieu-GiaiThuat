package QuanLyDuLieu;

import java.util.Scanner;

import CauTrucLuuTru.LinkedList;
import CauTrucLuuTru.NodeLinkedList;
import QuanLyKTX.QuanLy;

public class QuanLyQuanLy extends QuanLy {

	public QuanLyQuanLy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuanLyQuanLy(String maQL, String hoTen, String sDT, String diaChi, String phuTrach, String matKhau) {
		super(maQL, hoTen, sDT, diaChi, phuTrach, matKhau );
		// TODO Auto-generated constructor stub
	}

	public void hienThiThongTinQL() {
		System.out.printf("|%4s|%20s|%10s|%20s|%10s|%10s|\n", maQL, hoTen, SDT, diaChi, phuTrach, matKhau);
	}
	
	public String dangNhapQuanLy(LinkedList lL) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Vui lòng nhập mã quản lý: ");
		String maQL = sc.next();
		System.out.println("Vui lòng nhập mật khẩu: ");
		String matKhau = sc.next();
		
		if(lL.checkMatKhauQL(matKhau, maQL) != null)
			return maQL;
		else {
			System.err.println("Tài khoản hoặc mật khẩu không đúng");
			return null;
		}
	}
	
}
