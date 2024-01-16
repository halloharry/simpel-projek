package com.demo.sistem.transaksi.endpoint;

import com.demo.sistem.transaksi.service.TransactionService;
import com.photo.dto.request.RequestTransactionDto;
import com.photo.dto.response.ResponseTransactionDto;
import com.photo.util.IResultDto;
import com.photo.util.core.APIResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionEndpoint {

    private final TransactionService transactionService;

    @PostMapping("top-up")
    public IResultDto<ResponseTransactionDto> topUpBalance(@RequestBody RequestTransactionDto requestTransactionDto, HttpServletRequest request) {
        try {
            return APIResponseBuilder.ok(transactionService.topUpBalance(requestTransactionDto));
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }

    @GetMapping("account/{accountNumber}")
    public IResultDto<ResponseTransactionDto> getAccountDetail(@PathVariable("accountNumber") String accountNumber, HttpServletRequest request) {
        try {
            return APIResponseBuilder.ok(transactionService.getAccountDetail(accountNumber));
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }

    @PostMapping("account/{fromAccountNumber}/transfer")
    public IResultDto<ResponseTransactionDto> transferBalance(@PathVariable("fromAccountNumber") String fromAccountNumber, @RequestBody RequestTransactionDto requestTransactionDto, HttpServletRequest request) {
        try {
            return APIResponseBuilder.ok(transactionService.transferBalance(fromAccountNumber, requestTransactionDto));
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }
}
