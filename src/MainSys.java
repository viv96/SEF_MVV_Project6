import java.util.ArrayList;
import java.util.Scanner;

public class MainSys {
    public static ArrayList<user> users = new ArrayList<user>();
    public static ArrayList<project> projects = new ArrayList<project>();
    public static Scanner scan = new Scanner(System.in);
    public static int menuReply;
    private static String userName;
    private static String password;

    public static void main (String [] args){
        scan = new Scanner(System.in);
        do {
            System.out.print("Welcome to project Management System.\n1.login\n2.quit\n3.create profile\nYour Choice: ");
            menuReply = scan.nextInt();
            switch (menuReply){

            }
        }while(true);
    }

}
