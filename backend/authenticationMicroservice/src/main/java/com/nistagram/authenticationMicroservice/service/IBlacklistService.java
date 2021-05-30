package com.nistagram.authenticationMicroservice.service;

public interface IBlacklistService {

    Boolean isBlacklisted(String password);
}
