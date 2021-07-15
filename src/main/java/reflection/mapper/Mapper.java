package reflection.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Mapper<TYPE extends Object> {

    private Class<TYPE> type;
    Field[] declaredFields;
    TYPE object;
    String[] stringsPare = new String[2];
    Map<String, MapperPare> mapperPareMap;

    public Mapper(Class<TYPE> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.type = type;
        declaredFields = type.getDeclaredFields();
        Constructor<TYPE> constructor = this.type.getConstructor();
        object = constructor.newInstance();
    }

    public String serialize(Object object) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        int length = declaredFields.length;
        for (Field field : declaredFields) {
            length--;
            Class<?> type = field.getType();
            JsonParse annotation = field.getAnnotation(JsonParse.class);
            String fieldName =  (annotation != null) ? annotation.name() : field.getName();
            String value = "";
            field.setAccessible(true);


            if (type.equals(long.class)) {
                long longNumber = (long) field.get(object);
                value = String.valueOf(longNumber);
            } else if (type.equals(String.class)) {
                value = "\"" + field.get(object) + "\"";
            } else if (type.equals(int.class)) {
                int intNumber = (int) field.get(object);
                value = String.valueOf(intNumber);
            } else if (field.get(object) == null){
                value = "null";
            } else if(type.equals((boolean.class))){
                value = field.get(object).toString();
            } else {
                Class<?> typeField = field.getType();
                Mapper<?> insideObject = new Mapper<>(typeField);
                value = insideObject.serialize(field.get(object));
            }

            builder.append("\"").append(fieldName).append("\"").append(":").append(value);
            if (length != 0) {
                builder.append(",");
            }
        }
        builder.append("}");

        return builder.toString();
    }

    public Object deSerialize(String string) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        char[] chars = String.valueOf(string).toCharArray();
        boolean passValue = true;
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
                addFiledObject(stringsPare);
            } else if (chars[i] == '"' && !passValue) {
                passValue = true;
                StringBuilder builder = new StringBuilder();
                i++;
                while (chars[i] != '"') {
                    builder.append(chars[i]);
                    i++;
                }
                stringsPare[1] = builder.toString();
                addField(stringsPare);
            } else if (Character.isDigit(chars[i])) {
                passValue = true;
                StringBuilder builder = new StringBuilder();
                while (Character.isDigit(chars[i])) {
                    builder.append(chars[i]);
                    i++;
                }
                stringsPare[1] = builder.toString();
                addField(stringsPare);
            } else if (chars[i] == 'n') {
                passValue = true;
                i = i + 4;
                stringsPare[1] = "null";
                addField(stringsPare);
            } else if (chars[i] == 't') {
                passValue = true;
                i = i + 4;
                stringsPare[1] = "true";
                addField(stringsPare);
            } else if (chars[i] == 'f') {
                passValue = true;
                i = i + 4;
                stringsPare[1] = "false";
                addField(stringsPare);
            }
        }
        return object;
    }

    private void addFiledObject(String[] strings) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (Field field : declaredFields) {
            if (field.getName().equals(strings[0])) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                Mapper<?> personMapper = new Mapper<>(type);
                field.set(object, personMapper.deSerialize(strings[1]));
            }
            stringsPare = new String[2];
        }
    }


    private void addField(String[] strings) throws IllegalAccessException {
        for (Field field : declaredFields) {
            JsonParse annotation = field.getAnnotation(JsonParse.class);
            String fieldName = (annotation != null) ? annotation.name() : field.getName();

            if (fieldName.equals(strings[0])) {
                field.setAccessible(true);
                Class<?> type = field.getType();

                if (type.equals(long.class))
                    field.set(object, Long.valueOf(strings[1].replace(" ", "")));
                else if (type.equals(String.class))
                    field.set(object, strings[1].replace("\"", "").replace("\n", ""));
                else if (type.equals(int.class)) {
                    field.set(object, Integer.parseInt(strings[1].replace(" ", "")));
                } else if (type.equals(boolean.class)) {
                    field.set(object, (strings[1].equals("true")));
                } else if (strings[1].equals("null")) {
                    field.set(object, null);
                }
            }
        }
        stringsPare = new String[2];
    }

    class MapperPare{

        private Field field;

        public MapperPare(Field field, String type) {
            this.field = field;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }
    }
}
