package com.baidu.medc.baiduapp.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

public class CommentIDTransformUDTF extends GenericUDTF {
    public StructObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        return null;
    }

    public void process(Object[] objects) throws HiveException {
        String param = objects[0].toString();
        System.out.println(param);
    }

    public void close() throws HiveException {

    }
}
