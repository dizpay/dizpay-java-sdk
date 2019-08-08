package com.dizpay.sample.servlet;

import com.alibaba.fastjson.JSONObject;
import com.dizpay.api.DizpayClient;
import com.dizpay.api.common.RestResult;
import com.dizpay.api.impl.DefaultDizpayClient;
import com.dizpay.api.request.checkout.CheckOutInvoiceRequest;
import com.dizpay.api.response.checkout.CheckOutInvoiceResponse;
import com.dizpay.sample.config.DizPayConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * check out servlet
 */
public class CheckOutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // accept amount
        String amount = request.getParameter("amount");
        if (StringUtils.isBlank(amount)) {
            amount = "0.1";
        }
        // assemble checkoutModel parameters
        CheckOutInvoiceRequest checkOutInvoiceRequest = new CheckOutInvoiceRequest();
        checkOutInvoiceRequest.setOrderNumber(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        checkOutInvoiceRequest.setName("DizPay Inc.");
        checkOutInvoiceRequest.setDescription("Add crypto to your account.");
        checkOutInvoiceRequest.setLogoUrl("https://cdn.shopifycloud.com/hatchful-web/assets/c3a241ae6d1e03513dfed6f5061f4a4b.png");
        checkOutInvoiceRequest.setPayerInfo("email");
        checkOutInvoiceRequest.setPricingType("fixed_price");
        checkOutInvoiceRequest.setCurrency("USD");
        checkOutInvoiceRequest.setRate("huobi");
        checkOutInvoiceRequest.setAmount(amount);
        checkOutInvoiceRequest.setCrypto("USDC,TUSD,PAX,GUSD,USDT,ETH,BTC,LTC,DASH");
        checkOutInvoiceRequest.setLocale("auto");
        checkOutInvoiceRequest.setSuccessUrl("https://example.com/diz-pay-result?type=success");
        checkOutInvoiceRequest.setCancelUrl("https://example.com/diz-pay-result?type=failed");
        checkOutInvoiceRequest.setNotifyUrl("https://demo.dizpay.com/webhook");
        checkOutInvoiceRequest.setExtra("another-params");

        // DizpayClient instance
        DizpayClient dizpayClient = new DefaultDizpayClient(DizPayConfig.APP_ID, DizPayConfig.APP_KEY);

        try {
            RestResult<CheckOutInvoiceResponse> responseRestResult = dizpayClient.checkoutInvoice(checkOutInvoiceRequest);
            if (responseRestResult != null && responseRestResult.getData() != null) {
                CheckOutInvoiceResponse checkOutInvoiceResponse = responseRestResult.getData();
                response.sendRedirect(checkOutInvoiceResponse.getPaymentUrl());
            } else {
                response.getWriter().write(JSONObject.toJSONString(responseRestResult));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
