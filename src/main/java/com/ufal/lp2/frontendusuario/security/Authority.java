package com.ufal.lp2.frontendusuario.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{

	private static final long serialVersionUID = 3L;
	private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
