package com.seekting.libprocessor;

import com.google.auto.service.AutoService;
import com.seekting.libanno.MyAnno;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * Created by Administrator on 2017/11/20.
 */
@AutoService(Processor.class)
public class AnnoProcesser extends AbstractProcessor {
    private Types mTypeUtils;
    private Elements mElementUtils;
    private Filer mFiler;
    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);


        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set == null || set.isEmpty()) {
            info(">>> set is null... <<<");
            return true;
        }

        info(">>> Found field, start... <<<");

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(MyAnno.class);

        if (elements == null || elements.isEmpty()) {
            info(">>> elements is null... <<<");
            return true;
        }

        for (Element annotatedElement : elements) {

            if (annotatedElement.getKind() != ElementKind.METHOD) {
                error(annotatedElement, "Only classes can be annotated with @%s",
                        MyAnno.class.getSimpleName());
                return true;
            }
            MyAnno annotation = annotatedElement.getAnnotation(MyAnno.class);
            Class<? extends Annotation> type = annotation.annotationType();
            Retention yuan = type.getAnnotation(Retention.class);
            info("haha" + annotatedElement.getAnnotation(MyAnno.class).toString());
//            info("haha1" + type);
            info("haha1" + yuan.value());

            MethodSpec main = MethodSpec.methodBuilder("main")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(void.class)
                    .addParameter(String[].class, "args")
                    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                    .build();

            TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addMethod(main)
                    .build();

            JavaFile javaFile = JavaFile.builder("com.seekting.helloano", helloWorld)
                    .build();

            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
                info("process error");
            }
        }

        info("process end");
        return true;

    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(MyAnno.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(Element e, String msg, Object... args) {
        mMessager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }

    private void info(String msg, Object... args) {
        mMessager.printMessage(
                Diagnostic.Kind.NOTE,
                String.format(msg, args));
    }
}
