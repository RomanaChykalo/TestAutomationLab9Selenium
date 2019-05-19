package com.igor.decorator.tools;

import com.igor.decorator.element.AbstractElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;
import java.util.Objects;

public class CustomFieldDecorator extends DefaultFieldDecorator {

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    /**
     * Метод вызывается фабрикой для каждого поля в классе
     */
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<AbstractElement> decoratableClass = decoratableClass(field);
        // если класс поля декорируемый
        if (!Objects.isNull(decoratableClass)) {
            ElementLocator locator = factory.createLocator(field);
            if (Objects.isNull(locator)) {
                return null;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }

            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    /**
     * Возвращает декорируемый класс поля,
     * либо null если класс не подходит для декоратора
     */
    @SuppressWarnings("unchecked")
    private Class<AbstractElement> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)) {

            // для списка обязательно должна быть задана аннотация
            if (Objects.isNull(field.getAnnotation(FindBy.class)) &&
                    Objects.isNull(field.getAnnotation(FindBys.class))) {
                return null;
            }

            // Список должен быть параметризирован
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            // получаем класс для элементов списка
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }

        if (AbstractElement.class.isAssignableFrom(clazz)) {
            return (Class<AbstractElement>) clazz;
        }
        else {
            return null;
        }
    }

    /**
     * Создание элемента.
     * Находит WebElement и передает его в кастомный класс
     */
    private AbstractElement createElement(ClassLoader loader, ElementLocator locator,
                                          Class<AbstractElement> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    /**
     * Создание списка
     */
    @SuppressWarnings("unchecked")
    private List<AbstractElement> createList(ClassLoader loader, ElementLocator locator,
                                             Class<AbstractElement> clazz) {
        InvocationHandler handler = new LocatingCustomElementListHandler(locator, clazz);
        return (List<AbstractElement>) Proxy.newProxyInstance(
                loader, new Class[] {List.class}, handler);
    }

}