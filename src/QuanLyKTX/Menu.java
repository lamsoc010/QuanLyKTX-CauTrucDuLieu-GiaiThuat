package QuanLyKTX;

public class Menu {
	public static void menuKTX() {
		System.out.println("----------------QLKTX----------------");
		System.out.println("|*1. Quản Lý Phòng                  |");
		System.out.println("|*2. Quản Lý Sinh Viên              |");
		System.out.println("|*3. Quản Lý Thuê Phòng             |");
		System.out.println("|*4. Quản Lý Kỷ Luật                |");
		System.out.println("|*5. Quản Lý Chuyển, Trả Phòng      |");
		System.out.println("|*6. Đổi mật khẩu                   |");
		System.out.println("|*7. Xuất file Excel list sinh viên |");
		System.out.println("|*0. Kết thúc                       |");
		System.out.println("-------------------------------------");
		System.out.println("Mời bạn chọn:");
	}
	
	public static void menuPhong() {
		System.out.println("-----------QLPhong-----------");
		System.out.println("|*1. Thêm phòng             |");
		System.out.println("|*2. Hiển thị phòng         |");
		System.out.println("|*3. Chỉnh sửa phòng        |");
		System.out.println("|*4. Xoá phòng              |");
		System.out.println("|*5. Tìm kiếm phòng         |");
		System.out.println("|*0. Quay lại               |");
		System.out.println("-----------------------------");
		System.out.println("Mời bạn chọn:");
	}
	
	public static void menuSinhVien() {
		System.out.println("--------------QLSinhVien-------------");
		System.out.println("|*1. Hiển thị sinh viên             |");
		System.out.println("|*2. Chỉnh sửa sinh viên            |");
		System.out.println("|*3. Tìm kiếm sinh viên	            |");
		System.out.println("|*4. Sắp xếp                        |");
		System.out.println("|*5. Tìm kiếm sinh viên theo phòng  |");
		System.out.println("|*6. Xem sinh nhật trong tháng      |");
		System.out.println("|*0. Quay lại                       |");
		System.out.println("-------------------------------------");
		System.out.println("Mời bạn chọn:");
	}
	
	public static void menuThuePhong() {
		System.out.println("------------------QLThuePhong----------------");
		System.out.println("|*1. Cho thuê phòng                         |");
		System.out.println("|*2. Hiển thị danh sách phòng đã cho thuê   |");
		System.out.println("|*3. Sinh viên quay lại thuê                |");
		System.out.println("|*0. Quay lại                               |");
		System.out.println("---------------------------------------------");
		System.out.println("Mời bạn chọn:");
	}
	
	public static void menuChuyenTraPhong() {
		System.out.println("--------------QLChuyenTraPhong---------------");
		System.out.println("|*1. Chuyển phòng                           |");
		System.out.println("|*2. Hiển thị danh sách chuyển phòng        |");
		System.out.println("|*3. Trả phòng                              |");
		System.out.println("|*4. Hiển thị danh sách phòng đã trả        |");
		System.out.println("|*0. Quay lại                               |");
		System.out.println("---------------------------------------------");
		System.out.println("Mời bạn chọn:");
	}
	
	public static void menuKyLuat() {
		System.out.println("---------------------QLKyLuat--------------------");
		System.out.println("|*1. Lập biên bản                               |");
		System.out.println("|*2. Hiển thị danh sách phiếu kỷ luật           |");
		System.out.println("|*3. Hiển thị danh sách sinh viên đã bị kỷ luật |");
		System.out.println("|*0. Quay lại                                   |");
		System.out.println("-------------------------------------------------");
		System.out.println("Mời bạn chọn:");
	}
	
	public static void menuSortSinhVien() {
		System.out.println("----------------Sắp xếp sinh viên------------");
		System.out.println("|*1. Sắp xếp theo tên                       |");
		System.out.println("|*2. Sắp xếp theo mã phòng                  |");
		System.out.println("|*3. Sắp xếp theo số lần vi phạm kỉ luật    |");
		System.out.println("|*4. Sắp xếp theo ngày vào                  |");
		System.out.println("|*0. Quay lại                               |");
		System.out.println("---------------------------------------------");
		System.out.println("Mời bạn chọn:");
	}
}
