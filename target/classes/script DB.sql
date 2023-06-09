DROP TABLE IF EXISTS imovel;

CREATE TABLE imovel(
	id integer primary key autoincrement,
	inscricao integer not null unique,
	dt_liberacao text not null,
	valor decimal(10,2) not null,
	area integer not null,
	categoria char not null
);

INSERT INTO imovel VALUES(1, 12345678, "20200507", 1500000, 500, "A");
INSERT INTO imovel VALUES(2, 23456789, "20151221", 1000000, 200, "B");
INSERT INTO imovel VALUES(3, 34567890, "20030210", 800000, 150, "C");
INSERT INTO imovel VALUES(4, 45678901, "19750915", 500000, 80, "D");
INSERT INTO imovel VALUES(5, 56789012, "19670427", 230000, 40, "E");
INSERT INTO imovel VALUES(6, 67890123, "19950617", 120000, 60, "Z");
INSERT INTO imovel VALUES(7, 21112207, "18680717", 16780000, 90, "A");
INSERT INTO imovel VALUES(8, 21112208, "18730717", 180000, 750, "B");
INSERT INTO imovel VALUES(9, 21112209, "18630717", 180000, 40, "B");
INSERT INTO imovel VALUES(10, 21112210, "19590717", 120000, 40, "A");
INSERT INTO imovel VALUES(11, 21112211, "18520717", 450000, 90, "Z");
INSERT INTO imovel VALUES(12, 21112212, "19180717", 2250000, 90, "C");
INSERT INTO imovel VALUES(13, 21112213, "20190717", 16780000, 145, "Z");
INSERT INTO imovel VALUES(14, 21112214, "19330717", 180000, 40, "Z");
INSERT INTO imovel VALUES(15, 21112215, "19080717", 450000, 750, "Z");
INSERT INTO imovel VALUES(16, 21112216, "19090717", 180000, 145, "Z");
INSERT INTO imovel VALUES(17, 21112217, "19780717", 16780000, 750, "B");
INSERT INTO imovel VALUES(18, 21112218, "18580717", 2250000, 90, "C");
INSERT INTO imovel VALUES(19, 21112219, "20090717", 16780000, 145, "A");
INSERT INTO imovel VALUES(20, 21112220, "19130717", 450000, 145, "Z");
INSERT INTO imovel VALUES(21, 21112221, "18940717", 16780000, 250, "D");
INSERT INTO imovel VALUES(22, 21112222, "19940717", 2250000, 145, "B");
INSERT INTO imovel VALUES(23, 21112223, "19740717", 450000, 750, "C");
INSERT INTO imovel VALUES(24, 21112224, "20180717", 120000, 90, "A");
INSERT INTO imovel VALUES(25, 21112225, "19790717", 2250000, 750, "C");
INSERT INTO imovel VALUES(26, 21112226, "19690717", 180000, 90, "B");
INSERT INTO imovel VALUES(27, 21112227, "20230117", 16780000, 40, "D");
INSERT INTO imovel VALUES(28, 21112228, "19390717", 120000, 750, "D");
INSERT INTO imovel VALUES(29, 21112229, "18930717", 16780000, 90, "B");
INSERT INTO imovel VALUES(30, 21112230, "18790717", 120000, 145, "D");
INSERT INTO imovel VALUES(31, 21112231, "19530717", 180000, 250, "D");
INSERT INTO imovel VALUES(32, 21112232, "19880717", 450000, 250, "A");
INSERT INTO imovel VALUES(33, 21112233, "18520717", 120000, 250, "Z");
INSERT INTO imovel VALUES(34, 21112234, "19490717", 180000, 145, "D");
INSERT INTO imovel VALUES(35, 21112235, "18520717", 120000, 250, "D");
INSERT INTO imovel VALUES(36, 21112236, "19340717", 180000, 250, "C");
INSERT INTO imovel VALUES(37, 21112237, "19030717", 120000, 750, "C");
INSERT INTO imovel VALUES(38, 21112238, "19280717", 450000, 90, "A");
INSERT INTO imovel VALUES(39, 21112239, "18530717", 450000, 90, "D");
INSERT INTO imovel VALUES(40, 21112240, "18830717", 180000, 145, "A");
INSERT INTO imovel VALUES(41, 21112241, "18640717", 450000, 250, "B");
INSERT INTO imovel VALUES(42, 21112242, "19730717", 180000, 90, "B");
INSERT INTO imovel VALUES(43, 21112243, "18590717", 16780000, 250, "A");
INSERT INTO imovel VALUES(44, 21112244, "20140717", 2250000, 40, "C");
INSERT INTO imovel VALUES(45, 21112245, "19580717", 180000, 40, "C");
INSERT INTO imovel VALUES(46, 21112246, "19840717", 450000, 750, "Z");
INSERT INTO imovel VALUES(47, 21112247, "18520717", 2250000, 145, "Z");
INSERT INTO imovel VALUES(48, 21112248, "20080717", 2250000, 750, "D");
INSERT INTO imovel VALUES(49, 21112249, "18990717", 2250000, 750, "Z");
INSERT INTO imovel VALUES(50, 21112250, "19540717", 2250000, 145, "C");
INSERT INTO imovel VALUES(51, 21112251, "19190717", 2250000, 40, "C");
INSERT INTO imovel VALUES(52, 21112252, "19230717", 180000, 250, "C");
INSERT INTO imovel VALUES(53, 21112253, "20040717", 16780000, 40, "B");
INSERT INTO imovel VALUES(54, 21112254, "19990717", 120000, 40, "A");
INSERT INTO imovel VALUES(55, 21112255, "19480717", 450000, 750, "D");
INSERT INTO imovel VALUES(56, 21112256, "19430717", 120000, 145, "Z");
INSERT INTO imovel VALUES(57, 21112257, "18520717", 180000, 145, "A");
INSERT INTO imovel VALUES(58, 21112258, "18780717", 450000, 90, "A");
INSERT INTO imovel VALUES(59, 21112259, "19380717", 450000, 40, "D");
INSERT INTO imovel VALUES(60, 21112260, "18540717", 120000, 90, "Z");
INSERT INTO imovel VALUES(61, 21112261, "18840717", 16780000, 250, "D");
INSERT INTO imovel VALUES(62, 21112262, "19440717", 16780000, 145, "A");
INSERT INTO imovel VALUES(63, 21112263, "18980717", 450000, 90, "Z");
INSERT INTO imovel VALUES(64, 21112264, "19240717", 16780000, 145, "C");
INSERT INTO imovel VALUES(65, 21112265, "19930717", 450000, 250, "Z");
INSERT INTO imovel VALUES(66, 21112266, "19140717", 16780000, 750, "B");
INSERT INTO imovel VALUES(67, 21112267, "20030717", 2250000, 750, "B");
INSERT INTO imovel VALUES(68, 21112268, "18690717", 16780000, 90, "B");
INSERT INTO imovel VALUES(69, 21112269, "18740717", 450000, 40, "B");
INSERT INTO imovel VALUES(70, 21112270, "19290717", 120000, 145, "B");
INSERT INTO imovel VALUES(71, 21112271, "19830717", 2250000, 750, "C");
INSERT INTO imovel VALUES(72, 21112272, "18890717", 16780000, 250, "D");
INSERT INTO imovel VALUES(73, 21112273, "19040717", 120000, 750, "B");
INSERT INTO imovel VALUES(74, 21112274, "18880717", 120000, 250, "A");
INSERT INTO imovel VALUES(75, 21112275, "19980717", 2250000, 250, "A");
INSERT INTO imovel VALUES(76, 21112276, "19630717", 2250000, 250, "D");
INSERT INTO imovel VALUES(77, 21112277, "20130717", 180000, 40, "C");
INSERT INTO imovel VALUES(78, 21112278, "19640717", 120000, 40, "A");
INSERT INTO imovel VALUES(79, 21112279, "19890717", 2250000, 40, "C");