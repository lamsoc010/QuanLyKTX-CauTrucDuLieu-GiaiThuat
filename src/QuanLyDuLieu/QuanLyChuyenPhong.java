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
				System.out.println("Nh???p m?? sinh vi??n mu???n chuy???n: ");
				maSV = sc.nextLine();
				if(node.qLSV.setMaSV(maSV)) {
					currNode = dLL.checkSV(node, maSV);
					if(currNode != null) {
						currNode.qLSV.hienThiTieuDe();
						currNode.qLSV.hienThiThongTinSV();
						break;
					} else
						System.err.println("Sinh vi??n n??y kh??ng t???n t???i trong k?? t??c x??");
				}
			} catch (Exception e) {
			}
		}
		while(true) {
			try {
				System.out.println("Nh???p m?? ph??ng mu???n chuy???n t???i: ");
				maPhong = Integer.parseInt(sc.nextLine());
				if(qLP.setMaPhong(maPhong)) {
					//Check xem ph??ng ???? c?? t???n t???i hay kh??ng v?? ???? ????? ng?????i hay ch??a
					currTreeNode = qLP.checkFullPhong(treeNode, maPhong);
					if(currTreeNode != null) {
						if(currNode.qLSV.getMaPhong() == maPhong)
							System.err.println("B???n ??ang ??? ph??ng n??y r???i!");
						else
							break;
					}
				}
			} catch (Exception e) {
				System.err.println("B???n ???? nh???p sai, vui l??ng nh???p l???i");
			}
		}
		while(true) {
			try {
				System.out.println("Nh???p ng??y chuy???n: ");
				df.setLenient(false);
				ngayChuyen = df.parse(sc.nextLine());
				if(ngayChuyen.after(currNode.qLSV.getNgayVao()))
					break;
				else
					System.err.println("Ng??y chuy???n kh??ng ???????c nh??? h??n ng??y v??o");
			} catch (ParseException e) {
				System.err.println("Vui l??ng nh???p ????ng ?????nh d???ng (yyyy-MM-dd)");
			}
		}
		
		System.out.println("Nh???p l?? do chuy???n: ");
		liDo = sc.nextLine();
		
		if(currTreeNode != null) {
			//L???y ra m?? ph??ng hi???n t???i
			int maPhongCu = currNode.qLSV.getMaPhong();
			//X??t l???i m?? ph??ng m???i sau khi chuy???n
			currNode.qLSV.setMaPhong(currTreeNode.qLP.getMaPhong());
			dao.updateSinhVien(currNode.qLSV, maSV, maPhong);
			
			//T??ng gi???m s??? l?????ng ng?????i trong ph??ng
			currTreeNode.qLP.setSoNguoi(currTreeNode.qLP.getSoNguoi() + 1);
			dao.updatePhong(currTreeNode.qLP, maPhong);
			
			//S??t l???i s??? ng?????i trong ph??ng
			TreeNode phongCuNode;
			phongCuNode = qLP.checkPhong(treeNode, maPhongCu);
			phongCuNode.qLP.setSoNguoi(phongCuNode.qLP.getSoNguoi() - 1);
			dao.updatePhong(phongCuNode.qLP, maPhongCu);
			
			//L??u v??o danh s??ch chuy???n phong
			qLCP.setMaSV(maSV);
			qLCP.setMaPhongCu(maPhongCu);
			qLCP.setMaPhongMoi(currTreeNode.qLP.getMaPhong());
			qLCP.setMaQL(maQL);
			qLCP.setNgayChuyen(ngayChuyen);
			qLCP.setLiDo(liDo);
			listChuyenPhong.add(qLCP);
			dao.insertChuyenphong(listChuyenPhong.get(listChuyenPhong.size() - 1));
			//Hi???n th??? sinh vi??n sau khi chuy???n!
			currNode.qLSV.hienThiTieuDe();
			currNode.qLSV.hienThiThongTinSV();
			System.out.println("Chuy???n ph??ng th??nh c??ng!");
		}
	}
	
}
