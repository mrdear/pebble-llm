package fun.libx.pebble;

import io.pebbletemplates.pebble.template.PebbleTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author quding
 * @since 2024/12/20
 */
public class MaxRenderedSizeTest extends AbstractTest {

    @Test
    public void test_maxRenderedSize() {
        Map<String,Object> context = new HashMap<>();
        // 创建一个简单的死循环模板
        String templateContent = "{% for i in 1..100000000 %}{{ i }}{% endfor %}";

        PebbleTemplate template = PebbleTemplateFactory.getTemplate(templateContent);
        Assertions.assertThrows(io.pebbletemplates.pebble.error.PebbleException.class, () -> {
            testTemplate(template, context);
        });
    }

}
