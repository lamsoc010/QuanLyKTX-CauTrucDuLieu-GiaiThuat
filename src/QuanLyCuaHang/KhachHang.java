package QuanLyCuaHang;

public class KhachHang {
	String maKh;
	String tenCongTy;
	String tenGiaoDich;
	String diaChi;
	String email;
	String dienThoai;
	String fax;
	
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public KhachHang(String maKh, String tenCongTy, String tenGiaoDich, String diaChi, String email, String dienThoai,
			String fax) {
		super();
		this.maKh = maKh;
		this.tenCongTy = tenCongTy;
		this.tenGiaoDich = tenGiaoDich;
		this.diaChi = diaChi;
		this.email = email;
		this.dienThoai = dienThoai;
		this.fax = fax;
	}


	public String getMaKh() {
		return maKh;
	}
	public void setMaKh(String maKh) {
		this.maKh = maKh;
	}
	public String getTenCongTy() {
		return tenCongTy;
	}
	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}
	public String getTenGiaoDich() {
		return tenGiaoDich;
	}
	public void setTenGiaoDich(String tenGiaoDich) {
		this.tenGiaoDich = tenGiaoDich;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDienThoai() {
		return dienThoai;
	}
	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		fax = fax;
	}
	
	
}
