package QuanLyDuLieu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


import CauTrucLuuTru.MyBST;
import CauTrucLuuTru.TreeNode;
import QuanLyKTX.DAO;
import QuanLyKTX.Phong;

public class QuanLyPhong extends Phong {
	
	
	public QuanLyPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuanLyPhong(int maPhong, String loaiPhong, int soGiuong, int soNguoi, float giaTien) {
		super(maPhong, loaiPhong, soGiuong, soNguoi, giaTien);
		// TODO Auto-generated constructor stub
	}

	public void nhapThongTin(TreeNode treeNode) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("Nhập mã phòng: ");
				maPhong = Integer.parseInt(sc.nextLine());
				boolean check = setMaPhong(maPhong);
				if(check) {
					TreeNode currNode = checkPhong(treeNode, maPhong);
					if(currNode != null) 
						System.err.println("Mã phòng đã tồn tại");
					else 
						break;
				}
			} catch (Exception e) {
				System.err.println("Bạn đã nhập sai vui lòng nhập lại");
			}
		}
		System.out.println("Nhập loại phòng(LOẠI 1 ||LOẠI 2): ");
		while(!setLoaiPhong(sc.nextLine()));
		
		
		System.out.println("Nhập số giường: ");
		while(true) {
			try {
				soGiuong = Integer.parseInt(sc.nextLine());
				if(soGiuong >=2 && soGiuong <= 4)
					break;
				else
					System.err.println("Số giường từ 2 đến 4 giường");
			} catch (Exception e) {
				System.err.println("Số giường từ 2 đến 4 giường");
			}
		}
		soNguoi = 0;
		if(loaiPhong.equals("LOẠI 1"))
			giaTien = soGiuong * 300000;
		else 
			giaTien = soGiuong * 200000;
		
	}
	
	public static void hamNhapThongTin(MyBST bST) throws SQLException {
		DAO dao = new DAO();
		Scanner sc = new Scanner(System.in);
		int n;
		while(true) {
			try {
				System.out.println("Nhập số phòng cần nhập: ");
				n = Integer.parseInt(sc.nextLine());
				if(n >= 0 && n <= 10)
					break;
				else
					System.err.println("Số phòng nhập lớn hơn 0");
			} catch (Exception e) {
				System.err.println("Vui lòng nhập số lớn hơn 0");
			}
		}
		
		for(int i = 0; i < n; i++) {
			QuanLyPhong qLP = new QuanLyPhong();
			qLP.nhapThongTin(bST.mRoot);
			TreeNode treeNode = new TreeNode(qLP);
			dao.insertListPhong(qLP);
			bST.mRoot = qLP.insertIntoBST(bST.mRoot, treeNode);
		}
		System.out.println("Thêm thành công!");
	}
	
	public void hienThiThongTin() {
		System.out.printf("|%8s|%10s|%9s|%8s|%11s|\n", maPhong, loaiPhong, soGiuong, soNguoi, giaTien);
	}
	
	public static void hienThiTieuDePhong() {
		System.out.println("----------------------------------------------------");
		System.out.println("|Ma Phong|Loai Phong|So Giuong|So Nguoi| Gia Tien  |");
		System.out.println("----------------------------------------------------");
	}
	
//	Kiểm tra phòng có tồn tại không, nếu tồn tại thì return về node của phòng đó không thì return null
	public TreeNode checkPhong(TreeNode root, int maPhong) {
		if(root == null) {
			return null;
		} else {
			if(maPhong < root.qLP.getMaPhong())
				return checkPhong(root.left, maPhong);
			else if(maPhong > root.qLP.getMaPhong()) 
				return checkPhong(root.right, maPhong);
			else
				return root;
		}
	}
	
	
	//Kiểm tra xem phòng có tồn tại không và đã đủ người chưa, trả về phòng đó
	public TreeNode checkFullPhong(TreeNode treeNode, int maPhong) {
		TreeNode currNode = checkPhong(treeNode, maPhong);
		if(currNode != null) {
			if(currNode.qLP.getSoNguoi() < currNode.qLP.getSoGiuong() * 2)
				return currNode;
			else 
				System.err.println("Phòng này đã đủ người");
		} else 
			System.err.println("Phòng không tồn tại");
		
		return null;
	}
	
	//Tìm kiếm
	public void searchBST(TreeNode root) {
		Scanner sc = new Scanner(System.in);
		int maPhong;
		while(true) {
			try {
				System.out.println("Nhập mã phòng bạn muốn tìm: ");
				maPhong = Integer.parseInt(sc.next());
				if(setMaPhong(maPhong)) 
					break;
			} catch (Exception e) {
				System.err.println("Bạn đã nhập sai, vui lòng nhập lại");
			}
		}
		TreeNode currNode = checkPhong(root, maPhong);
		if(currNode == null)
			System.err.println("Không tìm thấy phòng theo mã phòng");
		else {
			System.out.println("Phòng bạn muốn tìm: ");
			currNode.qLP.hienThiTieuDePhong();
			currNode.qLP.hienThiThongTin();
		}
		
	}
	
	
	public TreeNode insertIntoBST(TreeNode root, TreeNode treeNode) {
		if(root == null) {
			root = treeNode;
			return root;
		}
		if(treeNode.qLP.getMaPhong() > root.qLP.getMaPhong()) {
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
		
		currNode.hienThiListP();
		
		InOder(currNode.right);
	}
	
	//Node - L - R
	public static void PreOder(TreeNode currNode) {
		if(currNode == null) 
			return;
		currNode.qLP.hienThiThongTin();
		
		//Duyệt bên trái, duyệt bên phải
		PreOder(currNode.left);
		PreOder(currNode.right);
	}
	
	//L - R - Node
	public static void PostOder(TreeNode currNode) {
		if(currNode == null)
			return;
		//Duyệt bên trái
		PostOder(currNode.left);
		
		PostOder(currNode.right);
		
		currNode.qLP.hienThiThongTin();
	}
	
	
	//Tìm node con trái cùng của root
	public TreeNode findLeftModeNode(TreeNode root) {
		if(root == null)
			return null;
		TreeNode leftModeNode = root;
		while(leftModeNode.left != null) 
			leftModeNode = leftModeNode.left;
		return leftModeNode;
	}
	
	//Tìm node con phải cùng của root
	public TreeNode findRightModeNode(TreeNode root) {
		if(root == null)
			return null;
		TreeNode leftModeNode = root;
		while(leftModeNode.left != null) 
			leftModeNode = leftModeNode.right;
		return leftModeNode;
	}
	
	public void ganPhong(TreeNode root1, TreeNode root2) {
//		int maPhong = root1.qLP.getMaPhong();
//		String loaiPhong = root1.qLP.getLoaiPhong();
//		int soGiuong = root1.qLP.getSoGiuong();
//		int soNguoi = root1.qLP.getSoNguoi();
//		float giaTien = root1.qLP.getGiaTien();
		
		root1.qLP.setMaPhong(root2.qLP.getMaPhong());
		root2.qLP.setLoaiPhong(root2.qLP.getLoaiPhong());
		root1.qLP.setSoGiuong(root2.qLP.getSoGiuong());
		root1.qLP.setSoNguoi(root2.qLP.getSoNguoi());
		root1.qLP.setGiaTien(root2.qLP.getGiaTien());
		
//		root2.qLP.setMaPhong(maPhong);
//		root2.qLP.setLoaiPhong(loaiPhong);
//		root2.qLP.setSoGiuong(soGiuong);
//		root2.qLP.setSoNguoi(soNguoi);
//		root2.qLP.setGiaTien(soNguoi);
	}
	
	public TreeNode deleteNode(TreeNode root, int maPhong) throws SQLException {
		DAO dao = new DAO();
		if(root == null)
			return null;
		else {
			//B1: đi tìm node xoá
			if(maPhong < root.qLP.getMaPhong()) 
				root.left = deleteNode(root.left, maPhong);
			else if(maPhong > root.qLP.getMaPhong()) 
				root.right = deleteNode(root.right, maPhong);
			else { //root.value == key ==> xoá root
				//B2: xoá node
//					Kiểm tra xem phòng còn người ở hay không
				if(root.qLP.getSoNguoi() != 0) {
					System.err.println("Phòng còn sinh viên thuê không thể xoá");
				}
				else {
					dao.deletePhong(maPhong);
					//Th1: Node xoá là node lá: 
					if(root.left == null && root.right == null) {
						System.out.println("Xoá thành công");
						return null;
					}
					//TH2.1: Chỉ có 1 con bên trái
					if(root.left != null && root.right == null) {
						System.out.println("Xoá thành công");
						return root.left;
					}
					//TH2.2: Chỉ có 1 con bên phải
					if(root.left == null && root.right != null) {
						System.out.println("Xoá thành công!");
						return root.right;
					}
					//TH3: Tồn tại 2 con
					//Tìm node trái cùng của con bên phải
					TreeNode leftModeNode = findLeftModeNode(root.right);
					ganPhong(root, leftModeNode);
					root.right = deleteNode(root.right, leftModeNode.qLP.getMaPhong());
				}
			}
			return root;
		}
	}
	
	
	public TreeNode updateNode(TreeNode root) throws SQLException {
		DAO dao = new DAO();
		Scanner sc = new Scanner(System.in);
		int maPhong;
		while(true) {
			try {
				System.out.println("Nhập mã phòng bạn muốn chỉnh sửa: ");
				maPhong = Integer.parseInt(sc.next());
				if(setMaPhong(maPhong)) 
					break;
			} catch (Exception e) {
				System.err.println("Bạn đã nhập sai, vui lòng nhập lại");
			}
		}
		if(root == null)
			return null;
		else {
			QuanLyPhong qLP = new QuanLyPhong();
			TreeNode currNode = checkPhong(root, maPhong);
			if(currNode != null) {
				MyBST bST = new MyBST();
				qLP.nhapThongTin(bST.mRoot);
				//Kiểm tra xem số người tối đa của phòng sau khi chỉnh sửa có lớn hơn số người đang ở không
				if(qLP.getSoGiuong()*2 > currNode.qLP.getSoNguoi()) {
					qLP.setSoNguoi(currNode.qLP.getSoNguoi());
					currNode.qLP.setLoaiPhong(qLP.getLoaiPhong());
					currNode.qLP.setSoGiuong(qLP.getSoGiuong());
					currNode.qLP.setGiaTien(qLP.getGiaTien());
					dao.updatePhong(qLP, maPhong);
				}
				else 
					System.err.println("Chỉnh sửa số giường không thể nhỏ hơn số người đang ở trong phòng!");
			} else 
				System.out.println("Không tìm thấy phòng để chỉnh sửa");
			qLP.hienThiTieuDePhong();
			qLP.InOder(root);
			
			return root;
		}
			
			
			
		}
		
		
		
}
