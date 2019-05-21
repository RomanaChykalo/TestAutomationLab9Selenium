package com.kryviak.decorator;

import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class CustomFieldDecor extends DefaultFieldDecorator {

    public CustomFieldDecor(ElementLocatorFactory factory) {
        super(factory);
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {

        ElementLocator locator = factory.createLocator(field);

        if (ButtonDecor.class.isAssignableFrom(field.getType())) {
            return new ButtonDecor(proxyForLocator(loader, locator));
        } else if (InputTextDecor.class.isAssignableFrom(field.getType())) {
            return new InputTextDecor(proxyForLocator(loader, locator));
        } else if (LinkDecor.class.isAssignableFrom(field.getType())) {
            return new LinkDecor(proxyForLocator(loader, locator));
        } else if (MyElement.class.isAssignableFrom(field.getType())) {
            return new MyElement(proxyForLocator(loader, locator));
        } else {
            return super.decorate(loader, field);
        }
    }
}
