package com.dizpay.demo.servlet;

import com.alibaba.fastjson.JSONObject;
import com.dizpay.api.common.StatusCodeEnum;
import com.dizpay.api.util.DizpaySignature;
import com.dizpay.api.util.HttpClientUtil;
import com.dizpay.demo.config.DizPayConfig;
import com.dizpay.demo.model.CheckOutRequest;
import com.dizpay.demo.model.CheckOutResponse;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

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
        CheckOutRequest checkOutModel = new CheckOutRequest();
        checkOutModel.setOrderNumber("CHECKOUT_" + UUID.randomUUID());
        checkOutModel.setName("DizPay Inc.");
        checkOutModel.setDescription("Add crypto to your account.");
        checkOutModel.setLogoUrl("https://cdn.shopifycloud.com/hatchful-web/assets/c3a241ae6d1e03513dfed6f5061f4a4b.png");
        checkOutModel.setPayerInfo("email");
        checkOutModel.setPricingType("fixed_price");
        checkOutModel.setCurrency("USD");
        checkOutModel.setAmount(amount);
        checkOutModel.setCrypto("USDC,TUSD,PAX,GUSD,USDT,ETH,BTC,LTC,DASH");
        checkOutModel.setLocale("auto");
        checkOutModel.setTheme("dark");
        checkOutModel.setRedirectUrl("https://example.com/diz-pay-result?type=success");
        checkOutModel.setCancelUrl("https://example.com/diz-pay-result?type=failed");
        checkOutModel.setNotifyUrl("https://demo.dizpay.com/webhook");
        checkOutModel.setExtra("another-params");

        // signature
        String signature = DizpaySignature.signature(checkOutModel, DizPayConfig.APP_ID, DizPayConfig.APP_KEY);
        checkOutModel.setSignature(signature);
        checkOutModel.setAppId(DizPayConfig.APP_ID);

        // call checkout api
        Response checkOutRsp = HttpClientUtil.httpPost(DizPayConfig.CHECK_OUT_URL, JSONObject.toJSONString(checkOutModel));
        if (checkOutRsp != null && StatusCodeEnum.OK.getCode() == checkOutRsp.code()) {
            CheckOutResponse checkOutResponse = JSONObject.parseObject(checkOutRsp.body().string(), CheckOutResponse.class);
            response.sendRedirect(checkOutResponse.getPaymentUrl());
        }
    }
}
