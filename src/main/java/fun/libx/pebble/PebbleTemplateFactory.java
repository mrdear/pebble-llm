package fun.libx.pebble;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.loader.StringLoader;

/**
 * @author quding
 * @since 2024/12/20
 */
public class PebbleInstanceFactory {

    /**
     * 获取模板实例
     */
    public static PebbleEngine getPebbleEngine() {
        // 1. 创建 PebbleEngine，使用自定义扩展
        return new PebbleEngine.Builder()
                .loader(new StringLoader())
                .extension(new PebbleLimitedExtension())
                .build();
    }

}
