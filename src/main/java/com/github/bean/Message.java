package com.github.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by bs.yang on 11/27/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message implements Serializable {
    private Long id;
    private String strObj;
    private float floatObj;
    private List<Double> doubleObjList;
    private boolean boolObj;
    private byte[] bytesObj;
    private int int32Obj;
    private long int64Obj;
    private InnerMessage innerMessageObj;
    private Date createDate;
}
