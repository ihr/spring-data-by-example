package org.ingini.mongodb.spring.example.update;

import com.google.common.collect.Sets;
import org.bson.types.ObjectId;
import org.ingini.mongodb.spring.example.configuration.AppConfig;
import org.ingini.mongodb.spring.example.domain.beasts.DireWolf;
import org.ingini.mongodb.spring.example.domain.heroes.Hero;
import org.ingini.mongodb.spring.example.domain.heroes.Heroine;
import org.ingini.mongodb.spring.example.domain.heroes.Human;
import org.ingini.mongodb.spring.example.domain.weapons.Weapon;
import org.ingini.mongodb.spring.example.domain.weapons.WeaponDetails;
import org.ingini.mongodb.spring.example.util.CollectionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

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
public class TestUpdate {

    public static final String HEROES = "heroes";
    public static final String WEAPONS = "weapons";

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void shouldAddFieldToTheLightbringer() {
        //GIVEN
        CollectionManager.cleanAndFill(mongoTemplate.getDb(), "weapons.json", WEAPONS);

        WeaponDetails details = new WeaponDetails("The one who pulls out this sword from fire will be named Lord's Chosen ...", "Azor Ahai");

        //WHEN
        mongoTemplate.findAndModify(Query.query(Criteria.where("_id").is("Lightbringer")),
                new Update().set("details", details), Weapon.class);

        //AND WHEN
        Weapon weapon = mongoTemplate.findOne(Query.query(Criteria.where("_id").is("Lightbringer")), Weapon.class);


        //THEN
        assertThat(weapon).isNotNull();
    }

    @Test
    public void shouldAddADireWolfForEachStarkChild() {
        //GIVEN
        CollectionManager.cleanAndFill(mongoTemplate.getDb(), "heroes.json", HEROES);

        Hero eddardStark = mongoTemplate.findOne(Query.query(
                Criteria.where("_id").is(new ObjectId("5259a7fd3004e5974542c5e9"))), Hero.class);

        Set<Human> updatedChildren = Sets.newHashSet();

        for (Human child : eddardStark.getChildren()) {
            if (child.getFirstName().equals("Robb")) {
                updatedChildren.add(Hero.addBeast((Hero) child, new DireWolf("Grey Wind")));
            }

            if (child.getFirstName().equals("Sansa")) {
                updatedChildren.add(Heroine.addBeast((Heroine) child, new DireWolf("Lady")));
            }

            if (child.getFirstName().equals("Arya")) {
                updatedChildren.add(Heroine.addBeast((Heroine) child, new DireWolf("Nymeria")));
            }

            if (child.getFirstName().equals("Bran")) {
                updatedChildren.add(Hero.addBeast((Hero) child, new DireWolf("Summer")));
            }

            if (child.getFirstName().equals("Rickon")) {
                updatedChildren.add(Hero.addBeast((Hero) child, new DireWolf("Shaggydog")));
            }

            if (child.getFirstName().equals("Jon")) {
                updatedChildren.add(Hero.addBeast((Hero) child, new DireWolf("Ghost")));
            }
        }

        Hero updatedEddardStark = Hero.updateChildren(eddardStark, updatedChildren);

        //WHEN
        mongoTemplate.save(updatedEddardStark);

        //THEN manually verify that modification is ok

    }
}
