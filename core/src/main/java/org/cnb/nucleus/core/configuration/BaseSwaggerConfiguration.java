package org.cnb.nucleus.core.configuration;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

/**
 * Date of creation 10-May-2020
 *
 * @author Md. Rezaul Hasan
 * @since 1.0
 */
public class BaseSwaggerConfiguration {

  @Value("${security.oauth2.client.client-id}")
  private String clientId;

  @Value("${security.oauth2.client.client-secret}")
  private String clientSecret;

  @Value("${security.oauth2.client.access-token-uri}")
  private String serverUrl;

  public BaseSwaggerConfiguration() {}

  public OAuth securityScheme() {

    List<GrantType> grantTypes = newArrayList();
    GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(this.serverUrl);
    grantTypes.add(creGrant);
    return new OAuth("oauth2schema", getAuthorizationScopeList(), grantTypes);
  }

  private List<AuthorizationScope> getAuthorizationScopeList() {
    List<AuthorizationScope> authorizationScopeList = newArrayList();
    authorizationScopeList.add(new AuthorizationScope("read", "read all"));
    authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
    authorizationScopeList.add(new AuthorizationScope("write", "access all"));
    return authorizationScopeList;
  }

  public SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(this.defaultAuth())
        // .forPaths(PathSelectors.ant("/**"))
        .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope[] authorizationScopes =
        new AuthorizationScope[] {
          new AuthorizationScope("read", "read all"),
          new AuthorizationScope("trust", "trust all"),
          new AuthorizationScope("write", "write all")
        };
    return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
  }

  @Bean
  public SecurityConfiguration securityConfiguration() {

    Map<String, Object> additionalQueryStringParams = new HashMap<>();
    additionalQueryStringParams.put("nonce", "123456");

    return SecurityConfigurationBuilder.builder()
        .clientId(this.clientId)
        .clientSecret(this.clientSecret)
        // .realm("brain-station")
        // .appName("swagger-ui")
        // .additionalQueryStringParams(additionalQueryStringParams)
        .build();
  }
}
