package org.zerock.j2.controller.interceptor;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zerock.j2.util.JWTUtil;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class JWTInterceptor implements HandlerInterceptor {

    private final JWTUtil jwtUtil;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        if (request.getMethod().equals("OPTIONS")) { //preflight는 통과
            return true;
        }

        try {

            String headerStr = request.getHeader("Authorization");

            if (headerStr == null || headerStr.length() < 7) {
                throw new JWTUtil.CustomJWTException("NullToken");
            }

            // Bearer 액세스토큰 //Bearer는 하나의 인증 타입이다.
            String accessToken = request.getHeader("Authorization").substring(7);

            Map<String, Object> claims = jwtUtil.validateToken(accessToken);

            log.info("resul: " + claims);
        } catch (Exception e) {

            response.setContentType("application/json");

//            response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 401 에러

            Gson gson = new Gson();

            String str = gson.toJson(Map.of("error", e.getMessage()));

            response.getOutputStream().write(str.getBytes());

            return false;

        }

        log.info(">>>preHandle>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(handler);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(jwtUtil);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        return true;

    }

}
