
package com.dhcc.ms.ims.dto.request;

import javax.validation.constraints.NotNull;

public class JolokiaDtoReq {
  @NotNull
  private String type;
  private String mbean;
  private String attribute;
  private Object value;
  private String operation;
  private String[] arguments;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMbean() {
    return mbean;
  }

  public void setMbean(String mbean) {
    this.mbean = mbean;
  }

  public String getAttribute() {
    return attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String[] getArguments() {
    return arguments;
  }

  public void setArguments(String[] arguments) {
    this.arguments = arguments;
  }

}
