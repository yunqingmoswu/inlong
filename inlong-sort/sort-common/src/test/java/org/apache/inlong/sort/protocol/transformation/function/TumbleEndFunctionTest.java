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

package org.apache.inlong.sort.protocol.transformation.function;

import org.apache.inlong.sort.formats.common.TimestampFormatInfo;
import org.apache.inlong.sort.protocol.FieldInfo;
import org.apache.inlong.sort.protocol.transformation.Function;
import org.apache.inlong.sort.protocol.transformation.FunctionBaseTest;
import org.apache.inlong.sort.protocol.transformation.StringConstantParam;
import org.apache.inlong.sort.protocol.transformation.TimeUnitConstantParam;
import org.apache.inlong.sort.protocol.transformation.TimeUnitConstantParam.TimeUnit;

/**
 * Test for {@link TumbleEndFunction}
 */
public class TumbleEndFunctionTest extends FunctionBaseTest {

    @Override
    public Function getFunction() {
        return new TumbleEndFunction(new FieldInfo("time_field", new TimestampFormatInfo()),
                new StringConstantParam("1"),
                new TimeUnitConstantParam(TimeUnit.SECOND));
    }

    @Override
    public String getExpectFormat() {
        return "TUMBLE_END(`time_field`, INTERVAL '1' SECOND)";
    }

    @Override
    public String getExpectSerializeStr() {
        return "{\"type\":\"tumbleEnd\",\"timeAttr\":{\"type\":\"base\",\"name\":\"time_field\","
                + "\"formatInfo\":{\"type\":\"timestamp\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"precision\":2}},"
                + "\"interval\":{\"type\":\"stringConstant\",\"value\":\"1\"},"
                + "\"timeUnit\":{\"type\":\"timeUnitConstant\",\"timeUnit\":\"SECOND\",\"value\":\"SECOND\"}}";

    }
}
