import java.util.Scanner;

public class Console {
    private Scanner scanner;

    public Console() {
        this.scanner = new Scanner(System.in);
    }

    public void printMenu() {
        System.out.println("\nОберіть дію:");
        System.out.println("1. Додати новий фільм/серіал");
        System.out.println("2. Переглянути всі фільми/серіали");
        System.out.println("3. Оновити інформацію про фільм/серіал");
        System.out.println("4. Видалити фільм/серіал");
        System.out.println("5. Сортувати список");
        System.out.println("6. Пошук за ключовими словами");
        System.out.println("0. Вийти з програми");
        System.out.print("Ваш вибір: ");
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть число.");
            return -1;
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}