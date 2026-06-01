# Phân tích

## Vấn đề
- `@Before` Advice chỉ chạy trước khi phương thức gốc được gọi.
- Nếu chỉ in log (`System.out.println`), sau khi Advice kết thúc thì phương thức gốc vẫn tiếp tục chạy.
- Do đó, việc chỉ in cảnh báo không thể ngăn chặn hành động thêm sản phẩm.

## Giải pháp
- Phải ném ngoại lệ (RuntimeException hoặc AccessDeniedException).
- Khi ngoại lệ được ném ra, JVM sẽ dừng ngay lập tức, không thực thi phương thức gốc.
- Như vậy mới đảm bảo đúng quy tắc nghiệp vụ: chỉ ADMIN mới được phép gọi các hàm thêm dữ liệu.
