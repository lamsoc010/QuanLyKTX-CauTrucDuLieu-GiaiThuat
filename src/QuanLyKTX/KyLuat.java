package QuanLyKTX;

import java.util.Date;

public class KyLuat {
	static int count = -1;
	public int maPhieu;
	public String loaiKL;
	public String noiDung;
	public String hinhThucPhat;
	public String maSV;
	public String maQL;
	public Date ngayViPham;
	public KyLuat() {
		maPhieu = ++count;
	}
	
	
	public KyLuat(int maPhieu, String loaiKL, String noiDung, String hinhThucPhat, String maSV, String maQL, Date ngayViPham) {
		this.maPhieu = maPhieu;
		this.loaiKL = loaiKL;
		this.noiDung = noiDung;
		this.hinhThucPhat = hinhThucPhat;
		this.maSV = maSV;
		this.maQL = maQL;
		this.ngayViPham = ngayViPham;
	}


	public int getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(int maPhieu) {
		this.maPhieu = maPhieu;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getMaQL() {
		return maQL;
	}
	public void setMaQL(String maQL) {
		this.maQL = maQL;
	}
	public Date getNgayViPham() {
		return ngayViPham;
	}
	public void setNgayViPham(Date ngayViPham) {
		this.ngayViPham = ngayViPham;
	}
	public String getHinhThucPhat() {
		return hinhThucPhat;
	}
	public void setHinhThucPhat(String hinhThucPhat) {
		this.hinhThucPhat = hinhThucPhat;
	}
	public String getLoaiKL() {
		return loaiKL;
	}
	public void setLoaiKL(String loaiKL) {
		this.loaiKL = loaiKL;
	}
	
	
	
}
