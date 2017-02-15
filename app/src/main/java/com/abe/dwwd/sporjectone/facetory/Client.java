package com.abe.dwwd.sporjectone.facetory;

/**
 * Created by Administrator on 2017/2/15.
 */

public class Client {
    public Client() {
        ExportDBOperator op = new ExportDBOperator();
        String data = "我的数据";
        op.export(data);
    }
}
