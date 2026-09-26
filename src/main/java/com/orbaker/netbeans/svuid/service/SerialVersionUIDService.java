package com.orbaker.netbeans.svuid.service;

import javax.lang.model.element.TypeElement;

public interface SerialVersionUIDService
{
    long generate( TypeElement typeElement );
}
