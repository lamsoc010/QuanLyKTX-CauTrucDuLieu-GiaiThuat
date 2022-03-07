package QuanLyKTX;

import java.util.Date;

public class TraPhong {
	public static int count = -1;
	public int maPhieu;
	public String maSV;
	public int maPhong;
	public String maQL;
	public Date ngayTra;
	public String liDo;
	public TraPhong() {
		maPhieu = ++count;
	}
	public TraPhong(int maPhieu, String maSV, int maPhong, String maQL, Date ngayTra, String liDo) {
		this.maPhieu = ++count;
		this.maSV = maSV;
		this.maPhong = maPhong;
		this.maQL = maQL;
		this.ngayTra = ngayTra;
		this.liDo = liDo;
	}
	public int getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(int maPhieu) {
		this.maPhieu = maPhieu;
	}
	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public int getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(int maPhong) {
		this.maPhong = maPhong;
	}
	public String getMaQL() {
		return maQL;
	}
	public void setMaQL(String maQL) {
		this.maQL = maQL;
	}
	public Date getNgayTra() {
		return ngayTra;
	}
	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}
	public String getLiDo() {
		return liDo;
	}
	public void setLiDo(String liDo) {
		this.liDo = liDo;
	}
	
	
}
