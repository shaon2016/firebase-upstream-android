-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2017 at 09:13 AM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fcmtest`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(20) NOT NULL,
  `token` varchar(200) NOT NULL,
  `identification` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `token`, `identification`) VALUES
(1, 'dqOrjZDJlTA:APA91bGDGA8i8YSko6XjSTaIH2lVaoaKM70PA72SnpkNAyUhzZoA3iUUMHiC7oT13ENe3oiHeAEkdAI_ciX-znse8I4k4-ViQss-6LvQd95mO4EWZvW4LU2FBwtIkncZh1JMFnRXdsPo', NULL),
(2, 'fI8Ne0o9KUU:APA91bECbsevxt83eMXKyFCxiiDO23vIfK7813pADLVoEiVLG_NuC-ai7afgrJ2XBGLa7jA0ZrjIQzE3nZ0OTV-s4Kmm63rcAErEWKWwd6KsONMi3GZTDyJADYHGvog5COihvSnZrQ06', 123),
(7, 'ej-Ks47JVxw:APA91bEBDfqdf2tb_rFoB-yWfd8eFV1i2-6qx_p6Pf9Y7tByXJaqlwAbWR-brYjlUK2XRYhRgsv8DAQ5ARkys8DtwY-SeaMQVD7H1vxr-M2_iQKnLHCxTon5l8Gi1MLJEb48LHqeXVtr', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `token` (`token`),
  ADD UNIQUE KEY `token_2` (`token`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
