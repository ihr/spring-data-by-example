package org.ingini.mongodb.spring.example.domain.aggregation;

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
public class AggregatedStateData {

    private final String _id;

    private final int pop;


    public AggregatedStateData(String _id, int pop) {
        this._id = _id;
        this.pop = pop;
    }


    public String getState() {
        return _id;
    }

    public int getPopulation() {
        return pop;
    }
}
