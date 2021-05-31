package com.nistagram.authenticationmicroservice.service;

public interface IBlacklistService {

    Boolean isBlacklisted(String password);
}
