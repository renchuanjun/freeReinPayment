package org.open.demo.service;


import org.open.model.FQResult;

public interface IDemoService {

    FQResult<Object> getDemoService(String id);

    FQResult<Object> getDemoService2(String id);
}
