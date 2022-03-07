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
				System.out.println("Nhập mã sinh viên muốn trả: ");
				maSV = sc.nextLine();
				if(node.qLSV.setMaSV(maSV)) {
					NodeDoubleL currNode = dLL.checkSV(node, maSV);
					if(currNode != null) {
						currNode.qLSV.hienThiTieuDe();
						currNode.qLSV.hienThiThongTinSV();
						System.out.println("Nhập lí do muốn trả: ");
						String liDo = sc.nextLine();
						while(true) {
							try {
								System.out.println("Nhập ngày trả: ");
								df.setLenient(false);
								ngayTra = df.parse(sc.nextLine());
								if(ngayTra.after(currNode.qLSV.getNgayVao()))
									break;
								else
									System.err.println("Ngày trả không được trước ngày vào");
							} catch (ParseException e) {
								System.err.println("Vui lòng nhập đúng định dạng (yyyy-MM-dd)");
							}
						}
						int luaChon;
						while(true) {
							try {
								System.out.println("Có chắc chắn trả phòng không: *1. Có || *2. Huỷ");
								luaChon = Integer.parseInt(sc.nextLine());
								if(luaChon == 1 || luaChon == 2)
									break;
								else 
									System.err.println("Bạn đã chọn sai!");
							} catch (Exception e) {
								System.err.println("Bạn đã chọn sai!");
							}
						}
						maPhong = currNode.qLSV.getMaPhong();
						switch (luaChon) {
						case 1: {
							//Chuyển trạng thái sinh viên từ đang thuê sang đã trả
							currNode.qLSV.setTrangThai("Đã trả");
							dao.updateSinhVien(currNode.qLSV, maSV, maPhong);
							//Chuyển trạng thái của chuyển phòng đang thuê sang đã trả
							//Giảm số lượng trong phòng đã trả lại
							TreeNode currTreeNode = qLP.checkPhong(treeNode, maPhong);
							if(currTreeNode != null) {
								currTreeNode.qLP.setSoNguoi(currTreeNode.qLP.getSoNguoi() - 1);
								dao.updatePhong(currTreeNode.qLP, maPhong);
							}
							//Lưu vào danh sách trả phòng
							QuanLyTraPhong qLTraP = new QuanLyTraPhong();
							qLTraP.setMaSV(maSV);
							qLTraP.setMaPhong(maPhong);
							qLTraP.setMaQL(maQL);
							qLTraP.setNgayTra(ngayTra);
							qLTraP.setLiDo(liDo);
							listTraPhong.add(qLTraP);
							dao.insertTraPhong(listTraPhong.get(listTraPhong.size() - 1));
							
							//Hiển thị trả phòng
							hamHienThiTraPhong(listTraPhong);
							System.out.println("Hoàn thành trả phòng!");
							break;
						}
						case 2: {
							System.out.println("Huỷ thao tác");
							break;
						}
						}
						break;
					} else
						System.err.println("Sinh viên này không tồn tại trong kí túc xá");
				}
			} catch (Exception e) {
			}
		}
	}
	
}
