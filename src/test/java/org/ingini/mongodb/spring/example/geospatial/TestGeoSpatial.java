package org.ingini.mongodb.spring.example.geospatial;

import org.ingini.mongodb.spring.example.configuration.AppConfig;
import org.ingini.mongodb.spring.example.domain.zip.ZipData;
import org.ingini.mongodb.spring.example.util.CollectionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class TestGeoSpatial {

    public static final String DB_NAME = "aggregation_test_db";
    public static final String ZIP_CODES_COLLECTION_NAME = "zip_codes";

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Command line import: mongoimport --drop -d aggregation_test_db -c zipcodes zips.json
     * <p>
     * <p/>
     * db.zip_codes.find({loc: {$near : {$geometry : {type: 'Point', coordinates: [-122.252696, 37.900933] }},
     * $maxDistance: 10*1000 }});
     * </p>
     */
    @Test
    public void shouldFindAllTownsWithinRadius10km() {
        //GIVEN
        CollectionManager.cleanAndFill(mongoTemplate.getDb(), "zips.json", ZipData.COLLECTION_NAME);
//        mongoTemplate.indexOps(ZipData.class).ensureIndex(new GeospatialIndex("loc")"{loc: '2dsphere'}");

        int lowerLimit = 10 * 1000;

        //WHEN
//        List<ZipData> results = Lists.newArrayList(zipCodes.find("{loc: {$near : {$geometry : {type: 'Point', " +
//                "coordinates: [-122.252696, 37.900933] }}, $maxDistance: # }}", lowerLimit)
//                .as(ZipData.class));
//
//        //THEN
//        assertThat(results).hasSize(19);
    }

    /**
     * Command line import: mongoimport --drop -d aggregation_test_db -c zipcodes zips.json
     * <p>
     * db.zip_codes.find({loc: {$near : {$geometry : {type: 'Point', coordinates: [-122.252696, 37.900933] }},
     * $maxDistance: 10*1000 }, pop : {$gt: 10*1000}});
     * </p>
     */
    @Test
    public void shouldFindAllNearbyTownsWithPopulationOver20Thousands() {
        //GIVEN
        int lowerLimit = 10 * 1000;
        int population = 20 * 1000;

        //WHEN
//        List<ZipData> results = Lists.newArrayList(zipCodes.find("{loc: {$near : {$geometry : {type: 'Point', " +
//                "coordinates: [-122.252696, 37.900933] }}, $maxDistance: # }, pop : {$gt: #}}", lowerLimit, population)
//                .as(ZipData.class));
//
//        //THEN
//        assertThat(results).hasSize(8);
    }
}
