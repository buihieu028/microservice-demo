package com.example.commonservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class BaseEntity {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "createdAt")
  @Column(name = "created_at")
  private long createdAt;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "updatedAt")
  @Column(name = "updated_at")
  private long updatedAt;

  public BaseEntity() {
  }

  public BaseEntity(long createdAt, long updatedAt) {
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  public long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(long updatedAt) {
    this.updatedAt = updatedAt;
  }
}
