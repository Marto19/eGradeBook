package com.egradebook.eGradeBook.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//generate javadoc
/**
 * This class is responsible for converting the roles to a format that Spring Security can understand - with prefix
 */
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter =
            new JwtGrantedAuthoritiesConverter();

    @Value("${jwt.auth.converter.resource-id.principle-attribute}") //we take the principle attribute from the application.properties file
    private String principleAttribute;

    @Value("${jwt.auth.converter.resource-id}") //we extract the resource id from the application.properties file
    private String resourceId;


    /**
     * This method is responsible for converting the roles to a format that Spring Security can understand - with prefix
     * @param jwt the jwt token
     * @return the converted token
     */
    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt)
    {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream())
                .collect(Collectors.toSet());

        return new JwtAuthenticationToken(
                jwt,
                authorities,
                gerPrincipleClaimName(jwt));
    }

    private String gerPrincipleClaimName(Jwt jwt)
    {     //we extract the "preferred_username" claim from the jwt token
        String claimName = JwtClaimNames.SUB; //we use the sub claim as the default claim, in case the preferred_username is not present

        if(principleAttribute != null)
        {
            claimName = principleAttribute;
        }
        return jwt.getClaim(claimName);
    }

    /**
     * This method is responsible for extracting the roles from the jwt token
     *
     * The roles are extracted from the resource_access claim in the jwt token
     * @param jwt the jwt token
     * @return the roles
     */
    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt)
    {
        Map<String, Object> resourceAccess;

        Map<String, Object> resource;

        Collection<String> resourceRoles;
        //if we don't have the resource_access claim, we return an empty collection
        if(jwt.getClaim("resource_access") == null)
        {
            return Set.of(); //return empty collection
        }
        //if we have it, we extract it
        resourceAccess = jwt.getClaim("resource_access");

        //if we don't have the e-grade-book resource, we return an empty collection
        if(resourceAccess.get(resourceId) == null)
        {    //resource id, which is the client id, TODO: make it so that its not embedded
            return Set.of(); //return empty collection
        }

        //casting the resource to a map
        resource = (Map<String, Object>) resourceAccess.get(resourceId);

        //extracting the roles
        resourceRoles = (Collection<String>) resource.get("roles");

        //returning the roles with the ROLE_ prefix
        return resourceRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) //adding the ROLE_ prefix
                .collect(Collectors.toSet());
     }

}
