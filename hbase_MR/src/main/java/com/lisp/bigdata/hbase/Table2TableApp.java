package com.lisp.bigdata.hbase;
import com.lisp.bigdata.hbase.Tool.HbaseMRtool;
//import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Table2TableApp {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new HbaseMRtool(), args);

    }
}
