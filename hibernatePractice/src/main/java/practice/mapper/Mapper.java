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
                    } else if (getParam instanceof List) {
                        List<?> arrayListIn = new ArrayList<>((ArrayList<?>) getParam);
                        List<Object> newArrayListObjects = new ArrayList<>();

                        if (!arrayListIn.isEmpty()) {
                            Object o1 = arrayListIn.get(0);
                            if (!classHashSet.contains(o1.getClass())) {
                                for (Object o : arrayListIn) {
                                    Class<?> genericFromSetMethod = getGenericFromSetMethod(entry.getValue().getMethod());
                                    Object run = run(o, genericFromSetMethod);
                                    newArrayListObjects.add(run);
                                }
                                entry.getValue().getMethod().invoke(objectOut, newArrayListObjects);
                            } else {
                                entry.getValue().getMethod().invoke(objectOut, arrayListIn);
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

    private Class<?> getGenericFromSetMethod(Method method) {
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
    }

    public HashMap<String, MetaData> addMetaDataSet(Class<?> typeIn) {
        HashMap<String, MetaData> hashMap = new HashMap<>();

        List<Method> methods = Arrays.stream(typeIn.getMethods())
                .filter(x -> x.getName().startsWith(SET))
                .collect(Collectors.toList());

        for (Method method : methods) {
            MetaData metaData = new MetaData();
            Class<?> type = method.getParameterTypes()[0];
            if (type.equals(List.class)) {
                Class<?> genericType = getGenericFromSetMethod(method);
                metaData.setGenericType(genericType);
            } else if(type.isArray()){
                System.out.println(type);
            }
            else {
                if (!classHashSet.contains(type)) {
                    metaData.setMetaData(addMetaDataSet(type));
                } else {

                }
            }
            String paramName = Introspector.decapitalize(method.getName().substring(3));
            metaData.setParamName(paramName);
            metaData.setMethod(method);
            metaData.setType(type);
            hashMap.put(paramName, metaData);
        }
        if (!metaDataSetters.containsKey(typeIn)) {
            metaDataSetters.put(typeIn, hashMap);
        }
        return hashMap;
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
        classHashSet.add(Double.class);
        classHashSet.add(double.class);
    }

    private class MetaData {

        private String paramName;
        private Method method;
        private Class<?> type;
        private Class<?> genericType;
        private HashMap<String, MetaData> metaData;

        public MetaData() {
        }

        public MetaData(String paramName, Method method, Class<?> type, Class<?> genericType) {
            this.paramName = paramName;
            this.method = method;
            this.type = type;
            this.genericType = genericType;
        }

        public MetaData(String paramName, Method method, Class<?> type) {
            this.paramName = paramName;
            this.method = method;
            this.type = type;
        }

        public MetaData(String paramName, Method method) {
            this.paramName = paramName;
            this.method = method;
        }

        public HashMap<String, MetaData> getMetaData() {
            return metaData;
        }

        public void setMetaData(HashMap<String, MetaData> metaData) {
            this.metaData = metaData;
        }

        public Class<?> getGenericType() {
            return genericType;
        }

        public void setGenericType(Class<?> genericType) {
            this.genericType = genericType;
        }

        public Class<?> getType() {
            return type;
        }

        public void setType(Class<?> type) {
            this.type = type;
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
