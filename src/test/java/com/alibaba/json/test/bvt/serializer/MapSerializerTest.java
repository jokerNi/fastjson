package com.alibaba.json.test.bvt.serializer;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.MapSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class MapSerializerTest extends TestCase {

    public void test_empty_1() throws Exception {
        SerializeWriter out = new SerializeWriter();

        MapSerializer mapSerializer = new MapSerializer();
        mapSerializer.write(new JSONSerializer(out), Collections.EMPTY_MAP);

        Assert.assertEquals("{}", out.toString());
    }

    public void test_singleton_1() throws Exception {
        SerializeWriter out = new SerializeWriter();

        MapSerializer mapSerializer = new MapSerializer();
        mapSerializer.write(new JSONSerializer(out), Collections.singletonMap("A", 1));

        Assert.assertEquals("{\"A\":1}", out.toString());
    }

    public void test_int2_s() throws Exception {
        SerializeWriter out = new SerializeWriter();

        MapSerializer mapSerializer = new MapSerializer();
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("A", 1);
        map.put("B", 2);
        mapSerializer.write(new JSONSerializer(out), map);

        Assert.assertEquals("{\"A\":1,\"B\":2}", out.toString());
    }

    public void test_long2_s() throws Exception {
        SerializeWriter out = new SerializeWriter();

        MapSerializer mapSerializer = new MapSerializer();
        Map<String, Long> map = new LinkedHashMap<String, Long>();
        map.put("A", 1L);
        map.put("B", 2L);
        mapSerializer.write(new JSONSerializer(out), map);

        Assert.assertEquals("{\"A\":1,\"B\":2}", out.toString());
    }

    public void test_string2_s() throws Exception {
        SerializeWriter out = new SerializeWriter();

        MapSerializer mapSerializer = new MapSerializer();
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("A", "1");
        map.put("B", "2");
        mapSerializer.write(new JSONSerializer(out), map);

        Assert.assertEquals("{\"A\":\"1\",\"B\":\"2\"}", out.toString());
    }

    public void test_string3_s() throws Exception {
        SerializeWriter out = new SerializeWriter();

        JSONSerializer serializer = new JSONSerializer(out);
        serializer.config(SerializerFeature.UseSingleQuotes, true);

        MapSerializer mapSerializer = new MapSerializer();
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("A", "1");
        map.put("B", "2");
        mapSerializer.write(serializer, map);

        Assert.assertEquals("{'A':'1','B':'2'}", out.toString());
    }

    public void test_special_s() throws Exception {
        SerializeWriter out = new SerializeWriter();

        MapSerializer mapSerializer = new MapSerializer();
        mapSerializer.write(new JSONSerializer(out), Collections.singletonMap("A\nB", 1));

        Assert.assertEquals("{\"A\\nB\":1}", out.toString());
    }

    public void test_special2_s() throws Exception {
        SerializeWriter out = new SerializeWriter();

        MapSerializer mapSerializer = new MapSerializer();
        mapSerializer.write(new JSONSerializer(out), Collections.singletonMap("A\nB", 1));

        Assert.assertEquals("{\"A\\nB\":1}", out.toString());
    }

    public void test_special3_s() throws Exception {
        SerializeWriter out = new SerializeWriter();

        MapSerializer mapSerializer = new MapSerializer();
        mapSerializer.write(new JSONSerializer(out), Collections.singletonMap("A\nB", Collections.EMPTY_MAP));

        Assert.assertEquals("{\"A\\nB\":{}}", out.toString());
    }

    public void test_4() throws Exception {
        SerializeWriter out = new SerializeWriter();
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("TOP", "value");
        map.put("bytes", new byte[] { 1, 2 });

        MapSerializer mapSerializer = new MapSerializer();
        mapSerializer.write(new JSONSerializer(out), map);

        Assert.assertEquals("{\"TOP\":\"value\",\"bytes\":[1,2]}", out.toString());
    }
}