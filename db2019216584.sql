/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - db2019216584
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db2019216584` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `book` */

CREATE TABLE `book` (
  `bookid` int(10) NOT NULL AUTO_INCREMENT COMMENT '书id',
  `bookname` varchar(100) NOT NULL COMMENT '书名',
  `author` varchar(28) NOT NULL COMMENT '作者',
  `publish` varchar(40) DEFAULT NULL COMMENT '出版社',
  `introduction` text COMMENT '介绍',
  `classid` int(11) DEFAULT NULL COMMENT '类别',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `bookimg` varchar(100) DEFAULT NULL COMMENT '封面',
  PRIMARY KEY (`bookid`),
  KEY `bookID` (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`bookid`,`bookname`,`author`,`publish`,`introduction`,`classid`,`number`,`bookimg`) values (1,'秘密花园','乔汉娜·贝斯福 ','北京联合出版公司','欢迎来到秘密花园！ 在这个笔墨编织出的美丽世界中漫步吧 涂上你喜爱的颜色，为花园带来生机和活力 发现隐藏其中的各类小生物，与它们共舞 激活创造力，描绘那些未完成的仙踪秘境 各个年龄段的艺术家和“园丁”都可以来尝试喔！',3,19,'11'),(2,'大雪中的山庄','东野圭吾 ','北京十月文艺出版社','东野圭吾长篇小说杰作，中文简体首次出版。 一出没有剧本的舞台剧，为什么能让七个演员赌上全部人生.东野圭吾就是有这样过人的本领，能从充满悬念的案子写出荡气回肠的情感，在极其周密曲折的同时写出人性的黑暗与美丽。 一家与外界隔绝的民宿里，七个演员被要求住满四天，接受导演的考验，但不断有人失踪。难道这并非正常排练，而是有人布下陷阱要杀他们。 那时候我开始喜欢上戏剧和音乐，《大雪中的山庄》一书的灵感就来源于此。我相信这次的诡计肯定会让人大吃一惊。——东野圭吾',5,30,NULL),(3,'明朝那些事儿','当年明月 ','中国海关出版社','《明朝那些事儿》讲述从1344年到1644年，明朝三百年间的历史。作品以史料为基础，以年代和具体人物为主线，运用小说的笔法，对明朝十七帝和其他王公权贵和小人物的命运进行全景展示，尤其对官场政治、战争、帝王心术着墨最多。作品也是一部明朝政治经济制度、人伦道德的演义。',1,21,NULL),(4,'人类简史','[以色列] 尤瓦尔·赫拉利 ','中信出版社','十万年前，地球上至少有六种不同的人\r\n但今日，世界舞台为什么只剩下了我们自己？\r\n从只能啃食虎狼吃剩的残骨的猿人，到跃居食物链顶端的智人，\r\n从雪维洞穴壁上的原始人手印，到阿姆斯壮踩上月球的脚印，\r\n从认知革命、农业革命，到科学革命、生物科技革命，\r\n我们如何登上世界舞台成为万物之灵的？\r\n从公元前1776年的《汉摩拉比法典》，到1776年的美国独立宣言，\r\n从帝国主义、资本主义，到自由主义、消费主义，\r\n从兽欲，到物欲，从兽性、人性，到神性，\r\n我们了解自己吗？我们过得更快乐吗？\r\n我们究竟希望自己得到什么、变成什么？',2,33,NULL),(6,'方向','马克-安托万·马修 ','后浪丨北京联合出版公司','《方向》即便在马修的作品中也算得最独特的：不着一字，尽得风流。原作本无一字，标题只是一个→，出版时才加了个书名Sens——既可以指“方向”，也可以指“意义”。 《方向》没有“字”，但有自己的语言——请读者在尽情释放想象力和独立思考之余，破解作者的密码，听听作者对荒诞的看法。',3,32,NULL),(8,'画的秘密','马克-安托万·马修 ','北京联合出版公司·后浪出版公司','一本关于友情的疗伤图像小说，直击人内心最为隐秘的情感。 一部追寻艺术的纸上悬疑电影，揭示命运宇宙中奇诡的真相。 ★ 《画的秘密》荣获欧洲第二大漫画节“瑞士谢尔漫画节最佳作品奖”。 作者曾两度夺得安古兰国际漫画节重要奖项。 ★ 《画的秘密》是一部罕见的、结合了拼贴、镜像、3D等叙事手法的实验型漫画作品。作者巧妙地调度光线、纬度、时间、记忆，在一个悬念重重又温情治愈的故事中，注入了一个有关命运的哲学议题。',8,44,NULL),(15,'三生三世 十里桃花','唐七公子 ','沈阳出版社','三生三世，她和他，是否终能互许一个生生世世的承诺？',9,34,NULL),(16,'控方证人','阿加莎·克里斯蒂 ','新星出版社','经典同名话剧六十年常演不衰； 比利•怀尔德执导同名电影，获奥斯卡金像奖六项提名！ 阿加莎对神秘事物的向往大约来自于一种女性祖先的遗传，在足不出户的生活里，生出对世界又好奇又恐惧的幻想。 ——王安忆 伦纳德•沃尔被控谋杀富婆艾米丽以图染指其巨额遗产，他却一再表明自己的无辜。伦纳德的妻子是唯一能够证明他无罪的证人，却以控方证人的身份出庭指证其确实犯有谋杀罪。伦纳德几乎陷入绝境，直到一个神秘女人的出现…… 墙上的犬形图案；召唤死亡的收音机；蓝色瓷罐的秘密；一只疯狂的灰猫……十一篇神秘的怪谈，最可怕的不是“幽灵”，而是你心中的魔鬼。',2,3,NULL),(17,'少有人走的路','M·斯科特·派克 ','吉林文史出版社','这本书处处透露出沟通与理解的意味，它跨越时代限制，帮助我们探索爱的本质，引导我们过上崭新，宁静而丰富的生活；它帮助我们学习爱，也学习独立；它教诲我们成为更称职的、更有理解心的父母。归根到底，它告诉我们怎样找到真正的自我。 正如开篇所言：人生苦难重重。M·斯科特·派克让我们更加清楚：人生是一场艰辛之旅，心智成熟的旅程相当漫长。但是，他没有让我们感到恐惧，相反，他带领我们去经历一系列艰难乃至痛苦的转变，最终达到自我认知的更高境界。',4,3,NULL),(18,'追寻生命的意义','[奥] 维克多·弗兰克 ','新华出版社','《追寻生命的意义》是一个人面对巨大的苦难时，用来拯救自己的内在世界，同时也是一个关于每个人存在的价值和能者多劳们生存的社会所应担负职责的思考。本书对于每一个想要了解我们这个时代的人来说，都是一部必不可少的读物。这是一部令人鼓舞的杰作……他是一个不可思议的人，任何人都可以从他无比痛苦的经历中，获得拯救自己的经验……高度推荐。',9,4,NULL),(19,'11处特工皇妃','潇湘冬儿','江苏文艺出版社','《11处特工皇妃(套装上中下册)》内容简介：她是国安局军情十一处惊才绝艳的王牌军师——收集情报、策划部署、进不友好国家布置暗杀任务……她运筹帷幄之中，决胜于千里之外，堪称军情局大厦的定海神针。',7,8,NULL),(20,'造彩虹的人','东野圭吾 ','北京十月文艺出版社','其实每个人身上都会发光，但只有纯粹渴求光芒的人才能看到。 从那一刻起，人生会发生奇妙的转折。功一高中退学后无所事事，加入暴走族消极度日；政史备战高考却无法集中精神，几近崩溃；辉美因家庭不和对生活失去勇气，决定自杀。面对糟糕的人生，他们无所适从，直到一天夜里，一道如同彩虹的光闯进视野。 凝视着那道光，原本几乎要耗尽的气力，源源不断地涌了出来。一切又开始充满希望。打起精神来，不能输。到这儿来呀，快来呀——那道光低声呼唤着。 他们追逐着呼唤，到达一座楼顶，看到一个人正用七彩绚烂的光束演奏出奇妙的旋律。 他们没想到，这一晚看到的彩虹，会让自己的人生彻底转...',9,5,NULL),(21,'流血的仕途','11','111','好看',0,1,NULL),(22,'111','11','11','11',1,11,NULL);

/*Table structure for table `classinfo` */

CREATE TABLE `classinfo` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `classid` int(11) NOT NULL,
  `classname` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `classinfo` */

insert  into `classinfo`(`id`,`classid`,`classname`) values (1,1,'马克思主义'),(2,2,'哲学'),(3,3,'社会科学总论'),(4,4,'政治法律'),(5,5,'军事'),(6,6,'经济'),(7,7,'文化'),(8,8,'语言'),(9,9,'文学'),(10,10,'艺术'),(11,11,'历史地理'),(12,12,'自然科学总论'),(13,13,' 数理科学和化学'),(14,14,'天文学、地球科学'),(15,15,'生物科学'),(16,16,'医药、卫生');

/*Table structure for table `collectrecord` */

CREATE TABLE `collectrecord` (
  `collectid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookid` bigint(20) NOT NULL COMMENT '书',
  `userid` bigint(20) NOT NULL COMMENT '用户',
  `lenddate` datetime DEFAULT NULL COMMENT '收藏日期',
  PRIMARY KEY (`collectid`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

/*Data for the table `collectrecord` */

insert  into `collectrecord`(`collectid`,`bookid`,`userid`,`lenddate`) values (1,1,2,'2021-12-21 03:00:00'),(9,2,2,'2021-12-21 00:00:00'),(10,3,3,'2021-12-21 00:00:00'),(19,2,4,'2021-12-23 12:29:58'),(20,3,4,'2021-12-23 12:31:53'),(22,6,4,'2021-12-23 12:32:20'),(43,1,4,'2021-12-23 15:49:26'),(52,1,18,'2021-12-24 20:19:03'),(53,17,4,'2021-12-24 20:47:21'),(55,19,19,'2021-12-25 22:18:11'),(56,2,44,'2022-01-05 01:50:18'),(57,1,44,'2022-01-05 01:50:21'),(58,6,49,'2022-01-05 08:23:14'),(59,1,54,'2022-01-05 15:01:12'),(61,2,54,'2022-01-05 15:03:13'),(63,1,51,'2022-01-05 19:55:48'),(64,2,51,'2022-01-05 19:55:50'),(66,6,54,'2022-01-06 08:38:46');

/*Table structure for table `lendrecord` */

CREATE TABLE `lendrecord` (
  `lendid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookid` bigint(20) NOT NULL COMMENT '书',
  `userid` bigint(20) NOT NULL COMMENT '用户',
  `lenddate` datetime DEFAULT NULL COMMENT '借书日期',
  `backdate` datetime DEFAULT NULL COMMENT '还书日期',
  `number` bigint(20) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`lendid`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

/*Data for the table `lendrecord` */

insert  into `lendrecord`(`lendid`,`bookid`,`userid`,`lenddate`,`backdate`,`number`) values (10,3,3,'2021-12-21 00:00:00','2021-12-21 00:00:00',2),(39,1,4,'2021-12-25 19:33:38','2022-01-25 19:33:38',1),(41,19,19,'2021-12-25 22:18:04','2022-01-25 22:18:04',1),(42,2,19,'2021-12-25 22:21:45','2022-01-25 22:21:45',1),(43,1,44,'2022-01-05 01:50:08','2022-02-05 01:50:08',1),(45,3,49,'2022-01-05 08:23:22','2022-02-05 08:23:22',1),(46,3,53,'2022-01-05 14:08:39','2022-02-05 14:08:39',1),(47,6,53,'2022-01-05 14:08:53','2022-02-05 14:08:53',1),(48,6,54,'2022-01-05 15:01:16','2022-02-05 15:01:16',1),(51,4,51,'2022-01-05 19:54:29','2022-02-05 19:54:29',1),(53,1,18,'2022-01-05 19:58:33','2022-02-05 19:58:33',1),(54,1,54,'2022-01-06 08:33:21','2022-02-06 08:33:21',1),(57,1,57,'2022-01-06 08:47:39','2022-02-06 08:47:39',1),(59,1,18,'2022-01-06 08:56:50','2022-02-06 08:56:50',1);

/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(128) DEFAULT NULL COMMENT '获取用户的id,做第三方登录',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `sex` varchar(4) DEFAULT NULL COMMENT '男女',
  `head_img` varchar(128) DEFAULT NULL COMMENT '头像',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`openid`,`username`,`password`,`sex`,`head_img`,`phone`,`city`,`role`) values (2,NULL,'西风','6512BD43D9CAA6E02C990B0A82652DCA','男',NULL,'98765432111','上海','user'),(4,NULL,'李四','E10ADC3949BA59ABBE56E057F20F883E','女',NULL,'123456789','泰安','user'),(5,NULL,'王二','E10ADC3949BA59ABBE56E057F20F883E','女',NULL,'12345678911','北京','user'),(6,NULL,'西翼','FCEA920F7412B5DA7BE0CF42B8C93759','女',NULL,'11112233','富阳','user'),(7,NULL,'梅子','E10ADC3949BA59ABBE56E057F20F883E','女',NULL,'2333423','青岛','user'),(10,NULL,'111111','E10ADC3949BA59ABBE56E057F20F883E','男',NULL,'12345678912','11','user'),(12,NULL,'麻子','6512BD43D9CAA6E02C990B0A82652DCA','未知',NULL,'12345678911','11','user'),(13,NULL,'王二','B706835DE79A2B4E80506F582AF3676A','男',NULL,'12345678911','泰安','user'),(14,NULL,'4444444','79B7CDCD14DB14E9CB498F1793817D69','不限',NULL,'4444444','444444444','user'),(18,NULL,'admin','21232F297A57A5A743894A0E4A801FC3','未知',NULL,'12345678910','大阜阳',NULL),(19,NULL,'dss','07304E56C452BE73AD2B51A4647D0300','男',NULL,'11111111111','大阜阳',NULL),(20,NULL,'王三','96E79218965EB72C92A549DD5A330112','男',NULL,'12345678901','东城',NULL),(49,'63773D5DDD3211DD7A121EF14EE36AEC','微放',NULL,'男','http://qzapp.qlogo.cn/qzapp/101983512/63773D5DDD3211DD7A121EF14EE36AEC/50',NULL,'',NULL),(51,'EBEFC02F819D8138003B8C949A6865C2','Jeff',NULL,'男','http://qzapp.qlogo.cn/qzapp/101983512/EBEFC02F819D8138003B8C949A6865C2/50',NULL,'',NULL),(54,'3F5E60E800AB063AA2ED676C2FA03BBB','Ding',NULL,'男','http://qzapp.qlogo.cn/qzapp/101983512/3F5E60E800AB063AA2ED676C2FA03BBB/50',NULL,'马扎里沙里夫',NULL),(57,NULL,'李二','E10ADC3949BA59ABBE56E057F20F883E','男',NULL,'12345678911','阜阳1',NULL),(58,NULL,'11','B59C67BF196A4758191E42F76670CEBA','男',NULL,'12345678909','11',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
