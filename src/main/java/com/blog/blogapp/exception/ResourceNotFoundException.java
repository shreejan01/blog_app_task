package com.blog.blogapp.exception;

public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    long fieldValue;
    String fieldValues;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValues) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValues));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValues = fieldValues;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(long fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(String fieldValues) {
        this.fieldValues = fieldValues;
    }

    @Override
    public String toString() {
        return "ResourceNotFoundException{" +
                "resourceName='" + resourceName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldValue=" + fieldValue +
                ", fieldValues='" + fieldValues + '\'' +
                '}';
    }
}
