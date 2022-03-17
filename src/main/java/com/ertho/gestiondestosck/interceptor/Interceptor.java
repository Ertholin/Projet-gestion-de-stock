package com.ertho.gestiondestosck.interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.util.StringUtils;

import java.util.Locale;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {
        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")){
            sql = sql + "idEntreprise = 1";
            if(sql.contains("where")){
                sql = sql + "and idEntreprise = 1";
            } else {
                sql = sql + "where idEntreprise = 1";
            }
        }
        return super.onPrepareStatement(sql);
    }
}
