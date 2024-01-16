package com.demo.sistem.transaksi.service.impl;

import com.demo.sistem.transaksi.service.TransactionService;
import com.photo.dao.UserRepository;
import com.photo.dto.request.RequestTransactionDto;
import com.photo.dto.response.ResponseTransactionDto;
import com.photo.model.User;
import com.photo.util.exceptionn.UserCustomExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final UserRepository userRepository;


    @Override
    public ResponseTransactionDto getAccountDetail(String accountNumber) throws UserCustomExeption {
        User user = userRepository.findByAccountNumber(accountNumber);
        if (user == null) {
            throw new UserCustomExeption("akun tidak ditemukan");
        }

        return ResponseTransactionDto.builder()
                .accountName(user.getName())
                .accountNumber(user.getAccountNumber())
                .balance(user.getBalance())
                .email(user.getEmail())
                .build();
    }

    @Override
    @Transactional
    public ResponseTransactionDto transferBalance(String fromAccountNumber, RequestTransactionDto requestTransactionDto) throws UserCustomExeption {
        User userTransfer = userRepository.findByAccountNumber(fromAccountNumber);
        if (userTransfer == null) {
            throw new UserCustomExeption("akun pengirim tidak ditemukan");
        } else if(userTransfer.getBalance() < requestTransactionDto.getBalance()) {
            throw new UserCustomExeption("saldo tidak cukup untuk melakukan transfer");
        }

        User userReceive = userRepository.findByAccountNumber(requestTransactionDto.getAccountNumber());
        if (userReceive == null) {
            throw new UserCustomExeption("akun penerima tidak ditemukan");
        }

        userTransfer.setBalance(userTransfer.getBalance() - requestTransactionDto.getBalance());
        userRepository.save(userTransfer);

        userReceive.setBalance(userReceive.getBalance() + requestTransactionDto.getBalance());
        userRepository.save(userReceive);


        return ResponseTransactionDto.builder()
                .accountNumber(requestTransactionDto.getAccountNumber())
                .balance(userReceive.getBalance())
                .accountName(userReceive.getName())
                .email(userReceive.getEmail())
                .amount(requestTransactionDto.getBalance())
                .build();
    }

    @Override
    public ResponseTransactionDto topUpBalance(RequestTransactionDto request) throws UserCustomExeption {
        User user = userRepository.findByAccountNumber(request.getAccountNumber());
        if (user == null) {
            throw new UserCustomExeption("akun tidak ditemukan");
        }
        user.setBalance(request.getBalance());
        userRepository.save(user);

        return ResponseTransactionDto.builder().balance(request.getBalance()).build();

    }


}
