package org.sangaizhi.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 过滤器，可以做安全验证
 * @author sangaizhi
 * @date 2017/9/25
 */
@Component
public class MyFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    /**
     * 返回一个字符串代表过滤类型，
     * 在 Zuul 中定义了四种不同生命周期的过滤器类型：
     *     pre：路由之前
     *     routing：路由之时
     *     post：路由之后
     *     error：发生错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断是否要过滤，
     * @return true 需要过滤，false 不需要过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info("Request URL{}, Request Method:{}", request.getRequestURL().toString(), request.getMethod());
        Object token = request.getParameter("token");
        if(null == token){
            logger.info("token is empty");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                requestContext.getResponse().getWriter().write("token is empty");
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        logger.info("ok");
        return null;
    }
}
