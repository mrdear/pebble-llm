package fun.libx.pebble;

import fun.libx.pebble.config.ComprehensiveBlacklistMethodAccessValidator;
import fun.libx.pebble.config.PebbleLimitedExtension;
import fun.libx.pebble.config.TemplateSecurityManager;
import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.loader.StringLoader;
import io.pebbletemplates.pebble.template.PebbleTemplate;

import java.io.StringWriter;
import java.util.Map;

/**
 * @author quding
 * @since 2024/12/20
 */
public class PebbleTemplateFactory {

    private static final TemplateSecurityManager TEMPLATE_SECURITY_MANAGER = new TemplateSecurityManager();

    private static final PebbleEngine PEBBLE_ENGINE = new PebbleEngine.Builder()
            .loader(new StringLoader())
            .registerExtensionCustomizer(PebbleLimitedExtension::new)
            .methodAccessValidator(new ComprehensiveBlacklistMethodAccessValidator())
            .build();

    /**
     * 获取模板
     * @param templateName 模板内容
     * @return 获取结果
     */
    public static PebbleTemplate getTemplate(String templateName) {
        return PEBBLE_ENGINE.getTemplate(templateName);
    }


    public static String renderTemplate(PebbleTemplate template, Map<String, Object> context) {
        SecurityManager current = System.getSecurityManager();
        try {
            System.setSecurityManager(TEMPLATE_SECURITY_MANAGER);
            StringWriter result = new StringWriter();
            template.evaluate(result, context);
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.setSecurityManager(current);
        }
    }

}
