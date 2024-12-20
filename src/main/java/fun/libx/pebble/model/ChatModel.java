package fun.libx.pebble.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 对话模型
 * @author quding
 * @since 2024/12/20
 */
@Data
public class ChatModel {

    private String role;

    private String content;

    public ChatModel(String role, String content) {
        this.role = role;
        this.content = content;
    }


    /**
     * 生成一个模拟的对话列表。
     *
     * @param numMessages 对话消息的数量
     * @return 一个包含模拟对话消息的列表
     */
    public static List<ChatModel> generateMockDialog(int numMessages) {
        if (numMessages <= 0) {
            return new ArrayList<>();
        }

        List<ChatModel> dialog = new ArrayList<>();
        String[] roles = {"user", "assistant"}; // 定义角色
        boolean isUserTurn = true; // 模拟用户先说话

        for (int i = 0; i < numMessages; i++) {
            String role = isUserTurn ? roles[0] : roles[1];
            String content = generateMockContent(role, i);
            dialog.add(new ChatModel(role, content));
            isUserTurn = !isUserTurn; // 切换角色
        }
        return dialog;
    }

    /**
     * 根据角色和消息序号生成 mock 内容。
     *
     * @param role      角色 ("user" 或 "assistant")
     * @param messageNum 消息序号
     * @return mock 内容字符串
     */
    private static String generateMockContent(String role, int messageNum) {
        if ("user".equals(role)) {
            return "User message " + messageNum + " - Hello, how are you?";
        } else if ("assistant".equals(role)) {
            return "Assistant message " + messageNum + " - I'm fine, thank you! How can I help you?";
        } else {
            return "Invalid role";
        }
    }

}
