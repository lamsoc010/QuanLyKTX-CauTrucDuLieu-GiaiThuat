package QuanLyKTX;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

import CauTrucLuuTru.DoubleLinkedList;
import CauTrucLuuTru.LinkedList;
import CauTrucLuuTru.MyBST;
import CauTrucLuuTru.NodeDoubleL;
import CauTrucLuuTru.NodeLinkedList;
import CauTrucLuuTru.TreeNode;
import QuanLyDuLieu.QuanLyChuyenPhong;
import QuanLyDuLieu.QuanLyKyLuat;
import QuanLyDuLieu.QuanLyPhong;
import QuanLyDuLieu.QuanLyQuanLy;
import QuanLyDuLieu.QuanLySinhVien;
import QuanLyDuLieu.QuanLyThuePhong;
import QuanLyDuLieu.QuanLyTraPhong;

public class Main {
	static QuanLyPhong qLP = new QuanLyPhong();
	static QuanLySinhVien qLSV = new QuanLySinhVien();
	static QuanLyQuanLy qLQL = new QuanLyQuanLy();
	static QuanLyThuePhong qLTP = new QuanLyThuePhong();
	static QuanLyKyLuat qLKL = new QuanLyKyLuat();
	static QuanLyTraPhong qLTraP = new QuanLyTraPhong();
	static QuanLyChuyenPhong qLCP = new QuanLyChuyenPhong();
	static MyBST bST = new MyBST();
	static TreeNode treeNode = new TreeNode(qLP);
	static DoubleLinkedList dLL = new DoubleLinkedList();
	static NodeDoubleL nodeDLL = new NodeDoubleL(qLSV);
	static LinkedList lL = new LinkedList();
	static NodeLinkedList nodeLL = new NodeLinkedList(qLQL);
	static ArrayList<QuanLyTraPhong> listTraPhong = new ArrayList<QuanLyTraPhong>();
	static ArrayList<QuanLyChuyenPhong> listChuyenPhong = new ArrayList<QuanLyChuyenPhong>();
	
	public static void main(String[] args) throws SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		DAO dao = new DAO();
		dao.getListPhong(bST);
		dao.getListSinhVien(dLL);
		dao.getListKyLuat(bST);
		dao.getListThuePhong(bST);
		dao.getListQuanLy(lL);
		dao.getListTraPhong(listTraPhong);
		dao.getListChuyenPhong(listChuyenPhong);
		String maQL; 
		do {
			maQL = qLQL.dangNhapQuanLy(lL);
			if(maQL != null) {
				int chon;
				do {
					Menu.menuKTX();
					while(true) {
						try {
							chon = Integer.parseInt(sc.nextLine());
							if(chon >= 0 && chon <=7)
								break;
							else 
								System.out.println("Bạn đã chọn sai, vui lòng chọn lại");
						} catch (Exception e) {
							System.err.println("Bạn đã chọn sai, vui lòng chọn lại");
						}
					}
					
					switch (chon) {
					case 1: {
						int luaChon;
						do {
							Menu.menuPhong();
							while(true) {
								try {
									luaChon = Integer.parseInt(sc.nextLine());
									if(luaChon >= 0 && luaChon <=5)
										break;
									else 
										System.out.println("Bạn đã chọn sai, vui lòng chọn lại");
								} catch (Exception e) {
									System.err.println("Bạn đã chọn sai, vui lòng chọn lại");
								}
							}
							switch (luaChon) {
							case 1: {
								qLP.hamNhapThongTin(bST);
								qLP.hienThiTieuDePhong();
								qLP.InOder(bST.mRoot);								
								break;
							}
							case 2: {
								qLP.hienThiTieuDePhong();
								qLP.InOder(bST.mRoot);
								break;
							}
							case 3: {
								qLP.updateNode(bST.mRoot);
								break;
							}
							case 4: {
								int maPhong;
								while(true) {
									try {
										System.out.println("Nhập mã phòng bạn xoá: ");
										maPhong = Integer.parseInt(sc.nextLine());
										if(qLP.setMaPhong(maPhong)) {
											TreeNode currNode = qLP.checkPhong(bST.mRoot, maPhong);
											if(currNode == null) 
												System.out.println("Mã phòng không tồn tại");
											break;
										}
									} catch (Exception e) {
										System.err.println("Bạn đã nhập sai, vui lòng nhập lại");
									}
								}
								bST.mRoot = qLP.deleteNode(bST.mRoot, maPhong);
								qLP.hienThiTieuDePhong();
								qLP.InOder(bST.mRoot);
								break;
							}
							case 5: {
								qLP.searchBST(bST.mRoot);
								break;
							}
							case 0:
								System.out.println("Kết thúc chương trình");
							}
							
						} while (luaChon != 0);
						break;
					}
					//Hết case1 menu
					case 2: {
						int luaChon;
						do {
							Menu.menuSinhVien();
							while(true) {
								try {
									luaChon = Integer.parseInt(sc.nextLine());
									if(luaChon >= 0 && luaChon <=8)
										break;
									else 
										System.out.println("Bạn đã chọn sai, vui lòng chọn lại");
								} catch (Exception e) {
									System.err.println("Bạn đã chọn sai, vui lòng chọn lại");
								}
							}
							
							switch (luaChon) {
							case 1: {
								dLL.hamHienThiListSinhVien();
								break;
							}
							case 2: {
								dLL.updateSinhVien(dLL, nodeDLL);
								break;
							}
							case 3: {
								dLL.searchSV(nodeDLL);
								break;
							}
							case 4: {
								Menu.menuSortSinhVien();
								while(true) {
									try {
										luaChon = Integer.parseInt(sc.nextLine());
										if(luaChon >= 0 && luaChon <= 4)
											break;
										else
											System.err.println("Bạn đã chọn sai vui lòng chọn lại!");
									} catch (Exception e) {
										System.err.println("Bạn đã chọn sai vui lòng chọn lại!");
									}
								}
								switch (luaChon) {
								case 1: {
									dLL.sortName();
									break;
								}
								case 2: {
									dLL.selectionSortMaPhong();
									break;
								}
								case 3: {
									dLL.sortViPham();
//									dLL.insertionSortViPham(nodeDLL);
									break;
								}
								case 4: {
									dLL.sortNgayVao();
									break;
								}
								}
								break;
							}
							case 5: {
								dLL.showSinhVienInPhong(bST.mRoot, nodeDLL);
								break;
							}
							case 6: {
								dLL.checkBirthDayInMonth(nodeDLL);
								break;
							}
							}
						} while (luaChon != 0);
						break;
					} //Hết case 2
					
					case 3: {
						int luaChon;
						do {
							Menu.menuThuePhong();
							while(true) {
								try {
									luaChon = Integer.parseInt(sc.nextLine());
									if(luaChon >= 0 && luaChon <=3)
										break;
									else 
										System.out.println("Bạn đã chọn sai, vui lòng chọn lại");
								} catch (Exception e) {
									System.err.println("Bạn đã chọn sai, vui lòng chọn lại");
								}
							}
							switch (luaChon) {
							case 1: {
								qLTP.hamNhapThuePhong(bST, dLL, nodeDLL, maQL);
								qLTP.hienThiTieuDeThuePhong();
								qLTP.InOder(bST.mRootTP);
								break;
							}
							case 2: {
								qLTP.hienThiTieuDeThuePhong();
								qLTP.InOder(bST.mRootTP);
								break;
							}
							case 3: {
								qLTP.taiThuePhong(bST, bST.mRoot, dLL, nodeDLL, maQL);
								break;
							}
							}
						} while (luaChon != 0);
						break;
					} //Hết case 3
					case 4: {
						int luaChon;
						do {
							Menu.menuKyLuat();
							while(true) {
								try {
									luaChon = Integer.parseInt(sc.nextLine());
									if(luaChon >= 0 && luaChon <=3)
										break;
									else 
										System.out.println("Bạn đã chọn sai, vui lòng chọn lại");
								} catch (Exception e) {
									System.err.println("Bạn đã chọn sai, vui lòng chọn lại");
								}
							}
							switch (luaChon) {
							case 1: {
								qLKL.hamNhapThongTinKL(bST, dLL, lL, nodeDLL, maQL);
								break;
							}
							case 2: {
								qLKL.hienThiTieuDeKyLuat();
								qLKL.InOder(bST.mRootKL);
								break;
							}
							case 3: {
								dLL.showSinhVienKyLuat(nodeDLL);
								break;
							}
							}
						} while (luaChon != 0);
						break;
					} //Hết case 4
					case 5: {
						int luaChon;
						do {
							Menu.menuChuyenTraPhong();
							while(true) {
								try {
									luaChon = Integer.parseInt(sc.nextLine());
									if(luaChon >= 0 && luaChon <= 4)
										break;
									else
										System.err.println("Bạn đã chọn sai vui lòng chọn lại!");
								} catch (Exception e) {
									System.err.println("Bạn đã chọn sai vui lòng chọn lại!");
								}
							}
							switch (luaChon) {
							case 1: {
								qLCP.chuyenPhong(bST.mRoot, dLL, nodeDLL, maQL, listChuyenPhong);
								break;
							}
							case 2: {
								qLCP.hamHienThiChuyenPhong(listChuyenPhong);
								break;
							}
							case 3: {
								qLTraP.traPhong(bST.mRoot, dLL, nodeDLL, maQL, listTraPhong);
								break;
							}
							case 4: {
								qLTraP.hamHienThiTraPhong(listTraPhong);
								break;
							}
							}
						} while (luaChon != 0);
						break;
					} //Hết case 5
					case 6: {
						lL.doiMatKhau(maQL);
						break;
					}//Hết case 6
					case 7: {
						dLL.xuatFileSinhVien(nodeDLL);
						break;
					} //Hết case 7
					}
				} while (chon != 0);
			}
		} while (maQL == null);
		
		
	}

}
