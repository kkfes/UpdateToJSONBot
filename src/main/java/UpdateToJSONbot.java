import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UpdateToJSONbot extends TelegramLongPollingBot {

    public static final String BOT_NAME = "";
    public static final String BOT_TOKEN = "";

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                sendMessage("<code>" + mapper.writeValueAsString(update.getMessage()).replace(",", ",\n\t").replace("{", "\t\n{\n\t").replace("}", "\t\n}") + "</code>", update.getMessage().getChatId());
            } catch (JsonProcessingException e) {
                sendMessage("<code>" + e + "</code>", update.getMessage().getChatId());
            }
        }
    }

    private void sendMessage(String message, long chat_id) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chat_id));
        sendMessage.setDisableWebPagePreview(false);
        sendMessage.setParseMode("HTML");
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
