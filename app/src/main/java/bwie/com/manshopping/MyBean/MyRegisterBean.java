package bwie.com.manshopping.MyBean;

/**
 * Created by 黑白 on 2017/9/9.
 */

public class MyRegisterBean extends Basebean {

    /**
     * code : 400
     * datas : {"error":"请填写用户名"}
     */

    private DatasBean datas;

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * error : 请填写用户名
         */

        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
