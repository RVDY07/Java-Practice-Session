package com.java.demo.datastore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "users")
public class UserModel {

    @Id
    private String id;
    private String userName;
    private String password;
    private String roles;
    private Boolean active;

    public UserModel(String userName, String password, String roles, Boolean active){
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

}
