package edu.fudan.cs.schapp.util

import org.springframework.stereotype.Repository;

import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User

@Repository
class CachedDataProvider {
	List<Role> roles
	List<User> users
	List<Course> courses
	int seq_roles=40,seq_users=2000,seq_courses=400
	public CachedDataProvider() {

		def admin=new Role(id:1,name:'admin')
		def teacher=new Role(id:2,name:'teacher')
		def student=new Role(id:3,name:'student')
		roles=[
			admin,teacher,student
		]

		users=[
			new User(id:30,code:'8888',username:'admin',password:'1',main_role:admin),
			new User(id:101,code:'500101',username:'Dr Wang',password:'1',main_role:teacher),
			new User(id:102,code:'500102',username:'Dr Ellison',password:'1',main_role:teacher),
			new User(id:103,code:'500103',username:'Assist Prof Zhou',password:'1',main_role:teacher),
			new User(id:104,code:'500104',username:'Prof Zhou',password:'1',main_role:teacher),
			new User(id:105,code:'500105',username:'Dr Ritchie',password:'1',main_role:teacher),
			new User(id:106,code:'500106',username:'Dr Stroustrup',password:'1',main_role:teacher),
			new User(id:107,code:'500107',username:'Dr Zhen',password:'1',main_role:teacher),
			new User(id:108,code:'500108',username:'Михайлович ',password:'1',main_role:teacher),
			new User(id:109,code:'500109',username:'Prof Wu',password:'1',main_role:teacher),
			new User(id:110,code:'500110',username:'Prof Brin',password:'1',main_role:teacher),
			new User(id:111,code:'500111',username:'Prof Schmidt',password:'1',main_role:teacher),
			new User(id:112,code:'500112',username:'Prof Sun',password:'1',main_role:teacher),
			new User(id:113,code:'500113',username:'Prof Li',password:'1',main_role:teacher),
			new User(id:114,code:'500114',username:'Michio Kaku',password:'1',main_role:teacher),
			new User(id:115,code:'500115',username:'Dr Green',password:'1',main_role:teacher),
			new User(id:116,code:'500116',username:'Dr Jobs',password:'1',main_role:teacher),
			new User(id:117,code:'500117',username:'Dr Bar',password:'1',main_role:teacher),
			new User(id:118,code:'500118',username:'Dr Foo',password:'1',main_role:teacher),
			new User(id:1001,code:'14210241001',username:'student001',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1002,code:'14210241002',username:'student002',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1003,code:'14210241003',username:'student003',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1004,code:'14210241004',username:'student004',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1005,code:'14210241005',username:'student005',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1006,code:'14210241006',username:'student006',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1007,code:'14210241007',username:'student007',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1008,code:'14210241008',username:'student008',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1009,code:'14210241009',username:'student009',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1010,code:'14210241010',username:'student010',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1011,code:'14210241011',username:'student011',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1012,code:'14210241012',username:'student012',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1013,code:'14210241013',username:'student013',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1014,code:'14210241014',username:'student014',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1015,code:'14210241015',username:'student015',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1016,code:'14210241016',username:'student016',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1017,code:'14210241017',username:'student017',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1018,code:'14210241018',username:'ABC',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1019,code:'14210241019',username:'DEF',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1020,code:'14210241020',username:'GDD',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1021,code:'14210241021',username:'ESW',password:'1',main_role:student,major:'计算机技术'),
			new User(id:1022,code:'14210241022',username:'TWQ',password:'1',main_role:student,major:'计算机技术'),
		]
		users.each {
			it.department='计算机科学技术学院'			
		}
//		for (int i=1101;i<1140;i++){
//			users.add(
//				new User(id:i,code:'1421024'+i,username:'stu'+i,password:'1',main_role:student
//					,department:'软件学院',major:'软件工程')
//			)
//		}
		courses=[
			new Course(id:1,location:'Z2203',name:'C++程序设计',teacher: users[6] ),
			new Course(id:2,location:'Z2206',name:'数据结构',teacher: users[2]),
			new Course(id:3,location:'Z2205',name:'数据库系统概论',teacher: users[1]),
			new Course(id:4,location:'Z2202',name:'计算机组成原理',teacher: users[3]),
			new Course(id:5,location:'Z2213',name:'线性代数',teacher: users[4]),
			new Course(id:6,location:'Z2211',name:'现代几何',teacher: users[8]),
			new Course(id:7,location:'Z2203',name:'拓扑学',teacher: users[11]),
			new Course(id:8,location:'Z2101',name:'数学分析',teacher: users[10]),
			new Course(id:9,location:'Z2102',name:'算法导论',teacher: users[9]),
			new Course(id:10,location:'H5203',name:'高等数学',teacher: users[8]),
			new Course(id:11,location:'H5203',name:'英语口语与写作',teacher: users[11]),
			new Course(id:12,location:'H5101',name:'基础日语',teacher: users[14]),
			new Course(id:13,location:'Z2203',name:'操作系统',teacher: users[13]),
			new Course(id:14,location:'Z2203',name:'Java程序设计',teacher: users[5]),
			new Course(id:15,location:'Z2203',name:'UML应用基础',teacher: users[7]),
			new Course(id:16,location:'Z2223',name:'软件过程',teacher: users[12]),
			new Course(id:17,location:'Z2201',name:'面向对象的程序设计',teacher: users[9]),
			new Course(id:18,location:'Z2403',name:'软件测试',teacher: users[8]),
			new Course(id:19,location:'HA203',name:'机器学习',teacher: users[7]),
			new Course(id:20,location:'HA203',name:'人工智能',teacher: users[14]),
			new Course(id:21,location:'HA103',name:'编译原理',teacher: users[17]),
			new Course(id:22,location:'HA111',name:'计算机网络',teacher: users[15]),
			new Course(id:23,location:'HB333',name:'无线网络',teacher: users[16]),
			new Course(id:24,location:'G2211',name:'密码学与信息安全',teacher: users[18])
		]
		courses.each {it.credit=2}
		
	}
}
