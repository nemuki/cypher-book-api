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
        val headerNames = request.headerNames
        while (headerNames.hasMoreElements()) {
            // ヘッダ名と値を取得
            val headerName = headerNames.nextElement()
            val headerValue = request.getHeader(headerName)

            logger.debug("$headerName = $headerValue")
        }
        logger.debug("Method = ${request.method}")
        logger.debug("URI = ${request.requestURI}")

        val requestWrapper = ContentCachingRequestWrapper(request)
        val requestParameterNames = requestWrapper.parameterNames
        while (requestParameterNames.hasMoreElements()) {
            val parameterName = requestParameterNames.nextElement()
            val parameterValue = request.getParameter(parameterName)
            logger.debug("Body = $parameterName $parameterValue")
        }
        return true
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BookController::class.java)
    }
}
