package com.fitucab.ds1617b.fitucab.Model;

import com.fitucab.ds1617b.fitucab.Helper.LocalDate;

/**
 * Created by marvian on 27/05/17.
 */




    public class Diet extends Entity {
        private int _id;
        private int _calorie;
        private LocalDate _dateTime;
        private String _date;
        private String _food;
        private String _moment;
        private String _username;


        public Diet() {
        }

        public Diet(String moment, LocalDate dateTime, String username) {

            this._moment = moment;
            this._dateTime = dateTime;
            this._username = username;

        }

        public Diet(int id, int calorie, LocalDate dateTime, String food) {
            this._id = id;
            this._calorie = calorie;
            this._dateTime = dateTime;
            this._food = food;
        }

        public Diet(String date, String username) {

            this._date = date;
            this._username = username;

        }

        public Diet(int calorie, String food, String moment, String username) {
            this._calorie = calorie;
            this._food = food;
            this._moment = moment;
            this._username = username;
        }

        public Diet(String username) {
            this._username = username;
        }

        public Diet(int id, int calorie, LocalDate dateTime) {
            this._id = id;
            this._calorie = calorie;
            this._dateTime = dateTime;
        }

        public Diet(int calorie, LocalDate dateTime) {
            this._calorie = calorie;
            this._dateTime = dateTime;
        }

        public Diet(LocalDate dateTime) {
            this._dateTime = dateTime;
        }

        public Diet(int calorie) {
            this._calorie = calorie;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int id) {
            this._id = id;
        }

        public int get_calorie() {
            return _calorie;
        }

        public void set_calorie(int calorie) {
            this._calorie = calorie;
        }

        public LocalDate get_dateTime() {
            return _dateTime;
        }

        public void set_dateTime(LocalDate dateTime) {
            this._dateTime = dateTime;
        }

        public String get_food() {
            return _food;
        }

        public void set_food(String food) {
            this._food = food;
        }

        public String get_moment() {
            return _moment;
        }

        public void set_moment(String moment) {
            this._moment = moment;
        }

        public String get_username() {
            return _username;
        }

        public void set_username(String username) {
            this._username = username;
        }
    }


