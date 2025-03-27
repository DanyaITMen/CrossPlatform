import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Media {
    private String title;
    private LocalDate watchDate;
    private LocalDateTime addedDateTime;

    public Media(String title) {
        this.title = title;
        this.addedDateTime = LocalDateTime.now();
    }

    public Media(String title, LocalDate watchDate) {
        this.title = title;
        this.watchDate = watchDate;
        this.addedDateTime = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(LocalDate watchDate) {
        this.watchDate = watchDate;
    }

    public LocalDateTime getAddedDateTime() {
        return addedDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedWatchDate = watchDate != null ? watchDate.format(dateFormatter) : "Не переглянуто";
        return title + " (Додано: " + addedDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                ", Переглянуто: " + formattedWatchDate + ")";
    }
}