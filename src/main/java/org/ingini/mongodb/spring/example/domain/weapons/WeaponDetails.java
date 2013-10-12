package org.ingini.mongodb.spring.example.domain.weapons;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
public class WeaponDetails {

    public static final String PROPHECY = "prophecy";
    public static final String CREATOR = "creator";
    private final String prophecy;
    private final String creator;

    public WeaponDetails(String prophecy, String creator) {
        this.prophecy = prophecy;
        this.creator = creator;
    }

    public String getProphecy() {
        return prophecy;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("prophecy", prophecy)
                .append("creator", creator)
                .toString();
    }
}
