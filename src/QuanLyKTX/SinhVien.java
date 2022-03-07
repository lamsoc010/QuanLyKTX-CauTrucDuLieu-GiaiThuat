package QuanLyKTX;

import java.util.Date;
import java.util.regex.Pattern;

public class SinhVien {
	public String maSV;
	public String hoTen;
	public String SDT;
	public String gioiTinh;
	public String diaChi;
	public Date ngaySinh;
	public String nganh;
	public String khoa;
	public Date ngayVao;
	public int maPhong;
	public int soLanViPham;
	public String trangThai;
	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SinhVien(String maSV, String hoTen, String sDT, String gioiTinh, String diaChi, Date ngaySinh, String nganh,
			String khoa, Date ngayVao, int maPhong, int soLanViPham, String trangThai) {
		super();
		this.maSV = maSV;
		this.hoTen = hoTen;
		SDT = sDT;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.nganh = nganh;
		this.khoa = khoa;
		this.ngayVao = ngayVao;
		this.maPhong = maPhong;
		this.soLanViPham = soLanViPham;
		this.trangThai = trangThai;
	}
	public String getMaSV() {
		return maSV;
	}
	public boolean setMaSV(String maSV) {
		Pattern p = Pattern.compile("^C[0-9]{7}$");
		if(p.matcher(maSV).find()) {
			this.maSV = maSV;
			return true;
		} 
		else {
			System.err.println("Mã sinh viên có dạng (Cxxxxxx) (x là số): ");
			return false;
		}
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	
	public String getSDT() {
		return SDT;
	}
	public boolean setSDT(String sDT) {
		Pattern p = Pattern.compile("^[0][3,5,7,8,9][0-9]{8}$");
		if(p.matcher(sDT).find()) {
			this.SDT = sDT;
			return true;
		}
		else {
			System.err.println("Bạn đã nhập sai định dạng số điện thoại, vui lòng nhập lại: ");
			return false;
		}
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public boolean setGioiTinh(String gioiTinh) {
		if(gioiTinh.equals("NAM") || gioiTinh.equals("NỮ")) {
			this.gioiTinh = gioiTinh;
			return true;
		} else {
			System.err.println("Giới tính là NAM hoặc NỮ");
			return false;
		}
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getNganh() {
		return nganh;
	}
	public void setNganh(String nganh) {
		this.nganh = nganh;
	}
	public String getKhoa() {
		return khoa;
	}
	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}
	public Date getNgayVao() {
		return ngayVao;
	}
	public void setNgayVao(Date ngayVao) {
		this.ngayVao = ngayVao;
	}
	public int getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(int maPhong) {
		this.maPhong = maPhong;
	}
	public int getSoLanViPham() {
		return soLanViPham;
	}
	public void setSoLanViPham(int soLanViPham) {
		this.soLanViPham = soLanViPham;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
