import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        int arr = 100;
        int [][] grid = new int [arr][arr];

        for(int y = 30; y >= 70; y++){
            grid[50][y]=1;
        }
        for(int x =30;x <= 50;x++){
            grid[x][30]=1;
        }
        for(int x =30;x <= 50;x++){
            grid[x][70]=1;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a value here: ");
        long input = scanner.nextLong();

        Random random = new Random();

        for(int x = 0;x < arr;x++){
            for(int y = 0;y <arr ; y++){
                if(grid[x][y] ==0 && !(x==20 && y==50) && !(x==80 && y==50)){
                    if(random.nextDouble() < 0.15){
                        grid[x][y]=1;
                    }
                }

            }
        }
    }
}