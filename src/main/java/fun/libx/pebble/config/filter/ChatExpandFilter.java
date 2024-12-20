package fun.libx.pebble.config.filter;

import fun.libx.pebble.model.ChatModel;
import io.pebbletemplates.pebble.error.PebbleException;
import io.pebbletemplates.pebble.extension.Filter;
import io.pebbletemplates.pebble.extension.escaper.SafeString;
import io.pebbletemplates.pebble.template.EvaluationContext;
import io.pebbletemplates.pebble.template.PebbleTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author quding
 * @since 2024/12/20
 */
public class ChatExpandFilter implements Filter {

    private final List<String> argumentNames = new ArrayList<>();

    public ChatExpandFilter() {
        argumentNames.add("round");
        argumentNames.add("rightOffset");
    }

    @Override
    public Object apply(Object input, Map<String, Object> args, PebbleTemplate self, EvaluationContext context, int lineNumber) throws PebbleException {
        if (null == input) {
            return null;
        }

        final Long round = (Long) args.getOrDefault("round", 5L);
        final Long rightOffset = (Long) args.getOrDefault("rightOffset", 0L);

        Collection<ChatModel> chatModels = (Collection<ChatModel>) input;

        Long skipSize = Math.max(0, chatModels.size() - round * 2);
        Long limitSize = Math.min(chatModels.size() - rightOffset, round * 2 - rightOffset);

        StringBuilder builder = new StringBuilder();
        chatModels.stream().skip(skipSize).limit(limitSize).forEach(chatModel -> {
            builder.append(chatModel.getRole()).append(':').append(chatModel.getContent()).append('\n');
        });
        return new SafeString(builder.toString());
    }

    @Override
    public List<String> getArgumentNames() {
        return argumentNames;
    }
}
