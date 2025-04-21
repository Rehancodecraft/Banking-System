package Bank;

import javax.xml.namespace.QName;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("------------------------------------");
		System.out.print("Enter deposit ammount:");
		double depositAmount = input.nextDouble();
		System.out.println("-------------------------------------");
		
	}
	public static void withdraw(int balance) {
		balance = balance - 2000;
	}
	
	
	}
