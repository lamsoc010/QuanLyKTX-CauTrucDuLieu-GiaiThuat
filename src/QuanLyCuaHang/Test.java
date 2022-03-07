package QuanLyCuaHang;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Test {
	public static Date ngay;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Nhập ngày: ");
		
//		try {
//			ngay = df.parse(sc.next());
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		LocalDate ngayMoi;
//		ngayMoi = df.format(ngay)
		System.out.println(df.format(ngay));
	}

}
