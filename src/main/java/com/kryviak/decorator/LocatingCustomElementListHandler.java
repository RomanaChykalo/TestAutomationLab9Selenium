//package com.kryviak.decorator;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.pagefactory.ElementLocator;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LocatingCustomElementListHandler implements InvocationHandler {
//
//        private final ElementLocator locator;
//        private final Class<MyElement> clazz;
//
//        LocatingCustomElementListHandler(ElementLocator locator,
//                                         Class<MyElement> clazz) {
//            this.locator = locator;
//            this.clazz = clazz;
//        }
//
//        public Object invoke(Object object, Method method,
//                             Object[] objects) throws Throwable {
//            // Находит список WebElement и обрабатывает каждый его элемент,
//            // возвращает новый список с элементами кастомного класса
//            List<WebElement> elements = locator.findElements();
//            List<MyElement> customs = new ArrayList<>();
//
//            for (WebElement element : elements) {
//                customs.add(WrapperFactory.createInstance(clazz, element));
//            }
//            try {
//                return method.invoke(customs, objects);
//            } catch (InvocationTargetException e) {
//                throw e.getCause();
//            }
//        }
//}
