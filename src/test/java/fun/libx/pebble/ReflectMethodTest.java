package fun.libx.pebble;

import io.pebbletemplates.pebble.template.PebbleTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author quding
 * @since 2024/12/20
 */
public class ReflectMethodTest extends AbstractTest {
    @Test
    public void test_class() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.getClass() }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }

    @Test
    public void test_getField() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.class.getField('path').name }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }

    @Test
    public void test_declaredField() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.class.getDeclaredField('path').name }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }


    @Test
    public void test_getMethod() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.class.getMethod('getAbsolutePath').name }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }


    @Test
    public void test_declaredMethod() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.class.getDeclaredMethod('getAbsolutePath').name }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }

    @Test
    public void test_newInstance() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.class.newInstance() }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }

    @Test
    public void test_constructor() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.class.getConstructor().newInstance() }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }

    @Test
    public void test_declaredConstructor() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.class.getDeclaredConstructor().newInstance() }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }

    @Test
    public void test_invoke() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));
        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.class.getMethod('getAbsolutePath').invoke(file) }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }

    @Test
    public void test_forName() {
        Map<String, Object> context = new HashMap<>();
        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ T('java.lang.String') }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }

    @Test
    public void test_toString() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.toString() }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }

    @Test
    public void test_hashCode() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.hashCode() }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }

    @Test
    public void test_equals() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file == file }}");
        String result = testTemplate(template, context);
        Assertions.assertEquals("true", result);
    }

}
