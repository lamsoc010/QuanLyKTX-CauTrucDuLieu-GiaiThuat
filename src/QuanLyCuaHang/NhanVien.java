package QuanLyCuaHang;

import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class NhanVien {
	String maNv;
	String ho;
	String ten;
	Date ngaySinh;
	String diaChi;
	String dienThoai;
	double luongCoBan;
	double phuCap;
	Date ngayLamViec; 
	
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NhanVien(String maNv, String ho, String ten, Date ngaySinh, String diaChi, String dienThoai,
			double luongCoBan, double phuCap, Date ngayLamViec) {
		super();
		this.maNv = maNv;
		this.ho = ho;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.luongCoBan = luongCoBan;
		this.phuCap = phuCap;
		this.ngayLamViec = ngayLamViec;
	}


	public String getMaNv() {
		return maNv;
	}
	public void setMaNv(String maNv) {
		this.maNv = maNv;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getDienThoai() {
		return dienThoai;
	}
	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}
	public double getLuongCoBan() {
		return luongCoBan;
	}
	public void setLuongCoBan(double luongCoBan) {
		this.luongCoBan = luongCoBan;
	}
	public double getPhuCap() {
		return phuCap;
	}
	public void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}

	public Date getNgayLamViec() {
		return ngayLamViec;
	}

	public void setNgayLamViec(Date ngayLamViec) {
		this.ngayLamViec = ngayLamViec;
	}
	
	public void hienThiNhanVien() {
		System.out.format("|%10s|%10s|%10s|%10s|%20s|%10s|%5s|%5s|%10s|",maNv, ho, ten, df.format(ngaySinh), diaChi, dienThoai, luongCoBan, phuCap, df.format(ngayLamViec) );
		System.out.println("");
		System.out.println("----------------------------------------------------------------------------------------------------");
	}
	public static void hamHienThiNhanVien(ArrayList<NhanVien> listNhanVien) {
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("|  M?? NV   |    H???    |   T??n    |Ng??y Sinh |      ?????a Ch???       |  S??? ??T   |L????ng|P.C???p| Ng??y L??m |");
		System.out.println("----------------------------------------------------------------------------------------------------");
		for(int i = 0; i < listNhanVien.size(); i++) {
			listNhanVien.get(i).hienThiNhanVien();
		}
	}
	
	public void nhapNhanVien()  {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nh???p m?? nh??n vi??n: ");
		maNv = sc.nextLine();
		System.out.println("Nh???p h???: ");
		ho = sc.nextLine();
		System.out.println("Nh???p t??n: ");
		ten = sc.nextLine();
		System.out.println("Nh???p ng??y sinh: ");
		try {
			ngaySinh = df.parse(sc.nextLine());
		} catch (Exception e) {
		}
		System.out.println("Nh???p ?????a ch???: ");
		diaChi = sc.nextLine();
		System.out.println("Nh???p s??? ??i???n tho???i: ");
		dienThoai = sc.nextLine();
		System.out.println("Nh???p l????ng: ");
		luongCoBan = sc.nextDouble();
		System.out.println("Nh???p ph??? c???p: ");
		phuCap = sc.nextDouble();
		System.out.println("Nh???p ng??y l??m vi???c: ");
		try {
			ngayLamViec = df.parse(sc.next());
		} catch (Exception e) {
		}
	}
	
	public static void hamNhapNhanVien(ArrayList<NhanVien> listNhanVien)  {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nh???p s??? nh??n vi??n c???n nh???p: ");
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			NhanVien nhanVien = new NhanVien();
			nhanVien.nhapNhanVien();
			listNhanVien.add(nhanVien);
		}
	}

	
}
