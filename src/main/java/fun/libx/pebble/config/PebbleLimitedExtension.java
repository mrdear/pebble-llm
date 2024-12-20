package fun.libx.pebble.config;

import fun.libx.pebble.config.filter.ChatExpandFilter;
import io.pebbletemplates.pebble.extension.Extension;
import io.pebbletemplates.pebble.extension.ExtensionCustomizer;
import io.pebbletemplates.pebble.extension.Filter;
import io.pebbletemplates.pebble.tokenParser.ForTokenParser;
import io.pebbletemplates.pebble.tokenParser.IfTokenParser;
import io.pebbletemplates.pebble.tokenParser.TokenParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 限制pebble的扩展沙箱
 * @author quding
 * @since 2024/12/20
 */
public class PebbleLimitedExtension extends ExtensionCustomizer {

    public PebbleLimitedExtension(Extension delegate) {
        super(delegate);
    }

    /**
     * 仅保留if和for能力
     * {% if %}
     * {% for %}
     */
    @Override
    public List<TokenParser> getTokenParsers() {
        List<TokenParser> tokenParsers = super.getTokenParsers();
        if (null == tokenParsers) {
            return null;
        }

        List<TokenParser> newParsers = new ArrayList<>(tokenParsers.size());
        newParsers.add(new IfTokenParser());
        newParsers.add(new ForTokenParser());

        return newParsers;
    }

    @Override
    public Map<String, Filter> getFilters() {
        Map<String, Filter> filters = super.getFilters();
        if (null == filters) {
            return null;
        }

        // 自定义扩展区域
        filters.put("chatExpand", new ChatExpandFilter());
        return filters;
    }
}
