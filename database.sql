-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 27, 2020 lúc 06:20 PM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `oop`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `password`, `username`) VALUES
(1, 'a', 'a'),
(2, 'b', 'b');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `avg_score`
--

CREATE TABLE `avg_score` (
  `id` int(11) NOT NULL,
  `usrId` int(11) NOT NULL,
  `level1Score` float NOT NULL CHECK (`level1Score` between 0 and 100),
  `level2Score` float NOT NULL CHECK (`level2Score` between 0 and 100),
  `level3Score` float NOT NULL CHECK (`level3Score` between 0 and 100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `testName` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `testLevel` int(11) NOT NULL CHECK (`testLevel` between 1 and 3),
  `totalTime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test_history`
--

CREATE TABLE `test_history` (
  `id` int(11) NOT NULL,
  `usrId` int(11) NOT NULL,
  `testId` int(11) NOT NULL,
  `conpletedDate` datetime NOT NULL,
  `score` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test_section`
--

CREATE TABLE `test_section` (
  `id` int(11) NOT NULL,
  `testId` int(11) NOT NULL,
  `sectionPos` int(11) NOT NULL,
  `urlScript` varchar(100) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `urlMp3` varchar(100) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `sectionLength` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `firstName` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `lastName` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `usrLevel` int(11) NOT NULL CHECK (`usrLevel` between 1 and 3),
  `avatarUrl` varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `accId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `avg_score`
--
ALTER TABLE `avg_score`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usrId` (`usrId`);

--
-- Chỉ mục cho bảng `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `test_history`
--
ALTER TABLE `test_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usrId` (`usrId`),
  ADD KEY `testId` (`testId`);

--
-- Chỉ mục cho bảng `test_section`
--
ALTER TABLE `test_section`
  ADD PRIMARY KEY (`id`),
  ADD KEY `testId` (`testId`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `accId` (`accId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `avg_score`
--
ALTER TABLE `avg_score`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `test_history`
--
ALTER TABLE `test_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `test_section`
--
ALTER TABLE `test_section`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `avg_score`
--
ALTER TABLE `avg_score`
  ADD CONSTRAINT `avg_score_ibfk_1` FOREIGN KEY (`usrId`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `test_history`
--
ALTER TABLE `test_history`
  ADD CONSTRAINT `test_history_ibfk_1` FOREIGN KEY (`usrId`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `test_history_ibfk_2` FOREIGN KEY (`testId`) REFERENCES `test` (`id`);

--
-- Các ràng buộc cho bảng `test_section`
--
ALTER TABLE `test_section`
  ADD CONSTRAINT `test_section_ibfk_1` FOREIGN KEY (`testId`) REFERENCES `test` (`id`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`accId`) REFERENCES `account` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
