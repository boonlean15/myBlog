package com.cheney.executor;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@Slf4j
public class LogUtils {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class cli = String.class;

        String name1 = cli.getName();
        log.info("cli getName ---- " + name1);

        Object o = cli.getConstructor(String.class).newInstance("test");
        log.info("o ---- " + o);

        Field[] fields = cli.getFields();
        for(Field f : fields){
            int modifiers = f.getModifiers();
            log.info("modifiers --- " + modifiers);
            boolean aPrivate = Modifier.isPrivate(modifiers);
            boolean aPublic = Modifier.isPublic(modifiers);
            log.info("aPrivate ---- " + aPrivate + "aPublic --- " + aPublic);


            Class<?> declaringClass = f.getDeclaringClass();
            String name = declaringClass.getName();
            log.info("declaringClass name ----- " + name);

            String fieldName = f.getName();
            log.info("fieldName ----- " + fieldName);

            f.setAccessible(true);


        }


        Class componentType = cli.getComponentType();

        String cl = componentType+"[]";
        log.info("componentType ---- " + cl);

        Method[] methods = cli.getMethods();
        Method method = methods[0];

    }


}
