package CauTrucLuuTru;

import java.sql.SQLException;
import java.util.Scanner;

import QuanLyKTX.DAO;

public class LinkedList {
	int size;
	NodeLinkedList head;
	NodeLinkedList tail;
	
	public LinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	
	public int size() {
		return size;
	}
	
	public void insertHead(NodeLinkedList node) {
		if(head != null) {
			node.next = head;
		}
		head = node;
	}
	public void insertTail(NodeLinkedList node) {
		tail = head;
		if(head == null) {
			head = node;
		}
		else {
			while(tail.next != null) {
				tail = tail.next;
			}
			tail.next = node;
		}
	
	}
	
	public void insertAt(NodeLinkedList node, int position) {
		if(position == 0) {
			insertHead(node);
		}
		else {
			NodeLinkedList currentNode = head;
			int count = 0;
			while(currentNode != null) {
				count ++;
				if(count == position) {
					node.next = currentNode.next;
					currentNode.next = node;
					break;
				}
				currentNode = currentNode.next;
			}
		}
	}
	
	public void removeHead(NodeLinkedList node) {
		if(head != null) {
			head = head.next;
		}
		else {
			System.out.println("List rỗng không thể xoá");
		}
	}
	
	public void removeTail(NodeLinkedList node) {
		tail = head;
		if(head == null)
			System.out.println("List rỗng không thể xoá");
		else {
			NodeLinkedList prevNode = null;
			while(tail.next != null) {
				prevNode = tail;
				tail= tail.next;
			}
			if(prevNode == null)
				head = head.next;
			else {
				prevNode.next = tail.next;
			}
		}
	}
	
	public void removeAt(NodeLinkedList node, int position) {
		if(head == null) 
			System.out.println("List trống k thể xoá");
		if(position == 0) {
			removeHead(node);
		} else {
			NodeLinkedList currentNode = head;
			NodeLinkedList prevNode = null;
			int count = 0;
			boolean bIsFound = false;
			while(currentNode != null) {
				if(count == position) {
					bIsFound = true;
					break;
				}
				prevNode = currentNode;
				currentNode = currentNode.next;
				count++;
			}
			if(bIsFound)
				if(prevNode == null)
					head = head.next;
				else
					prevNode.next = currentNode.next;
		}
	}
	
	public NodeLinkedList checkMaQL(String maQL) {
		NodeLinkedList currNode = head;
		while(currNode != null) {
			if(currNode.qLQL.getMaQL().equals(maQL)) 
				return currNode;
			currNode = currNode.next;
		}
		return null;
	}
	
	public String checkMatKhauQL(String matKhau, String maQL) {
		NodeLinkedList checkNode = checkMaQL(maQL);
		if(checkNode != null) 
			if(checkNode.qLQL.getMatKhau().equals(matKhau)) {
				System.out.println("Đăng nhập thành công");
				return maQL;
			} 
		return null;
	}
	
	public void doiMatKhau(String maQL) throws SQLException {
		DAO dao = new DAO();
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập mật khẩu cũ:");
		String matKhauCu = sc.nextLine();
		NodeLinkedList currNode = searchQuanLy(maQL);
		if(currNode.qLQL.getMatKhau().equals(matKhauCu)) {
			System.out.println("Nhập mật khẩu mới:");
			String matKhauMoi = sc.nextLine();
			System.out.println("Nhập lại mật khẩu mới: ");
			String matKhauMoi2 = sc.nextLine();
			if(matKhauMoi.equals(matKhauMoi2)) {
				currNode.qLQL.setMatKhau(matKhauMoi);
				dao.updateQuanLy(currNode.qLQL, maQL);
				System.out.println("Đổi mật khẩu thành công!");
			}
			else 
				System.err.println("Mật khẩu nhập lại không đúng!");
		} else 
			System.err.println("Mật khẩu cũ không đúng!");
	}
	
	public NodeLinkedList searchQuanLy(String maQL) {
		NodeLinkedList currNode = head;
		while(currNode != null) {
			if(currNode.qLQL.getMaQL().equalsIgnoreCase(maQL))
					return currNode;
			currNode = currNode.next;
		}
		return null;
	}
	
	public void hamHienThiListQL() {
		System.out.println("--------------------------");
		NodeLinkedList currNode = head;
		while(currNode != null) {
			currNode.hienThiQuanLy();
			currNode = currNode.next;
		}
	}
}
