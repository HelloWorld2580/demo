package com.dhcc.ms.ims.service.impl.tcc.po;

public class Participant {
  private String globalTransactionId;
  private String branchQualifier;

  private String targetClass;

  private String methodName;

  private Object[] parameterTypes;

  private Object[] args;

  public String getGlobalTransactionId() {
    return globalTransactionId;
  }

  public void setGlobalTransactionId(String globalTransactionId) {
    this.globalTransactionId = globalTransactionId;
  }

  public String getBranchQualifier() {
    return branchQualifier;
  }

  public void setBranchQualifier(String branchQualifier) {
    this.branchQualifier = branchQualifier;
  }

  public String getTargetClass() {
    return targetClass;
  }

  public void setTargetClass(String targetClass) {
    this.targetClass = targetClass;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Object[] getParameterTypes() {
    return parameterTypes;
  }

  public void setParameterTypes(Object[] parameterTypes) {
    this.parameterTypes = parameterTypes;
  }

  public Object[] getArgs() {
    return args;
  }

  public void setArgs(Object[] args) {
    this.args = args;
  }
}
