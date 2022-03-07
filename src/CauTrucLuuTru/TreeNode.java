package CauTrucLuuTru;

import QuanLyDuLieu.QuanLyKyLuat;
import QuanLyDuLieu.QuanLyPhong;
import QuanLyDuLieu.QuanLyThuePhong;

public class TreeNode {
	public QuanLyPhong qLP;
	public QuanLyKyLuat qLKL;
	public QuanLyThuePhong qLTP;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(QuanLyPhong qLP) {
		this.qLP = qLP;
	}
	
	public TreeNode(QuanLyKyLuat qLKL) {
		this.qLKL = qLKL;
	}
	
	public TreeNode(QuanLyThuePhong qLTP) {
		this.qLTP = qLTP;
	}
	
	public void hienThiListP() {
		qLP.hienThiThongTin();
	}
	
	public void hienThiListKL() {
		qLKL.hienThiThongTinKL();
	}
	
	public void hienThiListTP() {
		qLTP.hienThiThongTinTP();
	}
}
