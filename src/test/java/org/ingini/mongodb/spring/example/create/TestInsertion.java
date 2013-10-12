package org.ingini.mongodb.spring.example.create;

import com.google.common.collect.Sets;
import org.ingini.mongodb.spring.example.configuration.AppConfig;
import org.ingini.mongodb.spring.example.domain.heroes.*;
import org.ingini.mongodb.spring.example.domain.weapons.Weapon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

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
public class TestInsertion {

    public static final String HEROES = "heroes";
    public static final String WEAPONS = "weapons";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void beforeEachTest() {
        mongoTemplate.dropCollection(HEROES);
    }

    @Test
    public void shouldInsertOneHeroineWithAutomaticObjectId() {
        //GIVEN
        Heroine aryaStark = Heroine.createHeroineWithoutChildrenAndNoBeasts("Arya", "Stark", //
                new Address("Winterfell", "Westeros", Region.THE_NORTH));

        //WHEN
        mongoTemplate.insert(aryaStark);

        //THEN manually check if we've correctly inserted the document
    }

    @Test
    public void shouldInsertOneHeroWithAutomaticObjectId() {
        //GIVEN
        Address castleWinterfell = new Address("Winterfell", "Westeros", Region.THE_NORTH);

        Set<Human> children = Sets.newHashSet();
        children.add(Hero.createHeroWithoutChildrenAndNoBeasts("Robb", "Stark", castleWinterfell));
        children.add(Heroine.createHeroineWithoutChildrenAndNoBeasts("Sansa", "Stark", castleWinterfell));
        children.add(Heroine.createHeroineWithoutChildrenAndNoBeasts("Arya", "Stark", castleWinterfell));
        children.add(Hero.createHeroWithoutChildrenAndNoBeasts("Bran", "Stark", castleWinterfell));
        children.add(Hero.createHeroWithoutChildrenAndNoBeasts("Rickon", "Stark", castleWinterfell));
        children.add(Hero.createHeroWithoutChildrenAndNoBeasts("Jon", "Snow", castleWinterfell));

        Hero eddardStark = Hero.createHeroWithoutBeasts("Eddard", "Stark", castleWinterfell, children);

        //WHEN
        mongoTemplate.insert(eddardStark);

        //THEN manually check if we've correctly inserted the document
    }

    @Test
    public void shouldInsertOneSwordWithCustomObjectId() {
        //GIVEN
        mongoTemplate.dropCollection(Weapon.class);
        Weapon lightbringer = new Weapon("Lightbringer", null, null);

        //WHEN
        mongoTemplate.insert(lightbringer);

        //THEN manually check if we've correctly inserted the document
    }
}