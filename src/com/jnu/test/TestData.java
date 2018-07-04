package com.jnu.test;

public class TestData {
    public long id;
    public String name;

    public TestData(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
