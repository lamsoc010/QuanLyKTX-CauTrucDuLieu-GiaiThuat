package CauTrucLuuTru;

import QuanLyDuLieu.QuanLyQuanLy;

public class NodeLinkedList {
	public QuanLyQuanLy qLQL;
	public NodeLinkedList next;
	
	public NodeLinkedList(QuanLyQuanLy qLQL) {
		this.qLQL = qLQL;
		this.next = null;
	}
	
	public void hienThiQuanLy() {
		qLQL.hienThiThongTinQL();
	}
}
