package CauTrucLuuTru;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Node;

import QuanLyDuLieu.QuanLyKyLuat;
import QuanLyDuLieu.QuanLyPhong;
import QuanLyDuLieu.QuanLySinhVien;
import QuanLyKTX.DAO;

public class DoubleLinkedList {
	public int size;
	public NodeDoubleL head;
	public NodeDoubleL tail;

	public DoubleLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	public int size() {
		return size;
	}

	// Tìm sinh viên cuối cùng.
	public NodeDoubleL NodeEnd(NodeDoubleL node) {
		NodeDoubleL currNode = head;
		while (currNode != null) {
			if (currNode.next == null) {
				return currNode;
			}
			currNode = currNode.next;
		}
		return node;
	}

	// Check xem sinh viên đã tồn tại trong danh sách hay chưa
	public NodeDoubleL checkSV(NodeDoubleL node, String maSV) {
		NodeDoubleL currNode = head;
		while (currNode != null) {
			if (currNode.qLSV.getMaSV().equalsIgnoreCase(maSV)
					&& currNode.qLSV.getTrangThai().equalsIgnoreCase("Đang thuê"))
				return currNode;
			currNode = currNode.next;
		}
		return null;
	}

//	Tìm sinh viên theo mã sinh viên, nếu tìm thấy thì return ra sinh viên đó, còn không thì return null;
	public NodeDoubleL searchSV(NodeDoubleL node) {
		if (head == null)
			return null;
		else {
			Scanner sc = new Scanner(System.in);
			String maSV;
			while (true) {
				try {
					System.out.println("Nhập mã sinh viên");
					maSV = sc.nextLine();
					if (node.qLSV.setMaSV(maSV))
						break;
				} catch (Exception e) {
				}
			}

			NodeDoubleL currNode = checkSV(node, maSV);
			if (currNode != null) {
				System.out.println("Sinh viên cần tìm: ");
				currNode.qLSV.hienThiTieuDe();
				currNode.qLSV.hienThiThongTinSV();
			} else
				System.err.println("Không tìm thấy sinh viên theo mã sinh viên");

		}
		return null;

	}

	public NodeDoubleL search(NodeDoubleL node, String maSV) {
		if (head == null)
			return null;
		else {
			NodeDoubleL currNode = head;
			int index = -1;
			while (currNode != null) {
				if (currNode.qLSV.getMaSV().equalsIgnoreCase(maSV)) {
					return currNode;
				}
				currNode = currNode.next;
			}
			return null;
		}
	}

	public void insertHead(NodeDoubleL node) {
		if (head == null)
			head = node;
		else {
			NodeDoubleL temp = head;
			head = node;
			temp.prev = node;
			node.next = temp;
		}
	}

	public void insertTail(NodeDoubleL node) {
		tail = head;
		if (head == null)
			head = node;
		else {
			while (tail.next != null) {
				tail = tail.next;
			}
			NodeDoubleL temp = tail;
			tail = node;
			node.prev = temp;
			temp.next = node;
		}
	}

	public void insertAt(NodeDoubleL node, int index) {
		if (index == 0)
			insertHead(node);
		else {
			NodeDoubleL currNode = head;
			int count = 0;
			while (currNode != null) {
				count++;
				if (count == index) {
					currNode.next.prev = node;
					node.next = currNode.next;
					currNode.next = node;
					node.prev = currNode;
					break;
				}
				currNode = currNode.next;
			}

		}
	}

	public void removeHead(NodeDoubleL node) {
		if (head == null) {
			System.out.println("List rỗng không thể xoá");
		} else {
			head = head.next;
			hamHienThiListSinhVien();
		}
	}

	public void removeTail(NodeDoubleL node) {
		tail = head;
		if (head == null) {
			System.out.println("List rỗng không thể xoá");
		} else {
			NodeDoubleL currNode = null;
			while (tail.next != null) {
				currNode = tail;
				tail = tail.next;
			}
			if (currNode == null) {
				head = head.next;
			} else {
				currNode.next = tail.next;
			}
		}
	}

	public void removeAt(NodeDoubleL node, int index) {
		if (head == null)
			System.out.println("List rỗng không thể xoá");
		else {
			if (index == 0)
				removeHead(node);
			else {
				NodeDoubleL currNode = head;
				NodeDoubleL prevNode = null;
				int count = 0;
				boolean bIsFound = false;
				while (currNode != null) {
					if (count == index) {
						bIsFound = true;
						break;
					}
					prevNode = currNode;
					currNode = currNode.next;
					count++;
				}
				if (bIsFound) {
					prevNode.next = currNode.next;
//					currNode.next.prev = prevNode;
				} else {
					head = head.next;
				}
				hamHienThiListSinhVien();
			}

		}
	}

	public void removeByMaSV(NodeDoubleL node) throws SQLException {
		DAO dao = new DAO();
		Scanner sc = new Scanner(System.in);
		String maSV;
		while (true) {
			try {
				System.out.println("Nhập mã sinh viên");
				maSV = sc.nextLine();
				if (maSV.length() != 8)
					System.out.println("Mã SV phải gồm 8 kí tự");
				else
					break;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		NodeDoubleL currNode = head;
		int count = 0;
		int index = -1;
		while (currNode != null) {
			if (currNode.qLSV.getMaSV().equalsIgnoreCase(maSV)) {
				removeAt(node, count);
				dao.deleteSinhVien(maSV);
				index = 1;
			}
			currNode = currNode.next;
			count++;
		}
		if (index == -1)
			System.out.println("Không tìm thấy mã sinh viên để xoá");
	}

	public void swapSV(NodeDoubleL node1, NodeDoubleL node2) {
		String maSV = node1.qLSV.getMaSV();
		String hoTen = node1.qLSV.getHoTen();
		String SDT = node1.qLSV.getSDT();
		String gioiTinh = node1.qLSV.getGioiTinh();
		String diaChi = node1.qLSV.getDiaChi();
		Date ngaySinh = (Date) node1.qLSV.getNgaySinh();
		String nganh = node1.qLSV.getNganh();
		String khoa = node1.qLSV.getKhoa();
		Date ngayVao = (Date) node1.qLSV.getNgayVao();
		int maPhong = node1.qLSV.getMaPhong();
		int soLanViPham = node1.qLSV.getSoLanViPham();
		String trangThai = node1.qLSV.getTrangThai();

		node1.qLSV.setMaSV(node2.qLSV.getMaSV());
		node1.qLSV.setHoTen(node2.qLSV.getHoTen());
		node1.qLSV.setSDT(node2.qLSV.getSDT());
		node1.qLSV.setGioiTinh(node2.qLSV.getGioiTinh());
		node1.qLSV.setDiaChi(node2.qLSV.getDiaChi());
		node1.qLSV.setNgaySinh(node2.qLSV.getNgaySinh());
		node1.qLSV.setNganh(node2.qLSV.getNganh());
		node1.qLSV.setKhoa(node2.qLSV.getKhoa());
		node1.qLSV.setNgayVao(node2.qLSV.getNgayVao());
		node1.qLSV.setMaPhong(node2.qLSV.getMaPhong());
		node1.qLSV.setSoLanViPham(node2.qLSV.getSoLanViPham());
		node1.qLSV.setTrangThai(node2.qLSV.getTrangThai());

		node2.qLSV.setMaSV(maSV);
		node2.qLSV.setHoTen(hoTen);
		node2.qLSV.setSDT(SDT);
		node2.qLSV.setGioiTinh(gioiTinh);
		node2.qLSV.setDiaChi(diaChi);
		node2.qLSV.setNgaySinh(ngaySinh);
		node2.qLSV.setNganh(nganh);
		node2.qLSV.setKhoa(khoa);
		node2.qLSV.setNgayVao(ngayVao);
		node2.qLSV.setMaPhong(maPhong);
		node2.qLSV.setSoLanViPham(soLanViPham);
		node2.qLSV.setTrangThai(trangThai);
	}

	public void ganSV(NodeDoubleL node1, NodeDoubleL node2) {
		node1.qLSV.setMaSV(node2.qLSV.getMaSV());
		node1.qLSV.setHoTen(node2.qLSV.getHoTen());
		node1.qLSV.setSDT(node2.qLSV.getSDT());
		node1.qLSV.setDiaChi(node2.qLSV.getDiaChi());
		node1.qLSV.setNgaySinh(node2.qLSV.getNgaySinh());
		node1.qLSV.setNganh(node2.qLSV.getNganh());
		node1.qLSV.setKhoa(node2.qLSV.getKhoa());
		node1.qLSV.setNgayVao(node2.qLSV.getNgayVao());
		node1.qLSV.setMaPhong(node2.qLSV.getMaPhong());
		node1.qLSV.setSoLanViPham(node2.qLSV.getSoLanViPham());
	}

	//Sắp xếp theo số lần vi phạm với thuật toán insertionsort(sắp xếp chèn)
	public void insertionSortViPham(NodeDoubleL node) {
		if(head == null)
			System.out.println("List rỗng không thể sắp xếp!");
		else {
			NodeDoubleL currNode = head.next;
			while(currNode != null) {
				NodeDoubleL tempNode = currNode;
				NodeDoubleL prevNode = currNode.prev;
				while(prevNode != null && prevNode.qLSV.getSoLanViPham() > tempNode.qLSV.getSoLanViPham()) {
					ganSV(prevNode.next, prevNode);
					prevNode = prevNode.prev;
				}
				ganSV(prevNode.next, tempNode);
				currNode = currNode.next;
			}
		}
	}

//	Sắp xếp theo số phòng tăng dần với thuật toàn selectionsort(sắp xếp chọn)
	public void selectionSortMaPhong() {
		if (head == null)
			System.out.println("List rỗng không thể sắp xếp");
		NodeDoubleL currNode = head;
		while (currNode != null) {
			NodeDoubleL minNode = currNode;
			NodeDoubleL prevNode = currNode.next;
			while (prevNode != null) {
				if (prevNode.qLSV.getMaPhong() < minNode.qLSV.getMaPhong())
					minNode = prevNode;
				prevNode = prevNode.next;
			}
			if (minNode.qLSV.getMaPhong() != currNode.qLSV.getMaPhong()) {
				swapSV(minNode, currNode);
			}
			currNode = currNode.next;
		}
		hamHienThiListSinhVien();
	}

	public void sortViPham() {
		if (head == null) {
			System.out.println("List rỗng không thể sắp xếp!");
		} else {
			NodeDoubleL currNode = head;
			NodeDoubleL prevNode = null;
			while (currNode != null) {
				prevNode = currNode.next;
				while (prevNode != null) {
					if (currNode.qLSV.getTrangThai().equalsIgnoreCase("đang thuê")
							&& prevNode.qLSV.getTrangThai().equalsIgnoreCase("đang thuê"))
						if (currNode.qLSV.getSoLanViPham() > prevNode.qLSV.getSoLanViPham())
							swapSV(currNode, prevNode);

					prevNode = prevNode.next;
				}
				currNode = currNode.next;
			}
			hamHienThiListSinhVien();
		}
	}

	public void sortName() {
		if (head == null) {
			System.out.println("List rỗng không thể sắp xếp!");
		} else {
			NodeDoubleL currNode = head;
			NodeDoubleL prevNode = null;
			String name;
			int age;
			double mark;
			while (currNode != null) {
				prevNode = currNode.next;
				while (prevNode != null) {
					if (currNode.qLSV.getHoTen().compareToIgnoreCase(prevNode.qLSV.getHoTen()) > 0) {
						swapSV(currNode, prevNode);
					}
					prevNode = prevNode.next;
				}
				currNode = currNode.next;
			}
			hamHienThiListSinhVien();
		}
	}
	
	public void sortNgayVao() {
		if (head == null) {
			System.out.println("List rỗng không thể sắp xếp!");
		} else {
			NodeDoubleL currNode = head;
			NodeDoubleL prevNode = null;
			String name;
			int age;
			double mark;
			while (currNode != null) {
				prevNode = currNode.next;
				while (prevNode != null) {
					if (currNode.qLSV.getNgayVao().after(prevNode.qLSV.getNgayVao())) {
						swapSV(currNode, prevNode);
					}
					prevNode = prevNode.next;
				}
				currNode = currNode.next;
			}
			hamHienThiListSinhVien();
		}
	}
	
	public void updateSinhVien(DoubleLinkedList dLL, NodeDoubleL node) throws SQLException {

		if (head == null)
			System.out.println("Danh sách sinh viên rỗng không thể chỉnh sửa");
		else {
			Scanner sc = new Scanner(System.in);
			QuanLySinhVien qLSV = new QuanLySinhVien();
			String maSV;
			while (true) {
				System.out.println("Nhập mã sinh viên muốn chỉnh sửa: ");
				maSV = sc.nextLine();
				boolean check = qLSV.setMaSV(maSV);
				if (check)
					break;
			}
			DAO dao = new DAO();
			NodeDoubleL currNode = checkSV(node, maSV);
			if (currNode != null) {
				qLSV.nhapThongTin(dLL, node);

				currNode.qLSV.setHoTen(qLSV.getHoTen());
				currNode.qLSV.setSDT(qLSV.getSDT());
				currNode.qLSV.setGioiTinh(qLSV.getGioiTinh());
				currNode.qLSV.setDiaChi(qLSV.getDiaChi());
				currNode.qLSV.setNgaySinh(qLSV.getNgaySinh());
				currNode.qLSV.setNganh(qLSV.getNganh());
				currNode.qLSV.setKhoa(qLSV.getKhoa());
//				currNode.qLSV.setNgayVao(qLSV.getNgayVao());
				qLSV.setSoLanViPham(currNode.qLSV.getSoLanViPham());
				dao.updateSinhVien(qLSV, maSV, currNode.qLSV.getMaPhong());
				System.out.println("Cập nhập thành công");
				hamHienThiListSinhVien();
			} else
				System.out.println("Không tìm thấy sinh viên theo mã sinh viên");
		}
	}

	// Hiển thị danh sách những sinh viên đã bị kĩ luật.
	public void showSinhVienKyLuat(NodeDoubleL node) {
		if (node == null)
			return;
		else {
			NodeDoubleL currNode = head;
			currNode.qLSV.hienThiTieuDe();
			while (currNode != null) {
				if (currNode.qLSV.getSoLanViPham() >= 1)
					currNode.qLSV.hienThiThongTinSV();
				currNode = currNode.next;
			}
		}
	}

	// Hiển thị danh sách sinh viên ở 1 phòng nào đó
	public void showSinhVienInPhong(TreeNode root, NodeDoubleL node) {
		Scanner sc = new Scanner(System.in);
		int maPhong;
		QuanLyPhong qLP = new QuanLyPhong();
		while (true) {
			try {
				System.out.println("Nhập mã phòng muốn tìm: ");
				maPhong = Integer.parseInt(sc.next());
				if (qLP.setMaPhong(maPhong))
					break;
			} catch (Exception e) {
				System.err.println("Bạn đã nhập sai vui lòng nhập lại");
			}
		}
		TreeNode currTreeNode = qLP.checkPhong(root, maPhong);
		int index = -1;
		if (currTreeNode != null) {
			NodeDoubleL currNode = head;
			currNode.qLSV.hienThiTieuDe();
			while (currNode != null) {
				if (currNode.qLSV.getMaPhong() == maPhong) {
					currNode.qLSV.hienThiThongTinSV();
					index = 1;
				}
				currNode = currNode.next;
			}
			if (index == -1)
				System.err.println("Phòng này chưa có sinh viên nào ở!");
		} else
			System.err.println("Phòng không tồn tại");
	}

	public void checkBirthDayInMonth(NodeDoubleL node) {
		NodeDoubleL currNode = head;
		Calendar c = Calendar.getInstance();
		java.util.Date toDay = null;
		toDay = c.getTime();
		int index = -1;
		int count = 0;
		currNode.qLSV.hienThiTieuDe();
		while(currNode != null) {
			if(currNode.qLSV.getTrangThai().equalsIgnoreCase("đang thuê")) {
				if(currNode.qLSV.getNgaySinh().getMonth()  == toDay.getMonth() ) {
					currNode.qLSV.hienThiThongTinSV();
					index = 1;
					count ++;
				}
			}
			currNode = currNode.next;
		}
		if(index == 1) {
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Danh sách sinh nhật của sinh viên trong tháng "+(toDay.getMonth()+1)+" có "+count+" sinh viên!");
		}
		else
			System.err.println("Tháng này không có sinh viên nào sinh nhật!");
	}
	
	public void xuatFileSinhVien(NodeDoubleL node) throws IOException {
		FileOutputStream file = new FileOutputStream("danhSachSinhVien.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("SinhVien");
		XSSFRow row;
		XSSFCell cell0;
		XSSFCell cell1;
		XSSFCell cell2;
		XSSFCell cell3;
		XSSFCell cell4;
		XSSFCell cell5;
		XSSFCell cell6;
		XSSFCell cell7;
		XSSFCell cell8;
		XSSFCell cell9;
		XSSFCell cell10;
		XSSFCell cell11;
		XSSFCell cell12;
		row = sheet.createRow(0);
		cell0 = row.createCell(0);
		cell0.setCellValue("STT");
		cell1 = row.createCell(1);
		cell1.setCellValue("Mã SV");
		cell2 = row.createCell(2);
		cell2.setCellValue("Họ Tên");
		cell3 = row.createCell(3);
		cell3.setCellValue("SDT");
		cell4 = row.createCell(4);
		cell4.setCellValue("Giới Tính");
		cell5 = row.createCell(5);
		cell5.setCellValue("Địa Chỉ");
		cell6 = row.createCell(6);
		cell6.setCellValue("Ngày Sinh");
		cell7 = row.createCell(7);
		cell7.setCellValue("Ngành");
		cell8 = row.createCell(8);
		cell8.setCellValue("Khoá");
		cell9 = row.createCell(9);
		cell9.setCellValue("Ngày Vào");
		cell10 = row.createCell(10);
		cell10.setCellValue("Mã Phòng");
		cell11 = row.createCell(11);
		cell11.setCellValue("Số Lần Vi Phạm");
		cell12 = row.createCell(12);
		cell12.setCellValue("Trạng Thái");
		
		NodeDoubleL currNode = head;
		int count = 0;
		while(currNode != null) {
			++count;
			row = sheet.createRow(count);
			cell0 = row.createCell(0);
			cell0.setCellValue(count);
			cell1 = row.createCell(1);
			cell1.setCellValue(currNode.qLSV.getMaSV());
			cell2 = row.createCell(2);
			cell2.setCellValue(currNode.qLSV.getHoTen());
			cell3 = row.createCell(3);
			cell3.setCellValue(currNode.qLSV.getSDT());
			cell4 = row.createCell(4);
			cell4.setCellValue(currNode.qLSV.getGioiTinh());
			cell5 = row.createCell(5);
			cell5.setCellValue(currNode.qLSV.getDiaChi());
			cell6 = row.createCell(6);
			cell6.setCellValue(String.valueOf(currNode.qLSV.getNgaySinh()) );
			cell7 = row.createCell(7);
			cell7.setCellValue(currNode.qLSV.getNganh());
			cell8 = row.createCell(8);
			cell8.setCellValue(currNode.qLSV.getKhoa());
			cell6 = row.createCell(9);
			cell6.setCellValue(String.valueOf(currNode.qLSV.getNgayVao()));
			cell10 = row.createCell(10);
			cell10.setCellValue(currNode.qLSV.getMaPhong());
			cell11 = row.createCell(11);
			cell11.setCellValue(currNode.qLSV.getSoLanViPham());
			cell12 = row.createCell(12);
			cell12.setCellValue(currNode.qLSV.getTrangThai());
			currNode = currNode.next;
		}
		wb.write(file);
		System.out.println("Xuất file thành công!");
		wb.close();
		file.close();
	}
	
	public void hamHienThiListSinhVien() {
		if(head == null) 
			System.out.println("Danh sách rỗng!"); 
		else {
			NodeDoubleL currNode = head;
			currNode.qLSV.hienThiTieuDe();
			while (currNode != null) {
				if (currNode.qLSV.getTrangThai().equalsIgnoreCase("đang thuê"))
					currNode.hienThiSinhVien();
				currNode = currNode.next;
			}
		}
	}

	
}
