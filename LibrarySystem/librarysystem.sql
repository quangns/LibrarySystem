-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 17, 2017 lúc 06:44 PM
-- Phiên bản máy phục vụ: 10.1.25-MariaDB
-- Phiên bản PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `librarysystem`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
  `BID` int(11) NOT NULL,
  `Title` char(50) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `Publisher` char(20) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `Author` char(20) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `Price` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `book`
--

INSERT INTO `book` (`BID`, `Title`, `Publisher`, `Author`, `Price`) VALUES
(1, 'nha gia kim', 'Nha Nam', 'Paulo', 123),
(2, 'cà phê cùng tony', 'Nha Nam', 'Tony', 70),
(3, 'trên đường băng', 'Nhã Nam', 'Tony', 70),
(4, 'cà phê buổi sáng', 'Nhã Nam', 'Người Nhật', 30),
(5, 'ca phe cung tony', 'dong nam', 'tony', 120),
(20, 'đừng để tách cà phê nguội', 'nhã nam', 'Người Nhật', 123),
(21, 'bố già', 'đông a', 'không biết', 130);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bookcp`
--

CREATE TABLE `bookcp` (
  `CPID` int(11) NOT NULL,
  `CID` int(10) DEFAULT NULL,
  `BID` int(10) NOT NULL,
  `Status` bit(1) NOT NULL DEFAULT b'1',
  `ExpDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `bookcp`
--

INSERT INTO `bookcp` (`CPID`, `CID`, `BID`, `Status`, `ExpDate`) VALUES
(1, 7, 1, b'0', '2017-12-24'),
(6, 1, 2, b'0', '2017-12-24'),
(7, 1, 2, b'0', '2017-12-24'),
(8, 7, 2, b'0', '2017-12-24'),
(9, 7, 2, b'0', '2017-12-24'),
(10, 7, 2, b'0', '2017-12-24'),
(11, 5, 1, b'0', '2017-12-31'),
(12, 5, 1, b'0', '2017-12-31'),
(13, NULL, 20, b'1', NULL),
(14, NULL, 20, b'1', NULL),
(15, NULL, 20, b'1', NULL),
(16, 5, 2, b'0', '2018-01-01'),
(17, NULL, 2, b'1', NULL),
(18, NULL, 2, b'1', NULL),
(19, NULL, 2, b'1', NULL),
(20, NULL, 2, b'1', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `card`
--

CREATE TABLE `card` (
  `CID` int(10) NOT NULL,
  `ExpiredDate` date DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  `NumActive` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `card`
--

INSERT INTO `card` (`CID`, `ExpiredDate`, `Status`, `NumActive`) VALUES
(1, '0000-00-00', b'1', NULL),
(2, '0000-00-00', NULL, NULL),
(3, '0000-00-00', NULL, NULL),
(4, '0000-00-00', NULL, NULL),
(5, '1995-05-12', b'1', NULL),
(6, '0000-00-00', NULL, NULL),
(7, '2018-06-18', b'1', NULL),
(8, NULL, b'1', NULL),
(9, NULL, b'1', NULL),
(10, NULL, b'1', NULL),
(11, NULL, b'1', NULL),
(12, NULL, b'1', NULL),
(14, '0000-00-00', NULL, NULL),
(15, '1995-05-12', NULL, NULL),
(16, NULL, b'1', NULL),
(17, NULL, b'0', NULL),
(18, '1999-03-12', b'1', NULL),
(19, '1999-07-18', b'1', NULL),
(20, NULL, b'1', NULL),
(21, NULL, b'0', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `FirstName` char(10) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `LastName` char(10) DEFAULT NULL,
  `UserName` char(20) NOT NULL,
  `PassWord` char(20) NOT NULL,
  `CardID` int(10) DEFAULT NULL,
  `Role` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`ID`, `FirstName`, `LastName`, `UserName`, `PassWord`, `CardID`, `Role`) VALUES
(1, 'nguyen', 'quang', 'qua434', '43', NULL, 'user'),
(2, 'tran', 'quang', 'khai', '43', 5, 'user'),
(3, 'tran', 'quang', 'minh', '43', NULL, 'librarian'),
(4, 'hoang', 'minh', 'quan', '43', NULL, 'user'),
(5, 'ly', 'nam', 'anh', '43', NULL, 'user'),
(6, 'nguyen', 'quang', 'hoang', '123', NULL, 'user'),
(7, 'minh', 'ho', 'hominh', '15144234', NULL, 'user'),
(8, 'thanh', 'do', 'ddthanh', 'dcomatkhau', NULL, 'user'),
(9, 'quyen', 'le', 'lequyen', '234', NULL, 'user'),
(10, 'hue', 'hoa', 'hoahue', '159', NULL, 'user'),
(11, 'hue', 'hoa', 'hoahue1', '159', NULL, 'user'),
(12, 'hong', 'hoa', 'hoahong', '159', NULL, 'user'),
(13, 'quang', 'nguyen', 'lsfjlkf', '123', NULL, 'user'),
(15, 'khai', 'nguyen', 'quangns', '123', 7, 'user'),
(16, 'quang', 'nguyen', 'quang123', '123', 1, 'user');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`BID`);

--
-- Chỉ mục cho bảng `bookcp`
--
ALTER TABLE `bookcp`
  ADD PRIMARY KEY (`CPID`);

--
-- Chỉ mục cho bảng `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`CID`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UserName` (`UserName`),
  ADD KEY `fr_cardid` (`CardID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `book`
--
ALTER TABLE `book`
  MODIFY `BID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT cho bảng `bookcp`
--
ALTER TABLE `bookcp`
  MODIFY `CPID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT cho bảng `card`
--
ALTER TABLE `card`
  MODIFY `CID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
