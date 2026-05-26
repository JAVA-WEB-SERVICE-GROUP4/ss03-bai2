1. Phân tích thành phần HTTP Request
   Một HTTP Request tiêu chuẩn luôn gồm 3 phần chính. Dựa vào ảnh, chúng ta có:

Request Line (Dòng yêu cầu): POST /api/sanpham HTTP/1.1

Method: POST (Hành động tạo mới dữ liệu).

URL (Path): /api/sanpham (Đường dẫn tài nguyên).

Version: HTTP/1.1 (Phiên bản giao thức).

Headers (Phần thông tin meta):

Host: example.com: Chỉ định tên miền của máy chủ đích mà request này muốn gửi tới.

Content-Type: application/json: Thông báo cho Server biết rằng dữ liệu được đính kèm ở phần Body đang được định dạng theo chuẩn JSON.

Authorization: Bearer abc123: Chứa mã thông báo xác thực (Token). Đây chính là "chiếc vé" để Server nhận diện người dùng có quyền gọi API hay không (thường được nhét vào qua các thư viện như Axios).

Content-Length: 48: Báo trước cho Server biết kích thước của phần Body là 48 byte, giúp Server cấp phát bộ nhớ để đọc cho chuẩn xác.

Body (Dữ liệu mang theo): {"ten":"Laptop","gia":15000000,"tonkho":10}

2. Phân tích thành phần HTTP Response & Mã 201
   Tương tự, HTTP Response từ Server trả về cũng có 3 phần:

Status Line (Dòng trạng thái): HTTP/1.1 201 Created

Headers:

Date: Mon, 10 Apr 2025 07:30:00 GMT: Thời điểm Server xử lý và tạo ra phản hồi.

Content-Type: application/json: Báo cho Client (Frontend) biết dữ liệu trả về đang ở dạng JSON để Client tự động parse.

Location: /api/sanpham/101: Cực kỳ quan trọng trong API POST. Nó cung cấp đường dẫn URL để Client có thể truy cập ngay vào cái "Laptop" vừa được tạo thành công (mang ID là 101).

Body (Dữ liệu trả về): {"id":101,"ten":"Laptop","gia":15000000,"tonkho":10} (Thường trả về toàn bộ đối tượng kèm theo ID vừa được Database cấp).

Về mã trạng thái 201 Created:

Nó thuộc nhóm 2xx (Success) – Nhóm biểu thị yêu cầu đã được Server tiếp nhận và xử lý thành công.

Ý nghĩa: Cụ thể mã 201 mang ý nghĩa "Đã tạo thành công". Nó chuẩn xác hơn mã 200 OK thông thường vì nó khẳng định rõ ràng rằng có một tài nguyên (resource) mới vừa được sinh ra trên hệ thống.

3. Trường hợp tìm sản phẩm không tồn tại
   Nếu Client gửi request GET /api/sanpham/999 nhưng ID 999 không có trong Database:

Mã trạng thái được trả về sẽ là 404 Not Found.

Giải thích: Mã này thuộc nhóm 4xx (Client Error), biểu thị rằng Server vẫn hoạt động bình thường, nhưng tài nguyên mà Client đang yêu cầu tìm kiếm không hề tồn tại ở đường dẫn đó.

4. Trường hợp Server gặp lỗi không xác định
   Khi Server gặp một lỗi trong quá trình xử lý logic (ví dụ: mất kết nối Database đột ngột, lỗi chia cho 0, hoặc như lỗi ràng buộc khóa ngoại chưa được bọc try-catch khiến hệ thống bị sập luồng):

Mã trạng thái được dùng sẽ là 500 Internal Server Error.

Giải thích: Mã này thuộc nhóm 5xx (Server Error). Nó là thông báo chung chung nhất báo hiệu rằng "Lỗi là do phía máy chủ (Backend), Client không làm gì sai cả, hãy thử lại sau".