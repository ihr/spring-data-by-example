package org.ingini.mongodb.spring.example.read;

import org.bson.types.ObjectId;
import org.ingini.mongodb.spring.example.configuration.AppConfig;
import org.ingini.mongodb.spring.example.domain.characters.Gender;
import org.ingini.mongodb.spring.example.domain.characters.Hero;
import org.ingini.mongodb.spring.example.domain.characters.Heroine;
import org.ingini.mongodb.spring.example.domain.characters.HumanCharacter;
import org.ingini.mongodb.spring.example.util.CollectionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
public class TestFindOne {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void shouldFindOneEntryBasedOnOIDOperator() {
        //GIVEN
        CollectionManager.cleanAndFill(mongoTemplate.getDb(), "characters.json", HumanCharacter.COLLECTION_NAME);

        //WHEN
        Hero hero = mongoTemplate.findOne(Query.query(
                Criteria.where("_id").is(new ObjectId("52516b563004ba6b745e864f"))), Hero.class);

        //THEN
        assertThat(hero).isNotNull();

    }

    @Test
    public void shouldFindOneEntryBasedOnObjectId() {
        //GIVEN
        CollectionManager.cleanAndFill(mongoTemplate.getDb(), "characters.json", HumanCharacter.COLLECTION_NAME);

        //WHEN
        Hero hero = mongoTemplate.findById(new ObjectId("52516b563004ba6b745e864f"), Hero.class);

        //THEN
        assertThat(hero).isNotNull();

    }


    @Test
    public void shouldFindOneEntryBasedOnGenderAndFirstName() {
        //GIVEN
        CollectionManager.cleanAndFill(mongoTemplate.getDb(), "characters.json", HumanCharacter.COLLECTION_NAME);

        //WHEN
        Heroine heroine = mongoTemplate.findOne(
                Query.query(Criteria.where("gender").is(Gender.FEMALE).and("first_name").is("Arya")), Heroine.class);

        //THEN
        assertThat(heroine).isNotNull();

    }

    @Test
    public void shouldFindOneArrayElement() {
        //GIVEN
        CollectionManager.cleanAndFill(mongoTemplate.getDb(), "characters.json", HumanCharacter.COLLECTION_NAME);

        //WHEN
        Heroine heroine = mongoTemplate.findOne(new BasicQuery(
                Query.query(Criteria.where("_id").is(new ObjectId("52516b563004ba6b745e864f"))).getQueryObject(),
                Query.query(Criteria.where("children").elemMatch(
                        Criteria.where("first_name").is("Sansa").and("last_name").is("Stark"))).getFieldsObject()), Heroine.class);

        //THEN
        assertThat(heroine).isNotNull();

    }
}
