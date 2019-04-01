package tv.muli.mulizuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import tv.muli.mulizuul.constant.RequestConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class FeFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        HttpServletResponse httpServletResponse = requestContext.getResponse();
        String requestUri = httpServletRequest.getRequestURI();
        if (RequestConstant.INDEX_URI.equals(requestUri)) {
            // 如果是访问首页，则判断UA跳转PC或Mobile
            requestContext.setSendZuulResponse(false);
            String userAgent = httpServletRequest.getHeader("user-agent").toLowerCase();
            String redirect = httpServletRequest.getScheme() + "://" + RequestConstant.HOST_PC;
            if (userAgent.contains(RequestConstant.UA_WEIXIN) || userAgent.contains(RequestConstant.UA_IPHONE)
                    || userAgent.contains(RequestConstant.UA_ANDROID) || userAgent.contains(RequestConstant.UA_IPAD)) {
                redirect = httpServletRequest.getScheme() + "://" + RequestConstant.HOST_MOBILE;
            }
            try {
                httpServletResponse.setStatus(HttpStatus.SC_MOVED_PERMANENTLY);
                httpServletResponse.setHeader("location", redirect);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
