package com.epam.java.training.jvm.task1;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import lombok.SneakyThrows;

/**
 * Created by Vladislav Zhelezov.
 */
public class MainSubsection5 {

    private static final String generatedMethodName = "methodGenerateLocalVar";

    @SneakyThrows
    public static void main(String[] args) {
        CtClass generatedClass = ClassPool.getDefault().makeClass("ClassWithoutReflection");
        CtMethod generatedMethod = CtNewMethod.make(getMethodBodyString(5_000), generatedClass);
        generatedClass.addMethod(generatedMethod);
        generatedClass.toClass();
    }

    private static String getMethodBodyString(final int numberOfLocalVarsToGenerate) {
        //do method with very much vars and the end count all vars
        StringBuilder methodBody = new StringBuilder("public static void ")
                .append(generatedMethodName).append("() {");
        long countLocalVar = 0;
        StringBuilder localVarsString = new StringBuilder(" int result = localVar0");
        while (countLocalVar < numberOfLocalVarsToGenerate) {
            methodBody.append(" int localVar").append(countLocalVar)
                    .append(" = ").append(countLocalVar).append(";");
            localVarsString.append(" + ").append("localVar").append(countLocalVar);
            countLocalVar++;
        }
        localVarsString.append(";");
        methodBody.append(localVarsString).append(" }");
        return methodBody.toString();
    }


}

