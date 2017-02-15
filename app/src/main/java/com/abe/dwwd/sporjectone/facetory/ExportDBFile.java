package com.abe.dwwd.sporjectone.facetory;

/**
 * Created by Administrator on 2017/2/15.
 * 数据库文件
 */

public class ExportDBFile implements ExportFileApi{

    @Override
    public void export(String data) {
        System.out.println("导出到数据库文件");
    }
}
