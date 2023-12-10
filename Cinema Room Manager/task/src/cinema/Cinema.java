package cinema;

import java.util.Scanner;

public class Cinema {
    private static final Scanner scanner = new Scanner(System.in);
    private static int rowsNum;
    private static int colsNum;
    private static char[][] seats;

    private static int ticketsNum;
    private static int currentIncome;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rowsNum = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        colsNum = scanner.nextInt();

        seats = new char[rowsNum][colsNum];
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < colsNum; j++) {
                seats[i][j] = 'S';
            }
        }

        int action;
        do {
            showMenu();
            action = scanner.nextInt();
            switch (action) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    showStatistics();
                    break;
                default:
                    break;
            }
        } while (action != 0);
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    private static void showSeats() {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= colsNum; i++) {
            System.out.printf(" %d", i);
        }
        System.out.println();
        for (int i = 0; i < rowsNum; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < colsNum; j++) {
                System.out.printf(" %c", seats[i][j]);
            }
            System.out.println();
        }
    }

    private static void buyTicket() {
        System.out.println();
        System.out.println("Enter a row number:");
        int selectedRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int selectedSeat = scanner.nextInt();
        if (selectedRow > rowsNum || selectedSeat > colsNum) {
            System.out.println();
            System.out.println("Wrong input!");
            buyTicket();
            return;
        }
        if (seats[selectedRow - 1][selectedSeat - 1] == 'B') {
            System.out.println();
            System.out.println("That ticket has already been purchased!");
            buyTicket();
            return;
        }

        int ticketPrice = 10;
        int seatsNum = rowsNum * colsNum;
        if (seatsNum > 60) {
            int frontHalf = rowsNum / 2;
            if (selectedRow > frontHalf) {
                ticketPrice = 8;
            }
        }

        ticketsNum++;
        currentIncome += ticketPrice;
        System.out.printf("%nTicket price: $%d%n", ticketPrice);
        seats[selectedRow - 1][selectedSeat - 1] = 'B';
    }

    private static void showStatistics() {
        int totalIncome;
        int seatsNum = rowsNum * colsNum;
        if (seatsNum < 60) {
            totalIncome = seatsNum * 10;
        } else {
            int frontHalf = rowsNum / 2;
            totalIncome = frontHalf * colsNum * 10 + (rowsNum - frontHalf) * colsNum * 8;
        }
        System.out.printf("%nNumber of purchased tickets: %d", ticketsNum);
        System.out.printf("%nPercentage: %.2f", (double) ticketsNum * 100 / seatsNum);
        System.out.print('%');
        System.out.printf("%nCurrent income: $%d", currentIncome);
        System.out.printf("%nTotal income: $%d%n", totalIncome);
    }

}
