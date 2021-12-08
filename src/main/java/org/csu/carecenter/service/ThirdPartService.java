package org.csu.carecenter.service;


public interface ThirdPartService {

    String getRfidFromRedis();

    Boolean sendOrder() throws InterruptedException;

}
