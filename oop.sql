-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 13, 2020 lúc 03:20 PM
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
  `username` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `avatarUrl` varchar(250) COLLATE utf8mb4_vietnamese_ci DEFAULT '/avatar/default-avatar.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `password`, `username`, `avatarUrl`) VALUES
(1, 'a', 'a', './avatar/default-avatar.jpg'),
(2, 'b', 'b', './avatar/87270348_2615532488557859_3404489428712816640_n.jpg'),
(21, 'bb', 'bb', './avatar/default-avatar.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `avg_score`
--

CREATE TABLE `avg_score` (
  `id` int(11) NOT NULL,
  `usrId` int(11) NOT NULL,
  `level1Score` float NOT NULL CHECK (`level1Score` between 0 and 100),
  `level2Score` float NOT NULL CHECK (`level2Score` between 0 and 100),
  `level3Score` float NOT NULL CHECK (`level3Score` between 0 and 100),
  `date` date DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `avg_score`
--

INSERT INTO `avg_score` (`id`, `usrId`, `level1Score`, `level2Score`, `level3Score`, `date`) VALUES
(1, 2, 66, 55, 44, '2020-11-11'),
(4, 2, 36, 85, 74, '2020-11-12'),
(5, 2, 76, 75, 48, '2020-11-13'),
(6, 2, 86, 45, 54, '2020-11-14'),
(7, 2, 96, 55, 44, '2020-11-15'),
(8, 1, 76, 15, 46, '2020-11-16'),
(9, 1, 86, 55, 64, '2020-11-17');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `testName` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `testLevel` int(11) NOT NULL CHECK (`testLevel` between 1 and 3),
  `totalTime` int(11) NOT NULL,
  `script` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `hint` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `mp3Url` varchar(100) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `demoScript` varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `test`
--

INSERT INTO `test` (`id`, `testName`, `testLevel`, `totalTime`, `script`, `hint`, `mp3Url`, `demoScript`) VALUES
(1, 'test1', 1, 130, 'For the past 20 years , I have been helping Maylaysian and other Southeast Asians to speak better English . And through training thousands of Southeast Asians , I have discovered a very surprising truth . ,, I have discovered that how well somebody communicates in English actually has very little to do with their English level . It has a lot to do with their attitude towards English . ,, There are people out there who have a very low level of English , and they can communicate very very well . One of them that I remember was a student , a participant of mine , named Faizal . He was a factory supervisor - English level very very low but this guy ,, could just sit and listen to anybody , very calmly , clearly , and then he could respond , absolutely express his thoughts beautifully , at a very low level of English . So , today I want to share with you : ,, what is so different about people like Faizal ? How do they do it ? And second of all , why is this so important not only to you , but to your children , to your community , and to the future of Malaysia ? ,, And third of all , what’s one thing you can do , starting today , if you want to speak with that calm , clear confidence that people like Faizal have . First of all , what is so different ? How do people like Faizal do it ? ,, To answer that question , I’m going to take you back about 10 years , okay ? I was training staff at that time , and my daughter , at that time , was taking piano lessons . And I started to notice two really strong similarities ,, between my daughter’s attitude or thinking towards playing the piano and a lot of Malaysians’ thinking or attitude towards English . First of all , I should tell you my daughter ,, absolutely hated piano , hated the lessons , hated practicing . This is my daughter practicing piano , okay ? This is as good as it got .', 'For the past 20 years , I have been helping Maylaysian and other Southeast Asians to speak better English . And through training thousands of Southeast Asians , I have discovered a very surprising truth . ,, I have discovered that how well somebody communicates in English actually has very little to do with their English level . It has a lot to do with their attitude towards English . ,, There are people out there who have a very low level of English , and they can communicate very very well . One of them that I remember was a student , a participant of mine , named Faizal . He was a factory supervisor - English level very very low but this guy ,, could just sit and listen to anybody , very calmly , clearly , and then he could respond , absolutely express his thoughts beautifully , at a very low level of English . So , today I want to share with you : ,, what is so different about people like Faizal ? How do they do it ? And second of all , why is this so important not only to you , but to your children , to your community , and to the future of Malaysia ? ,, And third of all , what’s one thing you can do , starting today , if you want to speak with that calm , clear confidence that people like Faizal have . First of all , what is so different ? How do people like Faizal do it ? ,, To answer that question , I’m going to take you back about 10 years , okay ? I was training staff at that time , and my daughter , at that time , was taking piano lessons . And I started to notice two really strong similarities ,, between my daughter’s attitude or thinking towards playing the piano and a lot of Malaysians’ thinking or attitude towards English . First of all , I should tell you my daughter ,, absolutely hated piano , hated the lessons , hated practicing . This is my daughter practicing piano , okay ? This is as good as it got .', './wav/test1.wav', 'demo1'),
(2, 'test2', 2, 130, 'For the past 20 years ,,  I have been helping ,, Maylaysian and other ,, Southeast Asians to ,,  speak better English ,, And through training thousands of ,, Southeast Asians ,, I have discovered a ,, very surprising truth . ', 'Maylaysian Faizal', './wav/test1.wav', 'demo2'),
(3, 'test3', 2, 130, 'For the past 20 years , I have been helping Maylaysian and other Southeast Asians to speak better English . And through training thousands of Southeast Asians , I have discovered a very surprising truth . ,, I have discovered that how well somebody communicates in English actually has very little to do with their English level . It has a lot to do with their attitude towards English . ,, There are people out there who have a very low level of English , and they can communicate very very well . One of them that I remember was a student , a participant of mine , named Faizal . He was a factory supervisor - English level very very low but this guy ,, could just sit and listen to anybody , very calmly , clearly , and then he could respond , absolutely express his thoughts beautifully , at a very low level of English . So , today I want to share with you : ,, what is so different about people like Faizal ? How do they do it ? And second of all , why is this so important not only to you , but to your children , to your community , and to the future of Malaysia ? ,, And third of all , what’s one thing you can do , starting today , if you want to speak with that calm , clear confidence that people like Faizal have . First of all , what is so different ? How do people like Faizal do it ? ,, To answer that question , I’m going to take you back about 10 years , okay ? I was training staff at that time , and my daughter , at that time , was taking piano lessons . And I started to notice two really strong similarities ,, between my daughter’s attitude or thinking towards playing the piano and a lot of Malaysians’ thinking or attitude towards English . First of all , I should tell you my daughter ,, absolutely hated piano , hated the lessons , hated practicing . This is my daughter practicing piano , okay ? This is as good as it got .', 'Maylaysian Faizal', './wav/test3.wav', 'demo3'),
(4, 'test4', 3, 130, 'For the past 20 years , I have been helping Maylaysian and other Southeast Asians to speak better English . And through training thousands of Southeast Asians , I have discovered a very surprising truth . ,, I have discovered that how well somebody communicates in English actually has very little to do with their English level . It has a lot to do with their attitude towards English . ,, There are people out there who have a very low level of English , and they can communicate very very well . One of them that I remember was a student , a participant of mine , named Faizal . He was a factory supervisor - English level very very low but this guy ,, could just sit and listen to anybody , very calmly , clearly , and then he could respond , absolutely express his thoughts beautifully , at a very low level of English . So , today I want to share with you : ,, what is so different about people like Faizal ? How do they do it ? And second of all , why is this so important not only to you , but to your children , to your community , and to the future of Malaysia ? ,, And third of all , what’s one thing you can do , starting today , if you want to speak with that calm , clear confidence that people like Faizal have . First of all , what is so different ? How do people like Faizal do it ? ,, To answer that question , I’m going to take you back about 10 years , okay ? I was training staff at that time , and my daughter , at that time , was taking piano lessons . And I started to notice two really strong similarities ,, between my daughter’s attitude or thinking towards playing the piano and a lot of Malaysians’ thinking or attitude towards English . First of all , I should tell you my daughter ,, absolutely hated piano , hated the lessons , hated practicing . This is my daughter practicing piano , okay ? This is as good as it got .', 'Maylaysian Faizal', './wav/test1.wav', 'demo4');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test_history`
--

CREATE TABLE `test_history` (
  `id` int(11) NOT NULL,
  `usrId` int(11) NOT NULL,
  `testId` int(11) NOT NULL,
  `conpletedDate` datetime NOT NULL,
  `score` float NOT NULL,
  `completeTime` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `test_history`
--

INSERT INTO `test_history` (`id`, `usrId`, `testId`, `conpletedDate`, `score`, `completeTime`) VALUES
(1, 2, 2, '2020-06-09 00:00:00', 20, 250),
(2, 1, 2, '2020-06-09 00:00:00', 75, 150),
(3, 1, 2, '2020-06-11 00:00:00', 100, 120),
(4, 1, 1, '2020-06-12 00:00:00', 28.75, 400),
(5, 2, 3, '2020-06-13 00:00:00', 8.18182, 500);

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
  `firstName` varchar(50) COLLATE utf8mb4_vietnamese_ci DEFAULT 'None',
  `lastName` varchar(50) COLLATE utf8mb4_vietnamese_ci DEFAULT 'None',
  `usrLevel` int(11) NOT NULL DEFAULT 1 CHECK (`usrLevel` between 1 and 3),
  `accId` int(11) NOT NULL,
  `level1Score` float DEFAULT 0,
  `level2Score` float DEFAULT 0,
  `level3Score` float DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `firstName`, `lastName`, `usrLevel`, `accId`, `level1Score`, `level2Score`, `level3Score`) VALUES
(1, 'Nguyễn Tài', 'Thao', 2, 1, 0, 0, 0),
(2, 'Nguyễn Thị', 'Quỳnh', 3, 2, 88, 67, 11),
(6, 'None', 'None', 1, 21, 0, 0, 0);

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
  ADD UNIQUE KEY `date` (`date`),
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `avg_score`
--
ALTER TABLE `avg_score`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `test_history`
--
ALTER TABLE `test_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `test_section`
--
ALTER TABLE `test_section`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
