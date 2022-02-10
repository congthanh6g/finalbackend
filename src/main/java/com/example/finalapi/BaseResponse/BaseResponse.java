package com.example.finalapi.BaseResponse;

public class BaseResponse<T> {
    public int totalItems;
    public T datas;
    public int totalPages;
    public int currentPage;
}
