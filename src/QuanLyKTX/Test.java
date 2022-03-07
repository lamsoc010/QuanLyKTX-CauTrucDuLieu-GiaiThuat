package QuanLyKTX;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Pattern p = java.util.regex.Pattern.compile("^C[0-9]{7}$");
//		while(true) {
//			System.out.print("Nhập: ");
//			String sdt = sc.nextLine();
//			if(p.matcher(sdt).find()) {
//				System.out.println("Ok");
//				break;
//			}
//			else {
//				System.err.println("not ok");
//			}
//			
//		}
//		Calendar c = Calendar.getInstance();
//		Date toDay = null;
//		toDay = c.getTime();
//		
//		System.out.println(toDay.getMonth());
		System.out.println("Nhập id");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("Nhập tên");
		String ten = sc.nextLine();
		System.out.println("Nhập tuổi");
		int tuoi = Integer.parseInt(sc.nextLine());
		System.out.println("Nhập gioitinh");
		String gioiTinh = sc.nextLine();
		System.out.println("Nhập nganh");
		String nganh = sc.nextLine();
		System.out.println("Nhập quê quán");
		String queQuan = sc.nextLine();
		System.out.println("Nhập sdt");
		String sdt = sc.nextLine();
		
	}
}
