/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.sort.protocol;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.inlong.sort.formats.common.StringFormatInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldInfoTest {

    @Test
    public void testSerialize() throws JsonProcessingException {
        FieldInfo fieldInfo = new FieldInfo("field_name", StringFormatInfo.INSTANCE);
        ObjectMapper objectMapper = new ObjectMapper();
        String expected = "{\"type\":\"base\",\"name\":\"field_name\",\"format_info\":{\"type\":\"string\"}}";
        assertEquals(expected, objectMapper.writeValueAsString(fieldInfo));
    }

    @Test
    public void testDeserialize() throws JsonProcessingException {
        FieldInfo fieldInfo = new FieldInfo("field_name", StringFormatInfo.INSTANCE);
        ObjectMapper objectMapper = new ObjectMapper();
        String fieldInfoStr = "{\"type\":\"base\",\"name\":\"field_name\",\"format_info\":{\"type\":\"string\"}}";
        FieldInfo expected = objectMapper.readValue(fieldInfoStr, FieldInfo.class);
        assertEquals(expected, fieldInfo);
    }

    @Test
    public void testSerializeWithNodeId() throws JsonProcessingException {
        FieldInfo fieldInfo = new FieldInfo("field_name", "1", StringFormatInfo.INSTANCE);
        ObjectMapper objectMapper = new ObjectMapper();
        String expected = "{\"type\":\"base\",\"name\":\"field_name\","
                + "\"format_info\":{\"type\":\"string\"},\"nodeId\":\"1\"}";
        assertEquals(expected, objectMapper.writeValueAsString(fieldInfo));
    }

    @Test
    public void testDeserializeWithNodeId() throws JsonProcessingException {
        FieldInfo fieldInfo = new FieldInfo("field_name", StringFormatInfo.INSTANCE);
        fieldInfo.setNodeId("1L");
        ObjectMapper objectMapper = new ObjectMapper();
        String fieldInfoStr = "{\"type\":\"base\",\"name\":\"field_name\","
                + "\"format_info\":{\"type\":\"string\"},\"nodeId\":\"1\"}";
        FieldInfo expected = objectMapper.readValue(fieldInfoStr, FieldInfo.class);
        assertEquals(expected, fieldInfo);
    }
}
