package QuanLyCuaHang;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class KetNoiCSDL {
		public Connection connection ;
		
		public KetNoiCSDL() {
			try {
				String connectionURL = "jdbc:sqlserver://ADMIN\\SQLEXPRESS:1433;databaseName=QuanLyCuaHang;integratedSecurity=true";
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(connectionURL);
				System.out.println("Kết nối thành công");
			} catch (Exception e) {
				System.err.println("Kết nối thất bại");
				e.printStackTrace();
			}
		}
		
		public void ghiFileNhanVien(NhanVien nhanVien2) {
			String sql = "INSERT INTO NHANVIEN (MANHANVIEN, HO, TEN, NGAYSINH, DIACHI, DIENTHOAI, LUONGCOBAN, PHUCAP, NGAYLAMVIEC) VALUES (?,?,?,?,?,?,?,?,?)";
//			for (NhanVien nhanVien2 : listNhanVien) {
				try {
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1, nhanVien2.getMaNv());
					ps.setString(2, nhanVien2.getHo());
					ps.setString(3, nhanVien2.getTen());
					ps.setDate(4, (java.sql.Date) nhanVien2.getNgaySinh());
					ps.setString(5, nhanVien2.getDiaChi());
					ps.setString(6, nhanVien2.getDienThoai());
					ps.setDouble(7, nhanVien2.getLuongCoBan());
					ps.setDouble(8, nhanVien2.getPhuCap());
					ps.setDate(9, (java.sql.Date) nhanVien2.getNgayLamViec());
					ps.executeUpdate();
					ps.close();
					
					
					if(ps.executeUpdate() > 0) {
						System.out.println("Ghi thành công");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
//			}
		}
	
		public ArrayList<NhanVien> getListNhanVien() {
			ArrayList<NhanVien> listNhanVien = new ArrayList<>();
			String sql = "SELECT * FROM NHANVIEN";
			
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					NhanVien nhanVien = new NhanVien();
					nhanVien.setMaNv(rs.getString("MANHANVIEN"));
					nhanVien.setHo(rs.getString("HO"));
					nhanVien.setTen(rs.getString("TEN"));
					nhanVien.setNgaySinh(rs.getDate("NGAYSINH"));
					nhanVien.setDiaChi(rs.getString("DIACHI"));
					nhanVien.setDienThoai(rs.getString("DIENTHOAI"));
					nhanVien.setLuongCoBan(rs.getDouble("LUONGCOBAN"));
					nhanVien.setPhuCap(rs.getDouble("PHUCAP"));
					nhanVien.setNgayLamViec(rs.getDate("NGAYLAMVIEC"));
					
					listNhanVien.add(nhanVien);
				}
//			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return listNhanVien;
		}
		
		public void ghiFileKhachHang(QuanLyKhachHang khachHang) {
			String sql = "INSERT INTO KHACHHANG(MAKHACHHANG, TENCONGTY, TENGIAODICH, DIACHI, EMAIL, DIENTHOAI, FAX) VALUES(?,?,?,?,?,?,?)";
//			for (QuanLyKhachHang khachHang : listKhachHang) {
				try {
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1, khachHang.getMaKh());
					ps.setString(2, khachHang.getTenCongTy());
					ps.setString(3, khachHang.getTenGiaoDich());
					ps.setString(4, khachHang.getDiaChi());
					ps.setString(5, khachHang.getEmail());
					ps.setString(6, khachHang.getDienThoai());
					ps.setString(7, khachHang.getFax());
					ps.executeUpdate();
					ps.close();
					
					if(ps.executeUpdate() > 0) {
						System.out.println("Ghi thành công");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
//			}
		}
		
		public ArrayList<QuanLyKhachHang> getListKhachHang() {
			ArrayList<QuanLyKhachHang> listKhachHang = new ArrayList<>();
			String sql = "SELECT * FROM KHACHHANG";
			
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					QuanLyKhachHang khachHang = new QuanLyKhachHang();
					khachHang.setMaKh(rs.getString(1));
					khachHang.setTenCongTy(rs.getString(2));
					khachHang.setTenGiaoDich(rs.getString(3));
					khachHang.setDiaChi(rs.getString(4));
					khachHang.setEmail(rs.getString(5));
					khachHang.setDienThoai(rs.getString(6));
					khachHang.setFax(rs.getString(7));
					
					listKhachHang.add(khachHang);
				}
//			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return listKhachHang;
		}
		
		
		public static void main(String[] args) throws ClassNotFoundException {
			new KetNoiCSDL();
			
		}


}
