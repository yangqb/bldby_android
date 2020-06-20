package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 14:02
 * email: 694125155@qq.com
 */
public class DocBookingInvoiceInfo implements Serializable {
    public String receiptType;
    public int receiptCode;
    public boolean supportElectronicInvoice;
    public List<DocBookingInvoiceTypeListInfo> invoiceTypeList;
}
