package fun.libx.pebble.config;

import java.io.FilePermission;
import java.net.SocketPermission;
import java.security.Permission;

/**
 * @author quding
 * @since 2024/12/20
 */
public class TemplateSecurityManager extends SecurityManager {

    @Override
    public void checkPermission(Permission perm) {
        if (perm instanceof RuntimePermission) {
            String name = perm.getName();

            if (name != null && (
                    name.startsWith("create") ||
                            name.startsWith("modify") ||
                            name.startsWith("accessClassInPackage") ||
                            name.startsWith("accessDeclaredMembers") ||
                            name.startsWith("defineClass") ||
                            name.startsWith("getClassLoader")
            )) {
                //禁用大部分 RuntimePermission
                throw new SecurityException("Runtime permission is denied: " + name);
            }

            return;
        }

        if (perm instanceof FilePermission || perm instanceof SocketPermission) {
            throw new SecurityException("File or socket access denied: " + perm);
        }

        // 默认拒绝所有其他权限
        super.checkPermission(perm);
    }
}
