package org.ingini.mongodb.spring.example.domain.weapons;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Copyright (c) 2013 Ivan Hristov
 * <p/>
 * Licensed under the Apa   che License, Version 2.0 (the "License");
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
@Document(collection = "weapons")
public class Weapon {

    public static final String MATERIAL = "material";

    public static final String DETAILS = "details";

    private final String _id;

    private final String material;

    private final WeaponDetails details;

    public Weapon(String _id, String material, WeaponDetails details) {
        this._id = _id;
        this.material = material;
        this.details = details;
    }

    public String getId() {
        return _id;
    }

    public String getMaterial() {
        return material;
    }

    public WeaponDetails getDetails() {
        return details;
    }
}
