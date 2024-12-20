package fun.libx.pebble;

import io.pebbletemplates.pebble.error.ParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static fun.libx.pebble.AbstractTest.testTemplate;

/**
 * @author quding
 * @since 2024/12/20
 */
class PebbleTagTest {

    @Test
    public void test_if() {

        Map<String, Object> context = new HashMap<>();
        context.put("items", new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry")));
        context.put("condition", true);

        String result = testTemplate(PebbleTemplateFactory.getTemplate("{% if condition %}Condition is true{% endif %}"), context);

        Assertions.assertEquals("Condition is true", result);
    }


    @Test
    public void test_foreach() {
        Map<String, Object> context = new HashMap<>();
        context.put("items", new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry")));
        context.put("condition", true);

        String result = testTemplate(PebbleTemplateFactory.getTemplate("{% for item in items %} {{item}} , {% endfor %}"), context); //应该能正常工作
        Assertions.assertEquals(" Apple ,  Banana ,  Cherry , ", result);
    }


    @Test
    public void test_block_not_support() {
        ParserException exception = null;

        try {
            Map<String, Object> context = new HashMap<>();
            context.put("items", new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry")));
            context.put("condition", true);

            testTemplate(PebbleTemplateFactory.getTemplate("{% filter upper %}\n" +
                    "\thello\n" +
                    "{% endfilter %}}"), context);
        } catch (ParserException e) {
            exception = e;
        }
        Assertions.assertNotNull(exception);
    }


}