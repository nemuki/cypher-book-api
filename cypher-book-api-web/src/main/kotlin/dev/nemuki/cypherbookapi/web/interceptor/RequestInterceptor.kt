package dev.nemuki.cypherbookapi.web.interceptor

import dev.nemuki.cypherbookapi.web.controller.BookController
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
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
        return true
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BookController::class.java)
    }
}
