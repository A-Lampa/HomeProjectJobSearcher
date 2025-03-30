package test;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

public interface TestInfo {
    String getDisplayName();

    Set<String> getTags();

    Optional<Class<?>> getTestClass();

    Optional<Method> getTestMethod();
}
