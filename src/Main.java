import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        MediaManager mediaManager = new MediaManager();
        Console console = new Console();

        boolean running = true;
        System.out.println("=== Трекер переглянутих фільмів та серіалів ===");

        while (running) {
            console.printMenu();
            switch (console.getInput()) {
                case "1":
                    System.out.print("Введіть назву: ");
                    String title = console.getInput();

                    System.out.print("Додати дату перегляду? (1 - Так, 2 - Ні): ");
                    String addDateChoice = console.getInput();

                    if (addDateChoice.equals("1")) {
                        System.out.print("Дата перегляду (у форматі YYYY-MM-DD): ");
                        try {
                            LocalDate watchDate = LocalDate.parse(console.getInput());
                            mediaManager.addMedia(title, watchDate);
                        } catch (DateTimeParseException e) {
                            System.out.println("Невірний формат дати. Додано без дати перегляду.");
                            mediaManager.addMedia(title);
                        }
                    } else {
                        mediaManager.addMedia(title);
                    }
                    break;
                case "2":
                    mediaManager.viewMedia();
                    break;
                case "3":
                    mediaManager.viewMedia();
                    if (mediaManager.isEmpty())
                        break;

                    System.out.print("Введіть номер для оновлення: ");
                    int updateIndex = console.getIntInput() - 1;

                    System.out.print("Що ви хочете оновити? (1 - Назва, 2 - Дата перегляду): ");
                    String updateChoice = console.getInput();

                    if (updateChoice.equals("1")) {
                        System.out.print("Нова назва: ");
                        mediaManager.updateMedia(updateIndex, console.getInput());
                    } else if (updateChoice.equals("2")) {
                        System.out.print("Нова дата перегляду (у форматі YYYY-MM-DD): ");
                        try {
                            LocalDate newWatchDate = LocalDate.parse(console.getInput());
                            mediaManager.updateMedia(updateIndex, newWatchDate);
                        } catch (DateTimeParseException e) {
                            System.out.println("Невірний формат дати. Оновлення скасовано.");
                        }
                    } else {
                        System.out.println("Невірний вибір.");
                    }
                    break;
                case "4":
                    mediaManager.viewMedia();
                    if (mediaManager.isEmpty())
                        break;

                    System.out.print("Введіть номер для видалення: ");
                    int deleteIndex = console.getIntInput() - 1;
                    mediaManager.deleteMedia(deleteIndex);
                    break;
                case "5":
                    mediaManager.sortMedia();
                    break;
                case "6":
                    System.out.print("Введіть ключове слово: ");
                    mediaManager.searchMedia(console.getInput());
                    break;
                case "0":
                    running = false;
                    System.out.println("Дякуємо за використання!");
                    break;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
        console.closeScanner();
    }
}