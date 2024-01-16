package com.demo.sistem.transaksi.service;


import com.photo.dto.request.RequestTransactionDto;
import com.photo.dto.response.ResponseTransactionDto;
import com.photo.util.exceptionn.UserCustomExeption;

public interface TransactionService {

    ResponseTransactionDto getAccountDetail(String accountNumber) throws UserCustomExeption;
    ResponseTransactionDto transferBalance(String fromAccountNumber, RequestTransactionDto requestTransactionDto) throws UserCustomExeption;
    ResponseTransactionDto topUpBalance(RequestTransactionDto request) throws UserCustomExeption;
}
