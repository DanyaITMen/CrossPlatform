import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class MediaManager {
    private List<Media> mediaList;

    public MediaManager() {
        // Завантажуємо дані з файлу при створенні
        this.mediaList = JsonHelper.loadFromFile();

        // Якщо список порожній (файл не існує), створюємо новий
        if (this.mediaList == null) {
            this.mediaList = new ArrayList<>();
        }
    }

    public void addMedia(String title) {
        mediaList.add(new Media(title));
        System.out.println("Додано!");
        JsonHelper.saveToFile(mediaList); // Зберігаємо зміни
    }

    public void addMedia(String title, LocalDate watchDate) {
        mediaList.add(new Media(title, watchDate));
        System.out.println("Додано!");
        JsonHelper.saveToFile(mediaList); // Зберігаємо зміни
    }

    public void viewMedia() {
        if (mediaList.isEmpty()) {
            System.out.println("Список порожній.");
        } else {
            for (int i = 0; i < mediaList.size(); i++) {
                System.out.println((i + 1) + ". " + mediaList.get(i));
            }
        }
    }

    public void updateMedia(int index, String newTitle) {
        if (index >= 0 && index < mediaList.size()) {
            mediaList.get(index).setTitle(newTitle);
            System.out.println("Оновлено!");
            JsonHelper.saveToFile(mediaList); // Зберігаємо зміни
        } else {
            System.out.println("Невірний номер.");
        }
    }

    public void updateMedia(int index, LocalDate newWatchDate) {
        if (index >= 0 && index < mediaList.size()) {
            mediaList.get(index).setWatchDate(newWatchDate);
            System.out.println("Дату перегляду оновлено!");
            JsonHelper.saveToFile(mediaList); // Зберігаємо зміни
        } else {
            System.out.println("Невірний номер.");
        }
    }

    public void deleteMedia(int index) {
        if (index >= 0 && index < mediaList.size()) {
            mediaList.remove(index);
            System.out.println("Видалено!");
            JsonHelper.saveToFile(mediaList); // Зберігаємо зміни
        } else {
            System.out.println("Невірний номер.");
        }
    }

    public void sortMedia() {
        mediaList.sort((m1, m2) -> m1.getTitle().compareTo(m2.getTitle()));
        System.out.println("Список відсортовано.");
        JsonHelper.saveToFile(mediaList); // Зберігаємо зміни
    }

    public void searchMedia(String keyword) {
        boolean found = false;
        for (Media media : mediaList) {
            if (media.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("- " + media);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Нічого не знайдено.");
        }
    }

    public boolean isEmpty() {
        return mediaList.isEmpty();
    }
}