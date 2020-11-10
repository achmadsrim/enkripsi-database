# Host: localhost  (Version 5.5.5-10.1.13-MariaDB)
# Date: 2017-11-23 15:34:48
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "master_a3l"
#

DROP TABLE IF EXISTS `master_a3l`;
CREATE TABLE `master_a3l` (
  `Kode_A3L` int(4) NOT NULL DEFAULT '0',
  `Kota` varchar(32) DEFAULT NULL,
  `Alamat` varchar(255) DEFAULT NULL,
  `Telepon` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`Kode_A3L`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "master_a3l"
#

INSERT INTO `master_a3l` VALUES (1,'Bogor','Jln Raya Tapos','025109808');

#
# Structure for table "master_barang"
#

DROP TABLE IF EXISTS `master_barang`;
CREATE TABLE `master_barang` (
  `Kode_Barang` int(4) NOT NULL DEFAULT '0',
  `Nama_Barang` varchar(50) DEFAULT NULL,
  `Satuan` varchar(10) DEFAULT NULL,
  `Harga` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Kode_Barang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "master_barang"
#

INSERT INTO `master_barang` VALUES (1,'Beras','pcs','4000000'),(2,'besi','unit','90000000');

#
# Structure for table "master_pengiriman"
#

DROP TABLE IF EXISTS `master_pengiriman`;
CREATE TABLE `master_pengiriman` (
  `Kode_Pengiriman` int(1) NOT NULL,
  `Tujuan_Pengiriman` varchar(255) DEFAULT NULL,
  `HrgPengiriman` varchar(255) DEFAULT '0',
  PRIMARY KEY (`Kode_Pengiriman`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "master_pengiriman"
#

INSERT INTO `master_pengiriman` VALUES (1,'Bogor','900000');

#
# Structure for table "transaksi"
#

DROP TABLE IF EXISTS `transaksi`;
CREATE TABLE `transaksi` (
  `No_Kwitansi` int(11) NOT NULL DEFAULT '0',
  `Tanggal` varchar(225) DEFAULT '',
  `Kode_A3L` varchar(225) NOT NULL DEFAULT '',
  `Kota` varchar(225) NOT NULL DEFAULT '',
  `Telepon` varchar(225) NOT NULL DEFAULT '',
  `Kode_Barang` varchar(225) NOT NULL DEFAULT '',
  `Nama_Barang` varchar(225) NOT NULL DEFAULT '',
  `Harga` varchar(225) NOT NULL DEFAULT '',
  `Kode_Pengiriman` varchar(255) NOT NULL DEFAULT '',
  `Tujuan_Pengiriman` varchar(225) NOT NULL DEFAULT '',
  `Jml_Barang` varchar(225) NOT NULL DEFAULT '',
  `Jml_Harga` varchar(225) NOT NULL DEFAULT '',
  `HrgPengiriman` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`No_Kwitansi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "transaksi"
#

INSERT INTO `transaksi` VALUES (4,'18-11-2017','DmZLTkXClXPDijF1XsKzJlfDoAR9YA==','cTNtXkTClQLCjykxCcO6KVLDqQB6b8KSw5zDrsOC','D347HArCinfDnG4xeMK/MRbCvkl1asKbw5jDqcONw5NmMRg=','DmZLTkXClXPDijF1XsKzJlfDoAR9YA==','cS14TEXClQLCjykxCcO6KVLDqQB6b8KSw5zDrsOC','A3A2HQrCg3/Diht0SsO6chbDpQ15Z8KYw5DDo8OBw55l','DmZLTkXClXPDijF1XsKzJlfDoAR9YA==','cTNtXkTClQLCjykxCcO6KVLDqQB6b8KSw5zDrsOC','Dn8gYFnDjGnDkHBwV8K3IVjDpQ15Z8KY','YTA0EATCgXbDnGYlBcOsfgTCsl80RcKTw4jCp8KWwpdqPBvCicOkA8KzwqBxwp4=','BnA2HQrCg2nCoTVoE8OgaFfDoAR9YMKXw5XDqsOFw5k='),(5,'16-11-2017','DmZLTkXClXPDijF1XsKzJlfDoAR9YA==','cTNtXkTClQLCjykxCcO6KVLDqQB6b8KSw5zDrsOC','D347HArCinfDnG4xeMK/MRbCvkl1asKbw5jDqcONw5NmMRg=','DmZLTkXClXPDijF1XsKzJlfDoAR9YA==','cS14TEXClQLCjykxCcO6KVLDqQB6b8KSw5zDrsOC','A3A2HQrCg3/Diht0SsO6chbDpQ15Z8KYw5DDo8OBw55l','DmZLTkXClXPDijF1XsKzJlfDoAR9YA==','cTNtXkTClQLCjykxCcO6KVLDqQB6b8KSw5zDrsOC','BmZLTkXClXPDijF1XsKzJlfDoAR9YA==','YTA0EgDCgXbDnGYlBcOsfgTCsl80RcKTw4jCp8KWwpdqPBvCicOkA8KzwqBxwp4=','BnA2HQrCg2nCoTVoE8OgaFfDoAR9YMKXw5XDqsOFw5k=');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nm_lengkap` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "user"
#

INSERT INTO `user` VALUES ('root','kampreto31',NULL,NULL);
