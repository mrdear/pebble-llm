package fun.libx.pebble;

import fun.libx.pebble.config.TemplateSecurityManager;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TemplateSecurityManagerTest extends AbstractTest {

    @Test
    public void test_file_access() {
        System.setSecurityManager(new TemplateSecurityManager());
        Assertions.assertThrows(SecurityException.class,() -> {
            new File("test.txt").createNewFile();
        });
        System.setSecurityManager(null);
    }


    @Test
    public void test_network_access() {
        System.setSecurityManager(new TemplateSecurityManager());
        Assertions.assertThrows(SecurityException.class,() -> {
            new Socket("127.0.0.1", 8080);
        });
        System.setSecurityManager(null);
    }

    @Test
    public void test_runtime_permission(){
        System.setSecurityManager(new TemplateSecurityManager());
        Assertions.assertThrows(SecurityException.class,() ->{
            Thread.currentThread().setContextClassLoader(null);
        });

        Assertions.assertThrows(SecurityException.class,() ->{
            System.setProperty("test","test");
        });
        System.setSecurityManager(null);
    }
    @Test
    public void test_template_file_access() {
        Map<String, Object> context = new HashMap<>();
        context.put("file", new File("src/test/resources/test.pebble"));

        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ file.getAbsolutePath() }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result); // 因为getAbsolutePath会调用到文件系统，应该会被安全管理器拦截，因此返回null

    }

    @Test
    public void test_template_network_access() {
        Map<String, Object> context = new HashMap<>();
        context.put("host", "www.example.com");
        try {
            PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ T('java.net.InetAddress').getByName(host) }}");
            String result = testTemplate(template, context);
            Assertions.assertNull(result);
        } catch (SecurityException e){
            //预期应该会抛出异常
        }
    }
    @Test
    public void test_template_system_properties() {
        Map<String, Object> context = new HashMap<>();
        PebbleTemplate template = PebbleTemplateFactory.getTemplate("{{ T('java.lang.System').getProperty('user.dir') }}");
        String result = testTemplate(template, context);
        Assertions.assertNull(result);
    }
}