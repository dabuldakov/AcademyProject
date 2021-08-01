package practice.mapper;

import java.beans.Introspector;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class Mapper {
    private HashMap<Class<?>, HashMap<String, MetaData>> metaDataSetters;
    private HashMap<Class<?>, HashMap<String, MetaData>> metaDataGetters;
    private HashSet<Class<?>> classHashSet;
    private static final String GET = "get";
    private static final String SET = "set";


    public Mapper() {
        metaDataGetters = new HashMap<>();
        metaDataSetters = new HashMap<>();
        createTypeMap();
    }

    public <T> T run(Object objectIn, Class<T> typeOut) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> typeIn = objectIn.getClass();
        Constructor<T> constructor = typeOut.getConstructor();
        Object objectOut = constructor.newInstance();
        HashMap<String, MetaData> hashMapIn = checkMetaDataGet(typeIn);
        HashMap<String, MetaData> hashMapOut = checkMetaDataSet(typeOut);

        for (Map.Entry<String, MetaData> entry : hashMapOut.entrySet()) {
            MetaData metaData = hashMapIn.get(entry.getKey());
            if (metaData != null) {
                Object getParam = metaData.getMethod().invoke(objectIn);
                if (getParam != null) {
                    if (getParam.getClass().isArray()) {
                        //Object[] newArray = (Object[]) getParam;
                        entry.getValue().getMethod().invoke(objectOut, getParam);
                        // TODO: 8/1/2021 down if array contain objects 
                    } else if (getParam instanceof Collection) {
                        List<?> newCollection = new ArrayList<>((Collection<?>) getParam);
                        List<Object> newCollectionObjects = new ArrayList<>();

                        if (!newCollection.isEmpty()) {
                            Object o1 = newCollection.get(0);
                            if (!classHashSet.contains(o1.getClass())) {
                                for (Object o : newCollection) {
                                    Class<?> genericFromSetMethod = getGenericFromSetMethod(entry.getValue().getMethod());
                                    Object run = run(o, genericFromSetMethod);
                                    newCollectionObjects.add(run);
                                }
                                entry.getValue().getMethod().invoke(objectOut, newCollectionObjects);
                            } else {
                                entry.getValue().getMethod().invoke(objectOut, newCollection);
                            }
                        }

                    } else {
                        if (!classHashSet.contains(getParam.getClass())) {
                            Class<?> aClass = entry.getValue().getMethod().getParameterTypes()[0];
                            getParam = run(getParam, aClass);
                        }
                        entry.getValue().getMethod().invoke(objectOut, getParam);
                    }
                }
            }
        }

        return (T) objectOut;
    }

    private Class<?> getGenericFromSetMethod(Method method){
        Type genericParameterType = method.getGenericParameterTypes()[0];
        ParameterizedType parameterizedType = (ParameterizedType) genericParameterType;
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

    private HashMap<String, MetaData> checkMetaDataSet(Class<?> type) {
        HashMap<String, MetaData> hashMap;
        if (metaDataSetters.get(type) == null) {
            hashMap = addMetaData(type, SET);
        } else {
            hashMap = metaDataSetters.get(type);
        }
        return hashMap;
    }

    private HashMap<String, MetaData> checkMetaDataGet(Class<?> type) {
        HashMap<String, MetaData> hashMap;
        if (metaDataGetters.get(type) == null) {
            hashMap = addMetaData(type, GET);
        } else {
            hashMap = metaDataGetters.get(type);
        }
        return hashMap;
    }

    private HashMap<String, MetaData> addMetaData(Class<?> typeIn, String setGet) {
        List<Method> methods = Arrays.stream(typeIn.getMethods())
                .filter(x -> x.getName().startsWith(setGet))
                .collect(Collectors.toList());
        HashMap<String, MetaData> hashMap = new HashMap<>();
        for (Method method : methods) {
            String paramName = Introspector.decapitalize(method.getName().substring(3));
            MetaData metaData = new MetaData(paramName, method);
            hashMap.put(paramName, metaData);
        }
        if (setGet.equals(GET))
            metaDataGetters.put(typeIn, hashMap);
        else if (setGet.equals(SET))
            metaDataSetters.put(typeIn, hashMap);

        return hashMap;

        /*
            Arrays.stream(methods)
                    .filter(x -> x.getName().startsWith("set"))
                    .map(x -> hashMapGetters.put(Introspector.decapitalize(x.getName().substring(3)), new MetaData(Introspector.decapitalize(x.getName().substring(3)), x)));
*/
    }

    private void createTypeMap() {
        classHashSet = new HashSet<>();
        classHashSet.add(String.class);
        classHashSet.add(long.class);
        classHashSet.add(Long.class);
        classHashSet.add(int.class);
        classHashSet.add(Integer.class);
        classHashSet.add(boolean.class);
        classHashSet.add(Date.class);
    }

    private class MetaData {

        private String paramName;
        private Method method;

        public MetaData(String paramName, Method method) {
            this.paramName = paramName;
            this.method = method;
        }

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }


    }
}
