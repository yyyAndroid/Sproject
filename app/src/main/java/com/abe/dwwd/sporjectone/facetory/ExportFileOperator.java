package com.abe.dwwd.sporjectone.facetory;

/**
 * Created by Administrator on 2017/2/15.
 */

public class ExportFileOperator extends ExportOperate{

    @Override
    public ExportFileApi newFile() {
        return new ExportTextFile();
    }

    public void export(String data) {
        ExportTextFile file = new ExportTextFile();
        file.export(data);
    }
}
