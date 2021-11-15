/*
 * Copyright 2021-Current jittagornp.me
 */
package me.jittagornp.defi;

import me.jittagornp.defi.model.TokenInfo;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author jittagornp
 */
public interface DeFi {

    enum Network {
        BSC_MAINNET,
        POLYGON_MAINNET
    }

    Network getNetwork();

    String getWalletAddress();

    String getWalletShortAddress();

    DeFi setDefaultSwapDeadlineMinutes(final int swapDeadlineMinutes);

    DeFi setDefaultSwapSlippage(final double swapSlippage);

    DeFi setTokenAutoApproveNTimes(double tokenAutoApproveNTimes);

    CompletableFuture<BigDecimal> getGasBalance();

    CompletableFuture<BigDecimal> getGasPrice();

    CompletableFuture<BigDecimal> getTokenBalance(final String token);

    CompletableFuture<TransactionReceipt> tokenTransfer(final String token, String recipient, final BigDecimal amount);

    CompletableFuture<BigDecimal> getTokenAmountsOut(final String router, final String tokenA, final String tokenB, final BigDecimal amount);

    CompletableFuture<BigDecimal> getTokenAmountsOutMin(final String router, final String tokenA, final String tokenB, final BigDecimal amount, final double slippage);

    CompletableFuture<BigDecimal> getTokenAmountsOutMin(final String router, final String tokenA, final String tokenB, final BigDecimal amount);

    CompletableFuture<BigDecimal> getTokenPrice(final String tokenA, final String tokenB, final String router);

    CompletableFuture<TokenInfo> getTokenInfo(final String token, final String tokenPair, final String router);

    CompletableFuture<List<TokenInfo>> getTokenInfoList(final List<String> tokens, final Function<String, String> tokenPair, final Function<String, String> tokenRouter);

    CompletableFuture<List<TokenInfo>> getTokenInfoList(final List<String> tokens, final String tokenPair, final String router);

    CompletableFuture<BigDecimal> getTokenAllowance(final String token, final String contractAddress);

    CompletableFuture<TransactionReceipt> tokenApprove(final String token, final BigDecimal amount, final String contractAddress);

    CompletableFuture<TransactionReceipt> tokenSwap(final String router, final String tokenA, final String tokenB, final BigDecimal amount, final double slippage, final int deadlineMinutes);

    CompletableFuture<TransactionReceipt> tokenSwap(final String router, final String tokenA, final String tokenB, final BigDecimal amount, final double slippage);

    CompletableFuture<TransactionReceipt> tokenSwap(final String router, final String tokenA, final String tokenB, final BigDecimal amount);

    CompletableFuture<TransactionReceipt> tokenSwapAndAutoApprove(final String router, final String tokenA, final String tokenB, final BigDecimal amount, final double slippage, final int deadlineMinutes);

    CompletableFuture<TransactionReceipt> tokenSwapAndAutoApprove(final String router, final String tokenA, final String tokenB, final BigDecimal amount, final double slippage);

    CompletableFuture<TransactionReceipt> tokenSwapAndAutoApprove(final String router, final String tokenA, final String tokenB, final BigDecimal amount);

    CompletableFuture<TransactionReceipt> fillGas(final String gasToken, final BigDecimal amount);

    CompletableFuture<TransactionReceipt> tokenSwapAndFillGas(final String router, final String token, final String gasToken, final BigDecimal amount);

    void onBlock(final Consumer<EthBlock.Block> consumer, final long throttleMillisec);

    void onBlock(final Consumer<EthBlock.Block> consumer);
}