package com.example.todo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "users")
@ApiModel(value = "User Nesnesi" ,description = "All details about the User. ")
public class User {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @ApiModelProperty(value = "User Id")
    private Long id;

    @ApiModelProperty(value = "User Name")
    private String userName;
    @ApiModelProperty(value = "User Password")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
