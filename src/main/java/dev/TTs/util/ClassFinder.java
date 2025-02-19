package dev.TTs.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@SuppressWarnings({"unused", "unchecked"})
public class ClassFinder<T> {
    private final Class<T> baseClass;
    private String packageName = "dev.TTs";

    public ClassFinder(Class<T> baseClass, String packageName) {
        this.baseClass = baseClass;
        this.packageName = packageName;
    }

    public ClassFinder(Class<T> baseClass) {
        this.baseClass = baseClass;
    }

    public List<Class<? extends T>> getClassesImplementingInterface(Class<?> interfac) throws IOException, ClassNotFoundException {
        return getClasses()
                .stream()
                .filter(predicate -> predicate.getClass().isInstance(interfac))
                .toList();
    }

    public List<Class<? extends T>> getClassesWithAnnotation(Class<? extends Annotation> annotation) throws IOException, ClassNotFoundException {
        return getClasses()
                .stream()
                .filter(predicate -> predicate.isAnnotationPresent(annotation))
                .toList();
    }

    public List<Class<? extends T>> getClasses() throws ClassNotFoundException, IOException {
        List<Class<? extends T>> subclasses = new ArrayList<>();
        List<Class<?>> classes = getClasses(packageName);

        for (Class<?> clazz : classes) {
            if (baseClass.isAssignableFrom(clazz) && !clazz.equals(baseClass)) {
                subclasses.add((Class<? extends T>) clazz);
                findInnerClasses((Class<? extends T>) clazz, subclasses);
            }
        }

        return subclasses;
    }

    private List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }
        return classes;
    }

    private void findInnerClasses(Class<? extends T> clazz, List<Class<? extends T>> subclasses) {
        Class<?>[] innerClasses = clazz.getDeclaredClasses();
        for (Class<?> innerClass : innerClasses) {
            if (baseClass.isAssignableFrom(innerClass) && !innerClass.equals(baseClass)) {
                subclasses.add((Class<? extends T>) innerClass);
                findInnerClasses((Class<? extends T>) innerClass, subclasses);
            }
        }
    }
}
