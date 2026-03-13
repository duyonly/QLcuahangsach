SELECT 
    DATE(NgayLap) AS Ngay,
    SUM(TongTien) AS DoanhThu
FROM hoadon
WHERE TrangThai = 'Đã thanh toán'
GROUP BY DATE(NgayLap)
ORDER BY Ngay;
SELECT 
    YEAR(NgayLap) AS Nam,
    MONTH(NgayLap) AS Thang,
    SUM(TongTien) AS DoanhThu
FROM hoadon
WHERE TrangThai='Đã thanh toán'
GROUP BY YEAR(NgayLap), MONTH(NgayLap)
ORDER BY YEAR(NgayLap), MONTH(NgayLap);
SELECT 
    SUM(TongTien) AS DoanhThu
FROM hoadon
WHERE TrangThai = 'Đã thanh toán'
AND NgayLap BETWEEN '2026-02-01' AND '2026-02-28';
SELECT SUM(TongTien) AS TongNhap
FROM phieunhap;
SELECT 
    (SELECT SUM(TongTien) FROM hoadon WHERE TrangThai='Đã thanh toán') 
    -
    (SELECT SUM(TongTien) FROM phieunhap)
AS LoiNhuan;
SELECT 
    s.MaSach,
    s.TenSach,
    SUM(ct.SoLuongMua) AS SoLuongBan
FROM chitiethoadon ct
JOIN sach s ON ct.MaSach = s.MaSach
JOIN hoadon h ON ct.MaHD = h.MaHD
WHERE h.TrangThai = 'Đã thanh toán'
GROUP BY s.MaSach, s.TenSach
ORDER BY SoLuongBan DESC
LIMIT 5;
SELECT 
    nv.MaNV,
    nv.TenNV,
    SUM(h.TongTien) AS DoanhThu
FROM hoadon h
JOIN nhanvien nv ON h.MaNV = nv.MaNV
WHERE h.TrangThai = 'Đã thanh toán'
GROUP BY nv.MaNV, nv.TenNV
ORDER BY DoanhThu DESC;
SELECT MaSach, TenSach, SoLuongTon
FROM sach
ORDER BY SoLuongTon ASC;