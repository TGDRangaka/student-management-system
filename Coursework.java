//import java.util.Arrays;
//hello java
import java.util.*;
class Coursework{
	static String[] sID=new String[0];
	static String[] sName=new String[0];
	static int[] prfmarks=new int[0];
	static int[] dbmmarks=new int[0];
	static int[] studentTot;
	static double[] studentAvg;
	static int[] studentRank;
	static String sid="";
	public static int checkSId(final int opt){	//to get index num [1] | to check sid in array [2]
		Scanner input=new Scanner(System.in);
		L1:while(true){
			System.out.print("\nEnter student ID : ");
			sid=input.next();
			int i=search(sID,sid);
			while(opt==1){
				if(i==-1){
					System.out.print("Invalid Student ID.");
					while(true){
						System.out.print("Do you want to search again? (Y/n) : ");
						String ans=input.next();
						if(ans.equals("y") || ans.equals("Y")){
							continue L1;
						}else if(ans.equals("n") || ans.equals("N")){
							return -1;
						}else{
							System.out.println("Invalid Option");
							continue;
						}
					}
				}
				return i;
			}
			while(opt==2){
				char ch=sid.charAt(0);
				if(ch!='S' || sid.length()!=4){
					System.out.println("The student ID Invalid! Try again\n");
					continue L1;
				}
				if(checkDuplicate(sID,Coursework.sid)){
					System.out.println("The student ID already exists\n");
					continue L1;
				}
				return -1;
			}
		}
	}
	public static void printTitle(String t){
		int len=78,x,y;
		x=(len-t.length())/2;
		if(t.length()%2==1){
			y=x+1;
		}else{
			y=x;
		}
		System.out.println("+------------------------------------------------------------------------------+");
		System.out.printf("|%"+x+"s%s%"+y+"s|\n","",t,"");
		System.out.println("+------------------------------------------------------------------------------+\n\n");
	}
	public final static void clearConsole() {
		try {
				final String os = System.getProperty("os.name");
				if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (final Exception e) {
			e.printStackTrace();
			// Handle any exceptions.
		}
	}
	public static String[] addIndex(String[] a){
		String[] temp=new String[a.length+1];
		for (int i = 0; i < a.length ; i++){
			temp[i]=a[i];
		}
		return temp;
	}
	public static int search(String[] a,String s){
		for (int i = 0; i < a.length; i++){
			if(a[i].equals(s)){
				return i;
			}
		}
		return -1;
	}
	public static int[] addIndex(int[] a){
		int[] temp=new int[a.length+1];
		for (int i = 0; i < a.length ; i++){
			temp[i]=a[i];
		}
		return temp;
	}
	public static boolean checkDuplicate(String[] ar,String s){
		for(String i:ar){
			if(i.equals(s)){
				return true;
			}
		}
		return false;
	}
	public static void addStudent(){	//1.Add new student
		Scanner input=new Scanner(System.in);
		L1:do{
			printTitle("ADD NEW STUDENT");
			checkSId(2);
			System.out.print("Enter student name : ");
			String sname=input.next();
			sID=addIndex(sID);
			sName=addIndex(sName);
			prfmarks=addIndex(prfmarks);
			dbmmarks=addIndex(dbmmarks);
			sID[sID.length-1]=sid;
			sName[sName.length-1]=sname;
			prfmarks[prfmarks.length-1]=-1;
			dbmmarks[dbmmarks.length-1]=-1;
			System.out.print("Student has been added successfully.");
			L2:do{
				System.out.print("Do you want to add a new student (Y/n): ");
				String ans=input.next();
				clearConsole();
				if(ans.equals("y") || ans.equals("Y")){
					continue L1;
				}else if(ans.equals("n") || ans.equals("N")){
					return;
				}else{
					System.out.println("Invalid Option...!");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	public static void addStudentWithMarks(){	//2.Add new student with marks
		Scanner input=new Scanner(System.in);
		L1:do{
			printTitle("ADD NEW STUDENT WITH MARKS");
			checkSId(2);
			System.out.print("Enter student name : ");
			String sname=input.next();
			System.out.println();
			sID=addIndex(sID);
			sName=addIndex(sName);
			prfmarks=addIndex(prfmarks);
			dbmmarks=addIndex(dbmmarks);
			sID[sID.length-1]=sid;
			sName[sName.length-1]=sname;
			prfmarks[prfmarks.length-1]=addPrfMarks();
			dbmmarks[dbmmarks.length-1]=addDbmMarks();
			System.out.print("Student has been added successfully.");
			while(true){
				System.out.print("Do you want to add a new student (Y/n): ");
				String ans=input.next();
				clearConsole();
				if(ans.equals("y") || ans.equals("Y")){
					continue L1;
				}else if(ans.equals("n") || ans.equals("N")){
					return;
				}else{
					System.out.println("Invalid Option...!");
					continue;
				}
			}
		}while(true);
	}
	public static void addMarks(){	//3.Add marks
		Scanner input=new Scanner(System.in);
		L1:do{
			printTitle("ADD MARKS");
			int i=checkSId(1);
			if(i==-1){
				return;
			}
			System.out.println("Student Name     : "+sName[i]);
			if(prfmarks[i]!=-1){
				System.out.println("This student's marks have been already added.\nIf you want to update the marks, please use [5] Update Marks option.\n");
			}else{
				System.out.println();
				prfmarks[i]=addPrfMarks();
				dbmmarks[i]=addDbmMarks();
				System.out.print("Marks have been added. ");
			}
			while(true){
				System.out.print("Do you want to add marks for another student? (Y/n) : ");
				String ans=input.next();
				clearConsole();
				if(ans.equals("y") || ans.equals("Y")){
					continue L1;
				}else if(ans.equals("n") || ans.equals("N")){
					return;
				}else{
					System.out.println("Invalid Option...!");
					continue;
				}
			}
		}while(true);
	}
	public static int addPrfMarks(){
		Scanner input=new Scanner(System.in);
		while(true){
			System.out.print("Programming Fundamental Marks : ");
			int prfm=input.nextInt();
			if(prfm<0 || prfm>100){
				System.out.println("Invalid marks, please enter correct marks.");
				continue;
			}
			return prfm;
		}
	}
	public static int addDbmMarks(){
		Scanner input=new Scanner(System.in);
		while(true){
			System.out.print("Database Managenment System Marks : ");
			int dbmm=input.nextInt();
			if(dbmm<0 || dbmm>100){
				System.out.println("Invalid marks, please enter correct marks.\n");
				continue;
			}
			return dbmm;
		}
	}
	public static void updateStudentDetail(){	//4.update student details
		Scanner input=new Scanner(System.in);
		L1:do{
			printTitle("UPDATE STUDENT DETAILS");
			int i=checkSId(1);
			if(i==-1){
				clearConsole();
				return;
			}
			System.out.println("Student Name     : "+sName[i]);
			System.out.print("\nEnter the new student name: ");
			String name=input.next();
			sName[i]=name;
			System.out.println("\nStudent details has been updated successfuly.");
			while(true){
				System.out.print("Do you want to update another student details? (Y/n): ");
				String ans=input.next();
				clearConsole();
				if(ans.equals("y") || ans.equals("Y")){
					continue L1;
				}else if(ans.equals("n") || ans.equals("N")){
					return;
				}else{
					System.out.println("Invalid Option...!");
					continue;
				}
			}
		}while(true);
	}
	public static void updateMarks(){	//5.update marks
		Scanner input=new Scanner(System.in);
		L1:do{
			printTitle("UPDATE MARKS");
			int i=checkSId(1);
			if(i==-1){
				return;
			}
			System.out.println("Student Name     : "+sName[i]);
			if(prfmarks[i]==-1){
				System.out.println("\nThis student's marks yet to be added.");
			}else{
				System.out.println("\nProgramming Fundamentals Marks   : "+prfmarks[i]);
				System.out.println("Database Management System Marks : "+dbmmarks[i]);
				System.out.print("\nEnter new ");
				prfmarks[i]=addPrfMarks();
				System.out.print("Enter new ");
				dbmmarks[i]=addDbmMarks();
			}
			while(true){
				System.out.print("Do you want to update marks for another student? (Y/n): ");
				String ans=input.next();
				clearConsole();
				if(ans.equals("y") || ans.equals("Y")){
					continue L1;
				}else if(ans.equals("n") || ans.equals("N")){
					return;
				}else{
					System.out.println("Invalid Option...!");
					continue;
				}
			}
		}while(true);
	}
	public static void deleteStudent(){		//6.delete student
		Scanner input=new Scanner(System.in);
		L1:do{
			printTitle("DELETE STUDENT");
			int i=checkSId(1);
			if(i==-1){
				clearConsole();
				return;
			}
			sID=deleteIndex(sID,i);
			sName=deleteIndex(sName,i);
			prfmarks=deleteIndex(prfmarks,i);
			dbmmarks=deleteIndex(dbmmarks,i);
			System.out.println("Student has been deleted successfully.");
			while(true){
				System.out.print("Do you want to delete another student? (Y/n): ");
				String ans=input.next();
				clearConsole();
				if(ans.equals("y") || ans.equals("Y")){
					continue L1;
				}else if(ans.equals("n") || ans.equals("N")){
					return;
				}else{
					System.out.println("Invalid Option...!");
					continue;
				}
			}
		}while(true);
	}
	public static String[] deleteIndex(String[] s,int in){
			String[] temp=new String[s.length-1];
			for (int i = 0,j=0; j < temp.length; i++,j++){
				if(i==in && in!=s.length-1){
					i++;
				}
				temp[j]=s[i];
			}
			return temp;
	}
	public static int[] deleteIndex(int[] s,int in){
		int[] temp=new int[s.length-1];
		for (int i = 0,j=0; j < temp.length; i++,j++){
			if(i==in && in!=s.length-1){
				i++;
			}
			temp[j]=s[i];
		}
		return temp;
	}
	public static void findTotAvg(){
		studentTot=new int[sID.length];
		studentAvg=new double[sID.length];
		for (int i = 0; i < sID.length; i++){
			studentTot[i]=prfmarks[i]+dbmmarks[i];
			studentAvg[i]=studentTot[i]/2d;
		}
	}
	public static int[] findRank(int[] a){
		int[] temp=new int[sID.length];
		int[] rank=new int[sID.length];
		for (int i = 0; i < sID.length; i++){
			temp[i]=a[i];
			rank[i]=i;
		}
		for (int i = sID.length-1; i > 0; i--){
			for(int j=1; j<=i; j++){
				if(temp[j-1]<temp[j]){
					int t=temp[j-1];
					int in=rank[j-1];
					temp[j-1]=temp[j];
					rank[j-1]=rank[j];
					temp[j]=t;
					rank[j]=in;
				}
			}
		}
		return rank;
	}
	public static int checkRank(int in){
		int rank=-1;
		for (int i = 0; i < sID.length; i++){
			if(studentRank[i]==in){
				rank=i+1;
				break;
			}
		}
		return rank;
	}
	public static String rankPlace(int rank){
		String p="";
		int count=0;
		for(int i:studentTot){
			count+=i==-2 ? 1 : 0;
		}
		rank+=rank==(sID.length-count) ? 100 : 0;
		
		p=rank==1 ? "1 (First)"
		 :rank==2 ? "2 (Second)"
		 :rank==3 ? "3 (Third)"
		 :rank==4 ? "4 (Fourth)"
		 :rank==5 ? "5 (Fifth)"
		 :rank==6 ? "6 (Sixth)"
		 :rank==7 ? "7 (Seventh)"
		 :rank==8 ? "8 (Eighth)"
		 :rank==9 ? "9 (ninth)"
		 :rank==10 ? "10 (tenth)"
		 :rank==11 ? "11 (eleventh)"
		 :rank==12 ? "12 (twelth)"
		 :rank==13 ? "13 (Thirteenth)"
		 :rank==14 ? "14 (Fourteenth)"
		 :rank==15 ? "15 (Fifteenth)"
		 :rank==16 ? "16 (Sixteenth)"
		 :rank==17 ? "17 (Seventeenth)"
		 :rank==18 ? "18 (Eighteenth)"
		 :rank==19 ? "19 (Nineteenth)"
		 :rank==20 ? "20 (Twentieth)"
		 : rank-100+" (Last)";
		 return p;
	}
	public static void printStuDetail(){		//7.Print student details
		Scanner input=new Scanner(System.in);
		L1:do{
			printTitle("PRINT STUDENT DETAILS");
			int i=checkSId(1);
			if(i==-1){
				clearConsole();
				return;
			}
			System.out.println("Student Name     : "+sName[i]);
			if(prfmarks[i]==-1){
				System.out.println("\nMarks yet to be added!\n");
			}else{
				System.out.println("+-----------------------------------+--------------------+");
				String row=String.format("|%-35s|%20d|","Programming Fundamentals Marks",prfmarks[i]);
				System.out.println(row);
				row=String.format("|%-35s|%20d|","Database Management System Marks",dbmmarks[i]);
				System.out.println(row);
				int tot=dbmmarks[i]+prfmarks[i];
				row=String.format("|%-35s|%20d|","Total Marks",tot);
				System.out.println(row);
				double avg=tot/2d;
				row=String.format("|%-35s|%20.2f|","Avg. Marks",avg);
				System.out.println(row);
				findTotAvg();
				studentRank=findRank(studentTot);
				row=String.format("|%-35s|%20s|","Rank",rankPlace(checkRank(i)));
				System.out.println(row);
				System.out.println("+-----------------------------------+--------------------+");
			}
			do{
				System.out.print("Do you want to search another student? (Y/n): ");
				String ans=input.next();
				clearConsole();
				if(ans.equals("y") || ans.equals("Y")){
					continue L1;
				}else if(ans.equals("n") || ans.equals("N")){
					return;
				}else{
					System.out.println("Invalid Option...!");
					continue;
				}
			}while(true);
		}while(true);
	}
	public static void printStuRank(){		//8. Print student details rankes
		Scanner input=new Scanner(System.in);
		L1:do{
			printTitle("PRINT STUDENT RANKS");
			findTotAvg();
			studentRank=findRank(studentTot);
			System.out.println("+----+----+--------------------+-----------+-----------+");
			String row=String.format("|%-4s|%-4s|%-20s|%11s|%11s|","Rank","ID","Name","Total Marks","Avg. Marks");
			System.out.println(row);
			System.out.println("+----+----+--------------------+-----------+-----------+");
			for(int i:studentRank){
				if(studentTot[i]==-2){
					break;
				}
				row=String.format("|%-4s|%-4s|%-20s|%11d|%11.2f|",checkRank(i),sID[i],sName[i],studentTot[i],studentAvg[i]);
				System.out.println(row);
			}
			System.out.println("+----+----+--------------------+-----------+-----------+");
			do{
				System.out.print("Do you want to go back to main menu?(Y/n): ");
				String ans=input.next();
				clearConsole();
				if(ans.equals("n") || ans.equals("N")){
					continue L1;
				}else if(ans.equals("y") || ans.equals("Y")){
					return;
				}else{
					System.out.println("Invalid Option...!");
					continue;
				}
			}while(true);
		}while(true);
	}
	public static void printBestInASub(int[] a,int[] b){	//9.Print best in subject
		Scanner input=new Scanner(System.in);
		L1:do{
			String s1="PRF Marks",s2="DBM Marks",s3="";
			if(a==dbmmarks){
				s3=s1;
				s1=s2;
				s2=s3;
				printTitle("BEST IN DATATBASE MANAGEMENT SYSTEM");
			}else{
				printTitle("BEST IN PROGRAMMING FUNDAMENTALS");
			}
			System.out.println("+----+--------------------+---------+---------+");
			String row=String.format("|%-4s|%-20s|%9s|%9s|","ID","Name",s1,s2);
			System.out.println(row);
			System.out.println("+----+--------------------+---------+---------+");
			findTotAvg();
			int[] rank=findRank(a);
			for(int i:rank){
				if(studentTot[i]==-2){
					break;
				}
				row=String.format("|%-4s|%-20s|%9d|%9d|",sID[i],sName[i],a[i],b[i]);
				System.out.println(row);
			}
			System.out.println("+----+--------------------+---------+---------+");
			do{
				System.out.print("Do you want to go back to main menu?(Y/n): ");
				String ans=input.next();
				clearConsole();
				if(ans.equals("n") || ans.equals("N")){
					continue L1;
				}else if(ans.equals("y") || ans.equals("Y")){
					return;
				}else{
					System.out.println("Invalid Option...!");
					continue;
				}
			}while(true);
		}while(true);
	}
	public static void optionCall(int ans){
		clearConsole();
		if(ans==1){
			addStudent();
		}else if(ans==2){
			addStudentWithMarks();
		}else if(ans==3){
			addMarks();
		}else if(ans==4){
			updateStudentDetail();
		}else if(ans==5){
			updateMarks();
		}else if(ans==6){
			deleteStudent();
		}else if(ans==7){
			printStuDetail();
		}else if(ans==8){
			printStuRank();
		}else if(ans==9){
			printBestInASub(prfmarks,dbmmarks);
		}else if(ans==10){
			printBestInASub(dbmmarks,prfmarks);
		}else if(ans==-1){
			return;
		}else{
			System.out.println("Wrong Answer! Enter Again..");
		}
	}
	public static void homePage(){
		printTitle("WELCOME TO GDSE MARKS MANAGEMENT SYSTEM");
		String row=String.format("[1] %-36s[2]  %-36s","Add New Student","Add New Studetn With Marks");
		System.out.println(row);
		row=String.format("[3] %-36s[4]  %-36s","Add Marks","Update Student Details");
		System.out.println(row);
		row=String.format("[5] %-36s[6]  %-36s","Update Marks","Delete Student");
		System.out.println(row);
		row=String.format("[7] %-36s[8]  %-36s","Print Student Details","Print Student Ranks");
		System.out.println(row);
		row=String.format("[9] %-36s[10] %-36s","Best in Programming Fundamentals","Best in Database Management System\n");
		System.out.println(row);
	}
	
	public static void main(String args[]){
		Scanner input=new Scanner(System.in);
		
		homePage();
		System.out.print("Enter an option to continue > ");
		int ans=input.nextInt();
		while(ans!=-1){
			optionCall(ans);
			clearConsole();
			homePage();
			System.out.print("Enter an option to continue > ");
			ans=input.nextInt();
		}
	}
}
