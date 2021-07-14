package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class Mapper<TYPE extends Object> {

    private Class<TYPE> type;
    Field[] declaredFields;
    TYPE object;
    String[] stringsPare = new String[2];

    public Mapper(Class<TYPE> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.type = type;
        declaredFields = type.getDeclaredFields();
        Constructor<TYPE> constructor = this.type.getConstructor();
        object = constructor.newInstance();
    }

    public String serialize(Object object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        int length = declaredFields.length;
        for (Field field : declaredFields) {
            length--;
            Class<?> type = field.getType();
            String name = field.getName();
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
            }

            builder.append("\"").append(name).append("\"").append(":").append(value);
            if (length != 0) {
                builder.append(",");
            }
        }
        builder.append("}");

        return builder.toString();
    }

    public Object deSerialize(String string) throws IllegalAccessException {
        char[] chars = String.valueOf(string).toCharArray();
        boolean passValue = true;
        for (int i = 0; i < chars.length; i++) {

            if (chars[i] == '{' && i > 0){
                StringBuilder builder = new StringBuilder();
                builder.append(chars[i]);
                i++;
                while (chars[i] != '}'){
                    builder.append(chars[i]);
                    i++;
                }
                builder.append(chars[i]);
                //Mapper<?> mapper = new Mapper<>();
                Object o = deSerialize(builder.toString());
            } else if(chars[i] == '"' && passValue){
                passValue = false;
                StringBuilder builder = new StringBuilder();
                i++;
                while (chars[i] != '"'){
                    builder.append(chars[i]);
                    i++;
                }
                stringsPare[0] = builder.toString();
            } else if(chars[i] == ':' && !passValue){
                passValue = true;
                StringBuilder builder = new StringBuilder();
                i++;
                while (chars[i] != ',' && chars[i] != '}'){
                    builder.append(chars[i]);
                    i++;
                }
                stringsPare[1] = builder.toString();
                addField(stringsPare);
            }
        }
        return object;
    }

    private String getInsideObject(char[] chars){

        return null;
    }

    private void addField(String[] strings) throws IllegalAccessException {
        for (Field field : declaredFields) {
            if (field.getName().equals(strings[0])){
                field.setAccessible(true);
                Class<?> type = field.getType();

                if (type.equals(long.class))
                    field.set(object, Long.valueOf(strings[1].replace(" ", "")));
                else if(type.equals(String.class))
                    field.set(object, strings[1].replace("\"","").replace("\n", ""));
                else if(type.equals(int.class)){
                    field.set(object, Integer.parseInt(strings[1].replace(" ", "")));
                }
            }
        }
        stringsPare = new String[2];
    }
}
