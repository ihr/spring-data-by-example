package org.ingini.mongodb.spring.example.domain.zip;

import org.springframework.data.mongodb.core.mapping.Document;

import static org.ingini.mongodb.spring.example.domain.zip.ZipData.*;

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
public class ZipData {

    public static final String COLLECTION_NAME = "zip_codes";

    public static final String ID = "_id";
    public static final String CITY = "city";
    public static final String LOCATION = "loc";
    public static final String POPULATION = "pop";
    public static final String STATE = "state";

    private final String _id;
    private final String city;
    private final double[] location;
    private final int population;
    private final String state;

    public ZipData(String _id, String city, double[] location,
                   int population, String state) {
        this._id = _id;
        this.city = city;
        this.location = location;
        this.population = population;
        this.state = state;
    }

    public String get_id() {
        return _id;
    }

    public String getCity() {
        return city;
    }

    public double[] getLocation() {
        return location;
    }

    public int getPopulation() {
        return population;
    }

    public String getState() {
        return state;
    }

}
