package com.example.javaProj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Rating {
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> usersRated;
    private int mark; // не сказано о том что нельзя делать ещё модели, насколько правильно
    //сделать модель Mark или Review чтобы хранить как того кто поставил отзыв + самой оценки
    // ( с возможным описанием) просто тут я не знаю как можно помнить о том какой пользователь дал какую
    // оценку.

}
