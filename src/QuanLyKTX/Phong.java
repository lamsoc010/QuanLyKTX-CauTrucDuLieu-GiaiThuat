package QuanLyKTX;

import java.util.regex.Pattern;

public class Phong {
	public int maPhong;
	public String loaiPhong;
	public int soGiuong;
	public int soNguoi;
	public float giaTien;
	
	public Phong() {
	}

	public Phong(int maPhong, String loaiPhong, int soGiuong, int soNguoi, float giaTien) {
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.soGiuong = soGiuong;
		this.soNguoi = soNguoi;
		this.giaTien = giaTien;
	}

	public int getMaPhong() {
		return maPhong;
	}
	public boolean setMaPhong(int maPhong) {
		if(maPhong >= 100 && maPhong <= 999) {
			this.maPhong = maPhong;
			return true;
		} else {
			System.err.println("Mã phòng gồm 3 số");
			return false;
		}
	}
	public String getLoaiPhong() {
		return loaiPhong;
	}
	public boolean setLoaiPhong(String loaiPhong) {
		if(loaiPhong.equals("LOẠI 1") || loaiPhong.equals("LOẠI 2")) {
			this.loaiPhong = loaiPhong;
			return true;
		} else {
			System.err.println("Loại phòng LOẠI 1 HOẶC LOẠI 2");
			return false;
		}
	}
	public int getSoGiuong() {
		return soGiuong;
	}
	public void setSoGiuong(int soGiuong) {
		this.soGiuong = soGiuong;
	}
	public int getSoNguoi() {
		return soNguoi;
	}
	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}
	public float getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(float giaTien) {
		this.giaTien = giaTien;
	}
	
	
}
