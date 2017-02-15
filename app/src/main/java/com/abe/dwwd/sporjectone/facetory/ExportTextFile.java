package com.abe.dwwd.sporjectone.facetory;

/**
 * Created by Administrator on 2017/2/15.
 * 文本文件
 */

public class ExportTextFile implements ExportFileApi{

    @Override
    public void export(String data) {
        System.out.println("导出到文本文件");
    }
}
