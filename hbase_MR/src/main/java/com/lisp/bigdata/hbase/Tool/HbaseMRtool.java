package com.lisp.bigdata.hbase.Tool;

import com.lisp.bigdata.hbase.mapper.ScanDataMapper;
import com.lisp.bigdata.hbase.reduce.InsertDataReducer;
import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

public class HbaseMRtool implements Tool {
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance();
        job.setJarByClass(HbaseMRtool.class);

        //mapper
        TableMapReduceUtil.initTableMapperJob(
                "student",
                new Scan(),
                ScanDataMapper.class,
                ImmutableBytesWritable.class,
                Put.class,
                job
        );
        //reducer
        TableMapReduceUtil.initTableReducerJob(
                "new",
                InsertDataReducer.class,
                job
        );
        //执行作业
        boolean b = job.waitForCompletion(true);

        return b?JobStatus.State.SUCCEEDED.getValue():JobStatus.State.FAILED.getValue();
    }

    public void setConf(Configuration conf) {

    }

    public Configuration getConf() {
        return null;
    }
}
