DELETE FROM area;

INSERT INTO area (id, province, city, area, postcode, del) VALUES (10001, '北京01', '北京01', '朝阳01', '80001', false),
(10002, '北京02', '北京02', '朝阳02', '80002', false),
(10003, '北京03', '北京03', '朝阳03', '80003', false),
(10004, '北京04', '北京04', '朝阳04', '80004', false),
(10005, '北京05', '北京05', '朝阳05', '80005', false),
(10006, '北京06', '北京06', '朝阳06', '80006', false),
(10007, '北京07', '北京07', '朝阳07', '80007', false),
(10008, '北京08', '北京08', '朝阳08', '80008', false),
(10009, '北京09', '北京09', '朝阳09', '80009', false),
(10010, '北京10', '北京10', '朝阳10', '80010', false),
(10011, '北京11', '北京11', '朝阳11', '80011', false),
(10012, '北京12', '北京12', '朝阳12', '80012', false),
(10013, '北京13', '北京13', '朝阳13', '80013', false),
(10014, '北京14', '北京14', '朝阳14', '80014', false),
(10015, '北京15', '北京15', '朝阳15', '80015', false),
(10016, '北京16', '北京16', '朝阳16', '80016', false),
(10017, '北京17', '北京17', '朝阳17', '80017', false),
(10018, '北京18', '北京18', '朝阳18', '80018', false),
(10019, '北京19', '北京19', '朝阳19', '80019', false),
(10020, '北京20', '北京20', '朝阳20', '80020', false),
(10021, '北京21', '北京21', '朝阳21', '80021', false),
(10022, '北京22', '北京22', '朝阳22', '80022', false);

DELETE FROM `user`;

INSERT INTO `user` (id, `name`, `address_id`, sex, head_img, del) VALUES
( 1, '张三01', 1, 1, 'https://url-01', false),
( 2, '李四02', 1, 0, 'https://url-02', false),
( 3, '李四02', 1, 0, 'https://url-03', false),
( 4, '李四04', 1, 0, 'https://url-04', false),
( 5, '李四05', 1, 0, 'https://url-05', false),
( 6, '李四06', 1, 0, 'https://url-06', false),
( 7, '李四07', 1, 0, 'https://url-07', false),
( 8, '李四08', 1, 0, 'https://url-08', false),
( 9, '李四09', 1, 0, 'https://url-09', false),
(10, '李四10', 1, 0, 'https://url-10', false),
(11, '李四11', 1, 0, 'https://url-11', false),
(12, '李四12', 1, 0, 'https://url-12', false),
(13, '李四13', 1, 0, 'https://url-13', false),
(14, '李四14', 1, 0, 'https://url-14', false),
(15, '李四15', 1, 0, 'https://url-15', false),
(16, '李四16', 1, 0, 'https://url-16', false),
(17, '李四17', 1, 0, 'https://url-17', false),
(18, '李四18', 1, 0, 'https://url-18', false),
(19, '李四19', 1, 0, 'https://url-19', false),
(20, '李四20', 1, 0, 'https://url-20', false),
(21, '李四21', 1, 0, 'https://url-21', false),
(22, '李四22', 1, 0, 'https://url-22', false);


DELETE FROM user_address;

INSERT INTO user_address (id, user_id, area_id, tel, address, del) VALUES
( 1, 1, 10001, '10000000001', '曹县01', false),
( 2, 1, 10002, '10000000002', '曹县02', false),
( 3, 1, 10003, '10000000003', '曹县03', false),
( 4, 1, 10004, '10000000004', '曹县04', false),
( 5, 1, 10005, '10000000005', '曹县05', false),
( 6, 1, 10006, '10000000006', '曹县06', false),
( 7, 1, 10007, '10000000007', '曹县07', false),
( 8, 1, 10008, '10000000008', '曹县08', false),
( 9, 1, 10009, '10000000009', '曹县09', false),
(10,10, 10010, '10000000010', '曹县10', false),
(11,11, 10011, '10000000011', '曹县11', false),
(12,12, 10012, '10000000012', '曹县12', false),
(13,13, 10013, '10000000013', '曹县13', false),
(14,14, 10014, '10000000014', '曹县14', false),
(15,15, 10015, '10000000015', '曹县15', false),
(16,16, 10016, '10000000016', '曹县16', false),
(17,17, 10017, '10000000017', '曹县17', false),
(18,18, 10018, '10000000018', '曹县18', false),
(19,19, 10019, '10000000019', '曹县19', false),
(20,20, 10020, '10000000020', '曹县20', false),
(21,21, 10021, '10000000021', '曹县21', false),
(22,22, 10022, '10000000022', '曹县22', false);