package dev.TTs.lang.processor;

import dev.TTs.lang.Buggy;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("dev.TTs.lang.Buggy")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@Buggy(reason = "Does not warn about Buggy Method usage")
public class BuggyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Buggy.class)) {
            Buggy buggy = element.getAnnotation(Buggy.class);
            String warningMessage = String.format("Element %s is marked as @Buggy: %s",
                    element.getSimpleName(), String.join(", ", buggy.reason()));

            switch (element.getKind()) {
                case CLASS:
                    processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.WARNING,
                            warningMessage + " (Class)");
                    break;
                case FIELD:
                    processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.WARNING,
                            warningMessage + " (Field)");
                    break;
                case METHOD:
                    processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.WARNING,
                            warningMessage + " (Method)");
                    break;
                case PARAMETER:
                    processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.WARNING,
                            warningMessage + " (Parameter)");
                    break;
                case CONSTRUCTOR:
                    processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.WARNING,
                            warningMessage + " (Constructor)");
                    break;
                default:
                    processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.WARNING,
                            warningMessage);
            }
        }
        return true;
    }
}
