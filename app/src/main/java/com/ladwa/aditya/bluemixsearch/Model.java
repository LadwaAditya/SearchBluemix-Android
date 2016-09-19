package com.ladwa.aditya.bluemixsearch;

import java.util.List;

/**
 * Created by Aditya on 18-Sep-16.
 */

public class Model {

    private String total_rows;
    private List<Rows> rows;

    public String getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(String total_rows) {
        this.total_rows = total_rows;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    public class Rows {
        private String id;
        private Fields fields;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Fields getFields() {
            return fields;
        }

        public void setFields(Fields fields) {
            this.fields = fields;
        }
    }

    public class Fields {
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
