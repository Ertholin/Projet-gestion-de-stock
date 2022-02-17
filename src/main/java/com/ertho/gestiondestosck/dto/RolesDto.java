package com.ertho.gestiondestosck.dto;

import com.ertho.gestiondestosck.model.Roles;
import com.ertho.gestiondestosck.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RolesDto {

    private Integer id;
    private String roleName;
    private Utilisateur utilisateur;

    public static RolesDto fromEntity(Roles roles){
        if(roles == null){
            return null;
        }
        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .build();
    }

    public static Roles toEntity(RolesDto rolesDto){
        if(rolesDto == null){
            return null;
        }
        Roles roles = new Roles();
            roles.setId(rolesDto.getId());
            roles.setRoleName(rolesDto.getRoleName());
        return roles;
    }

}
