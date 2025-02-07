package dev.TTs.TTsGames;

import dev.TTs.util.PackageAnalyzer;

public class Analyze {
    public static void main(String[] args) {
        Main.load(new String[]{"noLog"});

        PackageAnalyzer<Object> analyzer = new PackageAnalyzer<>(Object.class);
        analyzer.analyzePackage();

        Main.deMain(new String[]{"noLog"});
    }
}
