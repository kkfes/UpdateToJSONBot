import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(new UpdateToJSONbot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
