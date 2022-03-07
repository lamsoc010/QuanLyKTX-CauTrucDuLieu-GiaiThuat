package QuanLyDuLieu;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import CauTrucLuuTru.DoubleLinkedList;
import CauTrucLuuTru.LinkedList;
import CauTrucLuuTru.MyBST;
import CauTrucLuuTru.NodeDoubleL;
import CauTrucLuuTru.NodeLinkedList;
import CauTrucLuuTru.TreeNode;
import QuanLyKTX.DAO;
import QuanLyKTX.KyLuat;

public class QuanLyKyLuat extends KyLuat{
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public QuanLyKyLuat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuanLyKyLuat(int maPhieu, String tenKL, String noiDung, String hinhThucPhat, String maSV, String maQL, Date ngayViPham) {
		super(maPhieu, tenKL, noiDung, hinhThucPhat, maSV, maQL, ngayViPham);
		// TODO Auto-generated constructor stub
	}
	
	public void hienThiThongTinKL() {
		System.out.printf("|%8s|%20s|%40s|%30s|%8s|%5s|%12s|\n", maPhieu, loaiKL, noiDung, hinhThucPhat, maSV, maQL,df.format(ngayViPham));
	}
	
	public void hienThiTieuDeKyLuat() {
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|Ma Phieu|    Loai Ky Luat    |                Noi Dung                |        Hinh Thuc Phat        | Ma SV  |Ma QL|Ngay Vi Pham|");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public void nhapThongTinKL(DoubleLinkedList dLL, LinkedList lL, NodeDoubleL node, String maQL1) throws SQLException {
		DAO dao = new DAO();
		Scanner sc = new Scanner(System.in);
		int luaChon;
		while(true) {
			try {
				System.out.println("Nhập loại kỷ luật (*1. Vệ sinh || *2. Giờ giấc): ");
				luaChon = Integer.parseInt(sc.nextLine());
				if(luaChon == 1 || luaChon == 2) {
					if(luaChon == 1) {
						NodeLinkedList nodeLL = lL.checkMaQL(maQL1);
						if(nodeLL.qLQL.getPhuTrach().equalsIgnoreCase("vệ sinh")) {
							loaiKL = "Vệ sinh";
							break;
						}
						else
							System.err.println("Bạn không phụ trách phần vệ sinh");
					}
					else if(luaChon == 2) {
						NodeLinkedList nodeLL = lL.checkMaQL(maQL1);
						if(nodeLL.qLQL.getPhuTrach().equalsIgnoreCase("giờ giấc")) {
							loaiKL = "Giờ giấc";
							break;
						}
						else
							System.err.println("Bạn không phụ trách phần giờ giấc");
					}
				}
				else 
					System.err.println("Vui lòng chọn 1 trong 2");
			} catch (Exception e) {
				System.err.println("Vui lòng chọn 1 trong 2");
			}
		}
		
		System.out.println("Nhập nội dung vi phạm: ");
		noiDung = sc.nextLine();
		System.out.println("Nhập hình thức phạt: ");
		hinhThucPhat = sc.nextLine();
		NodeDoubleL currNode;
		while (true) {
			try {
				System.out.println("Nhập mã sinh viên: ");
				maSV = sc.nextLine();
				if(node.qLSV.setMaSV(maSV)) {
					currNode = dLL.checkSV(node, maSV);
					if(currNode == null) 
						System.err.println("Mã sinh viên không tồn tại trong danh sách");
					else {
						currNode.qLSV.setSoLanViPham(currNode.qLSV.getSoLanViPham() + 1);
						break;
					}
				}
			} catch (Exception e) {
			}
		}
		
		maQL = maQL1;
		df.setLenient(false);
		while(true){
			try {
				System.out.println("Nhập ngày vi phạm: ");
				ngayViPham = df.parse(sc.nextLine());
				if(ngayViPham.after(currNode.qLSV.getNgayVao()))
					break;
				else 
					System.err.println("Ngày vi phạm không thể nhỏ hơn ngày vào!");
			} catch (ParseException e) {
				System.out.println("Bạn đã nhập sai vui lòng nhập lại");
			}
		}
		dao.updateSinhVien(currNode.qLSV, maSV, currNode.qLSV.getMaPhong());
	}
	
	public static void hamNhapThongTinKL(MyBST bST, DoubleLinkedList dLL, LinkedList lL, NodeDoubleL node, String maQL) throws SQLException {
		DAO dao = new DAO();
		QuanLyKyLuat qLKL = new QuanLyKyLuat();
		qLKL.nhapThongTinKL(dLL, lL, node, maQL);
		dao.insertKyLuat(qLKL);
		TreeNode treeNode = new TreeNode(qLKL);
		bST.mRootKL = qLKL.insertIntoBST(bST.mRootKL, treeNode);
		System.out.println("Kỷ luật thành công!");
		qLKL.hienThiTieuDeKyLuat();
		qLKL.InOder(bST.mRootKL);
	}
	
	
	public TreeNode insertIntoBST(TreeNode root, TreeNode treeNode) {
		if(root == null) {
			root = treeNode;
			return root;
		}
		if(treeNode.qLKL.maPhieu > root.qLKL.getMaPhieu()) {
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
		
		currNode.hienThiListKL();
		
		InOder(currNode.right);
	}
	
	
}
