package com.alligator.blog.Models.Response;

public class OperationStatusResponseModel {
    private String OperationStatus;
    private String OperationName;

    public String getOperationStatus() {
        return OperationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        OperationStatus = operationStatus;
    }

    public String getOperationName() {
        return OperationName;
    }

    public void setOperationName(String operationName) {
        OperationName = operationName;
    }
}
