package dev.nemuki.cypherbookapi.web.interceptor

import dev.nemuki.cypherbookapi.web.controller.BookController
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.util.ContentCachingRequestWrapper
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestInterceptor : HandlerInterceptor {
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        val requestWrapper = ContentCachingRequestWrapper(request)
        // ヘッダー
        val headerNames = requestWrapper.headerNames
        while (headerNames.hasMoreElements()) {
            val headerName = headerNames.nextElement()
            val headerValue = requestWrapper.getHeader(headerName)
            logger.debug("$headerName = $headerValue")
        }
        logger.debug("Method = ${requestWrapper.method}")
        logger.debug("URI = ${requestWrapper.requestURI}")
        // リクエストボディ
        val requestParameterNames = requestWrapper.parameterNames
        while (requestParameterNames.hasMoreElements()) {
            val parameterName = requestParameterNames.nextElement()
            val parameterValue = requestWrapper.getParameter(parameterName)
            logger.debug("$parameterName = $parameterValue")
        }
        return true
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BookController::class.java)
    }
}
