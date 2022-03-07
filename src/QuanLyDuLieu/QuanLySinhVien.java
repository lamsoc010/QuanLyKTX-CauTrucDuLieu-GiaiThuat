package QuanLyDuLieu;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import CauTrucLuuTru.DoubleLinkedList;
import CauTrucLuuTru.NodeDoubleL;
import QuanLyKTX.DAO;
import QuanLyKTX.SinhVien;

public class QuanLySinhVien extends SinhVien{

	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public QuanLySinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuanLySinhVien(String maSV, String hoTen, String sDT, String gioiTinh, String diaChi, Date ngaySinh,
			String nganh, String khoa, Date ngayVao, int maPhong, int soLanViPham, String trangThai) {
		super(maSV, hoTen, sDT, gioiTinh, diaChi, ngaySinh, nganh, khoa, ngayVao, maPhong, soLanViPham, trangThai);
		// TODO Auto-generated constructor stub
	}
	
	public void hienThiThongTinSV() {
		System.out.printf("|%8s|%20s|%10s|%9s|%20s|%10s|%6s|%4s|%10s|%8s|%7s|%10s|\n", maSV, hoTen, SDT, gioiTinh, diaChi,df.format(ngaySinh), nganh, khoa, df.format(ngayVao), maPhong, soLanViPham, trangThai);
	}
	
	public static void hienThiTieuDe() {
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("| Ma SV  |       Ho Ten       |    SDT   |Gioi Tinh|      Dia Chi       | Ngay Sinh|Nganh |Khoá| Ngay Vao |Ma Phong|Vi Pham|Trang Thai|");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public void nhapThongTin (DoubleLinkedList dLL, NodeDoubleL node) {
		df.setLenient(false);
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Nhập mã sinh viên");
			String maSV1 = sc.nextLine();
			boolean check = setMaSV(maSV1);
			if(check) {
				NodeDoubleL currNode = dLL.checkSV(node, maSV1);
				if(currNode != null) 
					System.err.println("Sinh viên này đã tồn tại trong KTX");
				else
					break;
			}
		}
		
		System.out.println("Nhập họ tên: ");
		hoTen = sc.nextLine();
		System.out.println("Nhập số điện thoại");
		while(!setSDT(sc.nextLine()));
		System.out.println("Nhập giới tính: ");
		while(!setGioiTinh(sc.nextLine()));
		System.out.println("Nhập địa chỉ: ");
		diaChi = sc.nextLine();
		while(true) {
			try {
				System.out.println("Nhập ngày sinh(yyyy-MM-dd): ");
				ngaySinh = df.parse(sc.nextLine());
				break;
			} catch (ParseException e) {
				System.err.println("Bạn đã nhập sai định dạng (yyyy-MM-dd): ");
			}
		}
		System.out.println("Nhập ngành: ");
		nganh = sc.nextLine();
		while(true) {
			try {
				System.out.println("Nhập khoá (*1. K19, *2. K18, *3. K17):  ");
				int chon = Integer.parseInt(sc.nextLine());
				if(chon == 1) {
					khoa = "K19";
					break;
				} else if(chon == 2) {
					khoa = "K18";
					break;
				} else if(chon == 3) {
					khoa = "K17";
					break;
				} else 
					System.err.println("Bạn đã chọn sai vui lòng chọn lại");
			} catch (Exception e) {
				System.err.println("Bạn đã nhập sai vui lòng nhập lại");
			}
		}
		while(true) {
			try {
				System.out.println("Nhập ngày vào(yyyy-MM-dd): ");
				ngayVao = df.parse(sc.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("Bạn đã nhập sai định dạng (yyyy-MM-dd): ");
			}
		}
		soLanViPham = 0;
		trangThai = "Đang thuê";
	}
	
	public static void hamNhapThongTin(DoubleLinkedList dLL, NodeDoubleL node) throws SQLException {
			QuanLySinhVien qLSV = new QuanLySinhVien();
			qLSV.nhapThongTin(dLL, node);
			NodeDoubleL nodeDLL = new NodeDoubleL(qLSV);
			dLL.insertTail(nodeDLL);
//		}
	}
	
}
