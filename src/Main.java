import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

 class MovieTracker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> mediaList = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=== Трекер переглянутих фільмів та серіалів ===");

        while (running) {
            printMenu();
            switch (scanner.nextLine()) {
                case "1":
                    addMedia();
                    break;
                case "2":
                    viewMedia();
                    break;
                case "3":
                    updateMedia();
                    break;
                case "4":
                    deleteMedia();
                    break;
                case "5":
                    sortMedia();
                    break;
                case "6":
                    searchMedia();
                    break;
                case "0":
                    running = false;
                    System.out.println("Дякуємо за використання!");
                    break;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
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

    private static void addMedia() {
        System.out.print("Введіть назву: ");
        mediaList.add(scanner.nextLine());
        System.out.println("Додано!");
    }

    private static void viewMedia() {
        if (mediaList.isEmpty())
            System.out.println("Список порожній.");
        else for (String media : mediaList)
            System.out.println("- " + media);
    }

     private static void updateMedia() {
         viewMedia(); // Показуємо список перед оновленням
         if (mediaList.isEmpty()) return; // Якщо список порожній — виходимо

         System.out.print("Введіть номер для оновлення: ");
         int index = Integer.parseInt(scanner.nextLine()) - 1; // Отримуємо індекс елемента

         if (index >= 0 && index < mediaList.size()) { // Перевіряємо, чи індекс коректний
             System.out.print("Нова назва: ");
             mediaList.set(index, scanner.nextLine()); // Замінюємо стару назву
             System.out.println("Оновлено!");
         } else {
             System.out.println("Невірний номер.");
         }
     }

     private static void deleteMedia() {
         viewMedia(); // Виводимо список
         if (mediaList.isEmpty()) return; // Якщо список порожній, виходимо

         System.out.print("Введіть номер для видалення: ");
         int index = Integer.parseInt(scanner.nextLine()) - 1; // Отримуємо індекс

         if (index >= 0 && index < mediaList.size()) { // Перевіряємо, чи існує такий номер
             mediaList.remove(index); // Видаляємо елемент
             System.out.println("Видалено!");
         } else {
             System.out.println("Невірний номер.");
         }
     }

    private static void sortMedia() {
        mediaList.sort(String::compareTo); // Сортуємо за абеткою
        System.out.println("Список відсортовано.");
    }

     private static void searchMedia() {
         System.out.print("Введіть ключове слово: ");
         String keyword = scanner.nextLine().toLowerCase(); // Зчитуємо слово для пошуку
         boolean found = false;

         for (String media : mediaList) {
             if (media.toLowerCase().contains(keyword)) { // Шукаємо збіг
                 System.out.println("- " + media);
                 found = true;
             }
         }

         if (!found) System.out.println("Нічого не знайдено.");
     }
}