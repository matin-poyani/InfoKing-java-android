-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Sep 29, 2017 at 09:57 AM
-- Server version: 5.5.42
-- PHP Version: 5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `infoking`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'سیاست'),
(2, 'ورزش'),
(3, 'فرهنگ'),
(4, 'سینما'),
(5, 'تاریخ'),
(6, 'جغرافی'),
(7, 'ریاضی'),
(8, 'هوش'),
(9, 'بازی'),
(10, 'علم و دانش');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `question` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `a` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `b` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `c` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `d` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `answer` enum('a','b','c','d') COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `category_id`, `question`, `a`, `b`, `c`, `d`, `answer`) VALUES
(1, 1, 'رئیس جمهور دوره‌ی دهم ایران که بود؟', 'هاشمی رفسنجانی', 'احمدی نژاد', 'روحانی', 'خاتمی', 'b'),
(2, 2, 'پرافتخارترین بازیکن فوتبال ایران کیست؟', 'علی دایی', 'علی کریمی', 'ناصر حجازی', 'کریم باقری', 'a'),
(3, 2, 'بیشترین توپ طلا توسط چه کسی دریافت شده است؟', 'کریستیانو رونالدو', 'زین الدین زیدان', 'لیونل مسی', 'مایکل اوون', 'c'),
(4, 4, 'کدام بازیگر ایرانی موفق به دریافت جایزه از جشنواره‌ی فیلم کن شده است؟', 'ترانه علیدوستی', 'علی نصیریان', 'ابوالفضل پورعرب', 'شهاب حسینی', 'd'),
(5, 5, 'قدیمی‌ترین تمدن دنیا متعلق به چه کشوری است؟', 'ایران', 'مصر', 'ایتالیا', 'یونان', 'a'),
(6, 6, 'شهر سوخته در کدام استان واقع شده است؟', 'خراسان', 'یزد', 'فارس', 'سیستان و بلوچستان', 'd'),
(7, 6, 'کدام شهر در استان فارس قرار ندارد؟', 'صفاشهر', 'پاسارگاد', 'کازرون', 'گنبد کاووس', 'd'),
(8, 7, 'کدام عدد جزو توان‌های عدد 2 نیست؟', '24', '32', '64', '2048', 'a'),
(9, 9, 'شرکت سازنده‌ی بازی Assassin''s Creed کدام است؟', 'Ubisoft', 'Bioware', 'Konami', 'Bethethda', 'a'),
(10, 10, 'از ترکیب کدام دو عنصر، آب بدست می‌آید؟', 'اکسیژن و هلیوم', 'منیزیم و سدیم', 'هیدروژن و اکسیژن', 'کلر و نیکل', 'c');

-- --------------------------------------------------------

--
-- Table structure for table `quests`
--

CREATE TABLE `quests` (
  `id` int(11) NOT NULL,
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  `ts` int(11) NOT NULL,
  `q1` int(11) DEFAULT NULL,
  `a1u1` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `a1u2` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `q2` int(11) DEFAULT NULL,
  `a2u1` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `a2u2` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `q3` int(11) DEFAULT NULL,
  `a3u1` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `a3u2` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `q4` int(11) DEFAULT NULL,
  `a4u1` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `a4u2` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `q5` int(11) DEFAULT NULL,
  `a5u1` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `a5u2` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `q6` int(11) DEFAULT NULL,
  `a6u1` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `a6u2` enum('a','b','c','d') COLLATE utf8_persian_ci DEFAULT NULL,
  `finished` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `quests`
--

INSERT INTO `quests` (`id`, `user1_id`, `user2_id`, `ts`, `q1`, `a1u1`, `a1u2`, `q2`, `a2u1`, `a2u2`, `q3`, `a3u1`, `a3u2`, `q4`, `a4u1`, `a4u2`, `q5`, `a5u1`, `a5u2`, `q6`, `a6u1`, `a6u2`, `finished`) VALUES
(1, 1, 2, 1503820763, 10, 'a', 'a', 9, 'c', 'b', 8, 'd', 'a', 7, 'c', 'c', 6, 'd', 'd', 5, 'a', 'c', 1),
(2, 1, 3, 1503823955, 1, 'a', 'b', 2, 'a', 'b', 3, 'a', 'b', 4, 'a', 'b', 5, 'a', 'b', 6, 'a', 'b', 1),
(3, 2, 1, 1503895124, 1, 'a', 'b', 2, 'a', 'b', 3, 'a', 'b', 4, 'a', 'b', 5, 'a', 'b', 6, 'a', 'b', 1),
(4, 2, 3, 1503895147, 1, 'a', 'b', 2, 'a', 'b', 3, 'a', 'b', 4, 'a', 'b', 5, 'a', 'b', 6, 'a', 'b', 1),
(5, 1, 3, 1506608554, 6, 'd', 'c', 9, 'a', 'a', 1, 'b', 'a', 6, 'd', 'c', 4, 'd', 'a', 10, 'c', 'c', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `user` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `pass` char(40) COLLATE utf8_persian_ci NOT NULL,
  `searching` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `user`, `pass`, `searching`) VALUES
(1, 'محمد مصطفی شهرکی', 'ncis', '7c4a8d09ca3762af61e59520943dc26494f8941b', 0),
(2, 'جوجه پادشاه', 'koi', '7c4a8d09ca3762af61e59520943dc26494f8941b', 0),
(3, 'مستر مغز', 'mrbrain', '7c4a8d09ca3762af61e59520943dc26494f8941b', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `quests`
--
ALTER TABLE `quests`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user1_id` (`user1_id`),
  ADD KEY `user2_id` (`user2_id`),
  ADD KEY `q1` (`q1`),
  ADD KEY `q2` (`q2`),
  ADD KEY `q3` (`q3`),
  ADD KEY `q4` (`q4`),
  ADD KEY `q5` (`q5`),
  ADD KEY `q6` (`q6`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user` (`user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `quests`
--
ALTER TABLE `quests`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `fk_question_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `quests`
--
ALTER TABLE `quests`
  ADD CONSTRAINT `fk_quest_q1` FOREIGN KEY (`q1`) REFERENCES `questions` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_quest_q2` FOREIGN KEY (`q2`) REFERENCES `questions` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_quest_q3` FOREIGN KEY (`q3`) REFERENCES `questions` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_quest_q4` FOREIGN KEY (`q4`) REFERENCES `questions` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_quest_q5` FOREIGN KEY (`q5`) REFERENCES `questions` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_quest_q6` FOREIGN KEY (`q6`) REFERENCES `questions` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_quest_user1` FOREIGN KEY (`user1_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_quest_user2` FOREIGN KEY (`user2_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
