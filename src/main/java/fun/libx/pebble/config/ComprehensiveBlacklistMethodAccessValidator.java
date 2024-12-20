package fun.libx.pebble.config;

import io.pebbletemplates.pebble.attributes.methodaccess.MethodAccessValidator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ComprehensiveBlacklistMethodAccessValidator implements MethodAccessValidator {

    // 定义禁止访问的方法名称
    private static final Set<String> FORBIDDEN_METHODS = new HashSet<>(Arrays.asList(
            "getClass",
            "wait",
            "notify",
            "notifyAll",
            "getField",
            "getDeclaredField",
            "getMethod",
            "getDeclaredMethod",
            "getConstructor",
            "getDeclaredConstructor",
            "newInstance",
            "forName",
            "invoke",
            "toString", // 禁止 toString
            "hashCode", // 禁止 hashCode
            "equals", // 禁止 equals
            "clone"// 禁止 clone
    ));

    // 定义禁止访问的类
    private static final Set<String> FORBIDDEN_CLASSES = new HashSet<>(Arrays.asList(
            "java.lang.Class",
            "java.lang.Runtime",
            "java.lang.Thread",
            "java.lang.ThreadGroup",
            "java.lang.System",
            "java.lang.reflect.AccessibleObject",
            "java.lang.reflect.Field",
            "java.lang.reflect.Method",
            "java.lang.reflect.Constructor",
            "java.lang.ClassLoader",
            "java.lang.Object" // 禁止访问 Object 类
    ));
    @Override
    public boolean isMethodAccessAllowed(Object object, Method method) {

        // 1. 检查对象类型
        if (isForbiddenClass(object)) {
            return false; // 立即禁止
        }

        // 2. 检查是否是反射相关的方法调用, 比如 Class.getMethod(), Field.get()
        if (isForbiddenReflectCall(object,method)) {
            return false;
        }
        // 3. 检查方法名称
        if (isUnsafeMethod(method)) {
            return false;
        }
        // 如果都不是则允许访问
        return true;
    }

    private boolean isForbiddenClass(Object object) {
        if (object == null) {
            return false;
        }
        return FORBIDDEN_CLASSES.contains(object.getClass().getName());
    }
    private boolean isForbiddenReflectCall(Object object, Method method) {
        if (object instanceof Class){
            return isUnsafeMethod(method);
        }

        if (object instanceof Field || object instanceof Constructor){
            return isUnsafeMethod(method);
        }

        return false;
    }

    private boolean isUnsafeMethod(Method method) {
        return FORBIDDEN_METHODS.contains(method.getName());
    }

}