package dev.TTs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static dev.TTs.TTsGames.Main.logger;

public class PackageAnalyzer<T> {
    private final ClassFinder<T> classFinder;

    public PackageAnalyzer(Class<T> baseClass) {
        this.classFinder = new ClassFinder<>(baseClass);
    }

    public void analyzePackage() {
        try {
            List<Class<? extends T>> classes = classFinder.getClasses();

            CategoryInfo enums = new CategoryInfo("Enums");
            CategoryInfo interfaces = new CategoryInfo("Interfaces");
            CategoryInfo records = new CategoryInfo("Records");
            CategoryInfo classesInfo = new CategoryInfo("Classes");
            CategoryInfo abstractClasses = new CategoryInfo("Abstract Classes");
            CategoryInfo finalClasses = new CategoryInfo("Final Classes");
            CategoryInfo annotations = new CategoryInfo("Annotations");
            CategoryInfo exceptions = new CategoryInfo("Exceptions");

            for (Class<?> clazz : classes) {
                File classFile = new File(getFilePath(clazz));
                int[] linesAndChars = countLinesAndCharacters(classFile);
                int lines = linesAndChars[0];
                int chars = linesAndChars[1];

                if (clazz.isEnum()) {
                    enums.increment(lines, chars);
                } else if (clazz.isAnnotation()) {
                    annotations.increment(lines, chars);
                } else if (clazz.isInterface()) {
                    interfaces.increment(lines, chars);
                } else if (clazz.isRecord()) {
                    records.increment(lines, chars);
                } else if (Throwable.class.isAssignableFrom(clazz)) {
                    exceptions.increment(lines, chars);
                } else {
                    if (Modifier.isAbstract(clazz.getModifiers())) {
                        abstractClasses.increment(lines, chars);
                    } else if (Modifier.isFinal(clazz.getModifiers())) {
                        finalClasses.increment(lines, chars);
                    } else {
                        classesInfo.increment(lines, chars);
                    }
                }
            }

            List<CategoryInfo> categories = new ArrayList<>();
            categories.add(enums);
            categories.add(interfaces);
            categories.add(records);
            categories.add(classesInfo);
            categories.add(abstractClasses);
            categories.add(finalClasses);
            categories.add(annotations);
            categories.add(exceptions);

            List<CategoryInfo> sortedCategories = categories.stream()
                    .sorted(Comparator.comparingInt(CategoryInfo::getCount).reversed())
                    .toList();

            for (CategoryInfo category : sortedCategories) {
                logger.debug("%s: %d  Lines of Code: %d  Characters: %d",
                        category.getName(), category.getCount(), category.getLines(), category.getChars());
            }

            logger.newLine();

            int totalCount = categories.stream().mapToInt(CategoryInfo::getCount).sum();
            int totalLines = categories.stream().mapToInt(CategoryInfo::getLines).sum();
            int totalChars = categories.stream().mapToInt(CategoryInfo::getChars).sum();

            logger.debug("Total: %d   Total Lines of Code: %d   Total Characters: %d",
                    totalCount, totalLines, totalChars);

        } catch (IOException | ClassNotFoundException e) {
            logger.error("An error occurred while analyzing the package: %s", e.getMessage());
        }
    }

    private String getFilePath(Class<?> clazz) {
        String className = clazz.getName().replace('.', '/') + ".java";
        return "src/main/java/" + className;
    }

    private int[] countLinesAndCharacters(File file) {
        int lines = 0;
        int characters = 0;
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines++;
                    characters += line.length();
                }
            } catch (IOException e) {
                logger.error("Failed to read file: %s", file.getAbsolutePath(), e);
            }
        }
        return new int[]{lines, characters};
    }

    private static class CategoryInfo {
        private final String name;
        private int count;
        private int lines;
        private int chars;

        public CategoryInfo(String name) {
            this.name = name;
        }

        public void increment(int lines, int chars) {
            this.count++;
            this.lines += lines;
            this.chars += chars;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }

        public int getLines() {
            return lines;
        }

        public int getChars() {
            return chars;
        }
    }
}
