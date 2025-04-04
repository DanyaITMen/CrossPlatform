import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class JsonHelper {
    private static final String FILE_PATH = "media_data.json";

    public static void saveToFile(List<Media> mediaList) {
        try {
            JSONArray jsonArray = new JSONArray();

            for (Media media : mediaList) {
                JSONObject mediaJson = new JSONObject();
                mediaJson.put("title", media.getTitle());

                if (media.getWatchDate() != null) {
                    mediaJson.put("watchDate", media.getWatchDate().toString());
                }

                jsonArray.put(mediaJson);
            }

            Files.write(Paths.get(FILE_PATH), jsonArray.toString().getBytes());
            System.out.println("Дані збережено у файл!");

        } catch (Exception e) {
            System.out.println("Помилка при збереженні: " + e.getMessage());
        }
    }

    public static List<Media> loadFromFile() {
        List<Media> mediaList = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("Файл не знайдено. Створюємо новий список.");
                return mediaList;
            }

            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject mediaJson = jsonArray.getJSONObject(i);
                String title = mediaJson.getString("title");

                Media media;

                if (mediaJson.has("watchDate") && !mediaJson.isNull("watchDate")) {
                    LocalDate watchDate = LocalDate.parse(mediaJson.getString("watchDate"));
                    media = new Media(title, watchDate);
                } else {
                    media = new Media(title);
                }

                mediaList.add(media);
            }

            System.out.println("Дані завантажено з файлу!");

        } catch (Exception e) {
            System.out.println("Помилка при завантаженні: " + e.getMessage());
        }

        return mediaList;
    }
}