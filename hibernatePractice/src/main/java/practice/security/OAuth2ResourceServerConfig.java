package practice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    JwtAccessTokenConverter accessTokenConverter;
    TokenStore tokenStore;
    DefaultTokenServices tokenServices;

    public OAuth2ResourceServerConfig(JwtAccessTokenConverter accessTokenConverter, TokenStore tokenStore, DefaultTokenServices tokenServices) {
        this.accessTokenConverter = accessTokenConverter;
        this.tokenStore = tokenStore;
        this.tokenServices = tokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices);
    }

}
