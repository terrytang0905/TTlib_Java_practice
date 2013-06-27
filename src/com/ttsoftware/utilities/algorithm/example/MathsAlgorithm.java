package com.ttsoftware.utilities.algorithm.example;

import java.util.Scanner;
import java.io.*;

public class MathsAlgorithm {

	/*
	 * 【程序1】题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第四个月后每个月又生一对兔子，假如兔子都不
	 * 死，问每个月的兔子总数为多少？ 1.程序分析： 兔子的规律为数列1,1,2,3,5,8,13,21....
	 */
	public static int generatef(int x)

	{
		if (x == 1 || x == 2)
			return 1;
		else
			return generatef(x - 1) + generatef(x - 2);

	}

	/*
	 * 【程序2】题目：判断101-200之间有多少个素数，并输出所有素数。
	 * 1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除， 则表明此数不是素数，反之是素数。
	 */
	public boolean isPrime(int x) {
		for (int i = 2; i <= x / 2; i++)
			if (x % 2 == 0)
				return false;
		return true;

	}

	/*
	 * 【程序3】题目：打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。例如：153是一个 "水仙花 数
	 * "，因为153=1的三次方＋5的三次方＋3的三次方。 1.程序分析：利用for循环控制100-999个数，每个数分解出个位，十位，百位。
	 */
	public boolean flowerNumber() {
		return false;
	}

	/*
	 * 【程序4】题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
	 * 程序分析：对n进行分解质因数，应先找到一个最小的质数k，然后按下述步骤完成：
	 * (1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。 (2)如果n <>
	 * k，但n能被k整除，则应打印出k的值，并用n除以k的商,作为新的正整数你,重复执行第一步。
	 * (3)如果n不能被k整除，则用k+1作为k的值,重复执行第一步。
	 */
	public void decomposition(int n) {
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				System.out.print(i + "*");
				decomposition(n / i);
			}
		}
		System.out.print(n);
		System.exit(0);// /不能少这句，否则结果会出错
	}

	/*
	 * 【程序5】题目：利用条件运算符的嵌套来完成此题：学习成绩> =90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。 087
	 * 1.程序分析：(a> b)?a:b这是条件运算符的基本例子。
	 */

	/*
	 * 【程序6】题目：输入两个正整数m和n，求其最大公约数和最小公倍数。 102 1.程序分析：利用辗除法。 103 最大公约数：
	 */
	public static int commonDivisor(int M, int N) {
		if (N < 0 || M < 0) {
			System.out.println("ERROR!");
		}
		if (N == 0) {
			System.out.println("the biggest common divisor is :" + M);
		}
		return commonDivisor(N, M % N);
	}

	public static int gcd(int m, int n) {
		while (true) {
			if ((m = m % n) == 0)
				return n;
			if ((n = n % m) == 0)
				return m;
		}
	}

	/*
	 * 【程序7】题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。 132 1.程序分析：利用while语句,条件为输入的字符不为
	 * '\n '.
	 */

	public static void statisticNumber(String args[]) {
		String E1 = "[\u4e00-\u9fa5]";
		String E2 = "[a-zA-Z]";
		System.out.println("请输入字符串：");
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		int countH = 0;
		int countE = 0;
		char[] arrChar = str.toCharArray();
		String[] arrStr = new String[arrChar.length];
		for (int i = 0; i < arrChar.length; i++) {
			arrStr[i] = String.valueOf(arrChar[i]);
		}
		for (String i : arrStr) {
			if (i.matches(E1)) {
				countH++;
			}
			if (i.matches(E2)) {
				countE++;
			}
		}
		System.out.println("汉字的个数" + countH);
		System.out.println("字母的个数" + countE);
	}

	/*
	 * 【程序8】题目：求s=a+aa+aaa+aaaa+aa...a的值，其中a是一个数字。例如2+22+222+2222+22222(此时共有5个数相加
	 * )，几个数相 加有键盘控制。 1.程序分析：关键是计算出每一项的值。
	 */

	public static void Sumloop(String[] args) throws IOException {
		int s = 0;
		String output = "";
		BufferedReader stadin = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println("请输入a的值");
		String input = stadin.readLine();
		for (int i = 1; i <= Integer.parseInt(input); i++) {
			output += input;
			int a = Integer.parseInt(output);
			s += a;
		}
		System.out.println(s);
	}

	public static void Sumloop2(String[] args) throws IOException {
		int s = 0;
		int n;
		int t = 0;
		BufferedReader stadin = new BufferedReader(new InputStreamReader(
				System.in));
		String input = stadin.readLine();
		n = Integer.parseInt(input);
		for (int i = 1; i <= n; i++) {
			t = t * 10 + n;
			s = s + t;
			System.out.println(t);
		}
		System.out.println(s);
	}

	/* 【程序9】题目：一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。例如6=1＋2＋3.编程找出1000以内的所有完数。 */
	public static void completeNumber(String[] args) {
		int s;
		for (int i = 1; i <= 1000; i++) {
			s = 0;
			for (int j = 1; j < i; j++)
				if (i % j == 0)
					s = s + j;
			if (s == i)
				System.out.print(i + " ");
		}
		System.out.println();
	}

	/*
	 * 【程序12】题目：企业发放的奖金根据利润提成。利润(I)低于或等于10万元时，奖金可提10%；利润高于10万元，低于20万元时，低于10万 219
	 * 元的部分按10%提成，高于10万元的部分，可可提成7.5%；20万到40万之间时，高于20万元的部分，可提成5%；40万到60万之间时高 220
	 * 于40万元的部分，可提成3%；60万到100万之间时，高于60万元的部分，可提成1.5%，高于100万元时，超过100万元的部分按1%提成，从
	 * 221 键盘输入当月利润I，求应发放奖金总数？ 222 1.程序分析：请利用数轴来分界，定位。注意定义时需把奖金定义成长整型。
	 */
	public static void companyBonus(String[] args) {
		double sum;// 声明要储存的变量应发的奖金
		Scanner input = new Scanner(System.in);// 导入扫描器
		System.out.print("输入当月利润");
		double lirun = input.nextDouble();// 从控制台录入利润
		if (lirun <= 100000) {
			sum = lirun * 0.1;
		} else if (lirun <= 200000) {
			sum = 10000 + lirun * 0.075;
		} else if (lirun <= 400000) {
			sum = 17500 + lirun * 0.05;
		} else if (lirun <= 600000) {
			sum = lirun * 0.03;
		} else if (lirun <= 1000000) {
			sum = lirun * 0.015;
		} else {
			sum = lirun * 0.01;
		}
		System.out.println("应发的奖金是" + sum);
	}
	
	
	
	

}
