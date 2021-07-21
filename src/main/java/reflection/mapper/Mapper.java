package reflection.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;

public class Mapper {

    private Field[] declaredFields;
    private HashMap<Class<?>, HashMap<String, MetaData>> metaDataHashMap;
    private HashSet<Class<?>> classHashSet;

    public Mapper() {
        metaDataHashMap = new HashMap<>();
        createTypeMap();
    }

    public String serialize(Object object, Class<?> type) throws IllegalAccessException {
        declaredFields = type.getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        int length = declaredFields.length;
        for (Field field : declaredFields) {
            length--;
            Class<?> fieldType = field.getType();
            JsonParse annotation = field.getAnnotation(JsonParse.class);
            String fieldName = (annotation != null) ? annotation.name() : field.getName();
            field.setAccessible(true);
            String value = "";

            if (fieldType.equals(long.class)) {
                long longNumber = (long) field.get(object);
                value = String.valueOf(longNumber);
            } else if (fieldType.equals(String.class)) {
                value = "\"" + field.get(object) + "\"";
            } else if (fieldType.equals(int.class)) {
                int intNumber = (int) field.get(object);
                value = String.valueOf(intNumber);
            } else if (field.get(object) == null) {
                value = "null";
            } else if (fieldType.equals((boolean.class))) {
                value = field.get(object).toString();
            } else {
                value = serialize(field.get(object), fieldType);
            }

            builder.append("\"").append(fieldName).append("\"").append(":").append(value);
            if (length != 0) {
                builder.append(",");
            }
        }
        builder.append("}");

        return builder.toString();
    }

    public <T> T deSerialize(String string, Class<T> type) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        declaredFields = type.getDeclaredFields();
        char[] chars = String.valueOf(string).toCharArray();
        boolean passValue = true;
        Constructor<T> constructor = type.getConstructor();
        Object object = constructor.newInstance();
        String[] stringsPare = new String[2];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '"' && passValue) {
                passValue = false;
                StringBuilder builder = new StringBuilder();
                i++;
                while (chars[i] != '"') {
                    builder.append(chars[i]);
                    i++;
                }
                stringsPare[0] = builder.toString();
            } else if (chars[i] == '{' && i > 0) {
                passValue = true;
                StringBuilder builder = new StringBuilder();
                builder.append(chars[i]);
                i++;
                while (chars[i] != '}') {
                    builder.append(chars[i]);
                    i++;
                }
                builder.append(chars[i]);
                stringsPare[1] = builder.toString();
                addField(stringsPare, object);
            } else if (chars[i] == '"' && !passValue) {
                passValue = true;
                StringBuilder builder = new StringBuilder();
                i++;
                while (chars[i] != '"') {
                    builder.append(chars[i]);
                    i++;
                }
                stringsPare[1] = builder.toString();
                addField(stringsPare, object);
                stringsPare = new String[2];
            } else if (Character.isDigit(chars[i])) {
                passValue = true;
                StringBuilder builder = new StringBuilder();
                while (Character.isDigit(chars[i])) {
                    builder.append(chars[i]);
                    i++;
                }
                stringsPare[1] = builder.toString();
                addField(stringsPare, object);
                stringsPare = new String[2];
            } else if (chars[i] == 'n') {
                passValue = true;
                i = i + 4;
                stringsPare[1] = "null";
                addField(stringsPare, object);
                stringsPare = new String[2];
            } else if (chars[i] == 't') {
                passValue = true;
                i = i + 4;
                stringsPare[1] = "true";
                addField(stringsPare, object);
                stringsPare = new String[2];
            } else if (chars[i] == 'f') {
                passValue = true;
                i = i + 4;
                stringsPare[1] = "false";
                addField(stringsPare, object);
                stringsPare = new String[2];
            }
        }
        return (T) object;
    }

    private void addField(String[] stringsPare, Object object) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Class<?> aClass = object.getClass();
        HashMap<String, MetaData> hashMap = metaDataHashMap.get(aClass);
        MetaData metaData = hashMap.get(stringsPare[0]);
        if (metaData != null) {
            Field field = metaData.getField();
            if (field != null) {
                Class<?> type = field.getType();
                if (type.equals(long.class))
                    field.set(object, Long.valueOf(stringsPare[1]));
                else if (type.equals(String.class))
                    field.set(object, stringsPare[1].replace("\n", ""));
                else if (type.equals(int.class)) {
                    field.set(object, Integer.parseInt(stringsPare[1]));
                } else if (type.equals(boolean.class)) {
                    field.set(object, (stringsPare[1].equals("true")));
                } else if (stringsPare[1].equals("null")) {
                    field.set(object, null);
                } else {
                    // TODO: 7/19/2021 can down on not recognize type
                    field.set(object, deSerialize(stringsPare[1], field.getType()));
                }
            }
        }
    }

    public void addMetaData(Class<?> type) {
        if (metaDataHashMap.get(type) == null) {
            Field[] declaredFields = type.getDeclaredFields();
            HashMap<String, MetaData> hashMap = new HashMap<>();
            for (Field field : declaredFields) {
                JsonParse annotation = field.getAnnotation(JsonParse.class);
                String fieldName = (annotation != null) ? annotation.name() : field.getName();
                MetaData metaData = new MetaData();
                field.setAccessible(true);
                metaData.setField(field);
                metaData.setFieldType(field.getType());
                hashMap.put(fieldName, metaData);
                metaDataHashMap.put(type, hashMap);
                if (!classHashSet.contains(field.getType())) {
                    addMetaData(field.getType());
                }
            }
        }
    }

    private void createTypeMap() {
        classHashSet = new HashSet<>();
        classHashSet.add(long.class);
        classHashSet.add(String.class);
        classHashSet.add(int.class);
        classHashSet.add(boolean.class);
    }

    private class MetaData {

        private Field field;
        private Class<?> fieldType;

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public Class<?> getFieldType() {
            return fieldType;
        }

        public void setFieldType(Class<?> fieldType) {
            this.fieldType = fieldType;
        }
    }

}
