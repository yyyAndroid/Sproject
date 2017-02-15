package com.abe.dwwd.sporjectone.facetory;

/**
 * Created by Administrator on 2017/2/15.
 */

public abstract class ExportOperate {

        public abstract ExportFileApi newFile();

    /**
     * 导出数据
     * @param data
     */
       public void export(String data){
            ExportFileApi file = newFile();
       }
}
