package fun.libx.pebble;

import fun.libx.pebble.model.ChatModel;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author quding
 * @since 2024/12/20
 */
public class ChatExpandFilterTest extends AbstractTest {

    @Test
    public void test_expand() {
        List<ChatModel> chatModels = ChatModel.generateMockDialog(5);

        Map<String,Object> context = new HashMap<>();
        context.put("chatModels", chatModels);

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ chatModels | chatExpand}}");
        String result = testTemplate(template, context);

        Assertions.assertEquals("user:User message 0 - Hello, how are you?\n" +
                "assistant:Assistant message 1 - I'm fine, thank you! How can I help you?\n" +
                "user:User message 2 - Hello, how are you?\n" +
                "assistant:Assistant message 3 - I'm fine, thank you! How can I help you?\n" +
                "user:User message 4 - Hello, how are you?\n", result);
    }


    @Test
    public void test_expand_2() {
        List<ChatModel> chatModels = ChatModel.generateMockDialog(10);

        Map<String,Object> context = new HashMap<>();
        context.put("chatModels", chatModels);

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ chatModels | chatExpand(3,1)}}");
        String result = testTemplate(template, context);

        Assertions.assertEquals("user:User message 4 - Hello, how are you?\n" +
                "assistant:Assistant message 5 - I'm fine, thank you! How can I help you?\n" +
                "user:User message 6 - Hello, how are you?\n" +
                "assistant:Assistant message 7 - I'm fine, thank you! How can I help you?\n" +
                "user:User message 8 - Hello, how are you?\n", result);
    }


}
