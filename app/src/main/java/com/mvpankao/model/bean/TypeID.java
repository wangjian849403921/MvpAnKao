package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/19
 */

public class TypeID {

    /**
     * code : 200
     * result : 查询成功
     * object : [{"data":[{"product_category_name":"连接件","product_category_create_date":1482892220000,"product_category_type":0,"id":1,"product_category_level":1},{"product_category_name":"电缆输电系统","product_category_create_date":1484059815000,"product_category_type":0,"id":4,"product_category_level":0},{"product_category_name":"GIL输电系统","product_category_create_date":1484119107000,"product_category_type":0,"id":6,"product_category_level":0}],"name":"productType"}]
     */

    private int code;
    private String result;
    private List<ObjectBean> object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * data : [{"product_category_name":"连接件","product_category_create_date":1482892220000,"product_category_type":0,"id":1,"product_category_level":1},{"product_category_name":"电缆输电系统","product_category_create_date":1484059815000,"product_category_type":0,"id":4,"product_category_level":0},{"product_category_name":"GIL输电系统","product_category_create_date":1484119107000,"product_category_type":0,"id":6,"product_category_level":0}]
         * name : productType
         */

        private String name;
        private List<DataBean> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * product_category_name : 连接件
             * product_category_create_date : 1482892220000
             * product_category_type : 0
             * id : 1
             * product_category_level : 1
             */

            private String product_category_name;
            private long product_category_create_date;
            private int product_category_type;
            private String id;
            private int product_category_level;

            public String getProduct_category_name() {
                return product_category_name;
            }

            public void setProduct_category_name(String product_category_name) {
                this.product_category_name = product_category_name;
            }

            public long getProduct_category_create_date() {
                return product_category_create_date;
            }

            public void setProduct_category_create_date(long product_category_create_date) {
                this.product_category_create_date = product_category_create_date;
            }

            public int getProduct_category_type() {
                return product_category_type;
            }

            public void setProduct_category_type(int product_category_type) {
                this.product_category_type = product_category_type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getProduct_category_level() {
                return product_category_level;
            }

            public void setProduct_category_level(int product_category_level) {
                this.product_category_level = product_category_level;
            }
        }
    }
}
