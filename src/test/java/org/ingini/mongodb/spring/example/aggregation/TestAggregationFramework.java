package org.ingini.mongodb.spring.example.aggregation;

import org.ingini.mongodb.spring.example.configuration.AppConfig;
import org.ingini.mongodb.spring.example.domain.aggregation.AggregatedNameData;
import org.ingini.mongodb.spring.example.domain.aggregation.AggregatedStateData;
import org.ingini.mongodb.spring.example.domain.aggregation.NameData;
import org.ingini.mongodb.spring.example.domain.aggregation.StateData;
import org.ingini.mongodb.spring.example.util.CollectionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.fest.assertions.Assertions.assertThat;

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
public class TestAggregationFramework {

    public static final String DB_NAME = "aggregation_test_db";

    public static final String NAME_DAYS_COLLECTION_NAME = "name_days";

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Example taken from http://docs.mongodb.org/manual/tutorial/aggregation-examples/#aggregations-using-the-zip-code-data-set
     * Command line import: mongoimport --drop -d aggregation_test_db -c zipcodes zips.json
     * <p>
     *      db.zipcodes.aggregate(  { $group : { _id : "$state", totalPop : { $sum : "$pop" } } },
     *                              { $match : {totalPop : { $gt : 10*1000*1000 } } } )
     * </p>
     */
    @Test
    public void shouldFindAllStatesWithPopulationOver10Millions() {
        //GIVEN
        CollectionManager.cleanAndFill(mongoTemplate.getDb(), "zips.json", StateData.COLLECTION_NAME);
        int lowerLimit = 10 * 1000 * 1000;

        //WHEN
        AggregationResults<AggregatedStateData> aggregationResult = mongoTemplate.aggregate(
                Aggregation.newAggregation(StateData.class, Aggregation.group("state").sum("population").as("pop"),
                Aggregation.match(Criteria.where("pop").gt(lowerLimit))), AggregatedStateData.class);


        //THEN
        assertThat(aggregationResult.getMappedResults()).hasSize(7);
    }

    /**
     * Example taken from https://github.com/mongodb/mongo-ruby-driver/wiki/Aggregation-Framework-Examples
     * Command line import: mongoimport --drop --db aggregation_test_db --collection name_days name_days.json
     * <p>
     *      db.name_days.aggregate({$project : {names : 1, _id : 0}},
     *                             {$unwind : '$names'},
     *                             {$group : {_id: '$names', counter: {$sum: 1}}},
     *                             {$sort : {counter: -1}},
     *                             {$limit : 10});
     * </p>
     */
    @Test
    public void shouldFindThe10MostCommonNames() {
        //GIVEN
        CollectionManager.cleanAndFill(mongoTemplate.getDb(), "name_days.json", NameData.COLLECTION_NAME);
        int limit = 10;

        //WHEN
        AggregationResults<AggregatedNameData> aggregationResult = mongoTemplate.aggregate(
                Aggregation.newAggregation(NameData.class, Aggregation.project("names").andExclude("_id"),
                Aggregation.unwind("names"), Aggregation.group("names").count().as("counter"),
                Aggregation.sort(Sort.Direction.DESC, "counter"), Aggregation.limit(limit)), AggregatedNameData.class);

        //THEN
        assertThat(aggregationResult.getMappedResults()).hasSize(10);
        assertThat(aggregationResult.getMappedResults().get(0)).isEqualTo(new AggregatedNameData("Jana", 21));

    }
}
