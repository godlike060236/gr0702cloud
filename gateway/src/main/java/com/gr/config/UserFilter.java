package com.gr.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

// 将组件放入IoC容器
@Component
// 用户后端权限过滤器
public class UserFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 如果向请求头中加入数据后,则发请求时会先发一个option请求,在option请求下,无法拿到任何数据
        ServerHttpRequest request = exchange.getRequest();
        if (request.getMethod().name().equals("OPTIONS")) {
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        if (headers.get("token") != null) {
            List<String> list = headers.get("token");
            String token = list.get(0);
            // 反解析token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("guorui")).build();
            DecodedJWT verify = jwtVerifier.verify(token);
            String username = verify.getClaim("username").asString();
            // System.out.println("当前登录用户:" + username);
            List<String> backUrl = verify.getClaim("backUrl").asList(String.class);
            // System.out.println("具有的后端权限:" + backUrl);
            // 从request拿到当前请求地址
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
