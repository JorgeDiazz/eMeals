package com.jorgediaz.emeals.core.network.interceptors;

import androidx.annotation.NonNull;

import com.jorgediaz.emeals.base.data.HttpObject;
import com.jorgediaz.emeals.core.interfaces.Logger;
import com.jorgediaz.emeals.core.network.exceptions.NoConnectionException;
import com.jorgediaz.emeals.core.network.exceptions.ServerException;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ServerInterceptor implements Interceptor {
    private final Logger logger;

    @Inject
    public ServerInterceptor(Logger logger) {
        this.logger = logger;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        try {
            Response response = chain.proceed(request);
            int httpCode = response.code();

            String method = request.method();
            String endpoint = request.url().toString();

            if (httpCode < 400 || httpCode > 500) {
                return response;
            } else {
                JSONObject responseBody = null;
                ServerException serverException;
                try {
                    responseBody = new JSONObject(response.body() == null ? "" : Objects.requireNonNull(response.body()).string());
                    String code = responseBody.optString("code");
                    String message = responseBody.optString("message");
                    serverException = new ServerException(code, message, httpCode);
                } catch (Exception e) {
                    serverException = new ServerException("UNKNOWN", "GENERIC ERROR" + request + " " + response, httpCode);
                }

                HttpObject infoRequest = new HttpObject(
                        method, endpoint, Objects.requireNonNull(responseBody).toString(), httpCode
                );

                logger.http(
                        endpoint,
                        method,
                        infoRequest.toString(),
                        response.toString(),
                        httpCode
                );

                logger.e("Server Exception " + serverException.getMessage(), serverException);

                throw serverException;
            }
        } catch (ServerException | NoConnectionException serverException) {
            throw serverException;
        } catch (IOException e) {
            throw new NoConnectionException();
        } catch (Exception e) {
            throw new ServerException("Network Issue", e.getMessage(), 0);
        }
    }
}
