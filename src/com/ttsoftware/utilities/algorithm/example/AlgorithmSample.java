//package com.ttsoftware.utilities.algorithm.example;
//
//public class AlgorithmSample {
//
//
//
//
//
//	245
//	【程序13】
//	246
//	题目：一个整数，它加上100后是一个完全平方数，加上168又是一个完全平方数，请问该数是多少？
//	247
//	1.程序分析：在10万以内判断，先将该数加上100后再开方，再将该数加上268后再开方，如果开方后的结果满足如下条件，即是结果。请看具体
//	248
//	分析：
//	249
//	public class test { public static void main (String[]args){
//	250
//	long k=0; for(k=1;k<=100000l;k++) if(Math.floor(Math.sqrt(k+100))==Math.sqrt(k+100) &&
//	251
//	Math.floor(Math.sqrt(k+168))==Math.sqrt(k+168))
//	252
//	System.out.println(k);
//	253
//	}
//	254
//	}
//	255
//	【程序14】题目：输入某年某月某日，判断这一天是这一年的第几天？
//	256
//	1.程序分析：以3月5日为例，应该先把前两个月的加起来，然后再加上5天即本年的第几天，特殊情况，闰年且输入月份大于3时需考虑多加一
//	257
//	天。
//	258
//	import java.util.*; public class test { public static void main (String[]args){ int day=0; int month=0; int year=0; int sum=0; int leap;
//	259
//	System.out.print("请输入年,月,日\n");
//	260
//	Scanner input = new Scanner(System.in);
//	261
//	year=input.nextInt();
//	262
//	month=input.nextInt();
//	263
//	day=input.nextInt();
//	264
//	switch(month) /*先计算某月以前月份的总天数*/
//	265
//	{case 1:
//	266
//	sum=0;break; case 2:
//	267
//	sum=31;break; case 3:
//	268
//	sum=59;break; case 4:
//	269
//	sum=90;break; case 5:
//	270
//	sum=120;break; case 6:
//	271
//	sum=151;break; case 7:
//	272
//	sum=181;break; case 8:
//	273
//	sum=212;break; case 9:
//	274
//	sum=243;break; case 10:
//	275
//	sum=273;break; case 11:
//	276
//	sum=304;break; case 12:
//	277
//	sum=334;break; default:
//	278
//	System.out.println("data error");break;
//	279
//	}
//	280
//	sum=sum+day; /*再加上某天的天数*/
//	281
//	if(year%400==0||(year%4==0&&year%100!=0))/*判断是不是闰年*/
//	282
//	elseleap=1;
//	283
//	leap=0;
//	284
//	if(leap==1 && month>2)/*如果是闰年且月份大于2,总天数应该加一天*/
//	285
//	sum++;
//	286
//	System.out.println("It is the the day:"+sum);
//	287
//	}
//	288
//	}
//	289
//	【程序15】题目：输入三个整数x,y,z，请把这三个数由小到大输出。
//	290
//	1.程序分析：我们想办法把最小的数放到x上，先将x与y进行比较，如果x> y则将x与y的值进行交换，然后再用x与z进行比较，如果x> z则
//	291
//	将x与z的值进行交换，这样能使x最小。
//	292
//	import java.util.*; public class test { public static void main (String[]args){ int i=0; int j=0; int k=0; int x=0;
//	293
//	System.out.print("请输入三个数\n");
//	294
//	Scanner input = new Scanner(System.in);
//	295
//	i=input.nextInt();
//	296
//	j=input.nextInt();
//	297
//	k=iifnput.nextInt(); (i>j)
//	298
//	{
//	299
//	x=i;
//	300
//	i=j;
//	301
//	j=x;
//	302
//	}if(i>k)
//	303
//	{
//	304
//	x=i;
//	305
//	i=k;
//	306
//	k=x;
//	307
//	}if(j>k)
//	308
//	{
//	309
//	x=j;
//	310
//	j=k;
//	311
//	k=x;
//	312
//	}
//	313
//	System.out.println(i+", "+j+", "+k);
//	314
//	}
//	315
//	}
//	316
//	【程序16】题目：输出9*9口诀。
//	317
//	1.程序分析：分行与列考虑，共9行9列，i控制行，j控制列。
//	318
//	public class jiujiu { public static void main(String[] args)
//	319
//	{ int i=0; int j=0; for(i=1;i<=9;i++)
//	320
//	{ for(j=1;j<=9;j++)
//	321
//	System.out.print(i+"*"+j+"="+i*j+"\t");
//	322
//	System.out.println();
//	323
//	}
//	324
//	}}
//	325
//	不出现重复的乘积(下三角)
//	326
//	public class jiujiu { public static void main(String[] args)
//	327
//	{ int i=0; int j=0; for(i=1;i<=9;i++)
//	328
//	{ for(j=1;j<=i;j++)
//	329
//	System.out.print(i+"*"+j+"="+i*j+"\t");
//	330
//	System.out.println();
//	331
//	}
//	332
//	}}
//	333
//	上三角
//	334
//	public class jiujiu { public static void main(String[] args)
//	335
//	{ int i=0; int j=0; for(i=1;i<=9;i++)
//	336
//	{ for(j=i;j<=9;j++)
//	337
//	System.out.print(i+"*"+j+"="+i*j+"\t");
//	338
//	System.out.println();
//	339
//	}
//	340
//	}}
//	341
//	【程序17】题目：猴子吃桃问题：猴子第一天摘下若干个桃子，当即吃了一半，还不瘾，又多吃了一个 第二天早上又将剩下的桃子吃掉一半，
//	342
//	又多吃了一个。以后每天早上都吃了前一天剩下的一半零一个。到第10天早上想再吃时，见只剩下一个桃子了。求第一天共摘了多少。
//	343
//	1.程序分析：采取逆向思维的方法，从后往前推断。
//	344
//	public class 猴子吃桃 {
//	345
//	static int total(int day){ if(day == 10){ return 1;
//	346
//	}else{ return (total(day+1)+1)*2;
//	347
//	}
//	348
//	public s}tatic void main(String[] args)
//	349
//	{
//	350
//	System.out.println(total(1));
//	351
//	}}
//	352
//	【程序18】题目：两个乒乓球队进行比赛，各出三人。甲队为a,b,c三人，乙队为x,y,z三人。已抽签决定比赛名单。有人向队员打听比赛的名
//	353
//	单。a说他不和x比，c说他不和x,z比，请编程序找出三队赛手的名单。
//	354
//	1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除， 则表明此数不是素数，反之是素数。
//	355
//	import java.util.ArrayList; public class pingpang {
//	356
//	pSutbrliincg sat,abt,icc; void main(String[] args) {
//	357
//	String[] op = { "x", "y", "z" };
//	358
//	ArrayList<pingpang> arrayList=new ArrayList<pingpang>(); for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) for (int k = 0; k < 3; k++) {
//	359
//	pingpang a=new pingpang(op[i],op[j],op[k]); if(!a.a.equals(a.b)&&!a.b.equals(a.c)&&!a.a.equals("x")
//	360
//	&&!a.c.equals("x")&&!a.c.equals("z")){
//	361
//	arrayList.add(a);
//	362
//	}
//	363
//	for}(Object a:arrayList){
//	364
//	System.out.println(a);
//	365
//	}
//	366
//	}public pingpang(String a, String b, String c) { super(); this.a = a; this.b = b; this.c = c;
//	367
//	}
//	368
//	@pOubvelircride String toString() {
//	369
//	// TODO Auto-generated method stub
//	370
//	return "a的对手是"+a+","+"b的对手是"+b+","+"c的对手是"+c+"\n";
//	371
//	}
//	372
//	}
//	373
//	【程序19】题目：打印出如下图案（菱形）
//	374
//	*
//	375
//	***
//	376
//	******
//	377
//	********
//	378
//	******
//	379
//	***
//	380
//	*
//	381
//	1.程序分析：先把图形分成两部分来看待，前四行一个规律，后三行一个规律，利用双重 for循环，第一层控制行，第二层控制列。
//	382
//	三角形：
//	383
//	public class StartG { public static void main(String [] args)
//	384
//	{ int i=0; int j=0; for(i=1;i<=4;i++)
//	385
//	{ for(j=1;j<=2*i-1;j++)
//	386
//	System.out.print("*");
//	387
//	System.out.println("");
//	388
//	}for(i=4;i>=1;i--)
//	389
//	{ for(j=1;j<=2*i-3;j++)
//	390
//	System.out.print("*");
//	391
//	System.out.println("");
//	392
//	}
//	393
//	}
//	394
//	}
//	395
//	菱形：
//	396
//	public class StartG { public static void main(String [] args)
//	397
//	{ int i=0; int j=0;
//	398
//	for(i=1;i<=4;i++)
//	399
//	{ for(int k=1; k<=4-i;k++)
//	400
//	System.out for .print(" "); (j=1;j<=2*i-1;j++)
//	401
//	System.out.print("*");
//	402
//	System.out.println("");
//	403
//	}for(i=4;i>=1;i--)
//	404
//	{ for(int k=1; k<=5-i;k++)
//	405
//	for System.out.print(" "); (j=1;j<=2*i-3;j++)
//	406
//	System.out.print("*");
//	407
//	System.out.println("");
//	408
//	}
//	409
//	}
//	410
//	}
//	411
//	【程序20】题目：有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...求出这个数列的前20项之和。
//	412
//	1.程序分析：请抓住分子与分母的变化规律。
//	413
//	public class test20 { public static void main(String[] args) { float fm = 1f; float fz = 1f; float temp; float sum = 0f; for (int i=0;i<20;i++){
//	414
//	temp = fm;
//	415
//	fm = fz;
//	416
//	fz = fz + temp;
//	417
//	sum += fz/fm;
//	418
//	//System.out.println(sum);
//	419
//	}
//	420
//	System.out.println(sum);
//	421
//	}
//	422
//	}
//	423
//	【程序21】题目：求1+2!+3!+...+20!的和
//	424
//	1.程序分析：此程序只是把累加变成了累乘。
//	425
//	public class Ex21 { static long sum = 0; static long fac = 0; public static void main(String[] args) { long sum = 0; long fac = 1; for(int i=1; i<=10; i++) {
//	426
//	fac = fac * i;
//	427
//	sum += fac;
//	428
//	}
//	429
//	System.out.println(sum);
//	430
//	}}
//	431
//	【程序22】题目：利用递归方法求5!。
//	432
//	1.程序分析：递归公式：fn=fn_1*4!
//	433
//	import java.util.Scanner; public class Ex22 { public static void main(String[] args) {
//	434
//	Scanner s = new Scanner(System.in); int n = s.nextInt();
//	435
//	Ex22 tfr = new Ex22();
//	436
//	System.out.println(tfr.recursion(n));
//	437
//	}
//	438
//	public long recursion(int n) { long value = 0 ; if(n ==1 || n == 0) {
//	439
//	value = 1;
//	440
//	} else if(n > 1) {
//	441
//	value = n * recursion(n-1);
//	442
//	}return value;
//	443
//	}}
//	444
//	【程序23】题目：有5个人坐在一起，问第五个人多少岁？他说比第4个人大2岁。问第4个人岁数，他说比第3个人大2岁。问第三个人，又说比
//	445
//	第2人大两岁。问第2个人，说比第一个人大两岁。最后问第一个人，他说是10岁。请问第五个人多大？
//	446
//	1.程序分析：利用递归的方法，递归分为回推和递推两个阶段。要想知道第五个人岁数，需知道第四人的岁数，依次类推，推到第一人（10岁），
//	447
//	再往回推。
//	448
//	public class Ex23 { static int getAge(int n){ if (n==1){ return 10;
//	449
//	}return 2 + getAge(n-1);
//	450
//	}public static void main(String[] args) {
//	451
//	System.out.println("第五个的年龄为:"+getAge(5));
//	452
//	}
//	453
//	}
//	454
//	【程序24】题目：给一个不多于5位的正整数，要求：一、求它是几位数，二、逆序打印出各位数字。
//	455
//	import java.util.Scanner; public class Ex24 { public static void main(String[] args) {
//	456
//	Ex24 tn = new Ex24();
//	457
//	Scanner s = new Scanner(System.in); long a = s.nextLong(); if(a < 0 || a > 100000) {
//	458
//	System.out.println("Error Input, please run this program Again");
//	459
//	System.exit(0);
//	460
//	}if(a >=0 && a <=9) {
//	461
//	System.out.println( a + "是一位数");
//	462
//	System.out.println("按逆序输出是" + '\n' + a);
//	463
//	} else if(a >= 10 && a <= 99) {
//	464
//	System.out.println(a + "是二位数");
//	465
//	System.out.println("按逆序输出是" );
//	466
//	tn.converse(a);
//	467
//	} else if(a >= 100 && a <= 999) {
//	468
//	System.out.println(a + "是三位数");
//	469
//	System.out.println("按逆序输出是" );
//	470
//	tn.converse(a);
//	471
//	} else if(a >= 1000 && a <= 9999) {
//	472
//	System.out.println(a + "是四位数");
//	473
//	System.out.println("按逆序输出是" );
//	474
//	tn.converse(a);
//	475
//	} else if(a >= 10000 && a <= 99999) {
//	476
//	System.out.println(a + "是五位数");
//	477
//	System.out.println("按逆序输出是" );
//	478
//	tn.converse(a);
//	479
//	}
//	480
//	}public void converse(long l) {
//	481
//	String s = Long.toString char (l); [] ch = s.toCharArray(); for(int i=ch.length-1; i>=0; i--) {
//	482
//	System.out.print(ch[i]);
//	483
//	}
//	484
//	}}
//	485
//	【程序25】题目：一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同。
//	486
//	import java.util.Scanner; public class Ex25 { static int[] a = new int[5]; static int[] b = new int[5]; public static void main(String[] args) { boolean is =false;
//	487
//	Scanner s = new Scanner(System.in); long l = s.nextLong(); if (l > 99999 || l < 10000) {
//	488
//	System.out.println("Input error, please input again!");
//	489
//	l = s.nextLong();
//	490
//	}for (int i = 4; i >= 0; i--) { a[i] = (int) (l / (long) Math.pow(10, i));
//	491
//	l =(l % ( long) Math.pow(10, i));
//	492
//	}
//	493
//	Sfyosrtem.out.println(); (int i=0,j=0; i<5; i++, j++) {
//	494
//	b[j] = a[i];
//	495
//	}for(int i=0,j=4; i<5; i++, j--) { if(a[i] != b[j]) {
//	496
//	is = false; break;
//	497
//	} else {
//	498
//	is = true;
//	499
//	}
//	500
//	}if(is == false) {
//	501
//	System.out.println("is not a Palindrom!");
//	502
//	} else if(is == true) {
//	503
//	System.out.println("is a Palindrom!");
//	504
//	}
//	505
//	}}
//	506
//	【程序26】题目：请输入星期几的第一个字母来判断一下是星期几，如果第一个字母一样，则继续 判断第二个字母。
//	507
//	1.程序分析：用情况语句比较好，如果第一个字母一样，则判断用情况语句或if语句判断第二个字母。
//	508
//	import java.util.Scanner; public class Ex26 { public static void main(String[] args){
//	509
//	//保存用户输入的第二个字母
//	510
//	char weekSecond;
//	511
//	//将Scanner类示例化为input对象，用于接收用户输入
//	512
//	Scanner input = new Scanner(System.in);
//	513
//	//开始提示并接收用户控制台输入
//	514
//	System.out.print("请输入星期值英文的第一个字母，我来帮您判断是星期几：");
//	515
//	String letter = input.next();
//	516
//	//判断用户控制台输入字符串长度是否是一个字母
//	517
//	if (letter.length() == 1){
//	518
//	//利用取第一个索引位的字符来实现让Scanner接收char类型输入
//	519
//	char weekFirst = letter.charAt(0); switch (weekFirst){ case 'm':
//	520
//	//当输入小写字母时，利用switch结构特性执行下一个带break语句的case分支，以实现忽略用户控制台输入大小写敏感的功
//	521
//	能
//	522
//	case 'M':
//	523
//	System.out.println("星期一(Monday)");
//	524
//	break; case 't':
//	525
//	//当输入小写字母时，利用switch结构特性执行下一个带break语句的case分支，以实现忽略用户控制台输入大小写敏感的功
//	526
//	能
//	527
//	case 'T':
//	528
//	System.out.print("由于星期二(Tuesday)与星期四(Thursday)均以字母T开头，故需输入第二个字母才能正确判
//	529
//	断：");
//	530
//	letter = input.next();
//	531
//	//判断用户控制台输入字符串长度是否是一个字母
//	532
//	if (letter.length() == 1){
//	533
//	//利用取第一个索引位的字符来实现让Scanner接收char类型输入
//	534
//	weekSecond = letter.charAt(0);
//	535
//	//利用或（||）运算符来实现忽略用户控制台输入大小写敏感的功能
//	536
//	if (weekSecond == 'U' || weekSecond == 'u'){
//	537
//	System.out.println("星期二(Tuesday)");
//	538
//	break;
//	539
//	//利用或（||）运算符来实现忽略用户控制台输入大小写敏感的功能
//	540
//	} else if (weekSecond == 'H' || weekSecond == 'h'){
//	541
//	System.out.println("星期四(Thursday)");
//	542
//	break;
//	543
//	//控制台错误提示
//	544
//	} else{
//	545
//	System.out.println("输入错误，不能识别的星期值第二个字母，程序结束！");
//	546
//	break;
//	547
//	}
//	548
//	} else {
//	549
//	//控制台错误提示
//	550
//	System.out.println("输入错误，只能输入一个字母，程序结束！");
//	551
//	break;
//	552
//	ca}se 'w':
//	553
//	//当输入小写字母时，利用switch结构特性执行下一个带break语句的case分支，以实现忽略用户控制台输入大小写敏感的功
//	554
//	能
//	555
//	case 'W':
//	556
//	System.out.println("星期三(Wednesday)");
//	557
//	break; case 'f':
//	558
//	//当输入小写字母时，利用switch结构特性执行下一个带break语句的case分支，以实现忽略用户控制台输入大小写敏感的功
//	559
//	能
//	560
//	case 'F':
//	561
//	System.out.println("星期五(Friday)");
//	562
//	break; case 's':
//	563
//	//当输入小写字母时，利用switch结构特性执行下一个带break语句的case分支，以实现忽略用户控制台输入大小写敏感的功
//	564
//	能
//	565
//	case 'S':
//	566
//	System.out.print("由于星期六(Saturday)与星期日(Sunday)均以字母S开头，故需输入第二个字母才能正确判断：");
//	567
//	letter = input.next();
//	568
//	//判断用户控制台输入字符串长度是否是一个字母
//	569
//	if (letter.length() == 1){
//	570
//	//利用取第一个索引位的字符来实现让Scanner接收char类型输入
//	571
//	weekSecond = letter.charAt(0);
//	572
//	//利用或（||）运算符来实现忽略用户控制台输入大小写敏感的功能
//	573
//	if (weekSecond == 'A' || weekSecond == 'a'){
//	574
//	System.out.println("星期六(Saturday)");
//	575
//	break;
//	576
//	//利用或（||）运算符来实现忽略用户控制台输入大小写敏感的功能
//	577
//	} else if (weekSecond == 'U' || weekSecond == 'u'){
//	578
//	System.out.println("星期日(Sunday)");
//	579
//	break;
//	580
//	//控制台错误提示
//	581
//	} else{
//	582
//	System.out.println("输入错误，不能识别的星期值第二个字母，程序结束！");
//	583
//	break;
//	584
//	}
//	585
//	} else{
//	586
//	//控制台错误提示
//	587
//	System.out.println("输入错误，只能输入一个字母，程序结束！");
//	588
//	break;
//	589
//	de}fault:
//	590
//	//控制台错误提示
//	591
//	System.out.println("输入错误，不能识别的星期值第一个字母，程序结束！");
//	592
//	break;
//	593
//	}
//	594
//	} else{
//	595
//	//控制台错误提示
//	596
//	System.out.println("输入错误，只能输入一个字母，程序结束！");
//	597
//	}
//	598
//	}
//	599
//	}
//	600
//	【程序27】题目：求100之内的素数
//	601
//	public class Ex27 { public static void main(String args[])
//	602
//	{int sum,i; for(sum=2;sum<=100;sum++)
//	603
//	{for(i=2;i<=sum/2;i++)
//	604
//	{if(sum%i==0) break;
//	605
//	}if(i>sum/2)
//	606
//	System.out.println(sum+"是素数");
//	607
//	}
//	608
//	}
//	609
//	}
//	610
//	【程序28】题目：对10个数进行排序
//	611
//	1.程序分析：可以利用选择法，即从后9个比较过程中，选择一个最小的与第一个元素交换， 下次类推，即用第二个元素与后8个进行比较，并进
//	612
//	行交换。
//	613
//	import java.util.Arrays; import java.util.Random; import java.util.Scanner; public class Ex28 { public static void main(String[] args) { int arr[] = new int[11];
//	614
//	Random r=new Random(); for(int i=0;i<10;i++){
//	615
//	arr[i]=r.nextInt(100)+1;//得到10个100以内的整数
//	616
//	}
//	617
//	Arrays.sort for (arr); (int i=0;i<arr.length;i++){
//	618
//	System.out.print(arr[i]+"\t");
//	619
//	}
//	620
//	System.out.print("\nPlease Input a int number: ");
//	621
//	Scanner sc=new Scanner(System.in);
//	622
//	arr[10]=sc.nextInt();//输入一个int值
//	623
//	Afrorrays.sort(arr); (int i=0;i<arr.length;i++){
//	624
//	System.out.print(arr[i]+"\t");
//	625
//	}
//	626
//	}
//	627
//	}
//	628
//	【程序29】题目：求一个3*3矩阵对角线元素之和
//	629
//	1.程序分析：利用双重for循环控制输入二维数组，再将a[i][i]累加后输出。
//	630
//	public class Ex29 { public static void main(String[] args){ double sum=0; int array[][]={{1,2,3},{4,5, 6},{7,7,8}}; for(int i=0;i<3;i++) for(int j=0;j<3;j++){ if(i==j)
//	631
//	sum=sum + array[i][j];
//	632
//	}
//	633
//	System.out.println( sum);
//	634
//	}
//	635
//	}
//	636
//	【程序30】题目：有一个已经排好序的数组。现输入一个数，要求按原来的规律将它插入数组中。
//	637
//	1. 程序分析：首先判断此数是否大于最后一个数，然后再考虑插入中间的数的情况，插入后此元素之后的数，依次后移一个位置。
//	638
//	import java.util.Random; public class ArraySort { public static void main(String[] args)
//	639
//	{ int temp=0; int myarr[] = new int[12];
//	640
//	Random r=new Random(); for(int i=1;i<=10;i++)
//	641
//	fomryarr[i]=r.nextInt(1000); (int k=1;k<=10;k++)
//	642
//	fSoyrstem.out.print(myarr[k]+","); (int i=1;i<=9;i++) for(int k=i+1;k<=10;k++) if(myarr[i]>myarr[k])
//	643
//	{
//	644
//	temp=myarr[i];
//	645
//	myarr[i]=myarr[k];
//	646
//	myarr[k]=temp;
//	647
//	}
//	648
//	fSoyrstem.out.println(""); (int k=1;k<=10;k++)
//	649
//	System.out.print(myarr[k]+",");
//	650
//	fmoyarrr[11]=r.nextInt(1000); (int k=1;k<=10;k++) if(myarr[k]>myarr[11])
//	651
//	{
//	652
//	fteomrp=myarr[11]; (int j=11;j>=k+1;j--)
//	653
//	myarr[j]=myarr[j-1];
//	654
//	myarr[k]=temp;
//	655
//	}
//	656
//	forSystem.out.println(""); (int k=1;k<=11;k++)
//	657
//	System.out.print(myarr[k]+",");
//	658
//	}
//	659
//	}
//	660
//	【程序31】题目：将一个数组逆序输出。
//	661
//	程序分析：用第一个与最后一个交换。
//	662
//	其实，用循环控制变量更简单：
//	663
//	for(int k=11;k>=1;k--)
//	664
//	System.out.print(myarr[k]+",");
//	665
//	【程序32】题目：取一个整数a从右端开始的4～7位。
//	666
//	程序分析：可以这样考虑：
//	667
//	(1)先使a右移4位。
//	668
//	(2)设置一个低4位全为1,其余全为0的数。可用~(~0 < <4)
//	669
//	(3)将上面二者进行&运算。
//	670
//	public class Ex32 { public static void main(String[] args)
//	671
//	{ int a=0; long b=18745678;
//	672
//	a=(int) Math.floor(b % Math.pow(10,7)/Math.pow(10, 3));
//	673
//	System.out.println(a);
//	674
//	}}
//	675
//	【程序33】
//	676
//	题目：打印出杨辉三角形（要求打印出10行如下图）
//	677
//	1.程序分析：
//	678
//	1
//	679
//	1 1
//	680
//	1 2 1
//	681
//	1 3 3 1
//	682
//	1 4 6 4 1
//	683
//	1 5 10 10 5 1
//	684
//	public class Ex33 { public static void main(String args[]){ int i,j; int a[][];
//	685
//	a=new int[8][8]; for(i=0;i<8;i++){
//	686
//	a[i][i]=1;
//	687
//	a[i][0]=1;
//	688
//	for}(i=2;i<8;i++){ for(j=1;j<=i-1;j++){
//	689
//	a[i][j]=a[i-1][j-1]+a[i-1][j];
//	690
//	}
//	691
//	}for(i=0;i<8;i++){ for(j=0;j<i;j++){
//	692
//	System.out.printf(" "+a[i][j]);
//	693
//	}
//	694
//	System.out.println();
//	695
//	}
//	696
//	}
//	697
//	}
//	698
//	【程序34】题目：输入3个数a,b,c，按大小顺序输出。
//	699
//	1.程序分析：利用指针方法。
//	700
//	public class Ex34 { public static void main(String[] args)
//	701
//	{int []arrays = {800,56,500}; for(int i=arrays.length;--i>=0;)
//	702
//	{for(int j=0;j<i;j++)
//	703
//	{if(arrays[j]>arrays[j+1])
//	704
//	{int temp=arrays[j];
//	705
//	arrays[j]=arrays[j+1];
//	706
//	arrays[j+1]=temp;
//	707
//	}}} for(int n=0;n<arrays.length;n++)
//	708
//	System.out.println(arrays[n]);
//	709
//	}
//	710
//	}
//	711
//	【程序35】题目：输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，输出数组。
//
//	import java.util.*; public class Ex35 { public static void main(String[] args) { int i, min, max, n, temp1, temp2; int a[];
//	713
//	System.out.println("输入数组的长度:");
//	714
//	Scanner keyboard = new Scanner(System.in);
//	715
//	n = keyboard.nextInt();
//	716
//	a = new int[n]; for (i = 0; i < n; i++) {
//	717
//	System.out.print("输入第" + (i + 1) + "个数据");
//	718
//	a[i] = keyboard.nextInt();
//	719
//	}
//	720
//	//以上是输入整个数组
//	721
//	max = 0;
//	722
//	min = 0;
//	723
//	//设置两个标志,开始都指向第一个数
//	724
//	for (i = 1; i < n; i++) { if (a[i] > a[max])
//	725
//	max = i; //遍历数组,如果大于a[max]，就把他的数组下标赋给max
//	726
//	if (a[i] < a[min])
//	727
//	min = i; //同上，如果小于a[min],就把他的数组下标赋给min
//	728
//	}
//	729
//	//以上for循环找到最大值和最小值，max是最大值的下标，min是最小值的下标
//	730
//	temp1 = a[0];
//	731
//	temp2 = a[min]; //这两个temp只是为了在交换时使用
//	732
//	a[0] = a[max];
//	733
//	a[max] = temp1; //首先交换a[0]和最大值a[max]
//	734
//	if (min != 0) { //如果最小值不是a[0]，执行下面
//	735
//	a[min] = a[n - 1];
//	736
//	a[n - 1] = temp2; //交换a[min]和a[n-1]
//	737
//	} else { //如果最小值是a[0],执行下面
//	738
//	a[max] = a[n - 1];
//	739
//	a[n - 1] = temp1;
//	740
//	}
//	741
//	for (i = 0; i < n; i++) { //输出数组
//	742
//	System.out.print(a[i] + " ");
//	743
//	}}}
//	744
//	【程序36】题目：有n个整数，使其前面各数顺序向后移m个位置，最后m个数变成最前面的m个数
//	745
//	【程序37】
//	746
//	题目：有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
//	747
//	import java.util.Scanner; public class Ex37 { public static void main(String[] args) {
//	748
//	Scanner s = new Scanner(System.in); int n = s.nextInt(); boolean[] arr = new boolean[n]; for(int i=0; i<arr.length; i++) {
//	749
//	arr[i] = true;//下标为TRUE时说明还在圈里
//	750
//	}int leftCount = n; int countNum = 0; int index = 0; while(leftCount > 1) {
//	751
//	if(arr[index] == true) {//当在圈里时
//	752
//	countNum ++; //报数递加
//	753
//	if(countNum == 3) {//报道3时
//	754
//	countNum =0;//从零开始继续报数
//	755
//	arr[index] = false;//此人退出圈子
//	756
//	leftCount --;//剩余人数减一
//	757
//	}
//	758
//	}
//	759
//	index ++;//每报一次数，下标加一
//	760
//	if(index == n) {//是循环数数，当下标大于n时，说明已经数了一圈，
//	761
//	index = 0;//将下标设为零重新开始。
//	762
//	}
//	763
//	}for(int i=0; i<n; i++) { if(arr[i] == true) {
//	764
//	System.out.println(i);
//	765
//	}
//	766
//	}
//
//	}
//
//	}
//
//	【程序38】
//
//	题目：写一个函数，求一个字符串的长度，在main函数中输入字符串，并输出其长度。
//
//	import java.util.Scanner; public class Ex38 { public static void main(String [] args)
//
//	{
//
//	Scanner s = new Scanner(System.in);
//
//	System.out.println("请输入一个字符串");
//
//	String mys= s.next();
//
//	System.out.println(str_len(mys));
//
//	} public static int str_len(String x)
//
//	{ return x.length();
//
//	}
//
//	}
//
//	题目：编写一个函数，输入n为偶数时，调用函数求1/2+1/4+...+1/n,当输入n为奇数时，调用函数1/1+1/3+...+1/n
//
//	【程序39】
//
//	题目：字符串排序。
//
//	import java.util.*; public class test{ public static void main(String[] args)
//
//	{
//
//	ArrayList<String> list=new ArrayList<String>();
//
//	list.add("010101");
//
//	list.add("010003");
//
//	list.add("010201");
//
//	Collections.sort for (list); (int i=0;i<list.size();i++){
//
//	System.out.println(list.get(i));
//
//	}}}
//
//
//	/*【程序40】
//	题目：海滩上有一堆桃子，五只猴子来分。第一只猴子把这堆桃子凭据分为五份，多了一个，这只猴子把多的一个扔入海中，拿走了一份。第二只
//	猴子把剩下的桃子又平均分成五份，又多了一个，它同样把多的一个扔入海中，拿走了一份，第三、第四、第五只猴子都是这样做的，问海滩上原
//	来最少有多少个桃子？*/
//	public class Dg {
//
//	static int ts=0;//桃子总数
//
//	int fs=1;//记录分的次数
//
//	static int hs=5;//猴子数...
//
//	int tsscope=5000;//桃子数的取值范围.太大容易溢出.
//	public int fT(int t){ if(t==tsscope){
//	//当桃子数到了最大的取值范围时取消递归
//
//	System.out.println("结束");
//	return 0;
//	}else{ if((t-1)%hs==0 && fs <=hs){ if(fs==hs)
//	{
//	System.out.println("桃子数 = "+ts +" 时满足分桃条件");
//	}
//	fs+=1;
//	return fT((t-1)/5*4);// 返回猴子拿走一份后的剩下的总数
//
//	}else
//
//	{
//
//	//没满足条件
//
//	fs=1;//分的次数重置为1
//
//	return fT(ts+=1);//桃子数加+1
//
//	}}}
//
//	public static void main(String[] args) { new Dg().fT(0);
//
//	}
//
//	}
//
//}
