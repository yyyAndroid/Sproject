package com.abe.dwwd.sporjectone.facetory;

/**
 * Created by Administrator on 2017/2/15.
 */

public class ExportDBOperator extends ExportOperate{

    @Override
    public ExportFileApi newFile() {
        return new ExportDBFile();
    }

    public void export(String data){
        ExportDBFile file = new ExportDBFile();
        file.export(data);
    }
}
