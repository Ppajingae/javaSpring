package com.yoong.javaspring2.java_study.object.equals;

import java.util.Objects;

public class UserV2 {

    private final String id;

    public String getId() {
        return id;
    }

    public UserV2(String id) {
        this.id = id;
    }

//    @Override
//    public boolean equals(Object obj) {
//
//        UserV2 user = (UserV2) obj;
//
//        return id.equals(user.getId());
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserV2 userV2 = (UserV2) o;
        return Objects.equals(id, userV2.id);
    }

}
