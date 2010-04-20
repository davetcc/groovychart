package com.thecoderscorner.groovychart.util

class AutoBeanPropertySetter {
    public static void autoSetProp(Object bean, String prop, Object value) {
        bean[prop] = value
    }
}
