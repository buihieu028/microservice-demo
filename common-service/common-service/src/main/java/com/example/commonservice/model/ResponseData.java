package com.example.commonservice.model;

public class ResponseData {
  protected String code;
  protected Object data;
  protected String message;

  public ResponseData() {
  }

  public ResponseData(String code, Object data) {
    this.code = code;
    this.data = data;
  }

  public ResponseData(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public ResponseData(String code, Object data, String message) {
    this.code = code;
    this.data = data;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
