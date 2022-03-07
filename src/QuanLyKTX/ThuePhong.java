package QuanLyKTX;

import java.util.Date;

public class ThuePhong {
	public static int count = -1;
	public int maPhieu;
	public String maSV;
	public String maQL;
	public int maPhong;
	public Date ngayVao;
	public String trangThai;
	
	public ThuePhong() {
		maPhieu = ++count;
	}
	
	public ThuePhong(String maSV, String maQL, int maPhong, Date ngayVao, String trangThai) {
		maPhieu = ++count;
		this.maSV = maSV;
		this.maQL = maQL;
		this.maPhong = maPhong;
		this.ngayVao = ngayVao;
		this.trangThai = trangThai;
	}


	public int getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(int maPhieu) {
		this.maPhieu = maPhieu;
	}
	public int getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(int maPhong) {
		this.maPhong = maPhong;
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
	public Date getNgayVao() {
		return ngayVao;
	}
	public void setNgayVao(Date ngayVao) {
		this.ngayVao = ngayVao;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
