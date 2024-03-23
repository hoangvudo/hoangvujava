package quanlinhanvien;

import java.io.*;
import java.util.*;

	// Class định nghĩa Nhân Viên
	class NhanVien implements Serializable {
	    private String hoTen;
	    private int tuoi;
	    private String gioiTinh;
	    private String chucVu;

	    public NhanVien(String hoTen, int tuoi, String gioiTinh, String chucVu) {
	        this.hoTen = hoTen;
	        this.tuoi = tuoi;
	        this.gioiTinh = gioiTinh;
	        this.chucVu = chucVu;
	    }

	    public String getHoTen() {
	        return hoTen;
	    }

	    public int getTuoi() {
	        return tuoi;
	    }

	    public String getGioiTinh() {
	        return gioiTinh;
	    }

	    public String getChucVu() {
	        return chucVu;
	    }

	    @Override
	    public String toString() {
	        return "Họ tên: " + hoTen + ", Tuổi: " + tuoi + ", Giới tính: " + gioiTinh + ", Chức vụ: " + chucVu;
	    }
	}

	// Class ứng dụng
	public class QuanLyNhanVien {
	    private ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();
	    private Scanner scanner = new Scanner(System.in);

	    public void themNhanVien() {
	        System.out.print("Nhập họ tên nhân viên: ");
	        String hoTen = scanner.nextLine();
	        System.out.print("Nhập tuổi nhân viên: ");
	        int tuoi = Integer.parseInt(scanner.nextLine());
	        System.out.print("Nhập giới tính nhân viên: ");
	        String gioiTinh = scanner.nextLine();
	        System.out.print("Nhập chức vụ nhân viên: ");
	        String chucVu = scanner.nextLine();

	        NhanVien nv = new NhanVien(hoTen, tuoi, gioiTinh, chucVu);
	        danhSachNhanVien.add(nv);
	    }

	    public void hienThiDanhSach() {
	        for (NhanVien nv : danhSachNhanVien) {
	            System.out.println(nv);
	           
	        }
	    }

	    public void timKiemNhanVien(String hoTen) {
	        for (NhanVien nv : danhSachNhanVien) {
	            if (nv.getHoTen().equalsIgnoreCase(hoTen)) {
	                System.out.println(nv);
	                return;
	            }
	        }
	        System.out.println("Không tìm thấy nhân viên có họ tên là " + hoTen);
	    }

	    public void xoaNhanVien(String hoTen) {
	        Iterator<NhanVien> iterator = danhSachNhanVien.iterator();
	        while (iterator.hasNext()) {
	            NhanVien nv = iterator.next();
	            if (nv.getHoTen().equalsIgnoreCase(hoTen)) {
	                iterator.remove();
	                System.out.println("Đã xóa nhân viên có họ tên là " + hoTen);
	                return;
	            }
	        }
	        System.out.println("Không tìm thấy nhân viên có họ tên là " + hoTen + " để xóa.");
	    }

	    public void suaThongTinNhanVien(String hoTen) {
	        for (int i = 0; i < danhSachNhanVien.size(); i++) {
	            NhanVien nv = danhSachNhanVien.get(i);
	            if (nv.getHoTen().equalsIgnoreCase(hoTen)) {
	                System.out.print("Nhập họ tên mới: ");
	                String newHoTen = scanner.nextLine();
	                System.out.print("Nhập tuổi mới: ");
	                int newTuoi = Integer.parseInt(scanner.nextLine());
	                System.out.print("Nhập giới tính mới: ");
	                String newGioiTinh = scanner.nextLine();
	                System.out.print("Nhập chức vụ mới: ");
	                String newChucVu = scanner.nextLine();

	                // Cập nhật thông tin mới cho nhân viên tại vị trí i trong danh sách
	                danhSachNhanVien.set(i, new NhanVien(newHoTen, newTuoi, newGioiTinh, newChucVu));
	                System.out.println("Đã cập nhật thông tin cho nhân viên có họ tên là " + hoTen);
	                return;
	            }
	        }
	        System.out.println("Không tìm thấy nhân viên có họ tên là " + hoTen + " để sửa.");
	    }


	    public void sapXepTheoABC() {
	        Collections.sort(danhSachNhanVien, (nv1, nv2) -> {
	            String hoTen1 = nv1.getHoTen().toLowerCase();
	            String hoTen2 = nv2.getHoTen().toLowerCase();
	            return hoTen1.compareTo(hoTen2);
	        });
	        System.out.println("Đã sắp xếp danh sách nhân viên theo thứ tự ABC.");
	    }

	    public void thongKeNhanVien() {
	        System.out.println("Tổng số nhân viên: " + danhSachNhanVien.size());
	    }

	    public void docTuFile(String tenFile) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                // Tách thông tin của mỗi nhân viên từ dòng văn bản
	                String[] parts = line.split(", ");
	                if (parts.length == 4) {
	                    String hoTen = parts[0].substring(parts[0].indexOf(": ") + 2);
	                    int tuoi = Integer.parseInt(parts[1].substring(parts[1].indexOf(": ") + 2));
	                    String gioiTinh = parts[2].substring(parts[2].indexOf(": ") + 2);
	                    String chucVu = parts[3].substring(parts[3].indexOf(": ") + 2);
	                    NhanVien nv = new NhanVien(hoTen, tuoi, gioiTinh, chucVu);
	                    danhSachNhanVien.add(nv);
	                }
	            }
	            System.out.println("Đã đọc danh sách nhân viên từ file " + tenFile);
	        } catch (IOException e) {
	            System.out.println("Lỗi khi đọc từ file: " + e.getMessage());
	        }
	    }


	    public void luuFile(String tenFile) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
	            for (NhanVien nv : danhSachNhanVien) {
	                writer.write(nv.toString()); // Ghi thông tin nhân viên dưới dạng văn bản
	                writer.newLine(); // Thêm dòng mới cho mỗi nhân viên
	            }
	            System.out.println("Đã lưu danh sách nhân viên vào file " + tenFile);
	        } catch (IOException e) {
	            System.out.println("Lỗi khi lưu vào file: " + e.getMessage());
	        }
	    }

	    public static void main(String[] args) {
	        QuanLyNhanVien quanLy = new QuanLyNhanVien();
	        Scanner scanner = new Scanner(System.in);
	        int luaChon;

	        do {
	            System.out.println("=== MENU ===");
	            System.out.println("1. Thêm nhân viên");
	            System.out.println("2. Hiển thị danh sách nhân viên");
	            System.out.println("3. Tìm kiếm nhân viên");
	            System.out.println("4. Xóa nhân viên");
	            System.out.println("5. Sửa thông tin nhân viên");
	            System.out.println("6. Sắp xếp danh sách theo ABC");
	            System.out.println("7. Thống kê số lượng nhân viên");
	            System.out.println("8. Đọc từ file");
	            System.out.println("9. Lưu vào file");
	            System.out.println("0. Thoát chương trình");
	            System.out.print("Nhập lựa chọn của bạn: ");
	            luaChon = Integer.parseInt(scanner.nextLine());

	            switch (luaChon) {
	                case 1:
	                    quanLy.themNhanVien();
	                    break;
	                case 2:
	                    quanLy.hienThiDanhSach();
	                    break;
	                case 3:
	                    System.out.print("Nhập họ tên nhân viên cần tìm kiếm: ");
	                    String tenTimKiem = scanner.nextLine();
	                    quanLy.timKiemNhanVien(tenTimKiem);
	                    break;
	                case 4:
	                    System.out.print("Nhập họ tên nhân viên cần xóa: ");
	                    String tenXoa = scanner.nextLine();
	                    quanLy.xoaNhanVien(tenXoa);
	                    break;
	                case 5:
	                    System.out.print("Nhập họ tên nhân viên cần sửa: ");
	                    String tenSua = scanner.nextLine();
	                    quanLy.suaThongTinNhanVien(tenSua);
	                    break;
	                case 6:
	                	quanLy.sapXepTheoABC();	                    
	                	break;
	                case 7:
	                    quanLy.thongKeNhanVien();
	                    break;
	                case 8:
	                    System.out.print("Nhập tên file cần đọc: ");
	                    String tenFileDoc = scanner.nextLine();
	                    quanLy.docTuFile(tenFileDoc);
	                    break;
	                case 9:
	                    System.out.print("Nhập tên file cần lưu: ");
	                    String tenFileLuu = scanner.nextLine();
	                    quanLy.luuFile(tenFileLuu);
	                    break;
	                case 0:
	                    System.out.println("Đã thoát chương trình.");
	                    break;
	                default:
	                    System.out.println("Lựa chọn không hợp lệ.");
	            }
	        } while (luaChon != 0);
	    }
	}
