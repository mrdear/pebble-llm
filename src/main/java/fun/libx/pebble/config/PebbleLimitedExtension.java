package fun.libx.pebble;

import io.pebbletemplates.pebble.extension.Extension;
import io.pebbletemplates.pebble.extension.ExtensionCustomizer;
import io.pebbletemplates.pebble.extension.core.CoreExtension;
import io.pebbletemplates.pebble.operator.BinaryOperator;
import io.pebbletemplates.pebble.tokenParser.ForTokenParser;
import io.pebbletemplates.pebble.tokenParser.IfTokenParser;
import io.pebbletemplates.pebble.tokenParser.TokenParser;

import java.util.List;

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
        return List.of(new IfTokenParser(), new ForTokenParser());
    }

}
