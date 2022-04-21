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

import com.google.common.base.Preconditions;
import lombok.Data;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@Data
public class GroupInfo implements Serializable {

    private static final long serialVersionUID = 6034630524669634079L;

    @JsonProperty("groupId")
    private String id;
    @JsonProperty("streams")
    private List<StreamInfo> streams;

    @JsonCreator
    public GroupInfo(@JsonProperty("groupId") String id,
            @JsonProperty("streams") List<StreamInfo> streams) {
        this.id = Preconditions.checkNotNull(id, "groupId is null");
        this.streams = Preconditions.checkNotNull(streams, "streams is null");
        Preconditions.checkState(!streams.isEmpty(), "streams is empty");
    }
}
