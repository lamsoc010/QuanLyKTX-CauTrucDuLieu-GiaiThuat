package QuanLyDuLieu;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import CauTrucLuuTru.DoubleLinkedList;
import CauTrucLuuTru.NodeDoubleL;
import CauTrucLuuTru.TreeNode;
import QuanLyKTX.ChuyenPhong;
import QuanLyKTX.DAO;

public class QuanLyChuyenPhong extends ChuyenPhong{
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public QuanLyChuyenPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuanLyChuyenPhong(int maPhieu, String maSV, int maPhongCu, int maPhongMoi, String maQL, Date ngayChuyen,
			String liDo) {
		super(maPhieu, maSV, maPhongCu, maPhongMoi, maQL, ngayChuyen, liDo);
		// TODO Auto-generated constructor stub
	}
	
	public void hienThiChuyenPhong() {
		System.out.printf("|%8s|%8s|%5s|%6s|%5s|%11s|%40s|\n",maPhieu, maSV, maPhongCu, maPhongMoi, maQL, df.format(ngayChuyen), liDo);
	}
	
	public static void hienThiTieuDeChuyenPhong() {
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("|Ma Phieu| Ma SV  |MP Cu|MP Moi|Ma QL|Ngay Chuyen|                  Li Do                 |");
		System.out.println("-------------------------------------------------------------------------------------------");
	}
	
	public void hamHienThiChuyenPhong(ArrayList<QuanLyChuyenPhong> listChuyenPhong) {
		hienThiTieuDeChuyenPhong();
		for (QuanLyChuyenPhong list : listChuyenPhong) {
			list.hienThiChuyenPhong();
		}
	}
	
	public QuanLyChuyenPhong searchChuyenPhong(ArrayList<QuanLyChuyenPhong> listChuyenPhong, String maSV) {
		for(int i = 0; i < listChuyenPhong.size(); i++) {
			if(listChuyenPhong.get(i).getMaSV().equalsIgnoreCase(maSV))
				return listChuyenPhong.get(i);
		}
		
		return null;
	}
	
	public void chuyenPhong(TreeNode treeNode, DoubleLinkedList dLL, NodeDoubleL node, String maQL, ArrayList<QuanLyChuyenPhong> listChuyenPhong) throws SQLException {
		DAO dao = new DAO();
		Scanner sc = new Scanner(System.in);
		String maSV; int maPhong;
		Date ngayChuyen; String liDo;
		QuanLyPhong qLP = new QuanLyPhong();
		QuanLyChuyenPhong qLCP = new QuanLyChuyenPhong();
		NodeDoubleL currNode;
		TreeNode currTreeNode;
		while(true) {
			try {
				System.out.println("Nhập mã sinh viên muốn chuyển: ");
				maSV = sc.nextLine();
				if(node.qLSV.setMaSV(maSV)) {
					currNode = dLL.checkSV(node, maSV);
					if(currNode != null) {
						currNode.qLSV.hienThiTieuDe();
						currNode.qLSV.hienThiThongTinSV();
						break;
					} else
						System.err.println("Sinh viên này không tồn tại trong kí túc xá");
				}
			} catch (Exception e) {
			}
		}
		while(true) {
			try {
				System.out.println("Nhập mã phòng muốn chuyển tới: ");
				maPhong = Integer.parseInt(sc.nextLine());
				if(qLP.setMaPhong(maPhong)) {
					//Check xem phòng đó có tồn tại hay không và đã đủ người hay chưa
					currTreeNode = qLP.checkFullPhong(treeNode, maPhong);
					if(currTreeNode != null) {
						if(currNode.qLSV.getMaPhong() == maPhong)
							System.err.println("Bạn đang ở phòng này rồi!");
						else
							break;
					}
				}
			} catch (Exception e) {
				System.err.println("Bạn đã nhập sai, vui lòng nhập lại");
			}
		}
		while(true) {
			try {
				System.out.println("Nhập ngày chuyển: ");
				df.setLenient(false);
				ngayChuyen = df.parse(sc.nextLine());
				if(ngayChuyen.after(currNode.qLSV.getNgayVao()))
					break;
				else
					System.err.println("Ngày chuyển không được nhỏ hơn ngày vào");
			} catch (ParseException e) {
				System.err.println("Vui lòng nhập đúng định dạng (yyyy-MM-dd)");
			}
		}
		
		System.out.println("Nhập lí do chuyển: ");
		liDo = sc.nextLine();
		
		if(currTreeNode != null) {
			//Lấy ra mã phòng hiện tại
			int maPhongCu = currNode.qLSV.getMaPhong();
			//Xét lại mã phòng mới sau khi chuyển
			currNode.qLSV.setMaPhong(currTreeNode.qLP.getMaPhong());
			dao.updateSinhVien(currNode.qLSV, maSV, maPhong);
			
			//Tăng giảm số lượng người trong phòng
			currTreeNode.qLP.setSoNguoi(currTreeNode.qLP.getSoNguoi() + 1);
			dao.updatePhong(currTreeNode.qLP, maPhong);
			
			//Sét lại số người trong phòng
			TreeNode phongCuNode;
			phongCuNode = qLP.checkPhong(treeNode, maPhongCu);
			phongCuNode.qLP.setSoNguoi(phongCuNode.qLP.getSoNguoi() - 1);
			dao.updatePhong(phongCuNode.qLP, maPhongCu);
			
			//Lưu vào danh sách chuyển phong
			qLCP.setMaSV(maSV);
			qLCP.setMaPhongCu(maPhongCu);
			qLCP.setMaPhongMoi(currTreeNode.qLP.getMaPhong());
			qLCP.setMaQL(maQL);
			qLCP.setNgayChuyen(ngayChuyen);
			qLCP.setLiDo(liDo);
			listChuyenPhong.add(qLCP);
			dao.insertChuyenphong(listChuyenPhong.get(listChuyenPhong.size() - 1));
			//Hiển thị sinh viên sau khi chuyển!
			currNode.qLSV.hienThiTieuDe();
			currNode.qLSV.hienThiThongTinSV();
			System.out.println("Chuyển phòng thành công!");
		}
	}
	
}
