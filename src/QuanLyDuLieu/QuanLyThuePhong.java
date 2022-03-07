package QuanLyDuLieu;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ScheduledFuture;

import CauTrucLuuTru.DoubleLinkedList;
import CauTrucLuuTru.MyBST;
import CauTrucLuuTru.NodeDoubleL;
import CauTrucLuuTru.TreeNode;
import QuanLyKTX.DAO;
import QuanLyKTX.ThuePhong;

public class QuanLyThuePhong extends ThuePhong{

	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public QuanLyThuePhong() {
		super();
	}
	public QuanLyThuePhong(String maSV, String maQL, int maPhong, Date ngayVao, String trangThai) {
		super(maSV, maQL, maPhong, ngayVao, trangThai);
		// TODO Auto-generated constructor stub
	}
	public void hienThiThongTinTP() {
		System.out.printf("|%8s|%8s|%5s|%8s|%10s|%10s|\n", maPhieu, maSV, maQL,maPhong, df.format(ngayVao), trangThai);
	}
	
	public void hienThiTieuDeThuePhong() {
		System.out.println("--------------------------------------------------------");
		System.out.println("|Ma Phieu| Ma SV  |Ma QL|Ma Phong| Ngay Vao |Trang Thai|");
		System.out.println("--------------------------------------------------------");
	}
	
	public void nhapThuePhong(TreeNode treeNode, DoubleLinkedList dLL, NodeDoubleL node, String maQL1) throws SQLException {
		Scanner sc = new Scanner(System.in);
		DAO dao = new DAO();
		while(true) {
			try {
				System.out.println("Nhập mã phòng: ");
				maPhong = Integer.parseInt(sc.next());
				int index = -1;
				int index1 = -1;
//				
				//Check xem phòng tồn tại không và còn chổ không
				QuanLyPhong qLP = new QuanLyPhong();
				QuanLySinhVien qLSV = new QuanLySinhVien();
				TreeNode currNode = treeNode;
				currNode = qLP.checkFullPhong(treeNode, maPhong);
				
				if(currNode != null) {
					qLSV.hamNhapThongTin(dLL, node);
					dLL.NodeEnd(node).qLSV.setMaPhong(maPhong);
					dao.insertListSinhVien(dLL.NodeEnd(node));
					maSV = dLL.NodeEnd(node).qLSV.getMaSV();
					ngayVao = dLL.NodeEnd(node).qLSV.getNgayVao();
					
					currNode.qLP.setSoNguoi(currNode.qLP.getSoNguoi() + 1);
					dao.updatePhong(currNode.qLP, maPhong);
					index = 1;
					break;
				}
				if(index == 1) {
					break;
				}
			} catch (Exception e) {
				System.err.println("Bạn đã nhập sai vui lòng nhập lại");
			}
		}
//		System.out.println("Nhập mã quản lý: ");
		maQL = maQL1;
		trangThai ="Đang thuê";
	}
	
	public static void hamNhapThuePhong(MyBST bST, DoubleLinkedList dLL, NodeDoubleL node, String maQL ) throws SQLException {
		DAO dao = new DAO();
		QuanLyThuePhong qLTP = new QuanLyThuePhong();
		qLTP.nhapThuePhong(bST.mRoot, dLL, node, maQL);
		TreeNode treeNode = new TreeNode(qLTP);
		dao.insertThuePhong(qLTP);
		bST.mRootTP = qLTP.insertIntoBST(bST.mRootTP, treeNode);
		System.out.println("Thêm thành công!");
	}
	
	public TreeNode insertIntoBST(TreeNode root, TreeNode treeNode) {
		if(root == null) {
			root = treeNode;
			return root;
		}
		if(treeNode.qLTP.maPhieu > root.qLTP.getMaPhieu()) {
			if(root.right == null) 
				root.right = treeNode;
			else
				insertIntoBST(root.right, treeNode);
		}
		else 
			if(root.left == null) 
				root.left = treeNode;
			else
				insertIntoBST(root.left, treeNode);
		return root;
	}
	
	// L - Node - R
	public static void InOder(TreeNode currNode) {
			if(currNode == null) {
				return;
			}
			//Duyệt bên trái
			InOder(currNode.left);
			
			currNode.hienThiListTP();
			
			InOder(currNode.right);
	}
	
	public void taiThuePhong(MyBST bST, TreeNode treeNode, DoubleLinkedList dLL, NodeDoubleL node, String maQL1) throws SQLException {
		Scanner sc = new Scanner(System.in);
		DAO dao = new DAO();
		QuanLyPhong qLP = new QuanLyPhong();
		NodeDoubleL currNode;
		TreeNode treeCurrNode;
		while(true) {
			try {
				System.out.println("Nhập mã sinh viên muốn thuê lại: ");
				maSV = sc.nextLine();
				currNode = dLL.search(node, maSV);
				if(currNode != null) {
					if(currNode.qLSV.getTrangThai().equalsIgnoreCase("đã trả")) {
						currNode.qLSV.hienThiTieuDe();
						currNode.qLSV.hienThiThongTinSV();
						break;
					}
					else
						System.err.println("Sinh viên này đang thuê rồi!!!");
				}
				else
					System.err.println("Sinh viên này chưa thuê lần nào trong KTX!");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		while(true) {
			try {
				System.out.println("Nhập mã phòng muốn thuê: ");
				maPhong = Integer.parseInt(sc.nextLine());
				treeCurrNode = qLP.checkFullPhong(treeNode, maPhong);
				if(treeCurrNode != null) {
					break;
				}
			} catch (Exception e) {
				System.err.println("Vui lòng nhập đúng định dạng!");
			}
		}
		while(true) {
			try {
				df.setLenient(false);
				System.out.println("Nhập ngày vào: ");
				ngayVao = df.parse(sc.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("Vui lòng nhập đúng đinh dạng(yyyy-MM-dd): ");
			}
		}
		//Lưu thông tin thuê phòng, và thêm vào SQL
		QuanLyThuePhong qLTP = new QuanLyThuePhong();
		qLTP.setMaSV(maSV);
		qLTP.setNgayVao(ngayVao);
		qLTP.setMaPhong(maPhong);
		qLTP.setMaQL(maQL1);
		qLTP.setTrangThai("Đang thuê");
		dao.insertThuePhong(qLTP);
		TreeNode treeNode1 = new TreeNode(qLTP);
		bST.mRootTP = qLTP.insertIntoBST(bST.mRootTP, treeNode1);
		
//		Xét lại trạng thái và các thông tin cho sinh viên
		currNode.qLSV.setNgayVao(ngayVao);
		currNode.qLSV.setMaPhong(maPhong);
		currNode.qLSV.setTrangThai("Đang thuê");
		dao.updateSinhVien(currNode.qLSV, maSV, maPhong);
		
		//Tăng số người trong phòng thêm vào
		treeCurrNode.qLP.setSoNguoi(treeCurrNode.qLP.getSoNguoi() + 1);
		dao.updatePhong(treeCurrNode.qLP, maPhong);
		System.out.println("Cho thuê thành công!");
		
	}
		
}
