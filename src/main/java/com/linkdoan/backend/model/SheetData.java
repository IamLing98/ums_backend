package com.linkdoan.backend.model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define sheet data representation for sheet excel
 */
public class SheetData {

    /**
     * The data of #{key} is stored in the sheet page
     */
    private Map<String, Object> map = new HashMap<String, Object>();

    /**
     * List data storage sheet page replaces ${key} and assigns values​down the column
     */
    private List<Object> datas = new LinkedList<Object>();

    private String name;

    public SheetData(Map<String, Object> map, List<Object> datas, String name) {
        this.map = map;
        this.datas = datas;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SheetData(String name) {
        super();
        this.name = name;
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public Object get(String key) {
        return map.get(key);
    }

    /**
     * Clean up map storage and data storage
     */
    public void clear() {
        map.clear();
        datas.clear();
    }

    public void addData(Object t) {
        datas.add(t);
    }

    public void addDatas(List<? extends Object> list) {
        datas.addAll(list);
    }


    public List<Object> getDatas() {
        return datas;
    }

}