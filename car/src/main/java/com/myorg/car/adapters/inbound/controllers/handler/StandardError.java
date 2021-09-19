package com.myorg.car.adapters.inbound.controllers.handler;

public record StandardError(Integer status,String message,String path,Long timestamp) {
}
