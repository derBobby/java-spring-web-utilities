package eu.planlos.javaspringwebutilities.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
public class WebClientRetryFilter {

    public static boolean shouldRetry(Throwable throwable) {
        log.warn("Testing exception for retry. Class is {}", throwable.getClass());
        if (throwable instanceof WebClientResponseException responseException) {
            HttpStatusCode statusCode = responseException.getStatusCode();
            boolean isNotHttp400or500 = !statusCode.is5xxServerError() && !statusCode.is4xxClientError();
            log.warn("Is NOT http/4xx or http/500 status code -> No retry: {}", isNotHttp400or500);
            return isNotHttp400or500;
        }
        return true;
    }
}