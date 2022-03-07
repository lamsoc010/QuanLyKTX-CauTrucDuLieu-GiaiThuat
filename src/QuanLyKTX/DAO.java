package QuanLyKTX;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

public class DAO {
	public Connection conn;
	public SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public DAO () throws SQLException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connURL = "jdbc:sqlserver://ADMIN\\SQLEXPRESS:1433;databaseName=QuanLyKTX1;integratedSecurity=true";
			conn = DriverManager.getConnection(connURL);
//			System.out.println("Kết nối thành công");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Kết nối thất bại");
			e.printStackTrace();
		}
		
	}
//	Của Phòng
	public void getListPhong(MyBST bST) {
		String sql = "SELECT * FROM dbo.PHONG";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				QuanLyPhong qLP = new QuanLyPhong();
				qLP.setMaPhong(rs.getInt(1));
				qLP.setLoaiPhong(rs.getString(2));
				qLP.setSoGiuong(rs.getInt(3));
				qLP.setSoNguoi(rs.getInt(4));
				qLP.setGiaTien(rs.getFloat(5));
				
				TreeNode treeNode = new TreeNode(qLP);
				
				bST.mRoot = qLP.insertIntoBST(bST.mRoot, treeNode);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertListPhong(QuanLyPhong qLP) {
		String sql = "INSERT INTO PHONG VALUES(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, qLP.getMaPhong());
			ps.setString(2, qLP.getLoaiPhong());
			ps.setInt(3, qLP.getSoGiuong());
			ps.setInt(4, qLP.getSoNguoi());
			ps.setFloat(5, qLP.getGiaTien());
//			ps.execute();
			
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletePhong(int maPhong ) {
		String sql = "DELETE FROM dbo.PHONG WHERE MAPHONG = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, maPhong);
			
			if(ps.executeUpdate() > 0) 
				System.out.println("Xoá thành công!");
			else 
				System.out.println("Xoá thất bại");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePhong(QuanLyPhong qLP, int maPhong) {
		String sql = "UPDATE dbo.PHONG SET LOAIPHONG = ?, SOGIUONG =?, GIATIEN =? , SONGUOI = ? WHERE MAPHONG = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, qLP.getLoaiPhong());
			ps.setInt(2, qLP.getSoGiuong());
			ps.setFloat(3, qLP.getGiaTien());
			ps.setInt(4, qLP.getSoNguoi());
			ps.setInt(5, maPhong);
//			ps.execute();
			
			ps.executeUpdate();
//				System.out.println("Cập nhập thành công!");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
//	Của Sinh Viên
	public void getListSinhVien(DoubleLinkedList dLL) {
		String sql = "SELECT * FROM SINHVIEN";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				QuanLySinhVien qLSV = new QuanLySinhVien();
				qLSV.setMaSV(rs.getString(1));
				qLSV.setHoTen(rs.getString(2));
				qLSV.setSDT(rs.getString(3));
				qLSV.setGioiTinh(rs.getString(4));
				qLSV.setDiaChi(rs.getString(5));
				qLSV.setNgaySinh(rs.getDate(6));
				qLSV.setNganh(rs.getString(7));
				qLSV.setKhoa(rs.getString(8));
				qLSV.setNgayVao(rs.getDate(9));
				qLSV.setMaPhong(rs.getInt(10));
				qLSV.setSoLanViPham(rs.getInt(11));
				qLSV.setTrangThai(rs.getString(12));
				
				NodeDoubleL nodeDLLSV = new NodeDoubleL(qLSV);
				dLL.insertTail(nodeDLLSV);
				
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertListSinhVien(NodeDoubleL node) {
		String sql = "INSERT INTO SINHVIEN VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, node.qLSV.getMaSV());
			ps.setString(2, node.qLSV.getHoTen());
			ps.setString(3, node.qLSV.getSDT());
			ps.setString(4, node.qLSV.getGioiTinh());
			ps.setString(5, node.qLSV.getDiaChi());
			ps.setString(6, df.format(node.qLSV.getNgaySinh()));
			ps.setString(7, node.qLSV.getNganh());
			ps.setString(8, node.qLSV.getKhoa());
			ps.setString(9, df.format(node.qLSV.getNgayVao()));
			ps.setInt(10, node.qLSV.getMaPhong());
			ps.setInt(11, node.qLSV.getSoLanViPham());
			ps.setString(12, node.qLSV.getTrangThai());
			
			ps.executeUpdate();
//				System.out.println("Thêm dữ liệu thành công!");
//			else {
//				System.out.println("Thất bại");
//			}
		
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateSinhVien(QuanLySinhVien qLSV, String maSV, int maPhong) {
		String sql = "UPDATE dbo.SINHVIEN SET HOTEN = ?, SDT = ?, GIOITINH = ?, DIACHI = ?, NGAYSINH = ?, "
				+ "NGANH = ?, KHOA = ?, NGAYVAO = ?, MAPHONG = ? , SOLANVIPHAM = ?, TRANGTHAI = ? WHERE MASV = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, qLSV.getHoTen());
			ps.setString(2, qLSV.getSDT());
			ps.setString(3, qLSV.getGioiTinh());
			ps.setString(4, qLSV.getDiaChi());
			ps.setString(5, df.format(qLSV.getNgaySinh()));
			ps.setString(6, qLSV.getNganh());
			ps.setString(7, qLSV.getKhoa());
			ps.setString(8, df.format(qLSV.getNgayVao()));
			ps.setInt(9, maPhong);
			ps.setInt(10, qLSV.getSoLanViPham());
			ps.setString(11, qLSV.getTrangThai());
			ps.setString(12, maSV);
			
			
			ps.executeUpdate();
//				System.out.println("Cập nhập thành công!");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSinhVien(String maSV) {
		String sql = "DELETE FROM dbo.SINHVIEN WHERE MASV = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, maSV);
			
			if(ps.executeUpdate() > 0) 
				System.out.println("Xoá thành công!");
			else 
				System.out.println("Xoá thất bại");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//	Của Quản Lý
	public void getListQuanLy(LinkedList lL) {
		String sql = "SELECT * FROM QUANLY";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				QuanLyQuanLy qLQL = new QuanLyQuanLy();
				
				qLQL.setMaQL(rs.getString(1));
				qLQL.setHoTen(rs.getString(2));
				qLQL.setSDT(rs.getString(3));
				qLQL.setDiaChi(rs.getString(4));
				qLQL.setPhuTrach(rs.getString(5));
				qLQL.setMatKhau(rs.getString(6));
				
				NodeLinkedList nodeLL = new NodeLinkedList(qLQL);
				lL.insertTail(nodeLL);
				
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateQuanLy(QuanLyQuanLy qLQL, String MaQL) {
		String sql = "UPDATE QUANLY SET MATKHAU = ? WHERE MAQL= ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, qLQL.getMatKhau());
			ps.setString(2, MaQL);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	Của Thuê Phòng
	public void getListThuePhong(MyBST bST) {
		String sql = "SELECT * FROM THUEPHONG";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				QuanLyThuePhong qLTP = new QuanLyThuePhong();
				
				qLTP.setMaPhieu(rs.getInt(1));
				qLTP.setMaSV(rs.getString(2));
				qLTP.setMaQL(rs.getString(3));
				qLTP.setMaPhong(rs.getInt(4));
				qLTP.setNgayVao(rs.getDate(5));
				qLTP.setTrangThai(rs.getString(6));
				
				TreeNode treeNode = new TreeNode(qLTP);
				bST.mRootTP = qLTP.insertIntoBST(bST.mRootTP, treeNode);
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void insertThuePhong(QuanLyThuePhong qLTP) {
		String sql = "INSERT INTO THUEPHONG (MAPHIEU, MASV, MAQL,MAPHONG, NGAYVAO, TRANGTHAI) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, qLTP.getMaPhieu());
			ps.setString(2, qLTP.getMaSV());
			ps.setString(3, qLTP.getMaQL());
			ps.setInt(4, qLTP.getMaPhong());
			ps.setString(5, df.format(qLTP.getNgayVao()));
			ps.setString(6, qLTP.getTrangThai());
//			ps.execute();
			
			ps.executeUpdate();
//				System.out.println("Thêm dữ liệu thành công!");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
//	Của Kỷ Luật
	public void getListKyLuat(MyBST bST) {
		String sql = "SELECT * FROM KYLUAT";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				QuanLyKyLuat qLKL = new QuanLyKyLuat();
				qLKL.setMaPhieu(rs.getInt(1));
				qLKL.setLoaiKL(rs.getString(2));
				qLKL.setNoiDung(rs.getString(3));
				qLKL.setHinhThucPhat(rs.getString(4));
				qLKL.setMaSV(rs.getString(5));
				qLKL.setMaQL(rs.getString(6));
				qLKL.setNgayViPham(rs.getDate(7));
				
				TreeNode treeNode = new TreeNode(qLKL);
				bST.mRootKL = qLKL.insertIntoBST(bST.mRootKL, treeNode);
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void insertKyLuat(QuanLyKyLuat qLKL) {
		String sql = "INSERT INTO KYLUAT (MAPHIEU, LOAIKL, NOIDUNG, HINHTHUCPHAT, MASV, MAQL, NGAYVIPHAM) "
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, qLKL.getMaPhieu());
			ps.setString(2, qLKL.getLoaiKL());
			ps.setString(3, qLKL.getNoiDung());
			ps.setString(4, qLKL.getHinhThucPhat());
			ps.setString(5, qLKL.getMaSV());
			ps.setString(6, qLKL.getMaQL());
			ps.setString(7, df.format(qLKL.getNgayViPham()) );
//			ps.execute();
			
			ps.executeUpdate();
//				System.out.println("Thêm dữ liệu thành công!");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Của trả phòng
	public void getListTraPhong(ArrayList<QuanLyTraPhong> listTraPhong) {
		String sql = "SELECT * FROM TRAPHONG";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				QuanLyTraPhong qLTraP = new QuanLyTraPhong();
				qLTraP.setMaPhieu(rs.getInt(1));
				qLTraP.setMaSV(rs.getString(2));
				qLTraP.setMaPhong(rs.getInt(3));
				qLTraP.setMaQL(rs.getString(4));
				qLTraP.setNgayTra(rs.getDate(5));
				qLTraP.setLiDo(rs.getString(6));
				
				listTraPhong.add(qLTraP);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertTraPhong(QuanLyTraPhong qLTraP) {
		String sql = "INSERT INTO TRAPHONG VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, qLTraP.getMaPhieu());
			ps.setString(2, qLTraP.getMaSV());
			ps.setInt(3, qLTraP.getMaPhong());
			ps.setString(4, qLTraP.getMaQL());
			ps.setString(5, df.format(qLTraP.getNgayTra()) );
			ps.setString(6, qLTraP.getLiDo());
			
			ps.executeUpdate();
//				System.out.println("Thêm dữ liệu thành công!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Của chuyển phòng
	public void getListChuyenPhong(ArrayList<QuanLyChuyenPhong> listChuyenPhong) {
		String sql = "SELECT * FROM CHUYENPHONG";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				QuanLyChuyenPhong qLCP = new QuanLyChuyenPhong();
				qLCP.setMaPhieu(rs.getInt(1));
				qLCP.setMaSV(rs.getString(2));
				qLCP.setMaPhongCu(rs.getInt(3));
				qLCP.setMaPhongMoi(rs.getInt(4));
				qLCP.setMaQL(rs.getString(5));
				qLCP.setNgayChuyen(rs.getDate(6));
				qLCP.setLiDo(rs.getString(7));
				
				listChuyenPhong.add(qLCP);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertChuyenphong(QuanLyChuyenPhong qLCP) {
		String sql = "INSERT INTO CHUYENPHONG VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, qLCP.getMaPhieu());
			ps.setString(2, qLCP.getMaSV());
			ps.setInt(3, qLCP.getMaPhongCu());
			ps.setInt(4, qLCP.getMaPhongMoi());
			ps.setString(5, qLCP.getMaQL());
			ps.setString(6, df.format(qLCP.getNgayChuyen()) );
			ps.setString(7, qLCP.getLiDo());
			
			ps.executeUpdate();
//				System.out.println("Thêm dữ liệu thành công!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

