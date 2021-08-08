package practice.mapper;

import practice.mapper.model.CarDto;

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
        createTypeSet();
    }

    public Mapper(ArrayList<Class<?>> classes) {
        metaDataGetters = new HashMap<>();
        metaDataSetters = new HashMap<>();
        createTypeSet();
        startUpClasses(classes);
    }

    private void createTypeSet() {
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

    private void startUpClasses(ArrayList<Class<?>> classes) {
        for (Class<?> startClass : classes) {
            checkMetaDataGet(startClass);
            checkMetaDataSet(startClass);
        }
    }

    public <T> T convert(Object objectIn, Class<T> typeOut) {
        try {
            Class<?> typeIn = objectIn.getClass();
            Constructor<T> constructor = typeOut.getConstructor();
            T objectOut = constructor.newInstance();
            HashMap<String, MetaData> hashMapIn = checkMetaDataGet(typeIn);
            HashMap<String, MetaData> hashMapOut = checkMetaDataSet(typeOut);
            for (Map.Entry<String, MetaData> entry : hashMapOut.entrySet()) {
                MetaData metaDataIn = hashMapIn.get(entry.getKey());
                if (metaDataIn != null) {
                    Object valueIn = metaDataIn.getMethod().invoke(objectIn);
                    if (valueIn != null) {
                        if (valueIn.getClass().isArray()) {
                            //Object[] newArray = (Object[]) valueIn;
                            entry.getValue().getMethod().invoke(objectOut, valueIn);
                            // TODO: 8/1/2021 down if array contain objects
                        } else if (valueIn instanceof Collection) {
                            List<?> arrayListIn = new ArrayList<>((Collection<?>) valueIn);
                            List<Object> newArrayListObjects = new ArrayList<>();

                            if (!arrayListIn.isEmpty()) {
                                Object o1 = arrayListIn.get(0);
                                if (!classHashSet.contains(o1.getClass())) {
                                    for (Object o : arrayListIn) {
                                        Class<?> genericFromSetMethod = getGenericFromMethod(entry.getValue().getMethod(), SET);
                                        Object convert = convert(o, genericFromSetMethod);
                                        newArrayListObjects.add(convert);
                                    }
                                    entry.getValue().getMethod().invoke(objectOut, newArrayListObjects);
                                } else {
                                    entry.getValue().getMethod().invoke(objectOut, arrayListIn);
                                }
                            }

                        } else {
                            if (!classHashSet.contains(valueIn.getClass())) {
                                Class<?> aClass = entry.getValue().getMethod().getParameterTypes()[0];
                                valueIn = convert(valueIn, aClass);
                            }
                            entry.getValue().getMethod().invoke(objectOut, valueIn);
                        }
                    }
                }
            }
            return objectOut;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T convertStructureRecursion(Object objectIn, Class<T> typeOut, HashMap<String, MetaData> hashMapIn, HashMap<String, MetaData> hashMapOut) {
        try {
            T objectOut = typeOut.getConstructor().newInstance();
            for (Map.Entry<String, MetaData> entry : hashMapOut.entrySet()) {
                MetaData metaDataIn = hashMapIn.get(entry.getKey());
                MetaData metaDataOut = hashMapOut.get((entry.getKey()));
                if (metaDataIn == null || metaDataOut == null)
                    continue;
                Object valueIn = metaDataIn.getMethod().invoke(objectIn);
                if (valueIn == null)
                    continue;
                if (metaDataOut.type.isArray()) {
                    if (metaDataOut.getMetaData() != null){
                        Object o = convertArray(valueIn, metaDataIn, metaDataOut);
                        entry.getValue().getMethod().invoke(objectOut, o);
                        // TODO: 8/1/2021 down if array contain objects
                    } else {
                        entry.getValue().getMethod().invoke(objectOut, valueIn);
                    }
                } else if (valueIn instanceof Collection) {
                    if (metaDataOut.getMetaData() != null) {
                        Collection<?> objects = convertCollection(valueIn, metaDataIn, metaDataOut);
                        entry.getValue().getMethod().invoke(objectOut, objects);
                    } else {
                        entry.getValue().getMethod().invoke(objectOut, valueIn);
                    }
                } else {
                    if (metaDataOut.getMetaData() != null) {
                        valueIn = convertStructureRecursion(valueIn, metaDataOut.getType(), metaDataIn.getMetaData(), metaDataOut.getMetaData());
                    }
                    entry.getValue().getMethod().invoke(objectOut, valueIn);
                }
            }
            return objectOut;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T convertStructure(Object objectIn, Class<T> typeOut) {
        return convertStructureRecursion(
                objectIn,
                typeOut,
                metaDataGetters.get(objectIn.getClass()),
                metaDataSetters.get(typeOut));
    }

    private <T,U> U[] convertArray(Object valueIn, MetaData metaDataIn, MetaData metaDataOut) {
        Object[] valueInArray = (Object[]) valueIn;
        U[] newArray = (U[]) new Object[valueInArray.length];
        for (int i = 0; i < valueInArray.length; i++) {
            newArray[i] = (U) convertStructureRecursion(valueInArray[i], metaDataOut.getGenericType(), metaDataIn.getMetaData(), metaDataOut.getMetaData());
        }
        return newArray;
    }
    private Collection<?> convertCollection(Object valueIn, MetaData metaDataIn, MetaData metaDataOut) {
        if (metaDataOut.getType().equals(List.class)) {
            List<Object> newArrayListObjects = new ArrayList<>();
            Collection<?> collection = (Collection<?>) valueIn;
            if (!collection.isEmpty()) {
                for (Object next : collection) {
                    Object o = convertStructureRecursion(next, metaDataOut.getGenericType(), metaDataIn.getMetaData(), metaDataOut.getMetaData());
                    newArrayListObjects.add(o);
                }
            }
            return newArrayListObjects;
        } else if (metaDataOut.getType().equals(Queue.class)) {
            return null;
        } else if (metaDataOut.getType().equals(Set.class)) {
            return null;
        }
        return null;
    }

    private Class<?> getGenericFromMethod(Method method, String setOrGet) {
        Type genericParameterType;
        if (setOrGet.equals(SET))
            genericParameterType = method.getGenericParameterTypes()[0];
        else
            genericParameterType = method.getGenericReturnType();
        ParameterizedType parameterizedType = (ParameterizedType) genericParameterType;
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

    private HashMap<String, MetaData> checkMetaDataSet(Class<?> type) {
        HashMap<String, MetaData> hashMap;
        if (metaDataSetters.get(type) == null) {
            hashMap = addMetaDataStructure(type, SET);
            metaDataSetters.put(type, hashMap);
        } else {
            hashMap = metaDataSetters.get(type);
        }
        return hashMap;
    }

    private HashMap<String, MetaData> checkMetaDataGet(Class<?> type) {
        HashMap<String, MetaData> hashMap;
        if (metaDataGetters.get(type) == null) {
            hashMap = addMetaDataStructure(type, GET);
            metaDataGetters.put(type, hashMap);
        } else {
            hashMap = metaDataGetters.get(type);
        }
        return hashMap;
    }

    private HashMap<String, MetaData> addMetaData(Class<?> typeIn, String setGet) {
        List<Method> methods = getMethodList(typeIn, setGet);
        HashMap<String, MetaData> hashMap = new HashMap<>();
        for (Method method : methods) {
            String paramName = Introspector.decapitalize(method.getName().substring(3));
            MetaData metaData = new MetaData(paramName, method);
            hashMap.put(paramName, metaData);
        }
        addMetaDataSetOrGet(typeIn, hashMap, setGet);
        return hashMap;
    }

    private HashMap<String, MetaData> addMetaDataStructure(Class<?> typeIn, String setOrGet) {
        HashMap<String, MetaData> hashMap = new HashMap<>();
        List<Method> methodList = getMethodList(typeIn, setOrGet);
        for (Method method : methodList) {
            MetaData metaData = new MetaData();
            Class<?> type = getType(method, setOrGet);
            if (type.equals(List.class)) {
                Class<?> genericFromMethod = getGenericFromMethod(method, setOrGet);
                metaData.setGenericType(genericFromMethod);
                if (!classHashSet.contains(genericFromMethod)) {
                    metaData.setMetaData(addMetaDataStructure(genericFromMethod, setOrGet));
                }
            } else if (type.isArray()) {
                Class<?> componentType = type.getComponentType();
                metaData.setGenericType(componentType);
                if (componentType != null && !classHashSet.contains(componentType)) {
                    metaData.setMetaData(addMetaDataStructure(componentType, setOrGet));
                }
            } else {
                if (!classHashSet.contains(type)) {
                    metaData.setMetaData(addMetaDataStructure(type, setOrGet));
                }
            }
            String paramName = Introspector.decapitalize(method.getName().substring(3));
            metaData.setParamName(paramName);
            metaData.setMethod(method);
            metaData.setType(type);
            hashMap.put(paramName, metaData);
        }
        return hashMap;
    }

    private List<Method> getMethodList(Class<?> type, String setOrGet) {
        return Arrays.stream(type.getMethods())
                .filter(x -> x.getName().startsWith(setOrGet) && !x.getName().contains("getClass"))
                .collect(Collectors.toList());
    }

    private Class<?> getType(Method method, String setOrGet) {
        if (setOrGet.equals(SET))
            return method.getParameterTypes()[0];
        else
            return method.getReturnType();
    }

    private void addMetaDataSetOrGet(Class<?> type, HashMap<String, MetaData> hashMap, String setOrGet) {
        if (setOrGet.equals(SET)) {
            if (!metaDataSetters.containsKey(type)) {
                metaDataSetters.put(type, hashMap);
            }
        } else {
            if (!metaDataGetters.containsKey(type)) {
                metaDataGetters.put(type, hashMap);
            }
        }
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
