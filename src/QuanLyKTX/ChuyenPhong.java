package QuanLyKTX;

import java.util.Date;

public class ChuyenPhong {
	public static int count = -1;
	public int maPhieu;
	public String maSV;
	public int maPhongCu;
	public int maPhongMoi;
	public String maQL;
	public Date ngayChuyen;
	public String liDo;
	
	public ChuyenPhong() {
		maPhieu = ++count;
	}
	public ChuyenPhong(int maPhieu, String maSV, int maPhongCu, int maPhongMoi, String maQL, Date ngayChuyen,
			String liDo) {
		this.maPhieu = ++count;
		this.maSV = maSV;
		this.maPhongCu = maPhongCu;
		this.maPhongMoi = maPhongMoi;
		this.maQL = maQL;
		this.ngayChuyen = ngayChuyen;
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
	public int getMaPhongCu() {
		return maPhongCu;
	}
	public void setMaPhongCu(int maPhongCu) {
		this.maPhongCu = maPhongCu;
	}
	public int getMaPhongMoi() {
		return maPhongMoi;
	}
	public void setMaPhongMoi(int maPhongMoi) {
		this.maPhongMoi = maPhongMoi;
	}
	public String getMaQL() {
		return maQL;
	}
	public void setMaQL(String maQL) {
		this.maQL = maQL;
	}
	public Date getNgayChuyen() {
		return ngayChuyen;
	}
	public void setNgayChuyen(Date ngayChuyen) {
		this.ngayChuyen = ngayChuyen;
	}
	public String getLiDo() {
		return liDo;
	}
	public void setLiDo(String liDo) {
		this.liDo = liDo;
	}
	
	
}
