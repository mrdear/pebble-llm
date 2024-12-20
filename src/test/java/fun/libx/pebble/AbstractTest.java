package fun.libx.pebble;

import io.pebbletemplates.pebble.template.PebbleTemplate;

import java.util.Map;

/**
 * @author quding
 * @since 2024/12/20
 */
public class AbstractTest {

    public static String testTemplate(PebbleTemplate template, Map<String, Object> context) {
        try {
            return PebbleTemplateFactory.renderTemplate(template, context);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
