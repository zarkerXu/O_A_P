package cn.com.oa.service;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import cn.com.oa.util.Const;
import cn.com.oa.util.WebUtil;

@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
        	Resource resource = new ClassPathResource("/other.properties");
    		try {
    			Properties props = PropertiesLoaderUtils.loadProperties(resource);
    			Const.HX_URL=(String) props.get("hx.url");
    			Const.HX_CLIENT_SECRET=(String) props.get("hx.client_secret");
    			Const.HX_CLIENT_ID=(String) props.get("hx.client_id");
    			WebUtil.getToken();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
    }

}