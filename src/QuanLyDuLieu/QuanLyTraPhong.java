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
import QuanLyKTX.DAO;
import QuanLyKTX.TraPhong;

public class QuanLyTraPhong extends TraPhong{
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public QuanLyTraPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuanLyTraPhong(int maPhieu, String maSV, int maPhong, String maQL, Date ngayTra, String liDo) {
		super(maPhieu, maSV, maPhong, maQL, ngayTra, liDo);
		// TODO Auto-generated constructor stub
	}
	
	public void hienThiTraPhong() {
		System.out.printf("|%8s|%8s|%8s|%5s|%10s|%40s|\n", maPhieu, maSV, maPhong, maQL, df.format(ngayTra), liDo);
	}
	
	public void hamHienThiTraPhong(ArrayList<QuanLyTraPhong> listTraPhong) {
		hienThiTieuDeTraPhong();
		for (QuanLyTraPhong list : listTraPhong) {
			list.hienThiTraPhong();
		}
	}
	
	public static void hienThiTieuDeTraPhong() {
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("|Ma Phieu| Ma SV  |Ma Phong|Ma QL| Ngay Tra |                 Li Do                  |");
		System.out.println("--------------------------------------------------------------------------------------");
	}

	public QuanLyTraPhong searchTraPhong(ArrayList<QuanLyTraPhong> listTraPhong, String maSV) {
		for(int i = 0; i < listTraPhong.size(); i++) {
			if(listTraPhong.get(i).getMaSV().equals(maSV))
				return listTraPhong.get(i);
		}
		
		return null;
	}
	
	public void traPhong(TreeNode treeNode, DoubleLinkedList dLL, NodeDoubleL node, String maQL, ArrayList<QuanLyTraPhong> listTraPhong) throws SQLException {
		DAO dao = new DAO();
		Scanner sc = new Scanner(System.in);
		String maSV; int maPhong;
		Date ngayTra;
		QuanLyPhong qLP = new QuanLyPhong();
		while(true) {
			try {
				System.out.println("Nh???p m?? sinh vi??n mu???n tr???: ");
				maSV = sc.nextLine();
				if(node.qLSV.setMaSV(maSV)) {
					NodeDoubleL currNode = dLL.checkSV(node, maSV);
					if(currNode != null) {
						currNode.qLSV.hienThiTieuDe();
						currNode.qLSV.hienThiThongTinSV();
						System.out.println("Nh???p l?? do mu???n tr???: ");
						String liDo = sc.nextLine();
						while(true) {
							try {
								System.out.println("Nh???p ng??y tr???: ");
								df.setLenient(false);
								ngayTra = df.parse(sc.nextLine());
								if(ngayTra.after(currNode.qLSV.getNgayVao()))
									break;
								else
									System.err.println("Ng??y tr??? kh??ng ???????c tr?????c ng??y v??o");
							} catch (ParseException e) {
								System.err.println("Vui l??ng nh???p ????ng ?????nh d???ng (yyyy-MM-dd)");
							}
						}
						int luaChon;
						while(true) {
							try {
								System.out.println("C?? ch???c ch???n tr??? ph??ng kh??ng: *1. C?? || *2. Hu???");
								luaChon = Integer.parseInt(sc.nextLine());
								if(luaChon == 1 || luaChon == 2)
									break;
								else 
									System.err.println("B???n ???? ch???n sai!");
							} catch (Exception e) {
								System.err.println("B???n ???? ch???n sai!");
							}
						}
						maPhong = currNode.qLSV.getMaPhong();
						switch (luaChon) {
						case 1: {
							//Chuy???n tr???ng th??i sinh vi??n t??? ??ang thu?? sang ???? tr???
							currNode.qLSV.setTrangThai("???? tr???");
							dao.updateSinhVien(currNode.qLSV, maSV, maPhong);
							//Chuy???n tr???ng th??i c???a chuy???n ph??ng ??ang thu?? sang ???? tr???
							//Gi???m s??? l?????ng trong ph??ng ???? tr??? l???i
							TreeNode currTreeNode = qLP.checkPhong(treeNode, maPhong);
							if(currTreeNode != null) {
								currTreeNode.qLP.setSoNguoi(currTreeNode.qLP.getSoNguoi() - 1);
								dao.updatePhong(currTreeNode.qLP, maPhong);
							}
							//L??u v??o danh s??ch tr??? ph??ng
							QuanLyTraPhong qLTraP = new QuanLyTraPhong();
							qLTraP.setMaSV(maSV);
							qLTraP.setMaPhong(maPhong);
							qLTraP.setMaQL(maQL);
							qLTraP.setNgayTra(ngayTra);
							qLTraP.setLiDo(liDo);
							listTraPhong.add(qLTraP);
							dao.insertTraPhong(listTraPhong.get(listTraPhong.size() - 1));
							
							//Hi???n th??? tr??? ph??ng
							hamHienThiTraPhong(listTraPhong);
							System.out.println("Ho??n th??nh tr??? ph??ng!");
							break;
						}
						case 2: {
							System.out.println("Hu??? thao t??c");
							break;
						}
						}
						break;
					} else
						System.err.println("Sinh vi??n n??y kh??ng t???n t???i trong k?? t??c x??");
				}
			} catch (Exception e) {
			}
		}
	}
	
}
