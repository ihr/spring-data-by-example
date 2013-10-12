package org.ingini.mongodb.spring.example.delete;

import org.ingini.mongodb.spring.example.configuration.AppConfig;
import org.ingini.mongodb.spring.example.domain.weapons.Weapon;
import org.ingini.mongodb.spring.example.util.CollectionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
public class TestDelete {

    public static final String WEAPONS = "weapons";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void shouldDeleteSingleDocumentById() {
        //GIVEN
        CollectionManager.cleanAndFill(mongoTemplate.getDb(), "weapons.json", WEAPONS);

        assertThat(mongoTemplate.findOne(Query.query(Criteria.where("_id").is("Lightbringer")), Weapon.class)).isNotNull();

        //WHEN
        mongoTemplate.remove(Query.query(Criteria.where("_id").is("Lightbringer")), Weapon.class);

        //THEN
        assertThat(mongoTemplate.findOne(Query.query(Criteria.where("_id").is("Lightbringer")), Weapon.class)).isNull();


    }
}
