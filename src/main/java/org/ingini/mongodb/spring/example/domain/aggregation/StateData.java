package org.ingini.mongodb.spring.example.domain.aggregation;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static org.ingini.mongodb.spring.example.domain.aggregation.StateData.*;

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
@Document(collection = COLLECTION_NAME)
public class StateData {

    public static final String COLLECTION_NAME = "zip_codes";

    private final String state;
    @Field("pop")
    private final int population;

    public StateData(String state, int population) {
        this.state = state;
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public String getState() {
        return state;
    }
}
