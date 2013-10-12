package org.ingini.mongodb.spring.example.domain.aggregation;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Copyright (c) 2013 Ivan Hristov
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class AggregatedNameData {


    public static final String ID = "_id";
    public static final String COUNTER = "counter";

    private final String _id;
    private final int counter;

    public AggregatedNameData(String _id, int counter) {
        this._id = _id;
        this.counter = counter;
    }

    public String get_id() {
        return _id;
    }

    public int getCounter() {
        return counter;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AggregatedNameData)) return false;

        AggregatedNameData aggregatedNameData = (AggregatedNameData) o;

        return new EqualsBuilder().append(this._id, aggregatedNameData._id).append(this.counter, aggregatedNameData.counter).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 51).append(_id).append(counter).toHashCode();
    }
}
