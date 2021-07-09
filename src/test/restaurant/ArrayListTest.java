package restaurant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayListTest {

    @Test
    void getArray() {
    }

    @Test
    void setArray() {
    }

    @Test
    void size() {
    }

    @Test
    void shouldAddObjectInEmptyArray() {
        //given
        ArrayList arrayList = new ArrayList();
        String s1 = "string1";
        //when
        arrayList.add(s1);
        //then
        assertEquals("string1", arrayList.get(0));
        assertEquals(1, arrayList.length());
    }

    @Test
    void shouldAddObjectInFullArray(){
        //given
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            String s = "String " + i;
            arrayList.add(s);
        }
        //when
        arrayList.add("last added");
        //then
        assertEquals(21, arrayList.length());
        assertEquals("last added", arrayList.get(20));
    }

    @Test
    void shouldAddObjects(){
        //given
        ArrayList arrayList = new ArrayList();

        //when
        for (int i = 0; i < 200; i++) {
            String s = "String " + i;
            arrayList.add(s);
        }

        //then
        assertEquals(200, arrayList.length());
        assertEquals("String 155", arrayList.get(155));
    }

    @Test
    void contain() {
    }

    @Test
    void get() {
    }

    @Test
    void shouldDeleteOneElement() {
        //given
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            String s = "" + i;
            arrayList.add(s);
        }

        //when
        arrayList.remove(5);

        //then
       assertEquals(arrayList.get(5), "6");
    }

    @Test
    void shouldDeleteManyElements() {
        //given
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            String s = "" + i;
            arrayList.add(s);
        }

        //when
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);
        arrayList.remove(5);

        //then
        assertEquals("18", arrayList.get(5));
        assertEquals(7, arrayList.length());
    }

    @Test
    void removeAll() {
    }

    @Test
    void update() {
    }
}