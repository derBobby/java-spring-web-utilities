package eu.planlos.javaspringwebutilities.web;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class WebClientRetryFilter {

    public static boolean shouldRetry(Throwable throwable) {
        if (throwable instanceof WebClientResponseException responseException) {
            HttpStatusCode statusCode = responseException.getStatusCode();
            return !statusCode.is5xxServerError() && !statusCode.is4xxClientError();
        }
        return true;
    }
}