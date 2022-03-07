package CauTrucLuuTru;

import QuanLyDuLieu.QuanLySinhVien;

public class NodeDoubleL {
	public QuanLySinhVien qLSV;
	public NodeDoubleL next;
	public NodeDoubleL prev;
	
	public NodeDoubleL(QuanLySinhVien qLSV) {
		this.qLSV = qLSV;
		this.next = next;
		this.prev = prev;
	}
	
	public void hienThiSinhVien() {
		qLSV.hienThiThongTinSV();
	}
	
}
