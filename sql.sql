CREATE TABLE Customer (
    customerID INT IDENTITY(1,1) PRIMARY KEY, -- Tự động tăng và là khóa chính
    userName NVARCHAR(50) NOT NULL, -- Tên người dùng, không được để trống
    email NVARCHAR(100) NULL UNIQUE, -- Địa chỉ email, không được để trống và phải là duy nhất
    avatar NVARCHAR(255), -- URL hoặc đường dẫn đến ảnh đại diện
    password NVARCHAR(255) NOT NULL, -- Mật khẩu, không được để trống
    dob DATE, -- Ngày sinh
    address NVARCHAR(255), -- Địa chỉ
    gender CHAR(1) CHECK (gender IN ('M', 'F', 'O')), -- Giới tính, với ràng buộc CHECK
    cusPhone NVARCHAR(15) CHECK (cusPhone LIKE '[0-9]%'), -- Số điện thoại với ràng buộc CHECK
    role INT NOT NULL, -- Vai trò
    googleID NVARCHAR(100) -- ID Google (nếu có)
);
